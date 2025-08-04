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

/**
 * Represents a persistent bi-directional connection running in its own thread.
 */
public abstract class ConnectionThread implements Runnable {
    protected final BufferedOutputStream outputStream;
    protected final BufferedInputStream inputStream;
    protected final BlockingQueue<Packet> receivedMessagesQueue;
    protected final AtomicBoolean endFlag;
    protected Socket socket;
    protected boolean initialized = false;

    private String clientId;

    public ConnectionThread(Socket socket) throws IOException {
        this.socket = socket;
        this.outputStream = new BufferedOutputStream(socket.getOutputStream());
        this.inputStream = new BufferedInputStream(socket.getInputStream());
        this.receivedMessagesQueue = new LinkedBlockingQueue<>();
        this.endFlag = new AtomicBoolean(false);
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientId() {
        return clientId;
    }

    /**
     * Enqueue an external packet for processing in this thread.
     */
    public void enqueuePacket(Packet packet) {
        if (!endFlag.get()) {
            receivedMessagesQueue.offer(packet);
        }
    }

    abstract public boolean initialHandshake();
    abstract protected boolean handlePacket(Packet packet);

    public synchronized void sendPacket(Packet packet) {
        if (packet == null || endFlag.get()) return;
        try {
            PacketParser.writePacket(outputStream, packet);
        } catch (IOException e) {
            System.err.println("Failed to send packet: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        // perform handshake if needed
        if (!initialHandshake()) {
            System.err.println("Initial handshake failed for " + clientId);
            end();
            return;
        }
        initialized = true;

        // main loop: process incoming packets
        while (!endFlag.get()) {
            try {
                Packet packet = receivedMessagesQueue.poll();
                if (packet == null) {
                    packet = PacketParser.readPacket(inputStream);
                }
                if (packet == null) continue;

                boolean handled = handlePacket(packet);
                if (!handled) {
                    // other processing...
                }
            } catch (Exception e) {
                System.err.println("Error in connection thread [" + clientId + "]: " + e.getMessage());
                break;
            }
        }
        end();
    }

    public void end() {
        endFlag.set(true);
        try { inputStream.close(); } catch (IOException ignored) {}
        try { outputStream.close(); } catch (IOException ignored) {}
        try { socket.close(); } catch (IOException ignored) {}
    }
}
