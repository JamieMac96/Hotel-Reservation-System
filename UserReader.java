import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

/**
 *The class UserReader has the responsibility of reading .
 *
 *@author Jamie Mac Manus
 *@version 1.0
 *@since 2016-11-10
 */
public class UserReader{
  private String fileString;
  private ArrayList<User> userInfo;

  /**
   * Constructor for creating new UserReader objects.
   * @param  fileString The URI of the SystemUsers.csv file.
   */
  public UserReader(String fileString){
    fileString = fileString;
    readInFileData(fileString);
  }


  private void readInFileData(String fileString){
    userInfo = new ArrayList<User>();
    try{
     File userFile = new File(fileString);
     Scanner fileIn = new Scanner(userFile);

     String [] lineSplit;

     while(fileIn.hasNext()){
       lineSplit = fileIn.nextLine().split(",");
       userInfo.add(new User(lineSplit[0], lineSplit[1], lineSplit[2]));
     }
    }
    catch(IOException e){
     System.out.println("Error: failed to read from: " + fileString);
    }
  }

  public boolean userExists(User inputUser){
    for(int i = 0; i < userInfo.size(); i++){
      if(inputUser.equals(userInfo.get(i))){
        return true;
      }
    }
    return false;
  }
}
