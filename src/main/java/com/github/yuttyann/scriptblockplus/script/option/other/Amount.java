package com.github.yuttyann.scriptblockplus.script.option.other;

import com.github.yuttyann.scriptblockplus.script.ScriptData;
import com.github.yuttyann.scriptblockplus.script.option.BaseOption;

public class Amount extends BaseOption {

	public Amount() {
		super("amount", "@amount:");
	}

	@Override
	protected boolean isValid() throws Exception {
		ScriptData scriptData = getScriptData();
		scriptData.addAmount(1);
		if (scriptData.getAmount() >= Integer.parseInt(getOptionValue())) {
			scriptData.remove();
			getMapManager().removeCoords(getScriptType(), getLocation());
		}
		scriptData.save();
		return true;
	}
}