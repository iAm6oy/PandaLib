package dev.panda.lib.rank.impl;

import com.broustudio.MizuAPI.MizuAPI;
import dev.panda.lib.rank.Rank;
import org.bukkit.OfflinePlayer;

public class Mizu implements Rank {

    public String getRank(OfflinePlayer player) {
        return MizuAPI.getAPI().getRank(player.getUniqueId());
    }

    @Override
    public String getName(OfflinePlayer player) {
        return this.getRank(player);
    }

    @Override
    public String getPrefix(OfflinePlayer player) {
        return MizuAPI.getAPI().getRankPrefix(this.getRank(player));
    }

    @Override
    public String getSuffix(OfflinePlayer player) {
        return MizuAPI.getAPI().getRankSuffix(this.getRank(player));
    }

    @Override
    public String getColor(OfflinePlayer player) {
        return MizuAPI.getAPI().getRankColor(this.getRank(player));
    }
}
