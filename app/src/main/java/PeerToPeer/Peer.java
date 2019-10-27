package PeerToPeer;

import java.io.IOException;

import ClientServer.Server;

public class Peer {
    static Server server;

    public static Server getServer() {
        return server;
    }

    public static void setServer(Server server) {
        Peer.server = server;
    }

    public  static void main(String[] args) throws IOException{
        ServerThread serverThread = new ServerThread(server.getPort());
        serverThread.start();


    }
}
