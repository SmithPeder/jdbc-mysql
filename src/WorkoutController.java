import java.sql.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class WorkoutController extends BaseController {

  public WorkoutController (String s) {
    super(s) ;
  }

  public void start() {
    int userInput;
    Boolean next = true;
    Scanner in = new Scanner(System.in);

    while (next) {
      JDBC.OUTPUT.workoutMenu();
      JDBC.OUTPUT.user("User choise: ");
      userInput = Integer.valueOf(in.next());

      switch(userInput) {
        case 0:
          next = false;
          break;
        case 1:
          getAll();
          break;
        case 2:
          registerWorkout();
          break;
        default:
          JDBC.OUTPUT.error("Illegal value!");
      }
    }
  }

  private void registerWorkout() {
    String date, time, duration, shape, performance;
    Scanner in = new Scanner(System.in);

    try {
      JDBC.OUTPUT.user("Workout date [YYYY-MM-DD HH:MM:SS]: ");
      date = in.nextLine();
      JDBC.OUTPUT.user("Workout time [HH:MM:SS]: ");
      time = in.nextLine();
      JDBC.OUTPUT.user("Workout duration [HH:MM:SS]: ");
      duration = in.nextLine();
      JDBC.OUTPUT.user("Workout shape: ");
      shape = in.nextLine();
      JDBC.OUTPUT.user("Workout performance: ");
      performance = in.nextLine();

      stmt = CON.createStatement();
      stmt.executeUpdate(
          "INSERT INTO workout (date, time, duration, shape, performance)" +
          "VALUES('"+ date +"' , '" + time + "' , '"+ duration +"', " + shape + ", " + performance + ");"
          );
      JDBC.OUTPUT.success("Workout saved!");
    } catch(SQLException sql) {
      JDBC.OUTPUT.error(sql.toString());
    }
  }
}
