package maoamo.WebCrawler.pipeLine;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
  public static Connection getConnection() {
    // TODO Auto-generated method stub
    Connection con = null; // 创建用于连接数据库的Connection对象
    try {
      Class.forName("com.mysql.jdbc.Driver");// 加载Mysql数据驱动

      con = DriverManager.getConnection(
          "jdbc:mysql://localhost:3306/spiderdata?useUnicode=true&characterEncoding=utf-8",
          "root", "123456");// 创建数据连接

    } catch (Exception e) {
      System.out.println(" connect mysql error " + e.getMessage());
      e.printStackTrace();
    }
    return con;
  }

  public static Connection getLocalConnection() {
    // TODO Auto-generated method stub
    Connection con = null; // 创建用于连接数据库的Connection对象
    try {
      Class.forName("com.mysql.jdbc.Driver");// 加载Mysql数据驱动
      con = DriverManager.getConnection(
          "jdbc:mysql://localhost:3306/spiderdata?useUnicode=true&characterEncoding=utf-8", "root",
          "");// 创建数据连接

    } catch (Exception e) {
      System.out.println(" connect mysql error " + e.getMessage());
      e.printStackTrace();
    }
    return con;
  }

}
