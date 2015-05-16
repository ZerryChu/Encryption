package encryption;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

public class TXTHandler {

	public static String read(String filePath) throws IOException {
		String data = "";
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String temp = br.readLine();// һ�ζ���һ�У�ֱ������nullΪ�ļ�����
		while (temp != null) {
			data += temp;
			data += '\n';
			temp = br.readLine(); // ���Ŷ���һ��
		}
		return data;
	}

	public static byte[] readBytes(String filePath) throws IOException {
		FileInputStream fi = new FileInputStream(filePath);
		byte[] data = new byte[fi.available()];
		fi.read(data);
		return data;
	}
	/*
	 * ��д������Ḳ��ԭ�ļ�
	 */
	public static void write(String savePath, String data) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(savePath));
		writer.write(data);
		writer.flush(); // ��������, ��֤����д��
		writer.close();
	}

	/*
	 * ��д������Ḳ��ԭ�ļ�
	 */
	public static void write(String savePath, int[] data) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(savePath));
		for (int i = 0; i < data.length; i++)
			writer.write(String.valueOf(data[i]));
		writer.flush(); // ��������, ��֤����д��
		writer.close();
	}

	/*
	 * ������ԭ�ļ���д�����
	 */
	public static void write2(String filePath, String data) throws IOException {
		FileOutputStream out = new FileOutputStream(filePath, true);
		out.write(data.getBytes());
		out.flush();
		out.close();
	}

	/*
	 * ����
	 */
	public static void writeBytes(String filePath, byte[] data) throws IOException {
		FileOutputStream out = new FileOutputStream(filePath, false);
		out.write(data);
		out.flush();
		out.close();
	}
	
	/*
	 * test public static void main(String[] args) throws IOException {
	 * TXTHandler txtHandler = new TXTHandler(); txtHandler.read("test.txt");
	 * txtHandler.write("test2.txt"); }
	 */

	public static void parse(String TXTPath, String filePath, Stack<String> keys)
			throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(TXTPath));
		String temp = br.readLine();// һ�ζ���һ�У�ֱ������nullΪ�ļ�����
		while (temp != null) {
			if (temp.charAt(0) == '#')
				temp = br.readLine();
			int pos = temp.lastIndexOf(':');
			String path = temp.substring(0, pos);
			temp = temp.substring(pos + 1);
			if (filePath.equals(path)) {
				for (int i = 0; i < 4; i++) { 
					for (int j = 0; j < temp.length(); j++)
						if (temp.charAt(j) != ' ') {
							temp = temp.substring(j);
							break;
						}
					pos = temp.indexOf(" ");
					String key;
					if (pos != -1)
						key = temp.substring(0, pos);
					else {
						key = temp;
					}
					temp = temp.substring(pos + 1);
					keys.add(key);
				}
				return;
			}
			temp = br.readLine(); // ���Ŷ���һ��
		}
	}
	/*
	 * public static void main(String[] args) throws IOException { Stack<String>
	 * keys = new Stack<>(); TXTHandler.parse("test.txt", "AAA", keys);
	 * System.out.println(keys); }
	 */
}
