package Models.Items.Foragings;

import Enums.ItemType;
import Enums.Season;
import Models.Game.App;
import Models.Items.Item;

import java.util.ArrayList;

public abstract class Plant extends Item {
    protected boolean hasDeluxe = false;
    protected boolean hasSpeed = false;

    Plant(ItemType itemType){
        super(itemType);
    }

    @Override
    public Plant makeEdible(double energy){
        this.energy = energy;
        return this;
    }

    void setEdible(){
        isEdible = true;
    }

    public boolean isEdible(){
        return isEdible;
    }

    public String details(){
        return "Name: " + itemType.name();
    }

    public boolean HasDeluxe() {
        return hasDeluxe;
    }

    public void setHasDeluxe(boolean hasDeluxe) {
        this.hasDeluxe = hasDeluxe;
    }

    public boolean HasSpeed() {
        return hasSpeed;
    }

    public void setHasSpeed(boolean hasSpeed) {
        this.hasSpeed = hasSpeed;
    }

    static final ArrayList<Season> specialSeasons = new ArrayList<>() {{
        add(Season.FALL);
        add(Season.SPRING);
        add(Season.SUMMER);
        add(Season.WINTER);
    }};
    static final ArrayList<Season> summer = new ArrayList<>(){{
        add(Season.SUMMER);
    }};
    static final ArrayList<Season> spring = new ArrayList<>(){{
        add(Season.SPRING);
    }};
    static final ArrayList<Season> fall = new ArrayList<>(){{
        add(Season.FALL);
    }};
    static final ArrayList<Season> winter = new ArrayList<>(){{
        add(Season.WINTER);
    }};
    static final ArrayList<Season> summerFall = new ArrayList<>(){{
        add(Season.SUMMER);
        add(Season.FALL);
    }};
    static final ArrayList<Season> springSummerFall = new ArrayList<>(){{
        add(Season.SPRING);
        add(Season.SUMMER);
        add(Season.FALL);
    }};
    static final ArrayList<Season> springSummer = new ArrayList<>(){{
        add(Season.SUMMER);
        add(Season.SPRING);
    }};
}
