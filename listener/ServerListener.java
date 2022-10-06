package me.ghost.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

import me.flame.utils.permissions.enums.Group;
import me.ghost.Main;
import me.ghost.API.barapi.BarAPI;
import me.ghost.events.HGCountEvent;
import me.ghost.events.TimeSecondEvent;
import me.ghost.manager.CooldownManager;
import me.ghost.manager.ServerManager;
import net.minecraft.server.v1_7_R4.EntityPlayer;
import net.minecraft.util.com.google.common.io.ByteArrayDataOutput;
import net.minecraft.util.com.google.common.io.ByteStreams;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import de.inventivegames.hologram.Hologram;
import de.inventivegames.hologram.HologramAPI;

public class ServerListener implements Listener {
	private HashMap<UUID, Integer> mute = new HashMap<UUID, Integer>();
	private ArrayList<UUID> jump = new ArrayList<>();

	@SuppressWarnings("deprecation")
	@EventHandler
	public void tudo(TimeSecondEvent e) {
		Main.plugin.updateConfig();
		Main.plugin.getCount();
		Main.plugin.getAllCount();
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (Main.plugin.holos.containsKey(p.getUniqueId())) {
				Hologram holo = Main.plugin.holos.get(p.getUniqueId());
				if (holo != null) {
					holo.setText(ChatColor.GREEN + "" + ChatColor.BOLD + "FreeHG-EU");
					if (!holo.isSpawned())
						holo.spawn();
					if (holo.getLineBelow().isSpawned())
						holo.getLineBelow()
								.setText(ChatColor.GRAY + "Players: " + ChatColor.YELLOW + Main.plugin.freehgcouting);
					else {
						holo.getLineBelow().spawn();
						holo.getLineBelow()
								.setText(ChatColor.GRAY + "Players: " + ChatColor.YELLOW + Main.plugin.freehgcouting);
					}
				}
			}
		}

	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void HGCount(HGCountEvent e) {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("HGCount");
		Main.plugin.getServer().sendPluginMessage(Main.plugin, "BungeeCord", out.toByteArray());
		for (Player p : Bukkit.getOnlinePlayers()) {
			ServerManager.openHGMenu(p);
		}
	}

