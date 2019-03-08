import java.sql.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class JDBC {
  static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
  static final String DB_URL = "jdbc:mysql://localhost:3306/?allowPublicKeyRetrieval=true&useSSL=false";

  static final String USER = "root";
  static final String PASS = "root";

  public static void main(String[] args) {
    try {
      executeScript();
    } catch(IOException io) {
      System.out.println("IO exeption");
      System.out.println(io);
    }
    catch(SQLException sql) {
      System.out.println("SQL exeption");
      System.out.println(sql);
    }
  }

  static boolean executeScript() throws IOException, SQLException {
	  Connection con = null;
    con = DriverManager.getConnection(DB_URL, USER, PASS);

    ScriptRunner runner = new ScriptRunner(con, false, false);
    runner.runScript(new BufferedReader(new FileReader("/Users/smith/code/jdbc-mysql/models/models.sql")));

    return true;
  }
}
