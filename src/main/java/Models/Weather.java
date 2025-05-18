package Models;

import Enums.WeatherType;

public class Weather {
    private WeatherType weather = WeatherType.SUNNY;
    private WeatherType tomorrowsWeather = WeatherType.SUNNY;

    public WeatherType getWeather() {
        return weather;
    }

    public void setWeather(WeatherType weather) {
        this.weather = weather;
    }

    public void setWeatherAccordingToSeason(){

    }
    public void thundering(){

    }
}
