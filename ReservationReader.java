import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
/**
 *
 * @author jamie
 *
 */
public class ReservationReader{
  private ArrayList<Reservation> reservations = new ArrayList<Reservation>();
  private String fileString;

  public ReservationReader(String fileString){
    this.fileString = fileString;
    readInFileData();
  }

/**
 *
 */
  public void removeExpiredReservations(){
    for(int i = 0; i < reservations.size(); i++){
      if(reservations.get(i).getCheckInDate().isMonthsOld(1) && reservations.get(i).isProcessed()){
        reservations.remove(i);
        i--;
      }
    }
    printUpdatedReservationsToFile();
  }

/**
 *
 * @param reservationNumber
 * @return
 */
  public Reservation findReservation(int reservationNumber){
    for(int i = 0; i < reservations.size(); i++){
      if(reservations.get(i).getReservationNumber() == reservationNumber){
        return reservations.get(i);
      }
    }
    return null;
  }

/**
 *
 */
  public void update(){
    reservations.clear();
    readInFileData();
  }

  /**
   *
   */
  public void printUpdatedReservationsToFile(){
    try{
      File reservationsFile = new File(fileString);
      PrintWriter reservationPrinter = new PrintWriter(reservationsFile);

      for(int i = 0; i < reservations.size(); i++){
        reservationPrinter.println(reservations.get(i).toString());
      }
      reservationPrinter.close();
    }
    catch(IOException e){
      System.out.println("Error: failed to update: " + fileString);
    }
  }

  /**
   * [applyDiscount description]
   */
  public void applyDiscount(){
    InputReader iReader = new InputReader();

    iReader.printDiscountStartPrompt();
    Reservation reservationToDiscount = iReader.getReservationToBeDiscounted(this);
    double discountAmount = iReader.getDiscountAmount();
    iReader.printDiscountEndPrompt();

    if(reservationToDiscount.getTotalCost() >= reservationToDiscount.getDeposit() + discountAmount){
      reservationToDiscount.setTotalCost(reservationToDiscount.getTotalCost() - discountAmount);
      printUpdatedReservationsToFile();
    }
    else{
      System.out.println("!Error: Reservation cost cannot be less than deposit!");
    }

  }

  public double incomeForDate(Date chosenDate){
    double income = 0;

    for(int i = 0; i < reservations.size(); i++){
      if(reservations.get(i).getCheckInDate().getDateString().equals(chosenDate.getDateString())){
        income += reservations.get(i).getTotalCost();
      }
    }
    return income;
  }

  /**
   *
   */
  private void readInFileData(){
    reservations = new ArrayList<Reservation>();
    try{
     File userFile = new File(fileString);
     Scanner fileIn = new Scanner(userFile);
     Reservation reservationFromFile;

     String [] lineSplit;
     Room[] roomsInReservation;
     Date checkInDate;

     while(fileIn.hasNext()){
       lineSplit = fileIn.nextLine().split(",");
       checkInDate = new Date(lineSplit[3]);
       roomsInReservation = getRoomsFromRoomNumbers(lineSplit[6]);//lineSplit[6] contains room numbers.

       reservationFromFile = new Reservation(Integer.parseInt(lineSplit[0]), lineSplit[1], lineSplit[2], checkInDate,
                                        Integer.parseInt(lineSplit[4]), Integer.parseInt(lineSplit[5]), roomsInReservation,
                                         Double.parseDouble(lineSplit[7]), Double.parseDouble(lineSplit[8]), lineSplit[9]);

       reservations.add(reservationFromFile);
     }
    }
    catch(IOException e){
     System.out.println("Error: failed to read from: " + fileString);
     e.printStackTrace();
    }
  }

/**
 *
 * @param roomNumbers
 * @return
 */
  private Room[] getRoomsFromRoomNumbers(String roomNumbers){
    String [] roomSplit = roomNumbers.split("\\*");
    String [] roomElementSplit;
    Room[] rooms = new Room[roomSplit.length];
    for(int i = 0; i < rooms.length; i++){
      roomElementSplit = roomSplit[i].split("\\.");
      rooms[i] = new Room(Integer.parseInt(roomElementSplit[0]), roomElementSplit[1], roomElementSplit[2]);
    }
    return rooms;
  }
}
