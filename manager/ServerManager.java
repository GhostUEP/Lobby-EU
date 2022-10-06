package me.ghost.manager;

import java.util.ArrayList;
import java.util.Random;

import me.ghost.Main;
import me.ghost.API.barapi.BarAPI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;

public class ServerManager {

	public static int a1players = 0;
	public static int a2players = 0;
	public static int a3players = 0;
	public static int a4players = 0;
	public static int a5players = 0;
	public static int a6players = 0;
	public static int a7players = 0;
	public static int a8players = 0;
	public static int a9players = 0;
	public static int a10players = 0;
	public static int a11players = 0;
	public static int a12players = 0;

	public static String a1motd = "";
	public static String a2motd = "";
	public static String a3motd = "";
	public static String a4motd = "";
	public static String a5motd = "";
	public static String a6motd = "";
	public static String a7motd = "";
	public static String a8motd = "";
	public static String a9motd = "";
	public static String a10motd = "";
	public static String a11motd = "";
	public static String a12motd = "";

	@SuppressWarnings("deprecation")
	public static void respawnPlayer(Player p) {
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

	@SuppressWarnings("deprecation")
	public static void giveItens(Player p) {
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
		if (Main.plugin.getLasIP(p).equalsIgnoreCase("craftcombat.eu.emitows.com")) {
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
		p.updateInventory();

	}

	@SuppressWarnings("deprecation")
	public static void openMenu(Player p) {
		Inventory i = Bukkit.getServer().createInventory(p, 9, ChatColor.BLACK + "MiniGames");

		ItemStack bussola3 = new ItemStack(Material.LEATHER_BOOTS);
		ItemMeta bussolaim3 = bussola3.getItemMeta();
		ArrayList<String> descricao3 = new ArrayList<>();
		bussolaim3.setDisplayName(ChatColor.DARK_PURPLE + "CorridaArmada");
		descricao3.add(ChatColor.GREEN + "" + ChatColor.BOLD + "NEW MINIGAME!!!");
		descricao3.add(ChatColor.GRAY + "This is a BETA test");
		descricao3.add(ChatColor.GRAY + "If some ADM want, only VIPs can play it");
		bussolaim3.setLore(descricao3);
		bussola3.setItemMeta(bussolaim3);

		ItemStack bussola2 = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta bussolaim2 = bussola2.getItemMeta();
		ArrayList<String> descricao2 = new ArrayList<>();
		bussolaim2.setDisplayName(ChatColor.DARK_PURPLE + "CraftCombat");
		descricao2.add(ChatColor.YELLOW + "Train your PvP and become the Best!");
		bussolaim2.setLore(descricao2);
		bussola2.setItemMeta(bussolaim2);

		ItemStack bussola = new ItemStack(Material.CAKE);
		ItemMeta bussolaim = bussola.getItemMeta();
		ArrayList<String> descricao = new ArrayList<>();
		bussolaim.setDisplayName(ChatColor.DARK_PURPLE + "FreeHG");
		descricao.add(ChatColor.YELLOW + "Click here to play HungerGames");
		bussolaim.setLore(descricao);
		bussola.setItemMeta(bussolaim);

		i.setItem(0, bussola);
		i.setItem(1, bussola2);
		i.setItem(2, bussola3);
		ItemStack[] arrayOfItemStack;
		arrayOfItemStack = i.getContents();
		int vidros = i.getContents().length;
		for (int metavidros = 0; metavidros < vidros; metavidros++) {
			ItemStack item2 = arrayOfItemStack[metavidros];
			if (item2 == null) {

				i.setItem(i.firstEmpty(), new ItemStack(Material.getMaterial(160), (short) 1, (short) 11));
			}

		}
		p.openInventory(i);
	}

	@SuppressWarnings("deprecation")
	public static void openHGMenu(Player p) {
		Inventory i = Main.plugin.freehgi.get(p.getUniqueId());

		if (a12motd.contains("Comeca")) {
			ItemStack bussola = new ItemStack(Material.MUSHROOM_SOUP, 12);
			ItemMeta bussolaim = bussola.getItemMeta();
			ArrayList<String> descricao = new ArrayList<>();
			bussolaim.setDisplayName(ChatColor.DARK_PURPLE + "12");
			descricao.add(ChatColor.YELLOW + "HardCore Games:");
			descricao.add(ChatColor.GREEN + "" + ChatColor.BOLD + "AVAILABLE");
			descricao.add(ChatColor.GRAY + "Starting in: " + ChatColor.GREEN + a12motd.replace("Comeca em ", "").replaceAll(" minutos", ":00").replaceAll(" minuto", ":00").replaceAll(" segundo", "").replace(" segundos", "").replace("\n", "").replaceAll("Visite", "").replaceAll("freehg.com", ""));
			descricao.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "- Players: " + ChatColor.YELLOW + a12players);
			bussolaim.setLore(descricao);
			bussola.setItemMeta(bussolaim);
			i.setItem(32, bussola);
			Main.plugin.itemip.put(bussola, "b3.eu.emitows.com");
			Main.plugin.itemd.put(bussola, "sim");
		} else if (a12motd.contains("Esperando")) {
			ItemStack bussola = new ItemStack(Material.MUSHROOM_SOUP, 12);
			ItemMeta bussolaim = bussola.getItemMeta();
			ArrayList<String> descricao = new ArrayList<>();
			bussolaim.setDisplayName(ChatColor.DARK_PURPLE + "12");
			descricao.add(ChatColor.YELLOW + "HardCore Games:");
			descricao.add(ChatColor.GREEN + "" + ChatColor.BOLD + "AVAILABLE");
			descricao.add(ChatColor.GRAY + "Waiting for Players");
			descricao.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "- Players: " + ChatColor.YELLOW + a12players);
			bussolaim.setLore(descricao);
			bussola.setItemMeta(bussolaim);
			i.setItem(32, bussola);
			Main.plugin.itemip.put(bussola, "b3.eu.emitows.com");
			Main.plugin.itemd.put(bussola, "sim");

		} else if (a12motd.contains("progresso")) {
			ItemStack bussola = new ItemStack(Material.BOWL, 12);
			ItemMeta bussolaim = bussola.getItemMeta();
			ArrayList<String> descricao = new ArrayList<>();
			bussolaim.setDisplayName(ChatColor.DARK_PURPLE + "12");
			descricao.add(ChatColor.YELLOW + "HardCore Games:");
			descricao.add(ChatColor.RED + "" + ChatColor.BOLD + "IN PROGRESS");
			descricao.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "- Players: " + ChatColor.YELLOW + a12players);
			bussolaim.setLore(descricao);
			bussola.setItemMeta(bussolaim);
			i.setItem(32, bussola);
			Main.plugin.itemip.put(bussola, "b3.eu.emitows.com");
			Main.plugin.itemd.put(bussola, "nao");
		} else {
			ItemStack bussola = new ItemStack(Material.getMaterial(160), (short) 12, (short) 15);
			ItemMeta bussolaim = bussola.getItemMeta();
			ArrayList<String> descricao = new ArrayList<>();
			bussolaim.setDisplayName(ChatColor.DARK_PURPLE + "12");
			descricao.add(ChatColor.YELLOW + "HardCore Games:");
			descricao.add(ChatColor.RED + "" + ChatColor.BOLD + "OFFLINE");
			bussolaim.setLore(descricao);
			bussola.setItemMeta(bussolaim);
			i.setItem(32, bussola);
		}

