package Models.Abilities;

import Enums.ItemType;
import Models.Game.App;
import Models.Items.Buildings.Shop;
import Models.Items.Item;
import Models.MessageManager;
import Models.Product;
import Models.Result;

public class Shopping {

    public void showAllProducts(){
        if(! (App.getGame().getGameMap().findBuilding(App.getGame().getCurrentPlayer().position.getX(),
                App.getGame().getCurrentPlayer().position.getY()) instanceof Shop shop)){
            MessageManager.getMessage(Result.failure("You must be inside a shop."));
            return;
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ALL PRODUCTS :\n________________");

        for(Product product : shop.getProducts()){
            stringBuilder.append("\n"+product.getItemType().name() + "   Price : "+ product.getPrice());
        }
        MessageManager.getMessage(Result.success(stringBuilder.toString()));
    }

    public void showAvailableProducts(){
        if(! (App.getGame().getGameMap().findBuilding(App.getGame().getCurrentPlayer().position.getX(),
                App.getGame().getCurrentPlayer().position.getY()) instanceof Shop shop)){
            MessageManager.getMessage(Result.failure("You must be inside a shop."));
            return;
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ALL PRODUCTS :\n________________");

        for(Product product : shop.getProducts()){
            if(product.getAvailableToday() == 0)
                continue;
            stringBuilder.append("\n"+product.getItemType().name() + "   Price : "+ product.getPrice());
        }

        if(stringBuilder.isEmpty()){
            MessageManager.getMessage(Result.failure("No product is available."));
            return;
        }
        MessageManager.getMessage(Result.success(stringBuilder.toString()));
    }

    public void purchase(ItemType itemType, int count){
        if(! (App.getGame().getGameMap().findBuilding(App.getGame().getCurrentPlayer().position.getX(),
                App.getGame().getCurrentPlayer().position.getY()) instanceof Shop shop)){
            MessageManager.getMessage(Result.failure("You must be inside a shop."));
            return;
        }

        for(Product product : shop.getProducts()){
            if(product.getItemType().equals(itemType)){
                if(product.getAvailableToday() < count){
                    MessageManager.getMessage(Result.success("The product is not available today, maybe tomorrow."));
                    return;
                }
                if(App.getGame().getCurrentPlayer().personalInfo.hasEnoughGold(count * product.getPrice())){
                    App.getGame().getCurrentPlayer().backpack.addItem(App.getGame().getItemByItemType(itemType), count);
                    return;
                }
                MessageManager.getMessage(Result.failure("Insufficient balance."));
                return;
            }
        }
        MessageManager.getMessage(Result.failure("No product with such name in " + shop.getShopName()));
    }

    public void sell(ItemType itemType, int count){
        if(App.getGame().getItemByItemType(itemType).getBaseSellPrice() == 0){
            MessageManager.getMessage(Result.failure("the product cant be sold!"));
            return;
        }

        if(!App.getGame().getCurrentPlayer().backpack.areItemsAvailable(App.getGame().getItemByItemType(itemType), count)){
            MessageManager.getMessage(Result.failure("Not enough quantity of the product."));
            return;
        }

        /*if(!/*not next to the shipping bin){
            MessageManager.getMessage(Result.failure("You must be next to a shipping bin."));
            return;
        }

        Item item = App.getGame().getItemByItemType(itemType);

        App.getGame().getCurrentPlayer().backpack.getItems().compute(item, (k, v) -> (v - count));
        App.*/
    }

}
