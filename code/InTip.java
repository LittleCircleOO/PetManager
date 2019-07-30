package store;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class InTip extends JDialog {

	private JPanel contentPane;
	
	public InTip(InFileOpen ifo,File selectedFile,Gui gui) {
		setTitle("\u6570\u636E\u8986\u76D6\u8B66\u544A");
		setBackground(new Color(255, 255, 255));
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
		getRootPane().setWindowDecorationStyle(JRootPane.WARNING_DIALOG);
		
		JLabel lbln = new JLabel("<html><body>\u5BFC\u5165\u6570\u636E\u540E\uFF0C\u539F\u6709\u6570\u636E\u4F1A\u88AB\u6E05\u7A7A\uFF0C<br>\u662F\u5426\u7EE7\u7EED\uFF1F<body></html>");
		lbln.setFont(new Font("思源黑体 CN", Font.PLAIN, 12));
		lbln.setIcon(new ImageIcon(InTip.class.getResource("/com/sun/javafx/scene/control/skin/caspian/dialog-warning.png")));
		lbln.setBounds(10, 10, 264, 48);
		contentPane.add(lbln);
		
		JButton button = new JButton("\u662F");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*清空操作*/
				int p,q;
				for(p = 0; p < Gui.db.maxline; p++) {
					for(q = 0; q < 4; q++) {
						Gui.db.data[p][q]=null;
					}
				}
				/*导入操作*/
				BufferedReader br = null;
				try {
					br = new BufferedReader(new FileReader(selectedFile));//读取文件
				}catch(IOException e1){
					e1.printStackTrace();
				}
		        try {
		            String line;
		            String []sp = null;
		            int count=0;
		            while((line=br.readLine())!=null) {//按行读取
		                sp = line.split(" ");//按空格进行分割
		                for(int i=0;i<sp.length;i++){
		                    Gui.db.data[count][i] = sp[i];
		                }
		                count++;
		                Gui.db.thelast++;
		            }
		        } catch (IOException e2) {
		            e2.printStackTrace();
		        }
		        /*提示*/
		        JOptionPane.showMessageDialog(null, "导入成功!");
		        /*更新表格*/
		        gui.pettable.setModel(new DefaultTableModel(
						Gui.db.data,
						new String[] {
							"\u79CD\u7C7B", "\u540D\u5B57", "\u989C\u8272", "\u5E74\u9F84"
						}
					) {
						boolean[] columnEditables = new boolean[] {
							false, false, false, false
						};
						public boolean isCellEditable(int row, int column) {
							return columnEditables[column];
						}
		        });
		        /*退出导入界面*/
				ifo.dispose();
				dispose();
			}
		});
		button.setBackground(new Color(204, 255, 204));
		button.setFont(new Font("思源黑体 CN", Font.PLAIN, 12));
		button.setBounds(164, 64, 50, 27);
		contentPane.add(button);
		
		JButton button_1 = new JButton("\u5426");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ifo.dispose();
				dispose();
			}
		});
		button_1.setBackground(new Color(255, 204, 204));
		button_1.setFont(new Font("思源黑体 CN", Font.PLAIN, 12));
		button_1.setBounds(224, 64, 50, 27);
		contentPane.add(button_1);
	}
}
