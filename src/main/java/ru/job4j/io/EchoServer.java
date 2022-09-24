package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.function.Predicate;

public class EchoServer {

    private static String message(String msg) {
        String rsl = "";
        int index = msg.indexOf("=");
        if (index != -1) {
            rsl = msg.substring(index + 1);
        }
        return rsl;
    }

    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream())
                     )) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        String[] rsl = str.split(" ");
                        for (String i : rsl) {
                            String msg = EchoServer.message(i);
                            if (msg.equals("Hello")) {
                                System.out.println("Hello :)");
                            } else if (msg.equals("Bye")) {
                                System.out.println("bye bye");
                                server.close();
                            }
                        }
                    }
                    out.flush();
                }
            }
        }
    }

}
