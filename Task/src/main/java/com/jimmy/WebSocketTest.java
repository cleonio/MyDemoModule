package com.jimmy;

import lombok.experimental.var;

import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.net.URI;

/**
 * @author xiongyang
 * @date 2020/4/21 10:06
 * @Description:
 */
@ClientEndpoint
public class WebSocketTest {


    private String deviceId;

    private Session session;

    public WebSocketTest () {

    }

    public WebSocketTest (String deviceId) {
        this.deviceId = deviceId;
    }

    protected boolean start(String userId) {
        WebSocketContainer Container = ContainerProvider.getWebSocketContainer();
        String uri = "ws://localhost:5002/map-location/websocket/location/"+userId+"/00270007";
        System.out.println("Connecting to " + uri);
        try {
            session = Container.connectToServer(WebSocketTest.class, URI.create(uri));
            System.out.println("count: " + deviceId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        for (int i = 1; i< 10000; i++) {
            WebSocketTest wSocketTest = new WebSocketTest(String.valueOf(i));
            String userId="123"+i;
            if (!wSocketTest.start(userId)) {
                System.out.println("测试结束！");
                break;
            }
        }
    }
}
