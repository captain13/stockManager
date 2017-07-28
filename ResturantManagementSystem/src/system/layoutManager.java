/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

/**
 *
 * @author Andrew
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class layoutManager extends JButton {

    private volatile int draggedAtX, draggedAtY;

    public layoutManager(String text) {
        super(text);
        setDoubleBuffered(false);
        setMargin(new Insets(0, 0, 0, 0));
        setSize(100, 100);
        setPreferredSize(new Dimension(40, 40));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                draggedAtX = e.getX();
                draggedAtY = e.getY();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                setLocation(e.getX() - draggedAtX + getLocation().x,
                        e.getY() - draggedAtY + getLocation().y);
            }
        });
    }

    public static void newButton() {
          MainSystem.pnlLayout.add(new layoutManager("Test"));
    }
}
