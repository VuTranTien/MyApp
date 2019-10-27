package ClientServer;

public class Server {
    private String HostName;
    private String Port;
    private String Avatar;
    private String TenHienThi;
    private String Email;

    public Server(String hostName, String port, String avatar, String tenHienThi, String email) {
        HostName = hostName;
        Port = port;
        Avatar = avatar;
        TenHienThi = tenHienThi;
        Email = email;
    }

    public String getHostName() {
        return HostName;
    }

    public void setHostName(String hostName) {
        HostName = hostName;
    }

    public String getPort() {
        return Port;
    }

    public void setPort(String port) {
        Port = port;
    }

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String avatar) {
        Avatar = avatar;
    }

    public String getTenHienThi() {
        return TenHienThi;
    }

    public void setTenHienThi(String tenHienThi) {
        TenHienThi = tenHienThi;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
    public String getText(){
        return "Server";
    }
}
