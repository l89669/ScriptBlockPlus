package com.github.yuttyann.scriptblockplus.player;

import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import com.github.yuttyann.scriptblockplus.script.Clipboard;

public interface SBPlayer extends ObjectData, CommandSender {

	public static SBPlayer fromPlayer(Player player) {
		return player == null ? null : fromUUID(player.getUniqueId());
	}

	public static SBPlayer fromUUID(UUID uuid) {
		return uuid == null ? null : BaseSBPlayer.getSBPlayer(uuid);
	}

	public Player getPlayer();

	public OfflinePlayer getOfflinePlayer();

	public PlayerInventory getInventory();

	public ItemStack getItemInMainHand();

	public ItemStack getItemInOffHand();

	public UUID getUniqueId();

	public Location getLocation();

	public boolean isOnline();

	public void setClipboard(Clipboard clipboard);

	public Clipboard getClipboard();

	public boolean hasClipboard();

	public void setScriptLine(String scriptLine);

	public String getScriptLine();

	public boolean hasScriptLine();

	public void setClickAction(String clickAction);

	public String getClickAction();

	public boolean hasClickAction();

	public void setOldFullCoords(String fullCoords);

	public String getOldFullCoords();

	public boolean hasOldFullCoords();
}