package ru.ragnok123.worldEdit.commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;
import ru.ragnok123.worldEdit.WEPlayer;
import ru.ragnok123.worldEdit.WorldEdit;

public abstract class WECommand extends Command {

	public WECommand(String name, String description) {
		super(name, description);
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(WorldEdit.getPrefix() + TextFormat.RED + "World Edit is available only in game");
		} else {
			Player p = (Player) sender;
			if (p.hasPermission("we.command")) {
				WEPlayer dat = WorldEdit.get().getWEPlayer(p);
				execute(p, dat, args);
			} else {
				p.sendMessage(WorldEdit.getPrefix() + TextFormat.GREEN + "You don't have enough permissions");
			}
		}
		return false;
	}

	public void checkForErrors(Player p, WEPlayer dat) {
		if (!dat.hasSelection()) {
			p.sendMessage(WorldEdit.getPrefix() +TextFormat.RED + "You have to select both positions first!");
			return;
		}

		if (dat.getSelection().getPosOne().level.getId() != dat.getSelection().getPosTwo().level.getId()) {
			p.sendMessage(WorldEdit.getPrefix() + TextFormat.RED + "Both positions must be in the same level!");
			return;
		}
	}

	public abstract void execute(Player p, WEPlayer dat, String[] args);

}
