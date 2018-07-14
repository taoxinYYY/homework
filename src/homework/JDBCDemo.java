package homework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by DELL on 2018/7/14.
 * 演示通过jdbc连接数据库和进行增、删、改、查的操作
 */
public class JDBCDemo {
    /**
     * 演示通过jdbc连接数据库
     */
    private void testConnection(){
        //1.加载驱动
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            //2.创建数据库连接字符串
            String dbURL = "jdbc:mysql://localhost:3306/hnb11";
            //3.建立数据库连接
        try {
            Connection connection = DriverManager.getConnection(dbURL,"root","root");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        JDBCDemo demo = new JDBCDemo();
        demo.testConnection();
    }
}
