package ru.ragnok123.worldEdit.commands;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.scheduler.AsyncTask;
import cn.nukkit.utils.TextFormat;
import ru.ragnok123.worldEdit.WEPlayer;
import ru.ragnok123.worldEdit.WorldEdit;
import ru.ragnok123.worldEdit.utils.WorldUtils;

public class RotateCommand extends WECommand {

	public RotateCommand() {
		super("/rotate", "Rotate structure around axis");
	}

	@Override
	public void execute(Player p, WEPlayer dat, String[] args) {
		checkForErrors(p, dat);
		if(args.length != 2) {
			p.sendMessage(WorldEdit.getPrefix() + TextFormat.RED + "Usage: //rotate <axis: y> <degrees>");
		} else {
			String axis = args[0];
			if(!axis.equalsIgnoreCase("y")) {
				p.sendMessage(WorldEdit.getPrefix() + TextFormat.RED + "Unknown axis type");
				return;
			}
			int degrees = Integer.valueOf(args[1]);
			if(degrees < 0 || degrees > 360) {
				p.sendMessage(WorldEdit.getPrefix() + TextFormat.GOLD + "Whoa, we don't do such math here");
				return;
			}
			Server.getInstance().getScheduler().scheduleAsyncTask(new AsyncTask() {

				@Override
				public void onRun() {
					p.sendMessage(WorldEdit.getPrefix() + TextFormat.AQUA + WorldUtils.rotateY(dat, p, dat.getSelection() , degrees) + 
							TextFormat.GREEN + " blocks have been rotated around axis " + TextFormat.AQUA + axis);
				}
				
			});
		}
	}

}
