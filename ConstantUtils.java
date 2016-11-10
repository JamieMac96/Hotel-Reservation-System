/**
 *The class ConstantUtils has the responsibility of storing all the URIs of the files in use by the program
 *as well as some constants that are used more than once in the progrma.
 *
 *@author Jamie Mac Manus
 *@version 1.0
 *@since 2016-11-10
 */
public class ConstantUtils{
  //private String directory;
  public final String CANCELLATIONS_FILE = "resources/Cancellations.csv";
  public final String HOTELS_FILE = "resources/l4Hotels.csv";
  public final String RESERVATIONS_FILE = "resources/Reservations.csv";
  public final String STAYS_FILE = "resources/Stays.csv";
  public final String USERS_FILE = "resources/SystemUsers.csv";
  public final int EXITVAL_CUSTOMER = 3;
  public final int EXITVAL_DESKADMIN = 5;
  public final int EXITVAL_SUPERVISOR = 8;
}
