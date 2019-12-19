package store;

import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Exit extends JDialog {

	private JPanel contentPane;

	public Exit() {
		setTitle("\u9000\u51FA\u786E\u8BA4");
		setBackground(new Color(255, 255, 255));
		setAlwaysOnTop(true);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(810, 370, 300, 140);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.INFORMATION_DIALOG);
		
		JLabel label = new JLabel("\u662F\u5426\u5C06\u66F4\u6539\u4FDD\u5B58\u5230\u6570\u636E\u6587\u4EF6\u4E2D\uFF1F");
		label.setFont(new Font("思源黑体 CN", Font.PLAIN, 12));
		label.setIcon(new ImageIcon(Exit.class.getResource("/com/sun/javafx/scene/control/skin/caspian/dialog-information.png")));
		label.setBounds(10, 10, 264, 48);
		contentPane.add(label);
		
		JButton button_1 = new JButton("\u53D6\u6D88");            //取消
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JFileChooser fileChooser = new JFileChooser();
		FileFilter filter = new FileNameExtensionFilter("数据存储文件(.txt)","txt");
		fileChooser.setFileFilter(filter);
		
		JButton button_2 = new JButton("\u4FDD\u5B58");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {        //保存
				int i = fileChooser.showSaveDialog(getContentPane());
				File selectedFile = null;
				if (i == JFileChooser.APPROVE_OPTION) {
					
					selectedFile = fileChooser.getSelectedFile();                 //文件型变量
					String fname = selectedFile.getName();                        //获取文件名
					if(fname.indexOf(".txt")==-1){                                //自动扩展名
						selectedFile=new File(fileChooser.getCurrentDirectory(),fname+".txt");
					}
					BufferedWriter bw = null;
					try {
						bw = new BufferedWriter(new FileWriter(selectedFile,true));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					for(int p = 0; p < Gui.db.thelast; p++) {
						try {
							bw.write(Gui.db.data[p][0] + " " + Gui.db.data[p][1] + " " + Gui.db.data[p][2] + " " + Gui.db.data[p][3] + "\n");
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					try {
						bw.flush();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					try {
						bw.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					System.exit(0);
				}
				else {
					selectedFile = null;
				}
				
			}
		});
		button_2.setFont(new Font("思源黑体 CN", Font.PLAIN, 12));
		button_2.setBackground(new Color(204, 255, 204));
		button_2.setBounds(14, 64, 80, 27);
		contentPane.add(button_2);
		
		JButton button = new JButton("\u4E0D\u4FDD\u5B58");       //不保存
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		button.setBackground(new Color(255, 204, 204));
		button.setFont(new Font("思源黑体 CN", Font.PLAIN, 12));
		button.setBounds(104, 64, 80, 27);
		contentPane.add(button);
		button_1.setBackground(new Color(255, 255, 204));
		button_1.setFont(new Font("思源黑体 CN", Font.PLAIN, 12));
		button_1.setBounds(194, 64, 80, 27);
		contentPane.add(button_1);
	}
}
