import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
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
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

public class guiProductListing {
	/** Initialising variables **/
	private Login login = null;
	/**
	 * 
	 * @param login The login of the current users.
	 */
	public guiProductListing(Login login) {
		this.login = login;
	}
	/**
	 * 
	 * @param pane The panel you want to add the visual componenets to.
	 */
	public void setPanel(JPanel pane) {
		JPanel panel = pane;
		 JPanel contianerProducts = new JPanel();
		 contianerProducts.setLayout(new GridLayout(0,1));
		 JButton basket = new JButton("Basket");
		 /** This basket button displays the contents of the users basket **/
		 basket.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				/** Clearing the panel **/
				pane.removeAll();
				pane.setLayout(null);
				/** Setting the layout of the panel to show the basket details **/
				pane.setLayout(new guiBasket(login).setSecondaryPanel(panel));				
			}
		});
		 /** adding the basket component to the panel **/
		 pane.add(basket);
		 /** Looping through all the products that have been approved **/
		 for(Products p : Products.getProducts()) {
			 /** Displaying all the products in the listing **/
			 addProduct(p,pane,contianerProducts);

		 }
		 /** Storing the container in a scoll pane to make the panel scrollable **/
		 JScrollPane  xs = new JScrollPane (contianerProducts);

		 pane.add(xs);
	      
	}
	
	/**
	 * 
	 * @param product The product you want to add to the listing.
	 * @param pane The pane that will store the compontents.
	 * @param tempPanel The temporary container panel.
	 */
	public void addProduct(Products product,Container pane,JPanel tempPanel) {
		JPanel temp = new JPanel();
		JButton btn = new JButton("Purchase Now!");
		btn.setAlignmentX(Component.LEFT_ALIGNMENT);
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				/** Adding the item into the basket **/
				if(login.getUser().getBasket().addItem(product)) {
					/** Displaying a success message **/
					JOptionPane.showMessageDialog(new JFrame(),product.getName() + " has been added to your basket!", "Marketplace - Message",JOptionPane.PLAIN_MESSAGE);

				}else {
					/** The product already exists so error message is displayed **/
					JOptionPane.showMessageDialog(new JFrame(),product.getName() + " has been already been added to your basket or an error has occured!", "Marketplace - Message",JOptionPane.ERROR_MESSAGE);

				}
			}
		});
		/** The label that displays the prodducts information. **/
		JLabel l = new JLabel(product.getName() + " Desc: " + product.getDescription() + " Price £" +product.getPrice());
		l.setAlignmentX(Component.CENTER_ALIGNMENT);
		temp.add(l);
		temp.add(btn);
		tempPanel.add(temp);
	}
	
}


