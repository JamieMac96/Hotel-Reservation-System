public class Room{
  private int roomNumber;
  private String roomType;
  private String occupancy;

  public Room(int roomNumber, String roomType, String occupancy){
    this.roomNumber = roomNumber;
    this.roomType = roomType;
    this.occupancy = occupancy;
  }

  public Room(int roomNumber){
    this(roomNumber, "", "");
  }

  public int getRoomNumber(){
    return roomNumber;
  }

  public String getRoomType(){
    return roomType;
  }

  public String getOccupancy(){
    return occupancy;
  }

  public void setRoomNumber(int rNumber){
    roomNumber = rNumber;
  }

  public void setRoomType(String rType){
    roomType = rType;
  }

  public void setOccupancy(String newOccupancy){
    occupancy = newOccupancy;
  }
}
