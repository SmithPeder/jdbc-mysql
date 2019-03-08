import java.sql.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public abstract class BaseController {
  protected Statement stmt = null;
  protected Connection CON = DatabaseController.CON;
  protected String table;

  public BaseController(String table) {
    this.table = table;
  }

  protected void getAll() {
    try {
      stmt = CON.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM " + this.table + ";");
      ResultSetMetaData rsmd = rs.getMetaData();
      JDBC.OUTPUT.printColumns(rs, rsmd);
    } catch(SQLException sql) {
      JDBC.OUTPUT.red(sql.toString());
    }
  }
}
