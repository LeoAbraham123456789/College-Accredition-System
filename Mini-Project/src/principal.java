import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.*;
import java.io.*;

public class principal extends JFrame implements ItemListener, ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int csv=0, mechv=0, ecv=0;
	JProgressBar percentage;
	JRadioButton cs, mech, ec;
	JButton validate;
	String dpt="";
	JLabel validated, heading;
	principal()
	{
		super("Welcome Principal");
		heading=new JLabel("Choose Department");
		validate=new JButton("Validate");
		percentage=new JProgressBar(0,100);
		percentage.setBounds(75, 250, 100, 30);
		percentage.setValue(0);
		percentage.setStringPainted(true);
		validated=new JLabel("validated");
		heading.setBounds(75, 50, 300, 30);
		heading.setForeground(Color.blue);
		heading.setFont(new Font("Verdana", Font.PLAIN, 24));
		validated.setBounds(200, 250, 100, 30);
		add(validated);
		validated.setVisible(false);
		cs=new JRadioButton("CS");
		mech=new JRadioButton("Mech");
		ec=new JRadioButton("EC");
		validate.setBounds(200, 250, 100, 30);
		validate.setVisible(false);
		cs.setBounds(75, 100, 100, 30);
		mech.setBounds(75, 150, 100, 30);
		ec.setBounds(75, 200, 100, 30);
		ButtonGroup bg=new ButtonGroup();
		bg.add(cs);
		bg.add(mech);
		bg.add(ec);
		validate.addActionListener(this);
		cs.addItemListener(this);
		ec.addItemListener(this);
		mech.addItemListener(this);
		add(heading);
		add(validate);
		add(cs);
		add(ec);
		add(mech);
		add(percentage);
		percentage.setVisible(false);
		setSize(400,450);
		setLayout(null);
		setVisible(true);
	}
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		try
		{	Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject", "root", "Leo@2002");
			Statement stmt= con.createStatement();
			if(arg0.getSource()==cs)
			{
				ResultSet rs=stmt.executeQuery("select * from progress_bar where Dept='CS'");
				while(rs.next())
				{	
					percentage.setValue(rs.getInt("percentage"));
					percentage.setVisible(true);
				}
				File f=new File("cs.txt");
				if(percentage.getValue()==100&&(!f.isFile()))
				{
					validate.setVisible(true);
					validated.setVisible(false);
				}
				else if(f.isFile())
				{
					validate.setVisible(false);
					validated.setVisible(true);
				}
				else
				{
					validated.setVisible(false);
					validate.setVisible(false);
				}
				dpt="CS";
			}
			else if(arg0.getSource()==mech)
			{
				ResultSet rs=stmt.executeQuery("select * from progress_bar where Dept='Mech'");
				while(rs.next())
				{	
					percentage.setValue(rs.getInt("percentage"));
					percentage.setVisible(true);
				}
				File f=new File("mech.txt");
				if(percentage.getValue()==100&&(!f.isFile()))
				{
					validate.setVisible(true);
					validated.setVisible(false);
				}
				else if(f.isFile())
				{
					validate.setVisible(false);
					validated.setVisible(true);
				}
				else
				{
					validated.setVisible(false);
					validate.setVisible(false);
				}
				dpt="Mech";
			}
			if(arg0.getSource()==ec)
			{
				ResultSet rs=stmt.executeQuery("select * from progress_bar where Dept='EC'");
				while(rs.next())
				{	
					percentage.setValue(rs.getInt("percentage"));
					percentage.setVisible(true);
				}
				File f=new File("ec.txt");
				if(percentage.getValue()==100&&(!f.isFile()))
				{
					validate.setVisible(true);
					validated.setVisible(false);
				}
				else if(f.isFile())
				{
					validate.setVisible(false);
					validated.setVisible(true);
				}
				else
				{
					validated.setVisible(false);
					validate.setVisible(false);
				}
				dpt="EC";
			}
			con.close();
		}catch(Exception ex){System.out.println(ex);}
		
	}
	@Override
	public void actionPerformed(ActionEvent arg1) {
		// TODO Auto-generated method stub
		try {
			if(dpt.equals("CS"))
			{
				FileWriter f=new FileWriter("cs.txt");
				f.write("Dept: CS"+ "\n"+ "CheckList: Registration, Yearly Report, Self Study Report");
				f.close();
				csv=1;
			}
			if(dpt.equals("Mech"))
			{
				FileWriter f=new FileWriter("mech.txt");
				f.write("Acredition for mech is done");
				f.close();
				mechv=1;
			}
			if(dpt.equals("EC"))
			{
				FileWriter f=new FileWriter("ec.txt");
				f.write("Acredition for ec is done");
				f.close();
				ecv=1;
			}
		}catch(Exception e) {System.out.println(e);}
	}

}