package me.ghost.API;

import me.ghost.Main;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Scoreboard {

	public static void addScoreboard(final Player p) {
		final SimpleScoreboard score = new SimpleScoreboard(ChatColor.DARK_RED + "EMITOWS" + ChatColor.GRAY + "." + ChatColor.RED + "COM", p);
		new BukkitRunnable() {
			public void run() {
				score.add(ChatColor.GRAY + "PLAYERS: " + ChatColor.DARK_GREEN + Main.plugin.globalcouting, 11);
				score.add(ChatColor.WHITE + "FreeHG: " + ChatColor.GREEN + Main.plugin.freehgcouting, 10);
				score.add(ChatColor.WHITE + "TeamHG: " + ChatColor.RED + "OFF", 9);
				score.add(ChatColor.WHITE + "KitPvP: " + ChatColor.RED + "OFF", 8);
				score.add(ChatColor.WHITE + "Lobby: " + ChatColor.GREEN + Main.plugin.lobbycouting, 7);
				score.add(ChatColor.DARK_RED + "    ", 6);
				score.add(ChatColor.AQUA + "@EmitowsNetwork", 5);
				score.add(ChatColor.YELLOW + "Snap: EmitowsMC", 4);
				score.add(ChatColor.DARK_PURPLE + "   ", 3);
				score.add(ChatColor.BLUE + "  ", 2);
				score.add(ChatColor.GOLD + "www.emitows.com", 1);
				score.update();
			}
		}.runTaskTimer(Main.plugin, 5L, 20L);
		score.send(p);
	}
}
