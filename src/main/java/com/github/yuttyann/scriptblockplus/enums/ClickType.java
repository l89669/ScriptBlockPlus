package com.github.yuttyann.scriptblockplus.enums;

import java.util.ArrayList;
import java.util.List;

public enum ClickType {
	CREATE,
	ADD,
	REMOVE,
	VIEW;

	private static final String[] TYPES;

	static {
		List<String> list = new ArrayList<String>();
		for (ScriptType scriptType : ScriptType.values()) {
			for (ClickType clickType : ClickType.values()) {
				list.add(clickType.create(scriptType));
			}
		}
		TYPES = list.toArray(new String[list.size()]);
	}

	public String create(ScriptType scriptType) {
		return scriptType.name() + "_" + name();
	}

	public static String[] types() {
		return TYPES;
	}
}