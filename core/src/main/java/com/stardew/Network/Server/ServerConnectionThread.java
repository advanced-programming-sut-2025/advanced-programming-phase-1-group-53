package com.stardew.Network.Server;

import com.stardew.Network.Common.ConnectionThread;
import com.stardew.Network.Common.Packet.*;

import java.io.IOException;
import java.net.Socket;

/**
 * نخ دائمی برای مدیریت ارتباط با یک کلاینت.
 * تمام پکت‌های دریافتی را می‌خواند، هندل می‌کند و در صورت نیاز پاسخ می‌دهد.
 */
public class ServerConnectionThread extends ConnectionThread {
    private final ServerApp serverApp = ServerApp.getInstance();

    public ServerConnectionThread(Socket socket) throws IOException {
        super(socket);
    }

    /**
     * در اینجا می‌توانیم یک handshake ساده انجام دهیم.
     * مثلاً ارسال یک Packet خاص برای تأیید اتصال.
     */
    @Override
    public boolean initialHandshake() {
        try {
            // مثال: ارسال یک LoginAckPacket یا پیام خوش‌آمدگویی
            Packet welcome = new WelcomePacket("SERVER", "Welcome, your ID is ", getClientId());
            sendPacket(welcome);
            return true;
        } catch (Exception e) {
            System.err.println("Handshake failed for client " + getClientId() + ": " + e.getMessage());
            return false;
        }
    }

    /**
     * هر پکت دریافتی از هم‌صف (یا مستقیم از سوکت) به اینجا می‌آید.
     * بسته به نوع، عملیات مختلف انجام می‌دهیم.
     */
    @Override
    protected boolean handlePacket(Packet packet) {
        // لاگ کردن
        System.out.println("Received packet from " + getClientId() + ": " + packet.getClass().getSimpleName());

        // مثال هندل چند نوع پکت:
        if (packet instanceof LoginPacket login) {
            // نباید اینجا باشه چون login قبلاً در handleInitialPacket هندل شد
            return true;
        }
        else if (packet instanceof MovePacket move) {
            // پخش حرکت بازیکن به همه‌ی کلاینت‌ها
            serverApp.broadcastExcept(this, move);
            return true;
        }
        else if (packet instanceof ChatPacket chat) {
            // ارسال پیام چت به همه
            serverApp.broadcast(chat);
            return true;
        }
        else {
            // اگر هیچ‌کدام، تحویل به سرور مرکزی
            return false;
        }
    }

    /**
     * در صورتی که بخواهیم در میانه‌ی اجرا پکتی از خودمان ارسال کنیم:
     */
    public void sendGameUpdate(Packet update) {
        sendPacket(update);
    }
}
