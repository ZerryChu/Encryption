package encryption;

/*
 * �滻�㷨
 * version: 1.0
 */
public class ReplaceEncryption {
	/*
	 * key: ��Կ
     * planText: ԭ��
     * return: ����
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
	 * cipher: ����
	 * key: ��Կ
	 * return: ԭ��
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
