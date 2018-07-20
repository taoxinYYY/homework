package table;

import homework.JDBCDemo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Created by DELL on 2018/7/17.
 */
public class JTableDemo extends JFrame {
    JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    public JTableDemo() {
        //1.声明表头 一维数组
        String[] heads = new String[]{"id", "isCheck", "color"};
        //2.声明表数据 二维数组
        Object[][] datas = new Object[100][3];
        for (int i = 0; i < datas.length; i++) {
            datas[i][0] = i;
            datas[i][1] = (i % 2 == 0);
            datas[i][2] = new Color(i, i * 2, i * 2);
        }
        //3.创建JTable对象
        JTable table = new JTable();
        datas = new JDBCDemo().bestFindAllData();
        DefaultTableModel tableModel = getTableModel(datas,heads);
        table.setModel(tableModel);
        table.setAutoCreateRowSorter(true);
        //JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().add(table);
        this.add(scrollPane);
        this.setSize(600, 800);
        this.setVisible(true);
    }

       private DefaultTableModel getTableModel(Object[][] datas , String[] heads) {
            DefaultTableModel tableModel = new DefaultTableModel();
            for (String head : heads) {//为Model添加表头
                tableModel.addColumn(head);
            }
            //添加数据
            for (Object[] rowData : datas) {
                tableModel.addRow(rowData);
                }
            return tableModel;
}

    public static void main(String[] args){
        JTableDemo demo = new JTableDemo();

    }
}
