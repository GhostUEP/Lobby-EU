package me.ghost.commands;

import me.ghost.Main;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class ModTeleport implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Voce tem que ser um Player");
		}
		Player p = (Player) sender;
		if (label.equalsIgnoreCase("modteleport")) {
			if (!Main.plugin.perm.isTrial(p)) {
				p.sendMessage(ChatColor.RED + "Voce nao possui permissao para usar este comando!");
				return true;
			}
			if (args.length != 1) {
				p.sendMessage(ChatColor.RED + "Use: /modteleport <nick>");
				return true;
			}
			ByteArrayDataOutput out = ByteStreams.newDataOutput();
			out.writeUTF("ModTeleport");
			out.writeUTF(args[0]);
			p.sendPluginMessage(Main.plugin, "BungeeCord", out.toByteArray());
		}
		return false;
	}
}
