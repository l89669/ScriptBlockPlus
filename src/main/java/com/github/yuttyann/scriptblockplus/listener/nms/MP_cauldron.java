package com.github.yuttyann.scriptblockplus.listener.nms;

import org.bukkit.craftbukkit.v1_7_R4.block.CraftBlock;

import net.minecraft.server.v1_7_R4.MovingObjectPosition;

public class MP_cauldron extends MovingPosition {

	MP_cauldron(Object rayTrace) throws ReflectiveOperationException {
		super();
		if (rayTrace instanceof MovingObjectPosition) {
			MovingObjectPosition mop = (MovingObjectPosition) rayTrace;
			this.vec3d = V3D_cauldron.fromNMSVec3D(mop.pos);
			this.blockFace = CraftBlock.notchToBlockFace(mop.face);
			this.blockPos = new BlockPos();
			this.blockPos.x = mop.b;
			this.blockPos.y = mop.c;
			this.blockPos.z = mop.d;
		}
	}

}
