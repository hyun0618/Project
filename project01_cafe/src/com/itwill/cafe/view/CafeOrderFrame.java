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
import com.itwill.cafe.view_menu.OrderAdeFrame;
import com.itwill.cafe.view_menu.OrderCappFrame;
import com.itwill.cafe.view_menu.OrderChocoFrame;
import com.itwill.cafe.view_menu.OrderEspFrame;
import com.itwill.cafe.view_menu.OrderLtFrame;
import com.itwill.cafe.view_menu.OrderStbFrame;
import com.itwill.cafe.view_menu.OrderUsaFrame;

import javax.swing.JTabbedPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;


import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class CafeOrderFrame extends JFrame implements UsaOrderNotify {
	
	private static final String[] SEARCH_TYPES = {
			"날짜", "음료"	
	};

	
	private static final String[] COLUMN_ORDERS = {
			"번호", "주문시간", "음료", "옵션", "가격"
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
	private JComboBox<String> comboBox;
	private JTextField textSearch;
	private JButton btnSearchOrder;
	private JButton btnAllOrder;
	private JButton btnCancelOrder;
	private JLabel lblUsaImage;
	private JTextField textLtPrice;
	private JTextField textUsaPrice;
	private JTextField textEspPrice;
	private JTextField textCappPrice;
	private JTextField textAdePrice;
	private JTextField textStbPrice;
	private JTextField textChocoPrice;
	private JButton btnTotal;
	private JTextField textTotal;
	private int totalBeveragePrice;

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
		tabbedPane.setBounds(12, 10, 662, 335);
		contentPane.add(tabbedPane);
		
		coffeePanel = new JPanel();
		tabbedPane.addTab("Coffee", null, coffeePanel, null);
		coffeePanel.setLayout(null);
		
		btnUsa = new JButton("아메리카노");
		btnUsa.setFont(new Font("D2Coding", Font.PLAIN, 17));
		btnUsa.addActionListener((e) -> orderUsa());
		btnUsa.setBounds(35, 30, 130, 30);
		coffeePanel.add(btnUsa);
		
		textUsaPrice = new JTextField();
		textUsaPrice.setText("\\3,000");
		textUsaPrice.setHorizontalAlignment(SwingConstants.CENTER);
		textUsaPrice.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		textUsaPrice.setEditable(false);
		textUsaPrice.setColumns(10);
		textUsaPrice.setBounds(35, 70, 130, 20);
		coffeePanel.add(textUsaPrice);
		
		btnEsp = new JButton("에스프레소");
		btnEsp.addActionListener((e) -> orderEsp());
		btnEsp.setFont(new Font("D2Coding", Font.PLAIN, 17));
		btnEsp.setBounds(35, 170, 130, 30);
		coffeePanel.add(btnEsp);
		
		textEspPrice = new JTextField();
		textEspPrice.setText("\\3,000");
		textEspPrice.setHorizontalAlignment(SwingConstants.CENTER);
		textEspPrice.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		textEspPrice.setEditable(false);
		textEspPrice.setColumns(10);
		textEspPrice.setBounds(35, 210, 130, 20);
		coffeePanel.add(textEspPrice);
		
		btnLt = new JButton("카페라떼");
		btnLt.addActionListener((e) -> orderLt());
		btnLt.setFont(new Font("D2Coding", Font.PLAIN, 17));
		btnLt.setBounds(500, 30, 120, 30);
		coffeePanel.add(btnLt);
		
		textLtPrice = new JTextField();
		textLtPrice.setHorizontalAlignment(SwingConstants.CENTER);
		textLtPrice.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		textLtPrice.setText("\\4,000");
		textLtPrice.setEditable(false);
		textLtPrice.setBounds(500, 70, 120, 20);
		coffeePanel.add(textLtPrice);
		textLtPrice.setColumns(10);
		
		btnCapp = new JButton("카푸치노");
		btnCapp.addActionListener((e) -> orderCapp());
		btnCapp.setFont(new Font("D2Coding", Font.PLAIN, 17));
		btnCapp.setBounds(500, 170, 120, 30);
		coffeePanel.add(btnCapp);
		
		textCappPrice = new JTextField();
		textCappPrice.setText("\\4,000");
		textCappPrice.setHorizontalAlignment(SwingConstants.CENTER);
		textCappPrice.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		textCappPrice.setEditable(false);
		textCappPrice.setColumns(10);
		textCappPrice.setBounds(500, 210, 120, 20);
		coffeePanel.add(textCappPrice);
		
		nonCoffee = new JPanel();
		tabbedPane.addTab("nonCoffee", null, nonCoffee, null);
		nonCoffee.setLayout(null);
		
		btnAde = new JButton("자몽에이드");
		btnAde.addActionListener((e) -> orderAde());
		btnAde.setFont(new Font("D2Coding", Font.PLAIN, 17));
		btnAde.setBounds(35, 30, 130, 30);
		nonCoffee.add(btnAde);
		
		textAdePrice = new JTextField();
		textAdePrice.setText("\\5,000");
		textAdePrice.setHorizontalAlignment(SwingConstants.CENTER);
		textAdePrice.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		textAdePrice.setEditable(false);
		textAdePrice.setColumns(10);
		textAdePrice.setBounds(35, 70, 130, 20);
		nonCoffee.add(textAdePrice);
		
		btnStb = new JButton("딸기스무디");
		btnStb.addActionListener((e) -> orderStb());
		btnStb.setFont(new Font("D2Coding", Font.PLAIN, 17));
		btnStb.setBounds(500, 30, 120, 30);
		nonCoffee.add(btnStb);
		
		textStbPrice = new JTextField();
		textStbPrice.setText("\\5,000");
		textStbPrice.setHorizontalAlignment(SwingConstants.CENTER);
		textStbPrice.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		textStbPrice.setEditable(false);
		textStbPrice.setColumns(10);
		textStbPrice.setBounds(500, 70, 120, 20);
		nonCoffee.add(textStbPrice);
		
		btnChoco = new JButton("초코프라푸치노");
		btnChoco.addActionListener((e) -> orderChoco());
		btnChoco.setFont(new Font("D2Coding", Font.PLAIN, 17));
		btnChoco.setBounds(35, 170, 155, 30);
		nonCoffee.add(btnChoco);
		
		textChocoPrice = new JTextField();
		textChocoPrice.setText("\\5,500");
		textChocoPrice.setHorizontalAlignment(SwingConstants.CENTER);
		textChocoPrice.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		textChocoPrice.setEditable(false);
		textChocoPrice.setColumns(10);
		textChocoPrice.setBounds(35, 210, 155, 20);
		nonCoffee.add(textChocoPrice);
		
		OrderDetails = new JPanel();
		tabbedPane.addTab("주문내역", null, OrderDetails, null);
		tabbedPane.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				orderHistory();
				
			}
		});
		
				
		OrderDetails.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 10, 633, 207);	
		OrderDetails.add(scrollPane);
		
		table = new JTable();
	
		table.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		tableModel = new DefaultTableModel(null, COLUMN_ORDERS);
						
		scrollPane.add(table);		
		table.setModel(tableModel);	
	
		scrollPane.setViewportView(table);
		
		comboBox = new JComboBox<>();
		final DefaultComboBoxModel<String> comboBoxModel = 
				new DefaultComboBoxModel<>(SEARCH_TYPES);
		comboBox.setModel(comboBoxModel);
		comboBox.setBounds(150, 230, 70, 25);
		OrderDetails.add(comboBox);
		comboBox.setFont(new Font("D2Coding", Font.PLAIN, 15));
		
		textSearch = new JTextField();
		textSearch.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		textSearch.setBounds(230, 230, 120, 25);
		OrderDetails.add(textSearch);
		textSearch.setColumns(10);
		
		btnSearchOrder = new JButton("주문 검색");
		btnSearchOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				try {
					searchOrder();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
			
		});
		btnSearchOrder.setBounds(360, 230, 100, 25);
		OrderDetails.add(btnSearchOrder);
		btnSearchOrder.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		
		btnAllOrder = new JButton("주문 전체");
		btnAllOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				orderHistory();
			}
		});
		btnAllOrder.setBounds(20, 230, 100, 25);
		OrderDetails.add(btnAllOrder);
		btnAllOrder.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		
		btnCancelOrder = new JButton("주문 취소 및 삭제");
		btnCancelOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteOrder();
			}
		});
		btnCancelOrder.setBounds(470, 230, 160, 25);
		OrderDetails.add(btnCancelOrder);
		btnCancelOrder.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		
		btnTotal = new JButton("주문 금액 합계");
		btnTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 textTotal.setText(String.format("\\%,d", totalBeveragePrice));
			}
		});
		btnTotal.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		btnTotal.setBounds(360, 265, 135, 25);
		OrderDetails.add(btnTotal);
		
		textTotal = new JTextField();
		textTotal.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		textTotal.setEditable(false);
		textTotal.setBounds(510, 265, 120, 25);
		OrderDetails.add(textTotal);
		textTotal.setColumns(10);
		
	}

