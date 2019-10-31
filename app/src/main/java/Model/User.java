package Model;

public class User {
    private String Email;
    private String Name;
    private String Pass;

    public User(String email, String name, String pass) {
        Email = email;
        Name = name;
        Pass = pass;
    }
    public User(){

    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        Pass = pass;
    }
}
