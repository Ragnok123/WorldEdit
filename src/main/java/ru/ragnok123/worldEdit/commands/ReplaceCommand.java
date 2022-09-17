package ru.ragnok123.worldEdit.commands;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.utils.TextFormat;
import ru.ragnok123.worldEdit.WEPlayer;
import ru.ragnok123.worldEdit.WorldEdit;
import ru.ragnok123.worldEdit.utils.Selection;
import ru.ragnok123.worldEdit.utils.Utils;
import ru.ragnok123.worldEdit.utils.WorldUtils;

public class ReplaceCommand extends WECommand {

	public ReplaceCommand() {
		super("/replace", "Replace blocks in selected area");
	}

	@Override
	public void execute(Player p, WEPlayer dat, String[] args) {
		checkForErrors(p, dat);
		Selection sel = dat.getSelection();
		if (args.length != 2) {
			p.sendMessage(WorldEdit.getPrefix() + TextFormat.RED + "Use //replace <block> <replace>");
			return;
		}

		Block b = Utils.fromString(args[0]);
		Block b2 = Utils.fromString(args[1]);

		if (b == null) {
			p.sendMessage(WorldEdit.getPrefix() + TextFormat.RED + "Block '" + args[0] + "' doesn't exist");
			return;
		}

		if (b2 == null) {
			p.sendMessage(WorldEdit.getPrefix() + TextFormat.RED + "Block '" + args[1] + "' doesn't exist");
			return;
		}

		p.sendMessage(WorldEdit.getPrefix() + TextFormat.AQUA + String.valueOf(WorldUtils.replace(dat, sel.getPosOne(), sel.getPosTwo(), b, b2)) + TextFormat.GREEN + " block(s) have been replaced.");
	}

}
