import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.IOException;
import java.io.File;

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
    if(sReader.getIndex(userInputStay) == -1){
      attemptFilePrint(sReader);
    }
    else{
      System.out.println("!Error: Already checked in!");
    }
  }

  public void checkOutCustomer(ReservationReader rReader, StayReader sReader){
    userInputStay = iReader.readInValidStay(rReader);
    int stayIndex;
    if((stayIndex = sReader.getIndex(userInputStay)) != -1){
      userInputStay.setCheckOutDate(getExpectedCheckoutDate());//we can assume that a customer will always leave when they are expected to.
      userInputStay.setCheckInDate(Date.getCurrentDate());
      sReader.getStayInfo().set(stayIndex, userInputStay);
      updateFile(sReader);
    }
    else{
      System.out.println("!Error: You must check in before you can check out!");
    }
  }

  private void attemptFilePrint(StayReader sReader){
    if(checkinHappenedOnExpectedDay()){
      userInputStay.setCheckInDate(Date.getCurrentDate());
      sReader.getStayInfo().add(userInputStay);
      updateFile(sReader);
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

    return expectedCheckinDate.getDateString().equals(Date.getCurrentDate().getDateString());
  }

  private void updateFile(StayReader sReader){
    ConstantUtils utils = new ConstantUtils();
    try{
      ArrayList<Stay> stays = sReader.getStayInfo();
      File staysFile = new File(utils.STAYS_FILE);
      PrintWriter staysPrinter = new PrintWriter(staysFile);

      for(int i = 0; i < stays.size(); i++){
        staysPrinter.println(stays.get(i).toString());
      }
      staysPrinter.close();
    }
    catch(IOException e){
      System.out.println("Error: failed to update: " + utils.STAYS_FILE);
    }
  }
}
