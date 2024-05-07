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

public class OrderAdeFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private OrderHistoryDao daohist = OrderHistoryDao.getInstance();	
	private final ButtonGroup buttonGroupAde = new ButtonGroup();
	private Component parent;
	
	private JPanel contentPane;
	private JLabel lblAde;
	private JRadioButton rbAdeRegular;
	private JLabel lblAdeOption;
	private JRadioButton rbAdeLarge;
	private JButton btnAdeChoice;
	private JTextArea textAdeOption;
	private JButton btnAdeOrder;
	private JLabel lblAdePrice;
	private JLabel lblAdeLarge;

	/**
	 * Launch the application.
	 */
	public static void showOrderAdeFrame(Component parent) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderAdeFrame frame = new OrderAdeFrame(parent);
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
	public OrderAdeFrame(Component parent) {
		this.parent = parent;
		
		initializeOrderAde();
		
	}
	
	public void initializeOrderAde() {
		setTitle("NonCoffee > 자몽에이드");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		int x = 0;
		int y = 0;
		if (parent != null) {
			x = parent.getX();
			y = parent.getY();
		}
		setBounds(x+250, y+90, 400, 250);
		
		if (parent == null) {
			setLocationRelativeTo(null);
		}
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblAde = new JLabel("자몽에이드");
		lblAde.setFont(new Font("D2Coding", Font.PLAIN, 17));
		lblAde.setBounds(15, 20, 100, 20);
		contentPane.add(lblAde);
		
		lblAdeOption = new JLabel("옵션");
		lblAdeOption.setFont(new Font("D2Coding", Font.PLAIN, 15));
		lblAdeOption.setBounds(62, 58, 37, 15);
		contentPane.add(lblAdeOption);
		
		rbAdeRegular = new JRadioButton("Regular");
		buttonGroupAde.add(rbAdeRegular);
		rbAdeRegular.setFont(new Font("D2Coding", Font.PLAIN, 15));
		rbAdeRegular.setBounds(110, 55, 90, 20);
		contentPane.add(rbAdeRegular);
		
		rbAdeLarge = new JRadioButton("Large");
		buttonGroupAde.add(rbAdeLarge);
		rbAdeLarge.setFont(new Font("D2Coding", Font.PLAIN, 15));
		rbAdeLarge.setBounds(204, 55, 70, 20);
		contentPane.add(rbAdeLarge);
		
		btnAdeChoice = new JButton("선택");
		btnAdeChoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleAdeClick();
			}
		});
		btnAdeChoice.setFont(new Font("D2Coding", Font.PLAIN, 15));
		btnAdeChoice.setBounds(292, 50, 70, 30);
		contentPane.add(btnAdeChoice);
		
		textAdeOption = new JTextArea();
		textAdeOption.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textAdeOption.setBounds(110, 124, 159, 40);
		contentPane.add(textAdeOption);
		
		btnAdeOrder = new JButton("주문");
		btnAdeOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String adeOption = textAdeOption.getText().trim();
				if (!adeOption.isEmpty()) {
					saveAdeOrder();
					dispose();
				} else {
					JOptionPane.showMessageDialog(contentPane, "옵션을 선택하지 않았습니다.");
				}
			}
		});
		btnAdeOrder.setFont(new Font("D2Coding", Font.PLAIN, 15));
		btnAdeOrder.setBounds(292, 134, 70, 30);
		contentPane.add(btnAdeOrder);
		
		lblAdePrice = new JLabel(String.format("\\%,d", 5000));
		lblAdePrice.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		lblAdePrice.setBounds(120, 20, 52, 15);
		contentPane.add(lblAdePrice);
		
		lblAdeLarge = new JLabel("(+\\500)");
		lblAdeLarge.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		lblAdeLarge.setBounds(214, 81, 50, 15);
		contentPane.add(lblAdeLarge);
	
	}
	
	private void saveAdeOrder() {
		String beverage = lblAde.getText();
		String option = textAdeOption.getText();
		String adePriceText = lblAdePrice.getText().replaceAll("\\\\", "").replaceAll(",", "");
		int price = Integer.parseInt(adePriceText);
		
		OrderHistory hist = new OrderHistory(0, null, beverage, option, price);
		int result = daohist.save(hist);
		if (result == 1) {
			
			dispose();
		}
	}
	
	private void handleAdeClick() {
		StringBuffer buffer = new StringBuffer();
		
		if (rbAdeLarge.isSelected()) {
			lblAdePrice.setText(String.format("\\%,d", 5500));
		} else {
			lblAdePrice.setText(String.format("\\%,d", 5000));
		}
		
		if (rbAdeRegular.isSelected()) {
			buffer.append(rbAdeRegular.getText());
		} else if (rbAdeLarge.isSelected()) {
			buffer.append(rbAdeLarge.getText());
		} else {
			JOptionPane.showMessageDialog(contentPane, "사이즈를 선택하지 않았습니다.");
		}
		
		if (rbAdeRegular.isSelected() || rbAdeLarge.isSelected()) {
			textAdeOption.setText(buffer.toString());	
		}	
	}
}
