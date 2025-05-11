package Models;

import Models.Items.Item;

public record Request(int numberOfGiveAwayItem, Item giveAwayItem, int numberOfRewardItem, Item rewardItem,
                      int goldReward) {
}
