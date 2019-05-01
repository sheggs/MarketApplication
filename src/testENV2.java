import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class testENV2 {

	private JFrame frame;
	private JTextField nameTextField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField;
	private JTextField privilidgeLevel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					testENV2 window = new testENV2();
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
	public testENV2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("MarketPlace - Manage User Account");
		frame.setBounds(100, 100, 723, 366);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Managing {NAME} Account");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(254, 11, 187, 32);
		frame.getContentPane().add(lblNewLabel);
		
		nameTextField = new JTextField();
		nameTextField.setBounds(202, 86, 114, 20);
		frame.getContentPane().add(nameTextField);
		nameTextField.setColumns(10);
		
		JLabel usernameTitle = new JLabel("Username");
		usernameTitle.setFont(new Font("Tahoma", Font.PLAIN, 13));
		usernameTitle.setBounds(122, 88, 70, 14);
		frame.getContentPane().add(usernameTitle);
		
		JLabel balanceLabel = new JLabel("Account Balance:");
		balanceLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		balanceLabel.setBounds(91, 120, 114, 14);
		frame.getContentPane().add(balanceLabel);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(202, 118, 114, 20);
		frame.getContentPane().add(textField_1);
		
		JLabel emailLabel = new JLabel("Email:");
		emailLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		emailLabel.setBounds(146, 145, 46, 14);
		frame.getContentPane().add(emailLabel);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(202, 145, 114, 20);
		frame.getContentPane().add(textField_2);
		
		JLabel passwordlabel = new JLabel("Password:");
		passwordlabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		passwordlabel.setBounds(122, 177, 70, 14);
		frame.getContentPane().add(passwordlabel);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(202, 177, 114, 20);
		frame.getContentPane().add(textField);
		
		JButton banUnbanButton = new JButton("Ban/Unban");
		banUnbanButton.setBounds(391, 86, 126, 23);
		frame.getContentPane().add(banUnbanButton);
		
		JButton btnPromoteToAdmin = new JButton("Promote to Admin");
		btnPromoteToAdmin.setBounds(391, 124, 126, 23);
		frame.getContentPane().add(btnPromoteToAdmin);
		
		JButton btnSubmitForm = new JButton("Submit Form");
		btnSubmitForm.setBounds(202, 225, 126, 23);
		frame.getContentPane().add(btnSubmitForm);
		
		privilidgeLevel = new JTextField();
		privilidgeLevel.setText("Privilidge Level");
		privilidgeLevel.setColumns(10);
		privilidgeLevel.setBounds(527, 125, 114, 20);
		frame.getContentPane().add(privilidgeLevel);
	}
}
