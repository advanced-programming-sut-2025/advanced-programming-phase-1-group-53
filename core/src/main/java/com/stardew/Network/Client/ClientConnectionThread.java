package com.stardew.Network.Client;

import com.stardew.Network.Common.ConnectionThread;
import com.stardew.Network.Common.Packet.LoginPacket;
import com.stardew.Network.Common.Packet.WelcomePacket;
import com.stardew.Network.Common.Packet.Packet;
import com.stardew.Network.Common.Packet.PacketParser;

import java.io.IOException;
import java.net.Socket;

/**
 * نخ دائمی کلاینت برای ارسال/دریافت پکت به/از سرور.
 * initialHandshake() اینجا پکت لاگین را می‌فرستد و منتظر WelcomePacket می‌ماند.
 */
public class ClientConnectionThread extends ConnectionThread {
    private final String clientId;

    public ClientConnectionThread(Socket socket, String clientId) throws IOException {
        super(socket);
        this.clientId = clientId;
        setClientId(clientId);
    }

    @Override
    public boolean initialHandshake() {
        try {
            // ۱) ارسال LoginPacket
            sendPacket(new LoginPacket(clientId));

            // ۲) منتظر WelcomePacket بمان
            Packet pkt;
            while ((pkt = PacketParser.readPacket(inputStream)) != null) {
                if (pkt instanceof WelcomePacket welcome) {
                    System.out.println("Server says: " + welcome.getMessage());
                    return true;
                }
                // اگر پکت دیگری آمد می‌توانید enqueuePacket کنید یا نادیده بگیرید
            }
        } catch (IOException e) {
            System.err.println("Handshake error: " + e.getMessage());
        }
        return false;
    }

    @Override
    protected boolean handlePacket(Packet packet) {
        // اینجا پکت‌های بعدی را هندل می‌کنیم
        // (مثلاً نمایش چت، به‌روزرسانی موقعیت، و …)
        System.out.println("Received from server: " + packet.getClass().getSimpleName());
        return true;
    }
}
