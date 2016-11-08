import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

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

  public void outputHotelOccupancyAnalytics(DateRange chosenRange){
    String fileName = "./analytics/" + "H.OCC_" + chosenRange.getStartDateString() + "_" + chosenRange.getEndDateString() + ".csv";
    File destinationFile = createAnalyticsFile(fileName);
    ArrayList<String> analytics = getHotelOccupancyAnalytics(chosenRange);
    printAnalyticsToFile(destinationFile, analytics);

    System.out.println("Created analytics file: " + destinationFile.getName());
  }

  public void outputRoomOccupancyAnalytics(DateRange chosenRange){
    String fileName = "./analytics/" + "R.OCC_" + chosenRange.getStartDateString() + "_" + chosenRange.getEndDateString() + ".csv";
    File destinationFile = createAnalyticsFile(fileName);

    System.out.println("Created analytics file: " + destinationFile.getName());
  }

  public void outputFinancialAnalytics(DateRange chosenRange){
    String fileName = "./analytics/" + "FIN_" + chosenRange.getStartDateString() + "_" + chosenRange.getEndDateString() + ".csv";
    File destinationFile = createAnalyticsFile(fileName);

    System.out.println("Created analytics file: " + destinationFile.getName());
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
    int numberOfDays = chosenRange.getLengthInDays();
    int totalRooms;
    int occupiedRooms;
    double percentageOccupied;
    String pieceOfData = "";

    for(int i = 0; i < numberOfDays; i++){
      Date currentDate = new Date(chosenRange.getStartDate().getDay() + i, chosenRange.getStartDate().getMonth(), chosenRange.getStartDate().getYear());
      analytics.add(currentDate.getDateString());
      for(int j = 0; j < hotelInfo.size(); i++){
        totalRooms = hotelInfo.get(i).getTotalNumberOfRooms();
        occupiedRooms = getNumberOfRoomsOccupied(hotelInfo.get(i), currentDate);
        percentageOccupied = ((double)occupiedRooms / (double)totalRooms) * 100;
        System.out.println("percentageOccupied: " + percentageOccupied);
        analytics.set(i, analytics.get(i) + "," + percentageOccupied);
      }
    }
    return analytics;
  }


  private void printAnalyticsToFile(File destinationFile, ArrayList<String> analytics){

  }


  private int getNumberOfRoomsOccupied(Hotel currentHotel, Date currentDate){
    ArrayList<Stay> stayDetails = sReader.getStayInfo();
    int numberOfRooms = 0;
    for(int i = 0; i < stayDetails.size(); i++){
      System.out.println("current date: " + currentDate.getDateString());
      System.out.println("checkin date: " + stayDetails.get(i).getCheckInDate().getDateString());
      if(currentDate.isAfterOrEqual(stayDetails.get(i).getCheckInDate()) && currentDate.isBeforeOrEqual(stayDetails.get(i).getCheckOutDate())){
        if(currentHotel.hasRoomType(stayDetails.get(i).getRoomType())){
          numberOfRooms += stayDetails.get(i).getNumberOfRooms();
        }
      }
    }
    return numberOfRooms;
  }















}
