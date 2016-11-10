import java.util.ArrayList;

/**
 *The class Hotel has the responsibility of defining a hotel and storing the associated information..
 *
 *@author Jamie Mac Manus
 *@version 1.0
 *@since 2016-11-10
 */
public class Hotel{
  private String hotelType;
  private ArrayList<RoomType> roomTypes;

  /**
   * Constructor that creates an object of Hotel type.
   * @param  hotelType the name/type of the hotel.
   */
  public Hotel(String hotelType){
    this.hotelType = hotelType;
    roomTypes = new ArrayList<RoomType>();
  }

  /**
   * Returns the name/type of the hotel.
   * @return hotelType the type/name of the hotel.
   */
  public String getHotelType(){
    return hotelType;
  }

  /**
   * Returns the RoomType for a particular index.
   * @param  index the index of the RoomType requested.
   * @return  the RoomType at the given index.
   */
  public RoomType getRoomType(int index){
    return roomTypes.get(index);
  }

  /**
   * returns all roomTypes for the hotel.
   * @return roomTypes An ArrayList of type RoomType containing the different types of rooms in the hotel.
   */
  public ArrayList<RoomType> getRoomTypes(){
    return roomTypes;
  }

  /**
   * This method allows the addition of a new RoomType to the hotel.
   * @param newRoom [description]
   */
  public void addRoomType(RoomType newRoom){
    roomTypes.add(newRoom);
  }

  /**
   * Returns the total number of rooms in the hotel.
   * @return total The total number of rooms in the hotel.
   */
  public int getTotalNumberOfRooms(){
    int total = 0;

    for(int i = 0; i < roomTypes.size(); i++){
      total += roomTypes.get(i).getNumberOfRooms();
    }
    return total;
  }

  /**
   * Checks if the hotel has a particular roomType.
   * @param  roomType the roomType we a checking for.
   * @return a boolean that represents whether or not the roomtype exists in the hotel.
   */
  public boolean hasRoomType(String roomType){
    for(int i = 0; i < roomTypes.size(); i++){
      if(roomTypes.get(i).getType().equals(roomType)){
        return true;
      }
    }
    return false;
  }
}
