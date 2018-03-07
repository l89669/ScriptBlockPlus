package com.github.yuttyann.scriptblockplus.commandblock;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_7_R4.CraftWorld;

import net.minecraft.server.v1_7_R4.CommandBlockListenerAbstract;
import net.minecraft.server.v1_7_R4.TileEntityCommand;

class Ref_v1_7_R4 extends Ref_Vx_x_Rx {

	@Override
	public boolean executeCommand(CommandSender sender, Location location, String command) {
		CommandBlockListenerAbstract iCommandListener = getICommandListener(sender, location);
		iCommandListener.a(((CraftWorld) location.getWorld()).getHandle());
		return iCommandListener.g() > 0;
	}

	private CommandBlockListenerAbstract getICommandListener(CommandSender sender, Location location) {
		TileEntityCommand titleEntityCommand = new TileEntityCommand();
		setWorld(titleEntityCommand, location.getWorld());
		setLocation(titleEntityCommand, location);
		CommandBlockListenerAbstract commandListener = getCommandBlock(titleEntityCommand);
		if (sender != null) {
			setName(commandListener, sender.getName());
		}
		return commandListener;
	}

	private void setName(CommandBlockListenerAbstract commandListener, String name) {
		commandListener.setName(name);
	}

	private void setWorld(TileEntityCommand titleEntityCommand, World world) {
		net.minecraft.server.v1_7_R4.World nmsWorld = ((CraftWorld) world).getHandle();
		titleEntityCommand.a(nmsWorld);
	}

	private void setLocation(TileEntityCommand titleEntityCommand, Location location) {
		titleEntityCommand.x = location.getBlockX();
		titleEntityCommand.y = location.getBlockY();
		titleEntityCommand.z = location.getBlockZ();
	}

	private CommandBlockListenerAbstract getCommandBlock(TileEntityCommand titleEntityCommand) {
		return titleEntityCommand.getCommandBlock();
	}

}
