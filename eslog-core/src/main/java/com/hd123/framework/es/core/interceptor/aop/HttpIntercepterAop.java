/*
 *  版权所有(C)，上海海鼎信息工程股份有限公司，2017，所有权利保留。
 *  项目名：api
 *  文件名：RequestLogAop
 *  模块说明：统一记录请求参数与返回参数信息
 *  修改历史：
 *  2017/12/18 - zenglin - 创建
 *
 */
package com.hd123.framework.es.core.interceptor.aop;


import com.hd123.framework.es.client.log.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * todo
 * 1：切点支持自定义路径
 * 2：此方案必须声明成bean且自动实例化
 * 3：
 */

@Component
@Aspect   //定义一个切面
public class HttpIntercepterAop {

    @Autowired
    private Logger logger;

    @Pointcut("@annotation(com.hd123.framework.es.core.annotation.Eslog)")
    public void excudeService() {
    }

    @Around("excudeService()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        try {
            RequestAttributes ra = RequestContextHolder.getRequestAttributes();
            ServletRequestAttributes sra = (ServletRequestAttributes) ra;
            HttpServletRequest request = sra.getRequest();

            //获取请求参数
            String queryString = request.getQueryString();
            //取路径，针对路径参数
            String path = request.getRequestURI();

            String requestParam = StringUtils.isEmpty(queryString) ? path : queryString;

            // result的值就是被拦截方法的返回值
            Object result = pjp.proceed();
            //ResponseResult responseResult = (ResponseResult)result;

            //获取body参数
            String requestBody = getRequestBody(request);

            String params = StringUtils.isEmpty(requestBody) ? requestParam : requestBody;

            logger.info(params);
            return result;

        } catch (Exception e) {
            // log.error("aop业务处理时失败," + e.getMessage());
            //return new ResponseResult(SERVER_ERROR);
            return null;
        }

    }

    //获取请求体信息
    String getRequestBody(HttpServletRequest request) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream) request.getInputStream(), "utf-8"));
        StringBuffer sb = new StringBuffer("");
        String temp;
        while ((temp = br.readLine()) != null) {
            sb.append(temp);
        }
        br.close();

        return sb.toString();
    }

}
