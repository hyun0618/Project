package com.itwill.cafe.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.time.LocalDateTime;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import com.itwill.cafe.controller.OrderCheckDao;
import com.itwill.cafe.model.OrderCheck;
import com.itwill.cafe.view.CafeOrderFrame.SaveNotify;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OrderCheckFrame extends JFrame {
	
	private static final String[] SEARCH_TYPES = {
			"날짜", "음료", "결제방법"
	};
	
	private static final String[] COLUMN_ORDER_CHECK = {
			"번호", "주문날짜 및 시간", "음료", "결제방법", "주문금액" 
	};
	
	private OrderCheckDao daoCheck = OrderCheckDao.getInstance();
	
	private JTable table;
	private DefaultTableModel tableModel;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Component parent;
	
	private JScrollPane scrollPane;

	private List<String> orderBeverages;
	private String orderPayment;
	private String orderPrice;
	private JButton btnSearchOrder;
	private JButton btnDeleteOrder;
	private JTextField textSearch;
	private JComboBox comboBox;
	private JButton btnReadAll;
	

	/**
	 * Launch the application.
	 */
	public static void showOrderCheckFrame(Component parent, List<String> orderBeverages, 
											String orderPayment, String orderPrice) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderCheckFrame frame = 
					new OrderCheckFrame(parent, orderBeverages, orderPayment, orderPrice);
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
	public OrderCheckFrame(Component parent, List<String> orderBeverages, 
								String orderPayment, String orderPrice) {
		this.orderBeverages = orderBeverages;
		this.orderPayment = orderPayment;
		this.orderPrice = orderPrice;
		
		this.parent = parent;
		
		initializeOrderCheck();
		orderCheck();
	}
	
	public void initializeOrderCheck() {
		setTitle("결제확인 및 주문내역");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		int x = 0;
		int y = 0;
		if (parent != null) {
			x = parent.getX();
			y = parent.getY();
		}
		setBounds(x, y, 600, 450);
		
		if (parent == null) {
			setLocationRelativeTo(null);
		}
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(248, 248, 255));
		scrollPane.setBounds(12, 10, 562, 250);
		contentPane.add(scrollPane);
		
		table = new JTable();	
		table.setBackground(new Color(248, 248, 255));
		table.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		scrollPane.setViewportView(table);
		
		btnSearchOrder = new JButton("주문 검색");
		btnSearchOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchOrder();
			}
		});
		btnSearchOrder.setFont(new Font("D2Coding", Font.PLAIN, 13));
		btnSearchOrder.setBounds(350, 280, 100, 23);
		contentPane.add(btnSearchOrder);
		
		btnDeleteOrder = new JButton("주문 삭제");
		btnDeleteOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteOrderCheck();
			}
		});
		btnDeleteOrder.setFont(new Font("D2Coding", Font.PLAIN, 13));
		btnDeleteOrder.setBounds(470, 281, 100, 23);
		contentPane.add(btnDeleteOrder);
		
		comboBox = new JComboBox<>();
		final DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>(SEARCH_TYPES);
		comboBox.setModel(comboBoxModel);
		comboBox.setFont(new Font("D2Coding", Font.PLAIN, 13));
		comboBox.setBounds(140, 280, 80, 23);
		contentPane.add(comboBox);
		
		textSearch = new JTextField();
		textSearch.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		textSearch.setBounds(235, 281, 100, 21);
		contentPane.add(textSearch);
		textSearch.setColumns(10);
		
		btnReadAll = new JButton("주문 전체");
		btnReadAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				orderCheck();
			}
		});
		btnReadAll.setFont(new Font("D2Coding", Font.PLAIN, 13));
		btnReadAll.setBounds(20, 280, 100, 23);
		contentPane.add(btnReadAll);
	
	}
	
	
	private void deleteOrderCheck() {
		int index = table.getSelectedRow(); 
		if (index == -1) { 
			JOptionPane.showMessageDialog(
					contentPane, 
					"삭제할 주문을 선택하세요.", 
					"경고", 
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		int confirm = JOptionPane.showConfirmDialog(
				contentPane, 
				"삭제하시겠습니까?", 
				"삭제 확인", 
				JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_OPTION) {
			Integer id = (Integer) tableModel.getValueAt(index, 0);
			int result = daoCheck.deleteOrder(id);
			if (result == 1) {
				orderCheck();
				JOptionPane.showMessageDialog(contentPane, "해당 주문내역을 삭제했습니다.");
			} 		
		}
	}
	
	private void searchOrder() {
		int type = comboBox.getSelectedIndex(); 
		String keyword = textSearch.getText(); 
		if (keyword.equals("")) {
			JOptionPane.showMessageDialog(
					contentPane, 
					"검색어를 입력하세요.", 
					"경고",  
					JOptionPane.WARNING_MESSAGE);
			textSearch.requestFocus(); // -> textSearch에 포커스.
			
			return;
		}
		
		List<OrderCheck> check = daoCheck.search(type, keyword);
		resetCheck(check);
	}
	
	private void orderCheck() {
		List<OrderCheck> check = daoCheck.read();
		resetCheck(check);
	}
	
	private String tableDateFormat(LocalDateTime time) {
		return String.format("%4d/%02d/%02d %02d:%02d", 
				time.getYear(), time.getMonthValue(), time.getDayOfMonth(),
				time.getHour(), time.getMinute());
	}
	
	private void resetCheck(List<OrderCheck> check) {
		tableModel = new DefaultTableModel(null, COLUMN_ORDER_CHECK);
		for (OrderCheck c : check) {
				Object[] row = {
						c.getId(), tableDateFormat(c.getOrderTime()), c.getOrderBeverages(),
						c.getOrderPayment(), c.getOrderPrice()
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
}
