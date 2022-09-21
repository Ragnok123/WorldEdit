package ru.ragnok123.worldEdit.utils;

import cn.nukkit.block.Block;
import cn.nukkit.level.Position;
import cn.nukkit.math.Vector3;
import ru.ragnok123.worldEdit.WEPlayer;

public class WorldUtils {

    public static int set(WEPlayer dat, Position pos1, Position pos2, Block b) {
        int minX = (int) Math.min(pos1.x, pos2.x);
        int minY = (int) Math.min(pos1.y, pos2.y);
        int minZ = (int) Math.min(pos1.z, pos2.z);
        int maxX = (int) Math.max(pos1.x, pos2.x);
        int maxY = (int) Math.max(pos1.y, pos2.y);
        int maxZ = (int) Math.max(pos1.z, pos2.z);

        Vector3 v = new Vector3();
        BlocksArray undo = new BlocksArray();
        int blocks = 0;

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                	v.setComponents(x, y, z);
                	
                	undo.blocks.add(pos1.level.getBlock(v));
                    pos1.level.setBlock(v, b, true, false);
                    blocks++;
                }
            }
        }
        dat.addUndoBlocks(undo);
        return blocks;
    }

    public static int walls(Position pos1, Position pos2, Block b) {
        int minX = (int) Math.min(pos1.x, pos2.x);
        int minY = (int) Math.min(pos1.y, pos2.y);
        int minZ = (int) Math.min(pos1.z, pos2.z);
        int maxX = (int) Math.max(pos1.x, pos2.x);
        int maxY = (int) Math.max(pos1.y, pos2.y);
        int maxZ = (int) Math.max(pos1.z, pos2.z);

        Vector3 v = new Vector3();
        int blocks = 0;

        for (int y = minY; y <= maxY; y++) {
            for (int x = minX; x <= maxX; x++) {
                pos1.level.setBlock(v.setComponents(x, y, minZ), b, true, false);
                pos1.level.setBlock(v.setComponents(x, y, maxZ), b, true, false);
                blocks += 2;
            }

            for (int z = minZ; z <= maxZ; z++) {
                pos1.level.setBlock(v.setComponents(minX, y, z), b, true, false);
                pos1.level.setBlock(v.setComponents(maxX, y, z), b, true, false);
                blocks += 2;
            }
        }

        return blocks;
    }

    public static int copy(Position pos1, Position pos2, Vector3 center, BlocksArray copy) {
        int minX = (int) Math.min(pos1.x, pos2.x);
        int minY = (int) Math.min(pos1.y, pos2.y);
        int minZ = (int) Math.min(pos1.z, pos2.z);
        int maxX = (int) Math.max(pos1.x, pos2.x);
        int maxY = (int) Math.max(pos1.y, pos2.y);
        int maxZ = (int) Math.max(pos1.z, pos2.z);

        int blocks = 0;

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    int id = pos1.level.getBlockIdAt(x, y, z);

                    if (id == 0) {
                        continue;
                    }

                    int damage = pos1.level.getBlockDataAt(x, y, z);

                    Block b = Block.get(id, damage);

                    if (b != null) {
                        b.setComponents(x - center.getFloorX(), y - center.getFloorY(), z - center.getFloorZ());
                        copy.blocks.add(b);
                        blocks++;
                    }
                }
            }
        }

        return blocks;
    }

    public static int paste(WEPlayer dat, Position center, BlocksArray copy) {
        int blocks = 0;
        Vector3 v = new Vector3();
        BlocksArray undo = new BlocksArray();

        for (Block b : copy.blocks) {
        	v.setComponents(center.getFloorX() + b.x, center.getFloorY() + b.y, center.getFloorZ() + b.z);
        	
        	undo.blocks.add(center.getLevel().getBlock(v));
            center.getLevel().setBlock(v, b, true, false);
            blocks++;
        }
        dat.addUndoBlocks(undo);

        return blocks;
    }
    
    public static int rotateY(WEPlayer dat, Position center, Selection sel, int degrees) {
    	double rad = Math.toRadians(degrees);
    	int blocks = 0;
    	Vector3 v = new Vector3();
    	
    	int diffX = center.getFloorX();
    	int diffZ = center.getFloorZ();
    	
    	int minX = (int) Math.min(sel.getPosOne().x, sel.getPosTwo().x);
        int minY = (int) Math.min(sel.getPosOne().y, sel.getPosTwo().y);
        int minZ = (int) Math.min(sel.getPosOne().z, sel.getPosTwo().z);
        int maxX = (int) Math.max(sel.getPosOne().x, sel.getPosTwo().x);
        int maxY = (int) Math.max(sel.getPosOne().y, sel.getPosTwo().y);
        int maxZ = (int) Math.max(sel.getPosOne().z, sel.getPosTwo().z);
    	
    	BlocksArray undo = new BlocksArray();
    	
        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    int id = sel.getPosOne().level.getBlockIdAt(x, y, z);
                    if (id == 0) {
                        continue;
                    }
                    int damage = center.level.getBlockDataAt(x, y, z);
                    Block b = Block.get(id, damage);
                    if(b != null) {
                        int newX = x - diffX;
                        int newZ = z - diffZ;
                        double kek = Math.pow(newX, 2) + Math.pow(newZ, 2);
                        double distance = Math.sqrt(kek);
                        double angle = Math.atan2(newZ, newX) + rad;

                        v.setComponents(
                        		Math.round(distance * Math.cos(angle)) + diffX,
                        		y,
                        		Math.round(distance * Math.sin(angle)) + diffZ);
                        undo.blocks.add(center.getLevel().getBlock(new Vector3(x,y,z)));
                        center.level.setBlock(new Vector3(x,y,z), Block.get(Block.AIR));
                        center.level.setBlock(v, b);
                        blocks++;
                    }
                }
            }
        }
        dat.getUndoSteps().add(undo);
    	
    	return blocks;
    }

    public static int cyl(WEPlayer dat, Position center, int radius, Block b) {
        Vector3 v = new Vector3();
        BlocksArray undo = new BlocksArray();
        int blocks = 0;

        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                if (x * x + z * z <= radius * radius) {
                	v.setComponents(center.x + x, center.y, center.z + z);
                	
                	undo.blocks.add(center.level.getBlock(v));
                    center.level.setBlock(v, b, true, false);
                    blocks++;
                }
            }
        }
        dat.addUndoBlocks(undo);
        return blocks;
    }

    public static int hcyl(WEPlayer dat, Position center, int radius, Block b) {

        Vector3 v = new Vector3();
        BlocksArray undo = new BlocksArray();
        int blocks = 0;

        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                if (x * x + z * z <= radius * radius) {
                    if (x * x + z * z > (radius - 1) * (radius - 1)) {
                    	v.setComponents(center.x + x, center.y, center.z + z);
                    	
                    	undo.blocks.add(center.level.getBlock(v));
                        center.level.setBlock(v, b, true, false);
                        blocks++;
                    }
                }
            }
        }
        dat.addUndoBlocks(undo);
        return blocks;
    }

    public static int sphere(WEPlayer dat, Position center, int radius, Block b) {
        Vector3 v = new Vector3();
        BlocksArray undo = new BlocksArray();
        int blocks = 0;

        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    if (x * x + y * y + z * z <= radius * radius) {
                    	v.setComponents(center.x + x, center.y + y, center.z + z);
                    	
                    	undo.blocks.add(center.level.getBlock(v));
                        center.level.setBlock(v, b, true, false);
                        blocks++;
                    }
                }
            }
        }
        dat.addUndoBlocks(undo);
        return blocks;
    }

    public static int hsphere(WEPlayer dat, Position center, int radius, Block b) {
        Vector3 v = new Vector3();
        BlocksArray undo = new BlocksArray();
        int blocks = 0;

        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    if (x * x + y * y + z * z <= radius * radius) {
                        if (x * x + y * y + z * z > (radius - 1) * (radius - 1)) {
                        	v.setComponents(center.x + x, center.y + y, center.z + z);
                        	
                        	undo.blocks.add(center.level.getBlock(v));
                            center.level.setBlock(v, b, true, false);
                            blocks++;
                        }
                    }
                }
            }
        }
        dat.addUndoBlocks(undo);
        return blocks;
    }

    public static int replace(WEPlayer dat, Position pos1, Position pos2, Block b, Block replace) {
        int minX = (int) Math.min(pos1.x, pos2.x);
        int minY = (int) Math.min(pos1.y, pos2.y);
        int minZ = (int) Math.min(pos1.z, pos2.z);
        int maxX = (int) Math.max(pos1.x, pos2.x);
        int maxY = (int) Math.max(pos1.y, pos2.y);
        int maxZ = (int) Math.max(pos1.z, pos2.z);

        Vector3 v = new Vector3();
        BlocksArray undo = new BlocksArray();
        int blocks = 0;

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    if (pos1.level.getBlockIdAt(x, y, z) == b.getId() && pos1.level.getBlockDataAt(x, y, z) == b.getDamage()) {
                    	v.setComponents(x, y, z);
                    	
                    	undo.blocks.add(pos1.level.getBlock(v));
                        pos1.level.setBlock(v, replace, true, false);
                        blocks++;
                    }
                }
            }
        }
        dat.addUndoBlocks(undo);
        return blocks;
    }
    
    public static int undo(WEPlayer dat, int step) {
    	
    	int blocks = 0;
    	Vector3 v = new Vector3();
    	BlocksArray redo = dat.getUndoSteps().get(step);
    	
    	for(Block b : redo.blocks) {
    		v.setComponents(b.x, b.y, b.z);
    		b.level.setBlock(v, b);
    		blocks++;
    	}
    	
    	dat.getUndoSteps().remove(step);
    	return blocks;
    }
}
