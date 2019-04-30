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
		
		JLabel yourAccountTitle = new JLabel("Your account");
		yourAccountTitle.setFont(new Font("Tahoma", Font.PLAIN, 26));
		
		JLabel label = new JLabel("Products on market");
		label.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel productsPurchasedTitle = new JLabel("Purchased Products");
		productsPurchasedTitle.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JTextArea productsPurchasedTextArea = new JTextArea();
		
		JLabel productsAwatingTitle = new JLabel("Products awaiting apporval");
		productsAwatingTitle.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JTextArea textArea = new JTextArea();
		
		JLabel yourDetailsLabel = new JLabel("Your Details");
		yourDetailsLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JLabel nameLable = new JLabel("Name: {NAME}");
		nameLable.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JLabel balanceLabel = new JLabel("Balance: $0");
		balanceLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JLabel emailLabel = new JLabel("Email:test@test.com");
		emailLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JTextField promoCode = new JTextField();
		promoCode.setText("Top Up Voucher");
		promoCode.setColumns(10);
		
		JButton confirmVoucherBtn = new JButton("Confirm");
		GroupLayout gl_main_panel = new GroupLayout(main_panel);
		gl_main_panel.setHorizontalGroup(
			gl_main_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_main_panel.createSequentialGroup()
					.addGroup(gl_main_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_main_panel.createSequentialGroup()
							.addGap(166)
							.addComponent(yourAccountTitle, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_main_panel.createSequentialGroup()
							.addGroup(gl_main_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_main_panel.createSequentialGroup()
									.addGap(50)
									.addComponent(label, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_main_panel.createSequentialGroup()
									.addGap(29)
									.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)))
							.addGap(48)
							.addGroup(gl_main_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(productsPurchasedTitle, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
								.addComponent(productsPurchasedTextArea, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_main_panel.createSequentialGroup()
							.addGap(20)
							.addComponent(productsAwatingTitle, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE)
							.addGap(72)
							.addComponent(yourDetailsLabel))
						.addGroup(gl_main_panel.createSequentialGroup()
							.addGap(31)
							.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
							.addGap(44)
							.addGroup(gl_main_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(emailLabel, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
								.addComponent(balanceLabel, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
								.addComponent(nameLable, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_main_panel.createSequentialGroup()
									.addComponent(promoCode, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(confirmVoucherBtn)))))
					.addContainerGap(34, Short.MAX_VALUE))
		);
		gl_main_panel.setVerticalGroup(
			gl_main_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_main_panel.createSequentialGroup()
					.addGroup(gl_main_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_main_panel.createSequentialGroup()
							.addComponent(yourAccountTitle, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(productsPurchasedTitle, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_main_panel.createSequentialGroup()
							.addGap(62)
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)))
					.addGap(7)
					.addGroup(gl_main_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
						.addComponent(productsPurchasedTextArea, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_main_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(productsAwatingTitle, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(yourDetailsLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_main_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_main_panel.createSequentialGroup()
							.addGap(18)
							.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_main_panel.createSequentialGroup()
							.addGap(4)
							.addComponent(nameLable, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(balanceLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(emailLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(gl_main_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(promoCode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(confirmVoucherBtn))))
					.addContainerGap(32, Short.MAX_VALUE))
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
