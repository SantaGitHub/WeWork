package org.sinrel.wework;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Logger;

public class WeWork extends JavaPlugin implements Listener{
	
	public final Logger log = Logger.getLogger("Minecraft");
	
	public void onEnable(){
		log.info("["+this.getDescription().getName()+"] "+this.getDescription().getName()+" version "+this.getDescription().getVersion()+" is enabled");
		
		if(!new File(this.getDataFolder(),"config.yml").exists()){
			createConfig();
		}
		getServer().getPluginManager().registerEvents(this,this);
	}
	
	private void createConfig(){
		this.getConfig().options().header("Copyright (c) 2012, Sinrel Group");
		
		this.getConfig().set("enabled", false);
		this.getConfig().set("debug",false);
		this.getConfig().set("kicking", true);
		this.getConfig().set("lang", "en");
		
		this.getConfig().set("en.tag", "WARNING!");
		this.getConfig().set("en.message","Server is closed");
		this.getConfig().set("en.joinMessage", "Server now closed!");
		this.getConfig().set("en.leftMessage", "Server now closed!");
		this.getConfig().set("en.closing","Server now closed!");
		this.getConfig().set("en.opening","Server now opened!");	
		
		this.getConfig().set("ru.tag", "Внимание!");
		this.getConfig().set("ru.message", "Сервер закрыт");
		this.getConfig().set("ru.joinMessage", "Сервер сейчас закрыт! Доступ ограничен");
		this.getConfig().set("ru.leftMessage", "Сервер сейчас закрыт! Доступ ограничен");
		this.getConfig().set("ru.closing","Сервер сейчас закрыт!");
		this.getConfig().set("ru.opening", "Сервер сейчас открыт!");
		
		this.getConfig().set("ua.tag", "Увага!");
		this.getConfig().set("ua.message", "Сервер зачинений");
		this.getConfig().set("ua.joinMessage", "Зараз сервер зачинений! Доступ обмежено");
		this.getConfig().set("ua.leftMessage", "Зараз сервер зачинений! Доступ обмежено");
		this.getConfig().set("ua.closing", "Зараз сервер зачинено");
		this.getConfig().set("ua.opening", "Зараз сервер відчинено");
		
		this.getConfig().set("by.tag", "Увага!");
		this.getConfig().set("by.message", "Cервер зачынены");
		this.getConfig().set("by.joinMessage", "Сервер зараз зачынены! Доступ абмежаваны");
		this.getConfig().set("by.leftMessage", "Сервер зараз зачынены! Доступ абмежаваны");
		this.getConfig().set("by.closing", "Сервер зараз зачынены!");
		this.getConfig().set("by.opening", "Сервер зараз адкрыты!");

		this.saveConfig();
	}
	
	@EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
	public void onJoin(PlayerJoinEvent e){
		if(this.getConfig().getBoolean("debug")){
			log.info("Player is OP: "+e.getPlayer().isOp());
			log.info("Found permission for Player : "+e.getPlayer().hasPermission("wework.join"));
		}		
		if(this.getConfig().getBoolean("enabled")){
			if(e.getPlayer().isOp() | e.getPlayer().hasPermission("wework.join")){
				e.getPlayer().sendMessage("["+ChatColor.RED+getMessage("tag")+ChatColor.WHITE+"] "+getMessage("joinMessage"));
			}else{
				e.getPlayer().kickPlayer(getMessage("tag")+"\n"+getMessage("message"));
			}
		}
	}
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
	    if(command.getName().equalsIgnoreCase("wework")){
	    	if(sender.hasPermission("wework.usage") | sender.isOp()){
		    		if(!this.getConfig().getBoolean("enabled")){
		    			
		    			this.getConfig().set("enabled", true);		
		    			this.saveConfig();
		    			this.reloadConfig();
		    			
		    			Player[] players = getServer().getOnlinePlayers();
		    			
		    			if(this.getConfig().getBoolean("kicking")){
		    				for(int i=0;i<players.length;i++){
		    					if((!players[i].isOp() | !players[i].hasPermission("wework.left"))& players[i]!= (Player) sender){
		    						if(players[i]!=null) players[i].kickPlayer(getMessage("leftMessage"));
		    						else break;
		    					}
		    				}
		    			}
		    			
		    			sender.sendMessage("["+ChatColor.RED+"Warning"+ChatColor.WHITE+"] "+getMessage("closing"));
		    			
		    	        return true;
		    		}
		    		
		    		if(this.getConfig().getBoolean("enabled")){
		    			this.getConfig().set("enabled", false);		
		    			this.saveConfig();
		    			this.reloadConfig();
		    			
		    			sender.sendMessage("["+ChatColor.RED+"Warning"+ChatColor.WHITE+"] "+getMessage("opening"));
		    			
		    	        return true;
		    		}
		    }
		}
	    return false;
	}
	
	public void onDisable(){
		log.info("["+this.getDescription().getName()+"] "+this.getDescription().getName()+ "version "+this.getDescription().getVersion()+" disabled");
	}
	
	private String getMessage(String msg){
		return this.getConfig().getString(this.getConfig().getString("lang")+"."+msg);
	}
}
