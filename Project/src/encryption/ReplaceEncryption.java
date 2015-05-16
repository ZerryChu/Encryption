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
		char[] data = new char[planText.length()];
		for(int i = 0;i < planText.length(); i++) {
			data[i] = planText.charAt(i);
			if(data[i] == ' ' || data[i] == '\n')
				 continue;
			data[i] = (char)(key[i % 10] + data[i]);

		}
		return String.valueOf(data);
	}
	
	/*
	 * cipher: 密文
	 * key: 密钥
	 * return: 原文
	 */
	public static String decryption(String cipher, int[] key) {
		char[] data = new char[cipher.length()];
		for(int i = 0;i < cipher.length(); i++) {
			data[i] = cipher.charAt(i);
			if(data[i] == ' ' || data[i] == '\n')
				continue;
			data[i] = (char)(data[i] - key[i % 10]);
		}
		return String.valueOf(data);
	}
	/*
	public static void main(String[] args) {
		int[] keys = {1,2,1,1,1,0,1,1,0,2};
		String cipher = ReplaceEncryption.decryption("j mpwe jrkt j bm {ets{- iaib jb# ", keys);
		System.out.println(cipher);
	}
	*/
}
