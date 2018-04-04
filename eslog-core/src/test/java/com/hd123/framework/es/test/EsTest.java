package com.hd123.framework.es.test;

import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;


/**
 * Created by LIWEIHUA on 2018-03-23.
 */
public class EsTest {
    private TransportClient client;

    private IndexRequest source;

    public static void main(String[] args){
        EsTest esTest = new EsTest();
        esTest.getElasticsearchClient();
        try {

            esTest.createIndex();
          //  esTest.updateByClient();
            esTest.testGet();
        }catch (Exception e){
            System.out.println(e);
        }
    }





    public TransportClient getElasticsearchClient() {
        try {
            Settings settings = Settings.builder()
                    .put("cluster.name", "elasticsearch")  //连接的集群名
                    .put("client.transport.ignore_cluster_name", true)  //如果集群名不对，也能连接
                    .build();
            //创建client
            client = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new TransportAddress(InetAddress.getByName("172.17.10.155"), 9300));  //主机和端口号
            return client;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public void testInfo() {
        List<DiscoveryNode> nodes = client.connectedNodes();
        for (DiscoveryNode node : nodes) {
            System.out.println(node.getHostAddress());
        }
    }


    public XContentBuilder createJson4() throws Exception {
        // 创建json对象, 其中一个创建json的方式
        XContentBuilder source = XContentFactory.jsonBuilder()
                .startObject()
                .field("user", "kimchy")
                .field("postDate", new Date())
                .field("message", "trying to out ElasticSearch")
                .endObject();
        return source;
    }
    //创建索引，并给索引某些字段指定iK分词，以后向该索引中查询时，就会用ik分词。
    public void createIndex() throws Exception {
        //创建映射
        XContentBuilder source = createJson4();
        // 存json入索引中
        IndexResponse response = client.prepareIndex("twitter", "tweet", "1").setSource(source).get();
//        // 结果获取
        String index = response.getIndex();
        String type = response.getType();
        String id = response.getId();
        long version = response.getVersion();
        //boolean created = response.isCreated();
        System.out.println(index + " : " + type + ": " + id + ": " + version + ": ");
    }

    public void testGet() {
//        GetResponse response = client.prepareGet("twitter", "tweet", "1")
//                                .get();
        GetResponse response = client.prepareGet("twitter", "tweet", "1")
                .setOperationThreaded(false)    // 线程安全
                .get();
        System.out.println(response.getSourceAsString());
    }

    public void testUpdate() throws Exception {
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.index("twitter");
        updateRequest.type("tweet");
        updateRequest.id("1");
        updateRequest.doc(XContentFactory.jsonBuilder()
                .startObject()
                // 对没有的字段添加, 对已有的字段替换
                .field("gender", "male")
                .field("message", "hello")
                .endObject());
        UpdateResponse response = client.update(updateRequest).get();

        // 打印
        String index = response.getIndex();
        String type = response.getType();
        String id = response.getId();
        long version = response.getVersion();
        System.out.println(index + " : " + type + ": " + id + ": " + version);
    }

    public void testDelete() {
        DeleteResponse response = client.prepareDelete("twitter", "tweet", "1")
                .get();
        String index = response.getIndex();
        String type = response.getType();
        String id = response.getId();
        long version = response.getVersion();
        System.out.println(index + " : " + type + ": " + id + ": " + version);
    }



}
