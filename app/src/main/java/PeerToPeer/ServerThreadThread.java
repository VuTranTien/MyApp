package PeerToPeer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import ClientServer.Server;

public class ServerThreadThread {
    private ServerThread serverThread;
    private Socket socket;
    private PrintWriter printWriter;

    public ServerThreadThread( Socket socket,ServerThread serverThread) {
        this.serverThread = serverThread;
        this.socket = socket;
    }

    public PrintWriter getPrintWriter() {
        return printWriter;
    }

    public void start() {
        try{
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((this.socket.getInputStream())));
            this.printWriter = new PrintWriter(socket.getOutputStream(),true);
            while(true){
                serverThread.getServerThreadThreads().remove(this);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
