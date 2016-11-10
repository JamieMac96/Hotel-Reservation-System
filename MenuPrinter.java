
/**
 *The class MenuPrinter has the responsibility of printing menus to screen.
 *
 *@author Jamie Mac Manus
 *@version 1.0
 *@since 2016-11-10
 */
public class MenuPrinter{

  /**
   * Prints the login menu.
   */
  public void printLoginMenu(){
    String [] messages = {"\n**********  LOGIN MENU **********",
                                "1.Customer login",
                                "2.Desk administrator login",
                                "3.Supervisor login",
                                "4.Exit",
                                "*********************************",
                                "Please enter your choice: "};

    printMenu(messages);
  }

  /**
   * Prints the customers menu.
   */
  public void printCustomerMenu(){
    String [] messages =  {"\n**********  CUSTOMER MENU  **********",
                          "1.Make reservation",
                          "2.Make cancellation",
                          "3.Log out",
                          "*************************************",
                          "Please enter your choice: "};

    printMenu(messages);
  }

  /**
   * Prints the supervisors menu.
   */
  public void printSupervisorMenu(){
    String [] messages = {"\n**********  SUPERVISOR MENU  **********",
                          "1.Make reservation",
                          "2.Make cancellation",
                          "3.Check in customer",
                          "4.Check out customer",
                          "5.Get analytics",
                          "6.Apply discount",
                          "7.Discard expired reservations",
                          "8.Log out",
                          "***************************************",
                          "Please enter your choice: "};

    printMenu(messages);
  }

  /**
   * Prints the analytics menu.
   */
  public void printAnalyticsMenu(){
    String [] messages = {"\n********** ANALYTICS MENU **********",
                          "1.Hotel occupancy analytics",
                          "2.Room occupancy analytics",
                          "3.Financial analytics",
                          "4.Go back",
                          "************************************",
                          "Please enter your choice: "};
    printMenu(messages);
  }

  /**
   * Prints the desk administrators menu.
   */
  public void printDeskAdministratorMenu(){
    String [] messages =  {"\n**********  DESK ADMINISTRATOR MENU  **********",
                          "1.Make reservation",
                          "2.Make cancellation",
                          "3.Check in customer",
                          "4.Check out customer",
                          "5.Log out",
                          "***********************************************",
                          "Please enter your choice: "};
    printMenu(messages);
  }

  private void printMenu(String [] menuMessages){
    for(int i = 0; i < menuMessages.length; i++){
      if(i == menuMessages.length - 1){
        System.out.print(menuMessages[i]);
      }
      else{
        System.out.println(menuMessages[i]);
      }
    }
  }
}
