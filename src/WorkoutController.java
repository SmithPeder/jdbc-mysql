import java.sql.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class WorkoutController extends BaseController {

  public WorkoutController(String s) {
    super(s);
  }

  public void start() {
    int userInput;
    Boolean next = true;
    Scanner in = new Scanner(System.in);

    while (next) {
      JDBC.OUTPUT.workoutMenu();
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
        registerWorkout();
        break;
      case 3:
        fetchLatestWithNotes();
        break;
      default:
        JDBC.OUTPUT.error("Illegal value!");
      }
    }
  }

  private void registerWorkout() {
    String exercises, date, duration, shape, performance;
    Scanner in = new Scanner(System.in);

    try {
      JDBC.OUTPUT.user("Exercise id(s) [comma seperated]): ");
      exercises = in.nextLine();
      JDBC.OUTPUT.user("Workout date [YYYY-MM-DD HH:MM:SS]: ");
      date = in.nextLine();
      JDBC.OUTPUT.user("Workout duration [HH:MM:SS]: ");
      duration = in.nextLine();
      JDBC.OUTPUT.user("Workout shape: ");
      shape = in.nextLine();
      JDBC.OUTPUT.user("Workout performance: ");
      performance = in.nextLine();

      stmt = CON.createStatement();
      stmt.executeUpdate("INSERT INTO workout (date, duration, shape, performance)" + "VALUES('" + date + "' , '"
          + duration + "', " + shape + ", " + performance + ");");

      for (String i : exercises.split(",")) {
        stmt.executeUpdate(
            "INSERT INTO workout_exercise (workout_id, exercise_id) VALUES((select w.id from workout as w where date='"
                + date + "'), '" + i + "'); ");
      }

      JDBC.OUTPUT.success("Workout saved!");
    } catch (SQLException sql) {
      JDBC.OUTPUT.error(sql.toString());
    }
  }

  private void fetchLatestWithNotes() {
    String numberOfWorkouts;
    Scanner in = new Scanner(System.in);

    try {
      JDBC.OUTPUT.user("Number of workouts: ");
      numberOfWorkouts = in.nextLine();

      stmt = CON.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT w.id, w.date, n.experiance, n.additional_comment "
          + "FROM workout as w left join note as n on w.note_id=n.id " + "order by date desc limit 0,"
          + numberOfWorkouts + ";");
      ResultSetMetaData rsmd = rs.getMetaData();
      JDBC.OUTPUT.printColumns(rs, rsmd);
      JDBC.OUTPUT.success("Workouts fetched!");
    } catch (SQLException sql) {
      JDBC.OUTPUT.error(sql.toString());
    }
  }
}
