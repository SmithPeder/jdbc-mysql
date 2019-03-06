import java.sql.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class JDBC {
  static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
  static final String DB_URL = "jdbc:mysql://localhost/?useSSL=false";

  static final String USER = "root";
  static final String PASS = "root";

  public static void main(String[] args) {
    String[] scripts = new String[] {"createTables.sql", "insertData.sql", "selectData.sql"};
    for(String s : scripts) {
      try {
        executeScript(s);
      } catch (IOException e) {
        e.printStackTrace();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  static boolean executeScript(String script) throws IOException, SQLException {
	  BufferedReader reader = null;
	  Connection conn = null;
	  Statement stmt = null;
    boolean isScriptExecuted = false;
    System.out.print("Running SQL script - " + script);

    try {
		  Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      BufferedReader in = new BufferedReader(new FileReader(script));
      String query = "";
      String str = "";
      while ((str = in.readLine()) != null) {
        query += str.replace("\n", " ");
      }
      String[] list = query.split(";");
      for (String q: list) {
        stmt = conn.createStatement();
        stmt.execute(q);
      }

      in.close();
      System.out.println("...Success");
      isScriptExecuted = true;
	  } catch (Exception e) {
		  e.printStackTrace();
	  } finally {
		  if (reader != null)
			  reader.close();
		  if (conn != null)
			  conn.close();
	  }
    return isScriptExecuted;
  }
}
