
/**
 *The class Room has the responsibility defining and storing a room.
 *
 *@author Jamie Mac Manus
 *@version 1.0
 *@since 2016-11-10
 */
public class Room{
  private int roomNumber;
  private String roomType;
  private String occupancy;

  /**
   * Constructor for creating a new room object.
   * @param   roomNumber The number that identifies the room.
   * @param   roomType The type of the room.
   * @param   occupancy A string that represents the occupancy of the room.
   */
  public Room(int roomNumber, String roomType, String occupancy){
    this.roomNumber = roomNumber;
    this.roomType = roomType;
    this.occupancy = occupancy;
  }

  /**
   * Constructor for creating a new room object.
   * @param  roomNumber The number that identifies the room.
   */
  public Room(int roomNumber){
    this(roomNumber, "", "");
  }

  /**
   * This method returns the number that identifies the room.
   * @return roomNumber The number that identifies the room.
   */
  public int getRoomNumber(){
    return roomNumber;
  }

  /**
   * This method returns the type of room.
   * @return roomType The type of room.
   */
  public String getRoomType(){
    return roomType;
  }

  /**
   * This method returns the occupancy ofthe room.
   * @return occupancy A string  that represents the rooms occupancy.
   */
  public String getOccupancy(){
    return occupancy;
  }

  /**
   * This method sets the room number.
   * @param rNumber The new room number.
   */
  public void setRoomNumber(int rNumber){
    roomNumber = rNumber;
  }

  /**
   * This method sets the room type.
   * @param rType The nre type of room.
   */
  public void setRoomType(String rType){
    roomType = rType;
  }

  /**
   * This method sets the occupancy
   * @param newOccupancy The new occupancy for the room.
   */
  public void setOccupancy(String newOccupancy){
    occupancy = newOccupancy;
  }
}
