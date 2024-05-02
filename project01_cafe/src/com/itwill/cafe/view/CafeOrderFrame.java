package com.itwill.cafe.view;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import com.itwill.cafe.controller.OrderHistoryDao;
import com.itwill.cafe.model.OrderHistory;
import com.itwill.cafe.view_menu.OrderUsaFrame.UsaOrderNotify;
import com.itwill.cafe.view_menu.OrderCappFrame;
import com.itwill.cafe.view_menu.OrderEspFrame;
import com.itwill.cafe.view_menu.OrderUsaFrame;

import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class CafeOrderFrame extends JFrame implements UsaOrderNotify {
	
	private static final String[] COLUMN_ORDERS = {
			"번호", "주문시간", "음료", "옵션"
	};

	private static final long serialVersionUID = 1L;
	
	private JFrame frame;
	private Component parent;
	private JPanel contentPane;
	private JButton btnUsa;
	private JButton btnEsp;
	private JPanel coffeePanel;
	private JPanel nonCoffee;
	private JButton btnCapp;
	private JButton btnLt;
	private JButton btnAde;
	private JButton btnStb;
	private JButton btnChoco;
	
	private JTabbedPane tabbedPane;
	private JPanel OrderDetails;
	private JTable table;
	private JScrollPane scrollPane;
	
	private DefaultTableModel tableModel;	
	private OrderHistoryDao daoHist = OrderHistoryDao.getInstance();
	private List<OrderHistory> hist;
	private JPanel panel;
	private JComboBox comboBox;
	private JTextField textField;
	private JButton btnSearchOrder;
	private JButton btnChangeOrder;
	private JButton btnCancelOrder;
	

	/**
	 * Launch the application.
	 */
	public static void showCafeOrderFrame(Component parent) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CafeOrderFrame frame = new CafeOrderFrame(parent);
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
	public CafeOrderFrame(Component parent) {
		this.parent = parent;
		
		initializeOrder();
		
	}
	
	public void initializeOrder() {
		setTitle("주문");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		int x = 0;
		int y = 0;
		if (parent != null) {
			x = parent.getX();
			y = parent.getY();
		}
		setBounds(x, y, 700, 500);
		
		if (parent == null) {
			setLocationRelativeTo(null);
		}
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 10, 660, 441);
		contentPane.add(tabbedPane);
		
		coffeePanel = new JPanel();
		tabbedPane.addTab("Coffee", null, coffeePanel, null);
		coffeePanel.setLayout(null);
		
		btnUsa = new JButton("아메리카노");
		btnUsa.setFont(new Font("D2Coding", Font.PLAIN, 15));
		btnUsa.addActionListener((e) -> orderUsa());
		btnUsa.setBounds(30, 30, 120, 30);
		coffeePanel.add(btnUsa);
		
		btnEsp = new JButton("에스프레소");
		btnEsp.addActionListener((e) -> orderEsp());
		btnEsp.setFont(new Font("D2Coding", Font.PLAIN, 15));
		btnEsp.setBounds(30, 170, 120, 30);
		coffeePanel.add(btnEsp);
		
		btnCapp = new JButton("카푸치노");
		btnCapp.addActionListener((e) -> orderCapp());
		btnCapp.setFont(new Font("D2Coding", Font.PLAIN, 15));
		btnCapp.setBounds(350, 170, 120, 30);
		coffeePanel.add(btnCapp);
		
		btnLt = new JButton("카페라떼");		
		btnLt.setFont(new Font("D2Coding", Font.PLAIN, 15));
		btnLt.setBounds(350, 30, 120, 30);
		coffeePanel.add(btnLt);
		
		nonCoffee = new JPanel();
		tabbedPane.addTab("nonCoffee", null, nonCoffee, null);
		nonCoffee.setLayout(null);
		
		btnAde = new JButton("자몽에이드");
		btnAde.setFont(new Font("D2Coding", Font.PLAIN, 15));
		btnAde.setBounds(30, 30, 120, 30);
		nonCoffee.add(btnAde);
		
		btnStb = new JButton("딸기스무디");
		btnStb.setFont(new Font("D2Coding", Font.PLAIN, 15));
		btnStb.setBounds(350, 30, 120, 30);
		nonCoffee.add(btnStb);
		
		btnChoco = new JButton("초코프라푸치노");
		btnChoco.setFont(new Font("D2Coding", Font.PLAIN, 15));
		btnChoco.setBounds(30, 170, 140, 30);
		nonCoffee.add(btnChoco);
		
		OrderDetails = new JPanel();
		tabbedPane.addTab("주문내역", null, OrderDetails, null);
		tabbedPane.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				orderHistory();
				
			}
		});
		
