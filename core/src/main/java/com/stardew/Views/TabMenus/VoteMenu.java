package com.stardew.Views.TabMenus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.stardew.Main;
import com.stardew.Models.Game.App;
import com.stardew.Models.Game.Player;
import com.stardew.Models.MessageManager;
import com.stardew.Network.Client.ClientApp;
import com.stardew.Network.Common.Packet.ClientPacket.ElectionPackets.ElectionType;
import com.stardew.Network.Common.Packet.ClientPacket.ElectionPackets.StartVotingPacket;
import com.stardew.Views.GameMenu;
import com.stardew.Views.Tab;

import java.util.ArrayList;
import java.util.HashMap;

public class VoteMenu extends Tab {
    private static final float SCREEN_WIDTH = Gdx.graphics.getWidth();
    private static final float SCREEN_HEIGHT = Gdx.graphics.getHeight();
    private static final int START_X =(int) SCREEN_WIDTH/8;
    private static final int START_Y =(int) 286;
    private static final float ROW_WIDTH = SCREEN_WIDTH/2;
    private static final float ROW_HEIGHT = SCREEN_HEIGHT/16;
    private ArrayList<Sprite> sprites = new ArrayList<>();
    private Player player = null;

    public VoteMenu(){
        this.player = App.getMyPlayer();
    }

    @Override
    public void show(){
        super.show();
        Array<String> players = new Array<>();
        HashMap<String, Player> playerHashMap = new HashMap<>();

        for(Player player1 : App.getGame().players){
            if(!(player1== player)){
                players.add(player1.personalInfo.getName());
                playerHashMap.put(player1.personalInfo.getName(), player1);
            }
        }

        SelectBox selectBox = Tab.createSelectBox(players);
        selectBox.setPosition(SCREEN_WIDTH/2- 150, SCREEN_HEIGHT/2+200);
        selectBox.setSize(300, 80);
        stage.addActor(selectBox);

        TextButton back = Tab.createTextButton("election for kicking");
        back.setPosition(SCREEN_WIDTH/2-550, SCREEN_HEIGHT/2-100);
        back.setSize(400, 70);
        back.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
//                ClientApp.getInstance().getConnectionThread().sendPacket(new StartVotingPacket(App.getMyPlayer(),
//                     ElectionType.REMOVE_PLAYER,(String) selectBox.getSelected());
            }
        });
        stage.addActor(back);

        TextButton dis = Tab.createTextButton("election for terminating");
        dis.setPosition(SCREEN_WIDTH/2+150, SCREEN_HEIGHT/2 -100);
        dis.setSize(400, 70);
        dis.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
//                ClientApp.getInstance().getConnectionThread().sendPacket(new StartVotingPacket(App.getMyPlayer(),
//                     ElectionType.TERMINATE_GAME,null));
            }
        });
        stage.addActor(dis);
    }


    @Override
    public void render(float delta){
        ScreenUtils.clear(1, 1, 1, 1);
        if (!batch.isDrawing()) {
            batch.begin();
        }
        for(Sprite s : sprites){
            try{
                s.draw(batch);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

        if (batch.isDrawing()) {
            batch.end();
        }

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }
}
