package com.jimmy;

import com.wt.mapLocation.location.pbf.TYUploadLocationPbf;
import org.springframework.web.socket.client.WebSocketClient;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.*;
import java.io.*;

/**
 * @author xiongyang
 * @date 2020/4/28 20:50
 * @Description:
 */
public class SocketTest {
    public static void main(String[] args) {
        try {

            Socket socket = new Socket("localhost", 5002);
            socket.setSoTimeout(1800);



            TYUploadLocationPbf.UploadLocationPbf.Builder builder = TYUploadLocationPbf.UploadLocationPbf.newBuilder();



            TYUploadLocationPbf.BLE.Builder bleBuild = TYUploadLocationPbf.BLE.newBuilder();
            bleBuild.setFloor(1);
            bleBuild.setX(1111.111);
            bleBuild.setY(1111.111);
            TYUploadLocationPbf.BLE ble = bleBuild.build();

            TYUploadLocationPbf.GPS.Builder gpsBuilder = TYUploadLocationPbf.GPS.newBuilder();
            gpsBuilder.setLat(12.11);
            gpsBuilder.setLng(13.12);
            gpsBuilder.setAccuracy(1.1);
            TYUploadLocationPbf.GPS gps = gpsBuilder.build();

            builder.setBuildingID("0027007");
            builder.setUserID("123456");
            builder.setTimestamp(123456789);
            builder.setUserName("hahah");
            builder.setIndex(1);
            builder.setPosition(true);
            builder.setBleState(true);
            builder.setBle(ble);
            builder.setGps(gps);

            TYUploadLocationPbf.UploadLocationPbf pbf = builder.build();
            byte[] bytes = pbf.toByteArray();


            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(bytes);
            outputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
