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
	private JLabel lblCappPrice;
	private JLabel lblEspDouble;
	

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
		setBounds(x+80, y+100, 350, 300);
		
		if (parent == null) {
			setLocationRelativeTo(null);
		}
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblCapp = new JLabel("카푸치노");
		lblCapp.setBounds(30, 40, 70, 20);
		lblCapp.setFont(new Font("D2Coding", Font.PLAIN, 15));
		contentPane.add(lblCapp);
		
		rbCappSingle = new JRadioButton("싱글");
		buttonGroupCapp.add(rbCappSingle);
		rbCappSingle.setBounds(70, 80, 60, 20);
		rbCappSingle.setFont(new Font("D2Coding", Font.PLAIN, 13));
		contentPane.add(rbCappSingle);
		
		rbCappDouble = new JRadioButton("더블");
		buttonGroupCapp.add(rbCappDouble);
		rbCappDouble.setBounds(150, 80, 60, 20);
		rbCappDouble.setFont(new Font("D2Coding", Font.PLAIN, 13));
		contentPane.add(rbCappDouble);
		
		btnCappChoice = new JButton("선택");
		btnCappChoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleCappClick();
			}
		});
		btnCappChoice.setBounds(246, 122, 70, 30);
		btnCappChoice.setFont(new Font("D2Coding", Font.PLAIN, 13));
		contentPane.add(btnCappChoice);
		
		textCappOption = new JTextArea();
		textCappOption.setLineWrap(true);
		textCappOption.setBounds(92, 126, 124, 30);
		textCappOption.setFont(new Font("D2Coding", Font.PLAIN, 13));
		contentPane.add(textCappOption);
		
		btnCappOrder = new JButton("담기");
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
		btnCappOrder.setBounds(246, 173, 70, 30);
		btnCappOrder.setFont(new Font("D2Coding", Font.PLAIN, 13));
		contentPane.add(btnCappOrder);
		
		lblCappPrice = new JLabel(String.format("\\%,d", 4000));
		lblCappPrice.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		lblCappPrice.setBounds(166, 178, 52, 15);
		contentPane.add(lblCappPrice);
		
		lblEspDouble = new JLabel("(+\\500)");
		lblEspDouble.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		lblEspDouble.setBounds(210, 83, 50, 15);
		contentPane.add(lblEspDouble);
	}
	
	private void saveCappOrder() {
		String beverage = lblCapp.getText();
		String option = textCappOption.getText();
		String cappPriceText = lblCappPrice.getText().replaceAll("\\\\", "").replaceAll(",", "");
		int price = Integer.parseInt(cappPriceText);
		
		OrderHistory hist = new OrderHistory(0, beverage, option, price);
		int result = daohist.save(hist);
		if (result == 1) {		
			dispose();
		}
		
	}
	
	private void handleCappClick() {
		StringBuffer buffer = new StringBuffer();
		
		if (rbCappDouble.isSelected()) {
			lblCappPrice.setText(String.format("\\%,d", 4500));
		} else {
			lblCappPrice.setText(String.format("\\%,d", 4000));
		}
		
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
