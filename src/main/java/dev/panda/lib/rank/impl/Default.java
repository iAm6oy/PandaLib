package dev.panda.lib.rank.impl;

import dev.panda.lib.rank.Rank;
import org.bukkit.OfflinePlayer;

public class Default implements Rank {

    @Override
    public String getName(OfflinePlayer player) {
        return "Default";
    }

    @Override
    public String getPrefix(OfflinePlayer player) {
        return "Default";
    }

    @Override
    public String getSuffix(OfflinePlayer player) {
        return "Default";
    }

    @Override
    public String getColor(OfflinePlayer player) {
        return "Default";
    }
}
