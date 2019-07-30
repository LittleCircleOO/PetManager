package code;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;

public class InFileOpen extends JDialog {

	private JPanel contentPane;
	private JTextField textField;
	File selectedFile = null;
	
	public InFileOpen(Gui gui) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(InFileOpen.class.getResource("/com/sun/javafx/scene/control/skin/caspian/dialog-more-details.png")));
		setTitle("\u5BFC\u5165\u2026");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setBounds(740, 365, 440, 150);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		
		JLabel label = new JLabel("\u6587\u4EF6\u8DEF\u5F84");
		label.setFont(new Font("思源黑体 CN", Font.PLAIN, 12));
		label.setBackground(new Color(255, 255, 255));
		label.setBounds(20, 30, 48, 15);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setForeground(Color.GRAY);
		textField.setText("\u70B9\u51FB\u6D4F\u89C8\u6309\u94AE\uFF0C\u9009\u62E9\u6587\u4EF6");
		textField.setFont(new Font("思源黑体 CN", Font.PLAIN, 12));
		textField.setEditable(false);
		textField.setBounds(78, 27, 250, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton applybutton = new JButton("\u5BFC\u5165");
		applybutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					InTip frame = new InTip(Gui.inframe,selectedFile,gui);
					frame.setVisible(true);
				} catch (Exception i) {
					i.printStackTrace();
				}
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
				int i = fileChooser.showOpenDialog(getContentPane());
				if (i == JFileChooser.APPROVE_OPTION) {
					selectedFile = fileChooser.getSelectedFile();
					textField.setText(selectedFile.getPath());
					applybutton.setEnabled(true);
				}
				else {
					selectedFile = null;
					textField.setText("点击浏览按钮，选择文件");
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
