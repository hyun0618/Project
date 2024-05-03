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
	private final ButtonGroup buttonGroupShot = new ButtonGroup();
	
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
	private JLabel lblUsaOption;

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
		setBounds(x+230, y+50, 400, 280);
		
		if (parent == null) {
			setLocationRelativeTo(null);
		}
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblUsa = new JLabel("아메리카노");
		lblUsa.setFont(new Font("D2Coding", Font.PLAIN, 15));
		lblUsa.setBounds(30, 20, 100, 20);
		contentPane.add(lblUsa);
		
		lblUsaOption = new JLabel("옵션");
		lblUsaOption.setFont(new Font("D2Coding", Font.PLAIN, 13));
		lblUsaOption.setBounds(45, 60, 30, 15);
		contentPane.add(lblUsaOption);
				
		rbUsaHot = new JRadioButton("Hot");		
		buttonGroupCold.add(rbUsaHot);
		rbUsaHot.setFont(new Font("D2Coding", Font.PLAIN, 15));
		rbUsaHot.setBounds(90, 60, 100, 20);
		contentPane.add(rbUsaHot);
		
		rbUsaIce = new JRadioButton("Ice");		
		buttonGroupCold.add(rbUsaIce);
		rbUsaIce.setFont(new Font("D2Coding", Font.PLAIN, 15));
		rbUsaIce.setBounds(190, 60, 90, 20);
		contentPane.add(rbUsaIce);
		
		rbUsaRegular = new JRadioButton("Regular");	
		buttonGroupSize.add(rbUsaRegular);
		rbUsaRegular.setFont(new Font("D2Coding", Font.PLAIN, 15));
		rbUsaRegular.setBounds(90, 90, 100, 20);
		contentPane.add(rbUsaRegular);
		
		rbUsaLarge = new JRadioButton("Large");		
		buttonGroupSize.add(rbUsaLarge);
		rbUsaLarge.setFont(new Font("D2Coding", Font.PLAIN, 15));
		rbUsaLarge.setBounds(190, 90, 90, 20);
		contentPane.add(rbUsaLarge);
		
		rbUsaShotPlus = new JRadioButton("샷 추가");
		buttonGroupShot.add(rbUsaShotPlus);
		rbUsaShotPlus.setFont(new Font("D2Coding", Font.PLAIN, 15));
		rbUsaShotPlus.setBounds(90, 120, 100, 20);
		contentPane.add(rbUsaShotPlus);
		
		rbUsaShotMinus = new JRadioButton("샷 1/2");		
		buttonGroupShot.add(rbUsaShotMinus);
		rbUsaShotMinus.setFont(new Font("D2Coding", Font.PLAIN, 15));
		rbUsaShotMinus.setBounds(190, 120, 90, 20);
		contentPane.add(rbUsaShotMinus);
		
		btnUsaChoice = new JButton("선택");
		btnUsaChoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleUsaClick();
			}
		});
		btnUsaChoice.setFont(new Font("D2Coding", Font.PLAIN, 15));
		btnUsaChoice.setBounds(290, 110, 70, 30);
		contentPane.add(btnUsaChoice);
		
		textUsaOption = new JTextArea();
		textUsaOption.setLineWrap(true);
		textUsaOption.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textUsaOption.setBounds(100, 160, 170, 50);
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
		btnUsaOrder.setBounds(290, 180, 70, 30);
		contentPane.add(btnUsaOrder);
			
	}
	
	private void saveUsaOrder() {
			String beverage = lblUsa.getText();
			String option = textUsaOption.getText();
			
			OrderHistory hist = new OrderHistory(0, null, beverage, option);
			int result = daohist.save(hist);
			if (result == 1) {
				app.usaOrderSuccess();
//				dispose();
			}
				
	}
	
	private void handleUsaClick() {
		StringBuffer buffer = new StringBuffer();
	
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
		
		if (rbUsaShotPlus.isSelected()) {
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
