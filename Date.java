import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Calendar;

/**
 *The class Date has the responsibility of storing a date.
 *
 *@author Jamie Mac Manus
 *@version 1.0
 *@since 2016-11-10
 */
public class Date{
  private String date;
  private int [] daysPerMonth = {31,28,31,30,31,30,31,31,30,31,30,31};
  private GregorianCalendar myCalendar;

  /**
   * Constructor for creating a new date.
   * @param  date the string representation of the date.
   */
  public Date(String date){
    this.date = date;
    myCalendar = new GregorianCalendar();
    if(isValid()){
      if(isLeapYear()){
        daysPerMonth[1] = 29;
      }
    }
  }

  /**
   * Constructor for creating a new date.
   * @param   dayOfMonth The day component of the date.
   * @param   month The month component of the date.
   * @param   year The year component of the date.
   */
  public Date(int dayOfMonth, int month, int year){
    this(dayOfMonth + "-" + month + "-" + year);
  }

  /**
   * This method returns the date as a string.
   * @return date The date represented as a string.
   */
  public String getDateString(){
    return date;
  }

  /**
   * This method rturns the day component of the date.
   * @return An integer representing the month.
   */
  public int getDay(){
    return dateComponentToInteger(0);
  }

  /**
   * This method returns the month component of the date.
   * @return An integer representing the month.
   */
  public int getMonth(){
    return dateComponentToInteger(1);
  }

  /**
   * This method returns the year component of the date.
   * @return An integer representing the year.
   */
  public int getYear(){
    return dateComponentToInteger(2);
  }

  /**
   * This method returns the day of the week from 0-6(mon-sun) of the current week.
   * @return dayIndex The index of the day of the week for the current date.
   */
  public int getDayOfWeekIndex(){
    int dayIndex;
    //months are represented from 0-11 in GregorianCalendar. Hence we decrement the return of getMonth()
    GregorianCalendar dateAsInstanceOfGregorianCalendar = new GregorianCalendar(getYear(), getMonth() - 1, getDay());
    dayIndex = dateAsInstanceOfGregorianCalendar.get(Calendar.DAY_OF_WEEK);

    dayIndex = convertToCorrectFormat(dayIndex);

    return dayIndex;
  }

  /**
   * This static method returns todays date as an object of type Date.
   * @return Todays date.
   */
  public static Date getCurrentDate(){
    GregorianCalendar staticCal = new GregorianCalendar();
    int currentDay = staticCal.get(Calendar.DAY_OF_MONTH);
    int currentMonth = (staticCal.get(Calendar.MONTH) + 1);
    int currentYear = staticCal.get(Calendar.YEAR);

    return new Date(currentDay, currentMonth, currentYear);
  }

  /**
   * This method checks if a date is valid.
   * @return valid A boolean stating whether or not the date is valid.
   */
  public boolean isValid(){
    boolean valid = false;
    String dateRegex = "\\d{1,2}-\\d{1,2}-\\d{4}";
    int [] daysPerMonth = {31,28,31,30,31,30,31,31,30,31,30,31};

    if(date.matches(dateRegex)){
      int dayOfMonth = getDay();
      int month = getMonth();
      if(month <= 12){
        if(daysPerMonth[month-1] >= dayOfMonth){
          valid = true;
        }
      }
    }
    return valid;
  }


  /**
   * This method checks if the year of the date object is a leap year.
   * @return A boolean representing whether or not it is a leap year.
   */
  public boolean isLeapYear(){
   return isLeapYear(getYear());
 }

 /**
  * This static method checks if a given year is a leap year.
  * @param  year The that we want to check.
  * @return leapYear A boolean representing whether or not it is a leap year.
  */
 public static boolean isLeapYear(int year){
   boolean leapYear = false;

   if(year % 4 == 0){
     leapYear = true;
     if(year % 100 == 0){
       leapYear = false;
       if(year % 400 == 0){
         leapYear = true;
       }
     }
   }
   return leapYear;
 }

 /**
  * This method returns whether or not the date object is a future date.
  * @return The truth value of whether or not the date is in the future.
  */
  public boolean isFutureDate(){
    return !isMonthsOld(0);
  }

  /**
   * This method returns whether or not the date is a certain number of months old.
   * @param  numberOfMonths The number of months.
   * @return  A boolean representing whether or not the date is x months old.
   */
  public boolean isMonthsOld(int numberOfMonths){
    boolean monthsOld = false;
    int currentDay = myCalendar.get(Calendar.DAY_OF_MONTH);
    int currentMonth = (myCalendar.get(Calendar.MONTH) + 1) - numberOfMonths;
    int currentYear = myCalendar.get(Calendar.YEAR);

    while(currentMonth < 1){
      currentYear --;
      currentMonth += 12;
    }

    Date establishedDate = new Date(currentDay, currentMonth, currentYear);

    return isBeforeOrEqualAlg(establishedDate);
  }

