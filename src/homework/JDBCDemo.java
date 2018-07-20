package homework;

import dao.Account;
import dao.AccountDAO;


import com.sun.javafx.binding.StringFormatter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * Created by DELL on 2018/7/14.
 * 演示通过jdbc连接数据库和进行增、删、改、查的操作
 */

public class JDBCDemo {
    /**
     * 演示通过jdbc连接数据库
     */
    private Connection getConnection() {
        //1.加载驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
        //2.创建数据库连接字符串
            String dbURL = "jdbc:mysql://localhost:3306/hnb?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        //3.建立数据库连接
        try {
            Connection connection = DriverManager.getConnection(dbURL, "root", "root");
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

  private void close(Connection connection,Statement statement,ResultSet resultSet){
        try{
            if(resultSet != null){
                resultSet.close();
            }
            if( statement != null){
                statement.close();
            }
            if(connection != null){
                connection.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }



    private void testInsertDate(int id,String accountValue,String password) {
        Connection connection = null;
        Statement statement =null;
        try {
            // 1.创建数据库连接
           connection = getConnection();
                //2.构建添加数据的sql语句
                String sql = "insert into account " +
                        "values(" + id +  ",'" + accountValue + " ','" + password +"')";
                //3.执行sql语句
                statement = connection.createStatement();
                //4.得到执行后的结果，确定是否添加成功
                int rows = statement.executeUpdate(sql);
                System.out.println("所受影响的行数为：" + rows);
                } catch (SQLException e) {
                   e.printStackTrace();
        } finally {
            close(connection,statement,null);
            }
        }



            public void testDeleteData(int id){
                Connection connection = null;
                Statement statement =null;
        try {
            //1.创建数据库连接
            connection = getConnection();
            //2.构建删除的sql语句
            String sql = "delete from account where id="+id;
            //3.执行删除语句
            statement = connection.createStatement();
            //4.得到执行后的结果，确定是否成功
            int rows = statement.executeUpdate(sql);
            System.out.println("有" + rows + "行被删除成功！");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(connection,statement,null);
            }
    }

    private void testUpdateData(int id ,String account,String password){
         Connection connection=null;
         Statement statement=null;
         try {
           connection = getConnection();
            String sql = "update account set user_account='"+ account + "',user_password = '" + password + "' where id=" + id;
            statement = connection.createStatement();
            int rows = statement.executeUpdate(sql);
            System.out.println("更新结果为："+ (rows>0));
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(connection,statement,null);
                }
        }



    public   String [][] bestFindAllData(){
        String [][] datas = new String[100][3];
        Statement  statement = null;
        ResultSet resultSet = null;
        Connection connection = getConnection();
        String sql = "select user_account,user_password,id from account ";
        statement = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            int index = 0;
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String account = resultSet.getString("user_account");
                String password = resultSet.getString("user_password");
                datas [index] [0] = id + "";
                datas [index] [1] = account + "";
                datas [index] [2] = password + "";
                index++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(connection,statement,resultSet);
            }
        return datas;
    }


   private   void findAllDataFormatOutput(){
      String [][] datas = bestFindAllData();
            StringBuffer buffer = new StringBuffer();
            buffer.append(" ========================================" + System.lineSeparator());
            buffer.append("|id\t\t|\taccount\t\t|\t password\t|"+System.lineSeparator());
            buffer.append(" ========================================" + System.lineSeparator());
       for (int i = 0; i < datas.length;i++){
           String [] values = datas[i];
           if(values[0] != null && values[1] != null && values[2] != null){
               buffer.append(String.format(" %s\t\t  %s\t\t%s",values[0],values[1],values[2]));
               buffer.append(System.lineSeparator());
           }
       }
       System.out.println(buffer.toString());
    }

    private void findAccountDataById(int id){
       Connection connection = null;
       Statement statement = null;
       ResultSet resultSet = null;

        //1.获取数据库连接
        connection = getConnection();
        //2.构建查询的sql语句
        String  sql = "select user_account,user_password,id from account where id=" + id;
        try {
            //3.执行sql语句，并获得结果
             statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            //4.遍历结果集，输出每条记录的信息
            StringBuffer buffer = new StringBuffer();
            buffer.append("=========================================" + System.lineSeparator());
            buffer.append("id\t\t\taccount\t\t\tpassword\t\t\t"+System.lineSeparator());
            buffer.append("=========================================" + System.lineSeparator());
            while (resultSet.next()) {
                id = resultSet.getInt("id");
                String account = resultSet.getString("user_account");
                String password = resultSet.getString("user_password");
                buffer.append(id + " \t|\t " + account + " \t|\t " + password + " \t| " + System.lineSeparator());
            }
            System.out.println(buffer.toString());
        } catch (SQLException e1) {
            e1.printStackTrace();
        }finally {
            close(connection,statement,resultSet);
        }
    }
    /**
     * 模糊搜索数据，根据用户输入的关键词来模糊查询
     * @paramKeyWord
     **/
    public String [][] findAccountDataLikeKeyWord(String keyWord) {
        String [][] datas = new String [100][3];
        //1. 获取数据库连接
        Connection connection = getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        //2. 构建查询的sql语句
        String sql = "select user_account,user_password,id from account " +
                "where user_account like '%" + keyWord + "%' or user_password like '%" + keyWord + "%'";
        try {
            //3. 执行sql语句，并获得结果集
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            int index = 0;
            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String account = resultSet.getString("user_account");
                String password = resultSet.getString("user_password");
                datas[index][0] = id + "";
                datas[index][1] = account;
                datas[index][2] = password;
                index ++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection,statement,resultSet);
        }
        return datas;
    }
    private List<String> getColumNames(ResultSet resultSet) {
        List<String> columNames = new ArrayList<>();
        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int colums = metaData.getColumnCount();
            for(int i = 0; i < colums; i ++) {
                columNames.add(metaData.getCatalogName(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columNames;
    }




    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //JDBCDemo jdbcDemo = new JDBCDemo();
        AccountDAO dao = new AccountDAO();
        while(true) {
            System.out.println("=============================================================");
            System.out.println("|******************** 欢迎使用HNB智能系统 ********************｜");
            System.out.println("| 1.添加数据   2.修改数据   3.删除数据   4.查询数据   5退出系统｜");
            System.out.println("=============================================================");
            System.out.println("====================请选择你要进行的操作======================");
            int select = 0;//接收用户选择的选项
            select = scanner.nextInt();
            while (select < 1 || select > 5) {
                System.out.println("选择的操作不能识别，请重新选择：");
                select = scanner.nextInt();
            }
            String value = null;
            if (select == 1) {
                System.out.println("请输入要添加的账号和密码，中间用逗号分隔。");
                System.out.println("例：1695208430@qq.com，123");
                value = scanner.next();
                String[] values = value.split(",");

                Account account = new Account();
                account.setId((int) System.currentTimeMillis());
                account.setUserAccount(values[0]);
                account.setUserPassword(values[1]);
                dao.insert(account);

               // jdbcDemo.testInsertDate((int) System.currentTimeMillis(), values[0], values[1]);
                System.out.println("是否继续加入？");
                System.out.println("1.继续     2.取消");
                int count = Integer.parseInt(scanner.next());
                while(count == 1){
                    System.out.println("请输入要添加的账号和密码，中间用逗号分隔。");
                    value = scanner.next();
                    values = value.split(",");
                    account.setId((int) System.currentTimeMillis());
                    account.setUserAccount(values[0]);
                    account.setUserPassword(values[1]);

                  //jdbcDemo.testInsertDate((int) System.currentTimeMillis(), values[0], values[1]);
                    System.out.println("是否继续加入？");
                    System.out.println("1.继续     2.取消");
                    value = scanner.next();
                }
            } else if (select == 2) {//修改数据
                System.out.println("请输入要修改的id、账号和密码。用逗号分隔。");
                value = scanner.next();
                String[] values = value.split(",");
                Account account = new Account();
                account.setId(Integer.parseInt(values[0]));
                account.setUserAccount(values[1]);
                account.setUserPassword(values[2]);
                dao.update(account);
                //JDBCDemo.testUpdateData(Integer.parseInt(values[0]),values[1],values[2]);
                System.out.println("是否继续修改？");
                System.out.println("1.继续     2.取消");
                int count = Integer.parseInt(scanner.next());
                while(count == 1){
                    System.out.println("请输入要修改的id、账号和密码。用逗号分隔。");
                    value = scanner.next();
                    String[] Updatevalues = value.split(",");
                    account.setId(Integer.parseInt(values[0]));
                    account.setUserAccount(values[1]);
                    account.setUserPassword(values[2]);
                    dao.update(account);
                    //jdbcDemo.testUpdateData(Integer.parseInt(values[0]),values[1],values[2]);
                    System.out.println("是否继续修改？");
                    System.out.println("1.继续     2.取消");
                    int counts = Integer.parseInt(scanner.next());
                }

            } else if (select == 3) {//删除数据
                System.out.println("请输入要删除的id。");
                value = scanner.next();
                //Account account = new Account();
                dao.delete(Integer.parseInt(value));
               // jdbcDemo.testDeleteData(Integer.parseInt(value));
                System.out.println("是否继续删除？");
                System.out.println("1.继续     2.取消");
                int count = Integer.parseInt(scanner.next());
                while(count == 1){
                    System.out.println("请输入要删除的id。");
                    value = scanner.next();
                    dao.delete(Integer.parseInt(value));
                    System.out.println("是否继续删除？");
                    System.out.println("1.继续     2.取消");
                    count =  Integer.parseInt(scanner.next());
                }

            } else if (select == 4) {//查询数据
                System.out.println("请选择查询的方法：1.查询全部数据  2.按id查询  3.查询关键字");
                int count = Integer.parseInt(scanner.next());
                        if (count == 1) {
                            //jdbcDemo.findAllDataFormatOutput();
                        } else if (count == 2) {
                            System.out.println("请输入要查询的id：");
                            int idNmber = Integer.parseInt(scanner.next());
                            dao.findById(idNmber);
                            //jdbcDemo.findAccountDataById(idNmber);
                        } else if (count == 3) {
                            System.out.println("请输入查询的关键字：");
                            String values = scanner.next();
                            dao.findByKeyword(values);
                            //jdbcDemo.findAccountDataLikeKeyWord(values);
                        }
                        System.out.println("是否继续查询？");
                        System.out.println("1.继续     2.取消");
                        int num = Integer.parseInt(scanner.next());
                        while (num == 1){
                            System.out.println("请选择查询的方法：1.查询全部数据  2.按id查询  3.查询关键字");
                            count = Integer.parseInt(scanner.next());
                            if (count == 1) {
                                dao.findAll();
                                //jdbcDemo.findAllDataFormatOutput();
                            } else if (count == 2) {
                                System.out.println("请输入要查询的id：");
                                int id = Integer.parseInt(scanner.next());
                                dao.findById(id);
                                //jdbcDemo.findAccountDataById(idNmber);
                            } else if (count == 3) {
                                System.out.println("请输入查询的关键字：");
                                String values = scanner.next();
                                dao.findByKeyword(values);
                                //jdbcDemo.findAccountDataLikeKeyWord(values);
                            }
                            System.out.println("是否继续查询？");
                            System.out.println("1.继续     2.取消");
                            num =Integer.parseInt(scanner.next());
                        }
            }else if (select == 5){//退出系统
                System.out.println("退出成功！");
                System.exit(-1);
            }

        }
    }
}
