package cz.pavelkalas.core;

import org.bukkit.ChatColor;

public class Strings {

	//
	// general strings
	//
	public static final String PREFIX_DELIM = ChatColor.WHITE + " >> ";
	public static final String PREFIX = ChatColor.GOLD + "MCGuard" + ChatColor.YELLOW + "Lite" + PREFIX_DELIM;
	
	public static final String VERSION = "1.0";
	
	public static final String JOINMSG_INFO = (ChatColor.GRAY + "" + ChatColor.BOLD) + "> McGuardLite v" + VERSION + " active - https://pavelkalas.xyz";
	
	//
	// ingame strings
	//
	public static final String MSG_YOURE_BANNED = PREFIX + (ChatColor.RED + "" + ChatColor.BOLD) + "You are permanently banned for cheating! If you belive this is mistake, contact us!";
	public static final String MSG_DONT_USE_CHEATS = PREFIX + (ChatColor.RED + "" + ChatColor.BOLD) + "You really shouldn't use cheats if you don't want be punished!";
	public static final String MSG_YOU_CANNOT_DO_THAT = PREFIX + (ChatColor.RED + "" + ChatColor.BOLD) + "You are not permitted to do this action!";
}
