package Enums;

import java.util.*;

public enum WeatherType {
    SUNNY(Set.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER)),
    RAINY(Set.of(Season.SPRING, Season.SUMMER, Season.FALL)),
    SNOWY(Set.of(Season.WINTER)),
    STORMY(Set.of(Season.SPRING, Season.SUMMER, Season.FALL));

    private final Set<Season> seasonsOfEachWeather;

    WeatherType(Set<Season> seasonsOfEachWeather) {
        this.seasonsOfEachWeather = seasonsOfEachWeather;
    }

    public Set<Season> getSeasons() {
        return seasonsOfEachWeather;
    }
}
