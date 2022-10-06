package me.ghost.listener;

import java.util.ArrayList;
import java.util.Random;

import me.ghost.Main;
import me.ghost.API.barapi.BarAPI;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;

public class DeathListener implements Listener {
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onmorrer2(PlayerRespawnEvent e) {
		Player p = e.getPlayer();
		if (Main.plugin.ispvp.contains(p.getUniqueId())) {
			Main.plugin.ispvp.remove(p.getUniqueId());
		}
		p.setHealth(20.0);
		p.setFoodLevel(20);
		for (PotionEffect pot : p.getActivePotionEffects()) {
			p.removePotionEffect(pot.getType());
			break;
		}
		Random r = new Random();
		Location loc = new Location(p.getWorld(), 0 + r.nextInt(5), 23, 0 + r.nextInt(5));
		loc.getWorld().getChunkAt(loc).load();
		p.closeInventory();
		p.teleport(loc);
		p.setFireTicks(0);
		p.getInventory().clear();
		p.getInventory().setHelmet(new ItemStack(Material.AIR));
		p.getInventory().setChestplate(new ItemStack(Material.AIR));
		p.getInventory().setLeggings(new ItemStack(Material.AIR));
		p.getInventory().setBoots(new ItemStack(Material.AIR));

		ItemStack relogio = new ItemStack(Material.getMaterial(347));
		ItemMeta relogioim = relogio.getItemMeta();
		ArrayList<String> descricaor = new ArrayList<>();
		relogioim.setDisplayName(ChatColor.DARK_PURPLE + "Your last IP");
		descricaor.add(ChatColor.YELLOW + "Connect in your last IP:");
		if (Main.plugin.getLasIP(p).equalsIgnoreCase("craftcombat.freehg.com")) {
			descricaor.add(ChatColor.GRAY + "- " + "craftcombat.com.br");
		} else {
			descricaor.add(ChatColor.GRAY + "- " + Main.plugin.getLasIP(p));
		}
		relogioim.setLore(descricaor);
		relogio.setItemMeta(relogioim);
		if (Main.plugin.hideall.contains(p.getUniqueId())) {
			ItemStack bussola2 = new ItemStack(Material.EYE_OF_ENDER);
			ItemMeta bussolaim2 = bussola2.getItemMeta();
			ArrayList<String> descricao2 = new ArrayList<>();
			bussolaim2.setDisplayName(ChatColor.DARK_PURPLE + "Esconder Players: " + ChatColor.GREEN + "ATIVADO");
			descricao2.add(ChatColor.GRAY + "Esconda todos os players do Servidor");
			bussolaim2.setLore(descricao2);
			bussola2.setItemMeta(bussolaim2);
			p.getInventory().setItem(8, bussola2);
		} else {
			ItemStack bussola2 = new ItemStack(Material.ENDER_PEARL);
			ItemMeta bussolaim2 = bussola2.getItemMeta();
			ArrayList<String> descricao2 = new ArrayList<>();
			bussolaim2.setDisplayName(ChatColor.DARK_PURPLE + "Esconder Players: " + ChatColor.RED + "DESATIVADO");
			descricao2.add(ChatColor.GRAY + "Esconda todos os players do Servidor");
			bussolaim2.setLore(descricao2);
			bussola2.setItemMeta(bussolaim2);
			p.getInventory().setItem(8, bussola2);
		}
		ItemStack bussola = new ItemStack(Material.COMPASS);
		ItemMeta bussolaim = bussola.getItemMeta();
		ArrayList<String> descricao = new ArrayList<>();
		bussolaim.setDisplayName(ChatColor.DARK_PURPLE + "Choose a MiniGame");
		descricao.add(ChatColor.GRAY + "Choose some MiniGame to play");
		bussolaim.setLore(descricao);
		bussola.setItemMeta(bussolaim);

		p.getInventory().setItem(1, relogio);
		p.getInventory().setItem(0, bussola);
		p.getInventory().setHeldItemSlot(0);
		BarAPI.setMessage(p, Main.plugin.barapi.replaceAll("&", "§"));
	}
}
