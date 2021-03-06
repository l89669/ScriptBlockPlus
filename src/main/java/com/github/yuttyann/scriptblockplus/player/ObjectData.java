package com.github.yuttyann.scriptblockplus.player;

public interface ObjectData {

	public void setData(String key, Object value);

	public <T> T getData(String key);

	public <T> T getData(String key, Class<T> classOfT);

	public <T> T removeData(String key);

	public <T> T removeData(String key, Class<T> classOfT);

	public boolean hasData(String key);

	public void clearData();
}