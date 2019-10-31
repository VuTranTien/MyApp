package Model;

import java.io.Serializable;

public class One_line_message implements Serializable {
    private String Email;
    private String Name;
    private String Image;
    private String lastMessage;
    private Boolean status;
    private String UID;

    public One_line_message(String email, String name, String image, String lastMessage, Boolean status, String UID) {
        Email = email;
        Name = name;
        Image = image;
        this.lastMessage = lastMessage;
        this.status = status;
        this.UID = UID;
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

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
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

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }
}
