package me.ghost.commands;

import me.ghost.Main;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Admin implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Voce nao e um player");
			return true;
		}
		Player p = (Player) sender;
		if (label.equalsIgnoreCase("admin")) {
			if (!Main.plugin.perm.isTrial(p)) {
				p.sendMessage(ChatColor.RED + "You don't have permission!");
				return true;
			}
			if (Main.plugin.adm.isAdmin(p)) {
				Main.plugin.adm.setPlayer(p);
			} else {
				Main.plugin.adm.setAdmin(p);
			}
			return true;
		}
		return false;
	}
}
