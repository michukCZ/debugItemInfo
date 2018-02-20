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
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author MICHUK
 */
public class main extends JavaPlugin implements Listener {

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
        if (cmd.getName().equalsIgnoreCase("debugitem")) {
            if (sender instanceof Player) {
                if (sender.hasPermission("debugiteminfo.use")) {
                    Player p = (Player) sender;
                    ItemMeta im = p.getInventory().getItemInMainHand().getItemMeta();
                    Material itemType = p.getInventory().getItemInMainHand().getType();
                    ItemStack itemStack = p.getInventory().getItemInMainHand();
                    p.sendMessage(" ");
                    if (im.hasDisplayName()) {
                        p.sendMessage("Jméno: '" + im.getDisplayName().replaceAll("§", "&") + "'");
                    }
                    p.sendMessage("Material: " + itemType.toString());
                    p.sendMessage("Amount: " + itemStack.getAmount());
                    p.sendMessage("Max stack size: " + itemType.getMaxStackSize());
                    p.sendMessage("Durability: " + itemStack.getDurability() + "/" + itemType.getMaxDurability());
                    p.sendMessage("isUnbreakable: " + im.isUnbreakable());
                    p.sendMessage("block: " + itemType.isBlock());
                    p.sendMessage("isBurnable: " + itemType.isBurnable());
                    p.sendMessage("isEdible: " + itemType.isEdible());
                    p.sendMessage("isFlammable: " + itemType.isFlammable());
                    p.sendMessage("isFuel: " + itemType.isFuel());
                    p.sendMessage("isItem" + itemType.isItem());
                    p.sendMessage("isOccluding: " + itemType.isOccluding());
                    p.sendMessage("isRecord: " + itemType.isRecord());
                    p.sendMessage("isSolid: " + itemType.isSolid());
                    p.sendMessage("isTransparent: " + itemType.isTransparent());

                    if (im.hasLore()) {
                        List lore = im.getLore();
                        p.sendMessage("---==== LORE ====---");
                        for (int i = 0; i < lore.size(); i++) {
                            p.sendMessage(i + 1 + ". lore: '" + lore.get(i).toString().replaceAll("§", "&") + "'");
                        }

                    }
                    p.sendMessage("---==== ItemFlag ====---");
                    for (ItemFlag iflag : ItemFlag.values()) {
                        p.sendMessage(iflag + ": " + im.hasItemFlag(iflag));
                    }
                    if (im.hasEnchants()) {
                        p.sendMessage("---==== ENCHANTY ====---");
                        Map<Enchantment, Integer> enchants = im.getEnchants();
                        for (Enchantment ench : enchants.keySet()) {
                            p.sendMessage(ench.getName() + " " + enchants.get(ench));
                        }
                    }
                } else {
                    sender.sendMessage("§cNemáš oprávnění použít tento příkaz!");
                }
            } else {
                sender.sendMessage("Příkaz je pouze pro hráče!");
            }
        }
        return false;
    }
}
