package ru.ragnok123.worldEdit.commands;

import cn.nukkit.Player;
import cn.nukkit.utils.TextFormat;
import ru.ragnok123.worldEdit.WEPlayer;
import ru.ragnok123.worldEdit.WorldEdit;
import ru.ragnok123.worldEdit.utils.WorldUtils;

public class UndoCommand extends WECommand {

	public UndoCommand() {
		super("/undo", "Cancel action");
		super.setAliases(new String[] {"/redo"});
	}

	@Override
	public void execute(Player p, WEPlayer dat, String[] args) {
		if(dat.getUndoSteps().isEmpty()) {
			p.sendMessage(WorldEdit.getPrefix() + TextFormat.RED + "There's nothing to undo");
			return;
		} else {
			int step = dat.getUndoSteps().size() - 1;
			p.sendMessage(WorldEdit.getPrefix() + TextFormat.AQUA + String.valueOf(WorldUtils.undo(dat, step)) + TextFormat.GREEN + " block(s) have been changed back");
		}
	}

}
