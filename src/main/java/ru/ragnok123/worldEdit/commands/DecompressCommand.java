package ru.ragnok123.worldEdit.commands;

import java.io.File;

import cn.nukkit.Player;
import cn.nukkit.utils.TextFormat;
import ru.ragnok123.worldEdit.WEPlayer;
import ru.ragnok123.worldEdit.WorldEdit;
import ru.ragnok123.worldEdit.utils.WorldUtils;

public class DecompressCommand extends WECommand {

	public DecompressCommand() {
		super("/decompress", "Decompress schematic to world");
	}

	@Override
	public void execute(Player p, WEPlayer dat, String[] args) {
		if (args.length != 1) {
			p.sendMessage(WorldEdit.getPrefix() + TextFormat.RED + "Use //decompress <schematic name>");
			return;
		}
		File file = new File(WorldEdit.get().getDataFolder() + "/" + args[0] + ".we");
		if(!file.exists()) {
			p.sendMessage(WorldEdit.getPrefix() + TextFormat.RED + "Schematic "+TextFormat.AQUA + args[0] + " does not exists.");
		}
		
		p.sendMessage(WorldEdit.getPrefix() + TextFormat.AQUA + String.valueOf(WorldUtils.decompress(dat, args[0])) + TextFormat.GREEN + " block(s) have been decompressed from "+TextFormat.AQUA + args[0] + ".we "+TextFormat.GREEN+".");
	}

}
