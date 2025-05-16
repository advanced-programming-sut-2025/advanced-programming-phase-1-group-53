package Models.Items;

import Enums.ItemType;
import Enums.Season;
import Models.Game.App;
import Models.MessageManager;
import Models.Result;

import java.util.*;

public class Animal extends Item{
    private int friendship = 0;
    private boolean isPettedToday = false;
    private boolean isFedToday = false;
    private boolean isOut = false;
    private String name;
    private final int cycleOfProduceDuring;
    private int hoursToProduce;
    private int homeX;
    private int homeY;
    private ArrayList<AnimalProduct> producedProducts = new ArrayList<>();
    private List<AnimalProduct> animalProds = new ArrayList<>();
    private ItemType livingPlace;

    public Animal(ItemType itemType, int cycleOfProduce, List<AnimalProduct> animalProds, ItemType livingPlace) {
        super(itemType);
        this.cycleOfProduceDuring = cycleOfProduce;
        this.hoursToProduce = cycleOfProduce;
        this.animalProds.addAll(animalProds);
        this.livingPlace = livingPlace;
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
        isOut = out;
    }

    public String getName() {
        return name;
    }

    public int getCycleOfProduceDuring() {
        return cycleOfProduceDuring;
    }

    public int getHoursToProduce() {
        return hoursToProduce;
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

    @Override
    public Animal makeSellPrice(double price){
        baseSellPrice = price;
        return this;
    }

    @Override
    public void update(){
        hoursToProduce =(int) Math.max(0, hoursToProduce - App.getGame().dateAndTime.getPassedHours());
        if(hoursToProduce == 0){
            produce();
            hoursToProduce = cycleOfProduceDuring;
        }
        if(App.getGame().dateAndTime.isADayPassed()){
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
        int numOfProd = (int) ((friendship + 150 * new Random().nextDouble(0.5, 1.5) * 5) / 1500);
        numOfProd = Math.min(numOfProd, animalProds.size());
        producedProducts.add(animalProds.get(numOfProd).makeSellPrice(animalProds.get(numOfProd).getBaseSellPrice()*coeff));
    }

    public Animal clone(String name){
        Animal animal = new Animal(itemType, cycleOfProduceDuring, animalProds, livingPlace).makeSellPrice(baseSellPrice);
        animal.name = name;
        return animal;
    }

    public void pet(){
        //TODO: if next to animal
        friendship = Math.min(1000, friendship + 15);
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

    public static final Animal Hen = new Animal(ItemType.Hen, 24, List.of(AnimalProduct.Egg,  AnimalProduct.BigEgg), ItemType.NormalCoop).makeSellPrice(800);
    public static final Animal Rabbit = new Animal(ItemType.Rabbit, 4*24, List.of(AnimalProduct.RabbitLeg, AnimalProduct.RabbitWool), ItemType.BigCoop).makeSellPrice(8000);
    public static final Animal Duck = new Animal(ItemType.Duck, 2*24, List.of(AnimalProduct.DuckEgg, AnimalProduct.DuckFeather), ItemType.DeluxeCoop).makeSellPrice(1200);
    public static final Animal Dino = new Animal(ItemType.Dino, 7*24, List.of(AnimalProduct.DinoEgg), ItemType.DeluxeCoop).makeSellPrice(14000);
    public static final Animal Cow = new Animal(ItemType.Cow, 24, List.of(AnimalProduct.Milk, AnimalProduct.BigMilk), ItemType.NormalBarn).makeSellPrice(1500);
    public static final Animal Sheep = new Animal(ItemType.Sheep, 2*24, List.of(AnimalProduct.SheepWool), ItemType.BigBarn).makeSellPrice(8000);
    public static final Animal Goat = new Animal(ItemType.Goat, 3*24, List.of(AnimalProduct.GoatMilk,AnimalProduct.BigGoatMilk), ItemType.DeluxeBarn).makeSellPrice(4000);
    public static final Animal Pig = new Animal(ItemType.Pig, 3*24, List.of(AnimalProduct.Truffle), ItemType.BigBarn).makeSellPrice(16000);


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
