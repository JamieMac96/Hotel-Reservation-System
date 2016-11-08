import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class HotelReader{
  private String fileString;
  private ArrayList<Hotel> hotelInfo;

  public HotelReader(String fileString){
    this.fileString = fileString;
    hotelInfo = new ArrayList<Hotel>();
    readInFileData();
  }


/*  public boolean hotelExists(Hotel hotel){
    for(int i = 0; i < hotelInfo.size(); i++){
      if(hotel.equals(hotelInfo.get(i))){
        return true;
      }
    }
    return false;
  }*/


  public Hotel getHotel(int index){
    return hotelInfo.get(index);
  }


  public ArrayList<Hotel> getHotelInfo(){
    return hotelInfo;
  }

  public ArrayList<RoomType> getAllRoomTypes(){
    ArrayList<RoomType> roomTypes = new ArrayList<RoomType>();

    for(int i = 0; i < hotelInfo.size(); i++){
      roomTypes.addAll(hotelInfo.get(i).getRoomTypes());
    }

    return roomTypes;
  }



  private void readInFileData(){
    try{
       File userFile = new File(fileString);
       Scanner fileIn = new Scanner(userFile);
       String hotelStarRating = "";
       ArrayList<String> allFileInfo = getFileInfo(fileIn);
       int numberOfHotels = 0;
       double [] dailyRoomCosts = new double[7];
       String [] lineSplit = {"", ""};

       for(int i = 0; i < allFileInfo.size(); i++){
         lineSplit = allFileInfo.get(i).split(",");
         dailyRoomCosts = getDailyCostsOfRoom(lineSplit);
         if(!lineSplit[0].equals("")){
           hotelInfo.add(new Hotel(lineSplit[0]));
           numberOfHotels++;
         }
         hotelInfo.get(numberOfHotels - 1).addRoomType(new RoomType(lineSplit[1], Integer.parseInt(lineSplit[2]), lineSplit[3], lineSplit[4], dailyRoomCosts));
       }
     }
      catch(IOException e){
        System.out.println("Error: failed to read from: " + fileString);
    }
  }

  private double [] getDailyCostsOfRoom(String [] lineSplit){
    double [] roomCosts = new double[7];
    for(int i = 0, j = 5; i < roomCosts.length; i++ , j++){
      roomCosts[i] = Double.parseDouble(lineSplit[j]);
    }
    return roomCosts;
  }


  private ArrayList<String> getFileInfo(Scanner fileIn){
    ArrayList<String> fileInfo = new ArrayList<String>();
    while(fileIn.hasNext()){
      fileInfo.add(fileIn.nextLine());
    }
    return fileInfo;
  }


}
