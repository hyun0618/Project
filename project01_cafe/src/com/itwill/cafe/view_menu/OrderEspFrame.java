package com.itwill.cafe.view_menu;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;

import com.itwill.cafe.controller.OrderHistoryDao;
import com.itwill.cafe.model.OrderHistory;
import com.itwill.cafe.view.CafeOrderFrame;
import com.itwill.cafe.view_menu.OrderUsaFrame.UsaOrderNotify;

import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextArea;


public class OrderEspFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private OrderHistoryDao daohist = OrderHistoryDao.getInstance();
	private final ButtonGroup buttonGroupEsp = new ButtonGroup();
	private Component parent;
	
	private JPanel contentPane;
	private JLabel lblEsp;
	private JLabel lblOption;
	private JRadioButton rbEspSingle;
	private JRadioButton rbEspDouble;
	private JButton btnEspChoice;
	private JTextArea textEspOption;
	private JButton btnEspOrder;
	private JLabel lblEspPrice;
	private JLabel lblEspDouble;
	private JLabel lblPayment;
	
	
	/**
	 * Launch the application.
	 */

	
	public static void showOrderEspFrame(Component parent) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderEspFrame frame = new OrderEspFrame(parent);
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
	
	public OrderEspFrame(Component parent) {
		this.parent = parent;
		
		initializeOrderEsp();
	}
	
	
	public void initializeOrderEsp() {
		setTitle("Coffee > 에스프레소");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		int x = 0;
		int y = 0;
		if (parent != null) {
			x = parent.getX();
			y = parent.getY();
		}
		setBounds(x+250, y+150, 390, 300);
		
		if (parent == null) {
			setLocationRelativeTo(null);
		}
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblEsp = new JLabel("에스프레소");
		lblEsp.setFont(new Font("D2Coding", Font.PLAIN, 17));
		lblEsp.setBounds(20, 20, 92, 20);
		contentPane.add(lblEsp);
		
		lblOption = new JLabel("옵션");
		lblOption.setFont(new Font("D2Coding", Font.PLAIN, 15));
		lblOption.setBounds(62, 57, 42, 15);
		contentPane.add(lblOption);
		
		rbEspSingle = new JRadioButton("싱글");
		buttonGroupEsp.add(rbEspSingle);
		rbEspSingle.setFont(new Font("D2Coding", Font.PLAIN, 15));
		rbEspSingle.setBounds(120, 54, 60, 20);
		contentPane.add(rbEspSingle);
		
		rbEspDouble = new JRadioButton("더블");
		buttonGroupEsp.add(rbEspDouble);
		rbEspDouble.setFont(new Font("D2Coding", Font.PLAIN, 15));
		rbEspDouble.setBounds(185, 54, 60, 20);
		contentPane.add(rbEspDouble);
		
		lblEspDouble = new JLabel("(+\\500)");
		lblEspDouble.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		lblEspDouble.setBounds(195, 75, 50, 15);
		contentPane.add(lblEspDouble);
		
		btnEspChoice = new JButton("선택");
		btnEspChoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleEspClick();
			}
		});
		btnEspChoice.setFont(new Font("D2Coding", Font.PLAIN, 15));
		btnEspChoice.setBounds(270, 110, 70, 30);
		contentPane.add(btnEspChoice);
		
		textEspOption = new JTextArea();
		textEspOption.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textEspOption.setBounds(120, 109, 125, 30);
		contentPane.add(textEspOption);
		
		btnEspOrder = new JButton("담기");
		btnEspOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String espOption = textEspOption.getText().trim();
				if (!espOption.isEmpty()) {
					saveEspOrder();
					dispose();
				} else {
					JOptionPane.showMessageDialog(contentPane, "옵션을 선택하지 않았습니다.");
				}
			}
		});
		btnEspOrder.setFont(new Font("D2Coding", Font.PLAIN, 15));
		btnEspOrder.setBounds(270, 166, 70, 30);
		contentPane.add(btnEspOrder);
		
		lblEspPrice = new JLabel(String.format("\\%,d", 3000));
		lblEspPrice.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		lblEspPrice.setBounds(193, 171, 52, 15);
		contentPane.add(lblEspPrice);
		
		lblPayment = new JLabel("금액");
		lblPayment.setFont(new Font("D2Coding", Font.PLAIN, 15));
		lblPayment.setBounds(62, 174, 42, 15);
		contentPane.add(lblPayment);
		
		
		
	}
	
	private void saveEspOrder() {
		String beverage = lblEsp.getText();
		String option = textEspOption.getText();
		String espPriceText = lblEspPrice.getText().replaceAll("\\\\", "").replaceAll(",", "");
		int price = Integer.parseInt(espPriceText);
		
		OrderHistory hist = new OrderHistory(0, beverage, option, price);
		int result = daohist.save(hist);
		if (result == 1) {
			
			dispose();
		}
	}
	
	private void handleEspClick() {
		StringBuffer buffer = new StringBuffer();
		
		if (rbEspDouble.isSelected()) {
			lblEspPrice.setText(String.format("\\%,d", 3500));
		} else {
			lblEspPrice.setText(String.format("\\%,d", 3000));
		}
		
		if (rbEspSingle.isSelected()) {
			buffer.append(rbEspSingle.getText());
		} else if (rbEspDouble.isSelected()) {
			buffer.append(rbEspDouble.getText());
		} else {
			JOptionPane.showMessageDialog(contentPane, "싱글/ 더블을 선택하지 않았습니다.");
		}
		
		if (rbEspSingle.isSelected() || rbEspDouble.isSelected()) {
			textEspOption.setText(buffer.toString());	
		}	
	}
}
