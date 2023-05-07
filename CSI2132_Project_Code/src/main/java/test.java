import java.sql.*;

public class test {
	
	public test() {
		
	}
//	public static void main(String[] args) throws SQLException {
//
////		String jdbcURL = "jdbc:mysql://localhost:3306/project";
////        String username = "root";
////        String password = "ihml6969";
////        Connection connection;
////        Statement statement = null;
////        try{
////
////    		 connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", username, password);
////    		 statement = connection.createStatement();
////             
////            System.out.println("hi");
////        }catch (SQLException e){
////            System.out.println("Error in connecting to PostgreSQL server");
////            e.printStackTrace();
////        }
//        
//call();
//        //System.out.println(getHighestID(statement, "hotel", "hotel_ID"));
//	}
	
	public static void call () throws SQLException {
		Runner run = new Runner();
	       System.out.println( run.getHighestID("hotel", "hotel_ID"));
	}
	
	public static int getHighestID(Statement statement, String tableName, String idName) throws SQLException{
		int returnID = 0;
		String query = "SELECT MAX("+idName+") FROM "+ tableName +";";
		ResultSet rs = statement.executeQuery(query);
		while (rs.next())
        {
			returnID = rs.getInt(1);
        }
		
		return returnID;
	}

}
