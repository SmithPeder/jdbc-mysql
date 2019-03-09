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

      try {
        // The wd database is not present, so we create it
        JDBC.DB_URL = "jdbc:mysql://localhost:3306/?allowPublicKeyRetrieval=true&useSSL=false";
        CON = DriverManager.getConnection(JDBC.DB_URL, JDBC.USER, JDBC.PASS);
        Statement stmt = CON.createStatement();
        stmt.execute("CREATE DATABASE wd");
        JDBC.DB_URL = "jdbc:mysql://localhost:3306/wd?allowPublicKeyRetrieval=true&useSSL=false";
        CON = DriverManager.getConnection(JDBC.DB_URL, JDBC.USER, JDBC.PASS);
        JDBC.OUTPUT.success("Empty databse 'wd' created for you!");
      } catch (SQLException err2) {}
    }
  }

  // Kill the connetcion to the running MySQL database
  public void closeConnection() {
    try {
      CON.close();
      JDBC.OUTPUT.success("Closed connection to the database!");
    } catch(SQLException err) {
      JDBC.OUTPUT.error(err.toString());
    }
  }

  // Migrate database schema
  public void migrateDatabase() {
    try {
      Statement stmt = CON.createStatement();
      stmt.execute("CREATE DATABASE IF NOT EXISTS wd");
      stmt.execute("use wd");
      executeScript("models/models");
      JDBC.OUTPUT.model("models/models");
    } catch (SQLException sql) {
      JDBC.OUTPUT.error(sql.toString());
    } catch (IOException io) {
      JDBC.OUTPUT.error(io.toString());
    }
  }

  public void dropDatabase() {
    try {
      Statement stmt = CON.createStatement();
      stmt.execute("DROP DATABASE wd");
      JDBC.OUTPUT.success("Database dropped!");
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

    try {
      Statement stmt = CON.createStatement();
      stmt.execute("use wd");

      // If the 'use wd' does not throw an exeption we can run the migration
      System.out.println();
      for(String s : scripts) {
        try {
          executeScript(s);
          JDBC.OUTPUT.fixture(s);
        } catch (SQLException sql) {
          JDBC.OUTPUT.error(sql.toString());
        } catch (IOException io) {
          JDBC.OUTPUT.error(io.toString());
        }
      }
      System.out.println();
    } catch (SQLException sql) {
      JDBC.OUTPUT.error("Can't apply fixtures with no database!");
    }

  }

  // Exectute a sql script
  static void executeScript(String script) throws SQLException, IOException {
    ScriptRunner runner = new ScriptRunner(CON, false, false);
    runner.runScript(new BufferedReader(new FileReader(JDBC.PATH + script + ".sql")));
  }
}
