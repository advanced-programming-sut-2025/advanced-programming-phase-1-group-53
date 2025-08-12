package com.stardew.Models.Items;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.stardew.Controllers.GameMenuController;
import com.stardew.Enums.ItemType;
import com.stardew.Enums.Season;
import com.stardew.Main;
import com.stardew.Models.Game.App;
import com.stardew.Models.Game.GameAssetManager;
import com.stardew.Models.GameMap;
import com.stardew.Models.MessageManager;
import com.stardew.Models.Product;
import com.stardew.Models.Result;
import com.stardew.Views.TabMenus.AnimalMenu;

import java.util.*;

public class Animal extends Item{
    private static HashMap<Sprite, Float> animalSprites = new HashMap<>();
    private int friendship = 0;
    private boolean isPettedToday = false;
    private boolean isFedToday = false;
    private boolean isOut = false;
    private String animalName;
    private final int cycleOfProduceDuring;
    private int daysToProduce;
    private int homeX;
    private int homeY;
    private boolean isMoving = false;
    private int direction  = 3;// left  right  back  forward
    private int numOfSprite = 0;
    private float originX ;
    private float originY;
    private final int MAX_MOVEMENT_DISTANCE = 400;
    private float TIME_GOING_CURRENT_WAY  = 0;
    private final float ADVANCE_OF_EACH_STEP = 0.3f;
    private float STEPING_TIME;
    private Random random = new Random();
    private AnimalMenu animalMenu = new AnimalMenu();


    private ArrayList<AnimalProduct> producedProducts = new ArrayList<>();
    private List<AnimalProduct> animalProds = new ArrayList<>();
    private ItemType livingPlace;

    public Animal(ItemType itemType, int cycleOfProduce, List<AnimalProduct> animalProds, ItemType livingPlace) {
        super(itemType);
        this.cycleOfProduceDuring = cycleOfProduce;
        this.daysToProduce = cycleOfProduce;
        this.animalProds.addAll(animalProds);
        this.livingPlace = livingPlace;
    }


    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    public boolean isPettedToday() {
        return isPettedToday;
    }

    public boolean isFedToday() {
        return isFedToday;
    }

    public boolean isOut() {
        return isOut;
    }

    public void setPettedToday(boolean pettedToday) {
        isPettedToday = pettedToday;
    }

    public void setFedToday(boolean fedToday) {
        isFedToday = fedToday;
    }

    public void setOut(boolean out) {
        isMoving = out;
        isOut = out;
    }

    public String getName() {
        return animalName;
    }

    public int getCycleOfProduceDuring() {
        return cycleOfProduceDuring;
    }

    public int getDaysToProduce() {
        return daysToProduce;
    }

    public int getHomeX() {
        return homeX;
    }

    public int getHomeY() {
        return homeY;
    }

    public ArrayList<AnimalProduct> getProducedProducts() {
        return producedProducts;
    }

    public List<AnimalProduct> getAnimalProds() {
        return animalProds;
    }

    public ItemType getLivingPlace() {
        return livingPlace;
    }

    public int getFriendship() {
        return friendship;
    }

    public void setFriendship(int friendship) {
        this.friendship = friendship;
    }

    public void updateFriendship(int a){
        friendship = Math.min(1000, friendship + a);
        friendship = Math.max(0, friendship);
    }

    public void setOriginY(float originY) {
        this.originY = originY;
    }

    public void setOriginX(float originX) {
        this.originX = originX;
    }

    public static void updateSprites(float delta){
        ArrayList<Sprite> mustRemove = new ArrayList<>();
        for(Sprite s : animalSprites.keySet()){
            animalSprites.compute(s, (k, v) -> v-delta);
            if(animalSprites.get(s) <= 0)
                mustRemove.add(s);
        }
        for(Sprite s : mustRemove){
            animalSprites.remove(s);
        }
    }

    public String details(){
        StringBuilder sb = new StringBuilder();
        sb.append("Animal : "+ itemType);
        sb.append(", Name : ");
        sb.append("\nfriendship level : "+friendship);
        sb.append("\n is petted today : "+ isPettedToday);
        sb.append(", is fed today : "+ isFedToday);
        sb.append("\nproducts : ");
        for(Item item : producedProducts){
            sb.append(", "+item.getItemType());
        }
        return sb.toString();
    }
    @Override
    public Animal makeSellPrice(double price){
        baseSellPrice = price;
        return this;
    }

