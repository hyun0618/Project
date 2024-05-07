package com.itwill.cafe.view_menu;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.itwill.cafe.controller.OrderHistoryDao;
import com.itwill.cafe.model.OrderHistory;

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
	private OrderHistoryDao daohist = OrderHistoryDao.getInstance();

	private final ButtonGroup buttonGroupCold = new ButtonGroup();
	private final ButtonGroup buttonGroupSize = new ButtonGroup();
	
	private Component parent;
	
	private JPanel contentPane;
	private JLabel lblLt;
	private JLabel lblOption;
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
	private JLabel lblLtPrice;
	private JLabel lblLtLarge;
	private JLabel lblLtShot;
	private JLabel lblLtSoy;

	/**
	 * Launch the application.
	 */
	public static void showOrderLtFrame(Component parent) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderLtFrame frame = new OrderLtFrame(parent);
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
	public OrderLtFrame(Component parent) {
		this.parent = parent;
		
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
		setBounds(x+20, y+90, 500, 290);
		
		if (parent == null) {
			setLocationRelativeTo(null);
		}
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblLt = new JLabel("카페라떼");
		lblLt.setFont(new Font("D2Coding", Font.PLAIN, 17));
		lblLt.setBounds(12, 10, 100, 20);
		contentPane.add(lblLt);
		
		lblLtPrice = new JLabel("\\4,000");
		lblLtPrice.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		lblLtPrice.setBounds(102, 13, 52, 15);
		contentPane.add(lblLtPrice);
		
		lblOption = new JLabel("옵션");
		lblOption.setFont(new Font("D2Coding", Font.PLAIN, 15));
		lblOption.setBounds(27, 50, 42, 15);
		contentPane.add(lblOption);
		
		rbLtHot = new JRadioButton("Hot");
		buttonGroupCold.add(rbLtHot);
		rbLtHot.setFont(new Font("D2Coding", Font.PLAIN, 15));
		rbLtHot.setBounds(88, 50, 100, 20);
		contentPane.add(rbLtHot);
	
		rbLtIce = new JRadioButton("Ice");
		buttonGroupCold.add(rbLtIce);
		rbLtIce.setFont(new Font("D2Coding", Font.PLAIN, 15));
		rbLtIce.setBounds(243, 50, 90, 20);
		contentPane.add(rbLtIce);
		
		rbLtRegular = new JRadioButton("Regular");
		buttonGroupSize.add(rbLtRegular);
		rbLtRegular.setFont(new Font("D2Coding", Font.PLAIN, 15));
		rbLtRegular.setBounds(88, 80, 100, 20);
		contentPane.add(rbLtRegular);
		
		rbLtLarge = new JRadioButton("Large");
		buttonGroupSize.add(rbLtLarge);
		rbLtLarge.setFont(new Font("D2Coding", Font.PLAIN, 15));
		rbLtLarge.setBounds(243, 80, 70, 20);
		contentPane.add(rbLtLarge);
		
		lblLtLarge = new JLabel("(+\\500)");
		lblLtLarge.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		lblLtLarge.setBounds(312, 80, 50, 15);
		contentPane.add(lblLtLarge);
		
		rbLtShotPlus = new JRadioButton("샷 추가");
		
		rbLtShotPlus.setFont(new Font("D2Coding", Font.PLAIN, 15));
		rbLtShotPlus.setBounds(88, 110, 84, 20);
		contentPane.add(rbLtShotPlus);
		
		lblLtShot = new JLabel("(+\\500)");
		lblLtShot.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		lblLtShot.setBounds(172, 110, 50, 15);
		contentPane.add(lblLtShot);
		
		rbLtShotMinus = new JRadioButton("샷 1/2");
		
		rbLtShotMinus.setFont(new Font("D2Coding", Font.PLAIN, 15));
		rbLtShotMinus.setBounds(243, 110, 90, 20);
		contentPane.add(rbLtShotMinus);
		
		rbLtLowFat = new JRadioButton("저지방 우유");
		
		rbLtLowFat.setFont(new Font("D2Coding", Font.PLAIN, 15));
		rbLtLowFat.setBounds(88, 140, 110, 20);
		contentPane.add(rbLtLowFat);
		
		rbLtSoymilk = new JRadioButton("두유");
		
		rbLtSoymilk.setFont(new Font("D2Coding", Font.PLAIN, 15));
		rbLtSoymilk.setBounds(242, 140, 60, 20);
		contentPane.add(rbLtSoymilk);
		
		lblLtSoy = new JLabel("(+\\500)");
		lblLtSoy.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		lblLtSoy.setBounds(302, 140, 50, 15);
		contentPane.add(lblLtSoy);
		
		btnLtChoice = new JButton("선택");
		btnLtChoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleLtClick();
			}
		});
		btnLtChoice.setFont(new Font("D2Coding", Font.PLAIN, 15));
		btnLtChoice.setBounds(390, 130, 70, 30);
		contentPane.add(btnLtChoice);
		
		textLtOption = new JTextArea();
		textLtOption.setLineWrap(true);
		textLtOption.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textLtOption.setBounds(88, 185, 274, 50);
		contentPane.add(textLtOption);
		
		btnLtOrder = new JButton("주문");
		btnLtOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ltOption = textLtOption.getText().trim();
				if(!ltOption.isEmpty()) {
					saveLtOrder();
					dispose();
				} else {
					JOptionPane.showMessageDialog(contentPane, "옵션을 선택하지 않았습니다.");
				}
			}
		});
		btnLtOrder.setFont(new Font("D2Coding", Font.PLAIN, 15));
		btnLtOrder.setBounds(390, 205, 70, 30);
		contentPane.add(btnLtOrder);
		
	}
	
	private void saveLtOrder() {
		String beverage = lblLt.getText();
		String option = textLtOption.getText();
		String usaPriceText = lblLtPrice.getText().replaceAll("\\\\", "").replaceAll(",", "");
		int price = Integer.parseInt(usaPriceText);
		
		OrderHistory hist = new OrderHistory(0, null, beverage, option, price);
		int result = daohist.save(hist);
		if (result == 1) {
			dispose();
		}
			
	}
	

	
	private void handleLtClick() {
		StringBuffer buffer = new StringBuffer();
		
		if (rbLtLarge.isSelected() && rbLtShotPlus.isSelected() && rbLtSoymilk.isSelected()) {
			lblLtPrice.setText(String.format("\\%,d", 5500));
		} else if (!rbLtLarge.isSelected() && rbLtShotPlus.isSelected() && rbLtSoymilk.isSelected()) {
			lblLtPrice.setText(String.format("\\%,d", 5000));
		} else if (rbLtLarge.isSelected() && !rbLtShotPlus.isSelected() && rbLtSoymilk.isSelected()) {
			lblLtPrice.setText(String.format("\\%,d", 5000));
		} else if (rbLtLarge.isSelected() && rbLtShotPlus.isSelected() && !rbLtSoymilk.isSelected()) {
			lblLtPrice.setText(String.format("\\%,d", 5000));
		} else if (!rbLtLarge.isSelected() && !rbLtShotPlus.isSelected() && rbLtSoymilk.isSelected()) {
			lblLtPrice.setText(String.format("\\%,d", 4500));
		} else if (rbLtLarge.isSelected() && !rbLtShotPlus.isSelected() && !rbLtSoymilk.isSelected()) {
			lblLtPrice.setText(String.format("\\%,d", 4500));
		} else if (!rbLtLarge.isSelected() && rbLtShotPlus.isSelected() && !rbLtSoymilk.isSelected()) {
			lblLtPrice.setText(String.format("\\%,d", 4500));
		} else if (!rbLtLarge.isSelected() && !rbLtShotPlus.isSelected() && !rbLtSoymilk.isSelected()) {
			lblLtPrice.setText(String.format("\\%,d", 4000));
		}
		
		if (rbLtHot.isSelected()) {
			buffer.append(rbLtHot.getText());
		} else if (rbLtIce.isSelected()) {
			buffer.append(rbLtIce.getText());
		} else {
			JOptionPane.showMessageDialog(contentPane, "HOT/ ICE를 선택하지 않았습니다.");
		}
		
		if (rbLtRegular.isSelected()) {
			buffer.append("/ " + rbLtRegular.getText()); 
		} else if (rbLtLarge.isSelected()) {
			buffer.append("/ " + rbLtLarge.getText());
		} else {
			JOptionPane.showMessageDialog(contentPane, "사이즈를 선택하지 않았습니다.");
		}
		
		if (rbLtShotPlus.isSelected() && rbLtShotMinus.isSelected()) {
			JOptionPane.showMessageDialog(contentPane, "shot 옵션은 한 개만 선택 가능합니다.");
		} else if (rbLtShotPlus.isSelected()) {
			buffer.append("/ " + rbLtShotPlus.getText());
		} else if (rbLtShotMinus.isSelected()) {
			buffer.append("/ " + rbLtShotMinus.getText()); 
		} else {
			buffer.append("");
		}
		
		if (rbLtLowFat.isSelected() && rbLtSoymilk.isSelected()) {
			JOptionPane.showMessageDialog(contentPane, "우유 옵션은 한 개만 선택 가능합니다.");
		} else if (rbLtLowFat.isSelected()) {
			buffer.append("/ " + rbLtLowFat.getText());
		} else if (rbLtSoymilk.isSelected()) {
			buffer.append("/ " + rbLtSoymilk.getText()); 
		} else {
			buffer.append("");
		}
	
		
		if ((rbLtHot.isSelected() || rbLtIce.isSelected()) && 
				(rbLtRegular.isSelected() || rbLtLarge.isSelected())) {
			textLtOption.setText(buffer.toString());
		}		
	}	
}
