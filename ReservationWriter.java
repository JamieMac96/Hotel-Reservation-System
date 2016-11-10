import java.io.Writer;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 *The class ReservationWriter has the responsibility of writing user input reservations to file.
 *
 *@author Jamie Mac Manus
 *@version 1.0
 *@since 2016-11-10
 */
public class ReservationWriter{
  private String fileString;
  private Reservation userInputReservation;


  /**
   * Constructor for creating instances of the ReservationWriter class.
   * @param The URI for the Cancellation.csv file.
   */
  ReservationWriter(String fileString){
    this.fileString = fileString;
  }

  /**
   * This method reads in a reservation from the user and prints the result to file.
   * @param hReader The object that contains info about hotels (allows for reservation validation).
   */
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
