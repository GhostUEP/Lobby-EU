package me.ghost.manager;

import me.flame.utils.permissions.enums.Group;
import me.ghost.Main;

import org.bukkit.entity.Player;

public class Permissions {
	public Main m;

	public Permissions(Main m) {
		this.m = m;
	}

	public boolean isLight(Player p) {
		return isPremium(p) || p.hasPermission("hg.vip") || me.flame.utils.Main.getPlugin().getPermissionManager().hasGroupPermission(p, Group.ADVENTURER);
	}

	public boolean isPremium(Player p) {
		return isUltimate(p) || p.hasPermission("hg.mvp") || me.flame.utils.Main.getPlugin().getPermissionManager().hasGroupPermission(p, Group.KING);
	}

	public boolean isUltimate(Player p) {
		return isYouTuber(p) || p.hasPermission("hg.pro") || me.flame.utils.Main.getPlugin().getPermissionManager().hasGroupPermission(p, Group.GOD);
	}

	public boolean isHelper(Player p) {
		return isTrial(p) || p.hasPermission("hg.pro") || me.flame.utils.Main.getPlugin().getPermissionManager().hasGroupPermission(p, Group.HELPER);
	}

	public boolean isYouTuber(Player p) {
		return isTrial(p) || p.hasPermission("hg.youtuber") || me.flame.utils.Main.getPlugin().getPermissionManager().hasGroupPermission(p, Group.YOUTUBER);
	}

	public boolean isTrial(Player p) {
		return isMod(p) || p.hasPermission("hg.mod") || me.flame.utils.Main.getPlugin().getPermissionManager().hasGroupPermission(p, Group.TRIAL);
	}

	public boolean isMod(Player p) {
		return isAdmin(p) || p.hasPermission("hg.plus") || me.flame.utils.Main.getPlugin().getPermissionManager().hasGroupPermission(p, Group.MOD);
	}

	public boolean isAdmin(Player p) {
		return isOwner(p) || p.hasPermission("hg.admin") || me.flame.utils.Main.getPlugin().getPermissionManager().hasGroupPermission(p, Group.ADMIN);
	}

	public boolean isOwner(Player p) {
		return p.hasPermission("hg.dono") || me.flame.utils.Main.getPlugin().getPermissionManager().isGroup(p, Group.DONO) || p.isOp();
	}

	public String getColorGroup(Player p) {
		if (isOwner(p))
			return "§4";
		if (isAdmin(p))
			return "§c";
		if (isMod(p))
			return "§5";
		if (isTrial(p))
			return "§5";
		if (isYouTuber(p))
			return "§b";
		if (isHelper(p))
			return "§9";
		if (isUltimate(p))
			return "§d";
		if (isPremium(p))
			return "§6";
		if (isLight(p))
			return "§a";
		return "§7";
	}
}