//		btnOrder.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				showCafeOrder();
//				frame.dispose();
//			}
//		});
		
		
		
		OrderDetails.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 10, 631, 301);	
		OrderDetails.add(scrollPane);
		
		table = new JTable();
	
		table.setFont(new Font("D2Coding", Font.PLAIN, 15));
		tableModel = new DefaultTableModel(null, COLUMN_ORDERS);
						
		scrollPane.add(table);		
		table.setModel(tableModel);	
	
		scrollPane.setViewportView(table);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 10, 10);
		OrderDetails.add(panel);
		panel.setLayout(null);
		
		comboBox = new JComboBox();
		comboBox.setBounds(20, 350, 70, 25);
		OrderDetails.add(comboBox);
		comboBox.setFont(new Font("D2Coding", Font.PLAIN, 15));
		
		textField = new JTextField();
		textField.setBounds(100, 350, 120, 25);
		OrderDetails.add(textField);
		textField.setColumns(10);
		
		btnSearchOrder = new JButton("주문 검색");
		btnSearchOrder.setBounds(230, 350, 120, 25);
		OrderDetails.add(btnSearchOrder);
		btnSearchOrder.setFont(new Font("D2Coding", Font.PLAIN, 15));
		
		btnChangeOrder = new JButton("주문 변경");
		btnChangeOrder.setBounds(390, 350, 120, 25);
		OrderDetails.add(btnChangeOrder);
		btnChangeOrder.setFont(new Font("D2Coding", Font.PLAIN, 15));
		
		btnCancelOrder = new JButton("주문 취소");
		btnCancelOrder.setBounds(520, 350, 120, 25);
		OrderDetails.add(btnCancelOrder);
		btnCancelOrder.setFont(new Font("D2Coding", Font.PLAIN, 15));
		
	}
	
	// 테이블 ORDERS 	
	private void orderHistory() {
		List<OrderHistory> hist = daoHist.read();
		resetHistory(hist);
		
	}
	

	private void resetHistory(List<OrderHistory> hist) {
	    tableModel = new DefaultTableModel(null, COLUMN_ORDERS);

	    for (OrderHistory h : hist) {
	        Object[] row = {
	                h.getId(), h.getOrderTime(),
	                h.getBeverage(), h.getBeverageOption()
	        };
	        tableModel.addRow(row);
	    }
	    table.setModel(tableModel);

	    // 각 열의 너비 동적 조정
	    for (int columnIndex = 0; columnIndex < table.getColumnCount(); columnIndex++) {
	        TableColumn column = table.getColumnModel().getColumn(columnIndex);
	        int maxWidth = 0;

	        // 각 행의 내용 중 가장 긴 길이를 찾음
	        for (int rowIndex = 0; rowIndex < table.getRowCount(); rowIndex++) {
	            TableCellRenderer renderer = table.getCellRenderer(rowIndex, columnIndex);
	            Component comp = table.prepareRenderer(renderer, rowIndex, columnIndex);
	            maxWidth = Math.max(comp.getPreferredSize().width, maxWidth);
	        }

	        // 최대 길이에 따라 열의 너비 조정
	        column.setPreferredWidth(maxWidth + 20); // 적절한 여유 공간을 더해줌
	    }
	}
	
	private void orderCapp() {
		OrderCappFrame.showOrderCappFrame(CafeOrderFrame.this);
	}
	
	private void orderEsp() {
		OrderEspFrame.showOrderEspFrame(CafeOrderFrame.this);
	}

	
	private void orderUsa() {
		OrderUsaFrame.showOrderUsaFrame(CafeOrderFrame.this, CafeOrderFrame.this);
	}

	@Override
	public void usaOrderSuccess() {
		orderHistory();
	}
}
