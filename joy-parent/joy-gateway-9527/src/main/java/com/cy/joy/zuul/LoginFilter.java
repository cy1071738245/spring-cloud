package com.cy.joy.zuul;

import com.cy.joy.config.FilterProperties;
import com.cy.joy.config.JwtProperties;
import com.cy.joy.util.JwtUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by cy.
 */
@Component
@EnableConfigurationProperties({JwtProperties.class , FilterProperties.class})
public class LoginFilter extends ZuulFilter {

    //2.2 注入jwt配置类实例
    @Resource
    private JwtProperties jwtProperties;
    @Resource
    private FilterProperties filterProperties;

    @Override
    public String filterType() {        //01.确定拦截位置，如果是访问前拦截（前置：pre）
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {          //02. 多个过滤器执行顺序，数组越小，优先执行
        return 5;
    }

    @Override
    public boolean shouldFilter() {     //当前过滤器是否执行，true执行，false不执行
        //获得用户请求路径
        //获得上下文
        RequestContext currentContext = RequestContext.getCurrentContext();
        //获得request
        HttpServletRequest request = currentContext.getRequest();
        //获得请求路径 //a/b/c
        String requestURI = request.getRequestURI();

        for (String path : filterProperties.getAllowPaths()) {
            //  /a/b/c  --> ["","a","b","c"]
            String[] pathArr = requestURI.split("/");
            //只要有一个路径匹配的，过滤器不执行
            if(path.equals("/" + pathArr[2]) || path.equals("/" + pathArr[3])){
                return false;
            }
        }
        //其他都执行
        return true;
    }

    @Override
    public Object run() throws ZuulException {  //过滤器核心业务代码
        //获得token
        //获得上下文
        RequestContext currentContext = RequestContext.getCurrentContext();
        //获得request对象
        HttpServletRequest request = currentContext.getRequest();
        //获得指定请求头的值
        String token = request.getHeader("Authorization");

        //校验token -- 使用JWT工具类进行解析
        //使用工具类，通过公钥获得对应信息
        try {
            JwtUtils.getInfoFromToken(token, jwtProperties.getPublicKey());
        } catch (Exception e) {
             // 2.4 如果有异常--没有登录（没有权限）
            currentContext.addOriginResponseHeader("content-type","text/html;charset=UTF-8");
            currentContext.addZuulResponseHeader("content-type","text/html;charset=UTF-8");
            currentContext.setResponseStatusCode( 403 );        //响应的状态码：403
            currentContext.setResponseBody("token失效或无效");
            currentContext.setSendZuulResponse( false );        //没有响应内容
        }
        //如果没有异常，登录了--放行
        return null;
    }
}
