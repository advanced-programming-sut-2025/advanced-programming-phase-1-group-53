package com.stardew.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.utils.Array;
import com.stardew.Controllers.GameMenuController;
import com.stardew.Controllers.ShareController;
import com.stardew.Enums.GameMenuCommand;
import com.stardew.Enums.ItemType;
import com.stardew.Enums.TileKind;
import com.stardew.Main;
import com.stardew.Models.Energy;
import com.stardew.Models.Game.App;
import com.stardew.Models.GameMap;
import com.stardew.Models.Items.*;
import com.stardew.Models.Items.CraftAbleAndArtisan.Artisan;
import com.stardew.Models.MessageManager;
import com.stardew.Network.Client.ClientApp;
import com.stardew.Network.Common.Packet.ClientPacket.KeyboardPackets.*;
import com.stardew.Views.TabMenus.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class GameMenu extends AppMenu implements InputProcessor {
    private static float TOTAL_TIME_SPENT = 0;
    private static int SCREEN_WIDTH;
    private static int SCREEN_HEIGHT;
    private static GameMenu gameMenu = null;
    private SpriteBatch batch ;
    private Sprite sprite;
    private Stage stage;
    private float thunderAlpha = 0f;
    private boolean isThunderActive = false;
    private ShapeRenderer shapeRenderer;
    private boolean SHOW_TILE_DETAILS = false;
    private final GameMenuController controller = new GameMenuController();
    private float nightScreenAlpha = 0;
    private boolean isGettingDark = false;
    private boolean goingInHouse =false;
    private boolean isGettingLight = false;
    private boolean setToolToMouse = false;
    private float mouseY = 0;
    private float mouseX = 0;
    private boolean isGoingInCoop = false;
    private boolean showFullTiles = false;


    public GameMenuController getController() {
        return controller;
    }

    private GameMenu(){
        super();
    }

    public static GameMenu getInstance(){
        if(gameMenu == null)
            gameMenu = new GameMenu();
        return gameMenu;
    }

    public static void renewInstance(){
        gameMenu = null;
    }
    private String currentPlayerName = App.getMyPlayer().getPersonalInfo().getName();

    public GameMenu(Game main) {
        super(main);
    }

    @Override
    public void check(String scanner) {
        String input = scanner;
        Matcher matcher;

        if ((matcher = GameMenuCommand.showCurrentMenu.getMatcher(input)) != null) {
            ShareController.showCurrentMenu();
        }else if ((matcher = GameMenuCommand.setEnergy.getMatcher(input)) != null) {
            App.getCurrentPlayer().energy.setEnergy((Energy.getMaxEnergy()/100 )* Integer.parseInt(matcher.group("count")));
            System.out.println("energy set successfully");
        }
        else if ((matcher = GameMenuCommand.advanceTime.getMatcher(input)) != null) {
            try{
                controller.advanceTime(Integer.parseInt(matcher.group("time")));
            }
            catch (Exception e){
                e.printStackTrace();
            }
        } else if ((matcher = GameMenuCommand.advanceDate.getMatcher(input)) != null) {
            try{
                controller.advanceDate(Integer.parseInt(matcher.group("date")));
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }  else if ((matcher = GameMenuCommand.thor.getMatcher(input)) != null) {
            controller.thor(Integer.parseInt(matcher.group("x")), Integer.parseInt(matcher.group("y")));
        } else if ((matcher = GameMenuCommand.weatherSet.getMatcher(input)) != null) {
            controller.weatherSet(matcher.group("weather"));
        } else if ((matcher = GameMenuCommand.addItem.getMatcher(input)) != null) {
            controller.addItem(matcher.group("ItemName"), Integer.parseInt(matcher.group("count")));
        }  else if ((matcher = GameMenuCommand.setFreindship.getMatcher(input)) != null) {
            controller.setFriendship(matcher.group("animalName"), Integer.parseInt(matcher.group("value")));
        } else if ((matcher = GameMenuCommand.addDollars.getMatcher(input)) != null) {
            controller.addDollars(Integer.parseInt(matcher.group("count")));
            System.out.println("money added successfully");
        } else if ((matcher = GameMenuCommand.enterMenu.getMatcher(input)) != null) {
            ShareController.enterMenu(matcher.group("menu"));
        } else if ((matcher = GameMenuCommand.exitGame.getMatcher(input)) != null) {
            controller.exitGame();
        } else if ((matcher = GameMenuCommand.loadGame.getMatcher(input)) != null) {
            controller.loadGame(matcher.group("index"));
        } else if ((matcher = GameMenuCommand.selectMap.getMatcher(input)) != null) {
            controller.selectMap(matcher.group("mapNumber"));
        } else if ((matcher = GameMenuCommand.nextTurn.getMatcher(input)) != null) {
            controller.nextTurn();
        } else if ((matcher = GameMenuCommand.time.getMatcher(input)) != null) {
            controller.time();
        } else if ((matcher = GameMenuCommand.date.getMatcher(input)) != null) {
            controller.date();
        } else if ((matcher = GameMenuCommand.dateTime.getMatcher(input)) != null) {
            controller.dateTime();
        } else if ((matcher = GameMenuCommand.dayOfTheWeek.getMatcher(input)) != null) {
            controller.dayOfTheWeek();
        } else if ((matcher = GameMenuCommand.advanceTime.getMatcher(input)) != null) {
            controller.advanceTime(Integer.parseInt(matcher.group("time")));
        } else if ((matcher = GameMenuCommand.advanceDate.getMatcher(input)) != null) {
            controller.advanceDate(Integer.parseInt(matcher.group("date")));
        } else if ((matcher = GameMenuCommand.season.getMatcher(input)) != null) {
            controller.season();
        } else if ((matcher = GameMenuCommand.thor.getMatcher(input)) != null) {
            controller.thor(Integer.parseInt(matcher.group("x")), Integer.parseInt(matcher.group("y")));
        } else if ((matcher = GameMenuCommand.weather.getMatcher(input)) != null) {
            controller.weather();
        } else if ((matcher = GameMenuCommand.weatherForeCast.getMatcher(input)) != null) {
            controller.weatherForecast();
        } else if ((matcher = GameMenuCommand.weatherSet.getMatcher(input)) != null) {
            controller.weatherSet(matcher.group("weather"));
        } else if ((matcher = GameMenuCommand.buildGreenHouse.getMatcher(input)) != null) {
            controller.buildGreenHouse();
        } else if ((matcher = GameMenuCommand.walk.getMatcher(input)) != null) {
            controller.walk(matcher.group("x"), matcher.group("y"));
        } else if ((matcher = GameMenuCommand.printMap.getMatcher(input)) != null) {
            controller.printMap();
        } else if ((matcher = GameMenuCommand.helpReadingMap.getMatcher(input)) != null) {
            controller.helpReadingMap();
        } else if ((matcher = GameMenuCommand.showEnergy.getMatcher(input)) != null) {
            controller.showEnergy();
        } else if ((matcher = GameMenuCommand.setEnergy.getMatcher(input)) != null) {
            controller.setEnergy(Integer.parseInt(matcher.group("energy")));
        } else if ((matcher = GameMenuCommand.setEnergyUnlimited.getMatcher(input)) != null) {
            controller.setEnergyUnlimited();
        } else if ((matcher = GameMenuCommand.showInventory.getMatcher(input)) != null) {
            controller.showInventory();
        } else if ((matcher = GameMenuCommand.InventoryTrash.getMatcher(input)) != null) {
            controller.inventoryTrash(matcher.group("name"), Integer.parseInt(matcher.group("number")));
        } else if ((matcher = GameMenuCommand.equipTools.getMatcher(input)) != null) {
            controller.equipTool(matcher.group("name"));
        } else if ((matcher = GameMenuCommand.showCurrentTool.getMatcher(input)) != null) {
            controller.showCurrentTools();
        } else if ((matcher = GameMenuCommand.showAvailableTools.getMatcher(input)) != null) {
            controller.showAvailableTools();
        } else if ((matcher = GameMenuCommand.upgradeTool.getMatcher(input)) != null) {
            controller.upgradeTool(matcher.group("name"));
        } else if ((matcher = GameMenuCommand.useTool.getMatcher(input)) != null) {
            controller.useTool(matcher.group("direction"));
        } else if ((matcher = GameMenuCommand.craftInfo.getMatcher(input)) != null) {
            controller.craftInfo(matcher.group("name"));
        } else if ((matcher = GameMenuCommand.plant.getMatcher(input)) != null) {
            controller.plant(matcher.group("seedName"), matcher.group("direction"));
        } else if ((matcher = GameMenuCommand.showPlant.getMatcher(input)) != null) {
            controller.showPlant(Integer.parseInt(matcher.group("x")), Integer.parseInt(matcher.group("y")));
        } else if ((matcher = GameMenuCommand.fertilize.getMatcher(input)) != null) {
//            controller.fertilize(matcher.group("fertilizer"), matcher.group("direction"));
        } else if ((matcher = GameMenuCommand.howMuchWater.getMatcher(input)) != null) {
            controller.howMuchWater();
        } else if ((matcher = GameMenuCommand.showCraftingRecipes.getMatcher(input)) != null) {
            controller.craftingShowRecipes();
        } else if ((matcher = GameMenuCommand.craftCraftings.getMatcher(input)) != null) {
            controller.craft(matcher.group("name"));
        } else if ((matcher = GameMenuCommand.placeItem.getMatcher(input)) != null) {
            controller.placeItem(matcher.group("ItemName"), matcher.group("direction"));
        } else if ((matcher = GameMenuCommand.addItem.getMatcher(input)) != null) {
            controller.addItem(matcher.group("ItemName"), Integer.parseInt(matcher.group("count")));
        } else if ((matcher = GameMenuCommand.refigratoratorPut.getMatcher(input)) != null) {
            controller.refrigerator(false, matcher.group("ItemName"));
        } else if ((matcher = GameMenuCommand.refigratoratorPick.getMatcher(input)) != null) {
            controller.refrigerator(true, matcher.group("ItemName"));
        } else if ((matcher = GameMenuCommand.showCookingRecipes.getMatcher(input)) != null) {
            controller.showCookingRecipes();
        } else if ((matcher = GameMenuCommand.prepareFood.getMatcher(input)) != null) {
            controller.prepareFood(matcher.group("recipeName"));
        } else if ((matcher = GameMenuCommand.eat.getMatcher(input)) != null) {
            controller.eat(matcher.group("foodName"));
        } else if ((matcher = GameMenuCommand.build.getMatcher(input)) != null) {
            controller.build();
        } else if ((matcher = GameMenuCommand.buyAnimal.getMatcher(input)) != null) {
            controller.buyAnimal(matcher.group("animal"), matcher.group("animalName"));
        } else if ((matcher = GameMenuCommand.pet.getMatcher(input)) != null) {
            controller.pet(matcher.group("animalName"));
        } else if ((matcher = GameMenuCommand.setFreindship.getMatcher(input)) != null) {
            controller.setFriendship(matcher.group("animalName"), Integer.parseInt(matcher.group("value")));
        } else if ((matcher = GameMenuCommand.Animals.getMatcher(input)) != null) {
            controller.animals();
        } else if ((matcher = GameMenuCommand.shepherdAnimals.getMatcher(input)) != null) {
            controller.shepherdAnimal(matcher.group("animalName"), Integer.parseInt(matcher.group("x")), Integer.parseInt(matcher.group("y")));
        } else if ((matcher = GameMenuCommand.feedHay.getMatcher(input)) != null) {
            controller.feed(matcher.group("animalName"));
        } else if ((matcher = GameMenuCommand.produces.getMatcher(input)) != null) {
            controller.produces();
        } else if ((matcher = GameMenuCommand.collectProduces.getMatcher(input)) != null) {
            controller.collectProduce(matcher.group("animalName"));
        } else if ((matcher = GameMenuCommand.sellAnimal.getMatcher(input)) != null) {
            controller.sellAnimal(matcher.group("animalName"));
        } else if ((matcher = GameMenuCommand.fishing.getMatcher(input)) != null) {
            controller.fishing(matcher.group("fishingPole"));
        } else if ((matcher = GameMenuCommand.artisanUse.getMatcher(input)) != null) {
            List<ItemType> types = new ArrayList<>();
            for (ItemType itemType : ItemType.values()) {
                if (itemType.name().equalsIgnoreCase(matcher.group("itemName")))
                    types.add(itemType);
            }
            controller.artisanUse(matcher.group("artisanName"), types);
        } else if ((matcher = GameMenuCommand.artisanGet.getMatcher(input)) != null) {
            controller.artisanGet(matcher.group("artisanName"));
        } else if ((matcher = GameMenuCommand.showAllProducts.getMatcher(input)) != null) {
            controller.showAllProducts();
        } else if ((matcher = GameMenuCommand.showAllAvailableProducts.getMatcher(input)) != null) {
            controller.showAvailableProducts();
        } else if ((matcher = GameMenuCommand.purchase.getMatcher(input)) != null) {
            controller.Purchase(matcher.group("productName"), Integer.parseInt(matcher.group("count")));
        } else if ((matcher = GameMenuCommand.addDollars.getMatcher(input)) != null) {
            controller.addDollars(Integer.parseInt(matcher.group("count")));
        } else if ((matcher = GameMenuCommand.sell.getMatcher(input)) != null) {
            controller.sell(matcher.group("productName"),Integer.parseInt( matcher.group("count")));
        } else if ((matcher = GameMenuCommand.friendships.getMatcher(input)) != null) {
            controller.friendships();
        } else if ((matcher = GameMenuCommand.talk.getMatcher(input)) != null) {
            controller.talk(matcher.group("username"), matcher.group("message"));
        } else if ((matcher = GameMenuCommand.talkHistory.getMatcher(input)) != null) {
            controller.talkHistory(matcher.group("username"));
        } else if ((matcher = GameMenuCommand.gift.getMatcher(input)) != null) {
            controller.gift(matcher.group("username"), matcher.group("itemName"), matcher.group("amount"));
        } else if ((matcher = GameMenuCommand.giftList.getMatcher(input)) != null) {
            controller.giftList();
        } else if ((matcher = GameMenuCommand.giftHistory.getMatcher(input)) != null) {
            controller.giftHistory(matcher.group("username"));
        } else if ((matcher = GameMenuCommand.hug.getMatcher(input)) != null) {
            controller.hug(matcher.group("username"));
        } else if ((matcher = GameMenuCommand.flower.getMatcher(input)) != null) {
            controller.flower(matcher.group("username"));
        } else if ((matcher = GameMenuCommand.askMarriage.getMatcher(input)) != null) {
            controller.askMarriage(matcher.group("username"), matcher.group("ring"));
        } else if ((matcher = GameMenuCommand.meetNPC.getMatcher(input)) != null) {
            controller.meetNPC(matcher.group("npcName"));
        } else if ((matcher = GameMenuCommand.giftNPC.getMatcher(input)) != null) {
            controller.giftNPC(matcher.group("npcName"), matcher.group("itemName"));
        } else if ((matcher = GameMenuCommand.friendshipNPCList.getMatcher(input)) != null) {
            controller.friendshipNPCList();
        } else if ((matcher = GameMenuCommand.questsList.getMatcher(input)) != null) {
            controller.questsList(matcher.group("npcName"));
        } else if ((matcher = GameMenuCommand.questFinish.getMatcher(input)) != null) {
            controller.questFinish(matcher.group("npcName"), matcher.group("index"));
        } else {
            System.out.println("invalid command");
        }

}

    @Override
    public void show() {
        table = new Table();
        stage = new Stage();
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        Gdx.input.setInputProcessor(this);
//        Label title = new Label("Game Menu", skin);
//        table.add(title).pad(20).row();
//        TextButton playersButton = new TextButton("Players", skin);
//        playersButton.addListener(new com.badlogic.gdx.scenes.scene2d.utils.ClickListener() {
//            @Override
//            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
//                showPlayersWindow();
//            }
//        });
//        table.add(playersButton).pad(10).row();
//        TextButton backButton = new TextButton("Back", skin);
//        backButton.addListener(new com.badlogic.gdx.scenes.scene2d.utils.ClickListener() {
//            @Override
//            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
//                main.setScreen(new MainMenu(main));
//            }
//        });
//        table.add(backButton).pad(20).row();
        for(TextButton textButton : MessageManager.getTextButtons().keySet()){
            stage.addActor(textButton);
            MessageManager.setChanged(false);
        }
    }

    @Override
    public void render(float delta) {
        SCREEN_WIDTH = Gdx.graphics.getWidth();
        SCREEN_HEIGHT = Gdx.graphics.getHeight();
        ScreenUtils.clear(0, 0, 0, 1);
        Gdx.gl.glClearColor(0, 0, 0, 1); // RGB + Alpha
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        TOTAL_TIME_SPENT += delta;
        controller.updateGame(delta, false);

        if (!batch.isDrawing()) {
            batch.begin();
        }
        for (Sprite s : controller.getSprites()) {
            s.draw(batch);
        }
        if (batch.isDrawing()) {
            batch.end();
        }

        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0, 0, 0, nightScreenAlpha);
        shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        shapeRenderer.end();

        Gdx.gl.glDisable(GL20.GL_BLEND);



        if (isThunderActive) {
            Gdx.gl.glEnable(GL20.GL_BLEND);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(1f, 1f, 1f, thunderAlpha);
            shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            shapeRenderer.end();
            Gdx.gl.glDisable(GL20.GL_BLEND);

            thunderAlpha -= Gdx.graphics.getDeltaTime();
            if (thunderAlpha <= 0f) {
                thunderAlpha = 0f;
                isThunderActive = false;
            }
        }
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        MessageManager.update(delta);
        if(MessageManager.isChanged()){
            stage = new Stage();
            for(TextButton textButton : MessageManager.getTextButtons().keySet()){
                stage.addActor(textButton);
                MessageManager.setChanged(false);
            }
            MessageManager.setChanged(false);
        }

        if(goingInHouse){
            TextButton sleep = Tab.createTextButton("sleep");
            TextButton refrigerator = Tab.createTextButton("refrigerator");
            TextButton cancel = Tab.createTextButton("cancel");
            sleep.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    ClientApp.getInstance().getConnectionThread().sendPacket(new ClickPacket(App.getMyPlayer(), TextButtonType.sleep, AbilityMenu.class));

                    try{
                        TimeCheatMenu.setIsCheatActivate(true);
                        float time = 6- App.getGame().dateAndTime.getHour();
                        if(App.getGame().dateAndTime.getHour() > 6){
                            time += 24;
                        }
                        TimeCheatMenu.setCheatHours(time);
                        Main.main.setScreen(new TimeCheatMenu());
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
            cancel.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {

                    try{
                        Main.main.setScreen(GameMenu.getInstance());
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
            refrigerator.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {

                    try{
                        Main.main.setScreen(RefrigeratorMenu.getInstance());
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });

            sleep.setPosition(100, 30);
            refrigerator.setPosition(100, 90);
            cancel.setPosition(100, 150);
            sleep.setSize(200, 50);
            refrigerator.setSize(200, 50);
            cancel.setSize(200, 50);
            stage.addActor(sleep);
            stage.addActor(refrigerator);
            stage.addActor(cancel);
            goingInHouse = false;
        }
    }

    public void updateScreen(float delta){
        if(isGettingLight)
            nightScreenAlpha -= delta/30;
        if(isGettingDark)
            nightScreenAlpha += delta/30;
        if(nightScreenAlpha >= 0.63){
            nightScreenAlpha = 0.63f;
            isGettingDark=false;
        }
        if(nightScreenAlpha <=0){
            nightScreenAlpha =0;
            isGettingLight = false;
        }
    }


    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean keyDown(int keycode) {
        ClientApp.getInstance().getConnectionThread().sendPacket(new KeyDownPacket(App.getMyPlayer(),keycode, AbilityMenu.class));

        return true;
    }

    public void useItem(int x, int y, Item item){
        if(item instanceof Tool){
            controller.activity.useTool(x, y);
            ((Tool) App.getCurrentPlayer().backpack.getItemInHand()).setMoving(true);
        }
        else if(item.getItemType().equals(ItemType.DeluxeSoil) || item.getItemType().equals(ItemType.SpeedGro)){
            controller.fertilize(x, y, item);
        }

        else if(item instanceof Food){
            controller.abilities.cooking.eat(item.getItemType());
            App.getCurrentPlayer().backpack.setItemInHand(null);
        }

        else if(item instanceof CoopAndBarn){
            item.getSprite().setPosition(mouseX, mouseY);
            App.getCurrentPlayer().backpack.getCoopsAndBarns().add((CoopAndBarn) item);
            App.getCurrentPlayer().backpack.setItemInHand(null);
            App.getGame().getGameMap().getTiles()[y][x].setTileKind(TileKind.coop);
            App.getGame().getGameMap().getTiles()[y][x].setItem(item);
        }

        else if(item instanceof Artisan){
            item.getSprite().setPosition(mouseX, mouseY);
            App.getCurrentPlayer().backpack.setItemInHand(null);
            App.getGame().getGameMap().getTiles()[y][x].setTileKind(TileKind.artisan);
            showFullTiles=false;
            App.getGame().getGameMap().getTiles()[y][x].setItem(item);
        }
    }

    @Override
    public boolean keyUp(int keycode) {
        ClientApp.getInstance().getConnectionThread().sendPacket(new KeyUpPacket(App.getMyPlayer(), keycode, AbilityMenu.class));


        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        ClientApp.getInstance().getConnectionThread().sendPacket(new TouchDownPacket(App.getMyPlayer(), screenX, screenY, pointer, button ,AbilityMenu.class));


        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {

        ClientApp.getInstance().getConnectionThread().sendPacket(new MouseMovePacket(App.getMyPlayer(), screenX, screenY, AbilityMenu.class));

        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    public void triggerThunder() {
        thunderAlpha = 1f; // full white
        isThunderActive = true;
    }

    public static float getTotalTimeSpent(){
        return TOTAL_TIME_SPENT;
    }

    public static int getScreenWidth() {
        return SCREEN_WIDTH;
    }

    public static int getScreenHeight() {
        return SCREEN_HEIGHT;
    }
    public void setGettingDark(boolean gettingDark) {
        isGettingDark = gettingDark;
    }

    public void setGettingLight(boolean gettingLight) {
        isGettingLight = gettingLight;
    }

    public static float angleBetweenPoints(double x1, double y1, double x2, double y2) {
        double dx = x2 - x1;
        double dy = y2 - y1;
        double radians = Math.atan2(dy, dx);
        double degrees = Math.toDegrees(radians);

        if (degrees < 0) {
            degrees += 360;
        }

        return(float) degrees;
    }

    public float getMouseX() {
        return mouseX;
    }

    public float getMouseY() {
        return mouseY;
    }

    public boolean isSetToolToMouse() {
        return setToolToMouse;
    }

    public void setSetToolToMouse(boolean setToolToMouse) {
        this.setToolToMouse = setToolToMouse;
    }

    public boolean isGoingInHouse() {
        return goingInHouse;
    }

    public void setGoingInHouse(boolean goingInHouse) {
        if(goingInHouse)
            Gdx.input.setInputProcessor(stage);
        else
            Gdx.input.setInputProcessor(this);
        this.goingInHouse = goingInHouse;
    }


    public boolean isSHOW_TILE_DETAILS() {
        return SHOW_TILE_DETAILS;
    }

    public void setSHOW_TILE_DETAILS(boolean SHOW_TILE_DETAILS) {
        this.SHOW_TILE_DETAILS = SHOW_TILE_DETAILS;
    }

    public boolean isShowFullTiles() {
        return showFullTiles;
    }

    public void setShowFullTiles(boolean showFullTiles) {
        this.showFullTiles = showFullTiles;
    }


    private void showPlayersWindow() {
        Array<String> usernames = new Array<String>();
        try {
            String projectRoot = System.getProperty("user.dir");
            com.badlogic.gdx.files.FileHandle profilesDir = Gdx.files.absolute(projectRoot + "/profiles");
            if (profilesDir.exists()) {
                for (com.badlogic.gdx.files.FileHandle file : profilesDir.list()) {
                    if (file.extension().equals("json")
                        && !file.nameWithoutExtension().equalsIgnoreCase("lastlog")
                        && !file.nameWithoutExtension().equalsIgnoreCase(currentPlayerName)
                    ) {
                        usernames.add(file.nameWithoutExtension());
                    }
                }
            }
        } catch (Exception e) {
        }
        if (usernames.size == 0) {
            usernames.add("ali");
            usernames.add("mammad");
            usernames.add("sadra");
        }

        Window playersWindow = new Window("Select Players", skin);
        playersWindow.setSize(600, 700);
        playersWindow.setPosition(Gdx.graphics.getWidth() / 2 - playersWindow.getWidth() / 2,
                Gdx.graphics.getHeight() / 2 - playersWindow.getHeight() / 2);

        TextButton profileDropdown1 = new TextButton(App.getCurrentPlayer().getPersonalInfo().getName(), skin);
        playersWindow.add(new Label("Profile 1:", skin)).pad(10);
        playersWindow.add(profileDropdown1).pad(10).row();

        SelectBox<String> profileDropdown2 = new SelectBox<String>(skin);
        profileDropdown2.setItems(usernames);
        playersWindow.add(new Label("Profile 2:", skin)).pad(10);
        playersWindow.add(profileDropdown2).pad(10).row();

        SelectBox<String> profileDropdown3 = new SelectBox<String>(skin);
        profileDropdown3.setItems(usernames);
        playersWindow.add(new Label("Profile 3:", skin)).pad(10);
        playersWindow.add(profileDropdown3).pad(10).row();

        SelectBox<String> profileDropdown4 = new SelectBox<String>(skin);
        profileDropdown4.setItems(usernames);
        playersWindow.add(new Label("Profile 4:", skin)).pad(10);
        playersWindow.add(profileDropdown4).pad(10).row();

        TextButton closeButton = new TextButton("Close", skin);
        closeButton.addListener(new com.badlogic.gdx.scenes.scene2d.utils.ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                playersWindow.remove();
            }
        });
        playersWindow.add(closeButton).pad(10).colspan(2);

        stage.addActor(playersWindow);
    }
}
