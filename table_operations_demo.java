package jdbc_programms_demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.PreparableStatement;
import com.mysql.cj.xdevapi.Result;

public class table_operations_demo 
{
	Scanner sc=new Scanner(System.in);
	

	public static void main(String[] args) throws ClassNotFoundException, SQLException
	{
		int b_id;
		String b_name;
		int b_price;
		String b_author;
		table_operations_demo obj=new table_operations_demo();
		obj.choice();

	}
	public void choice() throws ClassNotFoundException
	{
		try
		{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn;
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student",
					"root","root");
		
		while(true)
		{
		int ch;
		System.out.println("1:create table");
		System.out.println("2:insert values in the table");
		System.out.println("3:update values in the table");
		System.out.println("4:delete values from the table");
		System.out.println("5:View the table");
		System.out.println("6:View the row from the table");
		System.out.println("7:Exit");
		System.out.println("Enter your choice");
		ch=sc.nextInt();
		switch(ch) 
		{
		case 1:
				create_table(conn);
				break;
		case 2:
				insert_table(conn);
				break;
		case 3:
			   update_table(conn);
			   break;
		case 4:
			  delete(conn);
			  break;
		case 5:
			  view_table(conn);
			  break;
		case 6:
			  view_byinput(conn);
			  break;
		case 7:
			  System.exit(0);
			  break;
		default:
				System.out.println("You Enter Invalid  choice");
				break;
		}
	  }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void create_table(Connection conn) throws SQLException
	{
		String qur="create table book (b_id int primary key,b_name varchar(50),b_price int,b_author varchar(50))";
		Statement st=conn.createStatement();
		st.execute(qur);
		System.out.println("table created sucessfully");
	}
	public void insert_table(Connection conn) throws SQLException
	{
		String qur="insert into book values(?,?,?,?)";
		PreparedStatement ps=conn.prepareStatement(qur);
		int b_id;
		String b_name;
		int b_price;
		String b_author;
		System.out.println("Enter book id");
		b_id=sc.nextInt();
		System.out.println("Enter book name");
		b_name=sc.next();
		System.out.println("Enter book price");
		b_price=sc.nextInt();
		System.out.println("Enter book auther");
		b_author=sc.next();
		ps.setInt(1,b_id);
		ps.setString(2,b_name);
		ps.setInt(3,b_price);
		ps.setString(4,b_author);
		int count=ps.executeUpdate();
		if(count>0)
		{
			System.out.println("Data added Successfully");
		}
	}
	public void update_table(Connection conn) throws SQLException
	{
		int ch1;
		System.out.println("which column do you want to update");
		System.out.println("1:book name");
		System.out.println("2:book price");
		System.out.println("3:book author");
		ch1=sc.nextInt();
		switch (ch1)
		{
		case 1:
			System.out.println("Enter Book id");
			int b_id=sc.nextInt();
			System.out.println("Enter Book Name");
			String b_name=sc.next();
			String qur="update book set b_name=? where b_id=?";
			PreparedStatement ps=conn.prepareStatement(qur);
			ps.setString(1,b_name);
			ps.setInt(2,b_id);
			ps.executeUpdate();
			System.out.println("Updated");
			break;
		case 2:
			System.out.println("Enter Book id");
			 b_id=sc.nextInt();
			System.out.println("Enter Book price");
			int b_price=sc.nextInt();
			qur="update book set b_price=? where b_id=?";
			PreparedStatement pse=conn.prepareStatement(qur);
			pse.setInt(1,b_price);
			pse.setInt(2,b_id);
			pse.executeUpdate();
			System.out.println("Updated");
			break;
		case 3:
			System.out.println("Enter Book id");
			b_id=sc.nextInt();
			System.out.println("Enter Book author Name");
			String b_author=sc.next();
			qur="update book set b_author=? where b_id=?";
			PreparedStatement psr=conn.prepareStatement(qur);
			psr.setString(1,b_author);
			psr.setInt(2,b_id);
			psr.executeUpdate();
			System.out.println("Updated");
			break;
		default:
			System.out.println("You entered Invalid choice");
			break;
		}
		
	}
	public void delete(Connection conn) throws SQLException 
	{
		System.out.println("Enter Book id");
		int b_id=sc.nextInt();
		String qur="delete from book where b_id=?";
		PreparedStatement ps=conn.prepareStatement(qur);
		ps.setInt(1,b_id);
		ps.executeUpdate();
		System.out.println("Row is deleted successfully");
	}
	public void view_table(Connection conn) throws SQLException 
	{
		String qur="select * from book";
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery(qur);
		System.out.println("Book ID\tBook Name\t\tBook Price\t\tBook AUthor");
		while(rs.next())
		{
			System.out.println(rs.getInt(1)+"\t "+rs.getString(2)+"\t"+rs.getInt(3)+"\t"+rs.getString(4));
		}
	}
	public void view_byinput(Connection conn) throws SQLException 
	{
		System.out.println("Enter Book id");
		int b_id=sc.nextInt();
		String qur="select * from book where b_id=?";
		PreparedStatement ps = conn.prepareStatement(qur);
	    ps.setInt(1, b_id);
	    ResultSet rs = ps.executeQuery();
	    System.out.println("Book ID\tBook Name\tBook Price\tBook Author");
	    while (rs.next()) {
	        System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getInt(3) + "\t" + rs.getString(4));
	    }
	}
}
