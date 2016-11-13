import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *The class Stay has the responsibility storing and maintaining all the contents of the Stays.csv file.
 *
 *@author Jamie Mac Manus
 *@version 1.0
 *@since 2016-11-10
 */
public class StayReader{
  private String fileString;
  private ArrayList<Stay> stays;

  /**
   * Constructor for creating a new CancellationWriter.
   * @param  fileString The URI for the Cancellation.csv file.
   */
  public StayReader(String fileString){
    this.fileString = fileString;
    readInFileData();
  }

  /**
   * This method returns all the info relating to stays in the system.
   * @return stays All the stay info in the system.
   */
  public ArrayList<Stay> getStayInfo(){
    return stays;
  }

  /**
   * This method gets the index of a given stay.
   * @param  stay The stay that we are searching for.
   * @return i The index of the parameter stay.
   */
  public int getIndex(Stay stay){
    for(int i = 0; i < stays.size(); i++){
      if(stay.equals(stays.get(i))){
        return i;
      }
    }
    return -1;
  }

  /**
   * This method returns the total income from stays for a particular date.
   * @param  chosenDate The date that we are getting the income for.
   * @return income The total stay income for the chosen day.
   */
  public double incomeForDate(Date chosenDate){
    double income = 0;

    for(int i = 0; i < stays.size(); i++){
      if(chosenDate.getDateString().equals(stays.get(i).getCheckInDate().getDateString())){
        income += stays.get(i).getCost();
      }
    }
    return income;
  }

  private void readInFileData(){
    stays = new ArrayList<Stay>();
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
       roomsInReservation = getRooms(lineSplit[6]);//lineSplit[6] contains room numbers.

       reservationFromFile = new Reservation(Integer.parseInt(lineSplit[0]), lineSplit[1], lineSplit[2], checkInDate,
                                        Integer.parseInt(lineSplit[4]), Integer.parseInt(lineSplit[5]), roomsInReservation,
                                         Double.parseDouble(lineSplit[7]), Double.parseDouble(lineSplit[8]), "");

       stays.add(new Stay(reservationFromFile));
       stays.get(stays.size() - 1).setCheckInDate(new Date(lineSplit[9]));
       stays.get(stays.size() - 1).setCheckOutDate(new Date(lineSplit[10]));
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
   * @return rooms The rooms for that particular reservation.
   */
    private Room[] getRooms(String roomInfo){
      String [] roomSplit = roomInfo.split("\\*");
      String [] roomInfoSplit;
      Room[] rooms = new Room[roomSplit.length];
      for(int i = 0; i < rooms.length; i++){
        roomInfoSplit = roomSplit[i].split("\\.");
        rooms[i] = new Room(Integer.parseInt(roomInfoSplit[0]), roomInfoSplit[1], roomInfoSplit[2]);
      }
      return rooms;
    }

}
