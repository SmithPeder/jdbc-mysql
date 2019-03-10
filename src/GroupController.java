import java.sql.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class GroupController extends BaseController{

  public GroupController (String s) {
    super(s);
  }

  public void start() {
    int userInput;
    Boolean next = true;
    Scanner in = new Scanner(System.in);

    while (next) {
      JDBC.OUTPUT.groupMenu();
      JDBC.OUTPUT.user("User choice: ");
      userInput = Integer.valueOf(in.next());

      switch(userInput) {
        case 0:
          next = false;
          break;
        case 1:
          getAll();
          break;
        case 2:
          registerGroup();
          break;
        case 3:
          findSimilarToGroup();
          break;
        default:
          JDBC.OUTPUT.error("Illegal value!");
      }
    }
  }

  private void registerGroup() {
    String name;
    Scanner in = new Scanner(System.in);

    try {
      JDBC.OUTPUT.user("Group name: ");
      name = in.next();

      stmt = CON.createStatement();
      stmt.executeUpdate(
          "INSERT INTO exercise_group (name) VALUES('"+ name +"');"
          );
      JDBC.OUTPUT.success("Group: " + name + " created!");
    } catch(SQLException sql) {
      JDBC.OUTPUT.error(sql.toString());
    }
  }

  protected void findSimilarToGroup() {
    String name;
    Scanner in = new Scanner(System.in);

    try {
      JDBC.OUTPUT.user("Group name: ");
      name = in.next();

      stmt = CON.createStatement();
      ResultSet rs = stmt.executeQuery(
          "select eg.name, e.name, e.description from exercise_exercise_group as " +
          "eeg join exercise_group as eg " +
          "on eeg.exercise_group_id=eg.id join exercise as e on eeg.exercise_id=e.id " +
          "where eg.name='" + name + "';"
          );
      ResultSetMetaData rsmd = rs.getMetaData();
      JDBC.OUTPUT.printColumns(rs, rsmd);
    } catch(SQLException sql) {
      JDBC.OUTPUT.error(sql.toString());
    }
  }
}
