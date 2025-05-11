package Models.Abilities;

import Enums.FishingRod;
import Models.Game.App;
import Models.Items.Fish;

import java.util.ArrayList;
import java.util.Random;

public class Fishing {
    private FishingRod fishingRod;
    private Random random = new Random();

    public void fishing(){
        if (fishingRod == null) {
            return;
        }
        float mIndex = 1.5F;
        int skill = App.getGame().players.get(0).abilities.getFishingLevel();
        ArrayList<Fish> fishes = new ArrayList<>();
        int numberOfFishCaught = (int)Math.ceil(random.nextFloat(0, 1) * mIndex * (skill + 2));
        float quality = random.nextFloat(0, 1) * (skill + 2) * fishingRod.getCoeff()/(7-mIndex);

        App.getInstance()
    }
}
