import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JList;
import javax.swing.JScrollPane;
import java.awt.Canvas;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

public class testingEnv {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					testingEnv window = new testingEnv();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public testingEnv() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblConfirmPurchase = new JLabel("CONFIRM PURCHASE");
		lblConfirmPurchase.setFont(new Font("Tahoma", Font.PLAIN, 32));
		
		JLabel lblNewLabel = new JLabel("Basket Price: +$3000");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblPromotionalCode = new JLabel("Promotional Code: -$3000");
		lblPromotionalCode.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblTotalPrice = new JLabel("Total Price: $0");
		lblTotalPrice.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JButton btnNewButton = new JButton("Purchase");
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
						.addComponent(lblNewLabel))
					.addContainerGap(313, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(71)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 432, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(98, Short.MAX_VALUE))
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
					.addComponent(lblTotalPrice, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
					.addComponent(btnNewButton)
					.addGap(35))
		);
		frame.getContentPane().setLayout(groupLayout);
	
	}
}
