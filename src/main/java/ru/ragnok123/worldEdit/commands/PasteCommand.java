package ru.ragnok123.worldEdit.commands;

import cn.nukkit.Player;
import cn.nukkit.utils.TextFormat;
import ru.ragnok123.worldEdit.WEPlayer;
import ru.ragnok123.worldEdit.WorldEdit;
import ru.ragnok123.worldEdit.utils.BlocksArray;
import ru.ragnok123.worldEdit.utils.WorldUtils;

public class PasteCommand extends WECommand {

	public PasteCommand() {
		super("/paste", "Paste area");
	}

	@Override
	public void execute(Player p, WEPlayer dat, String[] args) {
		checkForErrors(p, dat);
		BlocksArray copy = dat.copiedBlocks;

		if (copy == null) {
			p.sendMessage(WorldEdit.getPrefix() + TextFormat.RED + "Use //copy first!");
			return;
		}

		p.sendMessage(WorldEdit.getPrefix() + TextFormat.AQUA + String.valueOf(WorldUtils.paste(dat, p, copy)) + TextFormat.GREEN + " block(s) have been pasted.");
		dat.copiedBlocks = null;
	}

}
