import java.util.Arrays;

public class RoomType{
  private String type;
  private int numberOfRooms;
  private String occupancyMin;
  private String occupancyMax;
  private double [] dailyCosts = new double[7];

  public RoomType(String type, int numberOfRooms, String occupancyMin, String occupancyMax, double[] dailyCosts){
    this.type = type;
    this.numberOfRooms = numberOfRooms;
    this.occupancyMin = occupancyMin;
    this.occupancyMax = occupancyMax;
    this.dailyCosts = dailyCosts;
  }


  public String getType(){
    return type;
  }

  public int getNumberOfRooms(){
    return numberOfRooms;
  }

  public double getCostForDay(int dayOfWeekIndex){
    return dailyCosts[dayOfWeekIndex];
  }

  public String toString(){
    return type + "," + numberOfRooms + "," + occupancyMin + "," + occupancyMax + "," + Arrays.toString(dailyCosts);
  }
}
