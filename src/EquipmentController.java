import java.sql.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class EquipmentController extends BaseController {

  public EquipmentController(String s) {
    super(s);
  }

  public void start() {
    int userInput;
    Boolean next = true;
    Scanner in = new Scanner(System.in);

    while (next) {
      JDBC.OUTPUT.equipmentMenu();
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
          registerEquipment();
          break;
        default:
          JDBC.OUTPUT.error("Illegal value!");
      }
    }
  }

  private void registerEquipment() {
    String name, description;
    Scanner in = new Scanner(System.in);

    try {
      JDBC.OUTPUT.user("Equipment name: ");
      name = in.next();
      JDBC.OUTPUT.user("Equipment decription: ");
      description = in.next();

      stmt = CON.createStatement();
      stmt.executeUpdate(
          "INSERT INTO equipment (name, description)" +
          "VALUES('"+ name +"' , '"+ description +"');"
          );
      JDBC.OUTPUT.success("Equipment: " + name + " created!");
    } catch(SQLException sql) {
      JDBC.OUTPUT.error(sql.toString());
    }
  }

  private void findSimilar() {
    String name;
    Scanner in = new Scanner(System.in);

    try {
      JDBC.OUTPUT.user("Find exercises simmilar to name: ");
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
}
