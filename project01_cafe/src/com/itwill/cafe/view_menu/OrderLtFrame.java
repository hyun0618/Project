package com.itwill.cafe.view_menu;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OrderLtFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private final ButtonGroup buttonGroupCold = new ButtonGroup();
	private final ButtonGroup buttonGroupSize = new ButtonGroup();
	private final ButtonGroup buttonGroupShot = new ButtonGroup();
	private final ButtonGroup buttonGroupMilk = new ButtonGroup();
	
	private Component parent;
	
	private JPanel contentPane;
	private JLabel lblLt;
	private JLabel lblLtOption;
	private JRadioButton rbLtHot;
	private JRadioButton rbLtIce;
	private JRadioButton rbLtRegular;
	private JRadioButton rbLtLarge;
	private JRadioButton rbLtShotPlus;
	private JRadioButton rbLtShotMinus;
	private JRadioButton rbLtLowFat;
	private JRadioButton rbLtSoymilk;
	private JButton btnLtChoice;
	private JTextArea textLtOption;
	private JButton btnLtOrder;

	/**
	 * Launch the application.
	 */
	public static void showOrderLtFrame() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderLtFrame frame = new OrderLtFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public OrderLtFrame() {
		initializeOrderLt();
	}
	
	public void initializeOrderLt() {
		setTitle("Coffee > 카페라떼");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		int x = 0;
		int y = 0;
		if (parent != null) {
			x = parent.getX();
			y = parent.getY();
		}
		setBounds(x+230, y+50, 450, 300);
		
		if (parent == null) {
			setLocationRelativeTo(null);
		}
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblLt = new JLabel("카페라떼");
		lblLt.setFont(new Font("D2Coding", Font.PLAIN, 15));
		lblLt.setBounds(30, 20, 100, 20);
		contentPane.add(lblLt);
		
		lblLtOption = new JLabel("옵션");
		lblLtOption.setFont(new Font("D2Coding", Font.PLAIN, 15));
		lblLtOption.setBounds(45, 60, 30, 15);
		contentPane.add(lblLtOption);
		
		rbLtHot = new JRadioButton("Hot");
		buttonGroupCold.add(rbLtHot);
		rbLtHot.setFont(new Font("D2Coding", Font.PLAIN, 15));
		rbLtHot.setBounds(90, 60, 100, 20);
		contentPane.add(rbLtHot);
		
		rbLtIce = new JRadioButton("Ice");
		buttonGroupCold.add(rbLtIce);
		rbLtIce.setFont(new Font("D2Coding", Font.PLAIN, 15));
		rbLtIce.setBounds(220, 60, 90, 20);
		contentPane.add(rbLtIce);
		
		rbLtRegular = new JRadioButton("Regular");
		buttonGroupSize.add(rbLtRegular);
		rbLtRegular.setFont(new Font("D2Coding", Font.PLAIN, 15));
		rbLtRegular.setBounds(90, 90, 100, 20);
		contentPane.add(rbLtRegular);
		
		rbLtLarge = new JRadioButton("Large");
		buttonGroupSize.add(rbLtLarge);
		rbLtLarge.setFont(new Font("D2Coding", Font.PLAIN, 15));
		rbLtLarge.setBounds(220, 90, 90, 20);
		contentPane.add(rbLtLarge);
		
		rbLtShotPlus = new JRadioButton("샷 추가");
		buttonGroupShot.add(rbLtShotPlus);
		rbLtShotPlus.setFont(new Font("D2Coding", Font.PLAIN, 15));
		rbLtShotPlus.setBounds(90, 120, 100, 20);
		contentPane.add(rbLtShotPlus);
		
		rbLtShotMinus = new JRadioButton("샷 1/2");
		buttonGroupShot.add(rbLtShotMinus);
		rbLtShotMinus.setFont(new Font("D2Coding", Font.PLAIN, 15));
		rbLtShotMinus.setBounds(220, 120, 90, 20);
		contentPane.add(rbLtShotMinus);
		
		rbLtLowFat = new JRadioButton("저지방 우유");
		buttonGroupMilk.add(rbLtLowFat);
		rbLtLowFat.setFont(new Font("D2Coding", Font.PLAIN, 15));
		rbLtLowFat.setBounds(90, 150, 110, 20);
		contentPane.add(rbLtLowFat);
		
		rbLtSoymilk = new JRadioButton("두유");
		buttonGroupMilk.add(rbLtSoymilk);
		rbLtSoymilk.setFont(new Font("D2Coding", Font.PLAIN, 15));
		rbLtSoymilk.setBounds(220, 150, 90, 20);
		contentPane.add(rbLtSoymilk);
		
		btnLtChoice = new JButton("선택");
		btnLtChoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleLtClick();
			}
		});
		btnLtChoice.setFont(new Font("D2Coding", Font.PLAIN, 15));
		btnLtChoice.setBounds(310, 145, 70, 30);
		contentPane.add(btnLtChoice);
		
		textLtOption = new JTextArea();
		textLtOption.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textLtOption.setBounds(100, 180, 170, 50);
		contentPane.add(textLtOption);
		
		btnLtOrder = new JButton("주문");
		btnLtOrder.setFont(new Font("D2Coding", Font.PLAIN, 15));
		btnLtOrder.setBounds(310, 200, 70, 30);
		contentPane.add(btnLtOrder);
			
	}
	
	private void handleLtClick() {
		StringBuffer buffer = new StringBuffer();
		
		if (rbLtHot.isSelected()) {
			buffer.append(rbLtHot.getText());
		} else if (rbLtIce.isSelected()) {
			buffer.append(rbLtIce.getText());
		} else {
			JOptionPane.showMessageDialog(contentPane, "HOT/ ICE를 선택하지 않았습니다.");
		}
	
		
		if ((rbLtHot.isSelected() || rbLtIce.isSelected()) && 
				(rbLtRegular.isSelected() || rbLtLarge.isSelected())) {
			textLtOption.setText(buffer.toString());
		}		
	}	
}
