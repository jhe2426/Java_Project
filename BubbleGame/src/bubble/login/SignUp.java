package bubble.login;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.JPasswordField;

public class SignUp extends JFrame implements ActionListener{
	private JPasswordField password,idCard;
	private JPasswordField passwordCheck;
	private JTextField phoneNumber;
	private JTextField  id, name;
	private JButton idCheckButton, signUpButton;
	 Statement stmt = null;
	 private JPasswordField passwordField;
	public SignUp() {
		setTitle("회원가입");
		setSize(420,450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setVisible(true);
		
		// id라벨
		JLabel l1 = new JLabel("아아디 :");
		l1.setFont(new Font("굴림", Font.PLAIN, 15));
		l1.setBounds(84, 94, 57, 15);
		getContentPane().add(l1);
		// id 텍스트영역
		id = new JTextField();
		id.setBounds(141, 89, 116, 23);
		getContentPane().add(id);
		id.setColumns(10);
		// 중복확인 버튼
		idCheckButton = new JButton("중복확인");
		idCheckButton.setBackground(new Color(255, 255, 255));
		idCheckButton.setFont(new Font("굴림", Font.PLAIN, 15));
		idCheckButton.setBounds(254, 89, 108, 23);
		idCheckButton.addActionListener(this);
		getContentPane().add(idCheckButton);
		
		
		
		
		
		//이름 라벨
		JLabel l2 = new JLabel("이름 :");
		l2.setFont(new Font("굴림", Font.PLAIN, 15));
		l2.setBounds(96, 49, 45, 20);
		getContentPane().add(l2);
		//이름 텍스트 필드
		name = new JTextField();
		name.setColumns(10);
		name.setBounds(141, 48, 221, 23);
		getContentPane().add(name);
		//비밀번호 라벨
		JLabel l3 = new JLabel("비밀번호  :");
		l3.setFont(new Font("굴림", Font.PLAIN, 15));
		l3.setBounds(55, 138, 84, 20);
		getContentPane().add(l3);
		//비밀번호 텍스트 필드
		password = new JPasswordField();
		password.setColumns(10);
		password.setBounds(141, 136, 221, 23);
		getContentPane().add(password);
		//비밀번호 재확인 라벨
		JLabel l4 = new JLabel("비밀번호 재확인  :");
		l4.setFont(new Font("굴림", Font.PLAIN, 15));
		l4.setBounds(17, 178, 124, 20);
		getContentPane().add(l4);
		//비밀번호 재확인 텍스트 필드
		passwordCheck = new JPasswordField();
		passwordCheck.setColumns(10);
		passwordCheck.setBounds(141, 176, 221, 23);
		getContentPane().add(passwordCheck);
		//전화번호 라벨
		JLabel l5 = new JLabel("전화번호  :");
		l5.setFont(new Font("굴림", Font.PLAIN, 15));
		l5.setBounds(55, 220, 71, 20);
		getContentPane().add(l5);
		//전화번호 텍스트 필드
		phoneNumber = new JTextField();
		phoneNumber.setColumns(10);
		phoneNumber.setBounds(141, 218, 221, 23);
		getContentPane().add(phoneNumber);
		//주민등록번호 라벨
		JLabel l6 = new JLabel("주민등록번호  :");
		l6.setFont(new Font("굴림", Font.PLAIN, 15));
		l6.setBounds(33, 266, 108, 20);
		getContentPane().add(l6);
		
		//주민등록번호 텍스트 필드
		idCard = new JPasswordField();
		idCard.setColumns(10);
		idCard.setBounds(141, 265, 221, 23);
		getContentPane().add(idCard);
		//회원가입 버튼
		signUpButton = new JButton("회원가입");
		signUpButton.setFont(new Font("굴림", Font.PLAIN, 15));
		signUpButton.setBackground(new Color(255, 255, 255));
		signUpButton.setBounds(160, 311, 97, 42);
		signUpButton.addActionListener(this);
		getContentPane().add(signUpButton);
		
		
		
		
		
	}
	
	
	
	public void actionPerformed (ActionEvent ae) {
		if(ae.getSource() == idCheckButton) {

			if(id.getText().equals("")) {
				JOptionPane.showMessageDialog(null,"아이디를 입력해주세요");
				return;
			}
			try {
				
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login","login","k404");
				Statement st  = con.createStatement();
				String query = "select * from login";
				ResultSet rs = st.executeQuery(query);
				
				String get_id="";
				while(rs.next()) {
					get_id = rs.getString(1);
				}
				if(get_id.equals(id.getText())) {
					JOptionPane.showMessageDialog(null,"이미 존재하는 아이디입니다.");
				}
				else {
					JOptionPane.showMessageDialog(null,"사용 가능한 아이디입니다.");
				}
			}
			
			catch (Exception e) {
				System.out.println(e);
			}
			
		}else {
			
				if(id.getText().equals("")|| name.getText().equals("")
					|| password.getText().equals("")|| passwordCheck.getText().equals("")
					|| phoneNumber.getText().equals("")|| idCard.getText().equals("")) {
				JOptionPane.showMessageDialog(null,"빈칸을 모두 입력해주세요!!");
				return;
			}
			if(passwordCheck.getText().equals(password.getText())) {
			try {
				boolean ok = false;
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login","login","k404");
				Statement st  = con.createStatement();
				String sql = "insert into login(" + "id,password,user_name,user_id_card,user_phone_number) " + "values(?,?,?,?,?)";
				
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, id.getText());
				ps.setString(2, password.getText());
				ps.setString(3, name.getText());
				ps.setString(4, idCard.getText());
				ps.setString(5, phoneNumber.getText());
				int r = ps.executeUpdate(); 
				if (r > 0) {
					System.out.println("가입 성공");
					this.dispose();
					ok = true;
				} else {
					System.out.println("가입 실패");
				}
				
				
			}
			
			catch (Exception e) {
				System.out.println(e);
			}
		}else {
			JOptionPane.showMessageDialog(null,"비밀번호가 일치하지 않습니다.");
		}
	}
		
}
}
