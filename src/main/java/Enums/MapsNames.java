package Enums;

import Models.Game.App;
import Models.Game.Player;
import Models.Tile;

public enum MapsNames {
    Farm1,
    Farm2,
    Farm3,
    Farm4,
    Village,
    House,
    Mine,
    GreenHouse;

    public static Tile[][] findMapByMapsName(MapsNames mapsName, Player player) {
        if (mapsName == Village || mapsName == Farm1 || mapsName == Farm2 || mapsName == Farm3 || mapsName == Farm4) {
            return App.getGame().getGameMap().getTiles();
        } else if (mapsName == Mine) {
            return player.getFarm().getMine().getBuildingMap();
        } else if (mapsName == GreenHouse) {
            return player.getFarm().getGreenHouse().getBuildingMap();
        } else if (mapsName == House) {
            return player.getFarm().getHouse().getBuildingMap();
        }
        return null;
    }
}
