import java.util.Scanner;

public class InputReader{
  private Scanner sc;
  private InputValidator userInputValidator;

  public InputReader(){
    sc = new Scanner(System.in);
    userInputValidator = new InputValidator();
  }

  public int getValidLoginChoice(){
    return getInputInRange(1,4);
  }

  public int getValidUserMenuChoice(int exitValue){
    return getInputInRange(1, exitValue);
  }

  public int getValidAnalyticsChoice(){
    return getValidLoginChoice();
  }

  public String readUsername(){
    System.out.print("Enter your username: ");
    return sc.nextLine();
  }

  public String readPassword(){
    System.out.print("Enter your password: ");
    return sc.nextLine();
  }

  public Reservation readInValidReservation(){
    Reservation reservation;
    int reservationNumber;
    String reservationName;
    String reservationType;
    Date checkInDate;
    int numberOfNights;
    int numberOfRooms;
    Room[] rooms;
    double totalCost;
    double deposit;

    System.out.println("\n************* REQUESTING RESERVATION INFO *************");
    System.out.print("Enter the reservation number: ");
    reservationNumber = getValidNumber();
    System.out.print("Enter the reservation Name: ");
    reservationName = sc.nextLine();
    System.out.print("Enter the reservation type(S or AP): ");
    reservationType = getValidReservationType();
    System.out.print("Enter the check in date: ");
    checkInDate = getValidDate();
    System.out.print("Enter the number of nights: ");
    numberOfNights = getValidNumber();
    System.out.print("Enter the number of rooms: ");
    numberOfRooms = getValidNumber();
    System.out.print("Enter the deposit: ");
    deposit = getValidDouble();
    System.out.println("*******************************************************\n");
    rooms = new Room[numberOfRooms];
    readInRooms(rooms);

    reservation = new Reservation(reservationNumber, reservationName, reservationType,
                                            checkInDate, numberOfNights, numberOfRooms, deposit);
    reservation.setRooms(rooms);

    return reservation;
  }

  public Cancellation readInValidCancellation(ReservationReader rReader){
    Cancellation userInputCancellation;
    Reservation chosenRes;
    System.out.println("\n************************** REQUESTING CANCELLATION INFO **************************");
    System.out.print("Enter the reservation number of the reservation that you would like to cancel: ");
    chosenRes = getReservationFromUserInputReservationNumber(rReader);
    System.out.println("**********************************************************************************\n");

    chosenRes.setAsProcessed();
    userInputCancellation = new Cancellation(chosenRes);

    return userInputCancellation;
  }

  public Stay readInValidStay(ReservationReader rReader){
    Stay userInputStay;
    Reservation chosenRes;
    System.out.println("\n************************** REQUESTING CHECK-IN INFO **************************");
    System.out.print("Enter the reservation number of the reservation you are checking in for: ");
    chosenRes = getReservationFromUserInputReservationNumber(rReader);
    System.out.println("******************************************************************************\n");

    userInputStay = new Stay(chosenRes);

    return userInputStay;
  }

  public void printDiscountStartPrompt(){
    System.out.println("**************************  REQUESTING DISCOUNT INFO **************************");
  }

  public Reservation getReservationToBeDiscounted(ReservationReader rReader){
    System.out.print("Enter the reservation number you would like to discount: ");
    return getReservationFromUserInputReservationNumber(rReader);
  }

  public double getDiscountAmount(){
    System.out.print("Enter the amount you would like to discount this reservation: ");
    return getValidDouble();
  }

  public void printDiscountEndPrompt(){
    System.out.println("******************************************************************************\n");
  }

  public DateRange getValidDateRange(){
    System.out.println("**************************  REQUESTING DATE INFO **************************");

    System.out.print("Enter the start date: ");
    Date startDate = getValidDate();
    System.out.print("Enter the end date: ");
    Date endDate = getValidDate();
    DateRange requestedRange = new DateRange(startDate, endDate);

    while(!requestedRange.isValidRange()){
      System.out.println("Error. This is not a valid date range, try again: ");
      System.out.print("Enter the start date: ");
      startDate = getValidDate();
      System.out.print("Enter the end date: ");
      endDate = getValidDate();
      requestedRange = new DateRange(startDate, endDate);
    }

    System.out.println("**************************************************************************\n");

    return requestedRange;
  }

  private Date getValidDate(){
    Date newDate;
    newDate = new Date(sc.nextLine());
    while(!userInputValidator.isValidDate(newDate)){
      System.out.print("Error. This is not a valid date, try again: ");
      newDate = new Date(sc.nextLine());
    }
    return newDate;
  }


  private void readInRooms(Room [] rooms){
    int roomNum;
    String typeOfRoom;
    String occupancy;

    System.out.println("\n**************** REQUESTING ROOM INFO ****************");
    for(int i = 0; i < rooms.length; i++){
      System.out.print("Enter room number: ");
      roomNum = getValidNumber();
      System.out.print("Enter room type: ");
      typeOfRoom = sc.nextLine();
      System.out.print("Enter occupancy(adults + children): ");
      occupancy = getValidOccupancy();

      rooms[i] = new Room(roomNum, typeOfRoom, occupancy);

    }
    System.out.println("******************************************************\n");

  }

  private int getInputInRange(int minValueOfRange, int maxValueOfRange){
    String choice;

    choice = sc.nextLine();
    while(!userInputValidator.inputIsInRange(choice, minValueOfRange, maxValueOfRange)){
      System.out.print("Error invalid input. Enter a value in the range " + minValueOfRange + " to " + maxValueOfRange + ": ");
      choice = sc.nextLine();
    }
    return Integer.parseInt(choice);
  }

  private int getValidNumber(){
    String input;
    int validNum;
    input = sc.nextLine();
    while(!userInputValidator.inputIsInteger(input)){
      System.out.print("Error, input must be a number. Try again: ");
      input = sc.nextLine();
    }
    validNum = Integer.parseInt(input);
    return validNum;
  }

  private String getValidReservationType(){
    String input;
    input = sc.nextLine();
    while(!userInputValidator.isValidReservationType(input)){
      System.out.print("Error, invalid input. Enter S(standard) or AP(advance purchase): ");
      input = sc.nextLine();
    }
    return input;
  }

  private double getValidDouble(){
    String input;
    double validDub;
    input = sc.nextLine();
    while(!userInputValidator.inputIsDouble(input)){
      System.out.print("Error, input must be a number. Try again: ");
      input = sc.nextLine();
    }
    validDub = Double.parseDouble(input);
    return validDub;
  }

  private String getValidOccupancy(){
    String input;
    input = sc.nextLine();
    while(!userInputValidator.isValidOccupancy(input)){
      System.out.print("Error, input must be in format number+number. Try again: ");
      input = sc.nextLine();
    }
    return input;
  }

  private Reservation getReservationFromUserInputReservationNumber(ReservationReader rReader){
    Reservation chosenRes;

    int resNumber = getValidReservationNumber(rReader);
    chosenRes = rReader.findReservation(resNumber);

    return chosenRes;
  }

  private int getValidReservationNumber(ReservationReader rReader){
    int resNumber = getValidNumber();
    while(rReader.findReservation(resNumber) == null){
      System.out.print("Reservation does not exist. Try again: ");
      resNumber = getValidNumber();
    }
    return resNumber;
  }
}
