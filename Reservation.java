import java.util.ArrayList;

/**
 *The class Reservation has the responsibility of defining and storing a reservation.
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
   * This method returns the number of nights reserved.
   * @return numberOfNights The number of nights reserved.
   */
  public int getNumberOfNights(){
    return numberOfNights;
  }

  /**
   * This method returns the number of rooms reserved.
   * @return numberOfRooms The number of rooms reserved.
   */
  public int getNumberOfRooms(){
    return numberOfRooms;
  }

  /**
   * This method returns the total cost of the reservation.
   * @return totalCost The total cost of the reservation.
   */
  public double getTotalCost(){
    return totalCost;
  }

  /**
   * This method returns the deposit left on the reservation.
   * @return deposit The deposit left on the reservation.
   */
  public double getDeposit(){
    return deposit;
  }

  /**
   * This method returns the room type of the first room in the reservation, it is
   * used by the AnalyticsGenerator to check if a hotel has a the room types of
   * a certain stay, we can say that all the roomtypes belong to that hotel if one
   * does since each reservation can only reserve rooms at one hotel.
   *
   * @return The room type of the first room in the reservation.
   */
  public String getFirstRoomType(){
    return rooms[0].getRoomType();
  }


  /**
   * This method sets the rooms reserved.
   * @param rooms An array of rooms.
   */
  public void setRooms(Room[] rooms){
    this.rooms = rooms;
  }

  /**
   * This method sets the totalCost of the reservation.
   * @param totalCost the total cost to be set.
   */
  public void setTotalCost(double totalCost){
    this.totalCost = totalCost;
  }

  /**
   * This method flags the reservation as processed.
   */
  public void setAsProcessed(){
    processed = "t";
  }

  /**
   * This method checks if the method has been processed.
   * @return The truth value of hether or not the reservation has been process.
   */
  public boolean isProcessed(){
    return processed.equals("t");
  }

  /**
   * This method  calculates the total cost of the reservation and sets totalCost to the result.
   * @param  hReaderWithRoomData The hotelReader used to calculate the cost of certain rooms.
   * @return totalCost The total cost of the reservation.
   */
  public double calculateTotalCost(HotelReader hReaderWithRoomData){

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

  /**
   * This method returns a string that describes the reservation.
   * @return reservationInfoAsString The string that reresents the reservation.
   */
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

  /**
   * This method reduced the price by 5% for advanced purchase reservations.
   */
  public void applyAdvancePurchaseDiscount(){
    totalCost = totalCost - (totalCost / 20);
  }

  /**
   * This method checks if the reservationNumber of the res supplied is the same as the reservationNumber of the current Reservation.
   * @param  res The Reservation we are comparing with.
   * @return     The truth value of whether or not the two reservations have the same reservationNumber.
   */
  public boolean equals(Reservation res){
    //Functionality to ensure reservationNumber is unique?
    if(res.getReservationNumber() == reservationNumber){
      return true;
    }
    return false;
  }

}
