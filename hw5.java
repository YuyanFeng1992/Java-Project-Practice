package hw5;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class hw5 {

	public static int count = 0;
	
	 public static void select(String table) throws Exception {
	        Connection conn = null;
	        try {
	            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/fengyuya","fengyuyaweb","password");
	            Statement state = conn.createStatement();
				ResultSet rs;
				 
				rs = state.executeQuery(table);
				System.out.println("major_p	minor_p	qty");
	            while(rs.next()){
	            	System.out.println(rs.getString(1)+"	"+rs.getString(2)+"	"+rs.getString(3));
	            	count++;
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	
	public static void others(String uc) throws Exception{
		Connection c = null;
		PreparedStatement pstmt = null;
		
		try {
			 Class.forName("org.postgresql.Driver");
			 c = DriverManager
					 .getConnection("jdbc:postgresql://localhost:5432/fengyuya",
					  "fengyuyaweb", "password");
			 pstmt = c.prepareStatement(uc);
			 int row = pstmt.executeUpdate();
			 System.out.println(row + " rows successful!");
			 
		 } catch (Exception e) {
			 e.printStackTrace();
			 System.err.println(e.getClass().getName()+": "+e.getMessage());
			 System.exit(0);
		 }
	}
	
	public static void main(String[] args) throws Exception {
		
		Scanner in = new Scanner(System.in);
		System.out.println("Enter sql statement: ");
		String table = in.nextLine();
		
		if(table.substring(0,6).equals("select")){
			//Please use lowercase letters !
			select(table);
			System.out.println(count + " rows!");
		}
		else {
			others(table);
		}
	}
}