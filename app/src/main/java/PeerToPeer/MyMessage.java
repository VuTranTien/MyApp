package PeerToPeer;

public class MyMessage {
    private String Content;
    private String Receiver;
    private String Sender;

    public MyMessage(String content, String receiver, String sender) {
        Content = content;
        Receiver = receiver;
        Sender = sender;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }


    public String getReceiver() {
        return Receiver;
    }

    public void setReceiver(String receiver) {
        Receiver = receiver;
    }

    public String getSender() {
        return Sender;
    }

    public void setSender(String sender) {
        Sender = sender;
    }
}
