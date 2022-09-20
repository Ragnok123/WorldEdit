package ru.ragnok123.worldEdit.commands;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.block.BlockAir;
import cn.nukkit.scheduler.AsyncTask;
import cn.nukkit.utils.TextFormat;
import ru.ragnok123.worldEdit.WEPlayer;
import ru.ragnok123.worldEdit.WorldEdit;
import ru.ragnok123.worldEdit.utils.Selection;
import ru.ragnok123.worldEdit.utils.WorldUtils;

public class CutCommand extends WECommand {

	public CutCommand() {
		super("/cut", "Delete blocks in area");
	}

	@Override
	public void execute(Player p, WEPlayer dat, String[] args) {
		checkForErrors(p, dat);
		Selection sel = dat.getSelection();
		Server.getInstance().getScheduler().scheduleAsyncTask(new AsyncTask() {

			@Override
			public void onRun() {
				p.sendMessage(WorldEdit.getPrefix() + TextFormat.AQUA + String.valueOf(WorldUtils.set(dat, sel.getPosOne(), sel.getPosTwo(), new BlockAir())) + TextFormat.GREEN + " block(s) have been changed.");
				
			}
			
		});
		
	}
}
