package com.stardew.Models;

import com.stardew.Enums.Season;
import com.stardew.Models.Game.App;
import com.stardew.Models.Game.Player;
import com.stardew.Views.GameMenu;

public class DateAndTime {
    private final static int REAL_SECOND_TO_GAME_SECOND_COEFFICIENT = 12;
    private float passedHours;
    private float hour;
    private int day;
    private Season season;
    private int year;
    private long lastUpdateTime;
    private boolean isADayPassed = false;// in hour

    public DateAndTime(){
        day = 1;
        year =1;
        season = Season.SPRING;
        passedHours = 9;
        lastUpdateTime = 9;
        hour = 9;
    }

    public void updateTime(){
        if(hour > 24){
            isADayPassed = true;
            day += (int)hour/24;
            hour = hour % 24;
        }
        if(day > 28){
            int seasonsPasses = day/28;
            day = day % 28;
            if(seasonsPasses + season.ordinal() > 3){
                year += (seasonsPasses + season.ordinal())/4;
            }
            season = Season.values()[(seasonsPasses + season.ordinal())%4];
        }
        if(18<hour && hour<24){
            GameMenu.getInstance().setGettingDark(true);
        }
        else if(6<hour && hour<14){
            GameMenu.getInstance().setGettingLight(true);
        }
        lastUpdateTime++;
    }

    public static float convertRealSecondToGameMinute(float second){
        return second/REAL_SECOND_TO_GAME_SECOND_COEFFICIENT;
    }

    public void timeCheat(float hour){
        if(hour <= 0){
            return;
        }
        this.hour += hour;
        passedHours += hour;
    }

    public void setMorning(){
        for(Player player : App.getGame().players){
            player.energy.setEnergy(player.energy.getMaxEnergy());
        }
        if(hour < 9){
            passedHours += (hour -9);
            hour = 9;
        }
        if(hour > 9){
            passedHours += (24 - hour + 9);
            hour = 9;
            day ++;
        }
    }

    public void showTime(){
        MessageManager.getMessage(Result.success("Hour : " + hour));
    }

    public void showDay(){
        int y = day%7;
        String s = switch (y){
            case 0 -> "Sunday";
            case 1 -> "Monday";
            case 2 -> " Thursday";
            case 3 -> "Wednesday";
            case 4 -> "Thursday";
            case 5 -> "Friday";
            case 6 -> "Saturday";
            default -> "ajnsc";
        };
        MessageManager.getMessage(Result.success(s));
    }

    public void showSeason(){
        MessageManager.getMessage(Result.success("Season : " + season.name()));
    }

    public void showDate(){
        MessageManager.getMessage(Result.success("Year : " + year + "\nDay : " + day));
    }

    public void showDateAndTime(){
        MessageManager.getMessage(Result.success("Hour : " + hour));
    }

    public boolean isADayPassed(){
        return isADayPassed;
    }

    public float getDiff() {
        return passedHours - lastUpdateTime;
    }

    public float getHour() {
        return hour;
    }

    public int getDay() {
        return day;
    }

    public int getAllDaysPassed(){
        int seasons = (year-1) * 4;
        seasons += season.ordinal();
        return day+seasons*28;
    }

    public boolean isTimeToSleep(){
        if(hour>22 || hour < 6){
            return true;
        }
        return false;
    }

    public Season getSeason() {
        return season;
    }

    public int getYear() {
        return year;
    }

    public long getTime(){
        return lastUpdateTime;
    }

    public void setADayPassed(boolean ADayPassed) {
        isADayPassed = ADayPassed;
    }
}
