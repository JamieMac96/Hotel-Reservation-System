import java.util.ArrayList;

public class Hotel{
  private String hotelType;
  private ArrayList<RoomType> roomTypes;

  public Hotel(String hotelType){
    this.hotelType = hotelType;
    roomTypes = new ArrayList<RoomType>();
  }

  public String getHotelType(){
    return hotelType;
  }

  public RoomType getRoomType(int index){
    return roomTypes.get(index);
  }

  public ArrayList<RoomType> getRoomTypes(){
    return roomTypes;
  }

  public void addRoomType(RoomType newRoom){
    roomTypes.add(newRoom);
  }

  public int getTotalNumberOfRooms(){
    int total = 0;

    for(int i = 0; i < roomTypes.size(); i++){
      total += roomTypes.get(i).getNumberOfRooms();
    }
    return total;
  }

  public boolean hasRoomType(String roomType){
    for(int i = 0; i < roomTypes.size(); i++){
      if(roomTypes.get(i).getType().equals(roomType)){
        return true;
      }
    }
    return false;
  }
}
