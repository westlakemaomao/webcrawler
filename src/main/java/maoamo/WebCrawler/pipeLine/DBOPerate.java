package maoamo.WebCrawler.pipeLine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import maoamo.WebCrawler.pipeLine.domain.Result;


public class DBOPerate {
  static Connection conn;
  static Statement st;

  public static void insertResults(Result rs, String table) {
    // TODO Auto-generated method stub

    Connection connection = DBConnection.getConnection();
    PreparedStatement ps = null;
    java.sql.Timestamp ti = new java.sql.Timestamp(rs.getTimeD().getTime());
    try {
      String sql = "insert into " + table
          + " (Url,title,writer,keywords,description,summary,content,create_time,crawler_time,source,category_navigation,site,first_img_src) select '"
          + rs.getUrl() + "','" + rs.getTitle() + "','" + rs.getAuthor() + "','" + rs.getKeywords()
          + "','" + rs.getDescription() + "','" + rs.getSumm() + "','" + rs.getContent() + "','"
          + ti + "','" + new Timestamp(System.currentTimeMillis()) + "','" + rs.getSource() + "','"
          + rs.getCategory_navigation() + "','" + rs.getSite() + "','" + rs.getImgSrc()
          + "' from dual where not exists(select url from " + table
          + " where TO_DAYS(NOW()) - TO_DAYS(crawler_time) <= 30 and url='" + rs.getUrl() + "')";
      ps = connection.prepareStatement(sql);
      ps.addBatch();
      ps.executeBatch();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      try {
        ps.close();
        connection.close();
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }

  public static void insertResults2(Result rs, String table) {
    // TODO Auto-generated method stub

    Connection connection = DBConnection.getConnection();
    PreparedStatement ps = null;
    Long id = null;
    try {
      String sql2 = "insert into " + table
          + " (Url,title,writer,keywords,description,summary,content,create_time,crawler_time,source,category_navigation,site) select (?, ?, ?,?,?,?,?,?,?,?,?,?)from dual where not exists(select url from "
          + table + " where TO_DAYS(NOW()) - TO_DAYS(crawler_time) <= 30 and url='" + rs.getUrl()
          + "')";
      ps = connection.prepareStatement(sql2);
      ps.setString(1, rs.getUrl());
      ps.setString(2, rs.getTitle());
      ps.setString(3, rs.getAuthor());
      ps.setString(4, rs.getKeywords());
      ps.setString(5, rs.getDescription());
      ps.setString(6, rs.getSumm());
      ps.setString(7, rs.getContent());
      ps.setString(8, rs.getTime());
      ps.setTimestamp(9, new Timestamp(System.currentTimeMillis()));
      ps.setString(10, rs.getSource());
      ps.setString(11, rs.getCategory_navigation());
      ps.setString(12, rs.getSite());
      ps.addBatch();
      ps.executeBatch();
      ResultSet Result = ps.getGeneratedKeys();
      if (Result.next()) {
        id = Result.getLong(1);
      }
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      try {
        ps.close();
        connection.close();
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }

  }

  

  public static void update(String sql) {
    conn = DBConnection.getConnection();
    try {

      st = (Statement) conn.createStatement();

      int count = st.executeUpdate(sql);

      System.out.println("result表中更新 " + count + " 条数");

      conn.close();
    } catch (SQLException e) {
      System.out.println("更新数据失败");
    }
  }

  public static java.sql.Date getdate() {
    java.sql.Date d2 = null;
    Calendar cal = Calendar.getInstance();
    // java.util.Date d1 = new SimpleDateFormat("yyyyMMddHHmmssSSS")
    // .format(new Date() );
    String s = "2012-06-21 00:10:00";
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    java.util.Date d1;
    try {
      d1 = cal.getTime();
      System.out.println("d1" + d1);
      d2 = new java.sql.Date(d1.getTime()); // 再转换为sql.Date对象
      System.out.println("d2" + d2);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } // 先把字符串转为util.Date对象
    return d2;
  }

}
