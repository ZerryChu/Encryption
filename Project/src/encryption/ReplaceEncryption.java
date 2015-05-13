package encryption;

/*
 * 替换算法
 * version: 1.0
 */
public class ReplaceEncryption {
	/*
	 * key: 密钥
     * planText: 原文
     * return: 密文
	 */
	public static String encryption(String planText, int[] key) {
		char[] data = new char[100010];
		for(int i = 0;i < key.length; i++) {
			data[i] = planText.charAt(i);
			data[i] += key[i];
		}
		return data.toString();
	}
	
	/*
	 * cipher: 密文
	 * key: 密钥
	 * return: 原文
	 */
	public static String decryption(String cipher, int[] key) {
		char[] data = new char[100010];
		for(int i = 0;i < key.length; i++) {
			data[i] = cipher.charAt(i);
			data[i] -= key[i];
		}
		return data.toString();
	}
}
