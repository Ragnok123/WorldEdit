package ru.ragnok123.worldEdit.commands;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.utils.TextFormat;
import ru.ragnok123.worldEdit.WEPlayer;
import ru.ragnok123.worldEdit.WorldEdit;
import ru.ragnok123.worldEdit.utils.Selection;
import ru.ragnok123.worldEdit.utils.Utils;
import ru.ragnok123.worldEdit.utils.WorldUtils;

public class SetCommand extends WECommand {

	public SetCommand() {
		super("/set", "Set blocks in selected area");
	}

	@Override
	public void execute(Player p, WEPlayer dat, String[] args) {
		checkForErrors(p, dat);
		Selection sel = dat.getSelection();
		if (args.length != 1) {
			p.sendMessage(WorldEdit.getPrefix() + TextFormat.RED + "Use //set <block>");
			return;
		}
		
		Block b = Utils.fromString(args[0]);
		if (b == null) {
			p.sendMessage(WorldEdit.getPrefix() + TextFormat.RED + "Block '" + args[0] + "' doesn't exist");
			return ;
		}
		
		p.sendMessage(WorldEdit.getPrefix() + TextFormat.AQUA + String.valueOf(WorldUtils.set(dat, sel.getPosOne(), sel.getPosTwo(), b)) + TextFormat.GREEN + " block(s) have been changed.");
	}

}
