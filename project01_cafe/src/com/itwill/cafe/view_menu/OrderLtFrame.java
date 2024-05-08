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
		setBounds(x, y+100, 400, 350);
		
		if (parent == null) {
			setLocationRelativeTo(null);
		}
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblLt = new JLabel("카페라떼");
		lblLt.setFont(new Font("D2Coding", Font.PLAIN, 15));
		lblLt.setBounds(30, 30, 100, 20);
		contentPane.add(lblLt);
		
		lblLtPrice = new JLabel("\\4,000");
		lblLtPrice.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		lblLtPrice.setBounds(220, 260, 52, 15);
		contentPane.add(lblLtPrice);
		
		rbLtHot = new JRadioButton("Hot");
		buttonGroupCold.add(rbLtHot);
		rbLtHot.setFont(new Font("D2Coding", Font.PLAIN, 13));
		rbLtHot.setBounds(50, 70, 100, 20);
		contentPane.add(rbLtHot);
	
		rbLtIce = new JRadioButton("Ice");
		buttonGroupCold.add(rbLtIce);
		rbLtIce.setFont(new Font("D2Coding", Font.PLAIN, 13));
		rbLtIce.setBounds(200, 70, 90, 20);
		contentPane.add(rbLtIce);
		
		rbLtRegular = new JRadioButton("Regular");
		buttonGroupSize.add(rbLtRegular);
		rbLtRegular.setFont(new Font("D2Coding", Font.PLAIN, 13));
		rbLtRegular.setBounds(50, 100, 100, 20);
		contentPane.add(rbLtRegular);
		
		rbLtLarge = new JRadioButton("Large");
		buttonGroupSize.add(rbLtLarge);
		rbLtLarge.setFont(new Font("D2Coding", Font.PLAIN, 13));
		rbLtLarge.setBounds(200, 100, 60, 20);
		contentPane.add(rbLtLarge);
		
		lblLtLarge = new JLabel("(+\\500)");
		lblLtLarge.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		lblLtLarge.setBounds(265, 102, 50, 15);
		contentPane.add(lblLtLarge);
		
		rbLtShotPlus = new JRadioButton("샷 추가");
		
		rbLtShotPlus.setFont(new Font("D2Coding", Font.PLAIN, 13));
		rbLtShotPlus.setBounds(50, 130, 75, 20);
		contentPane.add(rbLtShotPlus);
		
		lblLtShot = new JLabel("(+\\500)");
		lblLtShot.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		lblLtShot.setBounds(130, 132, 50, 15);
		contentPane.add(lblLtShot);
		
		rbLtShotMinus = new JRadioButton("샷 1/2");
		
		rbLtShotMinus.setFont(new Font("D2Coding", Font.PLAIN, 13));
		rbLtShotMinus.setBounds(200, 130, 90, 20);
		contentPane.add(rbLtShotMinus);
		
		rbLtLowFat = new JRadioButton("저지방 우유");
		
		rbLtLowFat.setFont(new Font("D2Coding", Font.PLAIN, 13));
		rbLtLowFat.setBounds(50, 160, 110, 20);
		contentPane.add(rbLtLowFat);
		
		rbLtSoymilk = new JRadioButton("두유");
		
		rbLtSoymilk.setFont(new Font("D2Coding", Font.PLAIN, 13));
		rbLtSoymilk.setBounds(200, 160, 55, 20);
		contentPane.add(rbLtSoymilk);
		
		lblLtSoy = new JLabel("(+\\500)");
		lblLtSoy.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		lblLtSoy.setBounds(255, 162, 50, 15);
		contentPane.add(lblLtSoy);
		
		btnLtChoice = new JButton("선택");
		btnLtChoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleLtClick();
			}
		});
		btnLtChoice.setFont(new Font("D2Coding", Font.PLAIN, 13));
		btnLtChoice.setBounds(295, 207, 70, 30);
		contentPane.add(btnLtChoice);
		
		textLtOption = new JTextArea();
		textLtOption.setLineWrap(true);
		textLtOption.setFont(new Font("D2Coding", Font.PLAIN, 13));
		textLtOption.setBounds(59, 207, 210, 30);
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
		btnLtOrder.setFont(new Font("D2Coding", Font.PLAIN, 13));
		btnLtOrder.setBounds(295, 250, 70, 30);
		contentPane.add(btnLtOrder);
		
	}
	
	private void saveLtOrder() {
		String beverage = lblLt.getText();
		String option = textLtOption.getText();
		String usaPriceText = lblLtPrice.getText().replaceAll("\\\\", "").replaceAll(",", "");
		int price = Integer.parseInt(usaPriceText);
		
		OrderHistory hist = new OrderHistory(0, beverage, option, price);
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