    private void updateMovement(float delta){
        TIME_GOING_CURRENT_WAY+= delta;
        STEPING_TIME += delta;
        if(isOut){
            if(TIME_GOING_CURRENT_WAY > 3){
                TIME_GOING_CURRENT_WAY = 0;
                direction = random.nextInt(4);
                System.out.println(direction);
                numOfSprite = 0;
                STEPING_TIME = 0;
            }
            float x = 0;
            float y = 0;
            if(isMoving){
                if(direction == 0)
                    x = -ADVANCE_OF_EACH_STEP;
                if(direction == 1)
                    x = ADVANCE_OF_EACH_STEP;
                if(direction == 2)
                    y=ADVANCE_OF_EACH_STEP;
                if(direction == 3)
                    y=-ADVANCE_OF_EACH_STEP;
                if(Math.hypot(sprite.getX() + x - originX, sprite.getY() + y - originY) > MAX_MOVEMENT_DISTANCE)
                    TIME_GOING_CURRENT_WAY = 6;
                else
                    sprite.setPosition(sprite.getX() + x, sprite.getY()+y);
                if(STEPING_TIME >= 0.4){
                    numOfSprite += 1;
                    numOfSprite%=4;
                    STEPING_TIME  = 0;
                }
            }
        }
    }

    @Override
    public void update(float delta){
        updateMovement(delta);
        if(App.getGame().dateAndTime.isADayPassed()){
            daysToProduce--;
            if(daysToProduce<=0){
                daysToProduce = cycleOfProduceDuring;
                produce();
            }
            if(isOut)
                updateFriendship(-20);
            if(!isFedToday)
                updateFriendship(-20);
            if(!isPettedToday)
                updateFriendship(friendship/200 - 10);
            isPettedToday = false;
            isFedToday = false;
        }
    }

    public void produce(){
        if(App.getGame().dateAndTime.getSeason().equals(Season.WINTER) && itemType.equals(ItemType.Pig)){
            return;
        }
        double quality =((double) (friendship/1000))*(0.5 + 0.5 * new Random().nextDouble(0, 1));
        double coeff = 1;
        if(quality > 0)
            coeff = 1;
        if(quality > 0.5)
            coeff = 1.25;
        if(quality > 0.7)
            coeff = 1.5;
        if(quality > 0.9)
            coeff =2;
        float probability =((float) (friendship + 150 * new Random().nextDouble(0.5, 1.5)) / 250);
        int prodIndex = 0;
        if(probability>=1 )
            prodIndex = animalProds.size()-1;
        producedProducts.add(animalProds.get(prodIndex).clone().makeSellPrice(animalProds.get(prodIndex).getBaseSellPrice()*coeff));
    }

    public Animal clone(String name){
        Animal animal = new Animal(itemType, cycleOfProduceDuring, animalProds, livingPlace).makeSellPrice(baseSellPrice);
        animal.animalName = name;
        return animal;
    }

    public void pet(){
        boolean b1 = GameMenuController.coordinateCollision(App.getCurrentPlayer().getSprite().getX()
            +GameMenuController.mvc.getV2().x, GameMap.getTilePrintSize(), sprite.getX(), sprite.getWidth());
        boolean b2 = GameMenuController.coordinateCollision(App.getCurrentPlayer().getSprite().getY()
            +GameMenuController.mvc.getV2().y, GameMap.getTilePrintSize(), sprite.getY(), sprite.getHeight());
        if(b1 && b2){
            Sprite s = new Sprite(GameAssetManager.getHeartSprite());
            s.setPosition(sprite.getX()+sprite.getWidth(), sprite.getY()+sprite.getHeight());
            animalSprites.put(s, 3f);
            friendship = Math.min(1000, friendship + 15);
            isPettedToday = true;
        }
    }

    public void setUpAnimalMenu(){
        animalMenu.setAnimal(this);
        animalMenu.setChanged(true);
        Main.main.setScreen(animalMenu);
    }

