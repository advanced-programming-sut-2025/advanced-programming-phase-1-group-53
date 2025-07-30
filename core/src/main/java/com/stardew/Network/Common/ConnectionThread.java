package com.stardew.Network.Common;

import com.stardew.Network.Common.Packet.Packet;
import com.stardew.Network.Common.Packet.PacketParser;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

abstract public class ConnectionThread extends Thread {
    protected final BufferedOutputStream outputStream;
    protected final BufferedInputStream inputStream;
    protected final BlockingQueue<Packet> receivedMessagesQueue;
    protected String otherSideIP;
    protected int otherSidePort;
    protected Socket socket;
    protected AtomicBoolean end;
    protected boolean initialized = false;

    public ConnectionThread(Socket socket) throws IOException {
        this.socket = socket;
        this.outputStream = new BufferedOutputStream(socket.getOutputStream());
        this.inputStream = new BufferedInputStream(socket.getInputStream());
        this.receivedMessagesQueue = new LinkedBlockingQueue<>();
        this.end = new AtomicBoolean(false);
    }

    abstract public boolean initialHandshake();

    abstract protected boolean handlePacket(Packet packet);

    public synchronized void sendPacket(Packet packet) {
        if (packet == null || end.get()) return;
        try {
            PacketParser.writePacket(outputStream, packet);
        } catch (IOException e) {
            System.err.println("Failed to send packet: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        initialized = false;
        if (!initialHandshake()) {
            System.err.println("Inital HandShake failed with remote device.");
            end();
            return;
        }

        initialized = true;
        while (!end.get()) {
            try {
                Packet packet = PacketParser.readPacket(inputStream);
                if (packet == null) continue;
                boolean handled = handlePacket(packet);
                if (!handled) try {
                    receivedMessagesQueue.put(packet);
                } catch (InterruptedException ignored) {}
            } catch (Exception e) {
                System.err.println("Error in connection thread: " + e.getMessage());
                break;
            }
        }

        end();
    }

    public void end() {
        end.set(true);
        try {
            inputStream.close();
        } catch (IOException ignored) {}
        try {
            outputStream.close();
        } catch (IOException ignored) {}
        try {
            socket.close();
        } catch (IOException ignored) {}
    }


    public String getOtherSideIP() {
        return otherSideIP;
    }

    public void setOtherSideIP(String otherSideIP) {
        this.otherSideIP = otherSideIP;
    }

    public int getOtherSidePort() {
        return otherSidePort;
    }

    public void setOtherSidePort(int otherSidePort) {
        this.otherSidePort = otherSidePort;
    }
}
