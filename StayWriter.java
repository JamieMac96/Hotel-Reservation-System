import java.io.Writer;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class StayWriter{
  private String fileString;
  private Stay userInputStay;
  private InputReader iReader;


  public StayWriter(String fileString){
    this.fileString = fileString;
     iReader = new InputReader();
  }

  public void checkInCustomer(ReservationReader rReader, StayReader sReader){
    userInputStay = iReader.readInValidStay(rReader);
    if(!sReader.stayExists(userInputStay)){
      attemptStayFilePrint();
    }
    else{
      System.out.println("!Error: Already checked in!");
    }
  }

  public void checkOutCustomer(ReservationReader rReader, StayReader sReader){
    userInputStay = iReader.readInValidStay(rReader);
    if(sReader.stayExists(userInputStay)){
      userInputStay.setCheckOutDate(getExpectedCheckoutDate());//we can assume that a customer will always leave when they are expected to.
      printStayToFile();
    }
    else{
      System.out.println("!Error: You must check in before you can check out!");
    }
  }

  private void attemptStayFilePrint(){
    if(checkinHappenedOnExpectedDay()){
      userInputStay.setCheckInDate(Date.getCurrentDate());
      printStayToFile();
    }
    else{
      System.out.println("!Error: This checkin is not expected today!");
    }
  }

  private Date getExpectedCheckoutDate(){
    Reservation res = userInputStay.getReservation();
    Date expectedCheckinDate = res.getCheckInDate();
    expectedCheckinDate.incrementDays(res.getNumberOfNights());
    return expectedCheckinDate;
  }

  private boolean checkinHappenedOnExpectedDay(){
    Reservation res = userInputStay.getReservation();
    Date expectedCheckinDate = res.getCheckInDate();

    return expectedCheckinDate.equals(Date.getCurrentDate());
  }

  private void printStayToFile(){
    try{
      Writer output = new BufferedWriter(new FileWriter(fileString, true));
      output.append(userInputStay.toString() + "\n");
      output.close();
    }
    catch(IOException e){
      System.out.println("Error could not open BufferedWriter.");
      e.printStackTrace();
    }
  }
}
