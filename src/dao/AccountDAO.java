package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;
/**
 * Created by DELL on 2018/7/18.
 * 对表Account的数据操作
 */
public class AccountDAO extends SuperDAO{

    public boolean delete(int id) {

            //2.构建删除的sql语句
            String sql = "delete from account where id=" + id;
            //3.执行删除语句

        return executeDML(sql) > 0;
    }

    public boolean insert(Account account) {

        //2.构建添加数据的sql语句
        String sql = "insert into account " +
                "values(" + account.getId() + ",'" +
                account.getUserAccount() + " ','" +
                account.getUserPassword() + "')";
        //3.执行sql语句
        return executeDML(sql) > 0;

    }

    public boolean update(Account account) {

        String sql = "update account set user_account='" + account.getUserAccount() +
                "',user_password = '" + account.getUserPassword() +
                "' where id=" + account.getId();
        return executeDML(sql) > 0;
    }

    public List<Account> findAll() {
        // 申明一个集合
        List<Account> list = new ArrayList<>();
        //1. 获取数据库连接
        Connection connection = JDBCUntil.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        //2. 构建查询的sql语句
        String sql = "select user_account,user_password,id from account";
        try {
            //3. 执行sql语句，并获得结果集
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            //4. 遍历结果集，输出每条记录的信息。
            int index = 0;
            while(resultSet.next()) {// 定位到某一行
                Account account = new Account();

                int id = resultSet.getInt("id");
                String userAccount = resultSet.getString("user_account");
                String password = resultSet.getString("user_password");

                account.setId(id);
                account.setUserAccount(userAccount);
                account.setUserPassword(password);

                list.add(account);// 把每行数据放到集合里
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUntil.close(connection,statement,resultSet);
        }
        return list;
    }



    public Account findById(int id ){
        //1. 获取数据库连接
        Connection connection = JDBCUntil.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        Account account = null;
        //2. 构建查询的sql语句
        String sql = "select user_account,user_password,id from account where id=" + id;
        try {
            //3. 执行sql语句，并获得结果集
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            //4. 遍历结果集，输出每条记录的信息。
            if(resultSet.next()) {
                String userAccount = resultSet.getString("user_account");
                String password = resultSet.getString("user_password");
                // 构建找到的数据对象
                account = new Account();
                account.setId(id);
                account.setUserAccount(userAccount);
                account.setUserPassword(password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUntil.close(connection,statement,resultSet);
        }
        return account;
    }


    public List<Account> findByKeyword(Object keyword){
        String sql = "select user_account,user_password,id from account " +
                "where user_account like '%" + keyword + "%' or user_password like '%" + keyword + "%'";
        List<Object> list = query(sql);
        List<Account> accountList = new ArrayList<>();
        for(Object data : list){
            Object [] d = (Object[]) data;
            Account account = new Account();
            account.setUserAccount(d[0] + "");
            account.setUserPassword(d[1] + "");
            account.setId(Integer.parseInt(d[2] + ""));
            accountList.add(account);
        }
        return accountList;
    }


    private void test(int ...sumArray) {
        int sum = 0;
        for(int i = 0; i < sumArray.length; i ++) {
            sum += sumArray[i];
        }
        System.out.println("sum = " + sum);
    }

    public static void main(String ... args) {
        AccountDAO dao = new AccountDAO();
        //int [] sum = {1,2,3,4,5,7};
        dao.test(1,2,3,4,5,6,7);
        //List<Account> list = dao.findAll();
//        Account account = dao.findById(50);
//        System.out.println(account);
    }
}