    public void feed(){
        isFedToday = true;

    }

    public static HashMap<Sprite, Float> getAnimalSprites(){
        return animalSprites;
    }

    public Sprite getShowSprite(){
        return new Sprite(GameAssetManager.getShowAnimals().get(itemType));
    }

    @Override
    public Sprite getSprite(){
        float x = 0;
        float y = 0;
        if(sprite != null){
            x = sprite.getX();
            y = sprite.getY();
        }
        sprite = new Sprite(GameAssetManager.getAnimalSprites().get(itemType)[direction][numOfSprite]);
        sprite.setPosition(x, y);
        sprite.setSize((float) (sprite.getWidth()*2.7), (float) (sprite.getHeight()*2.7));
        return sprite;
    }

    public void changePlace(int x, int y){
        //TODO : با استفاده از توابعی که از خسرو میگیریم
        isOut = true;// or false
        if (!isFedToday)
            updateFriendship(8);
        isFedToday = true;
    }

    public void collectProducts(){
        if(itemType.equals(ItemType.Cow) || itemType.equals(ItemType.Goat)){
            if(!App.getGame().getCurrentPlayer().backpack.areItemsAvailable(App.getGame().getItemByItemType(ItemType.MilkPail), 1)) {
                MessageManager.getMessage(Result.failure("You don't have requirements to collect the product."));
                return;
            }
            updateFriendship(5);
        }
        if(itemType.equals(ItemType.Sheep) || itemType.equals(ItemType.Rabbit)){
            if(!App.getGame().getCurrentPlayer().backpack.areItemsAvailable(App.getGame().getItemByItemType(ItemType.Shear), 1)) {
                MessageManager.getMessage(Result.failure("You don't have requirements to collect the product."));
                return;
            }
            updateFriendship(5);
        }
        if(itemType.equals(ItemType.Pig) && !isOut){
            MessageManager.getMessage(Result.failure("The pig must be out of barn to collect truffle."));
            return;
        }
        if(producedProducts.isEmpty()){
            MessageManager.getMessage(Result.failure("No product to collect."));
            return;
        }
        for(AnimalProduct animalProduct : producedProducts) {
            App.getGame().getCurrentPlayer().backpack.addItem(animalProduct);
        }
        MessageManager.getMessage(Result.success("Products collected successfully."));
    }

    public static final Animal Hen = new Animal(ItemType.Hen, 1, List.of(AnimalProduct.Egg,  AnimalProduct.BigEgg), ItemType.NormalCoop).makeSellPrice(800);
    public static final Animal Rabbit = new Animal(ItemType.Rabbit, 4, List.of(AnimalProduct.RabbitLeg, AnimalProduct.RabbitWool), ItemType.BigCoop).makeSellPrice(8000);
    public static final Animal Duck = new Animal(ItemType.Duck, 2, List.of(AnimalProduct.DuckEgg, AnimalProduct.DuckFeather), ItemType.DeluxeCoop).makeSellPrice(1200);
    public static final Animal Dino = new Animal(ItemType.Dino, 7, List.of(AnimalProduct.DinoEgg), ItemType.DeluxeCoop).makeSellPrice(14000);
    public static final Animal Cow = new Animal(ItemType.Cow, 1, List.of(AnimalProduct.Milk, AnimalProduct.BigMilk), ItemType.NormalBarn).makeSellPrice(1500);
    public static final Animal Sheep = new Animal(ItemType.Sheep, 2, List.of(AnimalProduct.SheepWool), ItemType.BigBarn).makeSellPrice(8000);
    public static final Animal Goat = new Animal(ItemType.Goat, 3, List.of(AnimalProduct.GoatMilk,AnimalProduct.BigGoatMilk), ItemType.DeluxeBarn).makeSellPrice(4000);
    public static final Animal Pig = new Animal(ItemType.Pig, 3, List.of(AnimalProduct.Truffle), ItemType.BigBarn).makeSellPrice(16000);


    public static final ArrayList<Animal> allAnimals = new ArrayList<>(){{
        add(Hen);
        add(Rabbit);
        add(Duck);
        add(Dino);
        add(Cow);
        add(Sheep);
        add(Goat);
        add(Pig);
    }};
}
