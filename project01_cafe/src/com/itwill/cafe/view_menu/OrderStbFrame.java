package com.itwill.cafe.view_menu;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.itwill.cafe.controller.OrderHistoryDao;
import com.itwill.cafe.model.OrderHistory;

import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OrderStbFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private OrderHistoryDao daohist = OrderHistoryDao.getInstance();	
	private final ButtonGroup buttonGroupAde = new ButtonGroup();
	private Component parent;
	
	private JPanel contentPane;
	private JLabel lblStb;
	private JRadioButton rbStbRegular;
	private JLabel lblStbExplain;
	private JTextArea textStbOption;
	private JButton btnStbOrder;
	private JLabel lblStbPrice;

	/**
	 * Launch the application.
	 */
	public static void showOrderStbFrame(Component parent) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderStbFrame frame = new OrderStbFrame(parent);
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
	public OrderStbFrame(Component parent) {
		this.parent = parent;
		
		initializeOrderStb();
	}
	
	public void initializeOrderStb() {
		setTitle("NonCoffee > 딸기스무디");
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
		
		lblStb = new JLabel("딸기스무디");
		lblStb.setFont(new Font("D2Coding", Font.PLAIN, 15));
		lblStb.setBounds(30, 30, 100, 20);
		contentPane.add(lblStb);
		
		rbStbRegular = new JRadioButton("Regular");
		rbStbRegular.setEnabled(false);
		rbStbRegular.setSelected(true);
		rbStbRegular.setFont(new Font("D2Coding", Font.PLAIN, 13));
		rbStbRegular.setBounds(50, 60, 81, 20);
		contentPane.add(rbStbRegular);
		
		textStbOption = new JTextArea();
		Color bgColor = textStbOption.getBackground();
	    textStbOption.setCaretColor(bgColor);
		textStbOption.setEditable(false);
		textStbOption.setFont(new Font("D2Coding", Font.PLAIN, 13));
		textStbOption.setBounds(115, 90, 100, 30);
		contentPane.add(textStbOption);
		textStbOption.setText(rbStbRegular.getText());
		
		lblStbExplain = new JLabel("(단일 사이즈)");
		lblStbExplain.setFont(new Font("D2Coding", Font.PLAIN, 13));
		lblStbExplain.setBounds(130, 62, 108, 15);
		contentPane.add(lblStbExplain);
		
		btnStbOrder = new JButton("담기");
		btnStbOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String beverage = lblStb.getText();
				String option = textStbOption.getText();		
				String stbPriceText = lblStbPrice.getText().replaceAll("\\\\", "").replaceAll(",", "");
				int price = Integer.parseInt(stbPriceText);
				
				OrderHistory hist = new OrderHistory(0, beverage, option, price);
				int result = daohist.save(hist);
				if (result == 1) {
					
					dispose();
				}
			}
		});
		btnStbOrder.setFont(new Font("D2Coding", Font.PLAIN, 13));
		btnStbOrder.setBounds(230, 130, 70, 30);
		contentPane.add(btnStbOrder);
		
		lblStbPrice = new JLabel(String.format("\\%,d", 5000));
		lblStbPrice.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		lblStbPrice.setBounds(170, 140, 60, 15);
		contentPane.add(lblStbPrice);
		
	
	}

}