	@EventHandler
	public void onFolhas(LeavesDecayEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void Inventario(PlayerInteractEntityEvent event) {
		if (Main.plugin.adm.isAdmin(event.getPlayer())) {
			if (event.getRightClicked() instanceof Player) {
				event.getPlayer().openInventory(((Player) event.getRightClicked()).getInventory());
			}
		}
	}

	@EventHandler
	public void onQuebrar(BlockBreakEvent e) {
		Player p = e.getPlayer();
		if (me.flame.utils.Main.getPlugin().getPermissionManager().hasGroupPermission(p, Group.DONO)) {
			if (Main.plugin.ispvp.contains(p.getUniqueId())) {
				e.setCancelled(true);
			}
		} else {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void Explosion(EntityExplodeEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void Comida(FoodLevelChangeEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void ondropar(PlayerDropItemEvent e) {
		if (e.getItemDrop() == null)
			return;
		Player p = e.getPlayer();
		final Item i = e.getItemDrop();
		if (Main.plugin.ispvp.contains(p.getUniqueId())) {
			if (e.getItemDrop().getItemStack().getType() == Material.DIAMOND_SWORD) {
				e.setCancelled(true);
				p.updateInventory();
			}
			if (e.getItemDrop().getItemStack().getType() == Material.IRON_HELMET) {
				e.setCancelled(true);
				p.updateInventory();
			}
			if (e.getItemDrop().getItemStack().getType() == Material.IRON_CHESTPLATE) {
				e.setCancelled(true);
				p.updateInventory();
			}
			if (e.getItemDrop().getItemStack().getType() == Material.IRON_LEGGINGS) {
				e.setCancelled(true);
				p.updateInventory();
			}
			if (e.getItemDrop().getItemStack().getType() == Material.IRON_BOOTS) {
				e.setCancelled(true);
				p.updateInventory();
			}
			if (e.getItemDrop().getItemStack().getType() == Material.MUSHROOM_SOUP) {
				e.setCancelled(true);
				p.updateInventory();
			}
			if (i.getItemStack().getType() == Material.BOWL) {
				new BukkitRunnable() {

					@Override
					public void run() {
						i.remove();

					}
				}.runTaskLater(Main.plugin, 2 * 20L);
				p.updateInventory();

			}
		} else {
			e.setCancelled(true);
		}

	}

	@EventHandler
	public void dano(EntityDamageEvent e) {
		if (!(e.getEntity() instanceof Player))
			return;
		Player p = (Player) e.getEntity();
		if (!Main.plugin.ispvp.contains(p.getUniqueId())) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void pegar(PlayerPickupItemEvent e) {
		if (!Main.plugin.adm.isAdmin(e.getPlayer())) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void dano2(EntityDamageByEntityEvent e) {
		if (!(e.getEntity() instanceof Player))
			return;
		if (!(e.getDamager() instanceof Player))
			return;
		Player p = (Player) e.getEntity();
		Player deudano = (Player) e.getDamager();

		if (!(Main.plugin.ispvp.contains(p.getUniqueId()) && Main.plugin.ispvp.contains(deudano.getUniqueId()))) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void queimar(BlockBurnEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void queimar2(BlockIgniteEvent e) {
		e.setCancelled(true);
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void mover(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if (Main.plugin.wait.contains(p.getUniqueId())) {
			Main.plugin.wait.remove(p.getUniqueId());
			p.sendMessage(ChatColor.RED + "VOCÊ SE MEXEU!!! Sua volta a base foi cancelada.");
		}
		if (p.getLocation().getY() <= Main.plugin.pvprange) {
			if (p.isOnGround()) {
				if (!Main.plugin.adm.isAdmin(p)) {
					if (!Main.plugin.ispvp.contains(p.getUniqueId())) {
						Main.plugin.ispvp.add(p.getUniqueId());
						p.sendMessage(ChatColor.GREEN + "PvP Mode ON!!!");
						p.setAllowFlight(false);
						p.setFlying(false);
						p.getInventory().clear();
						p.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
						p.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
						p.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
						p.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));

						ItemStack espada = new ItemStack(Material.DIAMOND_SWORD);
						espada.addEnchantment(Enchantment.DAMAGE_ALL, 1);
						p.getInventory().setItem(0, espada);
						int server = 0;
						ItemStack[] arrayOfItemStack;
						arrayOfItemStack = p.getInventory().getContents();
						int vidros = p.getInventory().getContents().length;
						for (int metavidros = 0; metavidros < vidros; metavidros++) {
							ItemStack item2 = arrayOfItemStack[metavidros];
							if (item2 == null) {
								if (server < 17 + Main.plugin.getKills(p)) {
									server++;
									p.getInventory().setItem(p.getInventory().firstEmpty(),
											new ItemStack(Material.MUSHROOM_SOUP));
								}
							}

						}
						p.getInventory().setHeldItemSlot(0);
						BarAPI.setMessage(p, ChatColor.RED + "Your kills" + ChatColor.GRAY + ": " + ChatColor.WHITE
								+ Main.plugin.getKills(p));
					}
				}
			}
		}
		Block b = p.getLocation().getBlock();
		Material m = Material.getMaterial(171);
		if (b.getRelative(BlockFace.UP).getType() == m) {
			if (!jump.contains(p.getUniqueId())) {
				Vector v1 = p.getLocation().getDirection().multiply(7.5D).setY(1.20D);
				p.setVelocity(v1);
				p.playSound(p.getLocation(), Sound.GHAST_FIREBALL, 2, 1);
				jump.add(p.getUniqueId());
			}
		}
		if (jump.contains(p.getUniqueId())) {
			if (p.isOnGround()) {
				jump.remove(p.getUniqueId());
			}
		}
	}

	@EventHandler
	public void onclicar(InventoryClickEvent e) {
		if (!(e.getWhoClicked() instanceof Player))
			return;
		Player p = (Player) e.getWhoClicked();
		if (e.getInventory() == null)
			return;
		if (!Main.plugin.ispvp.contains(p.getUniqueId()) && !Main.plugin.perm.isOwner(p)) {
			if (e.getInventory() != null) {
				e.setCancelled(true);
			}

		}

		if (e.getInventory().getName().contains("MiniGames")) {
			if (e.getCurrentItem() == null)
				return;
			if (e.getCurrentItem().hasItemMeta()
					&& e.getCurrentItem().getItemMeta().getDisplayName().contains("FreeHG")) {
				ServerManager.openHGMenu(p);
				p.openInventory(Main.plugin.freehgi.get(p.getUniqueId()));
			}
			if (e.getCurrentItem().hasItemMeta()
					&& e.getCurrentItem().getItemMeta().getDisplayName().contains("CraftCombat")) {
				Main.plugin.sendPlayerToTheServer(p, "craftcombat.eu.emitows.com");
			}
			if (e.getCurrentItem().hasItemMeta()
					&& e.getCurrentItem().getItemMeta().getDisplayName().contains("CorridaArmada")) {
				if (Main.plugin.onlyvip) {
					if (!Main.plugin.perm.isLight(p)) {
						p.sendMessage(ChatColor.RED + "Only VIPs can test this MiniGame");
						p.sendMessage(ChatColor.RED + "Buy VIP at:");
						p.sendMessage(ChatColor.GREEN + "www.emitows.com");
						return;
					}
					Main.plugin.sendPlayerToServerCA(p);
				} else {
					Main.plugin.sendPlayerToServerCA(p);
				}
			}
			e.setCancelled(true);
		}
		if (e.getInventory().getName().contains("FreeHG")) {
			if (e.getCurrentItem() == null)
				return;
			if (e.getCurrentItem().hasItemMeta()
					&& e.getCurrentItem().getItemMeta().getDisplayName().contains("RANDOM IP")) {
				Main.plugin.sendPlayerToServer(p);
				p.sendMessage(ChatColor.RED + "Connecting...");
			}
			if (e.getCurrentItem().hasItemMeta()
					&& e.getCurrentItem().getItemMeta().getDisplayName().toLowerCase().contains("return to menu")) {
				ServerManager.openMenu(p);
			}
			if (Main.plugin.itemip.containsKey(e.getCurrentItem())) {
				if (Main.plugin.itemd.containsKey(e.getCurrentItem())) {
					if (e.getCurrentItem().hasItemMeta()) {
						if (Main.plugin.itemd.get(e.getCurrentItem()).equalsIgnoreCase("sim")) {
							p.sendMessage(ChatColor.YELLOW + "Connecting...");
							Main.plugin.sendPlayerToTheServer(p, Main.plugin.itemip.get(e.getCurrentItem()));
						} else if (Main.plugin.itemd.get(e.getCurrentItem()).equalsIgnoreCase("nao")) {
							if (Main.plugin.perm.isUltimate(p)) {
								p.sendMessage(ChatColor.YELLOW + "Connecting...");
								Main.plugin.sendPlayerToTheServer(p, Main.plugin.itemip.get(e.getCurrentItem()));
							} else {
								p.sendMessage(ChatColor.RED + "Server in Progress");
							}
						} else {
							p.sendMessage(ChatColor.RED + "Server Offline");
						}
					}
				}
			}
			e.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onWeatherChange(WeatherChangeEvent e) {
		final World w = Bukkit.getWorld("Spawn4");
		if (!w.hasStorm()) {
			e.setCancelled(true);
		}
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
			public void run() {
				try {
					if (w.hasStorm()) {
						w.setStorm(false);
					}
				} catch (Exception localException) {
				}
			}
		}, 5L);
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPing(ServerListPingEvent e) {
		Main.plugin.updateConfig();
		e.setMotd(Main.plugin.motd.replaceAll("&", "§"));
		e.setMaxPlayers(Bukkit.getOnlinePlayers().length + 1);

	}

	@EventHandler
	public void Sopa(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		if (e.getAction().name().contains("RIGHT")) {
			ItemStack hand = player.getItemInHand();
			if ((hand != null) && (hand.getType() == Material.MUSHROOM_SOUP)) {
				EntityPlayer p = ((CraftPlayer) player).getHandle();
				if (p.getHealth() < p.getMaxHealth()) {
					if (p.getHealth() + 7 >= p.getMaxHealth()) {
						p.setHealth(p.getMaxHealth());
						player.getItemInHand().setType(Material.BOWL);
						player.updateInventory();
					} else {
						p.setHealth(p.getHealth() + 7);
						player.getItemInHand().setType(Material.BOWL);
						player.updateInventory();
					}
				} else if (player.getFoodLevel() < 20) {
					if (player.getFoodLevel() + 6 < 20.0F) {
						player.setFoodLevel((int) (player.getFoodLevel() + 6));
					} else {
						player.setFoodLevel((int) (player.getFoodLevel() + 6));
					}
					player.getItemInHand().setType(Material.BOWL);
					player.updateInventory();
				} else {
					player.getFoodLevel();
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void Interact(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		ItemStack i = p.getItemInHand();
		if (i != null) {
			if (i.hasItemMeta()) {
				if (e.getAction().name().contains("RIGHT") || e.getAction().name().contains("LEFT")) {
					if (p.getItemInHand().getType() == Material.COMPASS
							&& p.getItemInHand().getItemMeta().getDisplayName().contains("Choose a MiniGame")) {
						e.setCancelled(true);
						ServerManager.openMenu(p);
						return;
					}
					if (p.getItemInHand().getType() == Material.getMaterial(347)
							&& p.getItemInHand().getItemMeta().getDisplayName().contains("Your last IP")) {
						e.setCancelled(true);
						Main.plugin.sendPlayerToTheServer(p, Main.plugin.getLasIP(p));
						p.sendMessage(ChatColor.RED + "Connecting...");
						return;
					}
					if (p.getItemInHand().getType() == Material.EYE_OF_ENDER
							|| p.getItemInHand().getType() == Material.ENDER_PEARL) {
						e.setCancelled(true);
						if (CooldownManager.isInCooldown(p.getUniqueId(), "hideall")) {
							p.sendMessage(ChatColor.RED + "Espere para usar isso de novo");
							return;
						}
						if (Main.plugin.hideall.contains(p.getUniqueId())) {
							CooldownManager c = new CooldownManager(p.getUniqueId(), "hideall", 5);
							if (!CooldownManager.isInCooldown(p.getUniqueId(), "hideall")) {
								c.start();
							}
							ItemStack bussola2 = new ItemStack(Material.ENDER_PEARL);
							ItemMeta bussolaim2 = bussola2.getItemMeta();
							ArrayList<String> descricao2 = new ArrayList<>();
							bussolaim2.setDisplayName(
									ChatColor.DARK_PURPLE + "Esconder Players: " + ChatColor.RED + "DESATIVADO");
							descricao2.add(ChatColor.GRAY + "Esconda todos os players do Servidor");
							bussolaim2.setLore(descricao2);
							bussola2.setItemMeta(bussolaim2);
							p.setItemInHand(bussola2);
							if (Main.plugin.hideall.contains(p.getUniqueId())) {
								Main.plugin.hideall.remove(p.getUniqueId());
							}
							Main.plugin.vanish.updateVanished();
							p.sendMessage(ChatColor.GREEN + "Todos os players foram revelados");
						} else {

							CooldownManager c = new CooldownManager(p.getUniqueId(), "hideall", 5);
							if (!CooldownManager.isInCooldown(p.getUniqueId(), "hideall")) {
								c.start();
							}
							ItemStack bussola2 = new ItemStack(Material.EYE_OF_ENDER);
							ItemMeta bussolaim2 = bussola2.getItemMeta();
							ArrayList<String> descricao2 = new ArrayList<>();
							bussolaim2.setDisplayName(
									ChatColor.DARK_PURPLE + "Esconder Players: " + ChatColor.GREEN + "ATIVADO");
							descricao2.add(ChatColor.GRAY + "Esconda todos os players do Servidor");
							bussolaim2.setLore(descricao2);
							bussola2.setItemMeta(bussolaim2);
							p.setItemInHand(bussola2);
							Main.plugin.hideall.add(p.getUniqueId());
							Main.plugin.vanish.hideAllPlayers(p);
							p.sendMessage(ChatColor.GREEN + "Todos os players foram escondidos");
						}
					}
				}
			}
		}

	}

	@EventHandler
	public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {

		final Player p = event.getPlayer();
		if (!Main.plugin.perm.isTrial(p)) {
			if (CooldownManager.isInCooldown(p.getUniqueId(), "mutado")) {
				event.setCancelled(true);
				int timeleft = CooldownManager.getTimeLeft(p.getUniqueId(), "mutado");
				p.sendMessage(ChatColor.RED + "Você está mutado por flood, espere: " + timeleft + " segundos");
				return;
			}
			if (!mute.containsKey(p.getUniqueId())) {
				mute.put(p.getUniqueId(), Integer.valueOf(1));
				new BukkitRunnable() {

					@Override
					public void run() {
						if (mute.containsKey(p.getUniqueId())) {
							if (mute.get(p.getUniqueId()).intValue() == 1) {
								mute.remove(p.getUniqueId());
							}
						}

					}
				}.runTaskLater(Main.plugin, 20 * 5);
			} else if (mute.get(p.getUniqueId()).intValue() == 1) {
				mute.put(p.getUniqueId(), mute.get(p.getUniqueId()).intValue() + 1);
				new BukkitRunnable() {

					@Override
					public void run() {
						if (mute.containsKey(p.getUniqueId())) {
							if (mute.get(p.getUniqueId()).intValue() == 2) {
								mute.remove(p.getUniqueId());
							}
						}

					}
				}.runTaskLater(Main.plugin, 20 * 5);
			} else if (mute.get(p.getUniqueId()).intValue() == 2) {
				mute.put(p.getUniqueId(), mute.get(p.getUniqueId()).intValue() + 1);
				new BukkitRunnable() {

					@Override
					public void run() {
						if (mute.containsKey(p.getUniqueId())) {
							if (mute.get(p.getUniqueId()).intValue() == 3) {
								mute.remove(p.getUniqueId());
							}
						}

					}
				}.runTaskLater(Main.plugin, 20 * 5);
			} else if (mute.get(p.getUniqueId()).intValue() == 3) {
				mute.put(p.getUniqueId(), mute.get(p.getUniqueId()).intValue() + 1);
				p.sendMessage(ChatColor.RED + "Espere para escrever de novo ou será mutado!");
				p.sendMessage(ChatColor.RED + "Você tem mais 1 aviso");
				new BukkitRunnable() {

					@Override
					public void run() {
						if (mute.containsKey(p.getUniqueId())) {
							if (mute.get(p.getUniqueId()).intValue() == 4) {
								mute.remove(p.getUniqueId());
							}
						}

					}
				}.runTaskLater(Main.plugin, 20 * 5);
			} else if (mute.get(p.getUniqueId()).intValue() == 4) {
				mute.put(p.getUniqueId(), mute.get(p.getUniqueId()).intValue() + 1);
				p.sendMessage(ChatColor.RED + "Espere para escrever de novo ou será mutado!");
				p.sendMessage(ChatColor.RED + "Último aviso");
				new BukkitRunnable() {

					@Override
					public void run() {
						if (mute.containsKey(p.getUniqueId())) {
							if (mute.get(p.getUniqueId()).intValue() == 5) {
								mute.remove(p.getUniqueId());
							}
						}

					}
				}.runTaskLater(Main.plugin, 20 * 5);
			} else if (mute.get(p.getUniqueId()).intValue() == 5) {
				p.sendMessage(ChatColor.RED + "Você foi mutado por 1 minuto por flood");
				event.setCancelled(true);
				if (!CooldownManager.isInCooldown(p.getUniqueId(), "mutado")) {
					CooldownManager c = new CooldownManager(p.getUniqueId(), "mutado", 60);
					c.start();
				}
				mute.remove(p.getUniqueId());
			}
		}
	}

	@EventHandler
	public void sair(PlayerQuitEvent e) {
		e.setQuitMessage(null);
		Player p = e.getPlayer();
		if (Main.plugin.ispvp.contains(p.getUniqueId())) {
			Main.plugin.ispvp.remove(p.getUniqueId());
		}
		if (Main.plugin.holos.containsKey(p.getUniqueId())) {
			HologramAPI.removeHologram(Main.plugin.holos.get(p.getUniqueId()));
			for (Hologram holo : Main.plugin.holos.get(p.getUniqueId()).getLines()) {
				HologramAPI.removeHologram(holo);
			}
			Main.plugin.holos.remove(p.getUniqueId());
		}
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onMorrer(PlayerDeathEvent e) {
		Player p = e.getEntity();

		Player matador = p.getKiller();
		e.setDeathMessage(null);
		e.getDrops().clear();
		if (Main.plugin.ispvp.contains(p.getUniqueId())) {
			if (Main.plugin.kd.containsKey(p.getUniqueId())) {
				if (matador instanceof Player) {
					if (Main.plugin.getKills(p) == 5) {
						Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "KILLSTREAK "
								+ ChatColor.RESET + ChatColor.YELLOW + "O Jogador " + ChatColor.GREEN
								+ matador.getName() + ChatColor.YELLOW + " Acabou com o KillStreak de " + ChatColor.RED
								+ Main.plugin.getKills(p) + " kills" + ChatColor.YELLOW + " de " + ChatColor.RED
								+ p.getName());
					}
					if (Main.plugin.getKills(p) == 10) {
						Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "KILLSTREAK "
								+ ChatColor.RESET + ChatColor.YELLOW + "O Jogador " + ChatColor.GREEN
								+ matador.getName() + ChatColor.YELLOW + " Acabou com o KillStreak de " + ChatColor.RED
								+ Main.plugin.getKills(p) + " kills" + ChatColor.YELLOW + " de " + ChatColor.RED
								+ p.getName());
					}
					if (Main.plugin.getKills(p) == 15) {
						Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "KILLSTREAK "
								+ ChatColor.RESET + ChatColor.YELLOW + "O Jogador " + ChatColor.GREEN
								+ matador.getName() + ChatColor.YELLOW + " Acabou com o KillStreak de " + ChatColor.RED
								+ Main.plugin.getKills(p) + " kills" + ChatColor.YELLOW + " de " + ChatColor.RED
								+ p.getName());
					}
					if (Main.plugin.getKills(p) == 25) {
						Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "KILLSTREAK "
								+ ChatColor.RESET + ChatColor.YELLOW + "O Jogador " + ChatColor.GREEN
								+ matador.getName() + ChatColor.YELLOW + " Acabou com o KillStreak de " + ChatColor.RED
								+ Main.plugin.getKills(p) + " kills" + ChatColor.YELLOW + " de " + ChatColor.RED
								+ p.getName());
					}
					if (Main.plugin.getKills(p) == 30) {
						Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "KILLSTREAK "
								+ ChatColor.RESET + ChatColor.YELLOW + "O Jogador " + ChatColor.GREEN
								+ matador.getName() + ChatColor.YELLOW + " Acabou com o KillStreak de " + ChatColor.RED
								+ Main.plugin.getKills(p) + " kills" + ChatColor.YELLOW + " de " + ChatColor.RED
								+ p.getName());
					}
					if (Main.plugin.getKills(p) == 40) {
						Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "KILLSTREAK "
								+ ChatColor.RESET + ChatColor.YELLOW + "O Jogador " + ChatColor.GREEN
								+ matador.getName() + ChatColor.YELLOW + " Acabou com o KillStreak de " + ChatColor.RED
								+ Main.plugin.getKills(p) + " kills" + ChatColor.YELLOW + " de " + ChatColor.RED
								+ p.getName());
					}
					if (Main.plugin.getKills(p) == 50) {
						Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "KILLSTREAK "
								+ ChatColor.RESET + ChatColor.YELLOW + "O Jogador " + ChatColor.GREEN
								+ matador.getName() + ChatColor.YELLOW + " Acabou com o KillStreak de " + ChatColor.RED
								+ Main.plugin.getKills(p) + " kills" + ChatColor.YELLOW + " de " + ChatColor.RED
								+ p.getName());
					}
				}
				Main.plugin.kd.remove(p.getUniqueId());
			}
			if (matador instanceof Player) {
				if (Main.plugin.kd.containsKey(matador.getUniqueId())) {
					Main.plugin.kd.put(matador.getUniqueId(), Main.plugin.kd.get(matador.getUniqueId()).intValue() + 1);
					if (Main.plugin.getKills(matador) == 5) {
						Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "KILLSTREAK "
								+ ChatColor.RESET + ChatColor.YELLOW + "The Player " + ChatColor.GREEN
								+ matador.getName() + ChatColor.YELLOW + " Has a KillStreak of " + ChatColor.RED
								+ Main.plugin.getKills(matador) + " kills.");
					}
					if (Main.plugin.getKills(matador) == 10) {
						Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "KILLSTREAK "
								+ ChatColor.RESET + ChatColor.YELLOW + "The Player " + ChatColor.GREEN
								+ matador.getName() + ChatColor.YELLOW + " Has a KillStreak of " + ChatColor.RED
								+ Main.plugin.getKills(matador) + " kills.");
					}
					if (Main.plugin.getKills(matador) == 15) {
						Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "KILLSTREAK "
								+ ChatColor.RESET + ChatColor.YELLOW + "The Player " + ChatColor.GREEN
								+ matador.getName() + ChatColor.YELLOW + " Has a KillStreak of " + ChatColor.RED
								+ Main.plugin.getKills(matador) + " kills.");
					}
					if (Main.plugin.getKills(matador) == 25) {
						Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "KILLSTREAK "
								+ ChatColor.RESET + ChatColor.YELLOW + "The Player " + ChatColor.GREEN
								+ matador.getName() + ChatColor.YELLOW + " Has a KillStreak of " + ChatColor.RED
								+ Main.plugin.getKills(matador) + " kills.");
					}
					if (Main.plugin.getKills(matador) == 30) {
						Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "KILLSTREAK "
								+ ChatColor.RESET + ChatColor.YELLOW + "The Player " + ChatColor.GREEN
								+ matador.getName() + ChatColor.YELLOW + " Has a KillStreak of " + ChatColor.RED
								+ Main.plugin.getKills(matador) + " kills.");
					}
					if (Main.plugin.getKills(matador) == 40) {
						Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "KILLSTREAK "
								+ ChatColor.RESET + ChatColor.YELLOW + "The Player " + ChatColor.GREEN
								+ matador.getName() + ChatColor.YELLOW + " Has a KillStreak of " + ChatColor.RED
								+ Main.plugin.getKills(matador) + " kills.");
					}
					if (Main.plugin.getKills(matador) == 50) {
						Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "KILLSTREAK "
								+ ChatColor.RESET + ChatColor.YELLOW + "The Player " + ChatColor.GREEN
								+ matador.getName() + ChatColor.YELLOW + " Has a KillStreak of " + ChatColor.RED
								+ Main.plugin.getKills(matador) + " kills.");
					}
					BarAPI.setMessage(matador, ChatColor.RED + "Your kills" + ChatColor.GRAY + ": " + ChatColor.WHITE
							+ Main.plugin.getKills(matador));
					int server = 0;
					ItemStack[] arrayOfItemStack;
					arrayOfItemStack = matador.getInventory().getContents();
					int vidros = matador.getInventory().getContents().length;
					for (int metavidros = 0; metavidros < vidros; metavidros++) {
						ItemStack item2 = arrayOfItemStack[metavidros];
						if (item2 == null) {
							if (server < 9 + Main.plugin.getKills(matador)) {
								server++;
								matador.getInventory().setItem(matador.getInventory().firstEmpty(),
										new ItemStack(Material.MUSHROOM_SOUP));
							}
						}

					}
					matador.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
					matador.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
					matador.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
					matador.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));

					matador.getInventory().getHelmet().setDurability((short) 0);
					matador.getInventory().getChestplate().setDurability((short) 0);
					matador.getInventory().getLeggings().setDurability((short) 0);
					matador.getInventory().getBoots().setDurability((short) 0);
					matador.playSound(matador.getLocation(), Sound.LEVEL_UP, 2, 1);
				} else {
					Main.plugin.kd.put(matador.getUniqueId(), Integer.valueOf(1));
					BarAPI.setMessage(matador, ChatColor.RED + "Suas kills" + ChatColor.GRAY + ": " + ChatColor.WHITE
							+ Main.plugin.getKills(matador));
					int server = 0;
					ItemStack[] arrayOfItemStack;
					arrayOfItemStack = matador.getInventory().getContents();
					int vidros = matador.getInventory().getContents().length;
					for (int metavidros = 0; metavidros < vidros; metavidros++) {
						ItemStack item2 = arrayOfItemStack[metavidros];
						if (item2 == null) {
							if (server < 9 + Main.plugin.getKills(matador)) {
								server++;
								matador.getInventory().setItem(matador.getInventory().firstEmpty(),
										new ItemStack(Material.MUSHROOM_SOUP));
							}
						}

					}
					matador.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
					matador.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
					matador.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
					matador.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));

