package assignment04;

import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import static javax.swing.WindowConstants.HIDE_ON_CLOSE;

public class Assignment04 {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {

                MyForm form = new MyForm();

                ImageIcon icon = new ImageIcon("colors.png");
                form.setIconImage(icon.getImage());

                form.setTitle("Blinking application");

                PopupMenu popup = new PopupMenu();
                MenuItem miSettings = new MenuItem("Settings");
                MenuItem miClose = new MenuItem("Close");

                miSettings.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        form.setVisible(true);
                    }
                });

                miClose.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0);
                    }
                });

                popup.add(miSettings);
                popup.add(miClose);

                SystemTray systemTray = SystemTray.getSystemTray();
                try {

                    TrayIcon trayIcon = new TrayIcon(icon.getImage());
                    trayIcon.setPopupMenu(popup);
                    trayIcon.setToolTip("Blinking application");
                    systemTray.add(trayIcon);

                } catch (AWTException exc) {
                    System.out.println(exc.getMessage());
                }

                form.setDefaultCloseOperation(HIDE_ON_CLOSE);

            }

        });

    }

}
