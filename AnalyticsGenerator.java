public class AnalyticsGenerator{
  private HotelReader hReader;
  private ReservationReader rReader;
  private StayReader sReader;
  private CancellationReader cReader;

  public AnalyticsGenerator(HotelReader hReader, ReservationReader rReader, StayReader sReader, CancellationReader cReader){
    this.hReader = hReader;
    this.rReader = rReader;
    this.sReader = sReader;
    this.cReader = cReader;
  }

  public void getHotelOccupancyAnalytics(DateRange chosenRange){
    System.out.println("HOTEL OCCUPANCY ANALYTICS FROM: " + chosenRange.getStartDateString() + " TO: " + chosenRange.getEndDateString());
    System.out.println("********************************************************************************************************************");


    System.out.println("********************************************************************************************************************");
  }

  public void getRoomOccupancyAnalytics(DateRange chosenRange){
    System.out.println("ROOM OCCUPANCY ANALYTICS FROM: " + chosenRange.getStartDateString() + " TO: " + chosenRange.getEndDateString());
    System.out.println("********************************************************************************************************************");


    System.out.println("********************************************************************************************************************");
  }

  public void getFinancialAnalytics(DateRange chosenRange){
    System.out.println("FINANCIAL ANALYTICS FROM: " + chosenRange.getStartDateString() + " TO: " + chosenRange.getEndDateString());
    System.out.println("********************************************************************************************************************");


    System.out.println("********************************************************************************************************************");
  }
}
