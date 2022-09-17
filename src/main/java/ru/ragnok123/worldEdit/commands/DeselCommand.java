package ru.ragnok123.worldEdit.commands;

import cn.nukkit.Player;
import cn.nukkit.utils.TextFormat;
import ru.ragnok123.worldEdit.WEPlayer;
import ru.ragnok123.worldEdit.WorldEdit;

public class DeselCommand extends WECommand {

	public DeselCommand() {
		super("/desel", "Cancel selection");
		super.setAliases(new String[] { "/;", "/sel", "/deselect" });
	}

	@Override
	public void execute(Player p, WEPlayer dat, String[] args) {
		dat.getSelection().setPosOne(null);
		dat.getSelection().setPosTwo(null);

		p.sendMessage(WorldEdit.getPrefix() + TextFormat.GREEN + "Selection cleared.");
	}

}
