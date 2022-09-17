package ru.ragnok123.worldEdit.commands;

import cn.nukkit.Player;
import cn.nukkit.utils.TextFormat;
import ru.ragnok123.worldEdit.WEPlayer;
import ru.ragnok123.worldEdit.WorldEdit;
import ru.ragnok123.worldEdit.utils.BlocksArray;
import ru.ragnok123.worldEdit.utils.Selection;
import ru.ragnok123.worldEdit.utils.WorldUtils;

public class CopyCommand extends WECommand {

	public CopyCommand() {
		super("/copy", "Copy area to clipboard");
	}

	@Override
	public void execute(Player p, WEPlayer dat, String[] args) {
		checkForErrors(p, dat);
		Selection sel = dat.getSelection();
		dat.copiedBlocks = new BlocksArray();

		p.sendMessage(WorldEdit.getPrefix() + TextFormat.AQUA
				+ String.valueOf(WorldUtils.copy(sel.getPosOne(), sel.getPosTwo(), p, dat.copiedBlocks))
				+ TextFormat.GREEN + " block(s) have been copied.");
	}

}
