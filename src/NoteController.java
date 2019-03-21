import java.sql.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class NoteController extends BaseController {

  public NoteController(String s) {
    super(s);
  }

  public void start() {
    int userInput;
    Boolean next = true;
    Scanner in = new Scanner(System.in);

    while (next) {
      JDBC.OUTPUT.noteMenu();
      JDBC.OUTPUT.user("User choice: ");
      userInput = Integer.valueOf(in.next());

      switch (userInput) {
      case 0:
        next = false;
        break;
      case 1:
        getAll();
        break;
      case 2:
        addNote();
        break;
      default:
        JDBC.OUTPUT.error("Illegal value!");
      }
    }
  }

  private void addNote() {
    int workout;
    String purpose, experiance, additional_comment;
    Scanner in = new Scanner(System.in);

    try {
      JDBC.OUTPUT.user("Workout: ");
      workout = Integer.valueOf(in.next());
      JDBC.OUTPUT.user("Purpose: ");
      purpose = in.next();
      JDBC.OUTPUT.user("Experiance: ");
      experiance = in.next();
      JDBC.OUTPUT.user("Comment: ");
      additional_comment = in.next();

      stmt = CON.createStatement();
      stmt.executeUpdate("INSERT INTO note (purpose, experiance, additional_comment) VALUES('" + purpose + "' , '"
          + experiance + "', '" + additional_comment + "')", Statement.RETURN_GENERATED_KEYS);
      ResultSet rs = stmt.getGeneratedKeys();
      rs.next();

      stmt = CON.createStatement();
      stmt.executeUpdate("UPDATE workout SET note_id=" + rs.getLong(1) + " where id=" + workout + ";");

      JDBC.OUTPUT.success("Note: created and added!");
    } catch (SQLException sql) {
      JDBC.OUTPUT.error(sql.toString());
    }
  }
}
