import java.sql.*;
import java.util.*;

public class Output {
  void welcome() {
    System.out.println("\n\n\n");
    blue("========================================================");
    blue("|                 Welcome to the diary                 |");
    blue("========================================================");
    System.out.println();
  }

  void menu() {
    System.out.println();
    white("========================================================");
    white("=======================  MENU  =========================");
    white("========================================================");
    white("| 0   -   Save and quit                                |");
    white("| 1   -   Migrate database                             |");
    white("| 2   -   Load fixtures                                |");
    white("| 3   -   Drop database                                |");
    white("| 4   -   Equipment menu                               |");
    white("| 5   -   Exercise menu                                |");
    white("| 6   -   Note menu                                    |");
    white("| 7   -   Workout menu                                 |");
    white("| 8   -   Group menu                                   |");
    white("========================================================");
  }

  void equipmentMenu() {
    System.out.println();
    white("==================  Equipment Menu  ====================");
    white("| 0   -   Back                                         |");
    white("| 1   -   Get all equipment                            |");
    white("| 2   -   Add new equipment                            |");
    white("========================================================");
  }

  void exerciseMenu() {
    System.out.println();
    white("===================  Exercise Menu  ====================");
    white("| 0   -   Back                                         |");
    white("| 1   -   Get all exercises                            |");
    white("| 2   -   Add new exercise                             |");
    white("| 3   -   Find similar exercises                      |");
    white("========================================================");
  }

  void noteMenu() {
    System.out.println();
    white("=====================  Note Menu  ======================");
    white("| 0   -   Back                                         |");
    white("| 1   -   Get all notes                                |");
    white("========================================================");
  }

  void workoutMenu() {
    System.out.println();
    white("===================  Workout Menu  =====================");
    white("| 0   -   Back                                         |");
    white("| 1   -   Get all workouts                             |");
    white("| 2   -   Add new workout                              |");
    white("| 3   -   Fetch workouts with comments                 |");
    white("========================================================");
  }

  void groupMenu() {
    System.out.println();
    white("====================  Group Menu  ======================");
    white("| 0   -   Back                                         |");
    white("| 1   -   Get all groups                               |");
    white("| 2   -   Add new group                                |");
    white("| 3   -   Find exercises in group                      |");
    white("========================================================");
  }

  void save() {
    white("========================================================");
    white("|                   SAVED AND QUIT                     |");
    white("========================================================");
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

  void user(String s) {
    System.out.print((char)27 + "[35m" + s);
  }

  void success(String s) {
    System.out.println((char)27 + "[32m" + "\nSUCCESS: " + s + "\n");
  }

  void fixture(String s) {
    System.out.println((char)27 + "[32m" + "SUCCESS: " + String.format("%-40s", s) + "loaded!");
  }

  void model(String s) {
    System.out.println((char)27 + "[32m" + "\nSUCCESS: " + String.format("%-40s", s) + "loaded!\n");
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

  String getBorder(String s, int n) {
    return new String(new char[(n*32)+1]).replace("\0", s);
  }

  void printColumns(ResultSet rs, ResultSetMetaData rsmd) {
    int numberOfColumns = 0;
    try {
      numberOfColumns = rsmd.getColumnCount();
      yellow("\n" + getBorder("=", numberOfColumns));
      for (int i = 1; i <= numberOfColumns; i++) {
        String columnName = rsmd.getColumnName(i);
        yellownoln("| " + padRight(columnName,30));

        // If this is the last slot on this row
        if(i == numberOfColumns) {
          yellownoln("|");
        }
      }

      yellow("\n" + getBorder("-", numberOfColumns));

      while (rs.next()) {
        for (int i = 1; i <= numberOfColumns; i++) {
          String columnValue = rs.getString(i);
          yellownoln("| " + padRight(columnValue,30));

          // If this is the last slot on this row
          if(i == numberOfColumns) {
            yellownoln("|");
          }
        }
        System.out.println("");
      }
    } catch (SQLException sql) {
        red(sql.toString());
      }
    yellow(getBorder("=", numberOfColumns) + "\n");
  }
}

