package Models.Items.Buildings;

import Enums.ShopNames;
import Models.Position;
import Models.Product;

import java.util.ArrayList;

public class Shop extends Building {
    private static final int VILLAGE_START_X = 20;
    private static final int VILLAGE_START_Y = 20;

    private final ArrayList<Product> products;
    private final ShopNames shopName;

    private Shop(Position position, ShopNames shopName) {
        super(position);
        this.products = new ArrayList<>();
        this.shopName = shopName;
    }

    public static final Shop TheStardropSaloon = new Shop(
        new Position(VILLAGE_START_X + 2, VILLAGE_START_Y + 2, 3, 3), ShopNames.TheStardropSaloon
    );
    public static final Shop JojaMart = new Shop(
        new Position(VILLAGE_START_X + 6, VILLAGE_START_Y + 2, 3, 3), ShopNames.JojaMart
    );
    public static final Shop PierreGeneralStore = new Shop(
        new Position(VILLAGE_START_X + 10, VILLAGE_START_Y + 2, 3, 3), ShopNames.PierreGeneralStore
    );
    public static final Shop Blacksmith = new Shop(
        new Position(VILLAGE_START_X + 2, VILLAGE_START_Y + 6, 3, 3), ShopNames.Blacksmith
    );
    public static final Shop CarpenterShop = new Shop(
        new Position(VILLAGE_START_X + 6, VILLAGE_START_Y + 6, 3, 3), ShopNames.CarpenterShop
    );
    public static final Shop FishShop = new Shop(
        new Position(VILLAGE_START_X + 10, VILLAGE_START_Y + 6, 3, 3), ShopNames.FishShop
    );
    public static final Shop MarineRanch = new Shop(
        new Position(VILLAGE_START_X + 6, VILLAGE_START_Y + 10, 3, 3), ShopNames.MarineRanch
    );

    public ArrayList<Product> getProducts() {
        return products;
    }

    public ShopNames getShopName() {
        return shopName;
    }
}
