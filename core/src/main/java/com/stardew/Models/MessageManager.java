package com.stardew.Models;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.stardew.Enums.MessageTypes;
import com.stardew.Views.Tab;
import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class MessageManager {
    private static final int START_X = 20;
    private static final int START_Y = 10;
    private static boolean isChanged = true;
    private final static int MAX_LINE_LENGTH = 27;
    private final static int LINE_MARGIN = 37;
    private static float FIRST_EMPTY_Y_COORDINATE = 0;
    private final static int CHARACTER_WIDTH = 12;
    private static TextButton SHOW_TILE_DETAIL_BUTTON = null;
    private static final LinkedHashMap<TextButton, Float> textButtons = new LinkedHashMap<>();
    private static StringBuilder output= new StringBuilder();
    public static void getMessage(Result result){
        output.append("[success: " + result.success() + ", message: "+ result.message() + "]");
        System.out.println(result.message());
        textButtons.put(createTextButton(result.message()), 3.5f);
        isChanged = true;
    }

    public static void getMessage(MessageTypes msgType, String msg){
    }

    public static void getMessage(MessageTypes msgType, String msg, float x, float y){
        TextButton textButton = createTextButton(msg);
        textButton.setPosition(x, y);
        textButtons.put(textButton, 0.1f);
        isChanged = true;
    }

    public static void setShowTileDetailButton(String txt, float x, float y){
        if(txt == null) {
            SHOW_TILE_DETAIL_BUTTON = null;
            isChanged = true;
            return;
        }
        SHOW_TILE_DETAIL_BUTTON = createTextButton(txt);
        SHOW_TILE_DETAIL_BUTTON.setPosition(x, y);
        isChanged = true;
    }

    public static String wrapText(String text, int maxLineLength) {
        StringBuilder wrappedText = new StringBuilder();
        String[] words = text.split(" ");

        int lineLength = 0;
        for (String word : words) {
            if (lineLength + word.length() > maxLineLength) {
                wrappedText.append("\n");
                lineLength = 0;
            } else if (lineLength > 0) {
                wrappedText.append(" ");
                lineLength += 1;
            }

            wrappedText.append(word);
            lineLength += word.length();
        }

        return wrappedText.toString();
    }


    public static TextButton createTextButton(String text) {
        Skin skin = Tab.skin;
        String aligned = wrapText(text, MAX_LINE_LENGTH);
        int lineNum = 1;
        for(byte character : aligned.getBytes()){
            if(character == '\n')
                lineNum ++;
        }
        TextButton textButton = Tab.createTextButton(aligned);
        textButton.setSize(CHARACTER_WIDTH*MAX_LINE_LENGTH, lineNum*LINE_MARGIN);
        textButton.setPosition(-1, -1);
        return textButton;
    }

    public static StringBuilder getOutput() {
        return output;
    }

    public static LinkedHashMap<TextButton, Float> getTextButtons(){
        LinkedHashMap<TextButton, Float> textButtonFloatHashMap = new LinkedHashMap<>(textButtons);
        FIRST_EMPTY_Y_COORDINATE = 0;
        for(TextButton textButton : textButtonFloatHashMap.keySet()){
            textButton.setPosition(START_X, START_Y + FIRST_EMPTY_Y_COORDINATE);
            FIRST_EMPTY_Y_COORDINATE += textButton.getHeight();
        }

        if(SHOW_TILE_DETAIL_BUTTON != null)
            textButtonFloatHashMap.put(SHOW_TILE_DETAIL_BUTTON, Float.MAX_VALUE);
        return textButtonFloatHashMap;
    }

    public static boolean isChanged() {
        return isChanged;
    }

    public static void setChanged(boolean changed) {
        isChanged = changed;
    }

    public static void update(float delta){
        ArrayList<TextButton> removals = new ArrayList<>();
        for(TextButton textButton : textButtons.keySet()){
            textButtons.compute(textButton, (k, v)-> v-delta);
            if(textButtons.get(textButton) <= 0){
                removals.add(textButton);
                isChanged = true;
            }
        }
        for(TextButton textButton : removals){
            textButtons.remove(textButton);
        }
    }
}
