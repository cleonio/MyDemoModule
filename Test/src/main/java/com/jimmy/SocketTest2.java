package com.jimmy;


import com.wt.mapLocation.location.pbf.TYUploadLocationPbf;
import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;

/**
 * @author xiongyang
 * @date 2020/4/28 21:58
 * @Description:
 */
public class SocketTest2 extends WebSocketClient {

    public SocketTest2(String serverUri) throws URISyntaxException {
        super(new URI(serverUri));
    }

    @Override
    public void onOpen(ServerHandshake shake) {

        System.out.println("握手...");
        for(Iterator<String> it = shake.iterateHttpFields(); it.hasNext();) {
            String key = it.next();
            System.out.println(key+":"+shake.getFieldValue(key));
        }
    }

    @Override
    public void onMessage(String s) {
        System.out.println("接收到消息："+s);
    }

    @Override
    public void onClose(int i, String s, boolean b) {
        System.out.println("关闭...");
    }

    @Override
    public void onError(Exception e) {
        System.out.println("异常"+e);
    }

    public static void main(String[] args) {
        try {
            SocketTest2 client = new SocketTest2("ws://localhost:5002/map-location/websocket/uploadLocationPbfSocket/123456/00270007");
            client.connect();

            while (!client.getReadyState().equals(WebSocket.READYSTATE.OPEN)) {
                System.out.println("还没有打开");
            }
            System.out.println("建立websocket连接");

            TYUploadLocationPbf.UploadLocationPbf.Builder builder = TYUploadLocationPbf.UploadLocationPbf.newBuilder();



            TYUploadLocationPbf.BLE.Builder bleBuild = TYUploadLocationPbf.BLE.newBuilder();
            bleBuild.setFloor(4);
            bleBuild.setX(2);
            bleBuild.setY(3);
            TYUploadLocationPbf.BLE ble = bleBuild.build();

            /*TYUploadLocationPbf.GPS.Builder gpsBuilder = TYUploadLocationPbf.GPS.newBuilder();
            gpsBuilder.setLat(12.11);
            gpsBuilder.setLng(13.12);
            gpsBuilder.setAccuracy(1.1);
            TYUploadLocationPbf.GPS gps = gpsBuilder.build();*/

            builder.setBuildingID("00270000");
            builder.setUserID("innerpeacer");
            builder.setTimestamp(1000);
            //builder.setUserName("hahah");
            builder.setIndex(1);
            builder.setPosition(true);
            builder.setBleState(true);
            builder.setBle(ble);
            //builder.setGps(gps);
            TYUploadLocationPbf.UploadLocationPbf pbf = builder.build();
            byte[] bytes = pbf.toByteArray();
            System.out.println(bytes);

            FileOutputStream outputStream = new FileOutputStream(new File("D:\\location.pbf"));
            outputStream.write(bytes);
            client.send(bytes);

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
