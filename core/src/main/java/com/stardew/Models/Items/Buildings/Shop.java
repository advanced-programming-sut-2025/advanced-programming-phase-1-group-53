package com.stardew.Models.Items.Buildings;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.stardew.Controllers.GameMenuController;
import com.stardew.Enums.ShopNames;
import com.stardew.Main;
import com.stardew.Models.Game.App;
import com.stardew.Models.Game.GameAssetManager;
import com.stardew.Models.Position;
import com.stardew.Models.Product;
import com.stardew.Views.TabMenus.ShopMenu;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class Shop extends Building {
    private static final int VILLAGE_START_X = 20;
    private static final int VILLAGE_START_Y = 20;
    private TextureRegion[] textureRegions;

    private final ArrayList<Product> products;
    private final ShopNames shopName;
    private ShopMenu shopMenu = new ShopMenu();

    private Shop(Position position, ShopNames shopName, ArrayList<Product> products) {
        super(position);
        this.products = new ArrayList<>(products);
        this.shopName = shopName;
    }

    public Shop setTextures(TextureRegion[] textureRegions){
        this.textureRegions = textureRegions ;
        sprite = new Sprite(textureRegions[0]);
        return this;
    }

    @Override
    public Sprite getSprite(){
        try{
            sprite = new Sprite(textureRegions[App.getGame().dateAndTime.getSeason().ordinal()]);
            return super.getSprite();
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static final Shop TheStardropSaloon = new Shop(
            new Position(VILLAGE_START_X + 2, VILLAGE_START_Y + 2, 3, 3), ShopNames.TheStardropSaloon
        , Product.StarDropSaloonProducts).setTextures(GameAssetManager.getStardropSprites());
    public static final Shop JojaMart = new Shop(
            new Position(VILLAGE_START_X + 6, VILLAGE_START_Y + 2, 3, 3), ShopNames.JojaMart
        , Product.JojaMart).setTextures(GameAssetManager.getJojaMartSprites());
    public static final Shop PierreGeneralStore = new Shop(
            new Position(VILLAGE_START_X + 10, VILLAGE_START_Y + 2, 3, 3), ShopNames.PierreGeneralStore
        , Product.PierresGeneralStore).setTextures(GameAssetManager.getPierreSprites());
    public static final Shop Blacksmith = new Shop(
            new Position(VILLAGE_START_X + 2, VILLAGE_START_Y + 6, 3, 3), ShopNames.Blacksmith
        , Product.BlackSmithProducts).setTextures(GameAssetManager.getBlackSmithSprites());
    public static final Shop CarpenterShop = new Shop(
            new Position(VILLAGE_START_X + 6, VILLAGE_START_Y + 6, 3, 3), ShopNames.CarpenterShop
    , Product.CarpetnersShopProducts).setTextures(GameAssetManager.getCarpetnerSprites());
    public static final Shop FishShop = new Shop(
            new Position(VILLAGE_START_X + 10, VILLAGE_START_Y + 6, 3, 3), ShopNames.FishShop
    , Product.FishShopProducts).setTextures(GameAssetManager.getFishStoreSprites());
    public static final Shop MarineRanch = new Shop(
            new Position(VILLAGE_START_X + 6, VILLAGE_START_Y + 10, 3, 3), ShopNames.MarineRanch
    , Product.MarniesRanchProducts).setTextures(GameAssetManager.getMarnieRanchSprites());

    public static final ArrayList<Shop> shops = new ArrayList<>(){{
        add(MarineRanch);
//        add(FishShop);
//        add(CarpenterShop);
//        add(Blacksmith);
//        add(PierreGeneralStore);
//        add(JojaMart);
//        add(TheStardropSaloon);
    }};

    public void setUpShopMenu(){
        shopMenu.setProducts(products);
        Main.main.setScreen(shopMenu);
        shopMenu.setChanged(true);
    }

    public static Shop getShopByPosition(int x, int y){
        for(Shop shop : shops){
            if(GameMenuController.coordinateCollision(x, 0, shop.position.getX(), shop.position.getWidth()) &&
                GameMenuController.coordinateCollision(y, 0, shop.position.getY(), shop.position.getHeight()))
                return shop;
        }
        return null;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public ShopNames getShopName() {
        return shopName;
    }
}
