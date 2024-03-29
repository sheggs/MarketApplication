import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import net.miginfocom.layout.Grid;

public class guiYourAccount {
	JPanel temp = new JPanel();
	public guiYourAccount() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @param login The login of the user
	 * @param product The product the user has ptu on the market
	 * @param tempPanel Temporary panel that stores the product informations.
	 */
	public void addProduct(Login login,Products product,JPanel tempPanel) {
		/** Setting the gridlayout **/
		temp.setLayout(new GridLayout(0,1));
		/** Creating a button to remove the product **/
		JButton btn = new JButton("Remove product");
		btn.setAlignmentX(Component.LEFT_ALIGNMENT);
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				/** Removing the product **/
				if(product.removeProduct()) {
					/** Success message is displayed **/
					JOptionPane.showMessageDialog(new JFrame(), "Product has been removed from the market!", "MarketPlace",JOptionPane.PLAIN_MESSAGE);
				}else {
					/** Error message is displayed **/
					JOptionPane.showMessageDialog(new JFrame(), "Unknown Error", "Error",JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		/** Label to show the details about the product **/
		JLabel l = new JLabel(product.getName() + " Desc: " + product.getDescription() + " Price �" +product.getPrice());
		l.setAlignmentX(Component.CENTER_ALIGNMENT);
		temp.add(l);
		temp.add(btn);
		tempPanel.add(temp);
	}
	/**
	 * 
	 * @param login The login object of the currently logged in admin/user.
	 * @param frame The frame of the application.
	 * @param panel Temporary panel.
	 * @param main_panel The panel that stores all the components.
	 * @return
	 */
	public GroupLayout setSidePanel(Login login,JFrame frame,JPanel panel,JPanel main_panel) {
		/** Initialising the components **/
		JTextField promoCode = new JTextField();
		JButton confirmVoucherBtn = new JButton("Confirm");
		JLabel yourDetailsLabel = new JLabel("Your Details");	
		JLabel nameLable = new JLabel("Name: " + login.getUser().getUsername());
		JLabel balanceLabel = new JLabel("Balance: �"+login.getUser().getCurrentBalance());
		JLabel emailLabel = new JLabel("Email: " + login.getUser().getEmail());
		
		JTextArea productsPurchasedTextArea = new JTextArea();
		JTextArea productsWaitingApprovalAndSold = new JTextArea();
		/** When the confirm top up voucher button is pressed **/
		confirmVoucherBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PromotionalVoucherCodes topUp = new PromotionalVoucherCodes();
				// Apply the top up code
				if(topUp.applyTopUpCode(login,promoCode.getText())){
					/** Show a success message and refresh the balance **/
					JOptionPane.showMessageDialog(new JFrame(), "Successful application. Your balance should be updated", "TopUp Voucher",JOptionPane.PLAIN_MESSAGE);
					balanceLabel.setText("Balance: �"+login.getUser().getCurrentBalance());
				}
				// If there is an issue with the code.
				else {
					/** Show an error message **/
					JOptionPane.showMessageDialog(new JFrame(),"Code has been used or is invalid." , "TopUp Voucher",JOptionPane.ERROR_MESSAGE);

				}
			}
		});

		/** Getting details of the users purchase and selling history **/
		YourAccount getDetails = new YourAccount(login);
		/** getting the purchased products list **/
		ArrayList<Products> productsPurchased = getDetails.getProductsPurchased();
		/** Getting a list of all the products that belong to the user that is awaiting approval **/
		ArrayList<Products> productsWaitingApproval = getDetails.getProductsAwaitingApproval();
		/** Getting a list of all the products that belong to the user that is on the market**/
		ArrayList<Products> productsOnMarket = getDetails.getProductsOnMarket();
		/** Getting a list of all the products that belong to the user has been sold **/
		ArrayList<Products> productsSold = getDetails.getProductsSold();

		/** Initialising stringbuffer to store the text data **/
		StringBuffer listOfProducts = new StringBuffer();
		/** Looping through all the products inside the ArrayList **/

		for(Products p : productsPurchased) {
			/** Appending each product data **/
			listOfProducts.append(p.toString() + "\n");
		}
		productsPurchasedTextArea.setAutoscrolls(true);
		/** Setting the text area with the text from the string buffer **/
		productsPurchasedTextArea.setText(listOfProducts.toString());
		/** Panel is a container for the product listing **/
		JPanel containerPanel = new JPanel();
		/** Initialising stringbuffer to store the text data **/
		StringBuffer totalText = new StringBuffer();
		StringBuffer listOfProductsAwaitingApproval = new StringBuffer();
		StringBuffer listOfProductsSold = new StringBuffer();

		/** Looping through all the products inside the ArrayList **/
		for(Products p : productsWaitingApproval) {
			/** Appending each product data **/
			listOfProductsAwaitingApproval.append(p.toString() + "\n");
		}
		for(Products p : productsSold) {
			/** Appending each product data **/
			listOfProductsSold.append(p.toString() + "\n");
		}
		productsWaitingApprovalAndSold.setAutoscrolls(true);
		/** Setting the text area with the text from the string buffer **/
		productsWaitingApprovalAndSold.setText("Sold Products: \n" + listOfProductsAwaitingApproval.toString() + "\n" + "Products: Awaiting approval \n" + listOfProductsSold.toString());
		
		
		for(Products p : productsOnMarket) {
			/** Appending each product data **/
			 addProduct(login,p,containerPanel);
		}
		/** Setting up the scroll pane in the MarketPlace to show the containerPanel with options to scroll **/
		JScrollPane productsInMarketPanel = new JScrollPane(containerPanel);

		 //productsInMarketPanel.add(scrollPaneForProducts);
		 //
		JScrollPane productsPurchasedScroll = new JScrollPane(productsPurchasedTextArea);
		JScrollPane productAwaitngApprovalScroll = new JScrollPane(productsWaitingApprovalAndSold);
		
		
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
		/** Filling out the titles and labels **/
		JLabel yourAccountTitle = new JLabel("Your account");
		yourAccountTitle.setFont(new Font("Tahoma", Font.PLAIN, 26));
		
		JLabel label = new JLabel("Products on market");
		label.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		
		JLabel productsPurchasedTitle = new JLabel("Purchased Products");
		productsPurchasedTitle.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		//productsPurchasedTextArea.setEditable(false);
		
		JLabel productsAwatingTitle = new JLabel("Products awaiting apporval and sold");
		productsAwatingTitle.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		//productAwaitngApprovalTA.setEditable(false);
		productsWaitingApprovalAndSold.setEditable(false);

		yourDetailsLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));

		nameLable.setFont(new Font("Tahoma", Font.PLAIN, 17));

		balanceLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));

		emailLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		promoCode.setText("Top Up Voucher");
		promoCode.setColumns(10);

		
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
									.addComponent(productsInMarketPanel, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)))
							.addGap(48)
							.addGroup(gl_main_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(productsPurchasedTitle, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
								.addComponent(productsPurchasedScroll, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_main_panel.createSequentialGroup()
							.addGap(20)
							.addComponent(productsAwatingTitle, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE)
							.addGap(72)
							.addComponent(yourDetailsLabel))
						.addGroup(gl_main_panel.createSequentialGroup()
							.addGap(31)
							.addComponent(productAwaitngApprovalScroll, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
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
						.addComponent(productsInMarketPanel, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
						.addComponent(productsPurchasedScroll, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_main_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(productsAwatingTitle, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(yourDetailsLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_main_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_main_panel.createSequentialGroup()
							.addGap(18)
							.addComponent(productAwaitngApprovalScroll, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
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
		return gl_main_panel;
	}

}
