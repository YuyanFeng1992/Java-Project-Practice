package hw6;
import java.sql.*;
import java.util.Scanner;

import org.postgresql.util.PSQLException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.String;
public class hw6 {
	public static Connection c = null;
	public static PreparedStatement pstmt=null;
	public static ResultSet rs=null; 
	public static int Snumber=0;
	public static String Sname=null;
	public static int DoB=0;
	public static String prompt="no record";
	public static String readDataFromConsole(String prompt) {
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        String str = null;
	        try {
	            System.out.print(prompt);
	            str = br.readLine();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return str;
	    }
	 public static void show(PreparedStatement pstmt) throws SQLException{
		rs=pstmt.executeQuery();
		
		if(rs.next()){
			Snumber=rs.getInt("Snumber");
			Sname=rs.getString("Sname");
			DoB=rs.getInt("dob");
			//c.commit();
			System.out.println("student number:"+Snumber + ""
					+ "\t" +"student name:" +Sname + "\t" + "DoB:"+DoB + "   " );
			while(rs.next()){
				Snumber=rs.getInt("Snumber");
				Sname=rs.getString("Sname");
				DoB=rs.getInt("dob");
				//c.commit();
				System.out.println("student number:"+Snumber + ""
						+ "\t" +"student name:" +Sname + "\t" + "DoB:"+DoB + "   " );
			}
			pstmt.close();
	        rs.close();
		}else{
			pstmt.close();
	        rs.close();
			System.out.println(prompt);
		}
		
	}
	 public static void work()throws SQLException{
			try{
				Class.forName("org.postgresql.Driver");
				c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/fengyuya","fengyuyaweb","password");
				c.setAutoCommit(false);
				String sql=null;
				Sname = readDataFromConsole("1.student name(Press the Enter key for no input):\n");
				System.out.println("2.student number(Press 0 for no input):");
			    Scanner s = new Scanner(System.in);
			    Snumber = s.nextInt();
			    System.out.println("3.DoB(Press 0 for no input):");
			    DoB = s.nextInt();
				if(!Sname.equals("")){
					if(Snumber!=0){
						if(DoB!=0){
							sql="select * from Students where Sname=?  and Snumber=? and DoB=?";
							pstmt = c.prepareStatement(sql);
							pstmt.setString(1,Sname);
							pstmt.setInt(2,Snumber);
							pstmt.setInt(3, DoB);
							show(pstmt);
						}else{
							sql="select * from Students where Sname=? and Snumber=?";
							pstmt = c.prepareStatement(sql);
							pstmt.setString(1,Sname);
							pstmt.setInt(2,Snumber);
							show(pstmt);
						}
					}else{
						if(DoB!=0){
							sql="select * from Students where Sname=?  and DoB=?";
							pstmt = c.prepareStatement(sql);
							pstmt.setString(1,Sname);
							pstmt.setInt(2, DoB);
							show(pstmt);
						}else{
							sql="select * from Students where Sname=?";
							pstmt = c.prepareStatement(sql);
							pstmt.setString(1,Sname);
							show(pstmt);
						}
					}
				}else{
					if(Snumber!=0){
						if(DoB!=0){
							sql="select * from Students where  Snumber=? and DoB=?";
							pstmt = c.prepareStatement(sql);
							pstmt.setInt(1,Snumber);
							pstmt.setInt(2, DoB);
							show(pstmt);
						}else{
							sql="select * from Students where  Snumber=?";
							pstmt = c.prepareStatement(sql);
							pstmt.setInt(1,Snumber);
							show(pstmt);
						}
					}else{
						if(DoB!=0){
							sql="select * from Students where  DoB=?";
							pstmt = c.prepareStatement(sql);
							pstmt.setInt(1, DoB);
							show(pstmt);
						}else{
							sql=null;
						}
					}
				}
		        }catch (Exception e){
				e.printStackTrace();
				System.err.println(e.getClass().getName()+": "+e.getMessage());
				System.exit(0);
			}finally{
				if(c !=null) c.close();
			}
	 }

	public static void main(String args[]) {

			while(true){
				try {
					work();
				} catch (SQLException e) {
					// Auto-generated catch block
					e.printStackTrace();
				}
			}
	               
			
	}
	 

}
