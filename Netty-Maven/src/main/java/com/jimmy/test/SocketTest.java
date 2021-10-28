package com.jimmy.test;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author xiongyang
 * @date 2020/8/6 16:05
 * @Description:
 */
public class SocketTest {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8082);
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter=new PrintWriter(outputStream);
            /*for (int i = 0; i < 100; i++) {
                Thread.sleep(2000);
                String msg = "$tmb00035ET3318/08/22 11:5804029.94,027.25,20.00,20.00$"+i;
                printWriter.write(msg);
                printWriter.flush();
            }*/
            String msg = "$tmb00035ET3318/08/22 11:5804029.94,027.25,20.00,20.00$";
            printWriter.write(msg);
            printWriter.flush();
            socket.shutdownOutput();
            socket.close();
            Thread.sleep(2000);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
