public class Stay{
  private Reservation reservation;
  private Date checkInDate;
  private Date checkOutDate;

  public Stay(Reservation reservation){
    this.reservation = reservation;
    checkInDate = new Date("N/A");
    checkOutDate = new Date("N/A");
  }

  public Reservation getReservation(){
    return reservation;
  }

  public void setCheckInDate(Date todaysDate){
    checkInDate = todaysDate;
  }

  public void setCheckOutDate(Date expectedCheckoutDate){
    checkOutDate = expectedCheckoutDate;
  }

  public Date getCheckInDate(){
    return checkInDate;
  }

  public Date getCheckOutDate(){
    return checkOutDate;
  }

  public String getRoomType(){
    return reservation.getRoomType();
  }

  public int getNumberOfRooms(){
    return reservation.getNumberOfRooms();
  }

  public String toString(){
    String reservationString = reservation.toString();
    String partRequired = reservationString.substring(0,reservationString.length() - 2);//removes ",f" or ",t"

    return partRequired + "," + checkInDate.getDateString() + "," + checkOutDate.getDateString();
  }

  public boolean equals(Stay stay){
    return stay.getReservation().equals(reservation);
  }
}
