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
	/** Intalising variables **/
	JPanel temp = new JPanel();
	private Admin admin = null;
	/**
	 * 
	 * @param admin The admin that is using the system.
	 */
	public guiApproveProducts(Admin admin) {
		this.admin = admin;
	}
	/**
	 * 
	 * @param login The login object of the admin that is using the system.
	 * @param product The product the admin wants to approve
	 * @param panel The panel that displays the list of items that require approval. 
	 */
	public void addToListing(Login login,Products product,JPanel panel) {
		/** Setting out the grid layout **/
		temp.setLayout(new GridLayout(0,1));
		/** Creating a button, when clicked should approve the product **/
		JButton btn = new JButton("Approve Product");
		btn.setAlignmentX(Component.LEFT_ALIGNMENT);
		/**
		 * When the button is pressed the product should be approved or display an error if one occurs.
		 */
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
		/** Label that shows the products details **/
		JLabel l = new JLabel(product.getName() + " Desc: " + product.getDescription() + " Price £" +product.getPrice());
		l.setAlignmentX(Component.CENTER_ALIGNMENT);
		/** Adding each component to the frame **/
		temp.add(l);
		temp.add(btn);
		panel.add(temp);
	}
	/**
	 * 
	 * @param login The login of the admin using the system.
	 * @param frame The frame of the application.
	 * @param panel The temporary panel 
	 * @param main_panel The panel that stores all the components.
	 * @return
	 */
	public GroupLayout setSidePanel(Login login,JFrame frame,JPanel panel,JPanel main_panel) {
		/**
		 * Creating a title label
		 */
		JLabel manageUserTitle = new JLabel("Manage Users");
		manageUserTitle.setFont(new Font("Tahoma", Font.PLAIN, 26));
		
		/** Creating the group layout **/
		GroupLayout gl_main_panel = new GroupLayout(main_panel);
		/** Panel is a container for the user management **/
		JPanel containerPanel = new JPanel();
		/** Loop through every unapproved product **/
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
