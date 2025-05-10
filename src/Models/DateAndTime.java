package Models;

import Enums.Season;
import Models.Game.App;

public class DateAndTime {
    private long passedHours;
    private int hour;
    private int day;
    private Season season;
    private int year;
    private long lastUpdateTime;// in hour

    public DateAndTime(){

    }

    public long loopDuration(){
        return passedHours - lastUpdateTime;
    }

    public void updateTime(){
        if(App.getInstance().getGame().getNumOfTurn() == App.getInstance().getGame().getNumOfPlayers()) {
            App.getInstance().getGame().setNumOfTurn(0);
            hour ++;
            passedHours ++;
        }
        if(hour > 24){
            day += hour/24;
            hour = hour % 24;
        }
        if(day > 28){
            int seasonsPasses = day/28;
            day = day % 28;
            if(seasonsPasses + season.ordinal() > 3){
                year += (seasonsPasses + season.ordinal())/4;
                season = Season.values()[(seasonsPasses + season.ordinal())%4];
            }
        }
    }

    public void timeCheat(int hour){
        this.hour += hour;
        passedHours += hour;
    }

    public void setMorning(){
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

    }

    public boolean isADayPassed(){
        if((passedHours/24) > (lastUpdateTime/24))
            return true;
        return false;
    }

    public long getPassedHours() {
        return passedHours;
    }

    public int getHour() {
        return hour;
    }

    public int getDay() {
        return day;
    }

    public Season getSeason() {
        return season;
    }

    public int getYear() {
        return year;
    }

    public long getTime(){
        return passedHours;
    }

}
