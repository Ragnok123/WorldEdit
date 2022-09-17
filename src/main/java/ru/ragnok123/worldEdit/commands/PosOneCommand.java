package ru.ragnok123.worldEdit.commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;
import ru.ragnok123.worldEdit.WEPlayer;
import ru.ragnok123.worldEdit.WorldEdit;

public class PosOneCommand extends WECommand {

	public PosOneCommand() {
		super("/pos1", "Set first position");
		super.setAliases(new String[] {"/1"});
	}

	@Override
	public void execute(Player player, WEPlayer dat, String[] args) {
		dat.getSelection().setPosOne(player.getPosition().clone());
		player.sendMessage(WorldEdit.getPrefix() + TextFormat.GREEN + "First Position set to (" + TextFormat.AQUA + player.getFloorX() + ".0" + TextFormat.GREEN + ", " + TextFormat.AQUA + player.getFloorY() + ".0" + TextFormat.GREEN + ", " + TextFormat.AQUA + player.getFloorZ() + ".0" + TextFormat.GREEN + ").");
	}

}
