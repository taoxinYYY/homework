package homework;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by DELL on 2018/7/13.
 */
public class ThirdDay extends JFrame {
    //上部放置查询相关组件的面板
    private  JPanel panelSearch=new JPanel();
    //下部提供add,del,modify面板
    private JPanel panelProcess= new JPanel();
    //搜索框
    private JTextField txtSearch= new JTextField();
    private JButton btnSearch= new JButton("search");
    //添加功能所使用的面板，包含组件
    private JPanel panelAdd=new JPanel();
    private JLabel labAddAcount=new JLabel("账号：");
    private JTextField account=new JTextField();
    private JLabel labPassword=new JLabel("密码");
    private JPasswordField password=new JPasswordField();

    //添加下方按钮

    private JButton btnadd=new JButton("add");
    private JButton btndel=new JButton("delete");
    private JButton btnmodify=new JButton("modify");

       JPanel panelContent=new JPanel();

    public  ThirdDay() {
        //===初始化上方组件===
        panelContent.setLayout(new BorderLayout());
        panelContent.add(labAddAcount);

        panelSearch.setLayout(new BorderLayout());//设置布局
        panelSearch.add(txtSearch);//添加搜索框到中间部分
        panelSearch.add(btnSearch,BorderLayout.EAST);//添加搜索按钮到右边
        //===初始化panelAdd面板组件===
        panelAdd.setLayout(new GridLayout(2,2));
        panelAdd.add(labAddAcount);
        panelAdd.add(account);
        panelAdd.add(labPassword);
        panelAdd.add(password);

        //===初始化下方组件===
        panelProcess.add(btnadd);
        panelProcess.add(btndel);
        panelProcess.add(btnmodify);


        this.add(panelSearch,BorderLayout.NORTH);//添加搜索面板到上方
        this.add(panelProcess,BorderLayout.SOUTH);//添加操作面板到下方
        this.add(panelContent);
        //===添加中间事件处理===
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //1.获取文本框输入的内容
                String value = txtSearch.getText();
                //2.将文本内容付给标签显示
                account.setText(value);
            }
        });
        btnadd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               //1.拿到文本的text内容，根据这个内容决定逻辑
                String text=btnadd.getText();
                if(text.equals("add")){//进入添加记录的操作
                    //移除labAddAcount组件
                    ThirdDay.this.panelContent.remove(labAddAcount);
                    //添加panel到中间
                    ThirdDay.this.panelContent.add(panelAdd);
                    text = "save";
                    btndel.setText("cancel");
                    btnmodify.setVisible(false);
                }else{//还原回初始状态
                    text = "add";
                    ThirdDay.this.panelContent.remove(panelAdd);
                    labAddAcount.setText("保存成功！！！");
                    ThirdDay.this.panelContent.add(labAddAcount);
                    btndel.setText("delete");
                    btnmodify.setVisible(true);
                }
                btnadd.setText(text);

                //类似于刷新
                ThirdDay.this.panelContent.setVisible(false);
                ThirdDay.this.panelContent.setVisible(true);

            }
        });


        //===添加下方add按钮事件==
       // btnadd.addMouseListener(new MouseAdapter() {
           // @Override
           // public void mouseClicked(MouseEvent e) {
              //  new ScendDay();
           // }
       // });



        //===设定窗体相关属性===
        this.setSize(800,600);
        this.setTitle("账号首页");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    public static void main(String[] args){
        new ThirdDay();
    }
}

