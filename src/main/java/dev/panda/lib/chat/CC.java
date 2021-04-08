package dev.panda.lib.chat;

import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

public class CC {

    public static String LINE = "&7&m----------------------------------------";

    public static String translate(String in) {
        return ChatColor.translateAlternateColorCodes('&', in);
    }

    public static List<String> translate(List<String> in) {
        return in.stream().map(CC::translate).collect(Collectors.toList());
    }

    public static String strip(String in) {
        return ChatColor.stripColor(in);
    }

    public static void sender(CommandSender sender, String in) {
        sender.sendMessage(translate(in));
    }

    public static void message(Player player, String in) {
        player.sendMessage(translate(in));
    }

    public static void broadcast(String in) {
        Bukkit.broadcastMessage(translate(in));
    }

    public static void log(String in) {
        Bukkit.getConsoleSender().sendMessage(translate(in));
    }

    public static String capitalize(String str) {
        return WordUtils.capitalize(str);
    }

    public static String toReadable(Enum<?> enu) {
        return WordUtils.capitalize(enu.name().replace("_", " ").toLowerCase());
    }
}
