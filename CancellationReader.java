import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class CancellationReader{
  private String fileString;
  private ArrayList<Cancellation> cancellations;

  public CancellationReader(String fileString){
    this.fileString = fileString;
    readInFileData();
  }

  public double costForDate(Date chosenDate){
    double cancellationCosts = 0;
    for(int i = 0; i < cancellations.size(); i++){
      if(cancellations.get(i).isRefunded()){
        if(chosenDate.getDateString().equals(cancellations.get(i).getDate().getDateString())){
          cancellationCosts += cancellations.get(i).getCost();
        }
      }
    }
    return cancellationCosts;
  }


  private void readInFileData(){
    cancellations = new ArrayList<Cancellation>();
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

       cancellations.add(new Cancellation(reservationFromFile, new Date(lineSplit[9]), Boolean.parseBoolean(lineSplit[10])));
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
