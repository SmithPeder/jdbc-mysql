import java.sql.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class GroupController{
  Statement stmt = null;
  Connection CON = DatabaseController.CON;

  public void start() {
    int userInput;
    Boolean next = true;
    Scanner in = new Scanner(System.in);

    while (next) {
      JDBC.OUTPUT.groupMenu();
      System.out.print("User choise: ");
      userInput = Integer.valueOf(in.next());

      switch(userInput) {
        case 0:
          next = false;
          break;
        case 1:
          getAll();
          break;
        default:
          System.out.println("DEFAULT");
      }
    }
  }

  private void getAll() {
    try {
      stmt = CON.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM exercise_group;");
      ResultSetMetaData rsmd = rs.getMetaData();
      JDBC.OUTPUT.printColumns(rs, rsmd);
    } catch(SQLException sql) {
      JDBC.OUTPUT.red(sql.toString());
    }
  }
}
