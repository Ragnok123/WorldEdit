package ru.ragnok123.worldEdit.commands;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;
import ru.ragnok123.worldEdit.WEPlayer;
import ru.ragnok123.worldEdit.WorldEdit;
import ru.ragnok123.worldEdit.utils.Selection;
import ru.ragnok123.worldEdit.utils.Utils;
import ru.ragnok123.worldEdit.utils.WorldUtils;

public class CompressCommand extends WECommand {

	public CompressCommand() {
		super("/compress", "Compress selected area to schematic");
	}

	@Override
	public void execute(Player p, WEPlayer dat, String[] args) {
		checkForErrors(p, dat);
		Selection sel = dat.getSelection();
		if (args.length != 1) {
			p.sendMessage(WorldEdit.getPrefix() + TextFormat.RED + "Use //compress <schematic name>");
			return;
		}
		
		p.sendMessage(WorldEdit.getPrefix() + TextFormat.AQUA + String.valueOf(WorldUtils.compress(dat, sel.getPosOne(), sel.getPosTwo(), args[0])) + TextFormat.GREEN + " block(s) have been compressed into "+TextFormat.AQUA + args[0] + ".we "+TextFormat.GREEN+".");
	}

}
