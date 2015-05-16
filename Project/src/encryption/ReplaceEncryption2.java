package encryption;

/*
 * 替换算法
 * version: 2.0
 * 面向byte[]处理
 */
public class ReplaceEncryption2 {
	/*
	 * key: 密钥
     * planText: 原文
     * return: 密文
	 */
	public static byte[] encryption(byte[] planText, int[] key) {
		for(int i = 0;i < planText.length; i++) {
			planText[i] += key[i % 10];
		}
		return planText;
	}
	
	/*
	 * cipher: 密文
	 * key: 密钥
	 * return: 原文
	 */
	public static byte[] decryption(byte[] cipher, int[] key) {
		for(int i = 0;i < cipher.length; i++) {
			cipher[i] -= key[i % 10];
		}
		return cipher;
	}

	/*
	public static void main(String[] args) {
		int[] keys = {1,2,1,1,1,0,1,1,0,2};
		String planText = "ifjaljfla  f";
		byte[] planTextBytes = planText.getBytes();
		byte[] text = ReplaceEncryption2.encryption(planTextBytes, keys);
		byte[] cipher = ReplaceEncryption2.decryption(text, keys);
		System.out.println(new String(cipher));
	}
	*/
}