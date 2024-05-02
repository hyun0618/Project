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
	private JRadioButton rbEspSingle;
	private JRadioButton rbEspDouble;
	private JButton btnEspChoice;
	private JTextArea textEspOption;
	private JButton btnEspOrder;
	
	
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
		setBounds(x+230, y+200, 300, 200);
		
		if (parent == null) {
			setLocationRelativeTo(null);
		}
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblEsp = new JLabel("에스프레소");
		lblEsp.setFont(new Font("D2Coding", Font.PLAIN, 15));
		lblEsp.setBounds(30, 20, 100, 20);
		contentPane.add(lblEsp);
		
		rbEspSingle = new JRadioButton("싱글");
		buttonGroupEsp.add(rbEspSingle);
		rbEspSingle.setFont(new Font("D2Coding", Font.PLAIN, 15));
		rbEspSingle.setBounds(30, 55, 60, 20);
		contentPane.add(rbEspSingle);
		
		rbEspDouble = new JRadioButton("더블");
		buttonGroupEsp.add(rbEspDouble);
		rbEspDouble.setFont(new Font("D2Coding", Font.PLAIN, 15));
		rbEspDouble.setBounds(100, 55, 60, 20);
		contentPane.add(rbEspDouble);
		
		btnEspChoice = new JButton("확인");
		btnEspChoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleEspClick();
			}
		});
		btnEspChoice.setFont(new Font("D2Coding", Font.PLAIN, 15));
		btnEspChoice.setBounds(180, 50, 70, 30);
		contentPane.add(btnEspChoice);
		
		textEspOption = new JTextArea();
		textEspOption.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textEspOption.setBounds(70, 100, 60, 30);
		contentPane.add(textEspOption);
		
		btnEspOrder = new JButton("주문");
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
		btnEspOrder.setBounds(180, 100, 70, 30);
		contentPane.add(btnEspOrder);
		
	}
	
	private void saveEspOrder() {
		String beverage = lblEsp.getText();
		String option = textEspOption.getText();
		
		OrderHistory hist = new OrderHistory(0, null, beverage, option);
		int result = daohist.save(hist);
		if (result == 1) {
			
			dispose();
		}
	}
	
	private void handleEspClick() {
		StringBuffer buffer = new StringBuffer();
		
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
