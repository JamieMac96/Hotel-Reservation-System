import java.util.Arrays;

/**
 *The class RoomType has the responsibility of defining and storing room types.
 *
 *@author Jamie Mac Manus
 *@version 1.0
 *@since 2016-11-10
 */
public class RoomType{
  private String type;
  private int numberOfRooms;
  private String occupancyMin;
  private String occupancyMax;
  private double [] dailyCosts = new double[7];

  /**
   * Constructor for creating new objects of type RoomType.
   * @param   type the string that describes the room type.
   * @param   numberOfRooms The number of available rooms of this type.
   * @param   occupancyMin The minimum occupancy for this room type.
   * @param   occupancyMax The maximum occupancy for this room type.
   * @param   dailyCosts An array of douvles that stores the cost of staying in this type of room for each night of the week.
   * @return  [description]
   */
  public RoomType(String type, int numberOfRooms, String occupancyMin, String occupancyMax, double[] dailyCosts){
    this.type = type;
    this.numberOfRooms = numberOfRooms;
    this.occupancyMin = occupancyMin;
    this.occupancyMax = occupancyMax;
    this.dailyCosts = dailyCosts;
  }


  /**
   * This method returns a string that describes the type of room.
   * @return type A string that describes the type of room.
   */
  public String getType(){
    return type;
  }

  /**
   * This method returns the number of available rooms of this type.
   * @return numberOfRooms The number of rooms available of this type.
   */
  public int getNumberOfRooms(){
    return numberOfRooms;
  }

  /**
   * This method returns the cost of a room of this type for a given day of the week.
   * @param  dayOfWeekIndex the index that represnts the day of the week (0-6/mon-sun).
   * @return  dailyCosts[dayOfWeekIndex] The cost of the room type for a given day.
   */
  public double getCostForDay(int dayOfWeekIndex){
    return dailyCosts[dayOfWeekIndex];
  }

  /**
   * This method returns a string that represnts the room type.
   * @return A string that represents the string type.
   */
  public String toString(){
    return type + "," + numberOfRooms + "," + occupancyMin + "," + occupancyMax + "," + Arrays.toString(dailyCosts);
  }
}
