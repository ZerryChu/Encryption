package encryption;

/*
 * ���û��㷨
 * version: 1.0
 */
public class ColumnEncryption {
	/*
	 * key: ��Կ
     * planText: ԭ��
     * return: ����
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
		 * int i = 0; // ĩβ��x for (m = k % l; m < l; m++) {
		 * 
		 * plantext[k + i] = 'x'; i++; }
		 */
		if (k % l == 0) // �����ж�����
			m = k / l;
		else
			m = k / l + 1;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < l; j++) {
				cipher[i * l + j] = planText.charAt(i * l + num[j] - 1);

			}
		}
		String data = "";
		for (int i = 0; i < l; i++) { // �������
			for (int j = 0; j < m; j++)
				data += cipher[j * l + i];
		}
		return data;
	}
 
	/*
	 * cipher: ����
	 * key: ��Կ
	 * return: ԭ��
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
		if (k % l == 0) // �����ж�����
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