					matador.getInventory().getHelmet().setDurability((short) 0);
					matador.getInventory().getChestplate().setDurability((short) 0);
					matador.getInventory().getLeggings().setDurability((short) 0);
					matador.getInventory().getBoots().setDurability((short) 0);
					matador.playSound(matador.getLocation(), Sound.LEVEL_UP, 2, 1);
				}
			}

		}
	}

	@EventHandler
	public void onDouble1(PlayerToggleFlightEvent e) {
		Player p = e.getPlayer();
		if (!Main.plugin.ispvp.contains(p.getUniqueId())) {
			if (p.getGameMode() == GameMode.CREATIVE)
				return;
			e.setCancelled(true);
			p.setAllowFlight(false);
			p.setFlying(false);
			p.setVelocity(p.getLocation().getDirection().multiply(1.5).setY(1));
			p.playSound(p.getLocation(), Sound.GHAST_FIREBALL, 2, 1);
		}
	}

	@EventHandler
	public void onDouble(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if (!Main.plugin.ispvp.contains(p.getUniqueId())) {
			if ((p.getGameMode() != GameMode.CREATIVE)
					&& (p.getLocation().subtract(0, 1, 0).getBlock().getType() != Material.AIR) && (!p.isFlying()))
				p.setAllowFlight(true);
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void Entrar(PlayerJoinEvent e) {

		e.setJoinMessage(null);
		final Player p = e.getPlayer();
		if (Main.plugin.ispvp.contains(p.getUniqueId())) {
			Main.plugin.ispvp.remove(p.getUniqueId());
		}
		if (Main.plugin.perm.isTrial(p)) {
			Main.plugin.adm.setAdmin(p);
		}
		Main.plugin.freehgi.put(p.getUniqueId(), Bukkit.createInventory(p, 54, ChatColor.BLACK + "FreeHG"));
		p.setFireTicks(0);
		p.setHealth(20);
		p.setFoodLevel(20);
		Random r = new Random();
		Location loc = new Location(p.getWorld(), 0 + r.nextInt(5), 23, 0 + r.nextInt(5));
		p.teleport(loc);
		ServerManager.giveItens(p);
		p.sendMessage(ChatColor.DARK_PURPLE + "Wellcome!");
		p.sendMessage(ChatColor.YELLOW + "Use the compass to choose some MiniGame to play.");
		p.sendMessage(ChatColor.YELLOW + "To train your PvP jump the Lobby spawn.");
		BarAPI.setMessage(p, Main.plugin.barapi.replaceAll("&", "§"));
		// Scoreboard.addScoreboard(p);
		Main.plugin.vanish.updateVanished();
		// Hologram holo = HologramAPI.createRunningHologram(p,
		// ChatColor.DARK_PURPLE + "" + ChatColor.BOLD +
		// "Bem vindo ao Servidor", 0);
		// holo.spawn();
		// holo.addLineBelow(ChatColor.WHITE + "Se divirta!");
		// Main.plugin.holos.put(p.getUniqueId(), holo);
		// new BukkitRunnable() {

		// @Override
		// public void run() {
		// if (Main.plugin.holos.containsKey(p.getUniqueId())) {
		// HologramAPI.removeHologram(Main.plugin.holos.get(p.getUniqueId()));
		// for (Hologram holos :
		// Main.plugin.holos.get(p.getUniqueId()).getLines()) {
		// HologramAPI.removeHologram(holos);
		// }
		// }

		// }
		// }.runTaskLater(Main.plugin, 20 * 5);
		Hologram holo = HologramAPI.createPlayerHologram(p, new Location(Bukkit.getWorld("Spawn4"), 0.50, 25.50, 15.50),
				ChatColor.GREEN + "" + ChatColor.BOLD + "FreeHG-EU");
		holo.spawn();
		holo.addLineBelow(ChatColor.GRAY + "Players: " + ChatColor.YELLOW + Main.plugin.freehgcouting);
		Main.plugin.holos.put(p.getUniqueId(), holo);
		p.playSound(p.getLocation(), Sound.LEVEL_UP, 2, 1);
	}
}
