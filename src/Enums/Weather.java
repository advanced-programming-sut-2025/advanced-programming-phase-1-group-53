package Enums;

import java.util.ArrayList;

import java.util.*;

public enum Weather {
    SUNNY(new ArrayList<>(Arrays.asList(Season.FALL, Season.SPRING, Season.SUMMER, Season.WINTER))),
    RAINY(new ArrayList<>(Arrays.asList(Season.FALL, Season.SPRING, Season.SUMMER))),
    SNOWY(new ArrayList<>(Arrays.asList(Season.WINTER))),
    STORMY(new ArrayList<>(Arrays.asList(Season.FALL, Season.SPRING, Season.SUMMER)));

    private final List<Season> seasonsOfEachWeather;

    Weather(List<Season> seasonsOfEachWeather) {
        this.seasonsOfEachWeather = seasonsOfEachWeather;
    }

    public List<Season> getSeasons() {
        return seasonsOfEachWeather;
    }
}
