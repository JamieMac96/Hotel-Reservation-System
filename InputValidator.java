public class InputValidator{

  public boolean inputIsInRange(String input, int minValueOfRange, int maxValueOfRange){
    if(input.matches("\\d+")){
      int inputAsInt = Integer.parseInt(input);
      if(inputAsInt <= maxValueOfRange && inputAsInt >= minValueOfRange){
        return true;
      }
    }
    return false;
  }

  public boolean inputIsInteger(String input){
    return input.matches("\\d+");
  }

  public boolean isValidReservationType(String input){
    return input.equalsIgnoreCase("s") || input.equalsIgnoreCase("ap");
  }

  public boolean isValidDate(Date newDate){
    return newDate.isValid();
  }

  public boolean inputIsDouble(String input){
    return (input.matches("\\d+") || input.matches("\\d+.\\d+"));
  }

  public boolean isValidOccupancy(String input){
    return input.matches("[\\d][+][\\d]");
  }
}
