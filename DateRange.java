/**
 *The class DateRange has the responsibility of storing two dates that define a range of time.
 *
 *@author Jamie Mac Manus
 *@version 1.0
 *@since 2016-11-10
 */
public class DateRange{
  private Date startDate;
  private Date endDate;

  /**
   * Constructor to create a DateRange object.
   * @param   startDate The date that represents the start of the DateRange.
   * @param   endDate The date that represents the end of the DateRange.
   */
  public DateRange(Date startDate, Date endDate){
    this.startDate = startDate;
    this.endDate = endDate;
  }

  /**
   * returns a String representing the starting date in the range.
   * @return the string representing the starting date of the range.
   */
  public String getStartDateString(){
    return startDate.getDateString();
  }

  /**
   * returns a String representing the end date in the range.
   * @return the string representing the end date of the range.
   */
  public String getEndDateString(){
    return endDate.getDateString();
  }

  /**
   * returns a the date object for the first day in the range.
   * @return startDate the first Date in the range.
   */
  public Date getStartDate(){
    return startDate;
  }

  /**
   * returns the date object for the last day in the range.
   * @return endDate the last Date in the range.
   */
  public Date getEndDate(){
    return endDate;
  }

  /**
   * checks if the startDate is before or equal to the endDate.
   * @return a boolean that states whether or not the date range is valid.
   */
  public boolean isValidRange(){
    return (startDate.isBeforeOrEqual(endDate) && !endDate.isFutureDate());
  }

  /**
   * Returns the number of days in the DateRange.
   * @return totalDaysBetweenDates the total number of days between the start and end dates of the range.
   */
  public int getLengthInDays(){
    int dayDifferentialForThisYear = endDate.getDaysSinceStartOfYear() - startDate.getDaysSinceStartOfYear();
    int totalDaysBetweenDates = dayDifferentialForThisYear;
    int yearDifferential = endDate.getYear() - startDate.getYear();

    for(int i = 0; i < yearDifferential; i++){
      if(Date.isLeapYear(startDate.getYear() + i)){
        totalDaysBetweenDates += 366;
      }
      else{
        totalDaysBetweenDates += 365;
      }
    }
    return totalDaysBetweenDates;
  }
}
