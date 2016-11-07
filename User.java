public class User{
  private String type;
  private String name;
  private String password;

  public User(String type, String name, String password){
    this.type = type;
    this.name = name;
    this.password = password;
  }

  public String getType(){
    return type;
  }

  public String getName(){
    return name;
  }

  public String getPassword(){
    return password;
  }


  public boolean equals(User inputUser){
    return ((inputUser.getType().equalsIgnoreCase(type)) &&
    (inputUser.getName().equalsIgnoreCase(name)) && (inputUser.getPassword().equals(password)));
  }
}
