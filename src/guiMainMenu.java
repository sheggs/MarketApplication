import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JList;
import javax.swing.JScrollPane;
import java.awt.Canvas;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

public class guiMainMenu {

	private JFrame frame;
	private JTextField textField;
	private JTextField promoCode;
	private JTextField txtamount;
	private JTextField txtPrecentageOff;
	private JTextField generatedCode;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					guiMainMenu window = new guiMainMenu();
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
	public guiMainMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 782, 595);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.textHighlight);
		
		JPanel main_panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(main_panel, GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(main_panel, GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)
		);
		
		JLabel generateCodeTitle = new JLabel("Generate Codes");
		generateCodeTitle.setFont(new Font("Tahoma", Font.PLAIN, 26));
		
		JButton confirmVoucherBtn = new JButton("Confirm");
		
		txtamount = new JTextField();
		txtamount.setText("\u00A3Amount");
		txtamount.setColumns(10);
		
		txtPrecentageOff = new JTextField();
		txtPrecentageOff.setText("Precentage off %");
		txtPrecentageOff.setColumns(10);
		
		JButton button = new JButton("Confirm");
		
		JLabel lblTopupVoucher = new JLabel("Top-Up Voucher");
		lblTopupVoucher.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblPromotionalCode = new JLabel("Promotional Code");
		lblPromotionalCode.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		generatedCode = new JTextField();
		generatedCode.setText("Generated Code");
		generatedCode.setColumns(10);
		GroupLayout gl_main_panel = new GroupLayout(main_panel);
		gl_main_panel.setHorizontalGroup(
			gl_main_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_main_panel.createSequentialGroup()
					.addGroup(gl_main_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_main_panel.createSequentialGroup()
							.addGap(166)
							.addComponent(generateCodeTitle, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.LEADING, gl_main_panel.createSequentialGroup()
							.addGap(87)
							.addGroup(gl_main_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblTopupVoucher)
								.addGroup(gl_main_panel.createParallelGroup(Alignment.LEADING, false)
									.addComponent(txtamount, Alignment.TRAILING)
									.addComponent(confirmVoucherBtn, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)))
							.addGap(52)
							.addGroup(gl_main_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPromotionalCode)
								.addGroup(gl_main_panel.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(txtPrecentageOff)
									.addComponent(button, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)))))
					.addContainerGap(97, Short.MAX_VALUE))
				.addGroup(gl_main_panel.createSequentialGroup()
					.addGap(45)
					.addComponent(generatedCode)
					.addGap(76))
		);
		gl_main_panel.setVerticalGroup(
			gl_main_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_main_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(generateCodeTitle, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
					.addGap(71)
					.addGroup(gl_main_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPromotionalCode, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTopupVoucher, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_main_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtamount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtPrecentageOff, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_main_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(button, GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
						.addComponent(confirmVoucherBtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(75)
					.addComponent(generatedCode, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addGap(129))
		);
		main_panel.setLayout(gl_main_panel);
		JLabel lblNewLabe3 = new JLabel("New label");
		
		JLabel lblMarketplace = new JLabel("MarketPlace");
		lblMarketplace.setForeground(SystemColor.window);
		lblMarketplace.setFont(new Font("Tahoma", Font.PLAIN, 38));
		
		JButton btnBuyItems = new JButton("Buy Items");
		btnBuyItems.setActionCommand("");
		
		JButton btnSellItems = new JButton("Sell Items");
		btnSellItems.setActionCommand("");
		
		JButton btnMyAccount = new JButton("My Account");
		btnMyAccount.setActionCommand("");
		
		JButton btnManageUsers = new JButton("Manage Users");
		btnManageUsers.setActionCommand("");
		
		JButton btnManageAdmins = new JButton("Manage Admins");
		btnManageAdmins.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnManageAdmins.setActionCommand("");
		
		JButton btnGenerateCode = new JButton("Generate Code");
		btnGenerateCode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnGenerateCode.setActionCommand("");
		
		JButton btnApproveProducts = new JButton("Approve Product");
		btnApproveProducts.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnApproveProducts.setActionCommand("");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblMarketplace)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(46)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnSellItems, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnBuyItems, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnMyAccount, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnManageUsers, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnManageAdmins, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnGenerateCode, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnApproveProducts, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(13, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(51)
					.addComponent(lblMarketplace)
					.addPreferredGap(ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
					.addComponent(btnBuyItems, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnSellItems, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnMyAccount, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addGap(29)
					.addComponent(btnManageUsers, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnManageAdmins, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnGenerateCode, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnApproveProducts, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addGap(31))
		);
		panel.setLayout(gl_panel);
		frame.getContentPane().setLayout(groupLayout);
	}
}
