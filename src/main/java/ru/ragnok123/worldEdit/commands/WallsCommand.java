package ru.ragnok123.worldEdit.commands;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.block.Block;
import cn.nukkit.scheduler.AsyncTask;
import cn.nukkit.utils.TextFormat;
import ru.ragnok123.worldEdit.WEPlayer;
import ru.ragnok123.worldEdit.WorldEdit;
import ru.ragnok123.worldEdit.utils.Selection;
import ru.ragnok123.worldEdit.utils.Utils;
import ru.ragnok123.worldEdit.utils.WorldUtils;

public class WallsCommand extends WECommand {

	public WallsCommand() {
		super("/walls", "Build walls among area");
		super.setAliases(new String[] { "/wall" });
	}

	@Override
	public void execute(Player p, WEPlayer dat, String[] args) {
		checkForErrors(p, dat);
		Selection sel = dat.getSelection();
		if (args.length != 1) {
			p.sendMessage(WorldEdit.getPrefix() + TextFormat.RED + "Use //walls <block>");
			return ;
		}

		Block b = Utils.fromString(args[0]);

		if (b == null) {
			p.sendMessage(WorldEdit.getPrefix() + TextFormat.RED + "Block '" + args[0] + "' doesn't exist");
			return;
		}
		Server.getInstance().getScheduler().scheduleAsyncTask(new AsyncTask() {

			@Override
			public void onRun() {
				p.sendMessage(WorldEdit.getPrefix() + TextFormat.AQUA + String.valueOf(WorldUtils.walls(sel.getPosOne(), sel.getPosTwo(), b)) + TextFormat.GREEN + " block(s) have been changed.");
			}
			
		});
		
	}

}
