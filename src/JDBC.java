import java.sql.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class JDBC {
  public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
  public static String DB_URL = "jdbc:mysql://localhost:3306/wd?allowPublicKeyRetrieval=true&useSSL=false";
  public static final String PATH = "./";
  public static final Output OUTPUT = new Output();
  public static final DatabaseController DB = new DatabaseController();

  static final String USER = "root";
  static final String PASS = "root";

  public static void main(String[] args) {
    // Try to make a connection to the database
    OUTPUT.welcome();
    DB.makeConnection();

    int userInput;
    Boolean next = true;
    Scanner in = new Scanner(System.in);

    while (next) {
      OUTPUT.menu();
      OUTPUT.user("User choice: ");
      userInput = Integer.valueOf(in.next());

      switch (userInput) {
      case 0:
        next = false;
        DB.closeConnection();
        OUTPUT.save();
        break;
      case 1:
        DB.migrateDatabase();
        break;
      case 2:
        DB.initializeDevelopment();
        break;
      case 3:
        DB.dropDatabase();
        break;
      case 4:
        EquipmentController eqc = new EquipmentController("equipment");
        eqc.start();
        break;
      case 5:
        ExerciseController ecc = new ExerciseController("exercise");
        ecc.start();
        break;
      case 6:
        NoteController nc = new NoteController("note");
        nc.start();
        break;
      case 7:
        WorkoutController wc = new WorkoutController("workout");
        wc.start();
        break;
      case 8:
        GroupController gc = new GroupController("exercise_group");
        gc.start();
        break;
      default:
        JDBC.OUTPUT.error("Illegal value!");
      }
    }
  }
}
