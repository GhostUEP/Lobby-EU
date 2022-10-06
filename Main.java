package me.ghost;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import me.flame.utils.permissions.PermissionManager;
import me.flame.utils.permissions.enums.Group;
import me.ghost.API.barapi.BarAPI;
import me.ghost.admin.Mode;
import me.ghost.admin.Vanish;
import me.ghost.commands.Admin;
import me.ghost.commands.ModTeleport;
import me.ghost.commands.Spawn;
import me.ghost.events.HGCountEvent;
import me.ghost.events.TimeSecondEvent;
import me.ghost.listener.DamagerFixer;
import me.ghost.listener.DeathListener;
import me.ghost.listener.ServerListener;
import me.ghost.manager.Permissions;
import me.ghost.manager.ServerManager;
import net.minecraft.util.com.google.common.io.ByteArrayDataInput;
import net.minecraft.util.com.google.common.io.ByteArrayDataOutput;
import net.minecraft.util.com.google.common.io.ByteStreams;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import de.inventivegames.hologram.HologramListeners;
import de.inventivegames.hologram.Hologram;

public class Main extends JavaPlugin implements PluginMessageListener {
	public static Main plugin;
	public int globalcouting = 0;
	public int freehgcouting = 0;
	public int lobbycouting = 0;
	public ArrayList<UUID> ispvp = new ArrayList<>();
	public ArrayList<UUID> wait = new ArrayList<>();
	public ArrayList<UUID> hideall = new ArrayList<>();
	public PermissionManager PM;
	public ServerManager SM;
	public File confFile = new File(getDataFolder().getPath(), "config.yml");
	public FileConfiguration conf;
	public String motd = "Lobby";
	public boolean onlyvip;
	public String barapi = "Sem Menssagem";
	public int pvprange = 0;
	public Permissions perm;
	public Mode adm = new Mode(this);
	public Vanish vanish = new Vanish(this);
	public HashMap<UUID, Integer> kd = new HashMap<>();
	public HashMap<UUID, Inventory> freehgi = new HashMap<>();
	public HashMap<UUID, String> lastip = new HashMap<>();
	public HashMap<String, String> servers = new HashMap<>();
	public HashMap<ItemStack, String> itemip = new HashMap<>();
	public HashMap<ItemStack, String> itemd = new HashMap<>();
	public HashMap<UUID, Hologram> holos = new HashMap<>();
	public boolean isHolosSpawned;

