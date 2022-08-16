import java.awt.event.*;
import java.awt.*;
import java.sql.*;

import javax.swing.*;


public class swing extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String S = "";
	int flag = 0;
	static JLabel mssg, username, password, login;
	static JTextField tf;
	static JPasswordField pass;

	swing() {
	super("Acredition System");
	tf = new JTextField();
	username = new JLabel("UserName");
	password = new JLabel("Password");
	login=new JLabel("Login");
	mssg=new JLabel();
	pass = new JPasswordField();
	JButton submit = new JButton("Submit");
	tf.setForeground(Color.red);
	login.setBounds(150, 0, 350, 150);
	login.setFont(new Font("Verdana", Font.PLAIN, 24));
	username.setBounds(150, 125, 100, 30);
	tf.setBounds(150, 150, 100, 30);
	password.setBounds(150, 200, 100, 30);
	pass.setBounds(150, 225, 100, 30);
	mssg.setBounds(150, 350, 150, 30);
	submit.setBounds(150, 300, 100, 30);
	submit.addActionListener(this);
	login.setForeground(Color.blue);
	add(login);
	add(submit);
	add(password);
	add(pass);
	add(username);
	add(tf);
	add(mssg);
	setSize(400, 450);
	setLayout(null);
	setVisible(true);
	}


	public static void main(String[] args) {

		swing m=new swing();
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getActionCommand()=="Submit")
		{
			flag=0;
			try
			{	Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject", "root", "Leo@2002");
				Statement stmt= con.createStatement();
				ResultSet rs=stmt.executeQuery("select * from Login");
				
				while(rs.next())
				{	String userID=rs.getString("Username");
	                String password=rs.getString("Password");
	                if((userID.equals(tf.getText())) && (password.equals(String.valueOf(pass.getPassword()))))
					{
	                	mssg.setText("Correct Details");
	                	flag=1;
	                	break;
					}
				}
				if(flag==0)
				{
					mssg.setText("Incorrect Details");
				}
				else
				{
					
					if(tf.getText().equals("CS"))
					{
						new cse();
						dispose();
					}
					if(tf.getText().equals("EC"))
					{
						new Ec();
						dispose();
					}
					if(tf.getText().equals("Mech"))
					{
						new Mech();
						dispose();
					}
					if(tf.getText().equals("Principal"))
					{
						new principal();
						dispose();
					}
				}
				con.close();
			}catch(Exception ex){System.out.println(ex);}
		}
	}

}
