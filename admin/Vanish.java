package me.ghost.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import me.ghost.Main;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Vanish {
	private static HashMap<UUID, VLevel> vanished = new HashMap<>();
	private Main m;
	private List<UUID> spectatorsDisabled = new ArrayList<>();

	public Vanish(Main main) {
		this.m = main;
	}

	public void setSpectatorEnabled(Player p, boolean b) {
		if (!b) {
			if (!spectatorsDisabled.contains(p.getUniqueId()))
				spectatorsDisabled.add(p.getUniqueId());
		} else {
			spectatorsDisabled.remove(p.getUniqueId());
		}
	}

	public void makeVanished(Player p) {
		if (m.perm.isOwner(p)) {
			makeVanished(p, VLevel.DONO);
		} else if (m.perm.isAdmin(p)) {
			makeVanished(p, VLevel.ADMIN);
		} else if (m.perm.isMod(p)) {
			makeVanished(p, VLevel.MODPLUS);
		} else if (m.perm.isTrial(p)) {
			makeVanished(p, VLevel.MOD);
		} else if (m.perm.isYouTuber(p)) {
			makeVanished(p, VLevel.YOUTUBER);
		} else if (m.perm.isUltimate(p)) {
			makeVanished(p, VLevel.ULTIMATE);
		}
	}

	@SuppressWarnings("deprecation")
	public void makeVanished(Player p, VLevel level) {
		if (level.equals(VLevel.ULTIMATE)) {
			for (Player player : Bukkit.getOnlinePlayers()) {
				player.showPlayer(p);
				if (player.getName().equals(p.getName()))
					continue;
				if (!m.perm.isUltimate(player)) {
					if (!player.canSee(p))
						continue;
					player.hidePlayer(p);
					continue;
				}

				if (spectatorsDisabled.contains(player.getUniqueId())) {
					if (!player.canSee(p))
						continue;
					player.hidePlayer(p);
					continue;
				}
				if (!player.canSee(p))
					player.showPlayer(p);
			}
		} else if (level.equals(VLevel.YOUTUBER)) {
			for (Player player : Bukkit.getOnlinePlayers()) {
				player.showPlayer(p);
				if (player.getName().equals(p.getName()))
					continue;
				if (!m.perm.isYouTuber(player)) {
					if (!player.canSee(p))
						continue;
					player.hidePlayer(p);
					continue;
				}

				if (spectatorsDisabled.contains(player.getUniqueId())) {
					if (!player.canSee(p))
						continue;
					player.hidePlayer(p);
					continue;
				}
				if (!player.canSee(p))
					player.showPlayer(p);
			}
		} else if (level.equals(VLevel.MOD)) {
			for (Player player : Bukkit.getOnlinePlayers()) {
				player.showPlayer(p);
				if (player.getName().equals(p.getName()))
					continue;
				if (!m.perm.isTrial(player)) {
					if (!player.canSee(p))
						continue;
					player.hidePlayer(p);
					continue;
				}

				if (spectatorsDisabled.contains(player.getUniqueId())) {
					if (!player.canSee(p))
						continue;
					player.hidePlayer(p);
					continue;
				}
				if (!player.canSee(p))
					player.showPlayer(p);
			}
		} else if (level.equals(VLevel.MODPLUS)) {
			for (Player player : Bukkit.getOnlinePlayers()) {
				player.showPlayer(p);
				if (player.getName().equals(p.getName()))
					continue;
				if (!m.perm.isMod(player)) {
					if (!player.canSee(p))
						continue;
					player.hidePlayer(p);
					continue;
				}

				if (spectatorsDisabled.contains(player.getUniqueId())) {
					if (!player.canSee(p))
						continue;
					player.hidePlayer(p);
					continue;
				}
				if (!player.canSee(p))
					player.showPlayer(p);
			}
		} else if (level.equals(VLevel.ADMIN)) {
			for (Player player : Bukkit.getOnlinePlayers()) {
				player.showPlayer(p);
				if (player.getName().equals(p.getName()))
					continue;
				if (!m.perm.isAdmin(player)) {
					if (!player.canSee(p))
						continue;
					player.hidePlayer(p);
					continue;
				}

				if (spectatorsDisabled.contains(player.getUniqueId())) {
					if (!player.canSee(p))
						continue;
					player.hidePlayer(p);
					continue;
				}
				if (!player.canSee(p))
					player.showPlayer(p);
			}
		} else if (level.equals(VLevel.DONO)) {
			for (Player player : Bukkit.getOnlinePlayers()) {
				player.showPlayer(p);
				if (player.getName().equals(p.getName()))
					continue;
				if (!m.perm.isOwner(player)) {
					if (!player.canSee(p))
						continue;
					player.hidePlayer(p);
					continue;
				}

				if (spectatorsDisabled.contains(player.getUniqueId())) {
					if (!player.canSee(p))
						continue;
					player.hidePlayer(p);
					continue;
				}
				if (!player.canSee(p))
					player.showPlayer(p);
			}
		} else if (level.equals(VLevel.ALL)) {
			for (Player player : Bukkit.getOnlinePlayers()) {
				if (player != p) {
					if (Main.plugin.perm.isUltimate(player))
						continue;
					p.hidePlayer(player);

				}
			}
		}
		vanished.put(p.getUniqueId(), level);
	}

	public boolean isVanished(Player p) {
		return vanished.containsKey(p.getUniqueId()) && !vanished.get(p.getUniqueId()).equals(VLevel.PLAYER);
	}

	public VLevel getPlayerLevel(Player p) {
		return vanished.get(p.getUniqueId());
	}

	@SuppressWarnings("deprecation")
	public void updateVanished() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (isVanished(p)) {
				makeVanished(p, vanished.get(p.getUniqueId()));
			} else {
				makeVisible(p);
			}
		}

	}

	public void hideAllPlayers(Player p) {

	}

	@SuppressWarnings("deprecation")
	public void makeVisible(Player p) {
		for (Player player : Bukkit.getOnlinePlayers()) {
			if (player.canSee(p))
				continue;
			player.showPlayer(p);
		}
		vanished.put(p.getUniqueId(), VLevel.PLAYER);
	}
}
