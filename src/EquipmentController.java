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
          registerEquipment();
          break;
        default:
          System.out.println("DEFAULT");
      }
    }
  }

  private void registerEquipment() {
    String name, description;
    Scanner in = new Scanner(System.in);

    try {
      System.out.print("Equipment name: ");
      name = in.next();
      System.out.print("Equipment decription: ");
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
}
