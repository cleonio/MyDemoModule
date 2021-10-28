package com.jimmy.test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author xiongyang
 * @date 2020/8/6 17:10
 * @Description:
 */
public class SocketTest {

    public static void main(String[] args) {
        try {
            for (int i = 0; i < 5000; i++) {
                Socket socket = new Socket("127.0.0.1", 8082);
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                String msg = "$tmb00035ET3318/08/22 11:5804029.94,027.25,20.00,20.00$"+i;
                bufferedWriter.write(msg);
                bufferedWriter.flush();
            }

            /*socket.shutdownOutput();
            socket.close();*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
