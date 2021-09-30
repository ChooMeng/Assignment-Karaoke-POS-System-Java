package group5;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login {
	private String username;
	private String password;
	private static JLabel labelName;
	private static JTextField inputName;
	private static JLabel labelPassword;
	private static JPasswordField inputPassword;
	private static SalesPerson authAccount;
	public Login() {
	}
	public static SalesPerson getAuthAccount() {
		return authAccount;
	}
	public static void setAuthAccount(SalesPerson authAccount) {
		Login.authAccount = authAccount;
	}
	@SuppressWarnings("deprecation")
	public void loginGui() {
		JPanel panel = new JPanel();
		//UserName settings
		labelName = new JLabel("Username: ");
		labelName.setFont(new Font("Times New Roman", Font.BOLD, 24));
		inputName = new JTextField(16);
		inputName.setFont(new Font("Arial",Font.ITALIC, 20));
		//Password
		labelPassword = new JLabel("Password: ");
		labelPassword.setFont(new Font("Times New Roman", Font.BOLD, 24));
		labelPassword.createToolTip();
		labelPassword.setToolTipText("Enter your staff account password");
		
		inputPassword = new JPasswordField(16);
		inputPassword.setFont(new Font("Arial",Font.ITALIC, 20));
		panel.add(labelName);
		panel.add(inputName);
		panel.add(labelPassword);
		panel.add(inputPassword);
		int returnType = JOptionPane.showConfirmDialog(null,panel,"Login", JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE);
		if (returnType == 2) {
			JPanel panel2 = new JPanel();
			JLabel ask = new JLabel("Are you sure you want to shutdown?");
			ask.setFont(new Font("Times New Roman", Font.BOLD, 22));
			panel2.add(ask);
			int confirmType = JOptionPane.showConfirmDialog(null,panel2,"Close Confirmation",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
			if (confirmType == 0) {
				System.out.printf("\t\t************************\n");
				System.out.printf("\t\t>    SYSTEM STOPPED    <\n");
				System.out.printf("\t\t************************\n");
				System.exit(0);
			}
		}
		username = inputName.getText();
		password = inputPassword.getText();
	}
	public boolean validation() {
		for (int i = 0;i < SalesPerson.getTotalSalesPerson();i++) {
			if (username.equals(SalesPerson.getSalesPersonList()[i].getSalesUserName())) {
				if (password.equals(SalesPerson.getSalesPersonList()[i].getSalesPassword())) {
					Login.setAuthAccount(SalesPerson.getSalesPersonList()[i]);
					return true;
				}
			}
		}
		return false;
	}
	public void displayWelcome() {
		JPanel panel = new JPanel();
		JLabel welcome = new JLabel("Succesful Login!");
		welcome.setFont(new Font("Times New Roman", Font.BOLD, 22));
		panel.add(welcome);
		JOptionPane.showMessageDialog(null,welcome,"Succesful Login",JOptionPane.INFORMATION_MESSAGE);
	}
	public void displayWrong() {
		JPanel panel = new JPanel();
		JLabel fail = new JLabel("Wrong password or Username!");
		fail.setFont(new Font("Times New Roman", Font.BOLD, 22));
		panel.add(fail);
		JOptionPane.showMessageDialog(null,fail,"Failed Login",JOptionPane.ERROR_MESSAGE);
	}
}
