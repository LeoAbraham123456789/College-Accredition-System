import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class cse extends JFrame implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JCheckBox reg, yr, ssr;
	JButton update, refresh;
	public cse()
	{
		super("Computer Science Engineering");
		reg =new JCheckBox("Registration");
		yr=new JCheckBox("Yearly Report");
		ssr=new JCheckBox("Self Study Report");
		update =new JButton("Update");
		refresh=new JButton("Refresh");
		reg.setBounds(200, 100, 150, 30);
		yr.setBounds(200, 120, 150, 30);
		ssr.setBounds(200, 140, 150, 30);
		update.setBounds(100, 250, 100, 30);
		refresh.setBounds(250, 250, 100, 30);
		update.addActionListener(this);
		refresh.addActionListener(this);
		add(reg);
		add(yr);
		add(ssr);
		add(update);
		add(refresh);
		setSize(500,550);
		setLayout(null);
		setVisible(true);
	}
	public static void main(String args[])
	{
		cse c=new cse();
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getActionCommand()=="Update")
		{
			try
			{	Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject", "root", "Leo@2002");
				Statement stmt= con.createStatement();
				if(reg.isSelected())
				{
					
					stmt.execute("UPDATE CSE SET status=1 WHERE topics='Registration'");
					
				}
				else
				{
					stmt.execute("UPDATE CSE SET status=0 WHERE topics='Registration'");
					//stmt.execute("UPDATE progress_bar SET percentage=percentage-33 WHERE Dept='CS'");
				}
				if(yr.isSelected())
				{
					
					stmt.execute("UPDATE CSE SET status=1 WHERE topics='Yearly Report'");
					
				}
				else
				{
					stmt.execute("UPDATE CSE SET status=0 WHERE topics='Yearly Report'");
					//stmt.execute("UPDATE progress_bar SET percentage=percentage-33 WHERE Dept='CS'");
				}
				if(ssr.isSelected())
				{
					
					stmt.execute("UPDATE CSE SET status=1 WHERE topics='SSR'");
					//stmt.execute("UPDATE progress_bar SET percentage+=34 WHERE Dept='CS'");
				}
				else
				{
					stmt.execute("UPDATE CSE SET status=0 WHERE topics='SSR'");
					//stmt.execute("UPDATE progress_bar SET percentage=percentage-34 WHERE Dept='CS'");
				}
				if(reg.isSelected()&&yr.isSelected()&&ssr.isSelected())
				{
					stmt.execute("UPDATE progress_bar SET percentage=100 WHERE Dept='CS'");
				}
				else if((reg.isSelected()&&yr.isSelected())||(yr.isSelected()&&ssr.isSelected())||(reg.isSelected()&&ssr.isSelected()))
					stmt.execute("UPDATE progress_bar SET percentage=66 WHERE Dept='CS'");
				else if(reg.isSelected()||yr.isSelected()||ssr.isSelected())
					stmt.execute("UPDATE progress_bar SET percentage=33 WHERE Dept='CS'");
				
				else
					stmt.execute("UPDATE progress_bar SET percentage=0 WHERE Dept='CS'");
				con.close();
			}catch(Exception ex){System.out.println(ex);}
		}
		if(arg0.getActionCommand()=="Refresh")
		{
			try
			{	Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject", "root", "Leo@2002");
				Statement stmt= con.createStatement();
				ResultSet rs=stmt.executeQuery("Select * from CSE");
				while(rs.next())
				{
					int status=rs.getInt("status");
					if(rs.getString("topics").equals("Registration"))
					{
						if(status==1)
							reg.setSelected(true);
					}
					if(rs.getString("topics").equals("Yearly Report"))
					{
						if(status==1)
							yr.setSelected(true);
					}
					if(rs.getString("topics").equals("SSR"))
					{
						if(status==1)
							ssr.setSelected(true);
					}
				}
				con.close();
			}catch(Exception ex){System.out.println(ex);}
		}
		
	}
}