package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class EchoServer {

    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    private static String message(String[] msg) {
        return Arrays.stream(msg)
                .filter(s -> s.contains("?msg="))
                .map(s -> s.substring(s.indexOf("=") + 1))
                .findFirst().orElse("");
    }

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream())
                     )) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                            String msg = EchoServer.message(str.split(" "));
                            if (msg.equals("Hello")) {
                                out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                                out.write("Hello, dear friend.".getBytes());
                            } else if (msg.equals("Exit")) {
                                out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                                out.write("Bye.".getBytes());
                                server.close();
                            } else if (msg.length() > 1) {
                                out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                                out.write("Недопустимый вопрос!".getBytes());
                                throw new IOException("It is permissible to enter only pre-set questions to the bot");
                            }
                    }
                    out.flush();
                }
            }
        } catch (Exception e) {
            LOG.error("User request error, invalid question", e);
        }
    }

}
