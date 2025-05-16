package Models.Game;

import Enums.Gender;
import Enums.MapsNames;
import Models.*;
import Models.Abilities.Abilities;
import Models.Abilities.Activity;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Player {
    private Farm farm;
    public final PersonalInfo personalInfo;
    public final Abilities abilities = new Abilities();
    public final Backpack backpack = new Backpack();
    public final Energy energy = new Energy();
    public final Activity activity = new Activity();
    public final Position position = new Position(0, 0, 0, 0);
    private MapsNames currentMap = MapsNames.House;
    private final HashMap<Player, Integer> friendship = new HashMap<>();
    private final HashMap<Player, StringBuilder> conversation = new HashMap<>();
    public final FoodBuff foodBuff = new FoodBuff();

    public Player(String name, String password, String email, Gender gender) {
        this.personalInfo = new PersonalInfo(name, password, email, gender);
        /*for (Player player : App.getGame().players) {
            this.friendship.put(player, 0);
            this.conversation.put(player, new StringBuilder());
        }*/
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



    public void changeFriendship(Player player, int xp) {
        Player currentPlayer = this;
        currentPlayer.friendship.put(player, currentPlayer.friendship.get(player) + xp);
        player.friendship.put(currentPlayer, player.friendship.get(currentPlayer) + xp);
    }

    public int getFriendshipLevel(Player player) {
        int xp = this.friendship.get(player);
        return (xp / 100) - 1;
    }

    public HashMap<Player, StringBuilder> getConversation() {
        return conversation;
    }

    /**
     * Moves the player to the specified destination using shortest path (BFS).
     * Returns the energy cost of the move, or Double.MAX_VALUE if no path exists.
     */
    public int moveTo(int destX, int destY) {
        Tile[][] map = App.getGame().getCurrentMap();
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

                boolean isDoor = nextTile.getTileKind() == Enums.TileKind.door;
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
        int currentEnergy = energy.getEnergy();
        if (currentEnergy > energyCost) {
            energy.setEnergy(currentEnergy - energyCost);
            position.setX(destX);
            position.setY(destY);
        } else if (currentEnergy == energyCost) {
            energy.setEnergy(0);
            position.setX(destX);
            position.setY(destY);
            faint();
        } else {
            energy.setEnergy(0);
            faint();
        }
    }

    private void faint() {
        // To be implemented
    }


}
