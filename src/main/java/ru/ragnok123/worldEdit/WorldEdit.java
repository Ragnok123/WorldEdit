package ru.ragnok123.worldEdit;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.block.BlockAir;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockBreakEvent;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.event.player.PlayerInteractEvent.Action;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemAxeWood;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;
import lombok.Getter;
import ru.ragnok123.worldEdit.commands.*;
import ru.ragnok123.worldEdit.commands.geometry.*;
import ru.ragnok123.worldEdit.utils.Utils;
import ru.ragnok123.worldEdit.utils.WorldUtils;

import java.util.HashMap;

public class WorldEdit extends PluginBase implements Listener {

	public HashMap<String, WEPlayer> players = new HashMap<>();

	public static WorldEdit instance;
	@Getter
	private static String prefix = TextFormat.YELLOW + "WE> ";

	@Override
	public void onEnable() {
		instance = this;
		getServer().getPluginManager().registerEvents(new WEListener(), this);;

		getServer().getCommandMap().register("//help", new HelpCommand());

		getServer().getCommandMap().register("//pos1", new PosOneCommand());
		getServer().getCommandMap().register("//pos2", new PosTwoCommand());
		getServer().getCommandMap().register("//wand", new WandCommand());
		getServer().getCommandMap().register("//copy", new CopyCommand());
		getServer().getCommandMap().register("//paste", new PasteCommand());
		getServer().getCommandMap().register("//set", new SetCommand());
		getServer().getCommandMap().register("//walls", new WallsCommand());
		getServer().getCommandMap().register("//cut", new CutCommand());
		getServer().getCommandMap().register("//undo", new UndoCommand());
		getServer().getCommandMap().register("//desel", new DeselCommand());
		getServer().getCommandMap().register("//replace", new ReplaceCommand());
		getServer().getCommandMap().register("//rotate", new RotateCommand());

		getServer().getCommandMap().register("//cyl", new CylCommand());
		getServer().getCommandMap().register("//hcyl", new HCylCommand());
		getServer().getCommandMap().register("//sphere", new SphereCommand());
		getServer().getCommandMap().register("//hsphere", new HSphereCommand());
	}

	public static WorldEdit get() {
		return instance;
	}


	public WEPlayer getWEPlayer(Player p) {
		WEPlayer data = this.players.get(p.getName().toLowerCase());

		if (data == null) {
			data = new WEPlayer(p);
			this.players.put(p.getName().toLowerCase(), data);
		}

		return data;
	}

}
