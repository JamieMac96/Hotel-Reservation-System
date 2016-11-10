
/**
 *The class Reservation has the responsibility of defining and storing a reservations.
 *
 *@author Jamie Mac Manus
 *@version 1.0
 *@since 2016-11-10
 */
import java.util.ArrayList;

/**
 *The class Reservation has the responsibility of defining and storing a reservations.
 *
 *@author Jamie Mac Manus
 *@version 1.0
 *@since 2016-11-10
 */
public class Reservation{
  private int reservationNumber;
  private String reservationName;
  private String reservationType;
  private Date checkInDate;
  private int numberOfNights;
  private int numberOfRooms;
  private Room[] rooms;
  private double totalCost;
  private double deposit;
  private String processed;

  /**
   * Constructor to create new instance of Reservation (used when reading from user input).
   * @param   reservationNumber An integer that identifies the reservation.
   * @param   reservationName The chosen name of the reservation.
   * @param   reservationType The type of the reservation(S or AP).
   * @param   checkInDate The date that the customer is planned to check in.
   * @param   numberOfNights The number of nights the customer will stay.
   * @param   numberOfRooms The number of rooms reserved.
   * @param   deposit The deposit left.
   */
  public Reservation(int reservationNumber, String reservationName,
                     String reservationType, Date checkInDate,
                     int numberOfNights, int numberOfRooms, double deposit){

    this.reservationNumber = reservationNumber;
    this.reservationName = reservationName;
    this.reservationType = reservationType;
    this.checkInDate = checkInDate;
    this.numberOfNights = numberOfNights;
    this.numberOfRooms = numberOfRooms;
    this.rooms = new Room[numberOfRooms];
    this.totalCost = 0.0;
    this.deposit = deposit;
    processed = "f";
  }

  /**
   * [Reservation description]
   * @param   reservationNumber An integer that identifies the reservation.
   * @param   reservationName The chosen name of the reservation.
   * @param   reservationType The type of the reservation(S or AP).
   * @param   checkInDate The date that the customer is planned to check in.
   * @param   numberOfNights The number of nights the customer will stay.
   * @param   numberOfRooms The number of rooms reserved.
   * @param   rooms The rooms reserved by the reservation
   * @param   totalCost The total cost of the reservation.
   * @param   deposit The deposit left.
   * @param   processed Whether or not the reservation has been processed yet.
   */
  public Reservation(int reservationNumber, String reservationName,
                     String reservationType, Date checkInDate,
                     int numberOfNights, int numberOfRooms, Room[] rooms,
                     double totalCost, double deposit, String processed){
     this.reservationNumber = reservationNumber;
     this.reservationName = reservationName;
     this.reservationType = reservationType;
     this.checkInDate = checkInDate;
     this.numberOfNights = numberOfNights;
     this.numberOfRooms = numberOfRooms;
     this.rooms = rooms;
     this.totalCost = totalCost;
     this.deposit = deposit;
     this.processed = processed;
  }

  /**
   * This method returns the reservation number.
   * @return reservationNumber the number that identifies the reservation.
   */
  public int getReservationNumber(){
    return reservationNumber;
  }

  /**
   * This method returns the reservation name.
   * @return reservationName The name of the reservation.
   */
  public String getReservationName(){
    return reservationName;
  }

  /**
   * This method returns the type of reservation(S or AP).
   * @return reservationType the type of reservation.
   */
  public String getReservationType(){
    return reservationType;
  }

  /**
   * This method returns the checkInDate.
   * @return checkInDate The check in date.
   */
  public Date getCheckInDate(){
    return checkInDate;
  }

  /**
   * [getNumberOfNights description]
   * @return [description]
   */
  public int getNumberOfNights(){
    return numberOfNights;
  }

  public int getNumberOfRooms(){
    return numberOfRooms;
  }

  public double getTotalCost(){
    return totalCost;
  }

  public double getDeposit(){
    return deposit;
  }

  public String getRoomType(){
    return rooms[0].getRoomType();
  }

  public void setRooms(Room[] rooms){
    this.rooms = rooms;
  }

  public void setTotalCost(double totalCost){
    this.totalCost = totalCost;
  }


  public void setAsProcessed(){
    processed = "t";
  }

  public boolean isProcessed(){
    return processed.equals("t");
  }

  public double calculateTotalCost(HotelReader hReaderWithRoomData){
    /*Note: we need to pass the HotelReader here so that we can find the pricing of the rooms
      involved in the reservation.*/

    ArrayList<RoomType> roomTypeList = hReaderWithRoomData.getAllRoomTypes();
    int dayOfWeekIndex = checkInDate.getDayOfWeekIndex();
    double totalCost = 0;
    String roomTypeFromFile, roomTypeFromUserInput;

    for(int i = 0; i < rooms.length; i++){
      for(int j = 0; j < roomTypeList.size(); j++){
        roomTypeFromFile = roomTypeList.get(j).getType();
        roomTypeFromUserInput = rooms[i].getRoomType();
        if(roomTypeFromFile.equals(roomTypeFromUserInput)){
          for(int k = 0; k < numberOfNights; k++){
            totalCost += roomTypeList.get(j).getCostForDay(((dayOfWeekIndex + k) % 6));
          }
        }
      }
    }

    this.totalCost = totalCost;

    return totalCost;
  }

  public String toString(){
    String reservationInfoAsString = reservationNumber + "," + reservationName + "," + reservationType + "," + checkInDate.getDateString() +
                                      "," + numberOfNights + "," + numberOfRooms + ",";

    for(int i = 0; i < rooms.length; i++){
      if(i == rooms.length - 1){
        reservationInfoAsString += rooms[i].getRoomNumber() + "." + rooms[i].getRoomType() + "." + rooms[i].getOccupancy();
      }
      else{
        reservationInfoAsString += rooms[i].getRoomNumber() + "." + rooms[i].getRoomType() + "." + rooms[i].getOccupancy() + "*";
      }
    }

    reservationInfoAsString += "," + totalCost + "," + deposit + "," + processed;

    return reservationInfoAsString;
  }

  public void applyAdvancePurchaseDiscount(){
    totalCost = totalCost - (totalCost / 20);
  }

  public boolean equals(Reservation res){
    //Functionality to ensure reservationNumber is unique?
    if(res.getReservationNumber() == reservationNumber){
      return true;
    }
    return false;
  }

}
