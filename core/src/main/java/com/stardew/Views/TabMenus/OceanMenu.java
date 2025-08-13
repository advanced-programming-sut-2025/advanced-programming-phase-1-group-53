package com.stardew.Views.TabMenus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.stardew.Controllers.GameMenuController;
import com.stardew.Enums.Season;
import com.stardew.Enums.TileKind;
import com.stardew.Main;
import com.stardew.Models.Game.App;
import com.stardew.Models.Game.Player;
import com.stardew.Models.GameMap;
import com.stardew.Models.Items.Fish;
import com.stardew.Models.Position;
import com.stardew.Models.Tile;
import com.stardew.Network.Client.ClientApp;
import com.stardew.Network.Common.Packet.ClientPacket.KeyboardPackets.TouchDownPacket;
import com.stardew.Views.GameMenu;
import com.stardew.Views.Tab;

import java.util.ArrayList;
import java.util.Random;

public class OceanMenu extends Tab {
    private static final float SCREEN_WIDTH = Gdx.graphics.getWidth();
    private static final float SCREEN_HEIGHT = Gdx.graphics.getHeight();
    private static final int TILE_PRINT_SIZE = 20;
    private static final int NUM_OF_TILES =(int) (SCREEN_WIDTH/TILE_PRINT_SIZE);
    private static OceanMenu oceanMenu = null;
    private ArrayList<ArrayList<Tile>> tiles = new ArrayList<>();
    private ArrayList<Sprite> sprites = new ArrayList<>();
    private Player player;
    private ArrayList<Fish> fishes = new ArrayList<>();
    private float playerX;
    private float playerY;
    private boolean isRodOpen = false;
    private boolean takeRodUp = false;
    private float ballX;
    private float ballY;
    private Texture ROD_TEXTURE = new Texture("Tools/Fishing_Pole/Bamboo_Pole.png");
    private final Texture ROD_BALL = new Texture("Rod_Ball.png");
    private boolean isChanged = true;

    public boolean isMoveAllowed(float x, float y, boolean isNotFish){
        if(x <=0 || y<= 0)
            return false;
        if(tiles.get((int) (x/TILE_PRINT_SIZE)).get((int)y/TILE_PRINT_SIZE).getTileKind().equals(TileKind.shore)||
        tiles.get((int) (x/TILE_PRINT_SIZE)).get((int)y/TILE_PRINT_SIZE).getTileKind().equals(TileKind.sand)){
            return isNotFish;
        }
        else if(tiles.get((int) (x/TILE_PRINT_SIZE)+1).get((int)y/TILE_PRINT_SIZE).getTileKind().equals(TileKind.shore)||
            tiles.get((int) (x/TILE_PRINT_SIZE)+1).get((int)y/TILE_PRINT_SIZE).getTileKind().equals(TileKind.sand)){
            return isNotFish;
        }
        else if(tiles.get((int) (x/TILE_PRINT_SIZE)).get((int)y/TILE_PRINT_SIZE+1).getTileKind().equals(TileKind.shore)||
            tiles.get((int) (x/TILE_PRINT_SIZE)).get((int)y/TILE_PRINT_SIZE+1).getTileKind().equals(TileKind.sand)){
            return isNotFish;
        }
        return !isNotFish;
    }

