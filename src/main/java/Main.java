
import java.util.List;

import model.Equipment;
import model.database.EquipmentTable;
import model.enums.EquipmentStatus;



public class Main {
  public static void main(String[] args) {
	  System.out.println(System.getProperty("user.dir"));
      EquipmentTable db = EquipmentTable.getInstance();

      List<Equipment> testList = db.getEquipmentAsList();

      for (Equipment e : testList){
        System.out.println(e.getDescription());
      }

      db.addEquipment(new Equipment(6, "nametest", "desctest", "locationtest", EquipmentStatus.MAINTENANCE));
      System.out.println("added test equipment");
      
      try {
        db.update();
      } catch (Exception e) {
          e.printStackTrace(); 
      }
      
      
    }
}
