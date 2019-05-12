import java.awt.Component;
import java.awt.Container;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

public class guiBasket {
	/** Initialising fields **/
	private Login login = null;
	/**
	 * 
	 * @param login The login of the currently logged in user.
	 */
	public guiBasket(Login login ) {
		this.login = login;
	}
	
	/**
	 * 
	 * @param product The product object that the user wants to buy
	 * @param panel The panel that will store the product listing components.
	 */
	public void basketItems(Products product,JPanel panel) {
		JPanel temp = new JPanel();
		/** The label stores the products details and information.**/
		JLabel l = new JLabel(product.getName() + " Desc: " + product.getDescription() + " Price £" +product.getPrice());
		l.setAlignmentX(Component.CENTER_ALIGNMENT);
		temp.add(l);
		panel.add(temp);
	}
	/**
	 * 
	 * @param main_panel The panel that will store all the components.
	 * @return A GroupLayout of the components.
	 */
	public GroupLayout setSecondaryPanel(JPanel main_panel) {
		/** Creating the title **/
		JLabel basketTitle = new JLabel("Shopping Basket");
		basketTitle.setFont(new Font("Tahoma", Font.PLAIN, 26));
		/** Panel stores the items in the basket **/
		JPanel itemsInBasket = new JPanel();
		/** Promotional code section **/
		JTextField promoSection = new JTextField();
		promoSection.setColumns(10);
		JLabel lblInsertPromotionalCode = new JLabel("Insert promotional code");
		
		/** Purchase button to add product to the basket **/
		JButton purchaseButton = new JButton("Purchase");

		JLabel totalPriceLabel = new JLabel("Total: \u00A30");
		totalPriceLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		/** setting up the grouplayout. **/
		GroupLayout gl_main_panel = new GroupLayout(main_panel);
		gl_main_panel.setHorizontalGroup(
			gl_main_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_main_panel.createSequentialGroup()
					.addContainerGap(156, Short.MAX_VALUE)
					.addComponent(basketTitle, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE)
					.addGap(123))
				.addGroup(gl_main_panel.createSequentialGroup()
					.addGap(35)
					.addGroup(gl_main_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(itemsInBasket, GroupLayout.PREFERRED_SIZE, 473, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_main_panel.createSequentialGroup()
							.addGroup(gl_main_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_main_panel.createSequentialGroup()
									.addGap(10)
									.addComponent(lblInsertPromotionalCode))
								.addComponent(promoSection, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(totalPriceLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(purchaseButton, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
							.addGap(40)))
					.addContainerGap(26, Short.MAX_VALUE))
		);
		gl_main_panel.setVerticalGroup(
			gl_main_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_main_panel.createSequentialGroup()
					.addGap(7)
					.addComponent(basketTitle, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(itemsInBasket, GroupLayout.PREFERRED_SIZE, 405, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_main_panel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_main_panel.createSequentialGroup()
							.addGap(4)
							.addComponent(lblInsertPromotionalCode)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(promoSection, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_main_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_main_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(purchaseButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(totalPriceLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		/**
		 * Looping through the items in the basket and displaying them.
		 */
		for(Products p : this.login.getUser().getBasket().getBasket()) {
			basketItems(p, itemsInBasket);
		}
		/** Shows the total price **/
		totalPriceLabel.setText("Total £"+this.login.getUser().getBasket().getTotalPrice());
		purchaseButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				/** Getting the promo code from text box **/
				String promoCode = promoSection.getText();
				// Initalising the field
				double promotionalValue = 0.0;
				PromotionalVoucherCodes promoCodeValidator = new PromotionalVoucherCodes();
				// Checking if code exists and is not in use.
				if((promoCodeValidator.checkIfCodeExists(promoCode,CodeType.PROMOTIONAL_CODE)) && (!promoCodeValidator.checkIfCodeUsed(promoCode, CodeType.PROMOTIONAL_CODE))) {
					promotionalValue = promoCodeValidator.getCodeValue(promoCode, CodeType.PROMOTIONAL_CODE);
				}
	
				// Confirmation Frame
				JFrame frame = new JFrame();
				frame.setResizable(false);
				frame.setSize(607, 296);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				JLabel lblConfirmPurchase = new JLabel("CONFIRM PURCHASE");
				lblConfirmPurchase.setFont(new Font("Tahoma", Font.PLAIN, 32));
				JButton closeButton = new JButton("Close");
				closeButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// Closes Frame;
						frame.dispose();
					}
				});
				/**
				 * Displaying the details of the purchase
				 */
				JLabel lblNewLabel = new JLabel("Basket Price: +£"+login.getUser().getBasket().getTotalPrice());
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
				
				JLabel lblPromotionalCode = new JLabel("Promotional Code: "+promotionalValue+"%");
				lblPromotionalCode.setFont(new Font("Tahoma", Font.PLAIN, 15));
				
				JLabel accountBalanceLbl = new JLabel("Account Balance: £"+login.getUser().getCurrentBalance());
				accountBalanceLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
				
				JLabel lblTotalPrice = new JLabel("Total Price: £"+((login.getUser().getBasket().getTotalPrice() * (100-promotionalValue))/100));
				lblTotalPrice.setFont(new Font("Tahoma", Font.PLAIN, 15));
				
				JButton purchaseButton = new JButton("Purchase");
				/** Purchases the products **/
				purchaseButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						/** Purchasing basket items **/
						if(login.getUser().purchaseBasket(promoCode)) {
							/** Show a success message in a new frame **/
							JOptionPane.showMessageDialog(new JFrame(),"Success. You now own the items. Your balance is £" +login.getUser().getCurrentBalance(), "Marketplace - Message",JOptionPane.PLAIN_MESSAGE);

						}else {
							/** Show an error message in a new frame **/
							JOptionPane.showMessageDialog(new JFrame(),"A problem occured! Maybe you do not have enough money. Your balance is £"+login.getUser().getCurrentBalance(), "Marketplace - Message",JOptionPane.ERROR_MESSAGE);

						}
						
					}
				});
				GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
				groupLayout.setHorizontalGroup(
					groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap(140, Short.MAX_VALUE)
							.addComponent(lblConfirmPurchase, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE)
							.addGap(120))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addGap(61)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTotalPrice, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPromotionalCode, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE)
								.addComponent(accountBalanceLbl, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE)

								.addComponent(lblNewLabel))
							.addContainerGap(313, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addGap(71)
							.addComponent(purchaseButton, GroupLayout.PREFERRED_SIZE, 432, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(98, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
								.addGap(71)
								.addComponent(closeButton, GroupLayout.PREFERRED_SIZE, 432, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(20, Short.MAX_VALUE))
				);
				groupLayout.setVerticalGroup(
					groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblConfirmPurchase, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblPromotionalCode, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(accountBalanceLbl, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblTotalPrice, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
							.addComponent(purchaseButton)
							.addGap(35)
							.addComponent(closeButton)
							.addGap(35))
				);
				frame.getContentPane().setLayout(groupLayout);
				frame.setVisible(true);
			}
		});
		
		return gl_main_panel;

	}
	
}


