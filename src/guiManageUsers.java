import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class guiManageUsers {
	JPanel temp = new JPanel();

	public guiManageUsers() {
		// TODO Auto-generated constructor stub
	}
	public void addUser(Login login,User user,JPanel jb) {
		temp.setLayout(new GridLayout(0,1));
		JButton btn = new JButton("Manage User");
		btn.setAlignmentX(Component.LEFT_ALIGNMENT);
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(new JFrame(), "Product has been removed from the market!", "MarketPlace",JOptionPane.PLAIN_MESSAGE);
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
		
		for(User user : ManageUsers.getTotalUsers()) {
			/** Appending each product data **/
			addUser(login,user,containerPanel);
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

}
