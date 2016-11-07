import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class UserReader{
  private String fileString;
  private ArrayList<User> userInfo;

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