    private OceanMenu(){
        ArrayList<Fish> fish = new ArrayList<>();
        if(App.getGame().dateAndTime.getSeason().equals(Season.SPRING))
            fish = Fish.SpringFishes;
        if(App.getGame().dateAndTime.getSeason().equals(Season.SUMMER))
            fish = Fish.SummerFishes;
        if(App.getGame().dateAndTime.getSeason().equals(Season.FALL))
            fish = Fish.FallFishes;
        if(App.getGame().dateAndTime.getSeason().equals(Season.WINTER))
            fish = Fish.WinterFishes;
        Random random = new Random();
        for(int i = 0; i<20; i++){
            fishes.add(fish.get(random.nextInt(4)).clone());
        }
        for(int i = 0; i<NUM_OF_TILES; i++){
            tiles.add(new ArrayList<>());
            int waterTilesEnd = 0;
            int shoreEnd = 0;
            int lightEnd = 0;
            int veryLightEnd = 0;
            if(i<NUM_OF_TILES/6)
                waterTilesEnd = NUM_OF_TILES * 3 /5;
            else if(i == NUM_OF_TILES/6){
                lightEnd =NUM_OF_TILES*3/5;
                waterTilesEnd = NUM_OF_TILES/5;
            }
            else if(i == NUM_OF_TILES/6+1){
                veryLightEnd =NUM_OF_TILES*3/5;
                waterTilesEnd = NUM_OF_TILES/5;
            }
            else if(i < 2*NUM_OF_TILES/6){
                shoreEnd =NUM_OF_TILES*3/5;
                waterTilesEnd = NUM_OF_TILES/5;
            }
            else if(i == 2*NUM_OF_TILES/6+1){
                lightEnd =NUM_OF_TILES*3/5;
                waterTilesEnd = NUM_OF_TILES/5;
            }
            else if(i == 2*NUM_OF_TILES/6){
                veryLightEnd =NUM_OF_TILES*3/5;
                waterTilesEnd = NUM_OF_TILES/5;
            }
            else if(i < 3*NUM_OF_TILES/6){
                waterTilesEnd = NUM_OF_TILES*3/5;
            }
            else if(i == 3*NUM_OF_TILES/6){
                lightEnd =NUM_OF_TILES*3/5;
                waterTilesEnd = NUM_OF_TILES*2/5;
            }
            else if(i == 3*NUM_OF_TILES/6+1){
                veryLightEnd =NUM_OF_TILES*3/5 ;
                waterTilesEnd = NUM_OF_TILES*2/5;
            }
            else if(i < 4*NUM_OF_TILES/6){
                shoreEnd =NUM_OF_TILES*3/5;
                waterTilesEnd = NUM_OF_TILES*2/5;
            }
            else if(i < 5*NUM_OF_TILES/6){
                shoreEnd =NUM_OF_TILES*3/5;
                waterTilesEnd = NUM_OF_TILES*2/5;
            }
            else if(i == 5*NUM_OF_TILES/6+1){
                lightEnd =NUM_OF_TILES*3/5;
                waterTilesEnd = NUM_OF_TILES*2/5;
            }
            else if(i == 5*NUM_OF_TILES/6){
                veryLightEnd =NUM_OF_TILES*3/5;
                waterTilesEnd = NUM_OF_TILES*2/5;
            }
            else{
                waterTilesEnd = NUM_OF_TILES * 3 /5;
            }
            for(int j = 0; j<NUM_OF_TILES; j++){
                if(j < waterTilesEnd)
                    tiles.get(i).add(new Tile(new Position(i, j, TILE_PRINT_SIZE, TILE_PRINT_SIZE), TileKind.water));
                else if (j<waterTilesEnd+3)
                    tiles.get(i).add(new Tile(new Position(i, j, TILE_PRINT_SIZE, TILE_PRINT_SIZE), TileKind.lightWater));
                else if (j<lightEnd)
                    tiles.get(i).add(new Tile(new Position(i, j, TILE_PRINT_SIZE, TILE_PRINT_SIZE), TileKind.lightWater));
                else if (j<veryLightEnd)
                    tiles.get(i).add(new Tile(new Position(i, j, TILE_PRINT_SIZE, TILE_PRINT_SIZE), TileKind.veryLightWater));
                else if (j<waterTilesEnd+8)
                    tiles.get(i).add(new Tile(new Position(i, j, TILE_PRINT_SIZE, TILE_PRINT_SIZE), TileKind.veryLightWater));
                else if (j<shoreEnd)
                    tiles.get(i).add(new Tile(new Position(i, j, TILE_PRINT_SIZE, TILE_PRINT_SIZE), TileKind.shore));
                else
                    tiles.get(i).add(new Tile(new Position(i, j, TILE_PRINT_SIZE, TILE_PRINT_SIZE), TileKind.sand));
            }
        }
    }
    public static OceanMenu getInstance(){
        if(oceanMenu == null)
            oceanMenu = new OceanMenu();
        return oceanMenu;
    }

