package com.stardew.Views.TabMenus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.stardew.Main;
import com.stardew.Models.Game.App;
import com.stardew.Models.Game.GameAssetManager;
import com.stardew.Models.Items.Animal;
import com.stardew.Models.Items.AnimalProduct;
import com.stardew.Models.Items.CoopAndBarn;
import com.stardew.Models.Items.ShippingBin;
import com.stardew.Models.Product;
import com.stardew.Views.GameMenu;
import com.stardew.Views.Tab;

import java.util.ArrayList;

public class CoopMenu extends Tab {
    private static final float SCREEN_WIDTH = Gdx.graphics.getWidth();
    private static final float SCREEN_HEIGHT = Gdx.graphics.getHeight();
    private static final int START_X =(int) SCREEN_WIDTH/4;
    private static final int START_Y =(int) 10;
    private ArrayList<Animal> animals = new ArrayList<>();
    private ArrayList<Sprite> sprites = new ArrayList<>();
    private ArrayList<Animal> mustRemove = new ArrayList<>();
    private static final float MARGIN = 30;
    private static final float ROW_HEIGHT = 150;
    private static final float ROW_WIDTH = 1000;
    private boolean isChanged = true;
    private boolean showAll = true;
    private static final int NUM_OF_ITEMS_IN_A_PAGE = 5;
    private int currentPage  = 0;
    private TextButton nextPage;
    private TextButton previousPage;
    SelectBox selectBox;


    public void createRow(float x, float y, Animal animal){
        Sprite back = new Sprite(GameAssetManager.getBackgroundSprite());
        back.setSize(ROW_WIDTH*8/7, ROW_HEIGHT);
        back.setPosition(x, y);
        Sprite s = ((Animal)App.getGame().getItemByItemType(animal.getItemType())).getShowSprite();
        s.setSize(ROW_WIDTH/7, ROW_HEIGHT*4/5);
        s.setPosition(x, y+ROW_HEIGHT/10);
        if(animal.isOut()){
            back.setColor(0.3f, 0.3f, 0.3f, 1);
            s.setColor(0.3f, 0.3f, 0.3f, 1);
        }
        sprites.add(back);
        sprites.add(s);
        TextButton details = Tab.createTextButton(animal.details());
        details.setSize(0, 0);
        details.setPosition(x+2*ROW_WIDTH/7+20,y+20+ROW_HEIGHT/2);
        stage.addActor(details);
        TextButton purchase = Tab.createTextButton("move out");
        purchase.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //TODO packet

                for(CoopAndBarn coopAndBarn : App.getCurrentPlayer().backpack.getCoopsAndBarns()){
                    if(coopAndBarn.getAnimals().contains(animal)){
                        if(animal.isOut()){
                            coopAndBarn.getOutAnimals().remove(animal);
                        }
                        else {
                            animal.getSprite().setPosition(App.getCurrentPlayer().position.getX(),
                                App.getCurrentPlayer().position.getY());
                            coopAndBarn.getOutAnimals().add(animal);
                            animal.setOriginX(App.getCurrentPlayer().position.getX());
                            animal.setOriginY(App.getCurrentPlayer().position.getY());
                        }
                        animal.setOut(!animal.isOut());
                        isChanged = true;
                        return;
                    }
                }
            }
        });
        purchase.setSize(ROW_WIDTH/7, ROW_HEIGHT*4/5);
        purchase.setPosition(x+5*ROW_WIDTH/7, y+20);
        stage.addActor(purchase);
        TextButton collect = Tab.createTextButton("collect");
        collect.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //TODO packet

                for(AnimalProduct animalProduct : animal.getProducedProducts()){
                    App.getCurrentPlayer().backpack.addItem(animalProduct);
                }
                animal.getProducedProducts().clear();
                isChanged = true;
            }
        });
        collect.setSize(ROW_WIDTH/7, ROW_HEIGHT*4/5);
        collect.setPosition(x+7*ROW_WIDTH/7, y+20);
        stage.addActor(collect);
        TextButton sell = Tab.createTextButton("sell");
        sell.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //TODO packet

                ShippingBin.ShippingBin.getItems().put(animal, App.getCurrentPlayer());
                mustRemove.add(animal);
                isChanged = true;
            }
        });
        sell.setSize(ROW_WIDTH/7, ROW_HEIGHT*4/5);
        sell.setPosition(x+6*ROW_WIDTH/7, y+20);
        stage.addActor(sell);
    }

    @Override
    public void show(){
        selectBox =Tab.createSelectBox(new Array<>(){{
            add("show all");
            add("show availables");
        }});
        sprites = new ArrayList<>();
        super.show();
        Gdx.input.setInputProcessor(stage);
    }


    @Override
    public void render(float delta){
        if(selectBox != null) {
            boolean s = showAll;
            if(selectBox.getSelectedIndex() == 0)
                showAll = true;
            else
                showAll = false;
            if(showAll != s)
                isChanged = true;
        }
        ScreenUtils.clear(0, 0, 0, 1);
        Gdx.gl.glClearColor(0, 0, 0, 1);

        if (!batch.isDrawing()) {
            batch.begin();
        }
        for (Sprite s : sprites) {
            s.draw(batch);
        }
        if (batch.isDrawing()) {
            batch.end();
        }

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

        if(isChanged){
            for(Animal animal :mustRemove){
                animals.remove(animal);
            }
            mustRemove.clear();
            System.out.println("ppp");
            stage.clear();
            sprites.clear();
            TextButton back = Tab.createTextButton("back");
            back.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    //TODO packet

                    Main.main.setScreen(GameMenu.getInstance());
                }
            });
            back.setPosition(SCREEN_WIDTH/2-70, START_Y);
            back.setSize(140, 80);
            stage.addActor(back);
            float currentY = START_Y+100;
            for(int i = currentPage*NUM_OF_ITEMS_IN_A_PAGE; i<
                Math.min((currentPage+1)*NUM_OF_ITEMS_IN_A_PAGE, animals.size()); i++){
                Animal animal = animals.get(i);
                if(!showAll && animal.isOut())
                    continue;
                createRow(START_X,currentY,animal);
                currentY += (MARGIN + ROW_HEIGHT);
            }nextPage= Tab.createTextButton("next page");
            previousPage = Tab.createTextButton("previous page");

            nextPage.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    //TODO packet

                    if((currentPage+1)*NUM_OF_ITEMS_IN_A_PAGE < animals.size()) {
                        currentPage++;
                        isChanged = true;
                    }
                }
            });

            previousPage.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    //TODO packet

                    if(currentPage > 0) {
                        currentPage--;
                        isChanged = true;
                    }
                }
            });
            selectBox.setPosition(START_X, currentY);
            selectBox.setSize(ROW_WIDTH/2, ROW_HEIGHT/3);
            stage.addActor(selectBox);
            nextPage.setPosition(START_X+ROW_WIDTH+20, SCREEN_HEIGHT/2 +50);
            nextPage.setSize(200, 50);
            stage.addActor(nextPage);
            previousPage.setPosition(START_X+ROW_WIDTH+20, SCREEN_HEIGHT/2 -100);
            previousPage.setSize(200, 50);
            stage.addActor(previousPage);
            isChanged = false;
        }
    }

    public void setAnimals(ArrayList<Animal> animals){
        this.animals = new ArrayList<>();
        this.animals.addAll(animals);
    }

    public boolean isChanged() {
        return isChanged;
    }

    public void setChanged(boolean changed) {
        isChanged = changed;
    }
}
