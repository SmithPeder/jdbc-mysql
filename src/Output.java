import java.sql.*;

public class Output {
  void welcome() {
    System.out.println("\n\n");
    white("=============================");
    white("|   Welcome to the diary    |");
    white("=============================");
    System.out.println("\n\n");
  }

  void menu() {
    white("=============================");
    white("| 0 - Save and quit         |");
    white("| 1 - Migrate database      |");
    white("| 2 - Load fixtures         |");
    white("| 3 - Drop database         |");
    white("| 4 - Equipment menu        |");
    white("| 5 - Exercise menu         |");
    white("| 4 - Equipment menu        |");
    white("| 4 - Equipment menu        |");
    white("| 4 - Equipment menu        |");
    white("=============================");
  }

  void equipmentMenu() {
    white("=============================");
    white("| 0 - Back                  |");
    white("| 1 - Get all equipment     |");
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

  void groupMenu() {
    white("=============================");
    white("| 0 - Back                  |");
    white("| 1 - Get all groups        |");
    white("=============================");
  }

  void save() {
    System.out.println("\n\n");
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

  void success(String s) {
    System.out.println((char)27 + "[32m" + "\nSUCCESS: " + s + "\n");
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

  void printColumns(ResultSet rs, ResultSetMetaData rsmd) {
    try {
    int numberOfColumns = rsmd.getColumnCount();
        for (int i = 1; i <= numberOfColumns; i++) {
          if (i > 1) System.out.print(",  ");
          String columnName = rsmd.getColumnName(i);
          yellownoln(columnName);
        }
        System.out.println("");

        while (rs.next()) {
          for (int i = 1; i <= numberOfColumns; i++) {
            if (i > 1) System.out.print(",  ");
            String columnValue = rs.getString(i);
            yellownoln(columnValue);
          }
          System.out.println("");
        }

      } catch (SQLException sql) {
        red(sql.toString());
      }
    }
}

