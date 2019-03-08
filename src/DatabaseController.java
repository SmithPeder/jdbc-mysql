import java.sql.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class DatabaseController {

  public static Connection CON;
  // Try to make a connetcion to the running MySQL database
  public void makeConnection() {
    try {
      CON = DriverManager.getConnection(JDBC.DB_URL, JDBC.USER, JDBC.PASS);
      JDBC.OUTPUT.success("Connected to database!");
    } catch(SQLException err) {
      JDBC.OUTPUT.error(err.toString());
    }
  }

  // Migrate database schema
  public void migrateDatabase() {
    try {
      executeScript("models/models");
      JDBC.OUTPUT.success("Database migrated!");
    } catch (SQLException sql) {
      JDBC.OUTPUT.error(sql.toString());
    } catch (IOException io) {
      JDBC.OUTPUT.error(io.toString());
    }

    // Modify the URL with the database name
    JDBC.DB_URL = "jdbc:mysql://localhost:3306/?database_wd&allowPublicKeyRetrieval=true&useSSL=false";
  }

  public void dropDatabase() {
    try {
      Statement stmt = CON.createStatement();
      stmt.execute("DROP DATABASE wd");
      JDBC.OUTPUT.success("SUCCESS: Database dropped!");
    } catch(SQLException sql) {
      JDBC.OUTPUT.error(sql.toString());
    }
  }

  // Initialize development with premade fixtures
  public void initializeDevelopment() {
    String[] scripts = new String[] {
      "fixtures/workout",
      "fixtures/equipment",
      "fixtures/note",
      "fixtures/exercise",
      "fixtures/workout_exercise",
      "fixtures/exercise_group",
      "fixtures/exercise_exercise_group"
    };

    for(String s : scripts) {
      try {
        executeScript(s);
        JDBC.OUTPUT.success(s + " loaded!");
      } catch (SQLException sql) {
        JDBC.OUTPUT.error(sql.toString());
      } catch (IOException io) {
        JDBC.OUTPUT.error(io.toString());
      }
    }
  }

  // Exectute a sql script
  static void executeScript(String script) throws SQLException, IOException {
    ScriptRunner runner = new ScriptRunner(CON, false, false);
    runner.runScript(new BufferedReader(new FileReader(JDBC.PATH + script + ".sql")));
  }
}