    public void update(float delta){
        if(takeRodUp){
            takeRodUp =false;
            boolean b1;
            boolean b2;
            for(Fish fish : fishes){
                b1 = GameMenuController.coordinateCollision(ballX- TILE_PRINT_SIZE, 2*TILE_PRINT_SIZE,
                    fish.getSprite().getX(), fish.getSprite().getWidth());
                b2 = GameMenuController.coordinateCollision(ballY - TILE_PRINT_SIZE, 2*TILE_PRINT_SIZE,
                    fish.getSprite().getY(), fish.getSprite().getHeight());
                if(b1&& b2){
                    MiniGameMenu.getInstance().setUpMiniGame(player, fish);
                    Main.main.setScreen(MiniGameMenu.getInstance());
                    return;
                }
            }
            System.out.println("\n\n\n\n");
        }

        for(Fish fish : fishes){
            fish.update(delta);
        }
    }

    @Override
    public void show(){
        super.show();
        Gdx.input.setInputProcessor(this);
    }


    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(0, 0, 0, 1); // RGB + Alpha
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update(delta);
        if(isChanged){
            sprites.clear();
            for(ArrayList<Tile> tt : tiles){
                for(Tile t : tt){
                    Sprite s = t.getSprite();
                    s.setSize(TILE_PRINT_SIZE, TILE_PRINT_SIZE);
                    s.setPosition(t.getPosition().getX()*TILE_PRINT_SIZE, t.getPosition().getY()*TILE_PRINT_SIZE);
                    sprites.add(s);
                }
            }
            Sprite ss = player.getSprite();
            ss.setPosition(ss.getWidth()*((float) TILE_PRINT_SIZE / GameMap.getTilePrintSize()),
                ss.getHeight()*((float) TILE_PRINT_SIZE / GameMap.getTilePrintSize()));
            ss.setPosition(playerX, playerY);
            sprites.add(ss);
            Sprite sss = new Sprite(ROD_TEXTURE);
            sss.setPosition(playerX+ss.getWidth(), playerY+ss.getHeight());
            sprites.add(sss);
            isChanged = false;
            if(isRodOpen){
                Sprite ssss =new Sprite(ROD_BALL);
                ssss.setPosition(ballX, ballY);
                ssss.setSize(40, 40);
                sprites.add(ssss);
            }
        }

        if (!batch.isDrawing()){
            batch.begin();
        }
        for(Sprite s : sprites){
            s.draw(batch);
        }
        for(Fish fish : fishes){
            fish.getSprite().draw(batch);
        }
        if (batch.isDrawing()) {
            batch.end();
        }
    }

    @Override
    public boolean keyDown(int keycode){

        if(keycode == Input.Keys.ESCAPE){
            Main.main.setScreen(GameMenu.getInstance());
            return true;
        }
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        ClientApp.getInstance().getConnectionThread().sendPacket(new TouchDownPacket(App.getMyPlayer(), screenX, screenY, pointer, button,
            OceanMenu.class));

        if(button == Input.Buttons.LEFT){
            takeRodUp = isRodOpen;
            isRodOpen = !isRodOpen;
            isChanged = true;
            if(isRodOpen){
                if(isMoveAllowed(screenX, SCREEN_HEIGHT-screenY, false)){
                    ballX = screenX;
                    ballY = SCREEN_HEIGHT - screenY;
                }
            }
            return true;
        }
        if(button == Input.Buttons.RIGHT){
            if(isMoveAllowed(screenX, SCREEN_HEIGHT-screenY, true)){
                isChanged = true;
                playerX = screenX;
                playerY = SCREEN_HEIGHT-screenY;
            }
            return true;
        }
        return false;
    }

    public void setPlayer(Player player){
        this.player = player;
        this.player.setDirection(0);
        this.player.setIndexOfSprite(0);
        playerX = SCREEN_WIDTH*2/3;
        playerY= SCREEN_HEIGHT*2/3;
    }

    public void setChanged(boolean isChanged){
        this.isChanged = isChanged;
    }

    public static float getTilePrintSize(){
        return TILE_PRINT_SIZE;
    }
}
