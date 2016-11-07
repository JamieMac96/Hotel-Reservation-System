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


  public boolean stayExists(Stay stay){
    for(int i = 0; i < stays.size(); i++){
      if(stay.equals(stays.get(i))){
        return true;
      }
    }
    return false;
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
       roomsInReservation = getRoomsFromRoomNumbers(lineSplit[6]);//lineSplit[6] contains room numbers.

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
    private Room[] getRoomsFromRoomNumbers(String roomNumbers){
      String [] roomSplit = roomNumbers.split("\\+");
      Room[] rooms = new Room[roomSplit.length];
      for(int i = 0; i < rooms.length; i++){
        rooms[i] = new Room(Integer.parseInt(roomSplit[i]));
      }
      return rooms;
    }

}
