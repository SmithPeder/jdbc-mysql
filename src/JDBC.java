import java.sql.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class JDBC {
  public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
  public static String DB_URL = "jdbc:mysql://localhost:3306/?allowPublicKeyRetrieval=true&useSSL=false";
  public static final String PATH = "/Users/smith/code/jdbc-mysql/";
  public static final Output OUTPUT = new Output();
  public static final DatabaseController DB = new DatabaseController();

  static final String USER = "root";
  static final String PASS = "root";

  public static void main(String[] args) {
    // Try to make a connection to the database
    DB.makeConnection();

    int userInput;
    Boolean next = true;
    Scanner in = new Scanner(System.in);

    OUTPUT.welcome();
    while (next) {
      OUTPUT.menu();
      System.out.print("User choise: ");
      userInput = Integer.valueOf(in.next());

      switch(userInput) {
        case 0:
          next = false;
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
          EquipmentController eqc = new EquipmentController();
          eqc.start();
          break;
        case 5:
          ExerciseController ecc = new ExerciseController();
          ecc.start();
          break;
        default:
          System.out.println("DEFAULT");
      }
    }
  }
}
