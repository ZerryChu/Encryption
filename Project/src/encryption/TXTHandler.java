package encryption;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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

	public static void write(String savePath, String data) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(savePath));
		writer.write(data);
		writer.flush(); // ��������, ��֤����д��
		writer.close();
	}

	/*
	 * test public static void main(String[] args) throws IOException {
	 * TXTHandler txtHandler = new TXTHandler(); txtHandler.read("test.txt");
	 * txtHandler.write("test2.txt"); }
	 */
}
