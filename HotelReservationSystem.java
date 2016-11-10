import java.util.Scanner;

/**********************************************
 *Notes:
 *-we can read the names of  the files in resources and user that to
 * create the readers/writers instead of manually entering them.
 *-reservations are removed from the system after a month presuming they have been processed
 * ie converted to a cancellation or a stay. This means that stays and cancellations will need
 * to contain all the information of a reservation.
 *
 *
 * -reservations that are already stays can be cancelled. and vice versa
*/
public class HotelReservationSystem{
  private ConstantUtils utils;
  private UserReader userReader;
  private ReservationReader rReader;
  private HotelReader hReader;
  private InputReader userInputReader;
  private StayReader sReader;
  private CancellationReader cReader;
  private ReservationWriter reservationWriter;
  private StayWriter stayWriter;
  private MenuPrinter menuPrinter;
  private AnalyticsGenerator aGenerator;
  private final int EXITVAL_CUSTOMER = 3;
  private final int EXITVAL_DESKADMIN = 5;
  private final int EXITVAL_SUPERVISOR = 8;

  //private AnalyticsGenerator aGenerator;

  public HotelReservationSystem(){
    utils = new ConstantUtils();
    userReader = new UserReader(utils.USERS_FILE);
    rReader = new ReservationReader(utils.RESERVATIONS_FILE);
    hReader = new HotelReader(utils.HOTELS_FILE);
    userInputReader = new InputReader();
    sReader = new StayReader(utils.STAYS_FILE);
    cReader = new CancellationReader(utils.CANCELLATIONS_FILE);
    reservationWriter = new ReservationWriter(utils.RESERVATIONS_FILE);
    stayWriter = new StayWriter(utils.STAYS_FILE);
    menuPrinter = new MenuPrinter();
    aGenerator = new AnalyticsGenerator(hReader,rReader, sReader, cReader);
  }

  public void run(){
    loginMenuProcess();
  }

  private void loginMenuProcess(){
    int choice;
    String [] userTypes = {"c", "da", "s"};

    menuPrinter.printLoginMenu();
    choice = userInputReader.getValidLoginChoice();

    while(choice != 4){
      loginUser(userTypes[choice - 1]);
      if(choice == 1){
        subMenuLoopProcess(utils.EXITVAL_CUSTOMER);
      }
      else if(choice == 2){
        subMenuLoopProcess(utils.EXITVAL_DESKADMIN);
      }
      else if(choice == 3){
        subMenuLoopProcess(utils.EXITVAL_SUPERVISOR);
      }
      menuPrinter.printLoginMenu();
      choice = userInputReader.getValidLoginChoice();
    }
    System.out.println("\n*********   LOGGING OFF...           ");
  }

  private void subMenuLoopProcess(int exitValue){
    /*Note: choice will already have been validated so there is no chance that
      a user will be able to execute an action that are not allowed to execute*/
    int choice = -1;
    printCorrectMenu(exitValue);
    choice = userInputReader.getValidUserMenuChoice(exitValue);
    while(choice != exitValue){
      if(choice == 1){
        makeReservation();
      }
      else if(choice == 2){
        makeCancellation();
      }
      else if(choice == 3){
        checkInCustomer();
      }
      else if(choice == 4){
        checkOutCustomer();
      }
      else if(choice == 5){
        displayAnalytics();
      }
      else if(choice == 6){
        rReader.applyDiscount();
      }
      else if(choice == 7){
        rReader.removeExpiredReservations();
      }
      printCorrectMenu(exitValue);
      choice = userInputReader.getValidUserMenuChoice(exitValue);
    }
  }

  private void makeReservation(){
    reservationWriter.makeReservation(hReader);
    rReader.update();
  }

  private void makeCancellation(){
    CancellationWriter cancellationWriter = new CancellationWriter("resources/Cancellations.csv");
    cancellationWriter.makeCancellation(rReader);
    rReader.printUpdatedReservationsToFile();
  }

  private void checkInCustomer(){
    stayWriter.checkInCustomer(rReader, sReader);
  }

  private void checkOutCustomer(){
    stayWriter.checkOutCustomer(rReader, sReader);
  }

  private void displayAnalytics(){
    int choice = -1;
    menuPrinter.printAnalyticsMenu();
    choice = userInputReader.getValidAnalyticsChoice();
    while(choice != 4){
      DateRange chosenRange = userInputReader.getValidDateRange();//we are allowing the user to enter a range regardless of choice.
      if(choice == 1){
        aGenerator.outputHotelOccupancyAnalytics(chosenRange);
      }
      else if(choice == 2){
        aGenerator.outputRoomOccupancyAnalytics(chosenRange);
      }
      else if(choice == 3){
        aGenerator.outputFinancialAnalytics(chosenRange);
      }
      menuPrinter.printAnalyticsMenu();
      choice = userInputReader.getValidAnalyticsChoice();
    }
  }

  private void loginUser(String userType){
    User enteredUser = new User(userType, userInputReader.readUsername(), userInputReader.readPassword());

    while(!userReader.userExists(enteredUser)){
      System.out.println("Error: Invalid username or password.");
      enteredUser = new User(userType, userInputReader.readUsername(), userInputReader.readPassword());
    }
  }

  private void printCorrectMenu(int exitValue){
    if(exitValue == utils.EXITVAL_CUSTOMER){
      menuPrinter.printCustomerMenu();
    }
    else if(exitValue == utils.EXITVAL_SUPERVISOR){
      menuPrinter.printSupervisorMenu();
    }
    else{
      menuPrinter.printDeskAdministratorMenu();
    }
  }
}
