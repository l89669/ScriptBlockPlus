package com.github.yuttyann.scriptblockplus.script.option.nms;

import org.bukkit.entity.Player;

import com.github.yuttyann.scriptblockplus.enums.reflection.PackageType;
import com.github.yuttyann.scriptblockplus.utils.StringUtils;
import com.github.yuttyann.scriptblockplus.utils.Utils;

public class NMSHelper {

	protected static void sendPacket(Player player, Object packet) {
		try {
			Object handle = player.getClass().getMethod("getHandle").invoke(player);
			Object connection = handle.getClass().getField("playerConnection").get(handle);
			Class<?> nmsPacketClass = PackageType.NMS.getClass("Packet");
			connection.getClass().getMethod("sendPacket", nmsPacketClass).invoke(connection, packet);
		} catch (ReflectiveOperationException e) {
			e.printStackTrace();
		}
	}

	protected static String getChatSerializerName() {
		String chatSerializer = "ChatSerializer";
		return Utils.isCB183orLater() ? "IChatBaseComponent$" + chatSerializer : chatSerializer;
	}

	protected static String getEnumTitleActionName() {
		String enumTitleAction = "EnumTitleAction";
		return Utils.isCB183orLater() ? "PacketPlayOutTitle$" + enumTitleAction : enumTitleAction;
	}

	protected static Object getEnumField(Class<?> clazz, String name) {
		if (clazz == null || StringUtils.isEmpty(name)) {
			return null;
		}
		for (Object field : clazz.getEnumConstants()) {
			if (String.valueOf(field).equals(name)) {
				return field;
			}
		}
		return null;
	}
}
