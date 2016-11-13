/**
 *The class AnalyticsGenerator has the responsibility of
 *defining what a cancellation is and storing that data.
 *
 *@author Jamie Mac Manus
 *@version 1.0
 *@since 2016-11-10
 */
public class Cancellation{
  private Reservation reservation;
  private Date cancellationDate;
  private boolean refunded;

  /**
   * Constructor for creating a Cancellation object (used when creating cancellation from user input).
   * @param  reservation The reservation that is being cancelled.
   *
   */
  public Cancellation(Reservation reservation){
    this.reservation = reservation;
    cancellationDate = Date.getCurrentDate();
    refunded = amountMustBeRefunded();
  }

  /**
   * Constructor for creatinf a Cancellation object (used when creating a cancellation read from Cancellations.csv file).
   * @param   reservation The reservation that is being cancelled.
   * @param   cancellationDate The date on which the cancellation was made.
   * @param   refunded whether or not the cancellation resulted in a refund.
   */
  public Cancellation(Reservation reservation, Date cancellationDate, boolean refunded){
    this.reservation = reservation;
    this.cancellationDate = cancellationDate;
    this.refunded = refunded;
  }

  /**
   * Returns whether or not the cancellation resulted in a refund.
   * @return refunded Whether or not the cancellation resulted in a refund.
   */
  public boolean isRefunded(){
    return refunded;
  }

  /**
   * Returns the date.
   * @return cancellationDate The date the cancellation was made.
   */
  public Date getDate(){
    return cancellationDate;
  }

  /**
   * Returns the total cost of the reservation being cancelled.
   * @return the total cost of the reservation being cancelled.
   */
  public double getCost(){
    return reservation.getTotalCost();
  }

  /**
   * This method returns the reservation that belongss to the current cancellation.
   * @return reservation the reservation that belongs to this cancellation.
   */
  public Reservation getReservation(){
    return reservation;
  }

  /**
   * Returns a string that represents the data contained by a cancellation and is compatible with a csv file.
   * @return the string representation of the Cancellation.
   */
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
