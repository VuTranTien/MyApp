package Model;

public class User {
    protected String ID;
    protected String Name;
    protected int Image;

    public User(String ID, String name, int image) {
        this.ID = ID;
        Name = name;
        Image = image;
    }
    public User(){

    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }
}
