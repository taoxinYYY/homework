package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2018/7/19.
 */
public class SuperDAO {
    public int executeDML(String sql) {
        Connection connection = JDBCUntil.getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            return statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUntil.close(connection, statement, null);
        }
        return 0;
    }

    public List query (String sql) {
        Connection connection = JDBCUntil.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        List datas = new ArrayList();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            ResultSetMetaData metaData = resultSet.getMetaData();//拿到结果集元数据
            int column = metaData.getColumnCount();//获取当前sql返回的列数量
            while (resultSet.next()) {
                //声明一个一维数组代表当前行的数据
                Object[] columnDatas = new Object[column];
                for (int i = 0; i < columnDatas.length; i++) {
                    //获得当前行第X列的值
                    columnDatas[i] = resultSet.getObject(i + 1);
                }
                datas.add(columnDatas);//添加行数据到集合
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUntil.close(connection, statement, resultSet);
        }
        return datas;
    }
}