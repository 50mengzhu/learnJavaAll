package com.learn.netty.bio.server;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;


/**
 * 传说中的bio程序
 *
 * client per thread
 */
public class BioServer {


    public static void main(String[] args) {
        try {
            ServerSocket socket = new ServerSocket(9999);
            while (true) {
                System.out.println("listen at port 9999");
                Socket accept = socket.accept();
                OutputStream outputStream = accept.getOutputStream();
                outputStream.write("hello world!".getBytes("utf-8"));
                new Thread(() -> {
                    try {
                        InputStream inputStream = accept.getInputStream();
                        byte[] bytes = new byte[1024];
                        int count = 0;
                        while ((count = inputStream.read(bytes, 0, 1024)) != -1)
                            System.out.println(new String(bytes, 0, count));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
