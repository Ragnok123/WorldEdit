package ru.ragnok123.worldEdit.utils;

import cn.nukkit.block.Block;
import cn.nukkit.item.Item;
import cn.nukkit.math.Vector3;
import net.novatech.library.nbt.tags.ByteArrayTag;
import net.novatech.library.nbt.tags.ByteTag;
import net.novatech.library.nbt.tags.CompoundTag;
import net.novatech.library.nbt.tags.DoubleTag;
import net.novatech.library.nbt.tags.FloatTag;
import net.novatech.library.nbt.tags.IntArrayTag;
import net.novatech.library.nbt.tags.IntTag;
import net.novatech.library.nbt.tags.ListTag;
import net.novatech.library.nbt.tags.LongTag;
import net.novatech.library.nbt.tags.ShortTag;
import net.novatech.library.nbt.tags.StringTag;
import net.novatech.library.nbt.tags.Tag;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Utils {

	public static Block fromString(String str) {
		String[] b = str.trim().replace(' ', '_').replace("minecraft:", "").split(":");

		int id = 0;
		int meta = 0;

		Pattern integerPattern = Pattern.compile("^[1-9]\\d*$");
		if (integerPattern.matcher(b[0]).matches()) {
			id = Integer.valueOf(b[0]);
		} else {
			try {
				id = Item.class.getField(b[0].toUpperCase()).getInt(null);
			} catch (Exception ignore) {
			}
		}

		id = id & 0xFFFF;
		if (b.length != 1)
			meta = Integer.valueOf(b[1]) & 0xFFFF;

		return Block.get(id, meta);
	}

	public static boolean insideArea(Vector3 vector, Vector3 pos1, Vector3 pos2) {
		if ((vector.getX() > pos1.getX()) && (vector.getX() < pos2.getX())) {
			if ((vector.getY() > pos1.getY()) && (vector.getY() < pos2.getY())) {
				if ((vector.getZ() > pos1.getZ()) && (vector.getZ() < pos2.getZ())) {
					return true;
				}
			}
		}
		return false;
	}

	public static CompoundTag convertNukkitCompoundToNova(cn.nukkit.nbt.tag.CompoundTag old) {
		CompoundTag tag = new CompoundTag(old.getName());
		for (cn.nukkit.nbt.tag.Tag t : old.getAllTags()) {
			if (t instanceof cn.nukkit.nbt.tag.IntTag) {
				tag.add(convertNukkitIntToNova((cn.nukkit.nbt.tag.IntTag) t));
			} else if (t instanceof cn.nukkit.nbt.tag.ShortTag) {
				tag.add(convertNukkitShortToNova((cn.nukkit.nbt.tag.ShortTag) t));
			} else if (t instanceof cn.nukkit.nbt.tag.LongTag) {
				tag.add(convertNukkitLongToNova((cn.nukkit.nbt.tag.LongTag) t));
			} else if (t instanceof cn.nukkit.nbt.tag.StringTag) {
				tag.add(convertNukkitStringToNova((cn.nukkit.nbt.tag.StringTag) t));
			} else if (t instanceof cn.nukkit.nbt.tag.IntArrayTag) {
				tag.add(convertNukkitIntArrayToNova((cn.nukkit.nbt.tag.IntArrayTag) t));
			} else if (t instanceof cn.nukkit.nbt.tag.ByteTag) {
				tag.add(convertNukkitByteToNova((cn.nukkit.nbt.tag.ByteTag) t));
			} else if (t instanceof cn.nukkit.nbt.tag.ByteArrayTag) {
				tag.add(convertNukkitByteArrayToNova((cn.nukkit.nbt.tag.ByteArrayTag) t));
			} else if (t instanceof cn.nukkit.nbt.tag.DoubleTag) {
				tag.add(convertNukkitDoubleToNova((cn.nukkit.nbt.tag.DoubleTag) t));
			} else if (t instanceof cn.nukkit.nbt.tag.FloatTag) {
				tag.add(convertNukkitFloatToNova((cn.nukkit.nbt.tag.FloatTag) t));
			} else if (t instanceof cn.nukkit.nbt.tag.ListTag) {
				tag.add(convertNukkitListToNova((cn.nukkit.nbt.tag.ListTag) t));
			} else if (t instanceof cn.nukkit.nbt.tag.CompoundTag) {
				tag.add(convertNukkitCompoundToNova((cn.nukkit.nbt.tag.CompoundTag) t));
			}
		}
		return tag;
	}

	public static IntTag convertNukkitIntToNova(cn.nukkit.nbt.tag.IntTag old) {
		return new IntTag(old.getName(), old.getData());
	}

	public static ShortTag convertNukkitShortToNova(cn.nukkit.nbt.tag.ShortTag old) {
		return new ShortTag(old.getName(), old.getData().shortValue());
	}

	public static LongTag convertNukkitLongToNova(cn.nukkit.nbt.tag.LongTag old) {
		return new LongTag(old.getName(), old.getData());
	}

	public static StringTag convertNukkitStringToNova(cn.nukkit.nbt.tag.StringTag old) {
		return new StringTag(old.getName(), old.parseValue());
	}

	public static IntArrayTag convertNukkitIntArrayToNova(cn.nukkit.nbt.tag.IntArrayTag old) {
		return new IntArrayTag(old.getName(), old.getData());
	}

	public static ByteTag convertNukkitByteToNova(cn.nukkit.nbt.tag.ByteTag old) {
		return new ByteTag(old.getName(), old.getData().byteValue());
	}

	public static ByteArrayTag convertNukkitByteArrayToNova(cn.nukkit.nbt.tag.ByteArrayTag old) {
		return new ByteArrayTag(old.getName(), old.getData());
	}

	public static DoubleTag convertNukkitDoubleToNova(cn.nukkit.nbt.tag.DoubleTag old) {
		return new DoubleTag(old.getName(), old.getData());
	}

	public static FloatTag convertNukkitFloatToNova(cn.nukkit.nbt.tag.FloatTag old) {
		return new FloatTag(old.getName(), old.getData());
	}

	public static ListTag convertNukkitListToNova(cn.nukkit.nbt.tag.ListTag old) {
		ListTag list = new ListTag(old.getName());
		List<cn.nukkit.nbt.tag.Tag> tt = old.getAll();
		for (cn.nukkit.nbt.tag.Tag t : tt) {
			if (t instanceof cn.nukkit.nbt.tag.IntTag) {
				list.add(convertNukkitIntToNova((cn.nukkit.nbt.tag.IntTag) t));
			} else if (t instanceof cn.nukkit.nbt.tag.ShortTag) {
				list.add(convertNukkitShortToNova((cn.nukkit.nbt.tag.ShortTag) t));
			} else if (t instanceof cn.nukkit.nbt.tag.LongTag) {
				list.add(convertNukkitLongToNova((cn.nukkit.nbt.tag.LongTag) t));
			} else if (t instanceof cn.nukkit.nbt.tag.StringTag) {
				list.add(convertNukkitStringToNova((cn.nukkit.nbt.tag.StringTag) t));
			} else if (t instanceof cn.nukkit.nbt.tag.IntArrayTag) {
				list.add(convertNukkitIntArrayToNova((cn.nukkit.nbt.tag.IntArrayTag) t));
			} else if (t instanceof cn.nukkit.nbt.tag.ByteTag) {
				list.add(convertNukkitByteToNova((cn.nukkit.nbt.tag.ByteTag) t));
			} else if (t instanceof cn.nukkit.nbt.tag.ByteArrayTag) {
				list.add(convertNukkitByteArrayToNova((cn.nukkit.nbt.tag.ByteArrayTag) t));
			} else if (t instanceof cn.nukkit.nbt.tag.DoubleTag) {
				list.add(convertNukkitDoubleToNova((cn.nukkit.nbt.tag.DoubleTag) t));
			} else if (t instanceof cn.nukkit.nbt.tag.FloatTag) {
				list.add(convertNukkitFloatToNova((cn.nukkit.nbt.tag.FloatTag) t));
			} else if (t instanceof cn.nukkit.nbt.tag.ListTag) {
				list.add(convertNukkitListToNova((cn.nukkit.nbt.tag.ListTag) t));
			} else if (t instanceof cn.nukkit.nbt.tag.CompoundTag) {
				list.add(convertNukkitCompoundToNova((cn.nukkit.nbt.tag.CompoundTag) t));
			}
		}
		return list;
	}

	public static cn.nukkit.nbt.tag.CompoundTag convertNovaCompoundToNukkit(CompoundTag novaTag) {
		cn.nukkit.nbt.tag.CompoundTag t = new cn.nukkit.nbt.tag.CompoundTag(novaTag.getName());
		for (Tag tag : novaTag.getValue().values()) {
			if (tag instanceof IntTag) {
				t.put(tag.getName(), convertNovaIntToNukkit((IntTag) tag));
			} else if (tag instanceof ShortTag) {
				t.put(tag.getName(), convertNovaShortToNukkit((ShortTag) tag));
			} else if (tag instanceof LongTag) {
				t.put(tag.getName(), convertNovaLongToNukkit((LongTag) tag));
			} else if (tag instanceof StringTag) {
				t.put(tag.getName(), convertNovaStringToNukkit((StringTag) tag));
			} else if (tag instanceof IntArrayTag) {
				t.put(tag.getName(), convertNovaIntArrayToNukkit((IntArrayTag) tag));
			} else if (tag instanceof ByteTag) {
				t.put(tag.getName(), convertNovaByteToNukkit((ByteTag) tag));
			} else if (tag instanceof ByteArrayTag) {
				t.put(tag.getName(), convertNovaByteArrayToNukkit((ByteArrayTag) tag));
			} else if (tag instanceof DoubleTag) {
				t.put(tag.getName(), convertNovaDoubleToNukkit((DoubleTag) tag));
			} else if (tag instanceof FloatTag) {
				t.put(tag.getName(), convertNovaFloatToNukkit((FloatTag) tag));
			} else if (tag instanceof ListTag) {
				t.put(tag.getName(), convertNovaListToNukkit((ListTag) tag));
			} else if (tag instanceof CompoundTag) {
				t.put(tag.getName(), convertNovaCompoundToNukkit((CompoundTag) tag));
			}
		}
		return t;
	}

	public static cn.nukkit.nbt.tag.IntTag convertNovaIntToNukkit(IntTag novaTag) {
		return new cn.nukkit.nbt.tag.IntTag(novaTag.getName(), novaTag.getValue());
	}

	public static cn.nukkit.nbt.tag.ShortTag convertNovaShortToNukkit(ShortTag novaTag) {
		return new cn.nukkit.nbt.tag.ShortTag(novaTag.getName(), (short) novaTag.getValue());
	}

	public static cn.nukkit.nbt.tag.LongTag convertNovaLongToNukkit(LongTag novaTag) {
		return new cn.nukkit.nbt.tag.LongTag(novaTag.getName(), novaTag.getValue());
	}

	public static cn.nukkit.nbt.tag.StringTag convertNovaStringToNukkit(StringTag novaTag) {
		return new cn.nukkit.nbt.tag.StringTag(novaTag.getName(), novaTag.getValue());
	}

	public static cn.nukkit.nbt.tag.IntArrayTag convertNovaIntArrayToNukkit(IntArrayTag novaTag) {
		return new cn.nukkit.nbt.tag.IntArrayTag(novaTag.getName(), novaTag.getValue());
	}

	public static cn.nukkit.nbt.tag.ByteTag convertNovaByteToNukkit(ByteTag novaTag) {
		return new cn.nukkit.nbt.tag.ByteTag(novaTag.getName(), (byte) novaTag.getValue());
	}

	public static cn.nukkit.nbt.tag.ByteArrayTag convertNovaByteArrayToNukkit(ByteArrayTag novaTag) {
		return new cn.nukkit.nbt.tag.ByteArrayTag(novaTag.getName(), novaTag.getValue());
	}

	public static cn.nukkit.nbt.tag.DoubleTag convertNovaDoubleToNukkit(DoubleTag novaTag) {
		return new cn.nukkit.nbt.tag.DoubleTag(novaTag.getName(), novaTag.getValue());
	}

	public static cn.nukkit.nbt.tag.FloatTag convertNovaFloatToNukkit(FloatTag novaTag) {
		return new cn.nukkit.nbt.tag.FloatTag(novaTag.getName(), novaTag.getValue());
	}

	public static cn.nukkit.nbt.tag.ListTag convertNovaListToNukkit(ListTag novaTag) {
		cn.nukkit.nbt.tag.ListTag nukkitList = new cn.nukkit.nbt.tag.ListTag(novaTag.getName());
		for (Tag tag : novaTag.getValue()) {
			if (tag instanceof IntTag) {
				nukkitList.add(convertNovaIntToNukkit((IntTag) tag));
			} else if (tag instanceof ShortTag) {
				nukkitList.add(convertNovaShortToNukkit((ShortTag) tag));
			} else if (tag instanceof LongTag) {
				nukkitList.add(convertNovaLongToNukkit((LongTag) tag));
			} else if (tag instanceof StringTag) {
				nukkitList.add(convertNovaStringToNukkit((StringTag) tag));
			} else if (tag instanceof IntArrayTag) {
				nukkitList.add(convertNovaIntArrayToNukkit((IntArrayTag) tag));
			} else if (tag instanceof ByteTag) {
				nukkitList.add(convertNovaByteToNukkit((ByteTag) tag));
			} else if (tag instanceof ByteArrayTag) {
				nukkitList.add(convertNovaByteArrayToNukkit((ByteArrayTag) tag));
			} else if (tag instanceof DoubleTag) {
				nukkitList.add(convertNovaDoubleToNukkit((DoubleTag) tag));
			} else if (tag instanceof FloatTag) {
				nukkitList.add(convertNovaFloatToNukkit((FloatTag) tag));
			} else if (tag instanceof ListTag) {
				nukkitList.add(convertNovaListToNukkit((ListTag) tag));
			} else if (tag instanceof CompoundTag) {
				nukkitList.add(convertNovaCompoundToNukkit((CompoundTag) tag));
			}
		}
		return nukkitList;
	}
}
