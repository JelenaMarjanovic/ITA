package assignment05;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyWindow extends Frame {

    public MyWindow(String title) {
        this.setTitle(title);

        // logika za zatvaranje prozora
        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent we) {
                System.out.println("Prekidanje konekcije...");
                System.exit(0);
            }

        });
    }

}
