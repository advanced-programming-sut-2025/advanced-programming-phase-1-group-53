package Models;

import Enums.ItemType;
import Models.Game.App;
import Models.Game.Player;
import Models.Items.Item;

import java.util.HashSet;
import java.util.Set;

public class Friendship {
    private int xp = 0;
    private int level = 0; // 0: Stranger, 1: Friend, 2: Close Friend, 3: Lover, 4: Married

    // Special action flags
    private boolean bouquetGiven = false;
    private boolean proposalMade = false;

    public int getLevel() {
        return level;
    }

    public int getXP() {
        return xp;
    }

    public void levelUp() {
        switch (level) {
            case 0:
                if (xp >= 100) {
                    level = 1; // Friend
                    xp = 0;
                }
                break;
            case 1:
                if (xp >= 200) {
                    level = 2; // Close Friend
                    xp = 0;
                }
                break;
            case 2:
                if (xp >= 300 && !bouquetGiven) {
                    level = 3; // Lover
                    xp = 0;
                }
                break;
            case 3:
                if (xp >= 400 && !proposalMade) {
                    level = 4; // Married
                    xp = 0;
                }
                break;
        }
    }

    public void addXP(int amount) {
        xp += amount;
        levelUp();
    }

    public boolean isBouquetGiven() {
        return bouquetGiven;
    }

    public boolean isProposalMade() {
        return proposalMade;
    }

    public static void talk(String message, Player talker, Player listener) {
        System.out.println("you have a new message");
        listener.getConversation().get(talker).append(message).append("\n");
        listener.getFriendship().get(talker).addXP(20);
    }

    public static void talkHistory(Player me, Player other) {
        StringBuilder conversation = me.getConversation().get(other);
        if (conversation != null) {
            System.out.println("Conversation with " + other.personalInfo.getName() + ":");
            System.out.println(conversation.toString());
        } else {
            System.out.println("No conversation history with " + other.personalInfo.getName());
        }
    }

    public static void gifting(Player me, Player other, Item item) {
        if (other.getFriendship().get(me).getLevel() >= 1) {
            if (me.backpack.areItemsAvailable(item, 1)) {
                me.backpack.getItems().compute(item, (k, v) ->(v-1));
                other.backpack.addItem(item);
                System.out.println("You gifted " + item + " to " + other.personalInfo.getName());
                me.getGiftHistory().get(other).append(item).append("\n");
                other.getFriendship().get(me).addXP(50);
            }
            else {
                System.out.println("not enough items");
            }
        } else {
            System.out.println("You need to be friends to gift items.");
        }
    }

    public static void hugging(Player me, Player other) {
        if (other.getFriendship().get(me).getLevel() >= 2) {
            System.out.println("You hugged " + other.personalInfo.getName());
            me.getFriendship().get(other).addXP(30);
        } else {
            System.out.println("You need to be close friends to hug.");
        }
    }

    public static void bouquetGiving(Player me, Player other, Item flower) {
        if (other.getFriendship().get(me).getLevel() >= 2) {
            if (me.backpack.areItemsAvailable(flower, 1)) {
                me.backpack.getItems().compute(flower, (k, v) ->(v-1));
                other.backpack.addItem(flower);
                System.out.println("You gave a bouquet to " + other.personalInfo.getName());
                me.getFriendship().get(other).bouquetGiven = true;
                me.getFriendship().get(other).addXP(10);
            }
            else {
                System.out.println("not enough flowers");
            }
        } else {
            System.out.println("You need to be lovers to give a bouquet.");
        }
    }

    public static void proposalMade(Player me, Player other, Item ring) {
        if (other.getFriendship().get(me).getLevel() >= 3 && other.getFriendship().get(me).getXP() >= 400) {
            if (me.backpack.areItemsAvailable(ring, 1)) {
                if (me.personalInfo.getGender() == other.personalInfo.getGender()) {
                    System.out.println("Not in the islamic country!");
                    return;
                }
                me.backpack.getItems().compute(ring, (k, v) ->(v-1));
                other.backpack.addItem(ring);
                me.energy.updateEnergy(50);
                other.energy.updateEnergy(50);
                me.getFriendship().get(other).proposalMade = true;
                me.getFriendship().get(other).addXP(100);
                me.personalInfo.setCoupleEmail(other.personalInfo.getEmail());
                other.personalInfo.setCoupleEmail(me.personalInfo.getEmail());
                System.out.println("You proposed to " + other.personalInfo.getName());
            }
            else {
                System.out.println("zir vanak cancele");
            }
        }
        else {
            System.out.println("boro bache khosgel hanooz sibilet dar niaomade");
        }
    }
}
