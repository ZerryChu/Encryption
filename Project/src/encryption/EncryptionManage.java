package encryption;

import java.io.IOException;
import java.util.Random;
import java.util.Stack;
import java.util.Vector;

import javax.swing.JCheckBox;

public class EncryptionManage {
	/*
	 * box 勾选框
	 * keySavePath 密钥存储的路径 
	 * filePath 原文文件路径
	 * planText 原文内容
	 * savePath 密文存储路径
	 */
	public static void runEncryption(JCheckBox[] box, String keySavePath,
			String filePath, String savePath) throws IOException {
		TXTHandler.write2(keySavePath, savePath + ": ");
		String planText = TXTHandler.read(filePath);
		String chipher;
		for (int i = 0; i < box.length; i++) {
			if (i == 0) {
				if (true == box[0].isSelected()) {
					String key = getRandomCharKey(planText.length());
					TXTHandler.write2(keySavePath, key + " ");
					chipher = ColumnEncryption.encryption(planText, key);
					planText = chipher;
				} else
					TXTHandler.write2(keySavePath, "null" + " ");
			}
			if (i == 1) {
				if (true == box[i].isSelected()) {
					int[] key = getRandomIntKey(10);
					String str = "";
					for(int j = 0;j < key.length; j++) {
						str += String.valueOf(key[j]);
					}
					TXTHandler.write2(keySavePath, str + " ");
					chipher = ReplaceEncryption.encryption(planText, key);
					planText = chipher;
				} else
					TXTHandler.write2(keySavePath, "null" + " ");
			}
			if (i == 2) {
				if (true == box[i].isSelected()) {
					String key = getRandomCharKey(10);
					TXTHandler.write2(keySavePath, key + " ");
					chipher = Rc4Encryption.HloveyRC4(planText, key);
					planText = chipher;
				} else
					TXTHandler.write2(keySavePath, "null" + " ");
			}
			if (i == 3) {
				if (true == box[i].isSelected()) {
					String key = getRandomCharKey(8);
					TXTHandler.write2(keySavePath, key + " ");
					chipher = DesEncryption.encryption(planText, key);
					planText = chipher;
				} else
					TXTHandler.write2(keySavePath, "null" + " ");
			}
		}
		TXTHandler.write2(keySavePath, "\r\n"); // 换行
		TXTHandler.write(savePath, planText);
	}

	/*
	 * box 勾选框 
	 * filePath 密文路径 
	 * savePath 解密后原文存储路径
	 * box与加密相反的读取顺序，即顺序读入密钥存入栈中,以后进先出的方式取出密钥解密
	 */
	public static boolean runDecryption(JCheckBox[] box, String filePath, 
			String savePath) throws Exception {
		Stack<String> keys = new Stack<>();
		String cipher = TXTHandler.read(filePath);
		String key;
		TXTHandler.parse("keys.txt", filePath, keys);
		System.out.println(keys);
		for (int i = box.length - 1; i >= 0; i--) { //debug: 加密后密文长度变短
			if (i == 0) {
				key = keys.pop();
				if(key.equals("null")) {
					continue;
				}
				if(true == box[i].isSelected()) {
					cipher = ColumnEncryption.decryption(cipher, key);
				}
			}
			if (i == 1) { 
				key = keys.pop();
				if(key.equals("null"))
					continue;
				if(true == box[i].isSelected()) {
					int[] intKey = new int[10];
					String temp;
					for(int j = 0;j < key.length(); j++) {
						temp = "";
						temp += key.charAt(j);
						intKey[j] = Integer.parseInt(temp);
					}
					cipher = ReplaceEncryption.decryption(cipher, intKey);
				}
			}
			if (i == 2) {
				key = keys.pop();
				if(key.equals("null"))
					continue;
				if(true == box[i].isSelected()) {
					cipher = Rc4Encryption.HloveyRC4(cipher, key);
				}
			}
			if (i == 3) {
				key = keys.pop();
				if(key.equals("null"))
					continue;
				if(true == box[i].isSelected()) {
					byte[] cipherBytes = DesEncryption.decryption(cipher.getBytes(), key);
					//cipherBytes怎么存
				}
			}
		}
		TXTHandler.write(savePath, cipher);
		return true;
		//解密完删除密钥
	}

	public static String getRandomCharKey(int len) {
		String key = "";
		Random random = new Random();
		for (int i = 0; i < len; i++) {
			key += (char) (65 + random.nextInt(26));
		}
		return key;
	}

	public static int[] getRandomIntKey(int len) {
		int[] key = new int[10];
		Random random = new Random();
		for (int i = 0; i < len; i++) {
			key[i] = random.nextInt(3);
		}
		return key;
	}
}
