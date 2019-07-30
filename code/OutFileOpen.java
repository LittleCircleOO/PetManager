package code;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;

public class OutFileOpen extends JDialog {

	private JPanel contentPane;
	private JTextField textField;
	File selectedFile = null;
	
	public OutFileOpen() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(OutFileOpen.class.getResource("/com/sun/javafx/scene/control/skin/caspian/dialog-fewer-details.png")));
		setTitle("\u5BFC\u51FA\u2026");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setBounds(740, 365, 440, 150);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		
		JLabel label = new JLabel("\u4FDD\u5B58\u8DEF\u5F84");
		label.setFont(new Font("思源黑体 CN", Font.PLAIN, 12));
		label.setBackground(new Color(255, 255, 255));
		label.setBounds(20, 30, 48, 15);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setForeground(Color.GRAY);
		textField.setText("\u70B9\u51FB\u6D4F\u89C8\u6309\u94AE\uFF0C\u9009\u62E9\u76EE\u5F55");
		textField.setFont(new Font("思源黑体 CN", Font.PLAIN, 12));
		textField.setEditable(false);
		textField.setBounds(78, 27, 250, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton applybutton = new JButton("\u5BFC\u51FA");
		applybutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*保存操作*/
				BufferedWriter bw = null;
				try {
					bw = new BufferedWriter(new FileWriter(selectedFile,true));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				for(int i = 0; i < Gui.db.thelast; i++) {
					try {
						bw.write(Gui.db.data[i][0] + " " + Gui.db.data[i][1] + " " + Gui.db.data[i][2] + " " + Gui.db.data[i][3] + "\n");
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
				JOptionPane.showMessageDialog(null, "保存成功!");
				dispose();
			}
		});
		applybutton.setFont(new Font("思源黑体 CN", Font.PLAIN, 12));
		applybutton.setEnabled(false);
		applybutton.setBackground(new Color(204, 255, 204));
		applybutton.setBounds(273, 82, 65, 23);
		contentPane.add(applybutton);
		
		JButton cancelbutton = new JButton("\u53D6\u6D88");
		cancelbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelbutton.setFont(new Font("思源黑体 CN", Font.PLAIN, 12));
		cancelbutton.setBackground(new Color(255, 204, 204));
		cancelbutton.setBounds(348, 82, 65, 23);
		contentPane.add(cancelbutton);
		
		JFileChooser fileChooser = new JFileChooser();
		FileFilter filter = new FileNameExtensionFilter("数据存储文件(.txt)","txt");
		fileChooser.setFileFilter(filter);
		
		JButton filebutton = new JButton("\u6D4F\u89C8...");
		filebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*浏览操作*/
				int i = fileChooser.showSaveDialog(getContentPane());
				if (i == JFileChooser.APPROVE_OPTION) {
					selectedFile = fileChooser.getSelectedFile();                 //文件型变量
					String fname = selectedFile.getName();                        //获取文件名
					if(fname.indexOf(".txt")==-1){                                //自动扩展名
						textField.setText(selectedFile.getPath() + ".txt");       //文件路径
						selectedFile=new File(fileChooser.getCurrentDirectory(),fname+".txt");
					}else {
						textField.setText(selectedFile.getPath());                //文件路径
					}
					applybutton.setEnabled(true);
				}
				else {
					selectedFile = null;
					textField.setText("点击浏览按钮，选择目录");
					applybutton.setEnabled(false);
				}
			}
		});
		filebutton.setFont(new Font("思源黑体 CN", Font.PLAIN, 12));
		filebutton.setBackground(new Color(255, 255, 255));
		filebutton.setBounds(338, 26, 75, 23);
		contentPane.add(filebutton);
		
	}
}
