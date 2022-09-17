package ru.ragnok123.worldEdit;

import java.util.ArrayList;

import cn.nukkit.Player;
import lombok.Getter;
import ru.ragnok123.worldEdit.utils.BlocksArray;
import ru.ragnok123.worldEdit.utils.Selection;

public class WEPlayer {

    @Getter
    private Player player;

    @Getter
    private Selection selection;

    @Getter
    public BlocksArray copiedBlocks = null;
    
    @Getter
    public ArrayList<BlocksArray> undoSteps = new ArrayList<BlocksArray>();

    public WEPlayer(Player p) {
        this.player = p;
        this.selection = new Selection();
    }
    
    public void addUndoBlocks(BlocksArray blocks) {
    	this.undoSteps.add(blocks);
    }
    
    public boolean hasSelection() {
    	return getSelection().getPosOne() != null && getSelection().getPosTwo() != null;
    }
}
