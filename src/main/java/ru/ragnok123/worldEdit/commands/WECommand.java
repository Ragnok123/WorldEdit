package ru.ragnok123.worldEdit.commands;

import java.io.File;
import java.io.IOException;
import java.nio.ByteOrder;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;
import net.novatech.library.nbt.NBTIO;
import net.novatech.library.nbt.NBTIO.CompressType;
import net.novatech.library.nbt.tags.CompoundTag;
import net.novatech.library.nbt.tags.IntTag;
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
	
	public boolean checkForDecompress(WEPlayer dat, String schematic) {
		boolean v = true;
		File file = new File(WorldEdit.get().getDataFolder() + "/" + schematic + ".we");
		try {
			CompoundTag data = NBTIO.read(file,ByteOrder.LITTLE_ENDIAN, true, CompressType.ZSTD);
			IntTag version = (IntTag) data.getValue("Version");
			
			if(version.getValue() < WorldEdit.COMPRESS_VERSION) {
				dat.getPlayer().sendMessage(WorldEdit.getPrefix() + TextFormat.RED + "You are trying to decompress older schematic.");
				v = false;
			} else if(version.getValue() > WorldEdit.COMPRESS_VERSION) {
				dat.getPlayer().sendMessage(WorldEdit.getPrefix() + TextFormat.RED + "You are trying to decompress unknown version of schematic");
				v = false;
			} else {
				v = true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return v;
	}

	public abstract void execute(Player p, WEPlayer dat, String[] args);

}
