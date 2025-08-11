package com.stardew.Models.Game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.stardew.Controllers.GameMenuController;
import com.stardew.Enums.Gender;
import com.stardew.Enums.MapsNames;
import com.stardew.Enums.TileKind;
import com.stardew.Models.*;
import com.stardew.Models.Abilities.Abilities;
import com.stardew.Models.Abilities.Activity;
import com.stardew.Models.Items.Item;
import com.stardew.Views.GameMenu;
import org.w3c.dom.Text;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Player {
    private float lastTimeUpdatedSprite = 0;


    private Sprite sprite = new Sprite(GameAssetManager.getAlexTextures()[0][0]);
    private int direction = 0;//front-right-behind-left
    private boolean isIdle = true;
    private int indexOfSprite = 0;
    private Farm farm;
    public final PersonalInfo personalInfo;
    public final Abilities abilities = new Abilities();
    public final Backpack backpack = new Backpack();
    public final Energy energy = new Energy();
    public final Activity activity = new Activity();
    public final Position position = new Position(0, 0, 1, 1);
    public final Position secondaryPosition = new Position(0, 0, 0, 0);
    private boolean isInBuilding = false;
    private MapsNames currentMap = null;
    private MapsNames myFarm = null;
    private final HashMap<Player, Friendship> friendship = new HashMap<>();
    private final HashMap<Player, StringBuilder> conversation = new HashMap<>();
    private final HashMap<Player, StringBuilder> giftHistory = new HashMap<>();
    public final FoodBuff foodBuff = new FoodBuff();
    public final HashMap<NPC, Integer> NPCsFriendship = new HashMap<>();
    public final ArrayList<Item> gifts = new ArrayList<>();
    public int level = 2;
    private final ArrayList<Lobby> lobbies = new ArrayList<>();
    private Lobby currentLobby = null;

    public Player(String name, String nickName, String password, String email, Gender gender) {
        this.personalInfo = new PersonalInfo(email, name, nickName, password, gender);
    }

    public Player(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }

    public Player setSprite(Texture texture){
        this.sprite.setTexture(texture);
        return this;
    }

    public void update(float delta){
        backpack.update(delta);
        if(!isIdle){
            if(indexOfSprite == 0) {
                indexOfSprite = 1;
                lastTimeUpdatedSprite = 0;
            }
            else if((GameMenu.getTotalTimeSpent()-lastTimeUpdatedSprite) >= App.TAKING_STEP_TIME_GAP){
                indexOfSprite = (indexOfSprite % 2) + 1;
                lastTimeUpdatedSprite = GameMenu.getTotalTimeSpent();
            }
            if(!GameMenuController.mvc.canPlayerMove(direction)){
                isIdle = true;
                return;
            }
            if(direction == 0)
                position.changeY(-App.ADVANCE_OF_EACH_STEP);
            if(direction == 1)
                position.changeX(App.ADVANCE_OF_EACH_STEP);
            if(direction == 2)
                position.changeY(App.ADVANCE_OF_EACH_STEP);
            if(direction == 3)
                position.changeX(-App.ADVANCE_OF_EACH_STEP);
            energy.updateEnergy(-(int) (energy.getMaxEnergy()*0.00005));
        }
        if(isIdle){
            indexOfSprite = 0;
        }
    }

    public Sprite getSprite(){
        sprite = new Sprite(GameAssetManager.getAlexTextures()[direction][indexOfSprite]);
        sprite.setX(position.getX() - GameMenuController.getPrintStartX());
        sprite.setY(position.getY() - GameMenuController.getPrintStartY());
        sprite.setSize((float) (32 * 1.5), (float) (64*1.5));
        return sprite;
    }

    public boolean isIdle() {
        return isIdle;
    }

    public void setIdle(boolean idle) {
        isIdle = idle;
    }

    public int getDirection() {
        return direction;
    }

    public void setIndexOfSprite(int indexOfSprite) {
        this.indexOfSprite = indexOfSprite;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    public MapsNames getCurrentMap() {
        return currentMap;
    }

    public void setCurrentMap(MapsNames currentMap) {
        this.currentMap = currentMap;
    }

    public MapsNames getMyFarm() {
        return myFarm;
    }

    public void setMyFarm(MapsNames myFarm) {
        this.myFarm = myFarm;
    }

    public HashMap<Player, StringBuilder> getConversation() {
        return conversation;
    }

    public HashMap<Player, Friendship> getFriendship() {
        return friendship;
    }

    public Vector2 getDirectionVector(){
        if(direction == 0)
            return new Vector2(0, -1);
        if(direction == 1)
            return new Vector2(1, 0);
        if(direction == 2)
            return new Vector2(0, 1);
        if(direction == 3)
            return new Vector2(-1, 0);
        return new Vector2(0, 0);
    }

    public HashMap<Player, StringBuilder> getGiftHistory() {
        return giftHistory;
    }

    public void changeNPCsFriendship(int amount, NPC npc) {
        this.NPCsFriendship.put(npc, this.NPCsFriendship.get(npc) + amount);
    }

    public int calculateNPCsFriendship(NPC npc) {
        int friendshipXP = NPCsFriendship.get(npc);
        return Math.min(friendshipXP, 800) / 200;
    }

    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    /**
     * Moves the player to the specified destination using shortest path (BFS).
     * Returns the energy cost of the move, or Double.MAX_VALUE if no path exists.
     */

    private void teleport() {
        MapsNames currentMap = this.currentMap;

        if (currentMap == MapsNames.Farm1 || currentMap == MapsNames.Farm2 ||
            currentMap == MapsNames.Farm3 || currentMap == MapsNames.Farm4) {

            ArrayList<Position> villageDoors = App.getGame().getGameMap().getVillageDoors();
            if (villageDoors == null || villageDoors.isEmpty()) return;

            int randomIndex = ThreadLocalRandom.current().nextInt(villageDoors.size());
            Position des = villageDoors.get(randomIndex);
            this.position.setX(des.getX());
            this.position.setY(des.getY());
            this.currentMap = MapsNames.Village;

        } else if (currentMap == MapsNames.Village) {

            ArrayList<Position> farmDoors = this.farm.getDoorPositions();
            if (farmDoors == null || farmDoors.isEmpty()) return;

            int randomIndex = ThreadLocalRandom.current().nextInt(farmDoors.size());
            Position des = farmDoors.get(randomIndex);
            this.position.setX(des.getX());
            this.position.setY(des.getY());
            this.currentMap = this.myFarm;
        }
    }

    public int moveTo(int destX, int destY) {
        Tile[][] map = App.getGame().getCurrentMap();
        if (map[destY][destX].getTileKind() == TileKind.structure && this.currentMap == this.myFarm) {
            moveToBuilding(destX, destY);
            return -1;
        }
        else if (map[destY][destX].getTileKind() != TileKind.empty && map[destY][destX].getTileKind() != TileKind.asphalt &&
            map[destY][destX].getTileKind() != TileKind.door && map[destY][destX].getTileKind() != TileKind.grass &&
            map[destY][destX].getTileKind() != TileKind.plowed) {
            return Integer.MAX_VALUE;
        }
        int rows = map.length;
        int cols = map[0].length;

        int startX = position.getX();
        int startY = position.getY();

        // Directions: 8 directions (dx, dy)
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        class Node {
            int x, y;
            int prevDir; // direction index from previous move
            int turns;
            int steps;
            boolean passedDoor;
            Node(int x, int y, int prevDir, int turns, int steps, boolean passedDoor) {
                this.x = x; this.y = y; this.prevDir = prevDir;
                this.turns = turns; this.steps = steps; this.passedDoor = passedDoor;
            }
        }

        boolean[][] visited = new boolean[rows][cols];
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(startX, startY, -1, 0, 0, false));
        visited[startY][startX] = true;

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.x == destX && node.y == destY) {
                // Path found, calculate energy cost
                return ((node.steps + 10 * node.turns)) / 20;
            }
            for (int dir = 0; dir < 8; dir++) {
                int nx = node.x + dx[dir];
                int ny = node.y + dy[dir];
                if (nx < 0 || ny < 0 || nx >= cols || ny >= rows) continue;
                if (visited[ny][nx]) continue;
                Tile nextTile = map[ny][nx];
                if (!nextTile.getTileKind().isWalkable()) continue;

                boolean isDoor = nextTile.getTileKind() == TileKind.door;
                boolean passedDoor = node.passedDoor || isDoor;

                int newTurns = node.turns;
                if (node.prevDir != -1 && node.prevDir != dir) {
                    // Direction changed (turn)
                    newTurns++;
                }
                boolean isDiagonal = (dx[dir] != 0 && dy[dir] != 0);

                queue.add(new Node(nx, ny, dir, newTurns, node.steps + 1, passedDoor));
                visited[ny][nx] = true;
            }
        }
        // No valid path found
        return Integer.MAX_VALUE;
    }

    public void applyMovementCost(int energyCost, int destX, int destY) {
        if (energyCost == -1) return;
        int currentEnergy = energy.getEnergy();
        if (currentEnergy > energyCost) {
            energy.setEnergy(currentEnergy - energyCost);
            position.setX(destX);
            position.setY(destY);
        } else if (currentEnergy == energyCost) {
            energy.setEnergy(0);
            position.setX(destX);
            position.setY(destY);
            App.getCurrentPlayer().energy.update();
        } else {
            energy.setEnergy(0);
            App.getCurrentPlayer().energy.update();
        }
        Tile currentTile = App.getGame().getCurrentMap()[position.getY()][position.getX()];
        // If on a door tile
        if (currentTile.getTileKind() == TileKind.door) {
            // If we are in a building map (House, GreenHouse, Mine), teleport to farm
            MapsNames mapName = this.currentMap;
            if (mapName == MapsNames.House || mapName == MapsNames.GreenHouse || mapName == MapsNames.Mine) {
                // Teleport to farm: pick a random farm door
                ArrayList<Position> farmDoors = this.farm.getDoorPositions();
                if (farmDoors != null && !farmDoors.isEmpty()) {
                    Position farmDoor = farmDoors.get(ThreadLocalRandom.current().nextInt(farmDoors.size()));
                    this.position.setX(farmDoor.getX());
                    this.position.setY(farmDoor.getY());
                    this.currentMap = this.myFarm;
                    App.getGame().setCurrentMap(App.getGame().getGameMap().getTiles());
                }
            } else {
                // Normal teleport between farm and village
                teleport();
            }
        }
    }

    private void moveToBuilding(int x, int y) {
        Player player = App.getGame().getCurrentPlayer();
        Farm farm = player.getFarm();
        if (GameMap.isInside(x, y, farm.getHouse().getPosition())) {
            App.getGame().setCurrentMap(farm.getHouse().getBuildingMap());
            player.setCurrentMap(MapsNames.House);
            player.energy.updateEnergy(-5);
            player.position.setX(3);
            player.position.setY(3);
        } else if (GameMap.isInside(x, y, farm.getGreenHouse().getPosition())) {
            if (farm.getGreenHouse().isBuild()) {
                App.getGame().setCurrentMap(farm.getGreenHouse().getBuildingMap());
                player.setCurrentMap(MapsNames.GreenHouse);
                player.energy.updateEnergy(-5);
                player.position.setX(3);
                player.position.setY(3);
            }
            else {
                System.out.println("GreenHouse is not build yet");
                return;
            }
        }
//        else if (GameMap.isInside(x, y, farm.getMine().getPosition())) {
//            App.getGame().setCurrentMap(farm.getMine().getBuildingMap());
//            player.setCurrentMap(MapsNames.Mine);
//            player.energy.updateEnergy(-5);
//            player.position.setX(3);
//            player.position.setY(3);
//        }
        else if (GameMap.isInside(x, y, farm.getLake().getPosition())) {
            System.out.println("you cant go to the lake");
            return;
        }
    }

    public static void initializePlayerRelations(List<Player> players) {
        for (Player p1 : players) {
            for (Player p2 : players) {
                if (p1 != p2) {
                    // Friendship: ensure both directions have the same Friendship object
                    if (!p1.getFriendship().containsKey(p2)) {
                        Friendship sharedFriendship = new Friendship();
                        p1.getFriendship().put(p2, sharedFriendship);
                        p2.getFriendship().put(p1, sharedFriendship);
                    }
                    // Conversation
                    if (!p1.getConversation().containsKey(p2)) {
                        p1.getConversation().put(p2, new StringBuilder());
                    }
                    if (!p2.getConversation().containsKey(p1)) {
                        p2.getConversation().put(p1, new StringBuilder());
                    }
                    // Gift history
                    if (!p1.getGiftHistory().containsKey(p2)) {
                        p1.getGiftHistory().put(p2, new StringBuilder());
                    }
                    if (!p2.getGiftHistory().containsKey(p1)) {
                        p2.getGiftHistory().put(p1, new StringBuilder());
                    }
                }
            }
            for (NPC npc : new NPC[]{NPC.Sebastian, NPC.Abigail, NPC.Harvey, NPC.Lia, NPC.Robin}) {
                p1.NPCsFriendship.put(npc, 0);
            }
        }
    }

    public boolean isInBuilding() {
        return isInBuilding;
    }

    public void setInBuilding(boolean inBuilding) {
        isInBuilding = inBuilding;
    }

    public ArrayList<Lobby> getLobbies() {
        return lobbies;
    }

    public Lobby getCurrentLobby() {
        return currentLobby;
    }

    public void setCurrentLobby(Lobby currentLobby) {
        this.currentLobby = currentLobby;
    }

    public static Result createPlayer(String name, String nickName, String password, String email, Gender gender) {
        Player player = new Player(name, nickName, password, email, gender);
        App.getInstance().getPlayers().add(player);
        return new Result(true, "Player created");
    }

    public String getUsername() {
        return personalInfo.getName();
    }
}

