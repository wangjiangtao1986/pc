package com.wang.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;;


@Aspect   //定义一个切面
@Configuration
public class PrivAspect {

    // 定义切点Pointcut
    @Pointcut("execution(* com.jiaobuchong.web.*Controller.*(..))")
    public void excudeService() {}

    @Around("excudeService()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();

        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        System.out.println("请求开始, 各个参数, url: {}, method: {}, uri: {}, params: {}" + url + method + uri + queryString);

//        校验
//        if(){
//        	
//        }
        // result的值就是被拦截方法的返回值
        Object result = pjp.proceed();
//        Gson gson = new Gson();
//        logger.info("请求结束，controller的返回值是 " + gson.toJson(result));
        return result;
    }
}