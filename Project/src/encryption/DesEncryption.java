package encryption;

import java.awt.TextArea;
import java.io.IOException;
import java.security.SecureRandom;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;

public class DesEncryption {
	/**
	 * 加密
	 * @param planText String
	 * @param password String
	 * @return String
	 */
	public static byte[] encryption(byte[] planText, String password) {
		try {
			byte[] datasource = planText;
			SecureRandom random = new SecureRandom();
			DESKeySpec desKey = new DESKeySpec(password.getBytes());
			// 创建一个密匙工厂，然后用它把DESKeySpec转换成
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(desKey);
			// Cipher对象实际完成加密操作
			Cipher cipher = Cipher.getInstance("DES");
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
			// 现在，获取数据并加密
			// 正式执行加密操作
			byte[] cipherBytes = cipher.doFinal(datasource);
			return cipherBytes;
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解密
	 * @param cipherString String
	 * @param password String
	 * @return String
	 * @throws Exception
	 */
	public static byte[] decryption(byte[] cipherString, String password) throws Exception {
		
		byte[] src = cipherString;
		// DES算法要求有一个可信任的随机数源
		SecureRandom random = new SecureRandom();
		// 创建一个DESKeySpec对象
		DESKeySpec desKey = new DESKeySpec(password.getBytes());
		// 创建一个密匙工厂
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		// 将DESKeySpec对象转换成SecretKey对象
		SecretKey securekey = keyFactory.generateSecret(desKey);
		// Cipher对象实际完成解密操作
		Cipher cipher = Cipher.getInstance("DES"); //DES/ECB/NoPadding
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey, random);
		// 真正开始解密操作
		byte[] planTextBytes = cipher.doFinal(src);
		return planTextBytes;
		/*String planText = "";
		for(int i = 0;i < planTextBytes.length; i++) {
			planText += (char)planTextBytes[i];
		}
		return planText;*/
	}
	
	
	public static void main(String[] args) throws IOException {
		byte[] Text;
		System.out.println(new String(Text = TXTHandler.readBytes("test.txt")));
		for (int i = 0;i < Text.length; i++) {
			System.out.println((char)Text[i]);
		}
	}
	/*
	public static void main(String[] args) throws Exception {
		String planText = "abcdefghijklmno";
		System.out.println(planText.getBytes().length);
		//i lov e you !!! haha ha dss
		byte[] chipher = DesEncryption.encryption(planText.getBytes(), "12345678");
        TXTHandler.writeBytes("check.txt", chipher);
       // String chipherString = TXTHandler.read("check.txt");
        //System.out.println(chipher.length); 
        chipherString = ColumnEncryption.encryption(chipherString, "123456789101234");
        TXTHandler.write("check.txt", chipherString);
        chipherString = TXTHandler.read("check.txt");
		String textsString = ColumnEncryption.decryption(chipherString, "123456789101234");
		TXTHandler.write("check.txt", textsString);
		byte[] Text = TXTHandler.readBytes("check.txt");
		Text = DesEncryption.decryption(Text, "12345678");
		//System.out.println(Text);
		System.out.println(new String(Text));
	//	for(int i = 0;i < Text.length; i++)
	}
	*/
}
