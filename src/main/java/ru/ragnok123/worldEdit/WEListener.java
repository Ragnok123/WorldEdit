package ru.ragnok123.worldEdit;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockBreakEvent;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.item.Item;
import cn.nukkit.utils.TextFormat;

public class WEListener implements Listener {
	
	String name = TextFormat.BOLD + "" + TextFormat.YELLOW + "MAGIC AXE " + TextFormat.RESET + TextFormat.RED + "(dangerous)";

	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		Block b = e.getBlock();
		Item item = p.getInventory().getItemInHand();

		if (p.hasPermission("we.command") && item.getId() == Item.WOODEN_AXE && item.getCustomName().equals(name)) {
			WEPlayer data = WorldEdit.get().getWEPlayer(p);

			data.getSelection().setPosTwo(b.getLocation().clone());
			p.sendMessage(WorldEdit.getPrefix() + TextFormat.GREEN + "Selected the second position at " + TextFormat.AQUA + b.x
					+ TextFormat.GREEN + ", " + TextFormat.AQUA + b.y + TextFormat.GREEN + ", " + TextFormat.AQUA + b.z
					+ TextFormat.GREEN);
			e.setCancelled();
		}
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		Block b = e.getBlock();
		Item item = p.getInventory().getItemInHand();

		if (p.hasPermission("we.command") && item.getId() == Item.WOODEN_AXE && item.getCustomName().equals(name)) {
			WEPlayer data = WorldEdit.get().getWEPlayer(p);

			switch(e.getAction()) {
			case LEFT_CLICK_BLOCK:
				data.getSelection().setPosTwo(b.getLocation().clone());
				p.sendMessage(WorldEdit.getPrefix() + TextFormat.GREEN + "Selected the second position at " + TextFormat.AQUA + b.x
						+ TextFormat.GREEN + ", " + TextFormat.AQUA + b.y + TextFormat.GREEN + ", " + TextFormat.AQUA
						+ b.z + TextFormat.GREEN);
				break;
			case RIGHT_CLICK_BLOCK:
				data.getSelection().setPosOne(b.getLocation().clone());
				p.sendMessage(WorldEdit.getPrefix() + TextFormat.GREEN + "Selected the first position at " + TextFormat.AQUA + b.x
						+ TextFormat.GREEN + ", " + TextFormat.AQUA + b.y + TextFormat.GREEN + ", " + TextFormat.AQUA
						+ b.z + TextFormat.GREEN);
				break;
			}
		}
	}

}