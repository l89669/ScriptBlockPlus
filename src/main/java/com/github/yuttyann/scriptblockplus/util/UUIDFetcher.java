package com.github.yuttyann.scriptblockplus.util;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;

import com.github.yuttyann.scriptblockplus.json.JsonMap;
import com.github.yuttyann.scriptblockplus.json.WebJson;

public class UUIDFetcher implements Callable<Map<String, UUID>> {

	private static final String PROFILE_URL = "https://api.mojang.com/users/profiles/minecraft/";

	private JsonMap jsonMap;

	public UUIDFetcher(String name) {
		jsonMap = new WebJson(PROFILE_URL + name).getJsonMap();
	}

	@Override
	public Map<String, UUID> call() throws Exception {
		Map<String, UUID> uuidMap = new HashMap<String, UUID>();
		if (jsonMap != null) {
			Map<Object, Object> map = jsonMap.get(0);
			String id = map.get("id").toString();
			String name = map.get("name").toString();
			UUID uuid = fromString(id);
			uuidMap.put(name, uuid);
		}
		return uuidMap;
	}

	private UUID fromString(String id) {
		return UUID.fromString(id.substring(0, 8) + "-" + id.substring(8, 12) + "-" + id.substring(12, 16) + "-" + id.substring(16, 20) + "-" + id.substring(20, 32));
	}

	public static UUID getUniqueId(String name) throws Exception {
		return new UUIDFetcher(name).call().get(name);
	}
}