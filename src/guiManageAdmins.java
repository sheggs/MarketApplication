import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class guiManageAdmins {
	JPanel temp = new JPanel();
	private Admin admin = null;
	public guiManageAdmins(Admin admin) {
		this.admin = admin;
	}
	public void addAdmin(Login login,User user,JPanel jb) {
		temp.setLayout(new GridLayout(0,1));
		JButton btn = new JButton("Manage Admin");
		btn.setAlignmentX(Component.LEFT_ALIGNMENT);
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				manageAdminPanel(admin,user);
			}
		});
		JLabel l = new JLabel(" Name: " +user.getUsername() + " Email: " + user.getEmail());
		l.setAlignmentX(Component.CENTER_ALIGNMENT);
		temp.add(l);
		temp.add(btn);
		jb.add(temp);
	}
	
	public GroupLayout setSidePanel(Login login,JFrame frame,JPanel panel,JPanel main_panel) {
		JLabel manageUserTitle = new JLabel("Manage Users");
		manageUserTitle.setFont(new Font("Tahoma", Font.PLAIN, 26));
		
		
		GroupLayout gl_main_panel = new GroupLayout(main_panel);
		/** Panel is a container for the user management **/
		JPanel containerPanel = new JPanel();
		
		for(Admin user : ManageUsers.getTotalAdmins()) {
			/** Appending each product data **/
			if(user.getPrivilidgeLevel() < admin.getPrivilidgeLevel()) {
				addAdmin(login,user,containerPanel);

			}
		}
		JScrollPane scrollPane = new JScrollPane(containerPanel);
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
		

		gl_main_panel.setHorizontalGroup(
			gl_main_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_main_panel.createSequentialGroup()
					.addContainerGap(176, Short.MAX_VALUE)
					.addComponent(manageUserTitle, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)
					.addGap(146))
				.addGroup(Alignment.LEADING, gl_main_panel.createSequentialGroup()
					.addGap(35)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 469, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(30, Short.MAX_VALUE))
		);
		gl_main_panel.setVerticalGroup(
			gl_main_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_main_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(manageUserTitle, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		return gl_main_panel;
	
	}
	
	
	public void manageAdminPanel(Admin admin,User user) {
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setTitle("MarketPlace - Manage User Account");
		frame.setBounds(100, 100, 723, 366);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	
		JLabel lblNewLabel = new JLabel("Managing "+user.getUsername()+" Account");
		JTextField nameTextField = new JTextField();
		JTextField accountBalanceTextField = new JTextField();
		JTextField emailTextField = new JTextField();
		JTextField passwordField = new JTextField();
		JTextField privilidgeLevel = new JTextField();
		JButton btnSubmitForm = new JButton("Submit Form");
		JButton banUnbanButton = new JButton("Ban");
		JButton btnPromoteToAdmin = new JButton("Promote to Admin");

		/** Setting banned text **/
		if(user.isBanned()) {
			banUnbanButton.setText("Unban");
		}
		/** When the admin wants to update the users information **/
		btnSubmitForm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = nameTextField.getText();
				String accountBalance= accountBalanceTextField.getText();
				String email = emailTextField.getText();
				String password = passwordField.getText();
				DatabaseHandlerHSQL db = DatabaseHandlerHSQL.getDatabase();
				//if((accountBalance.matches("[0-9].*[0-9]+")) && (!db.checkExistanceInDB("useraccount", "username", name)) && (!db.checkExistanceInDB("useraccount", "email", email))){
					
				//}
				/** String stores all the success messages and errors **/
				StringBuffer errors = new StringBuffer();
				/** Checking if balance string is correctly defined if so the balance will update **/
				if(accountBalance.matches("[0-9].*[0-9]+")) {
					user.updateBalance(-user.getCurrentBalance() + Double.parseDouble(accountBalance));
					errors.append("Updated account balance \n");
				}else {
					/** Appending an error message to show the user **/
					errors.append("Error updating account balance \n");

				}
				/** Checking if the username is not taken! **/
				if(!db.checkExistanceInDB("useraccount", "username", name)) {
					/** Updating username **/
					db.executeQuery("UPDATE FROM useraccount SET username = '"+name +"' WHERE user_id = '"+user.getUserID()+"'");
					errors.append("Updated username \n");
				}else {
					/** Appending an error message to show the user **/
					errors.append("Error updating username \n");
				}
				/** Checking if the email is not taken! **/
				if(!db.checkExistanceInDB("useraccount", "email", email)) {
					/** Updating email **/
					db.executeQuery("UPDATE FROM useraccount SET email = '"+email +"' WHERE user_id = '"+user.getUserID()+"'");
					errors.append("Updated email \n");

				}else {
					/** Appending an error message to show the user **/
					errors.append("Error updating email \n");
				}
				
				JOptionPane.showMessageDialog(new JFrame(), errors.toString(), "Messages after updating", JOptionPane.ERROR_MESSAGE);
				frame.dispose();
				
				
			}
		});
		/** When the user presses the unban/ban button **/
		banUnbanButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String message = "banned";
				/** Checking if the user is banned**/
				if(user.isBanned()) {
					message = "unbanned";
					user.unBanUser();
				}else {
					user.banUser();
				}
				JOptionPane.showMessageDialog(new JFrame(), "You have " + message + " the user.", "Messages after updating", JOptionPane.ERROR_MESSAGE);
				frame.dispose();

			}
		});
		/** Making the user into an admin **/
		btnPromoteToAdmin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				/** Check if privilidge level only contains numbers **/
				if(privilidgeLevel.getText().matches("[0-9]+")) {
					if(DatabaseHandlerHSQL.getDatabase().upgradeAccount(user, admin, Integer.parseInt(privilidgeLevel.getText()))) {
						JOptionPane.showMessageDialog(new JFrame(), "SUCCESS! USER IS NOW AN ADMIN", "Messages after updating", JOptionPane.PLAIN_MESSAGE);
						frame.dispose();
					}else {
						JOptionPane.showMessageDialog(new JFrame(), "ERROR: Target privilidge level must be lower than yours! Or a database error has occured!", "Upgrade message", JOptionPane.ERROR_MESSAGE);
					}
				}else{
					JOptionPane.showMessageDialog(new JFrame(), "ERROR: Your privilidge level is incorrectly formatted", "Upgrade message", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		passwordField.setText(user.getPassword());
		emailTextField.setText(user.getEmail());
		accountBalanceTextField.setText(""+user.getCurrentBalance());
		nameTextField.setText(user.getUsername());
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(254, 11, 187, 32);
		frame.getContentPane().add(lblNewLabel);
		
		nameTextField.setBounds(202, 86, 114, 20);
		frame.getContentPane().add(nameTextField);
		nameTextField.setColumns(10);
		
		JLabel usernameTitle = new JLabel("Username");
		usernameTitle.setFont(new Font("Tahoma", Font.PLAIN, 13));
		usernameTitle.setBounds(122, 88, 70, 14);
		frame.getContentPane().add(usernameTitle);
		
		JLabel balanceLabel = new JLabel("Account Balance:");
		balanceLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		balanceLabel.setBounds(91, 120, 114, 14);
		frame.getContentPane().add(balanceLabel);
		
		accountBalanceTextField.setColumns(10);
		accountBalanceTextField.setBounds(202, 118, 114, 20);
		frame.getContentPane().add(accountBalanceTextField);
		
		JLabel emailLabel = new JLabel("Email:");
		emailLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		emailLabel.setBounds(146, 145, 46, 14);
		frame.getContentPane().add(emailLabel);
		
		emailTextField.setColumns(10);
		emailTextField.setBounds(202, 145, 114, 20);
		frame.getContentPane().add(emailTextField);
		
		JLabel passwordlabel = new JLabel("Password:");
		passwordlabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		passwordlabel.setBounds(122, 177, 70, 14);
		frame.getContentPane().add(passwordlabel);
		
		passwordField.setColumns(10);
		passwordField.setBounds(202, 177, 114, 20);
		frame.getContentPane().add(passwordField);
		
		banUnbanButton.setBounds(391, 86, 126, 23);
		frame.getContentPane().add(banUnbanButton);
		
		btnPromoteToAdmin.setBounds(391, 124, 126, 23);
		frame.getContentPane().add(btnPromoteToAdmin);
		
		btnSubmitForm.setBounds(202, 225, 126, 23);
		frame.getContentPane().add(btnSubmitForm);
		
		privilidgeLevel.setText("Privilidge Level");
		privilidgeLevel.setColumns(10);
		privilidgeLevel.setBounds(527, 125, 114, 20);
		frame.getContentPane().add(privilidgeLevel);
	}

}
