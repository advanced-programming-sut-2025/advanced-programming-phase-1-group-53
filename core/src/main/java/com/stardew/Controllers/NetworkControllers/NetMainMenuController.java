package com.stardew.Controllers.NetworkControllers;


import com.badlogic.gdx.Game;
import com.stardew.Models.Game.App;
import com.stardew.Views.NetworkMenus.NetProfileMenu;
import com.stardew.Views.ProfileMenu;

public class NetMainMenuController {
    public void openPersonalInfoScreen(Game main) {
        main.setScreen(new NetProfileMenu(main, App.getCurrentPlayer().getPersonalInfo().getName()));
    }
}
