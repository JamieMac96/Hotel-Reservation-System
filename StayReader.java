import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class StayReader{
  private String fileString;
  private ArrayList<Stay> stays;

  public StayReader(String fileString){
    this.fileString = fileString;
    readInFileData();
  }

  public ArrayList<Stay> getStayInfo(){
    return stays;
  }

  public int getIndex(Stay stay){
    for(int i = 0; i < stays.size(); i++){
      if(stay.equals(stays.get(i))){
        return i;
      }
    }
    return -1;
  }

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
   * @return
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
