package ru.ragnok123.worldEdit.commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.item.Item;
import cn.nukkit.utils.TextFormat;
import ru.ragnok123.worldEdit.WEPlayer;
import ru.ragnok123.worldEdit.WorldEdit;

public class WandCommand extends WECommand {

	public WandCommand() {
		super("/wand", "Give wand axe");
	}

	@Override
	public void execute(Player p, WEPlayer dat, String[] args) {
		Item axe = Item.get(Item.WOODEN_AXE);
		axe.setCustomName(TextFormat.BOLD + "" + TextFormat.YELLOW + "MAGIC AXE " + TextFormat.RESET + TextFormat.RED + "(dangerous)");
		p.getInventory().setItemInHand(axe);
		p.getInventory().sendHeldItem(p);

		p.sendMessage(WorldEdit.getPrefix() + TextFormat.GREEN + "Tap block: " + TextFormat.GRAY + "select pos " 
				+ TextFormat.AQUA + "#1" + TextFormat.GRAY + "; " 
				+ TextFormat.GREEN + "Destroy block: " + TextFormat.GRAY + "select pos " + TextFormat.AQUA + "#2");
	}

}
