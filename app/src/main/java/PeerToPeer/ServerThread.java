package PeerToPeer;


import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashSet;
import java.util.Set;
import java.net.Socket;

public class ServerThread extends Thread{
    private ServerSocket serverSocket;
    private Set<ServerThreadThread> serverThreadThreads = new HashSet<ServerThreadThread>();
    public ServerThread (String portNum) throws IOException{
        serverSocket = new ServerSocket(Integer.valueOf(portNum));
    }
    public void run(){
        try{
            while (true){
                ServerThreadThread serverThreadThread = new ServerThreadThread(serverSocket.accept(),this);
                serverThreadThreads.add(serverThreadThread);
                serverThreadThread.start();
            }
        }catch (Exception e){
            
        }

    }

    public void sendMessage(final String message){
        try {//
            // serverThreadThreads.forEach(t-> t.getPrintWriter().println(message));
            for(ServerThreadThread stt: serverThreadThreads){
                stt.getPrintWriter().println(message);
            }

    }catch (Exception e){
            e.printStackTrace();

        }
    }

    public Set<ServerThreadThread> getServerThreadThreads() {
        return serverThreadThreads;
    }
}
