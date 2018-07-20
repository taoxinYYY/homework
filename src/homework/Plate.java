package homework;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by DELL on 2018/7/12.
 */
public class Plate extends JFrame {
    public Plate() {
        String value;
        this.setSize(800, 600);
        this.setTitle("accoun-log");
        //添加中间面板及多种组件
        JPanel panelCenter=new JPanel();
        JLabel titlelabel=new JLabel("title");
        JLabel accountlabel=new JLabel("account");
        JLabel passwordlabel=new JLabel("password");
        JLabel remarklabel=new JLabel("remark");
        JTextField title=new JTextField("");
        JTextField account=new JTextField("");
        JPasswordField password=new JPasswordField();
        JTextField remark=new JTextField("");
        //初始化上中间组件
        panelCenter.setLayout(new GridLayout(4,2));
        panelCenter.add(titlelabel);
        panelCenter.add(title);
        panelCenter.add(accountlabel);
        panelCenter.add(account);
        panelCenter.add(passwordlabel);
        panelCenter.add(password);
        panelCenter.add(remarklabel);
        panelCenter.add(remark);
        panelCenter.setVisible(true);
        //添加下方面板及其按钮
        JPanel panelDown=new JPanel();
        JButton btnok=new JButton("进入");
        JButton btncancel=new JButton("退出");
        //初始化下方组件
        panelDown.add(btnok);
        panelDown.add(btncancel);

        this.add(panelCenter);
        this.add(panelDown,BorderLayout.SOUTH);
        this.setVisible(true);

        //添加事件监听器
        btnok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(Plate.this,
                        title.getText()+","+account.getText()+","+password.getText()+","+remark.getText());
            }
        });

        btncancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(EXIT_ON_CLOSE);
            }
        });



    }


public static void main(String[] args) {
    Plate plate = new Plate();
}
}