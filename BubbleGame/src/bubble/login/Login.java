package bubble.login;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import bubble.game.BubbleFrame;
public class Login extends JFrame implements ActionListener{
	
	private BubbleFrame bubbleFrame;
	JLabel l1,l2;
	JTextField t1;
	JPasswordField t2;
	JButton loginButton;
	private JButton signUpButton;

	Login(){
		setTitle("로그인");
		setSize(350,200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setResizable(false);
		setLocationRelativeTo(null);
		l1 = new JLabel("아이디: ");
		l1.setBounds(58, 46, 48, 15);
		l2 = new JLabel("비밀번호: ");
		l2.setBounds(50, 72, 56, 15);
		t1 = new JTextField(25);
		t1.setBounds(111, 43, 171, 21);
		t2 = new JPasswordField(25);
		t2.setBounds(111, 69, 171, 21);
		loginButton = new JButton("로그인");
		loginButton.setBounds(111, 97, 85, 23);
		loginButton.addActionListener(this);
		getContentPane().setLayout(null);
		getContentPane().add(l1);
		getContentPane().add(t1);
		getContentPane().add(l2);
		getContentPane().add(t2);
		getContentPane().add(loginButton);
		
		signUpButton = new JButton("회원가입");
		signUpButton.setBounds(197, 97, 85, 23);
		signUpButton.addActionListener(this);
		getContentPane().add(signUpButton);
		setVisible(true);




	}
	
	public void actionPerformed (ActionEvent ae) {
		
		
	if(ae.getSource() == loginButton) {

		if((t1.getText().equals("") || t2.getText().equals(""))) {
			JOptionPane.showMessageDialog(null,"아이디와 비밀번호를 입력해주세요");
			return;
		}
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login","login","k404");
			Statement st  = con.createStatement();
			String query = "select password from login where id = '"+t1.getText()+"'";
			ResultSet rs = st.executeQuery(query);
			
			String get_password="";
			while(rs.next()) {
				get_password = rs.getString(1);
			}
			if(get_password.equals(t2.getText())) {
				JOptionPane.showMessageDialog(null,"로그인 성공");
				new BubbleFrame();
			}
			else {
				JOptionPane.showMessageDialog(null,"아이디 또는 비밀번호가 맞지 않습니다.");
			}
		}
		
		catch (Exception e) {
			System.out.println(e);
		}
		
	
	}else {
		this.dispose();
		new SignUp();
	}
	
	}
	public static void main(String args[]) {
		new Login();
	}

}
 