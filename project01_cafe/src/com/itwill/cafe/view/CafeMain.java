package com.itwill.cafe.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CafeMain {

	private JFrame frame;
	private JButton btnOrder;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CafeMain window = new CafeMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CafeMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setSize(700, 600);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		
		btnOrder = new JButton("주문하기");
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showCafeOrder();
				frame.dispose();
			}
		});
		
		
		btnOrder.setFont(new Font("D2Coding", Font.PLAIN, 20));
		btnOrder.setBounds(250, 400, 200, 50);
		frame.getContentPane().add(btnOrder);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Cafe");
	}

	private void showCafeOrder() {
		CafeOrderFrame.showCafeOrderFrame(frame);
	}
}
