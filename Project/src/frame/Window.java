package frame;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.EmptyStackException;

import javax.swing.JLabel;
import javax.swing.JCheckBox;

import encryption.EncryptionManage;

public class Window extends JFrame {
	private JTextField filePathText;
	private JTextField savePathText;
	private JPanel mainPanel;
	private String filePath;
	private String savePath;
	private JCheckBox[] box;

	public Window() {
		this.setTitle("多维加密");

		getContentPane().setBackground(new Color(119, 136, 153));
		getContentPane().setLayout(null);

		mainPanel = new JPanel();
		mainPanel.setBorder(new LineBorder(new Color(192, 192, 192), 3));
		mainPanel.setBackground(new Color(220, 220, 220));
		mainPanel.setBounds(10, 10, 414, 241);
		getContentPane().add(mainPanel);
		mainPanel.setLayout(null);

		filePathText = new JTextField();
		filePathText.setBounds(92, 32, 197, 21);
		mainPanel.add(filePathText);
		filePathText.setColumns(10);

		JButton browseButton1 = new JButton("\u6D4F\u89C8");
		browseButton1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int ret = chooser.showOpenDialog(null);
				if (ret == JFileChooser.APPROVE_OPTION) {
					File file = chooser.getSelectedFile();
					filePath = file.getAbsolutePath();
					filePathText.setText(filePath);
				}
			}
		});
		browseButton1.setBounds(311, 32, 61, 21);
		mainPanel.add(browseButton1);

		savePathText = new JTextField();
		savePathText.setColumns(10);
		savePathText.setBounds(92, 73, 197, 21);
		mainPanel.add(savePathText);

		JButton browseButton2 = new JButton("\u6D4F\u89C8");
		browseButton2.setBounds(311, 73, 61, 21);
		browseButton2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int ret = chooser.showOpenDialog(null);
				if (ret == JFileChooser.APPROVE_OPTION) {
					File file = chooser.getSelectedFile();
					savePath = file.getAbsolutePath();
					savePathText.setText(savePath);
				}
			}
		});
		mainPanel.add(browseButton2);

		JLabel label1 = new JLabel("\u786E\u8BA4\u6587\u4EF6");
		label1.setBounds(37, 35, 54, 15);
		mainPanel.add(label1);

		JLabel label2 = new JLabel("\u4FDD\u5B58\u6587\u4EF6");
		label2.setBounds(37, 76, 54, 15);
		mainPanel.add(label2);

		JButton encrypeButtom = new JButton("\u52A0\u5BC6");
		encrypeButtom.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					EncryptionManage.runEncryption(box, "keys.txt",
							filePathText.getText(), savePathText.getText());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "未知错误", "", JOptionPane.ERROR_MESSAGE);
					return;
				}
				JOptionPane.showMessageDialog(null, "操作成功", "", JOptionPane.DEFAULT_OPTION);
			}
		});
		encrypeButtom.setBounds(65, 166, 93, 23);
		mainPanel.add(encrypeButtom);

		JButton decrypeButton = new JButton("\u89E3\u5BC6");
		decrypeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					EncryptionManage.runDecryption(box,
							filePathText.getText(), savePathText.getText());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "文件路径无法匹配", "", JOptionPane.ERROR_MESSAGE);
					return;
				}
				catch (NullPointerException e1) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "密钥无法对应", "", JOptionPane.ERROR_MESSAGE);
					return;
				}
				catch (EmptyStackException e1) {
					JOptionPane.showMessageDialog(null, "密钥无法对应", "", JOptionPane.ERROR_MESSAGE);
					return;
				}
				catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "未知错误", "", JOptionPane.ERROR_MESSAGE);
					return;
				}
				JOptionPane.showMessageDialog(null, "操作成功", "", JOptionPane.DEFAULT_OPTION);
			}
		});
		decrypeButton.setBounds(242, 166, 93, 23);
		mainPanel.add(decrypeButton);

		box = new JCheckBox[4];

		box[0] = new JCheckBox("\u7F6E\u6362\u52A0\u5BC6");
		box[0].setBackground(new Color(220, 220, 220));
		box[0].setBounds(25, 117, 79, 23);
		mainPanel.add(box[0]);

		box[1] = new JCheckBox("\u4EE3\u6362\u52A0\u5BC6");
		box[1].setBounds(117, 117, 85, 23);
		box[1].setBackground(new Color(220, 220, 220));
		mainPanel.add(box[1]);

		box[2] = new JCheckBox("RC4\u7B97\u6CD5");
		box[2].setBounds(204, 117, 85, 23);
		box[2].setBackground(new Color(220, 220, 220));
		mainPanel.add(box[2]);

		box[3] = new JCheckBox("DES\u7B97\u6CD5");
		box[3].setBounds(298, 117, 74, 23);
		box[3].setBackground(new Color(220, 220, 220));
		mainPanel.add(box[3]);

		this.setResizable(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void run() {
		this.mainPanel.requestFocus();
		this.setSize(450, 300);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		Window window = new Window();
		window.run();
	}
}
