package code;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Gui extends JFrame {
	public static String type[]= {"请选择...","猫","狗","兔子","仓鼠","其他..."};
	public static String color[]= {"请选择...","白色","黑色","红色","橙色","黄色","绿色","青色","蓝色","紫色","棕色","灰色","粉色","其他..."};
	public static DataBase db = new DataBase();
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JTable pettable;
	public static Gui mainframe;
	public static InFileOpen inframe;
	public static OutFileOpen outframe;
	/*高亮算法*/
	/*public static void HighLight(JTable table, int line, boolean high) {          //table:表格 line:待高亮的行 high:是否开启高亮
		try{
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer(){
				public Component getTableCellRendererComponent(JTable table,Object value, boolean isSelected, boolean hasFocus,int row, int column){
					if(high) {
						if(row == line)
							setBackground(Color.yellow);
					}else {
						setBackground(Color.white);
					}
					return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				}
			};
			for(int i = 0; i < table.getColumnCount(); i++) {
				table.getColumn(table.getColumnName(i)).setCellRenderer(tcr);
			}
		}catch (Exception ex){
			ex.printStackTrace();
		}
	}*/
	
	
	public static void main(String[] args) {
		try {
			mainframe = new Gui();
			mainframe.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*设定表格高亮某行
	public static void HighLight(int rowIndex) {
		try {
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {
 
				public Component getTableCellRendererComponent(JTable table,
						Object value, boolean isSelected, boolean hasFocus,
						int row, int column) {
					if (row == rowIndex)
						setBackground(Color.YELLOW);
					return super.getTableCellRendererComponent(table, value,
							isSelected, hasFocus, row, column);
				}
			};
			int columnCount = mainframe.pettable.getColumnCount();
			for (int i = 0; i < columnCount; i++) {
				mainframe.pettable.getColumn(mainframe.pettable.getColumnName(i)).setCellRenderer(tcr);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}*/
	
	public Gui() {
		
		setBackground(new Color(255, 255, 255));
		setTitle("宠物商店实用程序");
		setBounds(710, 265, 500, 350);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				try {
					Exit ex = new Exit();
					ex.setVisible(true);
				} catch (Exception a) {
					a.printStackTrace();
				}
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 364, 291);
		contentPane.add(scrollPane);
		
		pettable = new JTable();
		pettable.setFont(new Font("思源黑体 CN", Font.PLAIN, 12));
		pettable.setBackground(Color.WHITE);
		pettable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(pettable);
		pettable.setSelectionBackground(new Color(204, 204, 255));
		pettable.setModel(new DefaultTableModel(
			db.data,
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
		
		JButton addbutton = new JButton("添加宠物");
		addbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {                         //调用添加窗口
				try {
					Add addframe = new Add(mainframe);
					addframe.setVisible(true);
				} catch (Exception i) {
					i.printStackTrace();
				}
			}
		});
		addbutton.setFont(new Font("思源黑体 CN", Font.PLAIN, 12));
		addbutton.setBackground(new Color(204, 255, 204));
		addbutton.setBounds(384, 13, 100, 23);
		contentPane.add(addbutton);
		
		JButton delbutton = new JButton("删除选中");
		delbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(pettable.getSelectedRow() == -1)                     //是否选中
					JOptionPane.showMessageDialog(null, "您没有选中任何一行!");
				else {
					if(pettable.getSelectedRow() >= db.thelast)                               //是否有内容
						JOptionPane.showMessageDialog(null, "选中行无信息!");
					else {
						/*删除操作*/
						try {
							DelTip dt = new DelTip(pettable.getSelectedRow(),mainframe);
							dt.setVisible(true);
						} catch (Exception i) {
							i.printStackTrace();
						}
					}
				}
			}
		});
		delbutton.setFont(new Font("思源黑体 CN", Font.PLAIN, 12));
		delbutton.setBackground(new Color(255, 204, 204));
		delbutton.setBounds(384, 46, 100, 23);
		contentPane.add(delbutton);
		
		JButton inbutton = new JButton("导入数据");
		inbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					inframe = new InFileOpen(mainframe);
					inframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		JButton editbutton = new JButton("修改选中");
		editbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(pettable.getSelectedRow() == -1)                     //是否选中
					JOptionPane.showMessageDialog(null, "您没有选中任何一行!");
				else {
					if(pettable.getSelectedRow() >= db.thelast)                               //是否有内容
						JOptionPane.showMessageDialog(null, "选中行无信息!");
					else {
						/*修改操作*/
						try {
							Edit ed = new Edit(mainframe, pettable.getSelectedRow());
							ed.setVisible(true);
						} catch (Exception i) {
							i.printStackTrace();
						}
					}
				}
			}
		});
		editbutton.setFont(new Font("思源黑体 CN", Font.PLAIN, 12));
		editbutton.setBackground(new Color(255, 204, 153));
		editbutton.setBounds(384, 79, 100, 23);
		contentPane.add(editbutton);
		inbutton.setFont(new Font("思源黑体 CN", Font.PLAIN, 12));
		inbutton.setBackground(new Color(204, 255, 255));
		inbutton.setBounds(384, 112, 100, 23);
		contentPane.add(inbutton);
		
		JButton outbutton = new JButton("导出数据");
		outbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					outframe = new OutFileOpen();
					outframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		outbutton.setFont(new Font("思源黑体 CN", Font.PLAIN, 12));
		outbutton.setBackground(new Color(255, 255, 204));
		outbutton.setBounds(384, 145, 100, 23);
		contentPane.add(outbutton);
		
		JButton exitbutton = new JButton("退出程序");
		exitbutton.setForeground(new Color(255, 255, 255));
		exitbutton.setFont(new Font("思源黑体 CN Medium", Font.PLAIN, 12));
		exitbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Exit frame = new Exit();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		JButton refreshbutton = new JButton("\u5237\u65B0\u5217\u8868");
		refreshbutton.setFont(new Font("思源黑体 CN", Font.PLAIN, 12));
		refreshbutton.setBackground(new Color(204, 204, 255));
		refreshbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pettable.setModel(new DefaultTableModel(
						db.data,
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
			}
		});
		refreshbutton.setBounds(384, 178, 100, 23);
		contentPane.add(refreshbutton);
		
		JButton findbutton = new JButton("查找信息");
		findbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Find frame = new Find(mainframe);
					frame.setVisible(true);
				} catch (Exception i) {
					i.printStackTrace();
				}
			}
		});
		
		findbutton.setFont(new Font("思源黑体 CN", Font.PLAIN, 12));
		findbutton.setBackground(new Color(204, 255, 153));
		findbutton.setBounds(384, 211, 100, 23);
		contentPane.add(findbutton);
		exitbutton.setBackground(new Color(0, 0, 0));
		exitbutton.setBounds(384, 278, 100, 23);
		contentPane.add(exitbutton);
	}
}
