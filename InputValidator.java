/**
 *The class InputValidator has the responsibility of validating user input.
 *
 *@author Jamie Mac Manus
 *@version 1.0
 *@since 2016-11-10
 */

public class InputValidator{

  /**
   * Checks if a certain string is in a certain range.
   * @param   input the string we are checking.
   * @param   minValueOfRange the min of the range.
   * @param   maxValueOfRange the max of the range.
   * @return  whether or not the input is in  the desired range.
   */
  public boolean inputIsInRange(String input, int minValueOfRange, int maxValueOfRange){
    if(input.matches("\\d+")){
      int inputAsInt = Integer.parseInt(input);
      if(inputAsInt <= maxValueOfRange && inputAsInt >= minValueOfRange){
        return true;
      }
    }
    return false;
  }

  /**
   * This method checks if a string contains an integer.
   * @param  input the string we a checking.
   * @return  The truth value of whether or not the input is an integer.
   */
  public boolean inputIsInteger(String input){
    return input.matches("\\d+") || input.matches("[-\\d+]");
  }

  /**
   * This method checks if a reservation type is valid.
   * @param  input the String we are checking.
   * @return  The truth value of whether or not the reservation type is valid.
   */
  public boolean isValidReservationType(String input){
    return input.equalsIgnoreCase("s") || input.equalsIgnoreCase("ap");
  }

  /**
   * This method checks if a string input contains a double.
   * @param  input the string we are checking.
   * @return  The truth value of whether or not the string is a double.
   */
  public boolean inputIsDouble(String input){
    return (input.matches("\\d+") || input.matches("\\d+.\\d+"));
  }

  /**
   * This method checks if the a certain string is correctly represents an occupancy.
   * @param  input the string we are checking
   * @return  The truth value of whether or not the string is a valid occupancy.
   */
  public boolean isValidOccupancy(String input){
    return input.matches("[\\d][+][\\d]");
  }
}
