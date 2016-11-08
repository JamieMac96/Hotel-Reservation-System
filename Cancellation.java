public class Cancellation{
  private Reservation reservation;
  private Date cancellationDate;
  private boolean refunded;

  public Cancellation(Reservation reservation){
    this.reservation = reservation;
    cancellationDate = Date.getCurrentDate();
    refunded = amountMustBeRefunded();
  }

  public Cancellation(Reservation reservation, Date cancellationDate, boolean refunded){
    this.reservation = reservation;
    this.cancellationDate = cancellationDate;
    this.refunded = refunded;
  }

  public String toString(){
    String reservationString = reservation.toString();
    String partRequired = reservationString.substring(0,reservationString.length() - 2);//removes ",f" or ",t"

    return partRequired + "," + cancellationDate.getDateString() + "," + refunded;
  }

  private boolean amountMustBeRefunded(){
    if(reservation.getReservationType().equalsIgnoreCase("s")){
      if(reservation.getCheckInDate().isDaysFromNow(2)){
        return true;
      }
    }
    return false;
  }
}
