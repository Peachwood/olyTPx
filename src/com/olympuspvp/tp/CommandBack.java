package com.olympuspvp.tp;

import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

public class CommandBack implements Listener{

	static HashMap<String, Location> previous = new HashMap<String, Location>();
	final static String tag = ChatColor.GOLD + "[" + ChatColor.YELLOW + "olyTP" + ChatColor.GOLD+"] " + ChatColor.YELLOW;
	
	protected CommandBack(olyTP tp){
		Bukkit.getPluginManager().registerEvents(this, tp);
	}
	
	public static void goBack(Player p){
		if(!p.hasPermission("olytp.use")){
			p.sendMessage(tag + "You do not have permission to use this command.");
			return;
		}if(!previous.containsKey(p.getName())){
			p.sendMessage(tag + "You have not been teleported recently.");
			return;
		}Location to = previous.get(p.getName());
		p.teleport(to, TeleportCause.PLUGIN);
		p.sendMessage(tag + "You have been return to your location.");
	}
	
	@EventHandler(priority=EventPriority.MONITOR)
	public void onPlayerTeleport(PlayerTeleportEvent e){
		Player p = e.getPlayer();
		if(!p.hasPermission("olytp.use")) return;
		previous.put(p.getName(), e.getFrom());
	}
	
}