import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *The class AnalyticsGenerator has the responsibility of
 *outputting analytics to csv fils.
 *
 *@author Jamie Mac Manus
 *@version 1.0
 *@since 2016-11-10
 */
public class AnalyticsGenerator{
  private HotelReader hReader;
  private ReservationReader rReader;
  private StayReader sReader;
  private CancellationReader cReader;


  /**
   * Constructor for AnalyticsGenerator
   * @param   hReader contains data on hotels.
   * @param   rReader contains data on reservations.
   * @param   sReader contains data on stays.
   * @param   cReader contains data on cancellations.
   */
  public AnalyticsGenerator(HotelReader hReader, ReservationReader rReader, StayReader sReader, CancellationReader cReader){
    this.hReader = hReader;
    this.rReader = rReader;
    this.sReader = sReader;
    this.cReader = cReader;
  }

  /**
   * This method creates and output file, generates hotel occupancy analytics and prints the analytics to the output file.
   * @param chosenRange the date range that the user has chosen to acquire analytics on.
   */
  public void outputHotelOccupancyAnalytics(DateRange chosenRange){
    String fileName = "./analytics/" + "hotel_occupancy/" + chosenRange.getStartDateString() + "_" + chosenRange.getEndDateString() + ".csv";
    File destinationFile = createAnalyticsFile(fileName);
    ArrayList<String> analytics = getHotelOccupancyAnalytics(chosenRange);
    printAnalyticsToFile(destinationFile, analytics);

    System.out.println("!Created analytics file: " + destinationFile.getName() + "!");
  }

  /**
   * This method creates and output file, generates room occupancy analytics and prints the analytics to the output file.
   * @param chosenRange the date range that the user has chosen to acquire analytics on.
   */
  public void outputRoomOccupancyAnalytics(DateRange chosenRange){
    String fileName = "./analytics/" + "room_occupancy/" + chosenRange.getStartDateString() + "_" + chosenRange.getEndDateString() + ".csv";
    File destinationFile = createAnalyticsFile(fileName);
    ArrayList<String> analytics = getRoomOccupancyAnalytics(chosenRange);
    printAnalyticsToFile(destinationFile, analytics);

    System.out.println("!Created analytics file: " + destinationFile.getName() + "!");
  }

  /**
   * This method creates and output file, generates financial analytics and prints the analytics to the output file.
   * @param chosenRange the date range that the user has chosen to acquire analytics on.
   */
  public void outputFinancialAnalytics(DateRange chosenRange){
    String fileName = "./analytics/" + "financial/" + chosenRange.getStartDateString() + "_" + chosenRange.getEndDateString() + ".csv";
    File destinationFile = createAnalyticsFile(fileName);
    ArrayList<String> analytics = getFinancialAnalytics(chosenRange);
    printAnalyticsToFile(destinationFile, analytics);

    System.out.println("!Created analytics file: " + destinationFile.getName() + "!");
  }

  private File createAnalyticsFile(String fileName){
    try{
      File destinationFile = new File(fileName);
      destinationFile.getParentFile().mkdirs();
      destinationFile.createNewFile();
      return destinationFile;
    }
    catch(IOException e){
      System.out.println("Error: Could open analytics file. ");
      e.printStackTrace();
    }
    return null;
  }

  private ArrayList<String> getHotelOccupancyAnalytics(DateRange chosenRange){
    ArrayList<String> analytics = new ArrayList<String>();
    ArrayList<Hotel> hotelInfo = hReader.getHotelInfo();
    int totalRooms, numberOfOccupiedRooms, numberOfDays = chosenRange.getLengthInDays();
    double percentageOccupied;
    double[] sumOfPercentages = new double[hotelInfo.size()];
    String pieceOfData = "";

    addFirstLineHotelOcc(hotelInfo, analytics);

    for(int i = 0; i <= numberOfDays; i++){
      Date currentDate = getCurrentDate(chosenRange, i);
      analytics.add(currentDate.getDateString());
      for(int j = 0; j < hotelInfo.size(); j++){
        totalRooms = hotelInfo.get(j).getTotalNumberOfRooms();
        numberOfOccupiedRooms = getNumberOfRoomsOccupied(hotelInfo.get(j), currentDate);
        percentageOccupied = Math.round((((double)numberOfOccupiedRooms / (double)totalRooms) * 100) * 100.0) / 100.0;
        sumOfPercentages[j] += percentageOccupied;
        analytics.set(i+1, analytics.get(i+1) + "," + percentageOccupied);
      }
    }

    analytics.add("Averages:");
    for(int i = 0 ; i < hotelInfo.size(); i++){
      analytics.set(analytics.size()-1, analytics.get(analytics.size()-1) + "," + (double)Math.round(sumOfPercentages[i]/numberOfDays * 100) / 100);
    }

    return analytics;
  }

