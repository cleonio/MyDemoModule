package com.jimmy;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author xiongyang
 * @date 2020/8/4 16:55
 * @Description:
 */
@Component
public class MyZuulFilter extends ZuulFilter {
    //统计当前Zuul调用次数
    int count = 0;

    //获取Zuul服务端口号
    @Value("${server.port}")
    private String prot;


    /**
     * 指定该Filter的类型
     * ERROR_TYPE = "error";
     * POST_TYPE = "post";
     * PRE_TYPE = "pre";
     * ROUTE_TYPE = "route";
     */
    @Override
    public String filterType() {
        System.out.println("filterType()...");
        return "pre";
    }


    /**
     * 指定该Filter执行的顺序（Filter从小到大执行）
     * DEBUG_FILTER_ORDER = 1;
     * FORM_BODY_WRAPPER_FILTER_ORDER = -1;
     * PRE_DECORATION_FILTER_ORDER = 5;
     * RIBBON_ROUTING_FILTER_ORDER = 10;
     * SEND_ERROR_FILTER_ORDER = 0;
     * SEND_FORWARD_FILTER_ORDER = 500;
     * SEND_RESPONSE_FILTER_ORDER = 1000;
     * SIMPLE_HOST_ROUTING_FILTER_ORDER = 100;
     * SERVLET_30_WRAPPER_FILTER_ORDER = -2;
     * SERVLET_DETECTION_FILTER_ORDER = -3;
     */
    @Override
    public int filterOrder() {
        System.out.println("filterOrder()...");
        return 0;
    }



    /**
     * 指定需要执行该Filter的规则
     * 返回true则执行run()
     * 返回false则不执行run()
     */
    @Override
    public boolean shouldFilter() {
        System.out.println("shouldFilter()...");
        return true;
    }


    /**
     * 该Filter具体的执行活动
     */
    @Override
    public Object run() throws ZuulException {
        // 获取上下文
        //RequestContext currentContext = RequestContext.getCurrentContext();
        //HttpServletRequest request = currentContext.getRequest();
        //获取userToken
        // String userToken = request.getParameter("userToken");
        //System.out.println("userToken: "+userToken);
        //if (StringUtils.isEmpty(userToken)) {
        //不会继续执行调用服务接口，网关直接响应给客户端
        //currentContext.setSendZuulResponse(false);
        //currentContext.setResponseStatusCode(401);
        // currentContext.setResponseBody("userToken is Null");
        // return null;
        // }else if(!userToken.equals("10010")){
        // currentContext.setSendZuulResponse(false);
        //currentContext.setResponseStatusCode(401);
        //currentContext.setResponseBody("userToken is Error");
        //return null;
        //}
        // 否则正常执行业务逻辑，调用服务.....
        System.out.println("访问Zuul网关端口为："+prot +"（total："+ ( count++) +"）");
        return null;
    }
}