// 주문 검색
	
	private void searchOrder() throws ParseException {
		int type = comboBox.getSelectedIndex(); 
		String keyword = textSearch.getText(); 
		if (keyword.equals("")) {
			JOptionPane.showMessageDialog(
					frame, 
					"검색어를 입력하세요.", 
					"경고",  
					JOptionPane.WARNING_MESSAGE);
			textSearch.requestFocus(); // -> textSearch에 포커스.
			
			return;
		}
		
		List<OrderHistory> hist = daoHist.search(type, keyword);
		resetHistory(hist);
	}
	
	
// 주문 취소 및 삭제
	private void deleteOrder() {
		int index = table.getSelectedRow(); 
		if (index == -1) { 
			JOptionPane.showMessageDialog(
					frame, 
					"삭제할 행을 선택하세요.", 
					"경고", 
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		int confirm = JOptionPane.showConfirmDialog(
				frame, 
				"정말 삭제하시겠습니까?", 
				"삭제 확인", 
				JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_OPTION) {
			Integer id = (Integer) tableModel.getValueAt(index, 0);
			int result = daoHist.delete(id);
			if (result == 1) {
				orderHistory();
				JOptionPane.showMessageDialog(frame, "주문이 삭제되었습니다.");
			} 		
		}
	}
	
	
// 테이블 ORDERS 	
	private void orderHistory() {
		List<OrderHistory> hist = daoHist.read();
		resetHistory(hist);	
	}

	private void resetHistory(List<OrderHistory> hist) {

		tableModel = new DefaultTableModel(null, COLUMN_ORDERS);
	    totalBeveragePrice = 0;

	    for (OrderHistory h : hist) {
	        Object[] row = {
	                h.getId(), h.getOrderTime(),
	                h.getBeverage(), h.getBeverageOption(), h.getBeveragePrice()
	        };
	        tableModel.addRow(row);
	      
	        totalBeveragePrice += h.getBeveragePrice();      
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
	
	
	private void orderChoco() {
		OrderChocoFrame.showOrderChocoFrame(CafeOrderFrame.this);
	}
	
	private void orderStb() {
		OrderStbFrame.showOrderStbFrame(CafeOrderFrame.this);
	}
	
	private void orderAde() {
		OrderAdeFrame.showOrderAdeFrame(CafeOrderFrame.this);
	}
	
	private void orderLt() {
		OrderLtFrame.showOrderLtFrame(CafeOrderFrame.this);
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
