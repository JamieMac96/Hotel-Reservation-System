import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Calendar;


public class Date{
  private String date;
  private int [] daysPerMonth = {31,28,31,30,31,30,31,31,30,31,30,31};
  private GregorianCalendar myCalendar;

  public Date(String date){
    this.date = date;
    myCalendar = new GregorianCalendar();
    if(isValid()){
      if(isLeapYear()){
        daysPerMonth[1] = 29;
      }
    }
  }

  public Date(int dayOfMonth, int month, int year){
    date = dayOfMonth + "-" + month + "-" + year;
  }

  public String getDateString(){
    return date;
  }

  public int getDay(){
    return dateComponentToInteger(0);
  }

  public int getMonth(){
    return dateComponentToInteger(1);
  }

  public int getYear(){
    return dateComponentToInteger(2);
  }

  public int getDayOfWeekIndex(){
    int dayIndex;
    //months are represented from 0-11 in GregorianCalendar. Hence we decrement the return of getMonth()
    GregorianCalendar dateAsInstanceOfGregorianCalendar = new GregorianCalendar(getYear(), getMonth() - 1, getDay());
    dayIndex = dateAsInstanceOfGregorianCalendar.get(Calendar.DAY_OF_WEEK);

    dayIndex = convertToCorrectFormat(dayIndex);

    return dayIndex;
  }

  public static Date getCurrentDate(){
    GregorianCalendar staticCal = new GregorianCalendar();
    int currentDay = staticCal.get(Calendar.DAY_OF_MONTH);
    int currentMonth = (staticCal.get(Calendar.MONTH) + 1);
    int currentYear = staticCal.get(Calendar.YEAR);

    return new Date(currentDay, currentMonth, currentYear);
  }

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


  public boolean isLeapYear(){
   return isLeapYear(getYear());
 }

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

  public boolean isFutureDate(){
    return !isMonthsOld(0);
  }

  public boolean isMonthsOld(int numberOfMonths){
    boolean monthsOld = false;
    int currentDay = myCalendar.get(Calendar.DAY_OF_MONTH);
    int currentMonth = (myCalendar.get(Calendar.MONTH) + 1) - numberOfMonths;
    int currentYear = myCalendar.get(Calendar.YEAR);
    int dayOfMonth = getDay();
    int month = getMonth();
    int year = getYear();

    while(currentMonth < 1){
      currentYear --;
      currentMonth += 12;
    }

    if(currentYear > year){
      monthsOld = true;
    }
    else if(currentYear == year){
      if(currentMonth > month){
        monthsOld = true;
      }
      else if(currentMonth == month){
        if(currentDay >= dayOfMonth){
          monthsOld = true;
        }
      }
    }
    return monthsOld;
  }

  public boolean isDaysFromNow(int numberOfDays){
    //This method needs to be tested.
    boolean daysFromNow = false;
    int currentDay = myCalendar.get(Calendar.DAY_OF_MONTH) + numberOfDays;
    int currentMonth = (myCalendar.get(Calendar.MONTH) + 1);
    int currentYear = myCalendar.get(Calendar.YEAR);
    int dayOfMonth = getDay();
    int month = getMonth();
    int year = getYear();

    while(daysPerMonth[currentMonth] < currentDay){
      currentDay = currentDay % daysPerMonth[currentMonth];
      currentMonth++;
    }

    if(currentYear > year){
      daysFromNow = true;
    }
    else if(currentYear == year){
      if(currentMonth > month){
        daysFromNow = true;
      }
      else if(currentMonth == month){
        if(currentDay >= dayOfMonth){
          daysFromNow = true;
        }
      }
    }
    return daysFromNow;
  }

  public boolean isBeforeOrEqual(Date comparisonDate){
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

  public boolean isAfterOrEqual(Date comparisonDate){
    boolean afterOrEqual = false;
    int comparisonYear = comparisonDate.getYear();
    int comparisonMonth = comparisonDate.getMonth();
    int comparisonDay = comparisonDate.getDay();
    int year = getYear();
    int month = getMonth();
    int day = getDay();


    if(comparisonYear > year){
      afterOrEqual = true;
    }
    else if(comparisonYear == year){
      if(comparisonMonth > month){
        afterOrEqual = true;
      }
      else if(comparisonMonth == month){
        if(comparisonDay >= day){
          afterOrEqual = true;
        }
      }
    }

    return afterOrEqual;
  }

  public void incrementDays(int numberOfDays){
    int currentDay = getDay();
    int currentMonth = getMonth();
    int currentYear = getYear();

    while(daysPerMonth[currentMonth] < currentDay){
      currentDay = currentDay % daysPerMonth[currentMonth];
      currentMonth++;
    }

    if(currentMonth > 12){
      currentYear++;
      currentMonth -= 12;
    }

    date = currentDay + "-" + currentMonth + "-" + currentYear;
  }

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

}
