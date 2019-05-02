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
	private static guiMainPanel window = null;
	private static Login login = null;
	private static PanelType panelType = null;
	JFrame frame = null;
	JPanel panel = null;
	JPanel main_panel = null;
	public guiMainPanel(Login login) {
		guiMainPanel.login = login;
		this.frame = this;
		this.setVisible(true);
		frame.setBounds(100, 100, 782, 595);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();
		panel.setBackground(SystemColor.textHighlight);
		
		this.main_panel = new JPanel();
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
				buyItemsPanel();
			}
		});		
		JButton btnSellItems = new JButton("Sell Items");
		btnSellItems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sellProductPanel();
			}
		});
		
		JButton btnMyAccount = new JButton("My Account");
		btnMyAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myAccountPanel();

			}
		});		
		JButton btnManageUsers = new JButton("Manage Users");
		btnManageUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manageUsersPanel();

			}
		});
		
		JButton btnManageAdmins = new JButton("Manage Admins");
		btnManageAdmins.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manageAdminsPanel();
			}
		});
	
		JButton btnGenerateCode = new JButton("Generate Code");
		btnGenerateCode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generateCodePanel();
			}
		});
		
		JButton btnApproveProducts = new JButton("Approve Product");
		btnApproveProducts.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnApproveProducts.setActionCommand("");
		btnApproveProducts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				approveProductPanel();
			}
		});
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
	public static guiMainPanel getFrame(Login login) {
		if(window == null) {
			window = new guiMainPanel(login);
		}
		return window;
	}
	public void buyItemsPanel() {
		/** Setting the panel type just incase for refreshes **/
		panelType = PanelType.BUY_ITEMS;
		System.out.println(panelType);
		main_panel.removeAll();
		main_panel.setLayout(new BoxLayout(main_panel, BoxLayout.X_AXIS));
		new guiProductListing(login).setPanel(main_panel);
		main_panel.setVisible(false);
		main_panel.setVisible(true);
	}
	public void sellProductPanel() {
		/** Setting the panel type just incase for refreshes **/
		panelType = PanelType.SELL_ITEMS;
		main_panel.removeAll();
		main_panel.setLayout(null);
		main_panel.setLayout(new guiRegisterProduct().setSecondaryPanel(login,frame, panel, this.main_panel));
	}
	public void myAccountPanel() {
		/** Setting the panel type just incase for refreshes **/
		this.panelType = PanelType.MY_ACCOUNT;
		main_panel.removeAll();
		main_panel.setLayout(null);
		main_panel.setLayout(new guiYourAccount().setSidePanel(login,frame, panel,this.main_panel));
	}
	public void manageUsersPanel() {
		/** Setting the panel type just incase for refreshes **/
		panelType = PanelType.MANAGE_USERS;
		main_panel.removeAll();
		main_panel.setLayout(null);
		main_panel.setLayout(new guiManageUsers(login.getAdmin()).setSidePanel(login,frame, panel, this.main_panel));
	}
	public void manageAdminsPanel() {
		/** Setting the panel type just incase for refreshes **/
		panelType = PanelType.MANAGE_ADMINS;
		main_panel.removeAll();
		main_panel.setLayout(null);
		main_panel.setLayout(new guiManageAdmins(login.getAdmin()).setSidePanel(login,frame, panel, this.main_panel));
	}
	public void generateCodePanel() {
		/** Setting the panel type just incase for refreshes **/
		panelType = PanelType.GENERATE_CODE;
		main_panel.removeAll();
		main_panel.setLayout(null);
		main_panel.setLayout(new guiCodeGenerate().setSidePanel(login,frame, panel, this.main_panel));
	}
	public void approveProductPanel() {
		/** Setting the panel type just incase for refreshes **/
		panelType = PanelType.APPROVE_PRODUCT;
		main_panel.removeAll();
		main_panel.setLayout(null);
		main_panel.setLayout(new guiApproveProducts(login.getAdmin()).setSidePanel(login,frame, panel, this.main_panel));
	}
	public PanelType getPanelType() {
		return panelType;
	}
	public void refreshPanel() {
			System.out.println(login);
		if(this.panelType == PanelType.APPROVE_PRODUCT) {
			main_panel.setLayout(new guiManageAdmins(login.getAdmin()).setSidePanel(login,frame, panel, this.main_panel));

		}
		else if(this.panelType == PanelType.BUY_ITEMS) {	
			guiMainPanel grabFrame = guiMainPanel.getFrame(login);
			grabFrame.buyItemsPanel();
		}
		else if(this.panelType == PanelType.GENERATE_CODE) {
			guiMainPanel grabFrame = guiMainPanel.getFrame(login);
			grabFrame.generateCodePanel();
		}
		else if(this.panelType == PanelType. MANAGE_ADMINS) {
			guiMainPanel grabFrame = guiMainPanel.getFrame(login);
			grabFrame.manageAdminsPanel();
		}
				
		else if(this.panelType == PanelType.MANAGE_USERS) {
			guiMainPanel grabFrame = guiMainPanel.getFrame(login);
			grabFrame.manageUsersPanel();
		}
		else if(this.panelType == PanelType.MY_ACCOUNT) {
			guiMainPanel grabFrame = guiMainPanel.getFrame(login);
			grabFrame.myAccountPanel();
		}
		else if(this.panelType == PanelType.SELL_ITEMS) {
			guiMainPanel grabFrame = guiMainPanel.getFrame(login);
			grabFrame.sellProductPanel();
		}
		
	}

}
