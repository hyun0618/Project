package com.itwill.cafe.view_menu;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.itwill.cafe.controller.OrderHistoryDao;
import com.itwill.cafe.model.OrderHistory;

import javax.swing.JRadioButton;
import java.awt.Font;


import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class OrderUsaFrame extends JFrame {
	
	public interface UsaOrderNotify {
		void usaOrderSuccess();
	}

	private static final long serialVersionUID = 1L;
	
	private OrderHistoryDao daohist = OrderHistoryDao.getInstance();
	private UsaOrderNotify app;
		
	private final ButtonGroup buttonGroupCold = new ButtonGroup();
	private final ButtonGroup buttonGroupSize = new ButtonGroup();
	
	
	private Component parent;
	private JPanel contentPane;
	private JRadioButton rbUsaShotPlus;
	private JRadioButton rbUsaShotMinus;
	private JRadioButton rbUsaHot;
	private JRadioButton rbUsaIce;
	private JRadioButton rbUsaRegular;
	private JRadioButton rbUsaLarge;
	private JButton btnUsaOrder;
	private JTextArea textUsaOption;
	private JButton btnUsaChoice;
	private JLabel lblUsa;
	private JLabel lblOption;
	private JTextField textUsaPrice;
	private JLabel lblUsaPrice;
	private JLabel lblShotPlus;
	private JLabel lblUsaLarge;

	/**
	 * Launch the application.
	 */
	public static void showOrderUsaFrame(Component parent, UsaOrderNotify app) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderUsaFrame frame = new OrderUsaFrame(parent, app);
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
	
	public OrderUsaFrame(Component parent, UsaOrderNotify app) {
		this.parent = parent;
		this.app = app;
		
		initializeOrderUsa();
	}
	
	public void initializeOrderUsa() {
		setTitle("Coffee > 아메리카노");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		int x = 0;
		int y = 0;
		if (parent != null) {
			x = parent.getX();
			y = parent.getY();
		}
		setBounds(x, y+310, 530, 280);
		
		if (parent == null) {
			setLocationRelativeTo(null);
		}
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblUsa = new JLabel("아메리카노");
		lblUsa.setFont(new Font("D2Coding", Font.PLAIN, 17));
		lblUsa.setBounds(12, 10, 90, 20);
		contentPane.add(lblUsa);
		
		lblOption = new JLabel("옵션");
		lblOption.setFont(new Font("D2Coding", Font.PLAIN, 15));
		lblOption.setBounds(65, 54, 37, 15);
		contentPane.add(lblOption);
				
		rbUsaHot = new JRadioButton("Hot");		
		buttonGroupCold.add(rbUsaHot);
		rbUsaHot.setFont(new Font("D2Coding", Font.PLAIN, 15));
		rbUsaHot.setBounds(121, 51, 100, 20);
		contentPane.add(rbUsaHot);
	
		rbUsaIce = new JRadioButton("Ice");		
		buttonGroupCold.add(rbUsaIce);
		rbUsaIce.setFont(new Font("D2Coding", Font.PLAIN, 15));
		rbUsaIce.setBounds(271, 51, 90, 20);
		contentPane.add(rbUsaIce);
		
		rbUsaRegular = new JRadioButton("Regular");	
		buttonGroupSize.add(rbUsaRegular);
		rbUsaRegular.setFont(new Font("D2Coding", Font.PLAIN, 15));
		rbUsaRegular.setBounds(121, 86, 100, 20);
		contentPane.add(rbUsaRegular);
		
		rbUsaLarge = new JRadioButton("Large");		
		buttonGroupSize.add(rbUsaLarge);
		rbUsaLarge.setFont(new Font("D2Coding", Font.PLAIN, 15));
		rbUsaLarge.setBounds(271, 86, 70, 20);
		contentPane.add(rbUsaLarge);
		
		lblUsaLarge = new JLabel("(+\\500)");
		lblUsaLarge.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		lblUsaLarge.setBounds(341, 86, 50, 15);
		contentPane.add(lblUsaLarge);
		
		
		rbUsaShotPlus = new JRadioButton("샷 추가");
		
		rbUsaShotPlus.setFont(new Font("D2Coding", Font.PLAIN, 15));
		rbUsaShotPlus.setBounds(121, 116, 80, 20);
		contentPane.add(rbUsaShotPlus);
		
		lblShotPlus = new JLabel("(+\\500)");
		lblShotPlus.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		lblShotPlus.setBounds(201, 116, 50, 15);
		contentPane.add(lblShotPlus);
		
		rbUsaShotMinus = new JRadioButton("샷 1/2");		
		
		rbUsaShotMinus.setFont(new Font("D2Coding", Font.PLAIN, 15));
		rbUsaShotMinus.setBounds(271, 116, 90, 20);
		contentPane.add(rbUsaShotMinus);
		
		btnUsaChoice = new JButton("선택");
		btnUsaChoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleUsaClick();
			}
		});
		btnUsaChoice.setFont(new Font("D2Coding", Font.PLAIN, 15));
		btnUsaChoice.setBounds(406, 111, 70, 30);
		contentPane.add(btnUsaChoice);
		
		textUsaOption = new JTextArea();
		textUsaOption.setLineWrap(true);
		textUsaOption.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textUsaOption.setBounds(121, 161, 250, 50);
		contentPane.add(textUsaOption);
		
		
		btnUsaOrder = new JButton("주문");
		btnUsaOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usaOption = textUsaOption.getText().trim();
				if (!usaOption.isEmpty()) {
					saveUsaOrder();
					dispose();
				} else {
					JOptionPane.showMessageDialog(contentPane, "옵션을 선택하지 않았습니다.");
				} 
			}
		});
		
		btnUsaOrder.setFont(new Font("D2Coding", Font.PLAIN, 15));
		btnUsaOrder.setBounds(406, 181, 70, 30);
		contentPane.add(btnUsaOrder);
		
		lblUsaPrice = new JLabel("\\3,000");
		lblUsaPrice.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		lblUsaPrice.setBounds(124, 11, 52, 15);
		contentPane.add(lblUsaPrice);
		
		
		
		
		
			
	}
	
	private void saveUsaOrder() {
			String beverage = lblUsa.getText();
			String option = textUsaOption.getText();
			String price = lblUsaPrice.getText();
			
			OrderHistory hist = new OrderHistory(0, null, beverage, option, price);
			int result = daohist.save(hist);
			if (result == 1) {
				app.usaOrderSuccess();
//				dispose();
			}
				
	}
	
	private void handleUsaClick() {
		StringBuffer buffer = new StringBuffer();
	
		if (rbUsaLarge.isSelected() && rbUsaShotPlus.isSelected()) {
			lblUsaPrice.setText("\\4,000");
		} else if (!rbUsaLarge.isSelected() && rbUsaShotPlus.isSelected()) {
			lblUsaPrice.setText("\\3,500");
		} else if (rbUsaLarge.isSelected() && !rbUsaShotPlus.isSelected()) {
			lblUsaPrice.setText("\\3,500");
		} else if (!(rbUsaLarge.isSelected() && rbUsaShotPlus.isSelected())) {
			lblUsaPrice.setText("\\3,000");
		}
		
		if (rbUsaHot.isSelected()) {
			buffer.append(rbUsaHot.getText());
		} else if (rbUsaIce.isSelected()) {
			buffer.append(rbUsaIce.getText());
		} else {
			JOptionPane.showMessageDialog(contentPane, "HOT/ ICE를 선택하지 않았습니다.");
		}
		
		if (rbUsaRegular.isSelected()) {
			buffer.append("/ " + rbUsaRegular.getText());
		} else if (rbUsaLarge.isSelected()) {
			buffer.append("/ " + rbUsaLarge.getText());
		} else {
			JOptionPane.showMessageDialog(contentPane, "사이즈를 선택하지 않았습니다.");
		}
		
		if (rbUsaShotPlus.isSelected() && rbUsaShotMinus.isSelected()) {
			JOptionPane.showMessageDialog(contentPane, "shot 옵션은 한 개만 선택 가능합니다.");
		} else if (rbUsaShotPlus.isSelected()) {
			buffer.append("/ " + rbUsaShotPlus.getText());			
		} else if (rbUsaShotMinus.isSelected()) {
			buffer.append("/ " + rbUsaShotMinus.getText());
		} else {
			buffer.append("");
		}
			
		if ((rbUsaHot.isSelected() || rbUsaIce.isSelected()) &&
				(rbUsaRegular.isSelected() || rbUsaLarge.isSelected())) {
			textUsaOption.setText(buffer.toString()); 
		} 
	}
}
