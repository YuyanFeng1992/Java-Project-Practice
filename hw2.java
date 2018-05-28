package hw2;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;
import java.sql.*;

public class hw2 {
	public static void main(String args[]) throws Exception{
			System.out.println("Enter major_p:");
			Scanner s = new Scanner(System.in);
			String GIVENP = s.next();
			CALLRECURSION(GIVENP);
	}
	public static void CALLRECURSION(String GIVENP) throws Exception{
		String UPPER_P = GIVENP;
		String LOWER_P = " ";
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/fengyuya","fengyuyaweb","password");
			Statement state = conn.createStatement();
			String sql = "SELECT MINOR_P FROM PART_STRUCTURE WHERE MAJOR_P = '" + UPPER_P + "' AND MINOR_P > '" + LOWER_P + "'  ORDER BY MINOR_P";
			System.out.println(UPPER_P);
			ResultSet rs = state.executeQuery(sql);
			while(rs.next()){
					LOWER_P = rs.getString("MINOR_P");
					if ( LOWER_P == null )
						return;
					else 
						CALLRECURSION( LOWER_P );
				}
			}catch (Exception e) {
				e.printStackTrace();
			} 
	}
}
