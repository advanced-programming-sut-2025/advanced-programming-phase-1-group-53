package com.stardew.Models.Abilities;

import com.stardew.Enums.ItemType;
import com.stardew.Models.Game.App;
import com.stardew.Models.Items.Animal;
import com.stardew.Models.Items.Buildings.Shop;
import com.stardew.Models.Items.CoopAndBarn;
import com.stardew.Models.Items.Item;
import com.stardew.Models.Items.ShippingBin;
import com.stardew.Models.MessageManager;
import com.stardew.Models.Product;
import com.stardew.Models.Result;

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
                    App.getGame().getCurrentPlayer().personalInfo.updateGold(count * product.getPrice());
                    App.getGame().getCurrentPlayer().backpack.addItem(App.getGame().getItemByItemType(itemType), count);
                    return;
                }
                MessageManager.getMessage(Result.failure("Insufficient balance."));
                return;
            }
        }
        MessageManager.getMessage(Result.failure("No product with such name in " + shop.getShopName()));
    }

    public void purchase(ItemType itemType, String name){
        if(! (App.getGame().getGameMap().findBuilding(App.getGame().getCurrentPlayer().position.getX(),
                App.getGame().getCurrentPlayer().position.getY()) instanceof Shop shop)){
            MessageManager.getMessage(Result.failure("You must be inside a shop."));
            return;
        }

        for(Product product : shop.getProducts()){
            if(product.getItemType().equals(itemType)){
                if(product.getAvailableToday() < 1){
                    MessageManager.getMessage(Result.success("The product is not available today, maybe tomorrow."));
                    return;
                }
                if(!(App.getGame().getItemByItemType(itemType) instanceof Animal animal)){
                    MessageManager.getMessage(Result.failure("hsjab"));
                    return;
                }
                if(App.getGame().getCurrentPlayer().personalInfo.hasEnoughGold( product.getPrice())){
                    for(CoopAndBarn coopAndBarn : App.getGame().getCurrentPlayer().backpack.getCoopsAndBarns()){
                        if(coopAndBarn.getItemType().name().contains("Coop")){
                            if(animal.getItemType().equals(ItemType.Hen) || animal.getItemType().equals(ItemType.Duck)
                                    || animal.getItemType().equals(ItemType.Dino) || animal.getItemType().equals(ItemType.Rabbit)){
                                Animal animal1 = animal.clone(name);
                                animal1.getPosition().setX(coopAndBarn.getPosition().getX());
                                animal1.getPosition().setY(coopAndBarn.getPosition().getY());
                                coopAndBarn.getAnimals().add(animal1);
                                App.getGame().getCurrentPlayer().personalInfo.updateGold((int) animal1.getBaseSellPrice());
                                App.getGame().getCurrentPlayer().backpack.addItem(animal.clone(name), 1);
                                return;
                            }
                        }
                    }
                    App.getGame().getCurrentPlayer().backpack.addItem(animal.clone(name), 1);
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

        if(nextToShippingBin(App.getGame().getCurrentPlayer().position.getX(),
                App.getGame().getCurrentPlayer().position.getY()) == null){
            MessageManager.getMessage(Result.failure("You must be next to a shipping bin."));
            return;
        }

        Item item = App.getGame().getItemByItemType(itemType);

        App.getGame().getCurrentPlayer().backpack.getItems().compute(item, (k, v) -> (v - count));((ShippingBin) nextToShippingBin(App.getGame().getCurrentPlayer().position.getX(),
                App.getGame().getCurrentPlayer().position.getY())).getItems().compute(App.getGame().getItemByItemType(itemType),
                (k, v) -> ( (v==null)? count : (v + count)));
    }

    public ShippingBin nextToShippingBin(int x, int y){
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        for(int i =0; i< 4; i++){
            if(App.getGame().findTile(x + dx[i], y+ dy[i]).getItem().getItemType().equals(ItemType.ShippingBin)){
                return (ShippingBin) App.getGame().findTile(x + dx[i], y+ dy[i]).getItem();
            }
        }
        return null;
    }

}
