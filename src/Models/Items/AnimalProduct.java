package Models.Items;

import Enums.ItemType;

public class AnimalProduct extends Item{

    private AnimalProduct(ItemType itemType){
        super(itemType);
    }

    public static final AnimalProduct Egg = new AnimalProduct(ItemType.Egg);
    public static final AnimalProduct BigEgg = new AnimalProduct(ItemType.BigEgg);
    public static final AnimalProduct DockEgg = new AnimalProduct(ItemType.DockEgg);
    public static final AnimalProduct DockFeather = new AnimalProduct(ItemType.DockFeather);
    public static final AnimalProduct RabbitWool = new AnimalProduct(ItemType.RabbitWool);
    public static final AnimalProduct RabbitLeg = new AnimalProduct(ItemType.RabbitLeg);
    public static final AnimalProduct DinoEgg = new AnimalProduct(ItemType.DinoEgg);
    public static final AnimalProduct CowMilk = new AnimalProduct(ItemType.CowMilk);
    public static final AnimalProduct BigCowMilk = new AnimalProduct(ItemType.BigCowMilk);
    public static final AnimalProduct GoatMilk = new AnimalProduct(ItemType.GoatMilk);
    public static final AnimalProduct BigGoatMilk = new AnimalProduct(ItemType.BigGoatMilk);
    public static final AnimalProduct SheepWool = new AnimalProduct(ItemType.SheepWool);
    public static final AnimalProduct Truffle = new AnimalProduct(ItemType.Truffle);
}
