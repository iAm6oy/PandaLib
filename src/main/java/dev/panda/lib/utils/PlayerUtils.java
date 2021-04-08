package dev.panda.lib.utils;

import dev.panda.lib.chat.CC;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;

public class PlayerUtils {

    public static int getPing(Player player) {
        try {
            String a = Bukkit.getServer().getClass().getPackage().getName().substring(23);
            Class<?> b = Class.forName("org.bukkit.craftbukkit." + a + ".entity.CraftPlayer");
            Object c = b.getMethod("getHandle").invoke(player);
            return (int) c.getClass().getDeclaredField("ping").get(c);
        }
        catch (Exception e) {
            return 0;
        }
    }

    public static String getCountry(String IP) {
        URL url;
        BufferedReader in;
        String country = "";
        try {
            url = new URL("http://ip-api.com/json/" + IP + "?fields=country");
            in = new BufferedReader(new InputStreamReader(url.openStream()));
            country = in.readLine().trim();
            if (country.length() <= 0)
                try {
                    InetAddress ip = InetAddress.getLocalHost();
                    System.out.println(ip.getHostAddress().trim());
                    country = ip.getHostAddress().trim();
                } catch (Exception exp) {
                    country = "Not Found";
                }
        }
        catch (Exception ex) {
            CC.log("Error in check country!");
        }
        return country
                .replace("{", "")
                .replace("}", "")
                .replace("\"\"", "")
                .replace(":", "");
    }

    public static boolean isInventoryFull(Player player) {
        return player.getInventory().firstEmpty() < 0;
    }

    public static void decrement(Player player) {
        ItemStack itemStack = player.getItemInHand();
        if (itemStack.getAmount() <= 1) player.setItemInHand(new ItemStack(Material.AIR, 1));
        else itemStack.setAmount(itemStack.getAmount() - 1);
        player.updateInventory();
    }
}