	public void onEnable() {
		plugin = this;
		isHolosSpawned = false;
		perm = new Permissions(this);
		RegistrarComandos();
		RegistrarEventos();
		getServer().getPluginManager().registerEvents(new HologramListeners(), this);
		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);
		Bukkit.getWorld("Spawn4").setSpawnLocation(0, 23, 0);
		if (!confFile.exists())
			saveResource("config.yml", false);
		updateConfig();
		try {
			World w = Bukkit.getWorld("Spawn4");
			if (w.hasStorm()) {
				w.setStorm(false);
			}
		} catch (Exception localException) {
		}

		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {

			public void run() {
				Bukkit.getPluginManager().callEvent(new TimeSecondEvent());

			}
		}, 1, 20 * 3);
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {

			public void run() {
				Bukkit.getPluginManager().callEvent(new HGCountEvent());

			}
		}, 1, 20 * 1);

	}

	public void onDisable() {
		ispvp.clear();
		isHolosSpawned = false;
	}

	public void updateConfig() {
		conf = YamlConfiguration.loadConfiguration(confFile);
		motd = conf.getString("motd");
		pvprange = conf.getInt("pvprange");
		barapi = conf.getString("barapi");
		onlyvip = conf.getBoolean("onlyvip");
	}

	public void MandarParaAdmins(String hack) {
		for (World w : Bukkit.getWorlds()) {
			for (Player mods : w.getPlayers()) {
				if (mods.hasPermission("PixelHG.admin")) {
					mods.sendMessage(hack);
				}
			}
		}
	}

	@Override
	public void onPluginMessageReceived(String channel, Player player, byte[] message) {
		if (!channel.equals("BungeeCord")) {
			return;
		}

		ByteArrayDataInput in = ByteStreams.newDataInput(message);
		String subchannel = in.readUTF();
		if (subchannel.equals("GetLast")) {
			String server = in.readUTF();
			String uuid = in.readUTF();

			UUID p = UUID.fromString(uuid);
			lastip.put(p, server);

		}
		if (subchannel.equals("GetStatus")) {
			String server = in.readUTF();
			String onoff = in.readUTF();

			servers.put(server, onoff);

		}
		if (subchannel.equals("GetAllCounts")) {
			int freehg = in.readInt();
			int lobby = in.readInt();
			Main.plugin.freehgcouting = freehg;
			Main.plugin.lobbycouting = lobby;
		}
		if (subchannel.equals("HGCount")) {
			String server = in.readUTF();
			String motd = in.readUTF();
			int playerCount = in.readInt();
			if (server.equalsIgnoreCase("a1.eu.emitows.com")) {
				ServerManager.a1players = playerCount;
				ServerManager.a1motd = motd;
			}
			if (server.equalsIgnoreCase("a2.eu.emitows.com")) {
				ServerManager.a2players = playerCount;
				ServerManager.a2motd = motd;
			}
			if (server.equalsIgnoreCase("a3.eu.emitows.com")) {
				ServerManager.a3players = playerCount;
				ServerManager.a3motd = motd;
			}
			if (server.equalsIgnoreCase("a4.eu.emitows.com")) {
				ServerManager.a4players = playerCount;
				ServerManager.a4motd = motd;
			}
			if (server.equalsIgnoreCase("a5.eu.emitows.com")) {
				ServerManager.a5players = playerCount;
				ServerManager.a5motd = motd;
			}
			if (server.equalsIgnoreCase("a6.eu.emitows.com")) {
				ServerManager.a6players = playerCount;
				ServerManager.a6motd = motd;
			}
			if (server.equalsIgnoreCase("a7.eu.emitows.com")) {
				ServerManager.a7players = playerCount;
				ServerManager.a7motd = motd;
			}
			if (server.equalsIgnoreCase("a8.eu.emitows.com")) {
				ServerManager.a8players = playerCount;
				ServerManager.a8motd = motd;
			}
			if (server.equalsIgnoreCase("a9.eu.emitows.com")) {
				ServerManager.a9players = playerCount;
				ServerManager.a9motd = motd;
			}
			if (server.equalsIgnoreCase("b1.eu.emitows.com")) {
				ServerManager.a10players = playerCount;
				ServerManager.a10motd = motd;
			}
			if (server.equalsIgnoreCase("b2.eu.emitows.com")) {
				ServerManager.a11players = playerCount;
				ServerManager.a11motd = motd;
			}
			if (server.equalsIgnoreCase("b3.eu.emitows.com")) {
				ServerManager.a12players = playerCount;
				ServerManager.a12motd = motd;
			}
		}
		if (subchannel.equals("PlayerCount")) {

			String server = in.readUTF();
			int playerCount = in.readInt();

			Main.plugin.globalcouting = playerCount;

			if (server.equalsIgnoreCase("ALL")) {
				Main.plugin.globalcouting = playerCount;
			}

		}
		if (subchannel.equalsIgnoreCase("ModTeleport")) {
			String uuidStr = in.readUTF();
			if (!me.flame.utils.Main.getPlugin().getPermissionManager().hasGroupPermission(player, Group.TRIAL)) {
				player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "ERRO " + ChatColor.WHITE + "You don't have permission to do that.");
				return;
			}
			UUID uuid = UUID.fromString(uuidStr);
			Player p = Main.plugin.getServer().getPlayer(uuid);
			if (p == null) {
				player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "ERRO " + ChatColor.WHITE + "The players doesn't exist.");
				return;
			}
			Main.plugin.adm.setAdmin(player);
			player.teleport(p);
			player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "TELEPORTE " + ChatColor.WHITE + "Teleported to " + p.getName());
		}
		if (subchannel.equalsIgnoreCase("ChatStaff")) {
			String jogador = in.readUTF();
			String servidor = in.readUTF();
			String menssagem = in.readUTF();
			Main.plugin.MandarParaAdmins(ChatColor.GRAY + "[" + servidor.toUpperCase() + "]" + "[" + jogador + "]" + ChatColor.RED + "Staff: " + ChatColor.GRAY + menssagem);
		}
	}

	public String getLasIP(Player p) {
		String ip = "Nenhum";
		if (lastip.containsKey(p.getUniqueId())) {
			ip = lastip.get(p.getUniqueId());
		}

		return ip;
	}

	public boolean isServerOn(String server) {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("GetStatus");
		out.writeUTF(server);
		Main.plugin.getServer().sendPluginMessage(Main.plugin, "BungeeCord", out.toByteArray());
		boolean onoff = false;
		if (servers.containsKey(server)) {
			if (servers.get(server).equalsIgnoreCase("on")) {
				onoff = true;
			}
		}
		return onoff;
	}

	public void sendPlayerToServer(Player p) {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("Aleatorio");
		p.sendPluginMessage(Main.plugin, "BungeeCord", out.toByteArray());
	}

	public void sendPlayerToServerCA(Player p) {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("AleatorioCA");
		p.sendPluginMessage(Main.plugin, "BungeeCord", out.toByteArray());
	}

	public int getKills(Player p) {
		int kills = 0;
		if (Main.plugin.kd.containsKey(p.getUniqueId())) {
			kills = Main.plugin.kd.get(p.getUniqueId()).intValue();
		}
		return kills;
	}

	public void sendStaffChat(Player p, String txt) {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("ChatStaff");
		out.writeUTF(txt);
		p.sendPluginMessage(Main.plugin, "BungeeCord", out.toByteArray());
	}

	public void sendBungee(String type) {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("Menssagens");
		out.writeUTF(type);
		Main.plugin.getServer().sendPluginMessage(Main.plugin, "BungeeCord", out.toByteArray());
	}

	public void sendPlayerToTheServer(Player p, String server) {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("Connect");
		out.writeUTF(server);

		p.sendPluginMessage(Main.plugin, "BungeeCord", out.toByteArray());
	}

	public void getCount() {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("PlayerCount");
		out.writeUTF("ALL");

		Main.plugin.getServer().sendPluginMessage(Main.plugin, "BungeeCord", out.toByteArray());

	}

	public void getAllCount() {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("GetAllCounts");
		Main.plugin.getServer().sendPluginMessage(Main.plugin, "BungeeCord", out.toByteArray());

	}

	public void RegistrarComandos() {
		Main.plugin.getCommand("admin").setExecutor(new Admin());
		Main.plugin.getCommand("spawn").setExecutor(new Spawn());
		Main.plugin.getCommand("modteleport").setExecutor(new ModTeleport());
	}

	public void RegistrarEventos() {
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(new ServerListener(), Main.plugin);
		pm.registerEvents(new DamagerFixer(), Main.plugin);
		pm.registerEvents(new DeathListener(), Main.plugin);
		pm.registerEvents(new BarAPI(this), this);

	}
}
