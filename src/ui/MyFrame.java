package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by DELL on 2018/7/12.
 */
public class MyFrame extends JFrame {
    public MyFrame() {
        this.setSize(300, 200);
        this.setTitle("First frame...");
        //FlowLayout flowLayout = new FlowLayout();
        GridLayout gridLayout = new GridLayout(3,2);
        this.setLayout(gridLayout);
        JButton btnNorth = new JButton("北方");
        JButton btnSouth = new JButton("南方");
        JButton btnEast = new JButton("东方");
        JButton btnWest = new JButton("西方");
         JButton btnCenter = new JButton("中间");
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                button.setText("Hallow");
            }
        };
        //===添加事件===
        btnNorth.addActionListener(actionListener);
        btnSouth.addActionListener(actionListener);
        btnEast.setActionCommand("east");
        btnWest.setActionCommand("west");
        btnCenter.setActionCommand("center");

        this.add(btnNorth);
        this.add(btnSouth);
        this.add(btnEast);
        this.add(btnWest);
        this.add(btnCenter);
        // this.add(btnNorth, BorderLayout.NORTH);
        // this.add(btnSouth, BorderLayout.SOUTH);
        // this.add(btnEast, BorderLayout.EAST);
        // this.add(btnWest, BorderLayout.WEST);
        // this.add(btnCenter, BorderLayout.CENTER);

        setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public static void main(String[] args) {
        MyFrame MyFrame = new MyFrame();
    }
}
