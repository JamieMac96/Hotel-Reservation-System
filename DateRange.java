public class DateRange{
  private Date startDate;
  private Date endDate;

  public DateRange(Date startDate, Date endDate){
    this.startDate = startDate;
    this.endDate = endDate;
  }

  public String getStartDateString(){
    return startDate.getDate();
  }

  public String getEndDateString(){
    return endDate.getDate();
  }

  public boolean isValidRange(){
    return (startDate.isBeforeOrEqual(endDate) && !endDate.isFutureDate());
  }
}
