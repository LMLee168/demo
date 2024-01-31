/**
 * 
 */
package unicorn.mp.common.cache;


import unicorn.mp.common.inter.AnnoCacheKeyDesc;

public enum CacheKeys {

	//缓存键值枚举
	@AnnoCacheKeyDesc(desc = "_telx")
	SSS,
	@AnnoCacheKeyDesc(desc = "_official")
	OFFICIAL_ACCESSTOKEN_KEY,
	;

	// key的版本号
	public static final int KEY_VERSION = 1;
	//应用名称
	public static final String APP_NAME = "demo";

	public static final String KEY_PREFIX = APP_NAME+":" + KEY_VERSION + "_";

	public String getKey(Object... keys) {
		StringBuilder sb = new StringBuilder(KEY_PREFIX);
		sb.append(this.name().toLowerCase());
		for (Object key : keys) {
			sb.append("_").append(key);
		}
		return sb.toString();
	}

	public static void main(String args[]) {
		System.out.println(SSS.getKey(2280));
	}

}
