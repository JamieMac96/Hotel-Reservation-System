
/**
 *The class Stay has the responsibility of defining and storing hotel stays.
 *
 *@author Jamie Mac Manus
 *@version 1.0
 *@since 2016-11-10
 */
public class Stay{
  private Reservation reservation;
  private Date checkInDate;
  private Date checkOutDate;

  /**
   * Constructor for creating objects of type Stay.
   * @param  reservation The reservation that preceded the stay (customer reserves room(s) then checks in to create a stay).
   */
  public Stay(Reservation reservation){
    this.reservation = reservation;
    checkInDate = new Date("N/A");
    checkOutDate = new Date("N/A");
  }

  /**
   * This method returns the reservation that the stay is based on.
   * @return reservation The reservation that the stay is based on.
   */
  public Reservation getReservation(){
    return reservation;
  }

  /**
   * This method sets the date that the customer checked in.
   * @param todaysDate Todays date (are you aware it is the current day?).
   */
  public void setCheckInDate(Date todaysDate){
    checkInDate = todaysDate;
  }

  /**
   * This method sets the checkOutDate of the stay.
   * @param expectedCheckoutDate The date we expected the customer to check out (we can presume a customer will when they're expected to).
   */
  public void setCheckOutDate(Date expectedCheckoutDate){
    checkOutDate = expectedCheckoutDate;
  }

  /**
   * This method returns the check in date.
   * @return checkInDate The check in date.
   */
  public Date getCheckInDate(){
    return checkInDate;
  }

  /**
   * This method returns the date the customer leaves the hotel.
   * @return checkOutDate The check out date.
   */
  public Date getCheckOutDate(){
    return checkOutDate;
  }

  /**
   * This method returns the type of room the customer stayed in.
   * @return A string representing the room type.
   */
  public String getFirstRoomType(){
    return reservation.getFirstRoomType();
  }

  /**
   * This method returns the number of rooms reserved for a stay.
   * @return An integer representing the number of rooms reserved.
   */
  public int getNumberOfRooms(){
    return reservation.getNumberOfRooms();
  }

  /**
   * This method returns the cost of the stay.
   * @return A double representing the cost of the stay.
   */
  public double getCost(){
    return reservation.getTotalCost();
  }

  /**
   * This method returns the number of rooms of a certain type for a stay.
   * @param  rType The type of room we are searching for.
   * @return   The number of rooms of the chosen type.
   */
  public int getNumberOfRoomsOfType(String rType){
    return reservation.getNumberOfRoomsOfType(rType);
  }

  /**
   * This method returns a string representing the stay(compatable for csv use).
   * @return A string representing the stay.
   */
  public String toString(){
    String reservationString = reservation.toString();
    String partRequired = reservationString.substring(0,reservationString.length() - 2);//removes ",f" or ",t"

    return partRequired + "," + checkInDate.getDateString() + "," + checkOutDate.getDateString();
  }

  /**
   * This method compares if two stays come from the same reservation(Note: if they come from
   *  the same reservation the other data fields will be equal due to restrictions put on check in
   *  and check out times).
   *
   * @param  stay The stay we are comparing  with
   * @return      The truth value of whether or not the two stays come from the same reservation.
   */
  public boolean equals(Stay stay){
    return stay.getReservation().equals(reservation);
  }

}
