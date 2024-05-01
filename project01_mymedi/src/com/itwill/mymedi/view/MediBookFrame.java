package com.itwill.mymedi.view;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JTextField;
import javax.swing.JLabel;

import com.itwill.mymedi.controller.MedsDao;
import com.itwill.mymedi.controller.PharmsDao;
import com.itwill.mymedi.model.*;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextArea;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MediBookFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private static final String[] COLUMN_MEDS = {
		"약", "재고"	
	};

	private Component parent;
	private PharmsDao dao = PharmsDao.getInstance();
	private int pharmId;
	
	private JPanel contentPane;
	private JLabel lblName;
	private JTextField textName;
	private JLabel lblAddress;
	private JTable table;
	private JTextArea textAddress;
	private JComboBox comboBoxTime;
	private JLabel lblTime;
	private JButton btnBuy;
	private JScrollPane scrollPane;
	
	private DefaultTableModel tableModel;
	private MedsDao daoMeds = MedsDao.getInstance();
	
	/**
	 * Launch the application.
	 */
	public static void showMediBookFrame(Component parent, int pharmId) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MediBookFrame frame = new MediBookFrame(parent, pharmId);
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
	public MediBookFrame(Component parent, int pharmId) {
		this.parent = parent;
		this.pharmId = pharmId;
		
		initialize();
		initializePharms();
		initializeMedTable();
	}
	
	public void initialize() {
		setTitle("주문하기");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		int x = 0;
		int y = 0;
		if (parent != null) {
			x = parent.getX();
			y = parent.getY();
		}
		setBounds(x+100, y+200, 300, 470);
		
		if (parent == null) {
			setLocationRelativeTo(null);
		}
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblName = new JLabel("약국 이름");
		lblName.setFont(new Font("D2Coding", Font.PLAIN, 15));
		lblName.setBounds(12, 10, 70, 25);
		contentPane.add(lblName);
		
		textName = new JTextField();
		textName.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textName.setBounds(92, 10, 120, 25);
		contentPane.add(textName);
		textName.setColumns(10);
		
		lblAddress = new JLabel("주소");
		lblAddress.setFont(new Font("D2Coding", Font.PLAIN, 15));
		lblAddress.setBounds(12, 45, 70, 25);
		contentPane.add(lblAddress);
		
		textAddress = new JTextArea();
		textAddress.setLineWrap(true);
		textAddress.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textAddress.setBounds(92, 45, 180, 35);
		contentPane.add(textAddress);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 95, 260, 278);
		contentPane.add(scrollPane);
		
		// meds 테이블
		table = new JTable();
		table.setFont(new Font("D2Coding", Font.PLAIN, 15));
		table.setRowHeight(20);
		tableModel = new DefaultTableModel(null, COLUMN_MEDS);
		table.setModel(tableModel);
		scrollPane.setViewportView(table);
		
		lblTime = new JLabel("픽업 시간");
		lblTime.setFont(new Font("D2Coding", Font.PLAIN, 15));
		lblTime.setBounds(12, 383, 70, 25);
		contentPane.add(lblTime);
		
		comboBoxTime = new JComboBox();
		comboBoxTime.setBounds(92, 384, 100, 23);
		contentPane.add(comboBoxTime);
		
		btnBuy = new JButton("주문");
		btnBuy.setFont(new Font("D2Coding", Font.PLAIN, 15));
		btnBuy.setBounds(202, 383, 70, 25);
		contentPane.add(btnBuy);
		
	}
	
	
	private void initializeMedTable() {
		List<Meds> meds = daoMeds.read();
		tableModel = new DefaultTableModel(null, COLUMN_MEDS);
		for (Meds m : meds) {
			Object[] row = {
					m.getMed(), m.getStock()
			};
			tableModel.addRow(row);
		}
		table.setModel(tableModel);
	}
	
// 예약 창에서 약국 정보 띄우기.
	private void initializePharms() {
		Pharms pharm = dao.read(pharmId);
		if (pharm == null) return;
		
		textName.setText(pharm.getName());
		textAddress.setText(pharm.getAddress());
		
	}

}
