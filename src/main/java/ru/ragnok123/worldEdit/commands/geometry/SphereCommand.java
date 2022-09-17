package ru.ragnok123.worldEdit.commands.geometry;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.utils.TextFormat;
import ru.ragnok123.worldEdit.WEPlayer;
import ru.ragnok123.worldEdit.WorldEdit;
import ru.ragnok123.worldEdit.commands.WECommand;
import ru.ragnok123.worldEdit.utils.Utils;
import ru.ragnok123.worldEdit.utils.WorldUtils;

public class SphereCommand extends WECommand {

	public SphereCommand() {
		super("/sphere", "Make sphere");
	}

	@Override
	public void execute(Player p, WEPlayer dat, String[] args) {
		if (args.length != 2) {
			p.sendMessage(WorldEdit.getPrefix() + TextFormat.RED + "Use //sphere <block> <radius>");
			return;
		}

		Block b = Utils.fromString(args[0]);
		if (b == null) {
			p.sendMessage(TextFormat.RED + "Block '" + args[0] + "' doesn't exist");
			return;
		}

		p.sendMessage(TextFormat.AQUA + String.valueOf(WorldUtils.sphere(dat, p.getPosition(), Integer.valueOf(args[1]), b)) + TextFormat.GREEN + " block(s) have been changed.");
	}

}
