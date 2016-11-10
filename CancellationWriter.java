import java.io.Writer;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 *The class CancellationWriter has the responsibility of allowing the user to make a cancellation
 *and then writing that cancellation to file.
 *
 *@author Jamie Mac Manus
 *@version 1.0
 *@since 2016-11-10
 */
public class CancellationWriter{
  private String fileString;
  private Cancellation userInputCancellation;


  /**
   * Constructor for creating a new CancellationWriter.
   * @param  fileString The URI for the Cancellation.csv file.
   */
  public CancellationWriter(String fileString){
    this.fileString = fileString;
  }

  /**
   * This method creates a new cancellation and prints it to file.
   * @param rReader the ReservationReader that contains all the info on current reservations in the system.
   */
  public void makeCancellation(ReservationReader rReader){
    InputReader iReader = new InputReader();
    userInputCancellation = iReader.readInValidCancellation(rReader);
    printCancellationToFile();
  }

  private void printCancellationToFile(){
    try{
      Writer output = new BufferedWriter(new FileWriter(fileString, true));
      output.append(userInputCancellation.toString() + "\n");
      output.close();
    }
    catch(IOException e){
      System.out.println("Error could not open BufferedWriter.");
      e.printStackTrace();
    }
  }
}
