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
		setBounds(x+100, y+100, 400, 300);
		
		if (parent == null) {
			setLocationRelativeTo(null);
		}
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblAde = new JLabel("자몽에이드");
		lblAde.setFont(new Font("D2Coding", Font.PLAIN, 15));
		lblAde.setBounds(30, 30, 100, 20);
		contentPane.add(lblAde);
		
		rbAdeRegular = new JRadioButton("Regular");
		buttonGroupAde.add(rbAdeRegular);
		rbAdeRegular.setFont(new Font("D2Coding", Font.PLAIN, 13));
		rbAdeRegular.setBounds(50, 70, 90, 20);
		contentPane.add(rbAdeRegular);
		
		rbAdeLarge = new JRadioButton("Large");
		buttonGroupAde.add(rbAdeLarge);
		rbAdeLarge.setFont(new Font("D2Coding", Font.PLAIN, 13));
		rbAdeLarge.setBounds(150, 70, 60, 20);
		contentPane.add(rbAdeLarge);
		
		btnAdeChoice = new JButton("선택");
		btnAdeChoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleAdeClick();
			}
		});
		btnAdeChoice.setFont(new Font("D2Coding", Font.PLAIN, 13));
		btnAdeChoice.setBounds(290, 120, 70, 30);
		contentPane.add(btnAdeChoice);
		
		textAdeOption = new JTextArea();
		textAdeOption.setEditable(false);
		textAdeOption.setFont(new Font("D2Coding", Font.PLAIN, 13));
		textAdeOption.setBounds(110, 110, 159, 30);
		contentPane.add(textAdeOption);
		
		btnAdeOrder = new JButton("담기");
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
		btnAdeOrder.setFont(new Font("D2Coding", Font.PLAIN, 13));
		btnAdeOrder.setBounds(290, 170, 70, 30);
		contentPane.add(btnAdeOrder);
		
		lblAdePrice = new JLabel(String.format("\\%,d", 5000));
		lblAdePrice.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		lblAdePrice.setBounds(215, 175, 52, 15);
		contentPane.add(lblAdePrice);
		
		lblAdeLarge = new JLabel("(+\\500)");
		lblAdeLarge.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		lblAdeLarge.setBounds(215, 72, 50, 15);
		contentPane.add(lblAdeLarge);
	
	}
	
	private void saveAdeOrder() {
		String beverage = lblAde.getText();
		String option = textAdeOption.getText();
		String adePriceText = lblAdePrice.getText().replaceAll("\\\\", "").replaceAll(",", "");
		int price = Integer.parseInt(adePriceText);
		
		OrderHistory hist = new OrderHistory(0, beverage, option, price);
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
