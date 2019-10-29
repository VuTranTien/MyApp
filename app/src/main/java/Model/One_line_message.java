package Model;

public class One_line_message  {
    private String ID;
    private String Name;
    private int Image;
    private String lastMessage;
    private Boolean status;

    public One_line_message(String ID, String name, int image, String lastMessage, Boolean status) {
        this.ID = ID;
        Name = name;
        Image = image;
        this.lastMessage = lastMessage;
        this.status = status;
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

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
