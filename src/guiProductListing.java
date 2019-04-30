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
	private Login login = null;
	public guiProductListing(Login login) {
		this.login = login;
	}
	public void setPanel(JPanel pane) {
		JPanel panel = pane;
		 JPanel contianerProducts = new JPanel();
		 contianerProducts.setLayout(new GridLayout(0,1));
		 JButton basket = new JButton("Basket");
		 basket.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				pane.removeAll();
				pane.setLayout(null);
				pane.setLayout(new guiBasket(login).setSecondaryPanel(panel));				
			}
		});
		 pane.add(basket);
		 for(Products p : Products.getProducts()) {
			 addProduct(p,pane,contianerProducts);

		 }
		 JScrollPane  xs = new JScrollPane (contianerProducts);

		 pane.add(xs);
	      
	}
	public void addProduct(Products product,Container pane,JPanel jb) {
		JPanel temp = new JPanel();
		JButton btn = new JButton("Purchase Now!");
		btn.setAlignmentX(Component.LEFT_ALIGNMENT);
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(login.getUser().getBasket().addItem(product)) {
					JOptionPane.showMessageDialog(new JFrame(),product.getName() + " has been added to your basket!", "Marketplace - Message",JOptionPane.PLAIN_MESSAGE);

				}else {
					JOptionPane.showMessageDialog(new JFrame(),product.getName() + " has been already been added to your basket or an error has occured!", "Marketplace - Message",JOptionPane.ERROR_MESSAGE);

				}
			}
		});
		JLabel l = new JLabel(product.getName() + " Desc: " + product.getDescription() + " Price £" +product.getPrice());
		l.setAlignmentX(Component.CENTER_ALIGNMENT);
		temp.add(l);
		temp.add(btn);
		jb.add(temp);
	}
	
}


