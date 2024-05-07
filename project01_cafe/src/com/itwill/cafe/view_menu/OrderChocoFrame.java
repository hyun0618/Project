package com.itwill.cafe.view_menu;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.itwill.cafe.controller.OrderHistoryDao;
import com.itwill.cafe.model.OrderHistory;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OrderChocoFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private OrderHistoryDao daohist = OrderHistoryDao.getInstance();	
	private final ButtonGroup buttonChocoSize = new ButtonGroup();
	private Component parent;
	
	private JPanel contentPane;
	private JLabel lblOption;
	private JLabel lblChocoPrice;
	private JLabel lblChoco;
	private JLabel lblChocoLarge;
	private JButton btnChocoOrder;
	private JRadioButton rbChocoCreamNo;
	private JRadioButton rbChocoCream;
	private JRadioButton rbChocoRegular;
	private JRadioButton rbChocoLarge;
	private JButton btnChocoChoice;
	private JTextArea textChocoOption;

	/**
	 * Launch the application.
	 */
	public static void showOrderChocoFrame(Component parent) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderChocoFrame frame = new OrderChocoFrame(parent);
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
	public OrderChocoFrame(Component parent) {
		this.parent = parent;
		
		initializeOrderChoco();
		
	}
	
	public void initializeOrderChoco() {
		setTitle("NonCoffee > 초코프라푸치노");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		int x = 0;
		int y = 0;
		if (parent != null) {
			x = parent.getX();
			y = parent.getY();
		}
		setBounds(x+250, y+120, 450, 260);
		
		if (parent == null) {
			setLocationRelativeTo(null);
		}
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblChoco = new JLabel("초코프라푸치노");
		lblChoco.setHorizontalAlignment(SwingConstants.CENTER);
		lblChoco.setFont(new Font("D2Coding", Font.PLAIN, 17));
		lblChoco.setBounds(15, 20, 160, 20);
		contentPane.add(lblChoco);
		
		lblChocoPrice = new JLabel(String.format("\\%,d", 5500));
		lblChocoPrice.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		lblChocoPrice.setBounds(184, 21, 50, 15);
		contentPane.add(lblChocoPrice);
		
		lblOption = new JLabel("옵션");
		lblOption.setFont(new Font("D2Coding", Font.PLAIN, 15));
		lblOption.setBounds(50, 70, 37, 15);
		contentPane.add(lblOption);
		
		rbChocoRegular = new JRadioButton("Regular");
		buttonChocoSize.add(rbChocoRegular);
		rbChocoRegular.setFont(new Font("D2Coding", Font.PLAIN, 15));
		rbChocoRegular.setBounds(100, 75, 90, 20);
		contentPane.add(rbChocoRegular);
		
		rbChocoLarge = new JRadioButton("Large");
		buttonChocoSize.add(rbChocoLarge);
		rbChocoLarge.setFont(new Font("D2Coding", Font.PLAIN, 15));
		rbChocoLarge.setBounds(200, 75, 70, 20);
		contentPane.add(rbChocoLarge);
		
		lblChocoLarge = new JLabel("(+\\500)");
		lblChocoLarge.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		lblChocoLarge.setBounds(277, 75, 50, 15);
		contentPane.add(lblChocoLarge);
		
		btnChocoChoice = new JButton("선택");
		btnChocoChoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleChocoClick();
			}
		});
		btnChocoChoice.setFont(new Font("D2Coding", Font.PLAIN, 15));
		btnChocoChoice.setBounds(350, 110, 70, 30);
		contentPane.add(btnChocoChoice);
		
		textChocoOption = new JTextArea();
		textChocoOption.setLineWrap(true);
		textChocoOption.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textChocoOption.setBounds(100, 170, 227, 30);
		contentPane.add(textChocoOption);
		
		btnChocoOrder = new JButton("주문");
		btnChocoOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usaOption = textChocoOption.getText().trim();
				if (!usaOption.isEmpty()) {
					saveChocoOrder();
					dispose();
				} else {
					JOptionPane.showMessageDialog(contentPane, "옵션을 선택하지 않았습니다.");
				} 
			}
		});
		btnChocoOrder.setFont(new Font("D2Coding", Font.PLAIN, 15));
		btnChocoOrder.setBounds(350, 170, 70, 30);
		contentPane.add(btnChocoOrder);
		
		rbChocoCreamNo = new JRadioButton("휘핑 없이");
		rbChocoCreamNo.setFont(new Font("D2Coding", Font.PLAIN, 15));
		rbChocoCreamNo.setBounds(100, 110, 100, 20);
		contentPane.add(rbChocoCreamNo);
		
		rbChocoCream = new JRadioButton("휘핑크림 많이");
		rbChocoCream.setFont(new Font("D2Coding", Font.PLAIN, 15));
		rbChocoCream.setBounds(200, 110, 130, 20);
		contentPane.add(rbChocoCream);
	}
	
	private void saveChocoOrder() {
		String beverage = lblChoco.getText();
		String option = textChocoOption.getText();
		String chocoPriceText = lblChocoPrice.getText().replaceAll("\\\\", "").replaceAll(",", "");
		int price = Integer.parseInt(chocoPriceText);
		
		OrderHistory hist = new OrderHistory(0, null, beverage, option, price);
		int result = daohist.save(hist);
		if (result == 1) {
			
			dispose();
		}
	}
	
	private void handleChocoClick() {
		StringBuffer buffer = new StringBuffer();
		
		if (rbChocoLarge.isSelected()) {
			lblChocoPrice.setText(String.format("\\%,d", 6000));
		} else {
			lblChocoPrice.setText(String.format("\\%,d", 5500));
		}
		
		if (rbChocoRegular.isSelected()) {
			buffer.append(rbChocoRegular.getText());
		} else if (rbChocoLarge.isSelected()) {
			buffer.append(rbChocoLarge.getText());
		} else {
			JOptionPane.showMessageDialog(contentPane, "사이즈를 선택하지 않았습니다.");
		}
		
		if (rbChocoCreamNo.isSelected() && rbChocoCream.isSelected()) {
			JOptionPane.showMessageDialog(contentPane, "휘핑크림 옵션은 한 개만 선택 가능합니다.");
		} else if (rbChocoCreamNo.isSelected()) {
			buffer.append("/ " + rbChocoCreamNo.getText());
		} else if (rbChocoCream.isSelected()) {
			buffer.append("/ " + rbChocoCream.getText());
		} else {
			buffer.append("");
		}
		
		if (rbChocoRegular.isSelected() || rbChocoLarge.isSelected()) {
			textChocoOption.setText(buffer.toString());	
		}
		
	}
}
