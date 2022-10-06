package me.ghost.admin;

import java.util.ArrayList;

import me.ghost.Main;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Mode {
	public ArrayList<Player> admin = new ArrayList<Player>();
	public ArrayList<Player> youtuber = new ArrayList<Player>();
	public ArrayList<Player> ultimate = new ArrayList<Player>();
	private Main m;
	public ItemStack chest;
	public ItemStack watch;

	public Mode(Main m) {
		this.m = m;
		watch = new ItemStack(Material.WATCH);
		ItemMeta imr = watch.getItemMeta();
		imr.setDisplayName(ChatColor.GOLD + "Proxima partida! §7(Clique para utilizar)");
		watch.setItemMeta(imr);
		chest = new ItemStack(Material.CHEST);
		ItemMeta ima = chest.getItemMeta();
		ima.setDisplayName(ChatColor.YELLOW + "Teleporte para jogadores " + ChatColor.GRAY + "(Clique para utilizar)");
		chest.setItemMeta(ima);
	}

	public void setAdmin(Player p) {
		if (!admin.contains(p) || admin.isEmpty())
			admin.add(p);
		p.setGameMode(GameMode.CREATIVE);
		m.vanish.makeVanished(p);
		m.vanish.updateVanished();
		p.sendMessage(ChatColor.RED + "Modo Admin ATIVADO");
		p.sendMessage(ChatColor.GRAY + "Voce esta invisivel para " + getInvisible(p) + " e abaixo!");
	}

	public void setPlayer(Player p) {
		if (admin.contains(p))
			p.sendMessage(ChatColor.GREEN + "Modo Admin DESATIVADO");
		if (youtuber.contains(p) || ultimate.contains(p))
			p.sendMessage(ChatColor.GREEN + "Modo Espectador DESATIVADO");
		p.sendMessage(ChatColor.GRAY + "Voce esta visivel para todos os players");
		admin.remove(p);
		youtuber.remove(p);
		ultimate.remove(p);
		p.setGameMode(GameMode.SURVIVAL);
		m.vanish.makeVisible(p);
		m.vanish.updateVanished();
	}

	public boolean isAdmin(Player p) {
		return admin.contains(p);
	}

	public boolean isSpectating(Player p) {
		return admin.contains(p) || youtuber.contains(p) || ultimate.contains(p);
	}

	public boolean isYTUT(Player p) {
		return youtuber.contains(p) || ultimate.contains(p);
	}

	private String getInvisible(Player p) {
		if (m.vanish.getPlayerLevel(p) == VLevel.DONO)
			return VLevel.ADMIN.name();
		if (m.vanish.getPlayerLevel(p) == VLevel.ADMIN)
			return VLevel.MODPLUS.name();
		if (m.vanish.getPlayerLevel(p) == VLevel.MODPLUS)
			return VLevel.MOD.name();
		if (m.vanish.getPlayerLevel(p) == VLevel.MOD)
			return VLevel.YOUTUBER.name();
		if (m.vanish.getPlayerLevel(p) == VLevel.YOUTUBER)
			return VLevel.ULTIMATE.name();
		return VLevel.PLAYER.name();
	}
}