package encryption;

/*
 * �滻�㷨
 * version: 2.0
 * ����byte[]����
 */
public class ReplaceEncryption2 {
	/*
	 * key: ��Կ
     * planText: ԭ��
     * return: ����
	 */
	public static byte[] encryption(byte[] planText, int[] key) {
		for(int i = 0;i < planText.length; i++) {
			planText[i] += key[i % 10];
		}
		return planText;
	}
	
	/*
	 * cipher: ����
	 * key: ��Կ
	 * return: ԭ��
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