  /**
   * This method returns whether or not the date atleast x days away from now.
   * @param  numberOfDays The number of days, ie x.
   * @return The truth value of whether the date is atleast x number of days from now.
   */
  public boolean isDaysFromNow(int numberOfDays){
    //This method needs to be tested.
    boolean daysFromNow = false;
    int currentDay = myCalendar.get(Calendar.DAY_OF_MONTH) + numberOfDays;
    int currentMonth = (myCalendar.get(Calendar.MONTH) + 1);
    int currentYear = myCalendar.get(Calendar.YEAR);

    while(daysPerMonth[currentMonth] < currentDay){
      currentDay = currentDay - daysPerMonth[currentMonth];
      currentMonth++;
    }
    while(currentMonth > 12){
      currentYear ++;
      currentMonth -= 12;
    }

    Date establishedDate = new Date(currentDay, currentMonth, currentYear);
    if(establishedDate.equals(this)){
      return true;
    }
    else{
      return !isBeforeOrEqualAlg(establishedDate);
    }
  }

  /**
   * This method checks if the date object (this) is before or equal to the parameter date object.
   * @param  comparisonDate The date we are comparing with.
   * @return The truth value of whther or not the current date is before or equal to the comparisonDate.
   */
  public boolean isBeforeOrEqual(Date comparisonDate){
    return isBeforeOrEqualAlg(comparisonDate);
  }

  /**
  * This method checks if the date object (this) is after or equal to the parameter date object.
  * @param  comparisonDate The date we are comparing with.
  * @return The truth value of whther or not the current date is after or equal to the comparisonDate.
   */
  public boolean isAfterOrEqual(Date comparisonDate){
    if(comparisonDate.equals(this)){
      return true;
    }
    else{
      return !isBeforeOrEqualAlg(comparisonDate);
    }
  }

  /**
   * This method increments the current date by a given number of days.
   * @param numberOfDays The number of days we want to increment the date by.
   */
  public void incrementDays(int numberOfDays){
    int currentDay = getDay() + numberOfDays;
    int currentMonth = getMonth();
    int currentYear = getYear();

    while(daysPerMonth[currentMonth] < currentDay){
      currentDay = currentDay - daysPerMonth[currentMonth];
      currentMonth++;
    }

    if(currentMonth > 12){
      currentYear++;
      currentMonth -= 12;
    }

    date = currentDay + "-" + currentMonth + "-" + currentYear;
  }

  /**
   * This method returns the total number of days since the start of the current year.
   * @return An integer representing the number of days since the start of the year.
   */
  public int getDaysSinceStartOfYear(){
    int daysSinceStartOfYear = 0;

    for(int i = 0; i < getMonth(); i++){
      daysSinceStartOfYear += daysPerMonth[i];
    }
    daysSinceStartOfYear += getDay();
    return daysSinceStartOfYear;
  }

  private int dateComponentToInteger(int componentIndex){
    String [] dateSplit = date.split("-");
    return Integer.parseInt(dateSplit[componentIndex]);
  }

  private int convertToCorrectFormat(int dayIndex){
    /*GregorianCalendar has sunday as the first day of the week.
      However we want sunday to be the last day of the week.
      We also want our days of the week ranging from 0 to 6 rather
      than 1 to 7. Thus if dayIndex is 1 we set it to 6 and otherwise
      we subtract 2 from dayIndex.*/
    if(dayIndex == 1){
      dayIndex = 6;
    }
    else{
      dayIndex -= 2;
    }

    return dayIndex;
  }

  private boolean isBeforeOrEqualAlg(Date comparisonDate){
    boolean beforeOrEqual = false;
    int comparisonYear = comparisonDate.getYear();
    int comparisonMonth = comparisonDate.getMonth();
    int comparisonDay = comparisonDate.getDay();
    int year = getYear();
    int month = getMonth();
    int day = getDay();

    if(comparisonYear > year){
      beforeOrEqual = true;
    }
    else if(comparisonYear == year){
      if(comparisonMonth > month){
        beforeOrEqual = true;
      }
      else if(comparisonMonth == month){
        if(comparisonDay >= day){
          beforeOrEqual = true;
        }
      }
    }

    return beforeOrEqual;
  }

  private boolean equals(Date comparisonDate){
    int cYear = comparisonDate.getYear();
    int cMonth = comparisonDate.getMonth();
    int cDay = comparisonDate.getDay();
    int day = getDay();
    int month = getMonth();
    int year = getYear();

    return ((cYear == year) && (cMonth == month) && (cDay == day));
  }

}
