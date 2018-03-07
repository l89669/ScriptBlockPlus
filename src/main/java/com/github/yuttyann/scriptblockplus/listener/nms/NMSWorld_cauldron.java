package com.github.yuttyann.scriptblockplus.listener.nms;

import org.bukkit.World;
import org.bukkit.craftbukkit.v1_7_R4.CraftWorld;

public class NMSWorld_cauldron extends NMSWorld {

	public NMSWorld_cauldron(World world) {
		this.world = ((CraftWorld) world).getHandle();
	}

	public Object getWorld() {
		return world;
	}

	public MovingPosition rayTrace(Vec3D start, Vec3D end, boolean flag) {
		try {
			return new MP_cauldron(((net.minecraft.server.v1_7_R4.World) this.world).rayTrace(
					(net.minecraft.server.v1_7_R4.Vec3D) start.toNMSVec3D(),
					(net.minecraft.server.v1_7_R4.Vec3D) end.toNMSVec3D(), flag));
		} catch (ReflectiveOperationException e) {
			return null;
		}
	}
}
