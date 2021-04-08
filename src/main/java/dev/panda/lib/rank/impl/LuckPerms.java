package dev.panda.lib.rank.impl;

import dev.panda.lib.rank.Rank;
import dev.panda.lib.rank.RankManager;
import org.bukkit.OfflinePlayer;

public class LuckPerms implements Rank {

    @Override
    public String getName(OfflinePlayer player) {
        return RankManager.getInstance().getChat().getPrimaryGroup(String.valueOf(
                RankManager.getInstance().getPlugin().getServer().getWorlds().get(0).getName()), player);
    }

    @Override
    public String getPrefix(OfflinePlayer player) {
        return RankManager.getInstance().getChat().getPlayerPrefix(String.valueOf(
                RankManager.getInstance().getPlugin().getServer().getWorlds().get(0).getName()), player);
    }

    @Override
    public String getSuffix(OfflinePlayer player) {
        return RankManager.getInstance().getChat().getPlayerSuffix(String.valueOf(
                RankManager.getInstance().getPlugin().getServer().getWorlds().get(0).getName()), player);
    }

    @Override
    public String getColor(OfflinePlayer player) {
        return RankManager.getInstance().getChat().getPrimaryGroup(String.valueOf(
                RankManager.getInstance().getPlugin().getServer().getWorlds().get(0).getName()), player);
    }
}
