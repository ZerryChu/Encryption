package encryption;
/*
 * 列置换算法
 * version: 2.0
 * 面向byte[]
 */
public class ColumnEncryption2 {
	/*
	 * key: 密钥
     * planText: 原文
     * return: 密文
	 */
	public static byte[] encryption(byte[] planText, String key) {
		int k, m;
		int[] num = new int[planText.length];
		byte[] cipher = new byte[planText.length];
		int l = key.length();
		for (int i = 0; i < l; i++) {
			num[i] = 0;
			for (int j = 0; j < l; j++) {
				if (key.charAt(j) <= key.charAt(i)) {
					num[i] = num[i] + 1;
				}
				if (key.charAt(j) == key.charAt(i) && j > i)
					num[i] = num[i] - 1;
			}
		}
		k = planText.length;

		/*
		 * int i = 0; // 末尾补x for (m = k % l; m < l; m++) {
		 * 
		 * plantext[k + i] = 'x'; i++; }
		 */
		if (k % l == 0) // 计算有多少行
			m = k / l;
		else
			m = k / l + 1;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < l; j++) {
				cipher[i * l + j] = planText[i * l + num[j] - 1];
			}
		}
		return cipher;
	}
 
	/*
	 * cipher: 密文
	 * key: 密钥
	 * return: 原文
	 */
	public static byte[] decryption(byte[] cipher, String key) {
		int m;
		int[] num = new int[cipher.length];
		int l = key.length();
		for (int i = 0; i < l; i++) {
			num[i] = 0;
			for (int j = 0; j < l; j++) {
				if (key.charAt(j) <= key.charAt(i)) {
					num[i] = num[i] + 1;
				}
				if (key.charAt(j) == key.charAt(i) && j > i)
					num[i] = num[i] - 1;
			}
		}
		int k = key.length();
		if (k % l == 0) // 计算有多少行
			m = k / l;
		else
			m = k / l + 1;
		byte[] plantext = new byte[cipher.length];
		int i, j = 0;
		for (i = 0; i < m; i++) {
			for (j = 0; j < l; j++) {
				plantext[i * l + num[j] - 1] = cipher[i * l + j];
			}
		}
		//plantext[(i - 1) * l + j] = '\0';
		return plantext;
	}
	
	/* test 
	public static void main(String[] args) {
		String planText = "1 3 g   33d 2 6 ";
		String key = EncryptionManage.getRandomCharKey(planText.getBytes().length);
		byte[] cipher = ColumnEncryption2.encryption(planText.getBytes(), key);
		System.out.println(new String(decryption(cipher, key)));		
	}
	*/
}
