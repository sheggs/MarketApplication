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

public class guiApproveProducts {
	JPanel temp = new JPanel();
	private Admin admin = null;
	public guiApproveProducts(Admin admin) {
		this.admin = admin;
	}
	public void addToListing(Login login,Products product,JPanel jb) {
		temp.setLayout(new GridLayout(0,1));
		JButton btn = new JButton("Approve Product");
		btn.setAlignmentX(Component.LEFT_ALIGNMENT);
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				/** Approve the product **/
				if(product.approveProduct()) {
					/** Succesful message is displayed **/
					JOptionPane.showMessageDialog(new JFrame(), product.getName()+" has been approved", "Messages after approving", JOptionPane.PLAIN_MESSAGE);
					/** Refreshing the panel **/
					guiMainPanel.getFrame(login).refreshPanel();
				}else {
					/** ERROR message is displayed **/
					JOptionPane.showMessageDialog(new JFrame(),"ERROR has occured", "Messages after approving", JOptionPane.ERROR_MESSAGE);

				}
			}
		});
		JLabel l = new JLabel("ASDAS");
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
		
		for(Products products : Products.getUnApprovedProducts()) {
			/** Appending each product data **/
			addToListing(login,products,containerPanel);

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
