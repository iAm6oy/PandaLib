package dev.panda.lib.rank.impl;

import dev.panda.lib.rank.Rank;
import me.quartz.hestia.HestiaAPI;
import org.bukkit.OfflinePlayer;

public class HestiaCore implements Rank {

    @Override
    public String getName(OfflinePlayer player) {
        return HestiaAPI.instance.getRank(player.getUniqueId());
    }

    @Override
    public String getPrefix(OfflinePlayer player) {
        return HestiaAPI.instance.getRankPrefix(player.getUniqueId());
    }

    @Override
    public String getSuffix(OfflinePlayer player) {
        return HestiaAPI.instance.getRankSuffix(player.getUniqueId());
    }

    @Override
    public String getColor(OfflinePlayer player) {
        return HestiaAPI.instance.getRank(player.getUniqueId());
    }
}
