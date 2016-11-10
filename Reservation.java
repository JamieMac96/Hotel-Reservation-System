import java.util.ArrayList;

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
  /*if user cancelled within 48 hours and the reservation was of standard type.*/

  //for user input.
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

  //for when reading from file.
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

  public int getReservationNumber(){
    return reservationNumber;
  }

  public String getReservationName(){
    return reservationName;
  }

  public String getReservationType(){
    return reservationType;
  }

  public Date getCheckInDate(){
    return checkInDate;
  }

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
