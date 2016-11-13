import java.util.Scanner;

/**
 *The class HotelReservationSystem has the responsibility of running the reservation system..
 *
 *@author Jamie Mac Manus
 *@version 1.0
 *@since 2016-11-10
 */
public class HotelReservationSystem{
  private ConstantUtils utils;
  private MenuPrinter menuPrinter;
  private InputReader userInputReader;
  private ReservationReader rReader;

  /**
   * Constructor for creating HotelReservationSystem objects.
   */
  public HotelReservationSystem(){
    utils = new ConstantUtils();
    menuPrinter = new MenuPrinter();
    userInputReader = new InputReader();
    rReader = new ReservationReader(utils.RESERVATIONS_FILE);
  }

  /**
   * This method runs the program by first allowing the user to log in.
   */
  public void run(){
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
    StayWriter stayWriter = new StayWriter(utils.STAYS_FILE);
    ReservationWriter rWriter = new ReservationWriter(utils.RESERVATIONS_FILE);
    CancellationWriter cancellationWriter = new CancellationWriter(utils.CANCELLATIONS_FILE);
    HotelReader hReader = new HotelReader(utils.HOTELS_FILE);
    StayReader sReader = new StayReader(utils.STAYS_FILE);
    CancellationReader cReader = new CancellationReader(utils.CANCELLATIONS_FILE);
    AnalyticsGenerator aGenerator = new AnalyticsGenerator(hReader,rReader, sReader, cReader);
    int choice = -1;
    printCorrectMenu(exitValue);
    choice = userInputReader.getValidUserMenuChoice(exitValue);
    while(choice != exitValue){
      if(choice == 1){
        rWriter.makeReservation(hReader);
        rReader.update();
      }
      else if(choice == 2){
        cancellationWriter.makeCancellation(rReader);
      }
      else if(choice == 3){
        stayWriter.checkInCustomer(rReader, sReader);
      }
      else if(choice == 4){
        stayWriter.checkOutCustomer(rReader, sReader);
      }
      else if(choice == 5){
        displayAnalytics(aGenerator);
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

  private void displayAnalytics(AnalyticsGenerator aGenerator){
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
    UserReader userReader = new UserReader(utils.USERS_FILE);

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
