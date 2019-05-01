import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class guiMainPanel extends JFrame{
	
	private Login login = null;
	
	public guiMainPanel(Login login) {
		this.login = login;
		JFrame frame = this;
		this.setVisible(true);
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
		JLabel lblMarketplace = new JLabel("MarketPlace");
		lblMarketplace.setForeground(SystemColor.window);
		lblMarketplace.setFont(new Font("Tahoma", Font.PLAIN, 38));
		if(login == null) {
			main_panel.setLayout(new guiLoginRegisterPanel().setSecondaryPanel(frame, panel, main_panel));
		}else {
		
		
		JButton btnBuyItems = new JButton("Buy Items");
		btnBuyItems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main_panel.removeAll();
				main_panel.setLayout(new BoxLayout(main_panel, BoxLayout.X_AXIS));
				new guiProductListing(login).setPanel(main_panel);
				main_panel.setVisible(false);
				main_panel.setVisible(true);
			}
		});		
		JButton btnSellItems = new JButton("Sell Items");
		btnSellItems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main_panel.removeAll();
				main_panel.setLayout(null);

				main_panel.setLayout(new guiRegisterProduct().setSecondaryPanel(login,frame, panel, main_panel));

			}
		});
		
		JButton btnMyAccount = new JButton("My Account");
		btnMyAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main_panel.removeAll();
				main_panel.setLayout(null);

				main_panel.setLayout(new guiYourAccount().setSidePanel(login,frame, panel, main_panel));

			}
		});		
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
				main_panel.removeAll();
				main_panel.setLayout(null);

				main_panel.setLayout(new guiCodeGenerate().setSidePanel(login,frame, panel, main_panel));
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
		if(login.getAdmin() == null) {
			btnManageUsers.setVisible(false);
			btnManageAdmins.setVisible(false);
			btnGenerateCode.setVisible(false);
			btnApproveProducts.setVisible(false);


		}
		panel.setLayout(gl_panel);
		}
		frame.getContentPane().setLayout(groupLayout);

	}
	public static void main(String[] args) {
		new guiMainPanel(null);
	}

}
