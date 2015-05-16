package encryption;

import java.io.IOException;

/*
 * version 2.0
 * 面向byte[]
 */
public class Rc4Encryption2 {

	/*
	 * aKey: 密钥
	 * aInput： 待处理的内容 
	 */
	public static byte[] HloveyRC4(byte[] aInput, String aKey) {
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
		byte[] aOutput = new byte[aInput.length];
		for (short x = 0; x < aInput.length; x++) {
			i = (i + 1) % 256;
			j = (j + iS[i]) % 256;
			int temp = iS[i];
			iS[i] = iS[j];
			iS[j] = temp;
			int t = (iS[i] + (iS[j] % 256)) % 256;
			int iY = iS[t];
			char iCY = (char) iY;
			aOutput[x] = (byte)(aInput[x] ^ iCY);
		}
		return aOutput;
	}
	
	/* 
	public static void main(String[] args) throws IOException {
		//System.out.println(text);
		byte[] text = TXTHandler.readBytes("test.txt");
		byte[] chipher = Rc4Encryption2.HloveyRC4(text, "UTIRIKMPMX");
		//TXTHandler.write("answer.txt", chipher);
		//chipher = TXTHandler.read("answer.txt");
		System.out.println(new String(HloveyRC4(chipher, "UTIRIKMPMX")));
	}
	*/
}