		if (a11motd.contains("Comeca")) {
			ItemStack bussola = new ItemStack(Material.MUSHROOM_SOUP, 11);
			ItemMeta bussolaim = bussola.getItemMeta();
			ArrayList<String> descricao = new ArrayList<>();
			bussolaim.setDisplayName(ChatColor.DARK_PURPLE + "11");
			descricao.add(ChatColor.YELLOW + "HardCore Games:");
			descricao.add(ChatColor.GREEN + "" + ChatColor.BOLD + "AVAILABLE");
			descricao.add(ChatColor.GRAY + "Starting in: " + ChatColor.GREEN + a11motd.replace("Comeca em ", "").replaceAll(" minutos", ":00").replaceAll(" minuto", ":00").replaceAll(" segundo", "").replaceAll(" minuto", "").replaceAll(" segundo", "").replace(" segundos", "").replace("\n", "").replaceAll("Visite", "").replaceAll("freehg.com", ""));
			descricao.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "- Players: " + ChatColor.YELLOW + a11players);
			bussolaim.setLore(descricao);
			bussola.setItemMeta(bussolaim);
			i.setItem(31, bussola);
			Main.plugin.itemip.put(bussola, "b2.eu.emitows.com");
			Main.plugin.itemd.put(bussola, "sim");
		} else if (a11motd.contains("Esperando")) {
			ItemStack bussola = new ItemStack(Material.MUSHROOM_SOUP, 11);
			ItemMeta bussolaim = bussola.getItemMeta();
			ArrayList<String> descricao = new ArrayList<>();
			bussolaim.setDisplayName(ChatColor.DARK_PURPLE + "11");
			descricao.add(ChatColor.YELLOW + "HardCore Games:");
			descricao.add(ChatColor.GREEN + "" + ChatColor.BOLD + "AVAILABLE");
			descricao.add(ChatColor.GRAY + "Waiting for Players");
			descricao.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "- Players: " + ChatColor.YELLOW + a11players);
			bussolaim.setLore(descricao);
			bussola.setItemMeta(bussolaim);
			i.setItem(31, bussola);
			Main.plugin.itemip.put(bussola, "b2.eu.emitows.com");
			Main.plugin.itemd.put(bussola, "sim");

		} else if (a11motd.contains("progresso")) {
			ItemStack bussola = new ItemStack(Material.BOWL, 11);
			ItemMeta bussolaim = bussola.getItemMeta();
			ArrayList<String> descricao = new ArrayList<>();
			bussolaim.setDisplayName(ChatColor.DARK_PURPLE + "11");
			descricao.add(ChatColor.YELLOW + "HardCore Games:");
			descricao.add(ChatColor.RED + "" + ChatColor.BOLD + "IN PROGRESS");
			descricao.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "- Players: " + ChatColor.YELLOW + a11players);
			bussolaim.setLore(descricao);
			bussola.setItemMeta(bussolaim);
			i.setItem(31, bussola);
			Main.plugin.itemip.put(bussola, "b2.eu.emitows.com");
			Main.plugin.itemd.put(bussola, "nao");
		} else {
			ItemStack bussola = new ItemStack(Material.getMaterial(160), (short) 11, (short) 15);
			ItemMeta bussolaim = bussola.getItemMeta();
			ArrayList<String> descricao = new ArrayList<>();
			bussolaim.setDisplayName(ChatColor.DARK_PURPLE + "11");
			descricao.add(ChatColor.YELLOW + "HardCore Games:");
			descricao.add(ChatColor.RED + "" + ChatColor.BOLD + "OFFLINE");
			bussolaim.setLore(descricao);
			bussola.setItemMeta(bussolaim);
			i.setItem(31, bussola);
		}

		if (a10motd.contains("Comeca")) {
			ItemStack bussola = new ItemStack(Material.MUSHROOM_SOUP, 10);
			ItemMeta bussolaim = bussola.getItemMeta();
			ArrayList<String> descricao = new ArrayList<>();
			bussolaim.setDisplayName(ChatColor.DARK_PURPLE + "10");
			descricao.add(ChatColor.YELLOW + "HardCore Games:");
			descricao.add(ChatColor.GREEN + "" + ChatColor.BOLD + "AVAILABLE");
			descricao.add(ChatColor.GRAY + "Starting in: " + ChatColor.GREEN + a10motd.replace("Comeca em ", "").replaceAll(" minutos", ":00").replaceAll(" minuto", ":00").replaceAll(" segundo", "").replace(" segundos", "").replace("\n", "").replaceAll("Visite", "").replaceAll("freehg.com", ""));
			descricao.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "- Players: " + ChatColor.YELLOW + a10players);
			bussolaim.setLore(descricao);
			bussola.setItemMeta(bussolaim);
			i.setItem(30, bussola);
			Main.plugin.itemip.put(bussola, "b1.eu.emitows.com");
			Main.plugin.itemd.put(bussola, "sim");
		} else if (a10motd.contains("Esperando")) {
			ItemStack bussola = new ItemStack(Material.MUSHROOM_SOUP, 10);
			ItemMeta bussolaim = bussola.getItemMeta();
			ArrayList<String> descricao = new ArrayList<>();
			bussolaim.setDisplayName(ChatColor.DARK_PURPLE + "10");
			descricao.add(ChatColor.YELLOW + "HardCore Games:");
			descricao.add(ChatColor.GREEN + "" + ChatColor.BOLD + "AVAILABLE");
			descricao.add(ChatColor.GRAY + "Waiting for Players");
			descricao.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "- Players: " + ChatColor.YELLOW + a10players);
			bussolaim.setLore(descricao);
			bussola.setItemMeta(bussolaim);
			i.setItem(30, bussola);
			Main.plugin.itemip.put(bussola, "b1.eu.emitows.com");
			Main.plugin.itemd.put(bussola, "sim");

		} else if (a10motd.contains("progresso")) {
			ItemStack bussola = new ItemStack(Material.BOWL, 10);
			ItemMeta bussolaim = bussola.getItemMeta();
			ArrayList<String> descricao = new ArrayList<>();
			bussolaim.setDisplayName(ChatColor.DARK_PURPLE + "10");
			descricao.add(ChatColor.YELLOW + "HardCore Games:");
			descricao.add(ChatColor.RED + "" + ChatColor.BOLD + "IN PROGRESS");
			descricao.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "- Players: " + ChatColor.YELLOW + a10players);
			bussolaim.setLore(descricao);
			bussola.setItemMeta(bussolaim);
			i.setItem(30, bussola);
			Main.plugin.itemip.put(bussola, "b1.eu.emitows.com");
			Main.plugin.itemd.put(bussola, "nao");
		} else {
			ItemStack bussola = new ItemStack(Material.getMaterial(160), (short) 10, (short) 15);
			ItemMeta bussolaim = bussola.getItemMeta();
			ArrayList<String> descricao = new ArrayList<>();
			bussolaim.setDisplayName(ChatColor.DARK_PURPLE + "10");
			descricao.add(ChatColor.YELLOW + "HardCore Games:");
			descricao.add(ChatColor.RED + "" + ChatColor.BOLD + "OFFLINE");
			bussolaim.setLore(descricao);
			bussola.setItemMeta(bussolaim);
			i.setItem(30, bussola);
		}

		if (a9motd.contains("Comeca")) {
			ItemStack bussola = new ItemStack(Material.MUSHROOM_SOUP, 9);
			ItemMeta bussolaim = bussola.getItemMeta();
			ArrayList<String> descricao = new ArrayList<>();
			bussolaim.setDisplayName(ChatColor.DARK_PURPLE + "9");
			descricao.add(ChatColor.YELLOW + "HardCore Games:");
			descricao.add(ChatColor.GREEN + "" + ChatColor.BOLD + "AVAILABLE");
			descricao.add(ChatColor.GRAY + "Starting in: " + ChatColor.GREEN + a9motd.replace("Comeca em ", "").replaceAll(" minutos", ":00").replaceAll(" minuto", ":00").replaceAll(" segundo", "").replace(" segundos", "").replace("\n", "").replaceAll("Visite", "").replaceAll("freehg.com", ""));
			descricao.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "- Players: " + ChatColor.YELLOW + a9players);
			bussolaim.setLore(descricao);
			bussola.setItemMeta(bussolaim);
			i.setItem(29, bussola);
			Main.plugin.itemip.put(bussola, "a9.eu.emitows.com");
			Main.plugin.itemd.put(bussola, "sim");
		} else if (a9motd.contains("Esperando")) {
			ItemStack bussola = new ItemStack(Material.MUSHROOM_SOUP, 9);
			ItemMeta bussolaim = bussola.getItemMeta();
			ArrayList<String> descricao = new ArrayList<>();
			bussolaim.setDisplayName(ChatColor.DARK_PURPLE + "9");
			descricao.add(ChatColor.YELLOW + "HardCore Games:");
			descricao.add(ChatColor.GREEN + "" + ChatColor.BOLD + "AVAILABLE");
			descricao.add(ChatColor.GRAY + "Waiting for Players");
			descricao.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "- Players: " + ChatColor.YELLOW + a9players);
			bussolaim.setLore(descricao);
			bussola.setItemMeta(bussolaim);
			i.setItem(29, bussola);
			Main.plugin.itemip.put(bussola, "a9.eu.emitows.com");
			Main.plugin.itemd.put(bussola, "sim");

		} else if (a9motd.contains("progresso")) {
			ItemStack bussola = new ItemStack(Material.BOWL, 9);
			ItemMeta bussolaim = bussola.getItemMeta();
			ArrayList<String> descricao = new ArrayList<>();
			bussolaim.setDisplayName(ChatColor.DARK_PURPLE + "9");
			descricao.add(ChatColor.YELLOW + "HardCore Games:");
			descricao.add(ChatColor.RED + "" + ChatColor.BOLD + "IN PROGRESS");
			descricao.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "- Players: " + ChatColor.YELLOW + a9players);
			bussolaim.setLore(descricao);
			bussola.setItemMeta(bussolaim);
			i.setItem(29, bussola);
			Main.plugin.itemip.put(bussola, "a9.eu.emitows.com");
			Main.plugin.itemd.put(bussola, "nao");
		} else {
			ItemStack bussola = new ItemStack(Material.getMaterial(160), (short) 9, (short) 15);
			ItemMeta bussolaim = bussola.getItemMeta();
			ArrayList<String> descricao = new ArrayList<>();
			bussolaim.setDisplayName(ChatColor.DARK_PURPLE + "9");
			descricao.add(ChatColor.YELLOW + "HardCore Games:");
			descricao.add(ChatColor.RED + "" + ChatColor.BOLD + "OFFLINE");
			bussolaim.setLore(descricao);
			bussola.setItemMeta(bussolaim);
			i.setItem(29, bussola);
		}
		if (a8motd.contains("Comeca")) {
			ItemStack bussola = new ItemStack(Material.MUSHROOM_SOUP, 8);
			ItemMeta bussolaim = bussola.getItemMeta();
			ArrayList<String> descricao = new ArrayList<>();
			bussolaim.setDisplayName(ChatColor.DARK_PURPLE + "8");
			descricao.add(ChatColor.YELLOW + "HardCore Games:");
			descricao.add(ChatColor.GREEN + "" + ChatColor.BOLD + "AVAILABLE");
			descricao.add(ChatColor.GRAY + "Starting in: " + ChatColor.GREEN + a8motd.replace("Comeca em ", "").replaceAll(" minutos", ":00").replaceAll(" minuto", ":00").replaceAll(" segundo", "").replace(" segundos", "").replace("\n", "").replaceAll("Visite", "").replaceAll("freehg.com", ""));
			descricao.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "- Players: " + ChatColor.YELLOW + a8players);
			bussolaim.setLore(descricao);
			bussola.setItemMeta(bussolaim);
			i.setItem(28, bussola);
			Main.plugin.itemip.put(bussola, "a8.eu.emitows.com");
			Main.plugin.itemd.put(bussola, "sim");
		} else if (a8motd.contains("Esperando")) {
			ItemStack bussola = new ItemStack(Material.MUSHROOM_SOUP, 8);
			ItemMeta bussolaim = bussola.getItemMeta();
			ArrayList<String> descricao = new ArrayList<>();
			bussolaim.setDisplayName(ChatColor.DARK_PURPLE + "8");
			descricao.add(ChatColor.YELLOW + "HardCore Games:");
			descricao.add(ChatColor.GREEN + "" + ChatColor.BOLD + "AVAILABLE");
			descricao.add(ChatColor.GRAY + "Waiting for Players");
			descricao.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "- Players: " + ChatColor.YELLOW + a8players);
			bussolaim.setLore(descricao);
			bussola.setItemMeta(bussolaim);
			i.setItem(28, bussola);
			Main.plugin.itemip.put(bussola, "a8.eu.emitows.com");
			Main.plugin.itemd.put(bussola, "sim");

		} else if (a8motd.contains("progresso")) {
			ItemStack bussola = new ItemStack(Material.BOWL, 8);
			ItemMeta bussolaim = bussola.getItemMeta();
			ArrayList<String> descricao = new ArrayList<>();
			bussolaim.setDisplayName(ChatColor.DARK_PURPLE + "8");
			descricao.add(ChatColor.YELLOW + "HardCore Games:");
			descricao.add(ChatColor.RED + "" + ChatColor.BOLD + "IN PROGRESS");
			descricao.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "- Players: " + ChatColor.YELLOW + a8players);
			bussolaim.setLore(descricao);
			bussola.setItemMeta(bussolaim);
			i.setItem(28, bussola);
			Main.plugin.itemip.put(bussola, "a8.eu.emitows.com");
			Main.plugin.itemd.put(bussola, "nao");
		} else {
			ItemStack bussola = new ItemStack(Material.getMaterial(160), (short) 8, (short) 15);
			ItemMeta bussolaim = bussola.getItemMeta();
			ArrayList<String> descricao = new ArrayList<>();
			bussolaim.setDisplayName(ChatColor.DARK_PURPLE + "8");
			descricao.add(ChatColor.YELLOW + "HardCore Games:");
			descricao.add(ChatColor.RED + "" + ChatColor.BOLD + "OFFLINE");
			bussolaim.setLore(descricao);
			bussola.setItemMeta(bussolaim);
			i.setItem(28, bussola);
		}
		if (a7motd.contains("Comeca")) {
			ItemStack bussola = new ItemStack(Material.MUSHROOM_SOUP, 7);
			ItemMeta bussolaim = bussola.getItemMeta();
			ArrayList<String> descricao = new ArrayList<>();
			bussolaim.setDisplayName(ChatColor.DARK_PURPLE + "7");
			descricao.add(ChatColor.YELLOW + "HardCore Games:");
			descricao.add(ChatColor.GREEN + "" + ChatColor.BOLD + "AVAILABLE");
			descricao.add(ChatColor.GRAY + "Starting in: " + ChatColor.GREEN + a7motd.replace("Comeca em ", "").replaceAll(" minutos", ":00").replaceAll(" minuto", ":00").replaceAll(" segundo", "").replace(" segundos", "").replace("\n", "").replaceAll("Visite", "").replaceAll("freehg.com", ""));
			descricao.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "- Players: " + ChatColor.YELLOW + a7players);
			bussolaim.setLore(descricao);
			bussola.setItemMeta(bussolaim);
			i.setItem(25, bussola);
			Main.plugin.itemip.put(bussola, "a7.eu.emitows.com");
			Main.plugin.itemd.put(bussola, "sim");
		} else if (a7motd.contains("Esperando")) {
			ItemStack bussola = new ItemStack(Material.MUSHROOM_SOUP, 7);
			ItemMeta bussolaim = bussola.getItemMeta();
			ArrayList<String> descricao = new ArrayList<>();
			bussolaim.setDisplayName(ChatColor.DARK_PURPLE + "7");
			descricao.add(ChatColor.YELLOW + "HardCore Games:");
			descricao.add(ChatColor.GREEN + "" + ChatColor.BOLD + "AVAILABLE");
			descricao.add(ChatColor.GRAY + "Waiting for Players");
			descricao.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "- Players: " + ChatColor.YELLOW + a7players);
			bussolaim.setLore(descricao);
			bussola.setItemMeta(bussolaim);
			i.setItem(25, bussola);
			Main.plugin.itemip.put(bussola, "a7.eu.emitows.com");
			Main.plugin.itemd.put(bussola, "sim");

		} else if (a7motd.contains("progresso")) {
			ItemStack bussola = new ItemStack(Material.BOWL, 7);
			ItemMeta bussolaim = bussola.getItemMeta();
			ArrayList<String> descricao = new ArrayList<>();
			bussolaim.setDisplayName(ChatColor.DARK_PURPLE + "7");
			descricao.add(ChatColor.YELLOW + "HardCore Games:");
			descricao.add(ChatColor.RED + "" + ChatColor.BOLD + "IN PROGRESS");
			descricao.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "- Players: " + ChatColor.YELLOW + a7players);
			bussolaim.setLore(descricao);
			bussola.setItemMeta(bussolaim);
			i.setItem(25, bussola);
			Main.plugin.itemip.put(bussola, "a7.eu.emitows.com");
			Main.plugin.itemd.put(bussola, "nao");
		} else {
			ItemStack bussola = new ItemStack(Material.getMaterial(160), (short) 7, (short) 15);
			ItemMeta bussolaim = bussola.getItemMeta();
			ArrayList<String> descricao = new ArrayList<>();
			bussolaim.setDisplayName(ChatColor.DARK_PURPLE + "7");
			descricao.add(ChatColor.YELLOW + "HardCore Games:");
			descricao.add(ChatColor.RED + "" + ChatColor.BOLD + "OFFLINE");
			bussolaim.setLore(descricao);
			bussola.setItemMeta(bussolaim);
			i.setItem(25, bussola);
		}
		if (a6motd.contains("Comeca")) {
			ItemStack bussola = new ItemStack(Material.MUSHROOM_SOUP, 6);
			ItemMeta bussolaim = bussola.getItemMeta();
			ArrayList<String> descricao = new ArrayList<>();
			bussolaim.setDisplayName(ChatColor.DARK_PURPLE + "6");
			descricao.add(ChatColor.YELLOW + "HardCore Games:");
			descricao.add(ChatColor.GREEN + "" + ChatColor.BOLD + "AVAILABLE");
			descricao.add(ChatColor.GRAY + "Starting in: " + ChatColor.GREEN + a6motd.replace("Comeca em ", "").replaceAll(" minutos", ":00").replaceAll(" minuto", ":00").replaceAll(" segundo", "").replace(" segundos", "").replace("\n", "").replaceAll("Visite", "").replaceAll("freehg.com", ""));
			descricao.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "- Players: " + ChatColor.YELLOW + a6players);
			bussolaim.setLore(descricao);
			bussola.setItemMeta(bussolaim);
			i.setItem(24, bussola);
			Main.plugin.itemip.put(bussola, "a6.eu.emitows.com");
			Main.plugin.itemd.put(bussola, "sim");
		} else if (a6motd.contains("Esperando")) {
			ItemStack bussola = new ItemStack(Material.MUSHROOM_SOUP, 6);
			ItemMeta bussolaim = bussola.getItemMeta();
			ArrayList<String> descricao = new ArrayList<>();
			bussolaim.setDisplayName(ChatColor.DARK_PURPLE + "6");
			descricao.add(ChatColor.YELLOW + "HardCore Games:");
			descricao.add(ChatColor.GREEN + "" + ChatColor.BOLD + "AVAILABLE");
			descricao.add(ChatColor.GRAY + "Waiting for Players");
			descricao.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "- Players: " + ChatColor.YELLOW + a6players);
			bussolaim.setLore(descricao);
			bussola.setItemMeta(bussolaim);
			i.setItem(24, bussola);
			Main.plugin.itemip.put(bussola, "a6.eu.emitows.com");
			Main.plugin.itemd.put(bussola, "sim");

		} else if (a6motd.contains("progresso")) {
			ItemStack bussola = new ItemStack(Material.BOWL, 6);
			ItemMeta bussolaim = bussola.getItemMeta();
			ArrayList<String> descricao = new ArrayList<>();
			bussolaim.setDisplayName(ChatColor.DARK_PURPLE + "6");
			descricao.add(ChatColor.YELLOW + "HardCore Games:");
			descricao.add(ChatColor.RED + "" + ChatColor.BOLD + "IN PROGRESS");
			descricao.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "- Players: " + ChatColor.YELLOW + a6players);
			bussolaim.setLore(descricao);
			bussola.setItemMeta(bussolaim);
			i.setItem(24, bussola);
			Main.plugin.itemip.put(bussola, "a6.eu.emitows.com");
			Main.plugin.itemd.put(bussola, "nao");
		} else {
			ItemStack bussola = new ItemStack(Material.getMaterial(160), (short) 6, (short) 15);
			ItemMeta bussolaim = bussola.getItemMeta();
			ArrayList<String> descricao = new ArrayList<>();
			bussolaim.setDisplayName(ChatColor.DARK_PURPLE + "6");
			descricao.add(ChatColor.YELLOW + "HardCore Games:");
			descricao.add(ChatColor.RED + "" + ChatColor.BOLD + "OFFLINE");
			bussolaim.setLore(descricao);
			bussola.setItemMeta(bussolaim);
			i.setItem(24, bussola);
		}

		if (a5motd.contains("Comeca")) {
			ItemStack bussola = new ItemStack(Material.MUSHROOM_SOUP, 5);
			ItemMeta bussolaim = bussola.getItemMeta();
			ArrayList<String> descricao = new ArrayList<>();
			bussolaim.setDisplayName(ChatColor.DARK_PURPLE + "5");
			descricao.add(ChatColor.YELLOW + "HardCore Games:");
			descricao.add(ChatColor.GREEN + "" + ChatColor.BOLD + "AVAILABLE");
			descricao.add(ChatColor.GRAY + "Starting in: " + ChatColor.GREEN + a5motd.replace("Comeca em ", "").replaceAll(" minutos", ":00").replaceAll(" minuto", ":00").replaceAll(" segundo", "").replace(" segundos", "").replace("\n", "").replaceAll("Visite", "").replaceAll("freehg.com", ""));
			descricao.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "- Players: " + ChatColor.YELLOW + a5players);
			bussolaim.setLore(descricao);
			bussola.setItemMeta(bussolaim);
			i.setItem(23, bussola);
			Main.plugin.itemip.put(bussola, "a5.eu.emitows.com");
			Main.plugin.itemd.put(bussola, "sim");
		} else if (a5motd.contains("Esperando")) {
			ItemStack bussola = new ItemStack(Material.MUSHROOM_SOUP, 5);
			ItemMeta bussolaim = bussola.getItemMeta();
			ArrayList<String> descricao = new ArrayList<>();
			bussolaim.setDisplayName(ChatColor.DARK_PURPLE + "5");
			descricao.add(ChatColor.YELLOW + "HardCore Games:");
			descricao.add(ChatColor.GREEN + "" + ChatColor.BOLD + "AVAILABLE");
			descricao.add(ChatColor.GRAY + "Waiting for Players");
			descricao.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "- Players: " + ChatColor.YELLOW + a5players);
			bussolaim.setLore(descricao);
			bussola.setItemMeta(bussolaim);
			i.setItem(23, bussola);
			Main.plugin.itemip.put(bussola, "a5.eu.emitows.com");
			Main.plugin.itemd.put(bussola, "sim");

		} else if (a5motd.contains("progresso")) {
			ItemStack bussola = new ItemStack(Material.BOWL, 5);
			ItemMeta bussolaim = bussola.getItemMeta();
			ArrayList<String> descricao = new ArrayList<>();
			bussolaim.setDisplayName(ChatColor.DARK_PURPLE + "5");
			descricao.add(ChatColor.YELLOW + "HardCore Games:");
			descricao.add(ChatColor.RED + "" + ChatColor.BOLD + "IN PROGRESS");
			descricao.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "- Players: " + ChatColor.YELLOW + a5players);
			bussolaim.setLore(descricao);
			bussola.setItemMeta(bussolaim);
			i.setItem(23, bussola);
			Main.plugin.itemip.put(bussola, "a5.eu.emitows.com");
			Main.plugin.itemd.put(bussola, "nao");
		} else {
			ItemStack bussola = new ItemStack(Material.getMaterial(160), (short) 5, (short) 15);
			ItemMeta bussolaim = bussola.getItemMeta();
			ArrayList<String> descricao = new ArrayList<>();
			bussolaim.setDisplayName(ChatColor.DARK_PURPLE + "5");
			descricao.add(ChatColor.YELLOW + "HardCore Games:");
			descricao.add(ChatColor.RED + "" + ChatColor.BOLD + "OFFLINE");
			bussolaim.setLore(descricao);
			bussola.setItemMeta(bussolaim);
			i.setItem(23, bussola);
		}
		if (a4motd.contains("Comeca")) {
			ItemStack bussola = new ItemStack(Material.MUSHROOM_SOUP, 4);
			ItemMeta bussolaim = bussola.getItemMeta();
			ArrayList<String> descricao = new ArrayList<>();
			bussolaim.setDisplayName(ChatColor.DARK_PURPLE + "4");
			descricao.add(ChatColor.YELLOW + "HardCore Games:");
			descricao.add(ChatColor.GREEN + "" + ChatColor.BOLD + "AVAILABLE");
			descricao.add(ChatColor.GRAY + "Starting in: " + ChatColor.GREEN + a4motd.replace("Comeca em ", "").replaceAll(" minutos", ":00").replaceAll(" minuto", ":00").replaceAll(" segundo", "").replace(" segundos", "").replace("\n", "").replaceAll("Visite", "").replaceAll("freehg.com", ""));
			descricao.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "- Players: " + ChatColor.YELLOW + a4players);
			bussolaim.setLore(descricao);
			bussola.setItemMeta(bussolaim);
			i.setItem(22, bussola);
			Main.plugin.itemip.put(bussola, "a4.eu.emitows.com");
			Main.plugin.itemd.put(bussola, "sim");
		} else if (a4motd.contains("Esperando")) {
			ItemStack bussola = new ItemStack(Material.MUSHROOM_SOUP, 4);
			ItemMeta bussolaim = bussola.getItemMeta();
			ArrayList<String> descricao = new ArrayList<>();
			bussolaim.setDisplayName(ChatColor.DARK_PURPLE + "4");
			descricao.add(ChatColor.YELLOW + "HardCore Games:");
			descricao.add(ChatColor.GREEN + "" + ChatColor.BOLD + "AVAILABLE");
			descricao.add(ChatColor.GRAY + "Waiting for Players");
			descricao.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "- Players: " + ChatColor.YELLOW + a4players);
			bussolaim.setLore(descricao);
			bussola.setItemMeta(bussolaim);
			i.setItem(22, bussola);
			Main.plugin.itemip.put(bussola, "a4.eu.emitows.com");
			Main.plugin.itemd.put(bussola, "sim");

		} else if (a4motd.contains("progresso")) {
			ItemStack bussola = new ItemStack(Material.BOWL, 4);
			ItemMeta bussolaim = bussola.getItemMeta();
			ArrayList<String> descricao = new ArrayList<>();
			bussolaim.setDisplayName(ChatColor.DARK_PURPLE + "4");
			descricao.add(ChatColor.YELLOW + "HardCore Games:");
			descricao.add(ChatColor.RED + "" + ChatColor.BOLD + "IN PROGRESS");
			descricao.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "- Players: " + ChatColor.YELLOW + a4players);
			bussolaim.setLore(descricao);
			bussola.setItemMeta(bussolaim);
			i.setItem(22, bussola);
			Main.plugin.itemip.put(bussola, "a4.eu.emitows.com");
			Main.plugin.itemd.put(bussola, "nao");
		} else {
			ItemStack bussola = new ItemStack(Material.getMaterial(160), (short) 4, (short) 15);
			ItemMeta bussolaim = bussola.getItemMeta();
			ArrayList<String> descricao = new ArrayList<>();
			bussolaim.setDisplayName(ChatColor.DARK_PURPLE + "4");
			descricao.add(ChatColor.YELLOW + "HardCore Games:");
			descricao.add(ChatColor.RED + "" + ChatColor.BOLD + "OFFLINE");
			bussolaim.setLore(descricao);
			bussola.setItemMeta(bussolaim);
			i.setItem(22, bussola);
		}

		if (a3motd.contains("Comeca")) {
			ItemStack bussola = new ItemStack(Material.MUSHROOM_SOUP, 3);
			ItemMeta bussolaim = bussola.getItemMeta();
			ArrayList<String> descricao = new ArrayList<>();
			bussolaim.setDisplayName(ChatColor.DARK_PURPLE + "3");
			descricao.add(ChatColor.YELLOW + "HardCore Games:");
			descricao.add(ChatColor.GREEN + "" + ChatColor.BOLD + "AVAILABLE");
			descricao.add(ChatColor.GRAY + "Starting in: " + ChatColor.GREEN + a3motd.replace("Comeca em ", "").replaceAll(" minutos", ":00").replaceAll(" minuto", ":00").replaceAll(" segundo", "").replace(" segundos", "").replace("\n", "").replaceAll("Visite", "").replaceAll("freehg.com", ""));
			descricao.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "- Players: " + ChatColor.YELLOW + a3players);
			bussolaim.setLore(descricao);
			bussola.setItemMeta(bussolaim);
			i.setItem(21, bussola);
			Main.plugin.itemip.put(bussola, "a3.eu.emitows.com");
			Main.plugin.itemd.put(bussola, "sim");
		} else if (a3motd.contains("Esperando")) {
			ItemStack bussola = new ItemStack(Material.MUSHROOM_SOUP, 3);
			ItemMeta bussolaim = bussola.getItemMeta();
			ArrayList<String> descricao = new ArrayList<>();
			bussolaim.setDisplayName(ChatColor.DARK_PURPLE + "3");
			descricao.add(ChatColor.YELLOW + "HardCore Games:");
			descricao.add(ChatColor.GREEN + "" + ChatColor.BOLD + "AVAILABLE");
			descricao.add(ChatColor.GRAY + "Waiting for Players");
			descricao.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "- Players: " + ChatColor.YELLOW + a3players);
			bussolaim.setLore(descricao);
			bussola.setItemMeta(bussolaim);
			i.setItem(21, bussola);
			Main.plugin.itemip.put(bussola, "a3.eu.emitows.com");
			Main.plugin.itemd.put(bussola, "sim");
		} else if (a3motd.contains("progresso")) {
			ItemStack bussola = new ItemStack(Material.BOWL, 3);
			ItemMeta bussolaim = bussola.getItemMeta();
			ArrayList<String> descricao = new ArrayList<>();
			bussolaim.setDisplayName(ChatColor.DARK_PURPLE + "3");
			descricao.add(ChatColor.YELLOW + "HardCore Games:");
			descricao.add(ChatColor.RED + "" + ChatColor.BOLD + "IN PROGRESS");
			descricao.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "- Players: " + ChatColor.YELLOW + a3players);
			bussolaim.setLore(descricao);
			bussola.setItemMeta(bussolaim);
			i.setItem(21, bussola);
			Main.plugin.itemip.put(bussola, "a3.eu.emitows.com");
			Main.plugin.itemd.put(bussola, "nao");

		} else {
			ItemStack bussola = new ItemStack(Material.getMaterial(160), (short) 3, (short) 15);
			ItemMeta bussolaim = bussola.getItemMeta();
			ArrayList<String> descricao = new ArrayList<>();
			bussolaim.setDisplayName(ChatColor.DARK_PURPLE + "3");
			descricao.add(ChatColor.YELLOW + "HardCore Games:");
			descricao.add(ChatColor.RED + "" + ChatColor.BOLD + "OFFLINE");
			bussolaim.setLore(descricao);
			bussola.setItemMeta(bussolaim);
			i.setItem(21, bussola);
		}

		if (a2motd.contains("Comeca")) {
			ItemStack bussola = new ItemStack(Material.MUSHROOM_SOUP, 2);
			ItemMeta bussolaim = bussola.getItemMeta();
			ArrayList<String> descricao = new ArrayList<>();
			bussolaim.setDisplayName(ChatColor.DARK_PURPLE + "2");
			descricao.add(ChatColor.YELLOW + "HardCore Games:");
			descricao.add(ChatColor.GREEN + "" + ChatColor.BOLD + "AVAILABLE");
			descricao.add(ChatColor.GRAY + "Starting in: " + ChatColor.GREEN + a2motd.replace("Comeca em ", "").replaceAll(" minutos", ":00").replaceAll(" minuto", ":00").replaceAll(" segundo", "").replace(" segundos", "").replace("\n", "").replaceAll("Visite", "").replaceAll("freehg.com", ""));
			descricao.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "- Players: " + ChatColor.YELLOW + a2players);
			bussolaim.setLore(descricao);
			bussola.setItemMeta(bussolaim);
			i.setItem(20, bussola);
			Main.plugin.itemip.put(bussola, "a2.eu.emitows.com");
			Main.plugin.itemd.put(bussola, "sim");

		} else if (a2motd.contains("Esperando")) {
			ItemStack bussola = new ItemStack(Material.MUSHROOM_SOUP, 2);
			ItemMeta bussolaim = bussola.getItemMeta();
			ArrayList<String> descricao = new ArrayList<>();
			bussolaim.setDisplayName(ChatColor.DARK_PURPLE + "2");
			descricao.add(ChatColor.YELLOW + "HardCore Games:");
			descricao.add(ChatColor.GREEN + "" + ChatColor.BOLD + "AVAILABLE");
			descricao.add(ChatColor.GRAY + "Waiting for Players");
			descricao.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "- Players: " + ChatColor.YELLOW + a2players);
			bussolaim.setLore(descricao);
			bussola.setItemMeta(bussolaim);
			i.setItem(20, bussola);
			Main.plugin.itemip.put(bussola, "a2.eu.emitows.com");
			Main.plugin.itemd.put(bussola, "sim");

		} else if (a2motd.contains("progresso")) {
			ItemStack bussola = new ItemStack(Material.BOWL, 2);
			ItemMeta bussolaim = bussola.getItemMeta();
			ArrayList<String> descricao = new ArrayList<>();
			bussolaim.setDisplayName(ChatColor.DARK_PURPLE + "2");
			descricao.add(ChatColor.YELLOW + "HardCore Games:");
			descricao.add(ChatColor.RED + "" + ChatColor.BOLD + "IN PROGRESS");
			descricao.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "- Players: " + ChatColor.YELLOW + a2players);
			bussolaim.setLore(descricao);
			bussola.setItemMeta(bussolaim);
			i.setItem(20, bussola);
			Main.plugin.itemip.put(bussola, "a2.eu.emitows.com");
			Main.plugin.itemd.put(bussola, "nao");
		} else {
			ItemStack bussola = new ItemStack(Material.getMaterial(160), (short) 2, (short) 15);
			ItemMeta bussolaim = bussola.getItemMeta();
			ArrayList<String> descricao = new ArrayList<>();
			bussolaim.setDisplayName(ChatColor.DARK_PURPLE + "2");
			descricao.add(ChatColor.YELLOW + "HardCore Games:");
			descricao.add(ChatColor.RED + "" + ChatColor.BOLD + "OFFLINE");
			bussolaim.setLore(descricao);
			bussola.setItemMeta(bussolaim);
			i.setItem(20, bussola);
		}

		if (a1motd.contains("Comeca")) {
			ItemStack bussola = new ItemStack(Material.MUSHROOM_SOUP, 1);

			ItemMeta bussolaim = bussola.getItemMeta();
			ArrayList<String> descricao = new ArrayList<>();
			bussolaim.setDisplayName(ChatColor.DARK_PURPLE + "1");
			descricao.add(ChatColor.YELLOW + "HardCore Games:");
			descricao.add(ChatColor.GREEN + "" + ChatColor.BOLD + "AVAILABLE");
			descricao.add(ChatColor.GRAY + "Starting in: " + ChatColor.GREEN + a1motd.replace("Comeca em ", "").replaceAll(" minutos", ":00").replaceAll(" minuto", ":00").replaceAll(" segundo", "").replace(" segundos", "").replace("\n", "").replaceAll("Visite", "").replaceAll("freehg.com", ""));
			descricao.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "- Players: " + ChatColor.YELLOW + a1players);
			bussolaim.setLore(descricao);
			bussola.setItemMeta(bussolaim);
			i.setItem(19, bussola);
			Main.plugin.itemip.put(bussola, "a1.eu.emitows.com");
			Main.plugin.itemd.put(bussola, "sim");
		} else if (a1motd.contains("Esperando")) {
			ItemStack bussola = new ItemStack(Material.MUSHROOM_SOUP, 1);

			ItemMeta bussolaim = bussola.getItemMeta();
			ArrayList<String> descricao = new ArrayList<>();
			bussolaim.setDisplayName(ChatColor.DARK_PURPLE + "1");
			descricao.add(ChatColor.YELLOW + "HardCore Games:");
			descricao.add(ChatColor.GREEN + "" + ChatColor.BOLD + "AVAILABLE");
			descricao.add(ChatColor.GRAY + "Waiting for Players");
			descricao.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "- Players: " + ChatColor.YELLOW + a1players);
			bussolaim.setLore(descricao);
			bussola.setItemMeta(bussolaim);
			i.setItem(19, bussola);
			Main.plugin.itemip.put(bussola, "a1.eu.emitows.com");
			Main.plugin.itemd.put(bussola, "sim");
		} else if (a1motd.contains("progresso")) {
			ItemStack bussola = new ItemStack(Material.BOWL, 1);
			ItemMeta bussolaim = bussola.getItemMeta();
			ArrayList<String> descricao = new ArrayList<>();
			bussolaim.setDisplayName(ChatColor.DARK_PURPLE + "1");
			descricao.add(ChatColor.YELLOW + "HardCore Games:");
			descricao.add(ChatColor.RED + "" + ChatColor.BOLD + "IN PROGRESS");
			descricao.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "- Players: " + ChatColor.YELLOW + a1players);
			bussolaim.setLore(descricao);
			bussola.setItemMeta(bussolaim);
			i.setItem(19, bussola);
			Main.plugin.itemip.put(bussola, "a1.eu.emitows.com");
			Main.plugin.itemd.put(bussola, "nao");
		} else {
			ItemStack bussola = new ItemStack(Material.getMaterial(160), (short) 1, (short) 15);
			ItemMeta bussolaim = bussola.getItemMeta();
			ArrayList<String> descricao = new ArrayList<>();
			bussolaim.setDisplayName(ChatColor.DARK_PURPLE + "1");
			descricao.add(ChatColor.YELLOW + "HardCore Games:");
			descricao.add(ChatColor.RED + "" + ChatColor.BOLD + "OFFLINE");
			bussolaim.setLore(descricao);
			bussola.setItemMeta(bussolaim);
			i.setItem(19, bussola);
		}

		ItemStack star2 = new ItemStack(Material.CHEST);
		ItemMeta starim2 = star2.getItemMeta();
		starim2.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Return to menu");
		star2.setItemMeta(starim2);

		ItemStack star = new ItemStack(Material.NETHER_STAR);
		ItemMeta starim = star.getItemMeta();
		ArrayList<String> stardescricao = new ArrayList<>();
		starim.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "RANDOM IP");
		stardescricao.add(ChatColor.YELLOW + "FreeHG:");
		stardescricao.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "- Click to join a random HG server");
		stardescricao.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "- Players: " + ChatColor.YELLOW + Main.plugin.freehgcouting);
		starim.setLore(stardescricao);
		star.setItemMeta(starim);

		i.setItem(13, star);
		i.setItem(0, star2);

		ItemStack[] arrayOfItemStack;
		arrayOfItemStack = i.getContents();
		int vidros = i.getContents().length;
		for (int metavidros = 0; metavidros < vidros; metavidros++) {
			ItemStack item2 = arrayOfItemStack[metavidros];
			if (item2 == null) {

				i.setItem(i.firstEmpty(), new ItemStack(Material.getMaterial(160), (short) 1, (short) 11));
			}

		}
	}
}
