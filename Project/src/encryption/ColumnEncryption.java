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
		int[] num = new int[planText.length()];
		char[] cipher = new char[planText.length()];
		int l = key.length();
		System.out.println(planText.length());
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
		int[] num = new int[cipher.length()];
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
		char[] plantext = new char[cipher.length()];
		int i, j = 0;
		for (i = 0; i < m; i++) {
			for (j = 0; j < l; j++) {
				plantext[i * l + num[j] - 1] = cipher.charAt(i * l + j);
			}
		}
		//plantext[(i - 1) * l + j] = '\0';
		return String.valueOf(plantext);
	}
	
	/* test 
	public static void main(String[] args) {
		String planText = "1 3 g 2 6 ";
		String cipher = ColumnEncryption.encryption(planText, "KTVGMXEJZY");
		System.out.println(planText.length());
		System.out.println(cipher.length());
		System.out.println(planText);
		System.out.println(cipher);
		System.out.println(ColumnEncryption.decryption(cipher, "KTVGMXEJZY"));
		
	}
	*/
}
