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
	
	private final ButtonGroup buttonGroupShot = new ButtonGroup();
	private final ButtonGroup buttonGroupSize = new ButtonGroup();
	private final ButtonGroup buttonGroupCold = new ButtonGroup();
	
	private Component parent;
	private JPanel contentPane;
	private JRadioButton rbShotPlus;
	private JRadioButton rbShotMinus;
	private JRadioButton rbHot;
	private JRadioButton rbIce;
	private JRadioButton rbRegular;
	private JRadioButton rbLarge;
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
		
	
		
		
		rbHot = new JRadioButton("HOT");		
		buttonGroupCold.add(rbHot);
		rbHot.setFont(new Font("D2Coding", Font.PLAIN, 15));
		rbHot.setBounds(90, 60, 100, 20);
		contentPane.add(rbHot);
		
		rbIce = new JRadioButton("ICE");		
		buttonGroupCold.add(rbIce);
		rbIce.setFont(new Font("D2Coding", Font.PLAIN, 15));
		rbIce.setBounds(190, 60, 90, 20);
		contentPane.add(rbIce);
		
		rbRegular = new JRadioButton("Regular");	
		buttonGroupSize.add(rbRegular);
		rbRegular.setFont(new Font("D2Coding", Font.PLAIN, 15));
		rbRegular.setBounds(90, 90, 100, 20);
		contentPane.add(rbRegular);
		
		rbLarge = new JRadioButton("Large");		
		buttonGroupSize.add(rbLarge);
		rbLarge.setFont(new Font("D2Coding", Font.PLAIN, 15));
		rbLarge.setBounds(190, 90, 90, 20);
		contentPane.add(rbLarge);
		
		rbShotPlus = new JRadioButton("샷 추가");
		buttonGroupShot.add(rbShotPlus);
		rbShotPlus.setFont(new Font("D2Coding", Font.PLAIN, 15));
		rbShotPlus.setBounds(90, 120, 100, 20);
		contentPane.add(rbShotPlus);
		
		rbShotMinus = new JRadioButton("샷 1/2");		
		buttonGroupShot.add(rbShotMinus);
		rbShotMinus.setFont(new Font("D2Coding", Font.PLAIN, 15));
		rbShotMinus.setBounds(190, 120, 90, 20);
		contentPane.add(rbShotMinus);
		
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
		
		lblUsaOption = new JLabel("옵션");
		lblUsaOption.setFont(new Font("D2Coding", Font.PLAIN, 13));
		lblUsaOption.setBounds(45, 60, 30, 15);
		contentPane.add(lblUsaOption);
		
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
	
		if (rbHot.isSelected()) {
			buffer.append(rbHot.getText());
		} else if (rbIce.isSelected()) {
			buffer.append(rbIce.getText());
		} else {
			JOptionPane.showMessageDialog(contentPane, "HOT/ ICE를 선택하지 않았습니다.");
		}
		
		if (rbRegular.isSelected()) {
			buffer.append("/ " + rbRegular.getText());
		} else if (rbLarge.isSelected()) {
			buffer.append("/ " + rbLarge.getText());
		} else {
			JOptionPane.showMessageDialog(contentPane, "사이즈를 선택하지 않았습니다.");
		}
		
		if (rbShotPlus.isSelected()) {
			buffer.append("/ " + rbShotPlus.getText());
		} else if (rbShotMinus.isSelected()) {
			buffer.append("/ " + rbShotMinus.getText());
		} else {
			buffer.append("");
		}
			
		if ((rbHot.isSelected() || rbIce.isSelected()) &&
				(rbRegular.isSelected() || rbLarge.isSelected())) {
			textUsaOption.setText(buffer.toString()); 
		} 
	}
}
