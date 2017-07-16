package assignment05;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

    @Override
    public void run() {

        while (true) {

            try (ServerSocket serverSocket = new ServerSocket(1000);
                    Socket ss = serverSocket.accept();
                    BufferedReader bis = new BufferedReader(new InputStreamReader(ss.getInputStream()));
                    BufferedOutputStream bos = new BufferedOutputStream(ss.getOutputStream());) {

                String[] operands = bis.readLine().split(" ");
                int sum = Integer.valueOf(operands[0]) + Integer.valueOf(operands[1]);
                bos.write((String.valueOf(sum) + "\r\n").getBytes());
                bos.flush();

            } catch (IOException ex) {
                System.err.println("Error in connection: " + ex.getMessage());
            }

        }

    }

}
