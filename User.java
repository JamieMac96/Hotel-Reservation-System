
/**
 *The class User has the responsibility of defining and storing users.
 *
 *@author Jamie Mac Manus
 *@version 1.0
 *@since 2016-11-10
 */
public class User{
  private String type;
  private String name;
  private String password;

  /**
   * Constructor to create a new user object.
   * @param   type The type of user(customer,desk-admin or supervisor).
   * @param   name The name of the user.
   * @param   password The password for the given user.
   */
  public User(String type, String name, String password){
    this.type = type;
    this.name = name;
    this.password = password;
  }

  /**
   * This method returns the type of user.
   * @return type The type of user.
   */
  public String getType(){
    return type;
  }

  /**
   * This method returns the name of the user.
   * @return name The name of the user.
   */
  public String getName(){
    return name;
  }

  /**
   * This method returns the password of the user.
   * @return password The user's password.
   */
  public String getPassword(){
    return password;
  }

  /**
   * This method compares two user objects.
   * @param  inputUser The user that we are comparing with.
   * @return The truth value of whether or not the two users are equal.
   */
  public boolean equals(User inputUser){
    return ((inputUser.getType().equalsIgnoreCase(type)) &&
    (inputUser.getName().equalsIgnoreCase(name)) && (inputUser.getPassword().equals(password)));
  }
}
