package com.stardew.Messengers;

import com.stardew.Models.Game.App;
import com.stardew.Views.AppMenu;
import com.stardew.Views.STab;

import java.util.ArrayList;

public class Observer extends AppMenu {
    ArrayList<String> textMessages;
    private static Thread refreshThread;
    private static int lastIndex = 0;


    public static void startRefreshThread() {
        refreshThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(3000);
                    if (App.getMyPlayer() != null) {
                        for (int i = lastIndex -1; i < App.getMyPlayer().getMessageBoxes().get(0).getMessages().size() -1; i++) {
                            if (i < 0 || i >= App.getMyPlayer().getMessageBoxes().get(0).getMessages().size()) {
                                break;
                            }
                            lastIndex = App.getMyPlayer().getMessageBoxes().size();
                            checkMsg(App.getMyPlayer().getMessageBoxes().get(0).getMessages().get(i));
                        }

                    }
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
        refreshThread.setDaemon(true);
        refreshThread.start();
    }

    private static void checkMsg(String msg) {
        if (msg.contains("@" + App.getMyPlayer().getUsername())) {
            STab.createDialog(msg, "OK").show(stage);
        }
    }

    @Override
    public void check(String scanner) {

    }
}
