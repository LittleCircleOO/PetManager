package code;

import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

public class DelTip extends JDialog {

	private JPanel contentPane;
	private JTable table;

	public DelTip(int selectedone,Gui gui) {                      //selectedone代表选中行的序号,由主界面传参代入;gui中含有静态(static)数据存储表db
		setTitle("\u5220\u9664\u786E\u8BA4");
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
		getRootPane().setWindowDecorationStyle(JRootPane.INFORMATION_DIALOG);
		
		JLabel label = new JLabel("\u786E\u8BA4\u5220\u9664\uFF1F");
		label.setFont(new Font("思源黑体 CN", Font.PLAIN, 12));
		label.setIcon(new ImageIcon(DelTip.class.getResource("/com/sun/javafx/scene/control/skin/caspian/dialog-confirm.png")));
		label.setBounds(10, 10, 264, 48);
		contentPane.add(label);
		
		JButton ybutton = new JButton("\u662F");
		ybutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i=selectedone;i<Gui.db.thelast;i++) {
					Gui.db.data[i][0]=Gui.db.data[i+1][0];
					Gui.db.data[i][1]=Gui.db.data[i+1][1];
					Gui.db.data[i][2]=Gui.db.data[i+1][2];
					Gui.db.data[i][3]=Gui.db.data[i+1][3];
				}
				Gui.db.thelast--;
				JOptionPane.showMessageDialog(null, "删除成功!");
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
				dispose();
			}
		});
		ybutton.setBackground(new Color(204, 255, 204));
		ybutton.setFont(new Font("思源黑体 CN", Font.PLAIN, 12));
		ybutton.setBounds(47, 64, 50, 27);
		contentPane.add(ybutton);
		
		JButton nbutton = new JButton("\u5426");
		nbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		nbutton.setBackground(new Color(255, 204, 204));
		nbutton.setFont(new Font("思源黑体 CN", Font.PLAIN, 12));
		nbutton.setBounds(107, 64, 50, 27);
		contentPane.add(nbutton);
		
		JButton morebutton = new JButton("详细信息▼");
		morebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(morebutton.getText()=="详细信息▼") {
					morebutton.setText("收起▲");
					setSize(300, 210);	
				}else {
					morebutton.setText("详细信息▼");
					setSize(300, 140);
				}
			}
		});
		morebutton.setFont(new Font("思源黑体 CN", Font.PLAIN, 12));
		morebutton.setBackground(new Color(255, 255, 255));
		morebutton.setBounds(167, 64, 106, 27);
		contentPane.add(morebutton);
		
		JLabel label_1 = new JLabel("\u5220\u9664\u4FE1\u606F\u8BE6\u60C5");
		label_1.setFont(new Font("思源黑体 CN", Font.PLAIN, 12));
		label_1.setBounds(10, 131, 72, 15);
		contentPane.add(label_1);
		
		table = new JTable();
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{Gui.db.data[selectedone][0], Gui.db.data[selectedone][1], Gui.db.data[selectedone][2], Gui.db.data[selectedone][3]},
			},
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
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(55);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(135);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(55);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(55);
		table.setBounds(10, 150, 274, 15);
		contentPane.add(table);
	}
}
