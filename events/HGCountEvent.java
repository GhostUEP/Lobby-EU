package me.ghost.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class HGCountEvent extends Event {
	public static final HandlerList handlers = new HandlerList();

	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}