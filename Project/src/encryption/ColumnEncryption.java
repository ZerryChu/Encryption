package encryption;

/*
 * 列置换算法
 * version: 1.0
 */
public class ColumnEncryption {
	/*
	 * key: 密钥
     * planText: 原文
     * return: 密文
	 */
	public static String encryption(String planText, String key) {
		int k, m;
		int[] num = new int[100010];
		char[] cipher = new char[100010];
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
		k = planText.length();

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
				cipher[i * l + j] = planText.charAt(i * l + num[j] - 1);

			}
		}
		String data = "";
		for (int i = 0; i < l; i++) { // 输出密文
			for (int j = 0; j < m; j++)
				data += cipher[j * l + i];
		}
		return data;
	}
 
	/*
	 * cipher: 密文
	 * key: 密钥
	 * return: 原文
	 */
	public static String decryption(String cipher, String key) {
		int m;
		int[] num = new int[100010];
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
		int k = cipher.length();
		if (k % l == 0) // 计算有多少行
			m = k / l;
		else
			m = k / l + 1;
		char[] plantext = new char[100010];
		int i, j = 0; // check
		for (i = 0; i < m; i++) {
			for (j = 0; j < l; j++) {
				plantext[i * l + num[j] - 1] = cipher.charAt(i * l + j);
			}
		}
		plantext[(i - 1) * l + j] = '\0';
		return plantext.toString();
	}
}
