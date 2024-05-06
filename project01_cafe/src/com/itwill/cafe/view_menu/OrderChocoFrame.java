package com.itwill.cafe.view_menu;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.itwill.cafe.controller.OrderHistoryDao;

public class OrderChocoFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private OrderHistoryDao daohist = OrderHistoryDao.getInstance();	
	private final ButtonGroup buttonGroupAde = new ButtonGroup();
	private Component parent;
	
	private JPanel contentPane;

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
		setBounds(x, y+350, 500, 250);
		
		if (parent == null) {
			setLocationRelativeTo(null);
		}
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
	}

}
