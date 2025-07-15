package com.stardew.GameLogic.Models;

import com.stardew.GameLogic.Enums.ItemType;
import com.stardew.GameLogic.Enums.WeatherType;
import com.stardew.GameLogic.Models.Game.App;
import com.stardew.GameLogic.Models.Items.Foragings.PlantAbleCrop;
import com.stardew.GameLogic.Models.Items.Foragings.Tree;
import com.stardew.GameLogic.Models.Items.Item;

import java.util.Random;

public class Weather {
    private WeatherType weather = WeatherType.SUNNY;
    private WeatherType tomorrowsWeather = WeatherType.SUNNY;

    public void weatherForecast(){
        if(new Random().nextInt(2) == 0)
            MessageManager.getMessage(Result.success(tomorrowsWeather.name()));
        else
            MessageManager.getMessage(Result.success(weather.name()));
    }

    public WeatherType getWeather() {
        return weather;
    }

    public void setWeather(WeatherType weather) {
        this.weather = weather;
    }

    public void setWeatherAccordingToSeason(){
        while (true) {
            WeatherType weatherType = WeatherType.values()[new Random().nextInt(4)];
            if(weatherType.getSeasons().contains(App.getGame().dateAndTime.getSeason())){
                tomorrowsWeather = weatherType;
                break;
            }
        }
    }

    public void update(){
        if(App.getGame().dateAndTime.isADayPassed()){
            int dx =App.getGame().getCurrentPlayer().getFarm().getPosition().getWidth();
            int dy =App.getGame().getCurrentPlayer().getFarm().getPosition().getHeight();
            int x =App.getGame().getCurrentPlayer().getFarm().getPosition().getX();
            int y =App.getGame().getCurrentPlayer().getFarm().getPosition().getY();

            for(int i = 0; i< 3; i++){
                int u = new Random().nextInt(dx) + x;
                int v= new Random().nextInt(dy) + y;
                thundering(u, v);
            }
            weather = tomorrowsWeather;
            setWeatherAccordingToSeason();
        }
    }
    public void thundering(int x, int y){
        Item item = App.getGame().findTile(x, y).getItem();
        if(item instanceof Tree || item instanceof PlantAbleCrop){
            App.getGame().findTile(x, y).setItem(App.getGame().getItemByItemType(ItemType.Coal));
        }
    }
}
