package ru.ragnok123.worldEdit.commands;

import cn.nukkit.Player;
import cn.nukkit.utils.TextFormat;
import ru.ragnok123.worldEdit.WEPlayer;
import ru.ragnok123.worldEdit.WorldEdit;

public class PosTwoCommand extends WECommand {

	public PosTwoCommand() {
		super("/pos2", "Set second position");
		super.setAliases(new String[] {"/2"});
	}

	@Override
	public void execute(Player player, WEPlayer dat, String[] args) {
		dat.getSelection().setPosTwo(player.getPosition().clone());
		player.sendMessage(WorldEdit.getPrefix() + TextFormat.GREEN + "Second Position set to (" + TextFormat.AQUA + player.getFloorX() + ".0" + TextFormat.GREEN + ", " + TextFormat.AQUA + player.getFloorY() + ".0" + TextFormat.GREEN + ", " + TextFormat.AQUA + player.getFloorZ() + ".0" + TextFormat.GREEN + ").");

	}

}