  private ArrayList<String> getRoomOccupancyAnalytics(DateRange chosenRange){
    ArrayList<String> analytics = new ArrayList<String>();
    ArrayList<RoomType> roomTypes = hReader.getAllRoomTypes();
    int numberOfDays = chosenRange.getLengthInDays();
    int totalNumberOfRooms;
    int numberOfOccupiedRooms;
    double percentageOccupied;
    double[] sumOfPercentages = new double[roomTypes.size()];

    addFirstLineRoomOcc(roomTypes, analytics);

    for(int i = 0; i <= numberOfDays; i++){
      Date currentDate = getCurrentDate(chosenRange, i);
      analytics.add(currentDate.getDateString());
      for(int j = 0; j < roomTypes.size(); j++){
        totalNumberOfRooms = roomTypes.get(j).getNumberOfRooms();
        numberOfOccupiedRooms = getNumberOfRoomsOccupied(roomTypes.get(j), currentDate);
        percentageOccupied = Math.round((((double)numberOfOccupiedRooms / (double)totalNumberOfRooms) * 100) * 100.0) / 100.0;
        sumOfPercentages[j] += percentageOccupied;
        analytics.set(i+1, analytics.get(i+1) + "," + percentageOccupied);
      }
    }
    analytics.add("Averages:");
    for(int i = 0 ; i < roomTypes.size(); i++){
      analytics.set(analytics.size()-1, analytics.get(analytics.size()-1) + "," + (double)Math.round(sumOfPercentages[i]/numberOfDays * 100.0) / 100.0);
    }

    return analytics;
  }

  private ArrayList<String> getFinancialAnalytics(DateRange chosenRange){
    ArrayList<String> analytics = new ArrayList<String>();
    int numberOfDays = chosenRange.getLengthInDays();
    double stayincome;
    double expectedResIncome;
    double cancellationLoss;
    double [] totals = new double[3];
    analytics.add("date,reservation revenue,cancellation loss,stay revenue");

    for(int i = 0; i <= numberOfDays; i++){
      Date currentDate = getCurrentDate(chosenRange, i);
      totals[0] += expectedResIncome = rReader.incomeForDate(currentDate);
      totals[1] += cancellationLoss = cReader.costForDate(currentDate);
      totals[2] += stayincome = sReader.incomeForDate(currentDate);
      analytics.add(currentDate.getDateString() + "," + Math.round(expectedResIncome * 100.0)/100.0 + "," +
                    Math.round(cancellationLoss * 100.0) / 100.0 + "," +  Math.round(stayincome * 100.0) / 100.0);

    }

    analytics.add("totals:"+ Math.round(totals[0] * 100.0) / 100.0 + "," +  Math.round(totals[1] * 100.0) / 100.0 + "," +
                    Math.round(totals[2] * 100.0) / 100.0);

    return analytics;
  }


  private void printAnalyticsToFile(File destinationFile, ArrayList<String> analytics){
    try{
      PrintWriter analyticsPrinter = new PrintWriter(destinationFile);

      for(int i = 0; i < analytics.size(); i++){
        analyticsPrinter.println(analytics.get(i));
      }
      analyticsPrinter.close();
    }
    catch(IOException e){
      System.out.println("Error: failed to update: " + destinationFile.getName());
    }
  }


  private int getNumberOfRoomsOccupied(Hotel currentHotel, Date currentDate){
    ArrayList<Stay> stayDetails = sReader.getStayInfo();
    int numberOfRooms = 0;
    for(int i = 0; i < stayDetails.size(); i++){
      if(isInDateRange(currentDate, stayDetails.get(i).getCheckInDate(), stayDetails.get(i).getCheckOutDate())){
        if(currentHotel.hasRoomType(stayDetails.get(i).getFirstRoomType())){
          numberOfRooms += stayDetails.get(i).getNumberOfRooms();
        }
      }
    }
    return numberOfRooms;
  }

  private int getNumberOfRoomsOccupied(RoomType roomType, Date currentDate){
    ArrayList<Stay> stayDetails = sReader.getStayInfo();
    int numberOfRooms = 0;
    for(int i = 0; i < stayDetails.size(); i++){
      if(isInDateRange(currentDate, stayDetails.get(i).getCheckInDate(), stayDetails.get(i).getCheckOutDate())){
        numberOfRooms += stayDetails.get(i).getNumberOfRoomsOfType(roomType.getType());
      }
    }
    return numberOfRooms;
  }

  private boolean isInDateRange(Date currentDate, Date checkinDate, Date checkoutDate){
    return currentDate.isAfterOrEqual(checkinDate) && currentDate.isBeforeOrEqual(checkoutDate);
  }




  private void addFirstLineHotelOcc(ArrayList<Hotel> hotelInfo, ArrayList<String> analytics){
    analytics.add("date,");
    for(int i = 0 ; i < hotelInfo.size(); i++){
      if(i == hotelInfo.size() - 1){
        analytics.set(0, analytics.get(0) + hotelInfo.get(i).getHotelType() + " %occupancy");
      }
      else{
        analytics.set(0, analytics.get(0) + hotelInfo.get(i).getHotelType() + " %occupancy,");
      }
    }
  }

  private void addFirstLineRoomOcc(ArrayList<RoomType> roomTypes, ArrayList<String> analytics){
    analytics.add("date,");
    for(int i = 0 ; i < roomTypes.size(); i++){
      if(i == roomTypes.size() - 1){
        analytics.set(0, analytics.get(0) + roomTypes.get(i).getType() + " %occupancy");
      }
      else{
        analytics.set(0, analytics.get(0) + roomTypes.get(i).getType() + " %occupancy,");
      }
    }
  }


private Date getCurrentDate(DateRange chosenRange, int i){
  Date currentDate = new Date(chosenRange.getStartDate().getDay(), chosenRange.getStartDate().getMonth(), chosenRange.getStartDate().getYear());
  currentDate.incrementDays(i);
  return currentDate;
}

}
