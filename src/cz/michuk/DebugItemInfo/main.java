/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.michuk.DebugItemInfo;

import java.util.List;
import java.util.Map;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author MICHUK
 */
public class main extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("DebugItemInfo enabled!");
    }

    @Override

    public void onDisable() {
        getLogger().info("DebugItemInfo disabled!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cPříkaz je pouze pro hráče!");
            return true;
        }
        Player player = (Player) sender;
        if (sender.hasPermission("debugiteminfo.use")) {
            ItemStack item = player.getEquipment().getItemInMainHand();
            if(item == null){
                player.sendMessage("§7Musíš mít nějaký item v ruce.");
                return true;
            }
            ItemMeta im = player.getInventory().getItemInMainHand().getItemMeta();
            Material itemType = player.getInventory().getItemInMainHand().getType();
            ItemStack itemStack = player.getInventory().getItemInMainHand();
            player.sendMessage(" ");
            if (im.hasDisplayName()) {
                player.sendMessage("DisplayName: '" + im.getDisplayName().replaceAll("§", "&") + "'");
            } else {
                player.sendMessage("Nemá displayName");
            }
            player.sendMessage("Material: " + itemType.toString());
            player.sendMessage("Amount: " + itemStack.getAmount());
            player.sendMessage("Max stack size: " + itemType.getMaxStackSize());
            player.sendMessage("Durability: " + itemStack.getDurability() + "/" + itemType.getMaxDurability());
            player.sendMessage("isUnbreakable: " + im.isUnbreakable());
            player.sendMessage("block: " + itemType.isBlock());
            player.sendMessage("isBurnable: " + itemType.isBurnable());
            player.sendMessage("isEdible: " + itemType.isEdible());
            player.sendMessage("isFlammable: " + itemType.isFlammable());
            player.sendMessage("isFuel: " + itemType.isFuel());
            //p.sendMessage("isItem" + itemType.isItem()); //TODO library
            player.sendMessage("isOccluding: " + itemType.isOccluding());
            player.sendMessage("isRecord: " + itemType.isRecord());
            player.sendMessage("isSolid: " + itemType.isSolid());
            player.sendMessage("isTransparent: " + itemType.isTransparent());

            if (im.hasLore()) {
                List lore = im.getLore();
                player.sendMessage("---==== LORE ====---");
                for (int i = 0; i < lore.size(); i++) {
                    player.sendMessage(i + 1 + ". lore: '" + lore.get(i).toString().replaceAll("§", "&") + "'");
                }

            }
            player.sendMessage("---==== ItemFlag ====---");
            for (ItemFlag iflag : ItemFlag.values()) {
                player.sendMessage(iflag + ": " + im.hasItemFlag(iflag));
            }
            if (im.hasEnchants()) {
                player.sendMessage("---==== ENCHANTY ====---");
                Map<Enchantment, Integer> enchants = im.getEnchants();
                for (Enchantment ench : enchants.keySet()) {
                    player.sendMessage(ench.getName() + " " + enchants.get(ench));
                }
            }
        } else {
            sender.sendMessage("§cNemáš oprávnění použít tento příkaz!");
        }
        return false;
    }
}
