package com.itwill.cafe.view_menu;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;

import com.itwill.cafe.controller.OrderHistoryDao;
import com.itwill.cafe.model.OrderHistory;

import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextArea;


public class OrderCappFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private OrderHistoryDao daohist = OrderHistoryDao.getInstance();
	private final ButtonGroup buttonGroupCapp = new ButtonGroup();
	private Component parent;
	
	private JPanel contentPane;
	private JLabel lblCapp;
	private JRadioButton rbCappSingle;
	private JRadioButton rbCappDouble;
	private JButton btnCappChoice;
	private JTextArea textCappOption;
	private JButton btnCappOrder;
	

	/**
	 * Launch the application.
	 */
	public static void showOrderCappFrame(Component parent) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderCappFrame frame = new OrderCappFrame(parent);
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
	
	public OrderCappFrame(Component parent) {
		this.parent = parent;
		
		initializeOrderCapp();
	}
	
	public void initializeOrderCapp() {
		setTitle("Coffee > 카푸치노");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		int x = 0;
		int y = 0;
		if (parent != null) {
			x = parent.getX();
			y = parent.getY();
		}
		setBounds(x+230, y+200, 300, 200);
		
		if (parent == null) {
			setLocationRelativeTo(null);
		}
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblCapp = new JLabel("카푸치노");
		lblCapp.setBounds(30, 20, 100, 20);
		lblCapp.setFont(new Font("D2Coding", Font.PLAIN, 15));
		contentPane.add(lblCapp);
		
		rbCappSingle = new JRadioButton("싱글");
		buttonGroupCapp.add(rbCappSingle);
		rbCappSingle.setBounds(30, 55, 60, 20);
		rbCappSingle.setFont(new Font("D2Coding", Font.PLAIN, 15));
		contentPane.add(rbCappSingle);
		
		rbCappDouble = new JRadioButton("더블");
		buttonGroupCapp.add(rbCappDouble);
		rbCappDouble.setBounds(100, 55, 60, 20);
		rbCappDouble.setFont(new Font("D2Coding", Font.PLAIN, 15));
		contentPane.add(rbCappDouble);
		
		btnCappChoice = new JButton("확인");
		btnCappChoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleCappClick();
			}
		});
		btnCappChoice.setBounds(180, 50, 70, 30);
		btnCappChoice.setFont(new Font("D2Coding", Font.PLAIN, 15));
		contentPane.add(btnCappChoice);
		
		textCappOption = new JTextArea();
		textCappOption.setBounds(70, 100, 60, 30);
		textCappOption.setFont(new Font("D2Coding", Font.PLAIN, 15));
		contentPane.add(textCappOption);
		
		btnCappOrder = new JButton("주문");
		btnCappOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cappOption = textCappOption.getText().trim();
				if (!cappOption.isEmpty()) {
					saveCappOrder();
					dispose();
				} else {
					JOptionPane.showMessageDialog(contentPane, "옵션을 선택하지 않았습니다.");
				}
			}
			
		});
		btnCappOrder.setBounds(180, 100, 70, 30);
		btnCappOrder.setFont(new Font("D2Coding", Font.PLAIN, 15));
		contentPane.add(btnCappOrder);
	}
	
	private void saveCappOrder() {
		String beverage = lblCapp.getText();
		String option = textCappOption.getText();
		
		OrderHistory hist = new OrderHistory(0, null, beverage, option);
		int result = daohist.save(hist);
		if (result == 1) {
			
			dispose();
		}
		
	}
	
	private void handleCappClick() {
		StringBuffer buffer = new StringBuffer();
		
		if (rbCappSingle.isSelected()) {
			buffer.append(rbCappSingle.getText());
		} else if (rbCappDouble.isSelected()) {
			buffer.append(rbCappDouble.getText());
		} else {
			JOptionPane.showMessageDialog(contentPane, "싱글/ 더블을 선택하지 않았습니다.");
		}
		
		if (rbCappSingle.isSelected() || rbCappDouble.isSelected()) {
		 textCappOption.setText(buffer.toString());	
		}
	}
}
