package Models;

import Models.Items.Item;

public record Request(int numberOfGiveAwayItem, Item giveAwayItem, int numberOfRewardItem, Item rewardItem,
                      int goldReward) {
    public String toString() {
        StringBuilder message = new StringBuilder();
        message.append("give away item: ");
        if(giveAwayItem != null) message.append(giveAwayItem.getItemType());
        else message.append("None");
        message.append(" reward item: ");
        if(rewardItem != null) message.append(rewardItem.getItemType());
        else message.append("None");
        message.append(" gold reward: ").append(goldReward).append("\n");
        return message.toString();
    }
}
