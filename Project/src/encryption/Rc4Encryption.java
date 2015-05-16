package encryption;

import java.io.IOException;

public class Rc4Encryption {

	/*
	 * aKey: 密钥
	 * aInput： 待处理的内容 
	 */
	public static String HloveyRC4(String aInput, String aKey) {
		int[] iS = new int[256];
		byte[] iK = new byte[256];
		for (int i = 0; i < 256; i++)
			iS[i] = i;
		int j = 1;
		for (short i = 0; i < 256; i++) {
			iK[i] = (byte) aKey.charAt((i % aKey.length()));
		}
		j = 0;
		for (int i = 0; i < 255; i++) {
			j = (j + iS[i] + iK[i]) % 256;
			int temp = iS[i];
			iS[i] = iS[j];
			iS[j] = temp;
		}
		int i = 0;
		j = 0;
		char[] iInputChar = aInput.toCharArray();
		char[] iOutputChar = new char[iInputChar.length];
		for (short x = 0; x < iInputChar.length; x++) {
			i = (i + 1) % 256;
			j = (j + iS[i]) % 256;
			int temp = iS[i];
			iS[i] = iS[j];
			iS[j] = temp;
			int t = (iS[i] + (iS[j] % 256)) % 256;
			int iY = iS[t];
			char iCY = (char) iY;
			iOutputChar[x] = (char) (iInputChar[x] ^ iCY);
		}
		return new String(iOutputChar);
	}
	
	// 不能直接存进文件系统 
	public static void main(String[] args) throws IOException {
		//System.out.println(text);
		String text = TXTHandler.read("test.txt");
		String chipher = Rc4Encryption.HloveyRC4(text, "UTIRIKMPMX");
		//TXTHandler.write("answer.txt", chipher);
		//chipher = TXTHandler.read("answer.txt");
		System.out.println(chipher);
		System.out.println(Rc4Encryption.HloveyRC4(chipher, "UTIRIKMPMX"));
	}
}
