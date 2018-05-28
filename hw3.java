package hw3;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.sql.*;

public class hw3 {
	private static Map<String, Integer> m = new HashMap<String, Integer>();
	public static void main(String args[]) throws Exception{
			System.out.println("Enter major_p:");
			Scanner s = new Scanner(System.in);
			String GIVENP = s.next();
			CALLRECURSION(GIVENP, 1);
			for (Map.Entry map : m.entrySet()) {
	            System.out.println(map.getKey() + " " + map.getValue());
	        }
	}
	public static void CALLRECURSION(String GIVENP, int qty) throws Exception{
		String UPPER_P = GIVENP;
		String LOWER_P = " ";
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/fengyuya","fengyuyaweb","password");
			Statement state = conn.createStatement();
			String sql = "SELECT MINOR_P, QTY FROM PART_STRUCTURE WHERE MAJOR_P = '" + UPPER_P + "' AND MINOR_P > '" + LOWER_P + "'  ORDER BY MINOR_P";
			List<String> content = new ArrayList<String>();
	        List<Integer> value = new ArrayList<Integer>();
			ResultSet rs = state.executeQuery(sql);
			while (rs.next()) {
				content.add(rs.getString("MINOR_P"));
                value.add(rs.getInt("QTY"));
            }
            if (content.size() == 0) {
                if (m.containsKey(UPPER_P)) {
                    m.put(UPPER_P, m.get(UPPER_P) + qty);
                } else {
                    m.put(UPPER_P, qty);
                }
            } else {
                for (int i = 0; i < content.size(); ++i) {
                	CALLRECURSION(content.get(i), qty * value.get(i));
                }
            }
			}catch (Exception e) {
				e.printStackTrace();
			} 
	}
}
