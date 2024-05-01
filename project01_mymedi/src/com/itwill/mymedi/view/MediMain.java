package com.itwill.mymedi.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.itwill.mymedi.controller.PharmsDao;
import com.itwill.mymedi.model.Meds;
import com.itwill.mymedi.model.Pharms;

import java.awt.Font;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class MediMain {
	
	private static final String[] SEARCH_TYPES = {
			"주소", "약국 이름"
		};
		
		private static final String[] COLUMN_NAMES = {
			"약국 이름", "주소"	
		};

	private JFrame frame;
	private JPanel searchPanel;
	private JComboBox<String> comboBox;
	private JTextField textSearchKeyword;
	private JButton btnSearch;
	private JPanel buttonPanel;
	private JButton btnReadAll;
	private JButton btnBooking;
	private JScrollPane scrollPane;
	private JTable table;

	private DefaultTableModel tableModel;
	private PharmsDao dao = PharmsDao.getInstance();
	private List<Pharms> pharms; // ***

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MediMain window = new MediMain();
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
	public MediMain() {
		initialize();
		initializeTable();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 700);
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("MyMedi");
		
		searchPanel = new JPanel();
		searchPanel.setBounds(0, 0, 684, 64);
		frame.getContentPane().add(searchPanel);
		searchPanel.setLayout(null);
		
		comboBox = new JComboBox<>();
		final DefaultComboBoxModel<String> comboBoxModel = 
				new DefaultComboBoxModel<>(SEARCH_TYPES);
		comboBox.setModel(comboBoxModel);
		comboBox.setFont(new Font("D2Coding", Font.PLAIN, 15));
		comboBox.setBounds(70, 30, 100, 25);
		searchPanel.add(comboBox);
		
		textSearchKeyword = new JTextField();
		textSearchKeyword.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textSearchKeyword.setBounds(200, 30, 300, 25);
		searchPanel.add(textSearchKeyword);
		textSearchKeyword.setColumns(10);
		
		btnSearch = new JButton("검색");
		btnSearch.addActionListener((e) -> search());
		btnSearch.setFont(new Font("D2Coding", Font.PLAIN, 15));
		btnSearch.setBounds(530, 30, 100, 25);
		searchPanel.add(btnSearch);
		
		buttonPanel = new JPanel();
		buttonPanel.setBounds(0, 608, 684, 53);
		frame.getContentPane().add(buttonPanel);
		
		btnReadAll = new JButton("전체 보기");
		btnReadAll.setBounds(350, 5, 120, 25);
		btnReadAll.addActionListener((e) -> initializeTable());
		buttonPanel.setLayout(null);
		btnReadAll.setFont(new Font("D2Coding", Font.PLAIN, 15));
		buttonPanel.add(btnReadAll);
		
		btnBooking = new JButton("예약");
		btnBooking.setBounds(500, 5, 100, 25);
		buttonPanel.add(btnBooking);
		btnBooking.addActionListener((e) -> showBookMedi());
		btnBooking.setFont(new Font("D2Coding", Font.PLAIN, 15));
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 74, 660, 524);
		frame.getContentPane().add(scrollPane);
		
		// pharms 테이블 
		table = new JTable();
		table.setFont(new Font("D2Coding", Font.PLAIN, 15));
		table.setRowHeight(20);
		tableModel = new DefaultTableModel(null, COLUMN_NAMES);
		table.setModel(tableModel);
		scrollPane.setViewportView(table);
		
	}
	
	private void showBookMedi() {
		int index = table.getSelectedRow();
		if (index == -1) {
			JOptionPane.showMessageDialog(
					frame, 
					"약국을 선택하세요.", 
					"경고", 
					JOptionPane.WARNING_MESSAGE);
			return;
		}
//		Integer id = (Integer) tableModel.getValueAt(index, 0);
		MediBookFrame.showMediBookFrame(frame, pharms.get(index).getId());
	}

	private void search() {
		int type = comboBox.getSelectedIndex();
		String keyword = textSearchKeyword.getText();
		if (keyword.equals("")) {
			JOptionPane.showMessageDialog(
					frame, 
					"검색어를 입력하세요.", 
					"경고", 
					JOptionPane.WARNING_MESSAGE);
			textSearchKeyword.requestFocus();
			
			return;
		}
		
		List<Pharms> pharm = dao.search(type, keyword);
		resetTable(pharm);
		
	}
	
	private void initializeTable() { // ***
		pharms = dao.read(); 
		resetTable(pharms);
	}

	private void resetTable(List<Pharms> pharms) {
		tableModel = new DefaultTableModel(null, COLUMN_NAMES);
		for (Pharms p : pharms) {
			Object[] row = {
					p.getName(), p.getAddress()
			};
			tableModel.addRow(row);
		}
		table.setModel(tableModel);
	}

}
