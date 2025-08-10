package com.stardew.Views.TabMenus;

import com.badlogic.gdx.utils.ScreenUtils;
import com.stardew.Main;
import com.stardew.Views.GameMenu;
import com.stardew.Views.Tab;

public class TimeCheatMenu extends Tab {
    private static boolean isCheatActivate = false;
    private static float cheatHours = 0;


    @Override
    public void render(float delta){
        ScreenUtils.clear(0, 0, 0, 1);
        if(isCheatActivate){
            GameMenu.getInstance().getController().updateGame(48*delta, true);
            cheatHours -= 4*delta;
            if(cheatHours <= 0){
                cheatHours = 0;
                isCheatActivate = false;
                Main.main.setScreen(CheatMenu.getInstance());
            }
        }
    }

    public static boolean isIsCheatActivate() {
        return isCheatActivate;
    }

    public static void setIsCheatActivate(boolean isCheatActivate) {
        TimeCheatMenu.isCheatActivate = isCheatActivate;
    }

    public static float getCheatHours() {
        return cheatHours;
    }

    public static void setCheatHours(float cheatHours) {
        TimeCheatMenu.cheatHours = cheatHours;
    }
}
