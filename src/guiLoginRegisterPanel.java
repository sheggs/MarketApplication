import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

public class guiLoginRegisterPanel {
	public guiLoginRegisterPanel( ) {
	}
	
	
	public GroupLayout setSecondaryPanel(JFrame frame,JPanel panel,JPanel main_panel) {
		JTextField txtUsername = new JTextField();
		txtUsername.setText("Username");
		txtUsername.setColumns(10);
		
		JTextField txtPassword = new JTextField();
		txtPassword.setText("Password");
		txtPassword.setColumns(10);
		
		JTextField txtEmail = new JTextField();
		txtEmail.setText("E-Mail");
		txtEmail.setColumns(10);
		
		JTextField email_login = new JTextField();
		email_login.setText("E-Mail");
		email_login.setColumns(10);
		
		JTextField passwordLogin = new JTextField();
		passwordLogin.setText("Password");
		passwordLogin.setColumns(10);
		
		JButton btnLogin = new JButton("login");
		btnLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				/** Creating an login object for the user **/
				Login login = new Login(email_login.getText(),passwordLogin.getText());
				/** Check if the login credentials are correct **/
				if(login.checkCredentials()) {
					/** Disposing old frame and creating a new frame with the user logged in **/
					if(login.getUser().isBanned()) {
						JOptionPane.showMessageDialog(new JFrame(),"You are banned from this market!", "Marketplace - Message",JOptionPane.ERROR_MESSAGE);
					}else {
						frame.dispose();
						new guiMainPanel(login);
					}
				}else {
					JOptionPane.showMessageDialog(new JFrame(),"Error login credentials incorrect!", "Marketplace - Message",JOptionPane.PLAIN_MESSAGE);

				}
			}
		});
		
		JButton registerButton = new JButton("register");
		registerButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String email = txtEmail.getText();
				String password =txtPassword.getText();
				String username = txtUsername.getText();
				/** Creating the user account **/
				if(DatabaseHandlerHSQL.getDatabase().createAccount(username, email, password)) {
					JOptionPane.showMessageDialog(new JFrame(),"Succesful account creation", "Marketplace - Message",JOptionPane.PLAIN_MESSAGE);
					
				}else {
					JOptionPane.showMessageDialog(new JFrame(),"Error credentials have already been used!", "Marketplace - Message",JOptionPane.ERROR_MESSAGE);

				}
				
				
			}
		});
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

		
		JLabel lblRegisterAccount = new JLabel("Register Account");
		lblRegisterAccount.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblLoginAccount = new JLabel("Login Account");
		lblLoginAccount.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GroupLayout gl_main_panel = new GroupLayout(main_panel);
		gl_main_panel.setHorizontalGroup(
			gl_main_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_main_panel.createSequentialGroup()
					.addGroup(gl_main_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_main_panel.createSequentialGroup()
							.addGap(41)
							.addComponent(registerButton))
						.addGroup(gl_main_panel.createSequentialGroup()
							.addGap(23)
							.addGroup(gl_main_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_main_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblRegisterAccount, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)))
					.addGroup(gl_main_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_main_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
							.addGroup(gl_main_panel.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_main_panel.createSequentialGroup()
									.addComponent(lblLoginAccount, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)
									.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(Alignment.TRAILING, gl_main_panel.createSequentialGroup()
									.addGroup(gl_main_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(email_login, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
										.addComponent(passwordLogin, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE))
									.addGap(97))))
						.addGroup(gl_main_panel.createSequentialGroup()
							.addGap(123)
							.addComponent(btnLogin)
							.addContainerGap())))
		);
		gl_main_panel.setVerticalGroup(
			gl_main_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_main_panel.createSequentialGroup()
					.addGap(145)
					.addGroup(gl_main_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRegisterAccount, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblLoginAccount, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_main_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_main_panel.createSequentialGroup()
							.addComponent(email_login, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(passwordLogin, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnLogin))
						.addGroup(gl_main_panel.createSequentialGroup()
							.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(registerButton))))
		);
		return gl_main_panel;

	}
	
}


