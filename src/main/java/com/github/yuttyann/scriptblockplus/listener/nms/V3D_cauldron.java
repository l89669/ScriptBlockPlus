package com.github.yuttyann.scriptblockplus.listener.nms;

public class V3D_cauldron extends Vec3D {

	public V3D_cauldron(double x, double y, double z) {
		super(x, y, z);
	}

	public static Vec3D fromNMSVec3D(Object nmsVec3D) throws ReflectiveOperationException {
		if (nmsVec3D instanceof net.minecraft.server.v1_7_R4.Vec3D) {
			net.minecraft.server.v1_7_R4.Vec3D v3d = (net.minecraft.server.v1_7_R4.Vec3D) nmsVec3D;
			return new Vec3D(v3d.a, v3d.b, v3d.c);
		}
		return Vec3D.fromNMSVec3D(nmsVec3D);
	}

	@Override
	public Object toNMSVec3D() {
		return newNMSVec3D(x, y, z);
	}

	public static Object newNMSVec3D(double x, double y, double z) {
		return net.minecraft.server.v1_7_R4.Vec3D.a(x, y, z);
	}

}
