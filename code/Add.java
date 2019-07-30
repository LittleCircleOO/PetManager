package store;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerNumberModel;

public class Add extends JDialog {

	private JPanel contentPane;
	private JTextField nametext;

	public Add(Gui gui) {
		setTitle("\u6DFB\u52A0\u2026");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setBounds(780, 340, 360, 200);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel typelabel = new JLabel("\u79CD\u7C7B");
		typelabel.setFont(new Font("思源黑体 CN", Font.PLAIN, 12));
		typelabel.setBounds(203, 28, 24, 18);
		contentPane.add(typelabel);
		
		JComboBox typeBox = new JComboBox(Gui.type);
		typeBox.setFont(new Font("思源黑体 CN", Font.PLAIN, 12));
		typeBox.setBackground(new Color(255, 255, 255));
		typeBox.setBounds(237, 27, 92, 21);
		contentPane.add(typeBox);
		
		JLabel namelabel = new JLabel("\u540D\u5B57");
		namelabel.setFont(new Font("思源黑体 CN", Font.PLAIN, 12));
		namelabel.setBounds(25, 28, 24, 18);
		contentPane.add(namelabel);
		
		nametext = new JTextField();
		nametext.setFont(new Font("思源黑体 CN", Font.PLAIN, 12));
		nametext.setBackground(new Color(255, 255, 255));
		nametext.setBounds(59, 26, 92, 21);
		contentPane.add(nametext);
		nametext.setColumns(10);
		
		JLabel colorlabel = new JLabel("\u989C\u8272");
		colorlabel.setFont(new Font("思源黑体 CN", Font.PLAIN, 12));
		colorlabel.setBounds(25, 84, 24, 18);
		contentPane.add(colorlabel);
		
		JComboBox colorBox = new JComboBox(Gui.color);
		colorBox.setFont(new Font("思源黑体 CN", Font.PLAIN, 12));
		colorBox.setBackground(new Color(255, 255, 255));
		colorBox.setBounds(59, 83, 92, 21);
		contentPane.add(colorBox);
		
		JLabel agelabel = new JLabel("\u5E74\u9F84");
		agelabel.setFont(new Font("思源黑体 CN", Font.PLAIN, 12));
		agelabel.setBounds(203, 84, 24, 18);
		contentPane.add(agelabel);
		
		JSpinner agespinner = new JSpinner();
		agespinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		agespinner.setBackground(new Color(255, 255, 255));
		agespinner.setFont(new Font("思源黑体 CN", Font.PLAIN, 12));
		agespinner.setBounds(237, 83, 92, 22);
		contentPane.add(agespinner);
		
		JButton addbutton = new JButton("\u6DFB\u52A0");
		addbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Gui.db.data[Gui.db.thelast][0]=(String) typeBox.getSelectedItem();
				Gui.db.data[Gui.db.thelast][1]=nametext.getText();
				Gui.db.data[Gui.db.thelast][2]=(String) colorBox.getSelectedItem();
				Gui.db.data[Gui.db.thelast][3]=String.valueOf(agespinner.getValue());
				Gui.db.thelast++;
				JOptionPane.showMessageDialog(null, "添加成功!");
				//System.out.println(Gui.db.data[0][0] + " " + Gui.db.data[0][1] + " " + Gui.db.data[0][2] + " " + Gui.db.data[0][3]);
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
		addbutton.setBackground(new Color(204, 255, 204));
		addbutton.setFont(new Font("思源黑体 CN", Font.PLAIN, 12));
		addbutton.setBounds(212, 138, 61, 23);
		addbutton.setEnabled(false);
		contentPane.add(addbutton);
		
		JButton cancelbutton = new JButton("\u53D6\u6D88");
		cancelbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelbutton.setBackground(new Color(255, 204, 204));
		cancelbutton.setFont(new Font("思源黑体 CN", Font.PLAIN, 12));
		cancelbutton.setBounds(283, 138, 61, 23);
		contentPane.add(cancelbutton);

		JLabel tip = new JLabel("请完整填写宠物信息");
		tip.setFont(new Font("思源黑体 CN", Font.PLAIN, 12));
		tip.setForeground(Color.LIGHT_GRAY);
		tip.setBackground(Color.WHITE);
		tip.setBounds(25, 142, 126, 15);
		contentPane.add(tip);
		
		nametext.getDocument().addDocumentListener(new DocumentListener(){
			public void changedUpdate(DocumentEvent arg0) {
				if(colorBox.getSelectedItem()=="请选择..."||typeBox.getSelectedItem()=="请选择..."||nametext.getText().length()==0||colorBox.getSelectedItem().toString().length()==0||typeBox.getSelectedItem().toString().length()==0) {
					addbutton.setEnabled(false);
					tip.setText("请完整填写宠物信息");
				}
				else {
					addbutton.setEnabled(true);
					tip.setText("点击添加按钮添加宠物");
				}
			}
			public void insertUpdate(DocumentEvent arg0) {
				if(colorBox.getSelectedItem()=="请选择..."||typeBox.getSelectedItem()=="请选择..."||nametext.getText().length()==0||colorBox.getSelectedItem().toString().length()==0||typeBox.getSelectedItem().toString().length()==0) {
					addbutton.setEnabled(false);
					tip.setText("请完整填写宠物信息");
				}
				else {
					addbutton.setEnabled(true);
					tip.setText("点击添加按钮添加宠物");
				}
			}
			public void removeUpdate(DocumentEvent arg0) {
				if(colorBox.getSelectedItem()=="请选择..."||typeBox.getSelectedItem()=="请选择..."||nametext.getText().length()==0||colorBox.getSelectedItem().toString().length()==0||typeBox.getSelectedItem().toString().length()==0) {
					addbutton.setEnabled(false);
					tip.setText("请完整填写宠物信息");
				}
				else {
					addbutton.setEnabled(true);
					tip.setText("点击添加按钮添加宠物");
				}
			}
		});
		
		typeBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(typeBox.getSelectedItem()=="其他...")
					typeBox.setEditable(true);
				else
					typeBox.setEditable(false);
				if(colorBox.getSelectedItem()=="请选择..."||typeBox.getSelectedItem()=="请选择..."||nametext.getText().length()==0||colorBox.getSelectedItem().toString().length()==0||typeBox.getSelectedItem().toString().length()==0) {
					addbutton.setEnabled(false);
					tip.setText("请完整填写宠物信息");
				}
				else {
					addbutton.setEnabled(true);
					tip.setText("点击添加按钮添加宠物");
				}
			}
		});
		
		colorBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(colorBox.getSelectedItem()=="其他...")
					colorBox.setEditable(true);
				else
					colorBox.setEditable(false);
				if(colorBox.getSelectedItem()=="请选择..."||typeBox.getSelectedItem()=="请选择..."||nametext.getText().length()==0||colorBox.getSelectedItem().toString().length()==0||typeBox.getSelectedItem().toString().length()==0) {
					addbutton.setEnabled(false);
					tip.setText("请完整填写宠物信息");
				}
				else {
					addbutton.setEnabled(true);
					tip.setText("点击添加按钮添加宠物");
				}
			}
		});
		
		setResizable(false);
	}
}
