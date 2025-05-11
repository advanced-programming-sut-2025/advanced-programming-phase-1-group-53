package Models.Game;

import Models.*;
import Models.Abilities.Abilities;

public class Player {
    public final Farm farm = new Farm();
    public final PersonalInfo personalInfo = new PersonalInfo();
    public final Abilities abilities = new Abilities();
    public final Backpack backpack = new Backpack();
    public final Energy energy = new Energy();
    public final Activity activity = new Activity();
    public final Position position = new Position(0, 0, 0, 0);

}
