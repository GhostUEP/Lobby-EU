package me.ghost.commands;

import me.ghost.Main;
import me.ghost.manager.ServerManager;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Spawn implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Voce nao e um player");
			return true;
		}
		final Player p = (Player) sender;
		if (label.equalsIgnoreCase("spawn")) {
			if (Main.plugin.ispvp.contains(p.getUniqueId())) {
				Main.plugin.wait.add(p.getUniqueId());
				p.sendMessage(ChatColor.GREEN + "Espere 3 Segundos para voltar para o Spawn, NÃO SE MEXA");
				new BukkitRunnable() {

					@Override
					public void run() {
						if (Main.plugin.wait.contains(p.getUniqueId())) {
							Main.plugin.wait.remove(p.getUniqueId());
							ServerManager.respawnPlayer(p);
						}

					}
				}.runTaskLater(Main.plugin, 20 * 3);
			} else {
				ServerManager.respawnPlayer(p);
			}
			return true;
		}
		return false;
	}
}
