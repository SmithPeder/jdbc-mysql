import java.sql.*;

public class Output {
  void welcome() {
    blue("=============================");
    blue("|   Welcome to the diary    |");
    blue("=============================");
    System.out.println();
  }

  void menu() {
    white("=============================");
    white("| 0 - Save and quit         |");
    white("| 1 - Migrate database      |");
    white("| 2 - Load fixtures         |");
    white("| 3 - Drop database         |");
    white("| 4 - Equipment menu        |");
    white("| 5 - Exercise menu         |");
    white("| 6 - Note menu             |");
    white("| 7 - Workout menu          |");
    white("| 8 - Group menu            |");
    white("=============================");
  }

  void equipmentMenu() {
    white("=============================");
    white("| 0 - Back                  |");
    white("| 1 - Get all equipment     |");
    white("| 2 - Add new equipment     |");
    white("=============================");
  }

  void exerciseMenu() {
    white("=============================");
    white("| 0 - Back                  |");
    white("| 1 - Get all exercises     |");
    white("=============================");
  }

  void noteMenu() {
    white("=============================");
    white("| 0 - Back                  |");
    white("| 1 - Get all notes         |");
    white("=============================");
  }

  void workoutMenu() {
    white("=============================");
    white("| 0 - Back                  |");
    white("| 1 - Get all workouts      |");
    white("=============================");
  }

  void groupMenu() {
    white("=============================");
    white("| 0 - Back                  |");
    white("| 1 - Get all groups        |");
    white("=============================");
  }

  void save() {
    white("=============================");
    white("|      SAVED AND QUIT       |");
    white("=============================");
  }

  void yellow(String s) {
    System.out.println((char)27 + "[33m" + s);
  }

  void yellownoln(String s) {
    System.out.print((char)27 + "[33m" + s);
  }

  void green(String s) {
    System.out.println((char)27 + "[32m" + s);
  }

  void blue(String s) {
    System.out.println((char)27 + "[34m" + s);
  }

  void success(String s) {
    System.out.println((char)27 + "[32m" + "\nSUCCESS: " + s + "\n");
  }

  void fixture(String s) {
    System.out.println((char)27 + "[32m" + "SUCCESS: " + String.format("%-40s", s) + "loaded!");
  }

  void red(String s) {
    System.out.println((char)27 + "[31m" + s);
  }

  void error(String s) {
    System.out.println((char)27 + "[31m" + "\nERROR: " + s + "\n");
  }

  void white(String s) {
    System.out.println((char)27 + "[0m" + s);
  }

  String padRight(String s, int n) {
    return String.format("%1$-" + n + "s", s);
  }

  void printColumns(ResultSet rs, ResultSetMetaData rsmd) {
    try {
    int numberOfColumns = rsmd.getColumnCount();
        for (int i = 1; i <= numberOfColumns; i++) {
          String columnName = rsmd.getColumnName(i);
          yellownoln(padRight(columnName,30));
        }
        System.out.println("\n");

        while (rs.next()) {
          for (int i = 1; i <= numberOfColumns; i++) {
            String columnValue = rs.getString(i);
            yellownoln(padRight(columnValue,30));
          }
          System.out.println("");
        }

      } catch (SQLException sql) {
        red(sql.toString());
      }
    }
}

