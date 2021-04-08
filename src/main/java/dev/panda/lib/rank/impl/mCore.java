package dev.panda.lib.rank.impl;

import me.abhi.core.CorePlugin;
import me.abhi.core.profile.CoreProfile;
import me.abhi.core.rank.Rank;
import org.bukkit.OfflinePlayer;

public class mCore implements dev.panda.lib.rank.Rank {

    public Rank getRank(OfflinePlayer player) {
        CoreProfile coreProfile = CorePlugin.getInstance().getProfileHandler().getCoreProfile(player.getUniqueId());
        try {
            return coreProfile.getRank();
        }
        catch (Exception ex) {
            return null;
        }
    }

    @Override
    public String getName(OfflinePlayer player) {
        return this.getRank(player) == null ? "None" : this.getRank(player).getName();
    }

    @Override
    public String getPrefix(OfflinePlayer player) {
        return this.getRank(player) == null ? "None" : this.getRank(player).getPrefix();
    }

    @Override
    public String getSuffix(OfflinePlayer player) {
        return this.getRank(player) == null ? "None" : this.getRank(player).getSuffix();
    }

    @Override
    public String getColor(OfflinePlayer player) {
        return this.getRank(player) == null ? "None" : this.getRank(player).getName();
    }
}
