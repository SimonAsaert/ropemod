package tld.sima.ropemod.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Tool {
    public static ItemStack getDefaultTool(){
        ItemStack tool = new ItemStack(Material.STICK);
        ItemMeta itemM = tool.getItemMeta();
        assert itemM != null;
        itemM.setDisplayName(ChatColor.DARK_GREEN + "Rope Tool");

        List<String> toolL = new ArrayList<>();
        toolL.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "Summons ropes");

        itemM.setLore(toolL);
        itemM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        tool.setItemMeta(itemM);
        return tool;
    }

    public static ItemStack getDeleteTool(){
        ItemStack tool = new ItemStack(Material.STICK);
        ItemMeta itemM = tool.getItemMeta();
        assert itemM != null;
        itemM.setDisplayName(ChatColor.DARK_RED + "Rope Delete");

        List<String> toolL = new ArrayList<>();
        toolL.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "Deletes ropes");

        itemM.setLore(toolL);
        itemM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        tool.setItemMeta(itemM);
        return tool;
    }
}
