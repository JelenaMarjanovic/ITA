package assignment05;

import java.io.IOException;

public class Assignment05 {

    public static void main(String[] args) throws IOException {

        Thread ts = new Thread(new Server());
        Thread tc = new Thread(new Client());

        ts.start();
        tc.start();

    }

}
