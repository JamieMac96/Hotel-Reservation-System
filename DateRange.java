public class DateRange{
  private Date startDate;
  private Date endDate;

  public DateRange(Date startDate, Date endDate){
    this.startDate = startDate;
    this.endDate = endDate;
  }

  public String getStartDateString(){
    return startDate.getDateString();
  }

  public String getEndDateString(){
    return endDate.getDateString();
  }

  public Date getStartDate(){
    return startDate;
  }

  public Date getEndDate(){
    return endDate;
  }

  public boolean isValidRange(){
    return (startDate.isBeforeOrEqual(endDate) && !endDate.isFutureDate());
  }

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
