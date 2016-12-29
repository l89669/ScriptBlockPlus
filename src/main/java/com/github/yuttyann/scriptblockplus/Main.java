package com.github.yuttyann.scriptblockplus;

import java.util.HashMap;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.yuttyann.scriptblockplus.collplugin.CollPlugins;
import com.github.yuttyann.scriptblockplus.command.ScriptBlockCommand;
import com.github.yuttyann.scriptblockplus.command.help.CommandView;
import com.github.yuttyann.scriptblockplus.file.Files;
import com.github.yuttyann.scriptblockplus.file.Yaml;
import com.github.yuttyann.scriptblockplus.listener.BlockListener;
import com.github.yuttyann.scriptblockplus.listener.PlayerInteractListener;
import com.github.yuttyann.scriptblockplus.listener.PlayerMoveListener;
import com.github.yuttyann.scriptblockplus.listener.PlayerQuitListener;
import com.github.yuttyann.scriptblockplus.manager.MapManager;
import com.github.yuttyann.scriptblockplus.util.Utils;

public class Main extends JavaPlugin {

	public static Main instance;
	private String encode;
	private PluginManager manager;
	private PluginDescriptionFile description;
	private HashMap<String, TabExecutor> commands;
	private HashMap<String, List<CommandView>> commandhelp;

	@Override
	public void onEnable() {
		instance = this;
		manager = getServer().getPluginManager();
		description = getDescription();
		if(!(Double.parseDouble(System.getProperty("java.specification.version")) >= 1.7))
		{
			Utils.sendPluginMessage("§cJava7以上をインストールしてください。");
			Utils.sendPluginMessage("§cJavaのバージョンが古いため、プラグインを無効化します。");
			manager.disablePlugin(this);
			return;
		}
		if (!CollPlugins.hasVault()) {
			Utils.sendPluginMessage("§cVaultが導入されていないため、プラグインを無効化します。");
			manager.disablePlugin(this);
			return;
		}
		if (Utils.isPluginEnabled("ScriptBlock")) {
			manager.disablePlugin(manager.getPlugin("ScriptBlock"));
		}
		setupFile();
		loadClass();
		loadCommand();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		return commands.get(command.getName()).onCommand(sender, command, label, args);
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		return commands.get(command.getName()).onTabComplete(sender, command, label, args);
	}

	public HashMap<String, List<CommandView>> getCommandHelp() {
		return commandhelp;
	}

	public String getPluginName() {
		return description.getName();
	}

	public String getPluginVersion() {
		return description.getVersion();
	}

	public String getEncode() {
		return encode;
	}

	private void setupFile() {
		if (Utils.isWindows() && !Utils.isUpperVersion_v19()) {
			encode = "s-jis";
		} else {
			encode = "utf-8";
		}
		String[] args = new String[]{"config", "messages"};
		Yaml.create(getDataFolder(), new StringBuilder(), encode, args);
		Files.reload();
	}

	private void loadClass() {
		new MapManager();
		MapManager.putAllScripts();
		manager.registerEvents(new BlockListener(), this);
		manager.registerEvents(new PlayerInteractListener(), this);
		manager.registerEvents(new PlayerMoveListener(), this);
		manager.registerEvents(new PlayerQuitListener(), this);
		manager.registerEvents(new Updater(this), this);
	}

	private void loadCommand() {
		commandhelp = new HashMap<String, List<CommandView>>();
		commands = new HashMap<String, TabExecutor>();
		commands.put("scriptblockplus", new ScriptBlockCommand());
	}
}