package com.github.yuttyann.scriptblockplus.script.option.other;

import org.bukkit.Material;
import org.bukkit.block.Block;

import com.github.yuttyann.scriptblockplus.script.option.BaseOption;
import com.github.yuttyann.scriptblockplus.utils.StreamUtils;
import com.github.yuttyann.scriptblockplus.utils.StringUtils;

public class BlockType extends BaseOption {

	public BlockType() {
		super("blocktype", "@blocktype:");
	}

	@Override
	protected boolean isValid() throws Exception {
		String[] array = StringUtils.split(getOptionValue(), ",");
		Block block = getLocation().getBlock();
		return StreamUtils.anyMatch(array, s -> equals(block, s));
	}

	private boolean equals(Block block, String blockType) {
		if (block == null || StringUtils.isEmpty(blockType)) {
			return false;
		}
		String[] array = StringUtils.split(blockType, ":");
		Material type = Material.getMaterial(array[0]);
		if (type == null || !type.isBlock()) {
			return false;
		}
		byte data = array.length == 2 ? Byte.parseByte(array[1]) : 0;
		return type == block.getType() && data == getData(block);
	}

	private byte getData(Block block) {
		@SuppressWarnings("deprecation")
		byte data = block.getData();
		return data;
	}
}