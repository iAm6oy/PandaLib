package dev.panda.lib.menu.pagination;

import dev.panda.lib.menu.Button;
import dev.panda.lib.menu.Menu;
import dev.panda.lib.menu.buttons.BackButton;
import lombok.Getter;
import lombok.NonNull;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;

public class ViewAllPagesMenu extends Menu {

    @NonNull @Getter PaginatedMenu menu;

    public ViewAllPagesMenu(Plugin plugin) {
        super(plugin);
    }

    @Override
    public String getTitle(Player player) {
        return "Jump to page";
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        HashMap<Integer, Button> buttons = new HashMap<>();

        buttons.put(0, new BackButton(menu));

        int index = 10;

        for (int i = 1; i <= menu.getPages(player); i++) {
            buttons.put(index++, new JumpToPageButton(i, menu, menu.getPage() == i));

            if ((index - 8) % 9 == 0) {
                index += 2;
            }
        }

        return buttons;
    }

    @Override
    public boolean isAutoUpdate() {
        return true;
    }
}
