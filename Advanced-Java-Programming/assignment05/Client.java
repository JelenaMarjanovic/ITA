package assignment05;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.TextField;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client implements Runnable {

    public Client() {

        MyWindow f = new MyWindow("Kalkulator");
        LayoutManager layOut = new GridBagLayout();
        GridBagConstraints c;
        f.setLayout(layOut);

        TextField tf1 = new TextField();
        tf1.setPreferredSize(new Dimension(150, 25));
        TextField tf2 = new TextField();
        tf2.setPreferredSize(new Dimension(150, 25));
        TextField tf3 = new TextField();
        tf3.setPreferredSize(new Dimension(150, 25));
        tf3.setEditable(false);

        Button b1 = new Button("Izracunaj");
        Button b2 = new Button("Ponisti");

        b1.addActionListener(e -> {

            if (!tf1.getText().isEmpty() && !tf2.getText().isEmpty()) {
                String operands = tf1.getText().trim() + " " + tf2.getText().trim();

                try (Socket cs = new Socket("localhost", 1000);
                        BufferedReader bis = new BufferedReader(new InputStreamReader(cs.getInputStream()));
                        BufferedOutputStream bos = new BufferedOutputStream(cs.getOutputStream());) {

                    bos.write((operands + "\r\n").getBytes());
                    bos.flush();

                    String result = bis.readLine();
                    if (result != null) {
                        tf3.setText(result);
                    }

                } catch (IOException ex) {
                    System.err.println("Error in connection: " + ex.getMessage());
                }
            } else {
                System.out.println("Neispravan unos!");
            }
        });

        b2.addActionListener(e -> {
            tf1.setText("");
            tf2.setText("");
            tf3.setText("");
        });

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10, 10, 10, 10);
        c.anchor = GridBagConstraints.LINE_END;
        f.add(new Label("Unesite prvi sabirak"), c);

        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 0;
        c.insets = new Insets(10, 10, 10, 10);
        f.add(tf1, c);

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(10, 10, 10, 10);
        c.anchor = GridBagConstraints.LINE_END;
        f.add(new Label("Unesite drugi sabirak"), c);

        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(10, 10, 10, 10);
        f.add(tf2, c);

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(10, 10, 10, 10);
        c.anchor = GridBagConstraints.LINE_END;
        f.add(new Label("Zbir"), c);

        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 2;
        c.insets = new Insets(10, 10, 10, 10);
        f.add(tf3, c);

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(10, 10, 10, 10);
        c.gridwidth = 2;
        c.fill = GridBagConstraints.BOTH;
        f.add(b1, c);

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 4;
        c.insets = new Insets(10, 10, 10, 10);
        c.gridwidth = 2;
        f.add(b2, c);

        f.pack();
        f.setVisible(true);

    }

    @Override
    public void run() {

        System.out.println("Konekcija je uspostavljena!");

    }

}
