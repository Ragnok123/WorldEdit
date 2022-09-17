package ru.ragnok123.worldEdit.commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;
import ru.ragnok123.worldEdit.WEPlayer;
import ru.ragnok123.worldEdit.WorldEdit;

public class HelpCommand extends WECommand {

	public HelpCommand() {
		super("/help", "List of World Edit Commands");
	}

	@Override
	public void execute(Player sender, WEPlayer dat, String[] args) {
		if (args.length == 0) {
			sender.sendMessage(getHelpPage());
		} else if (args.length == 1) {
			int page = Integer.valueOf(args[0]);
			sender.sendMessage(getHelpPage(page));
		} else {
			sender.sendMessage(WorldEdit.getPrefix() + TextFormat.RED + "Use //help <page>");
		}
	}

	private String getHelpPage() {
		return getHelpPage(1);
	}

	private String getHelpPage(int page) {
		String help = "";
		if (page < 1 && page > 3) {
			page = 1;
		}
		switch (page) {
		case 1:
			help += TextFormat.GREEN + "Showing help page " + TextFormat.AQUA + "1/3:";
			help += "\n" + TextFormat.YELLOW + "   //pos1 (//1)" + TextFormat.GRAY + " - " + TextFormat.RED
					+ "Set first position";
			help += "\n" + TextFormat.YELLOW + "   //pos2 (//2)" + TextFormat.GRAY + " - " + TextFormat.RED
					+ "Set second position";
			help += "\n" + TextFormat.YELLOW + "   //set <block>" + TextFormat.GRAY + " - " + TextFormat.RED
					+ "Set blocks in selected area";
			help += "\n" + TextFormat.YELLOW + "   //walls <block>" + TextFormat.GRAY + " - " + TextFormat.RED
					+ "Set walls in selectet area";
			help += "\n" + TextFormat.YELLOW + "   //replace <current block> <target block>" + TextFormat.GRAY + " - "
					+ TextFormat.RED + "Replace blocks in selected area";
			break;
		case 2:
			help += TextFormat.GREEN + "Showing help page " + TextFormat.AQUA + "2/3:";
			help += "\n" + TextFormat.YELLOW + "   //cyl <block> <radius>" + TextFormat.GRAY + " - " + TextFormat.RED
					+ "Make cylinder";
			help += "\n" + TextFormat.YELLOW + "   //hcyl <block> <radius>" + TextFormat.GRAY + " - " + TextFormat.RED
					+ "Make vertical cylinder";
			help += "\n" + TextFormat.YELLOW + "   //sphere <block> <radius>" + TextFormat.GRAY + " - " + TextFormat.RED
					+ "Make sphere";
			help += "\n" + TextFormat.YELLOW + "   //hsphere <block> <radius>" + TextFormat.GRAY + " - "
					+ TextFormat.RED + "Make vertical sphere";
			help += "\n" + TextFormat.YELLOW + "   //cut" + TextFormat.GRAY + " - " + TextFormat.RED
					+ "Cut area into clipboard";
			break;
		case 3:
			help += TextFormat.GREEN + "Showing help page " + TextFormat.AQUA + "3/3:";
			help += "\n" + TextFormat.YELLOW + "   //copy" + TextFormat.GRAY + " - " + TextFormat.RED
					+ "Copy entire area into clipboard";
			help += "\n" + TextFormat.YELLOW + "   //paste" + TextFormat.GRAY + " - " + TextFormat.RED
					+ "Paste clipboard";
			help += "\n" + TextFormat.YELLOW + "   //wand" + TextFormat.GRAY + " - " + TextFormat.RED + "Give wand axe";
			break;
		}
		return help;
	}

}
