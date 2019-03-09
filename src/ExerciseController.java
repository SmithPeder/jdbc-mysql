import java.sql.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ExerciseController extends BaseController {

  public ExerciseController (String s) {
    super(s);
  }

  public void start() {
    int userInput;
    Boolean next = true;
    Scanner in = new Scanner(System.in);

    while (next) {
      JDBC.OUTPUT.exerciseMenu();
      System.out.print("User choise: ");
      userInput = Integer.valueOf(in.next());

      switch(userInput) {
        case 0:
          next = false;
          break;
        case 1:
          getAll();
          break;
        case 2:
          registerExercise();
          break;
        default:
          System.out.println("DEFAULT");
      }
    }
  }

  private void registerExercise() {
    String name, equipment_id, description;
    Scanner in = new Scanner(System.in);

    try {
      System.out.print("Exercise name: ");
      name = in.next();
      System.out.print("Exercise equipment id: ");
      equipment_id = in.next();
      System.out.print("Exercise description: ");
      description = in.next();

      stmt = CON.createStatement();
      stmt.executeUpdate(
          "INSERT INTO exercise (name, equipment_id, description)" +
          "VALUES('"+ name +"' , " + equipment_id + " , '"+ description +"');"
          );
      JDBC.OUTPUT.success("Equipment: " + name + " created!");
    } catch(SQLException sql) {
      JDBC.OUTPUT.error(sql.toString());
    }
  }
}
