public class Cancellation{
  private Reservation resToBeCancelled;
  private Date cancellationDate;
  private boolean refunded;

  public Cancellation(Reservation resToBeCancelled){
    this.resToBeCancelled = resToBeCancelled;
    cancellationDate = Date.getCurrentDate();
    refunded = amountMustBeRefunded();
  }

  public Cancellation(Reservation resToBeCancelled, Date cancellationDate, boolean refunded){
    this.resToBeCancelled = resToBeCancelled;
    this.cancellationDate = cancellationDate;
    this.refunded = refunded;
  }

  public String toString(){
    String reservationString = resToBeCancelled.toString();
    String partRequired = reservationString.substring(0,reservationString.length() - 2);//removes ",f" or ",t"

    return partRequired + "," + cancellationDate.getDate() + "," + refunded;
  }

  private boolean amountMustBeRefunded(){
    if(resToBeCancelled.getReservationType().equalsIgnoreCase("s")){
      if(resToBeCancelled.getCheckInDate().isDaysFromNow(2)){
        return true;
      }
    }
    return false;
  }
}
