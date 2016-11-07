import java.io.Writer;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class ReservationWriter{
  private String fileString;
  private Reservation userInputReservation;


  //Note: userInputReservation not initialized here: problem?
  ReservationWriter(String fileString){
    this.fileString = fileString;
  }

  public void makeReservation(HotelReader hReader){
    readInReservation(hReader);
    printReservationToFile();
  }

  private void readInReservation(HotelReader hReader){
    InputReader iReader = new InputReader();
    userInputReservation = iReader.readInValidReservation();
    userInputReservation.calculateTotalCost(hReader);
    if(userInputReservation.getReservationType().equalsIgnoreCase("ap")){
      userInputReservation.applyAdvancePurchaseDiscount();
    }
  }

  private void printReservationToFile(){
    try{
      Writer output = new BufferedWriter(new FileWriter(fileString, true));
      output.append(userInputReservation.toString() + "\n");
      output.close();
    }
    catch(IOException e){
      System.out.println("Error could not open BufferedWriter.");
      e.printStackTrace();
    }
  }

}
