package com.stardew.Models;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.stardew.Enums.ItemType;
import com.stardew.Enums.WeatherType;
import com.stardew.Models.Game.App;
import com.stardew.Models.Items.Foragings.ForagingMineral;
import com.stardew.Models.Items.Foragings.ForagingTree;
import com.stardew.Models.Items.Foragings.PlantAbleCrop;
import com.stardew.Models.Items.Foragings.Tree;
import com.stardew.Models.Items.Item;

import java.util.ArrayList;
import java.util.Random;

public class Weather {
    private ArrayList<Sprite> weatherSprites = new ArrayList<>();
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

    private void reCreateWeatherSprites(){

    }

    public void update(float delta){
        for(Sprite sprite : weatherSprites){

        }
        if(App.getGame().dateAndTime.isADayPassed()){
            int dx =App.getGame().getCurrentPlayer().getFarm().getPosition().getWidth();
            int dy =App.getGame().getCurrentPlayer().getFarm().getPosition().getHeight();
            int x =App.getGame().getCurrentPlayer().getFarm().getPosition().getX();
            int y =App.getGame().getCurrentPlayer().getFarm().getPosition().getY();

            for(int i = 0; i< 3; i++){
                System.out.println("ajdey");
                int u = new Random().nextInt(dx) + x;
                int v= new Random().nextInt(dy) + y;
                thundering(u, v);
            }
            reCreateWeatherSprites();
            setWeatherAccordingToSeason();
            weather = tomorrowsWeather;
        }
    }

    public ArrayList<Sprite> getWeatherSprites() {
        return weatherSprites;
    }

    public WeatherType getTomorrowsWeather() {
        return tomorrowsWeather;
    }

    public void thundering(int x, int y){
        Item item = App.getGame().getGameMap().getTiles()[y][x].getItem();
        if(item instanceof Tree){
            ((Tree)App.getGame().getGameMap().getTiles()[y][x].getItem()).setThundered(true);
        }
        if(item instanceof PlantAbleCrop){

        }
        if(item instanceof ForagingTree){
            ((ForagingTree)App.getGame().getGameMap().getTiles()[y][x].getItem()).setThundered(true);
        }
    }
}
