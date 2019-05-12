import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class guiCodeGenerate {
	
	public guiCodeGenerate() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @param login The login object of the currently logged in admin/user.
	 * @param frame The frame of the application.
	 * @param panel Temporary panel.
	 * @param main_panel The panel that stores all the components.
	 * @return
	 */
	public GroupLayout setSidePanel(Login login, JFrame frame,JPanel panel,JPanel main_panel) {
		/**
		 * Initialising the variables needed, and creating the visual components.
		 */
		JLabel generateCodeTitle = new JLabel("Generate Codes");
		JTextField txtamount = new JTextField();
		JTextField txtPrecentageOff = new JTextField();
		JButton confirmPromoCode = new JButton("Confirm");
		JButton confirmVoucherBtn = new JButton("Confirm");
		JTextField generatedCode = new JTextField();
		generatedCode.setEditable(false);
		JLabel lblTopupVoucher = new JLabel("Top-Up Voucher");
		lblTopupVoucher.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblPromotionalCode = new JLabel("Promotional Code");
		lblPromotionalCode.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		generatedCode.setText("Generated Code");
		generatedCode.setColumns(10);
		/** When the user presses the generate top up code button **/
		confirmVoucherBtn.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// Checks if the txtamount variables contains numbers only
				if(txtamount.getText().matches("[0-9]+")) {
					/** Creating an promotionalvouchercode object **/
					PromotionalVoucherCodes voucherCode = new PromotionalVoucherCodes();
					/** Generating a top up code **/
					String code = voucherCode.generateCode(Double.parseDouble(txtamount.getText()), login.getAdmin().getAdminID(),CodeType.TOPUP_CODE);
					/** Displaying the code to the user **/
					generatedCode.setText(code);
					/** Creating a frame that tells the user the code has been generated **/
					JOptionPane.showMessageDialog(new JFrame(),"Code generated! Look in the text box", "Marketplace - Message",JOptionPane.PLAIN_MESSAGE);
				}else {
					/** Error message is shown if the value is not a number **/
					JOptionPane.showMessageDialog(new JFrame(),"Only numbers between 0-9. No decimal points.","Marketplace - Message",JOptionPane.ERROR_MESSAGE);

				}
				
			}
		});
		
		
		/** When the user presses the generate promotional code button **/
		confirmPromoCode.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// Checks if the txtPrecentageOff variables contains numbers only
				if(txtPrecentageOff.getText().matches("[0-9]+")) {
					/** Creating an promotionalvouchercode object **/
					PromotionalVoucherCodes voucherCode = new PromotionalVoucherCodes();
					/** Converting the precentage to a double value from a string **/
					Double requestPrecentage = Double.parseDouble(txtPrecentageOff.getText());
					/** Checking if the requested precentage is 100 or less. **/
					if((requestPrecentage <= 100)&&(requestPrecentage > 0)) {
						/** Generating the promotional code **/
						String code = voucherCode.generateCode(requestPrecentage, login.getAdmin().getAdminID(),CodeType.PROMOTIONAL_CODE);
						/** Displaying the code to the user **/
						generatedCode.setText(code);
						/** Creating a frame that tells the user the code has been generated **/
						JOptionPane.showMessageDialog(new JFrame(),"Code generated! Look in the text box", "Marketplace - Message",JOptionPane.PLAIN_MESSAGE);
					}else {
						/** Error message is shown if the precentage is less that 0 or greater than 100 **/
						JOptionPane.showMessageDialog(new JFrame(),"Percentages cannot be greater than 100!","Marketplace - Message",JOptionPane.ERROR_MESSAGE);

					}
				}else {
					/** Error message is shown if the value is not a number **/
					JOptionPane.showMessageDialog(new JFrame(),"Only numbers between 0-9. No decimal points.","Marketplace - Message",JOptionPane.ERROR_MESSAGE);

				}
				
			}
		});
		/**
		 * Creating the grouplayout 
		 */
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
		
		generateCodeTitle.setFont(new Font("Tahoma", Font.PLAIN, 26));
		
		
		txtamount.setText("\u00A3Amount");
		txtamount.setColumns(10);
		
		txtPrecentageOff.setText("Precentage off %");
		txtPrecentageOff.setColumns(10);
		

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
									.addComponent(confirmPromoCode, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)))))
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
						.addComponent(confirmPromoCode, GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
						.addComponent(confirmVoucherBtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(75)
					.addComponent(generatedCode, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addGap(129))
		);
		return gl_main_panel;
		
	}

}
