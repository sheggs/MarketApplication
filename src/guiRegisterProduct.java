import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

public class guiRegisterProduct {
	public guiRegisterProduct( ) {
	}
	
	
	public GroupLayout setSecondaryPanel(Login login,JFrame frame,JPanel panel,JPanel main_panel) {
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
			
			
			JTextField txtPrice = new JTextField();
			txtPrice.setText("Asking Price");
			txtPrice.setColumns(10);
			
			JTextField txtDesc = new JTextField();
			txtDesc.setText("Description");
			txtDesc.setColumns(10);
			
			JTextField txtName = new JTextField();
			txtName.setText("Product Name");
			txtName.setColumns(10);
			
			JLabel lblAddProduct = new JLabel("Add Product");
			lblAddProduct.setForeground(Color.BLUE);
			lblAddProduct.setFont(new Font("Tahoma", Font.PLAIN, 38));
			
			JButton submitbutton = new JButton("Submit Product");
			submitbutton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// Initalising database
					DatabaseHandlerHSQL db = DatabaseHandlerHSQL.getDatabase();
					// Getting required variables.
					String username = txtName.getText();
					String description = txtDesc.getText();
					/** Checking if the price is all numbers **/
					if(txtPrice.getText().matches("[0-9]+")) {
						int price = Integer.parseInt(txtPrice.getText());
						db.reestablishConnection();
						Products.registerProduct(login, username,  price, description);
						JOptionPane.showMessageDialog(new JFrame(), "Product is officially on the market.", "Successful.",JOptionPane.INFORMATION_MESSAGE);

					}else {
						// Error message is displayed
						JOptionPane.showMessageDialog(new JFrame(), "Price is not formatted correctly", "Error",JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			GroupLayout gl_main_panel = new GroupLayout(main_panel);
			gl_main_panel.setHorizontalGroup(
				gl_main_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_main_panel.createSequentialGroup()
						.addGroup(gl_main_panel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_main_panel.createSequentialGroup()
								.addGap(23)
								.addComponent(txtName, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
								.addGap(25)
								.addComponent(txtDesc, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(txtPrice, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_main_panel.createSequentialGroup()
								.addGap(202)
								.addComponent(submitbutton)))
						.addContainerGap(54, Short.MAX_VALUE))
					.addGroup(Alignment.TRAILING, gl_main_panel.createSequentialGroup()
						.addContainerGap(142, Short.MAX_VALUE)
						.addComponent(lblAddProduct, GroupLayout.PREFERRED_SIZE, 297, GroupLayout.PREFERRED_SIZE)
						.addGap(95))
			);
			gl_main_panel.setVerticalGroup(
				gl_main_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_main_panel.createSequentialGroup()
						.addGap(67)
						.addComponent(lblAddProduct, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
						.addGap(26)
						.addGroup(gl_main_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(txtName, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
							.addComponent(txtDesc, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
							.addComponent(txtPrice, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
						.addGap(51)
						.addComponent(submitbutton)
						.addGap(313))
			);
		return gl_main_panel;

	}
	
}


