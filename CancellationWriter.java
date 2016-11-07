import java.io.Writer;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class CancellationWriter{
  private String fileString;
  private Cancellation userInputCancellation;


  public CancellationWriter(String fileString){
    this.fileString = fileString;
  }

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
