package store;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;


public class Find extends JDialog {
	public static String type[]= {"<不限制>","猫","狗","兔子","仓鼠","<自定义>"};
	public static String color[]= {"<不限制>","白色","黑色","红色","橙色","黄色","绿色","青色","蓝色","紫色","棕色","灰色","粉色","<自定义>"};
	private JPanel contentPane;
	private JTextField nametext;
	int num;
	public Find(Gui gui) {
		setBackground(Color.WHITE);
		setTitle("查找…");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(410, 285, 300, 310);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setModal(false);
		
		JLabel label1 = new JLabel("种类筛选");
		label1.setFont(new Font("思源黑体 CN", Font.PLAIN, 12));
		label1.setBounds(10, 30, 48, 18);
		contentPane.add(label1);
		
		JComboBox comboBox1 = new JComboBox(type);
		comboBox1.setFont(new Font("思源黑体 CN", Font.PLAIN, 12));
		comboBox1.setBackground(Color.WHITE);
		comboBox1.setBounds(68, 27, 216, 24);
		contentPane.add(comboBox1);
		
		JLabel label2 = new JLabel("颜色筛选");
		label2.setFont(new Font("思源黑体 CN", Font.PLAIN, 12));
		label2.setBounds(10, 80, 48, 18);
		contentPane.add(label2);
		
		JComboBox comboBox2 = new JComboBox(color);
		comboBox2.setFont(new Font("思源黑体 CN", Font.PLAIN, 12));
		comboBox2.setBackground(Color.WHITE);
		comboBox2.setBounds(68, 77, 216, 24);
		contentPane.add(comboBox2);
		
		JLabel label3 = new JLabel("年龄筛选");
		label3.setFont(new Font("思源黑体 CN", Font.PLAIN, 12));
		label3.setBounds(10, 130, 48, 18);
		contentPane.add(label3);
		
		JLabel from = new JLabel("自");
		from.setFont(new Font("思源黑体 CN", Font.PLAIN, 12));
		from.setBounds(75, 130, 12, 18);
		contentPane.add(from);
		
		JSpinner fromnum = new JSpinner();
		fromnum.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		fromnum.setFont(new Font("思源黑体 CN", Font.PLAIN, 12));
		fromnum.setBounds(97, 128, 40, 22);
		contentPane.add(fromnum);
		
		JLabel to = new JLabel("至");
		to.setFont(new Font("思源黑体 CN", Font.PLAIN, 12));
		to.setBounds(147, 130, 12, 18);
		contentPane.add(to);
		
		JSpinner tonum = new JSpinner();
		tonum.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		tonum.setFont(new Font("思源黑体 CN", Font.PLAIN, 12));
		tonum.setBounds(169, 128, 40, 22);
		contentPane.add(tonum);
		
		JCheckBox agecheckBox = new JCheckBox("\u4E0D\u9650\u5236");
		agecheckBox.setFont(new Font("思源黑体 CN", Font.PLAIN, 12));
		agecheckBox.setBackground(Color.WHITE);
		agecheckBox.setBounds(219, 127, 65, 24);
		contentPane.add(agecheckBox);
		
		JLabel label4 = new JLabel("姓名查找");
		label4.setFont(new Font("思源黑体 CN", Font.PLAIN, 12));
		label4.setBounds(10, 180, 48, 18);
		contentPane.add(label4);
		
		nametext = new JTextField();
		nametext.setBackground(Color.WHITE);
		nametext.setFont(new Font("思源黑体 CN", Font.PLAIN, 12));
		nametext.setBounds(68, 177, 141, 24);
		contentPane.add(nametext);
		nametext.setColumns(10);
		
		JCheckBox namecheckBox = new JCheckBox("\u4E0D\u9650\u5236");
		namecheckBox.setFont(new Font("思源黑体 CN", Font.PLAIN, 12));
		namecheckBox.setBackground(Color.WHITE);
		namecheckBox.setBounds(219, 177, 65, 24);
		contentPane.add(namecheckBox);
		
		JButton findbutton = new JButton("查找下一个...");
		findbutton.setBackground(Color.WHITE);
		findbutton.setFont(new Font("思源黑体 CN", Font.PLAIN, 12));
		findbutton.setBounds(97, 248, 112, 23);
		contentPane.add(findbutton);
		findbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.pettable.setSelectionBackground(Color.YELLOW);
				//gui.HighLight(gui.pettable, 0, false);
				if((int)fromnum.getValue() > (int)tonum.getValue())
					JOptionPane.showMessageDialog(null, "请检查查找年龄的大小关系!", "注意", JOptionPane.WARNING_MESSAGE);
				else {
					/*查找算法*/
					boolean btype = (comboBox1.getSelectedItem()=="<不限制>");          //是否不限制
					boolean bcolor = (comboBox2.getSelectedItem()=="<不限制>");
					boolean bage = (agecheckBox.isSelected());
					boolean bname = (namecheckBox.isSelected());
					
					/*for(int i = 0; i < gui.db.thelast; i++)
						if(
								(btype||gui.db.data[i][0].equals(comboBox1.getSelectedItem()))&               //类型匹配
								(bcolor||gui.db.data[i][2].equals(comboBox2.getSelectedItem()))&              //颜色匹配
								(bage||(
										(Integer.decode(gui.db.data[i][3])>=(int)fromnum.getValue())          //大于等于最小值
										&(Integer.decode(gui.db.data[i][3])<=(int)tonum.getValue())           //小于等于最大值
										))&
								(bname||gui.db.data[i][1].contentEquals(nametext.getText()))                  //姓名匹配
								) {
							/*高亮*/
							//gui.HighLight(gui.pettable, i, true);}*/
					if(gui.pettable.getSelectedRow()==-1)                     //判断是否选中行
						num = 0;
					else
						num = gui.pettable.getSelectedRow()+1;
					while(num <= gui.db.thelast) {                                //循环遍历
						if(
								(num != gui.db.thelast)&&                                                       //末尾判断
								(btype||gui.db.data[num][0].equals(comboBox1.getSelectedItem()))&&               //类型匹配
								(bcolor||gui.db.data[num][2].equals(comboBox2.getSelectedItem()))&&              //颜色匹配
								(bage||(
										(Integer.decode(gui.db.data[num][3])>=(int)fromnum.getValue())          //大于等于最小值
										&&(Integer.decode(gui.db.data[num][3])<=(int)tonum.getValue())           //小于等于最大值
										))&&
								(bname||gui.db.data[num][1].contentEquals(nametext.getText()))) {                 //姓名匹配
							gui.pettable.setRowSelectionInterval(num,num);
							num++;
							break;
							}else {
								num++;
							}
					}
					if(num > gui.db.thelast){                                                 //到达表格末尾
						JOptionPane.showMessageDialog(null, "已到达表格末尾,再次点击将从头开始寻找");
						gui.pettable.clearSelection();
					}
				}
			}
		});
		
		JButton finishbutton = new JButton("完成");
		finishbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//gui.HighLight(gui.pettable, 0, false);
				gui.pettable.setSelectionBackground(new Color(204, 204, 255));
				dispose();
			}
		});
		finishbutton.setBackground(Color.WHITE);
		finishbutton.setFont(new Font("思源黑体 CN", Font.PLAIN, 12));
		finishbutton.setBounds(219, 248, 65, 23);
		contentPane.add(finishbutton);
		
		agecheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(agecheckBox.isSelected()) {
					fromnum.setValue(0);
					fromnum.setEnabled(false);
					tonum.setValue(0);
					tonum.setEnabled(false);
				}
				else {
					fromnum.setEnabled(true);
					tonum.setEnabled(true);
				}
			}
		});
		
		namecheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(namecheckBox.isSelected()) {
					nametext.setText("");
					nametext.setEnabled(false);
				}
				else {
					nametext.setEnabled(true);
				}
			}
		});
		
		comboBox1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox1.getSelectedItem()=="<自定义>")
					comboBox1.setEditable(true);
				else
					comboBox1.setEditable(false);
			}
		});
		
		comboBox2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox2.getSelectedItem()=="<自定义>")
					comboBox2.setEditable(true);
				else
					comboBox2.setEditable(false);
			}
		});
	}
}
