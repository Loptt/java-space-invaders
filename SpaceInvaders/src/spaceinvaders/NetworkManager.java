/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author charles
 */
public class NetworkManager implements Runnable {
    private DatagramSocket socket;
    private DatagramPacket packet;
    private InetAddress address;
    private int port;
    
    private GsonBuilder builder;
    private Gson gson;
    
    private boolean server;
    
    private NetworkData receivedData;
    
    public NetworkManager(boolean isServer) {
        builder = new GsonBuilder();
        //builder.setPrettyPrinting();
        gson = builder.create();
        server = isServer;
        port = 0;
    }
    
    public void initClient(String address, int port) {
        if (server) {
            //Not valid if instance is not a client
            return;
        }
        
        try {
            this.address = InetAddress.getByName(address);
            this.port = port;
            socket = new DatagramSocket();
        } catch(UnknownHostException | SocketException e) {
            System.out.println(e);
        }
    }
    
    public void initServer() {
        try {  
            socket = new DatagramSocket(6000);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
    
    /**
     *
     * @param data
     */
    public void sendData(NetworkData data) {
        if (port == 0) {
            //No address specified
            //beep beep bop
            return;
        }
        
        String message = gson.toJson(data, NetworkData.class);
        packet = new DatagramPacket(message.getBytes(), message.getBytes().length, address, port);
        
        try { 
            socket.send(packet);
        } catch (IOException ex) {
            Logger.getLogger(NetworkManager.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void receiveData() {
        try {
            //If port is 0, address and port have not been specified
            //So define them
            
            byte[] buff = new byte[/*(int) ObjectSizeFetcher.getObjectSize(receivedData)*/ 256];
            packet = new DatagramPacket(buff, buff.length);
            socket.receive(packet);
            
            if (port == 0) {
                port = packet.getPort();
                address = packet.getAddress();
            }
            
            String message = new String(buff).trim();
            System.out.println(message);
            receivedData = gson.fromJson(message, NetworkData.class);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public void run() {
        receiveData();
    }

    public NetworkData getReceivedData() {
        return receivedData;
    }
}
