package customTools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.BhPost;

public class DbPost {

public static int insert(java.util.Date postdate,String posttext,int userid) {
String sql = "insert into bhpost (postdate,posttext,bhuserid) " +
"values(?,?,?)";
int recordsAffected = 0;
Connection con = null;
PreparedStatement pstmt = null;

try{
Class.forName("oracle.jdbc.driver.OracleDriver");
con = DriverManager.getConnection("jdbc:oracle:thin:ora1/ora1@localhost:1521:orcl");
pstmt = con.prepareStatement(sql);
pstmt.setDate(1,new java.sql.Date(postdate.getTime()));
pstmt.setString(2, posttext);
pstmt.setInt(3, userid);
recordsAffected = pstmt.executeUpdate();
}catch (SQLException e) {
e.printStackTrace();
}catch (ClassNotFoundException e) {
e.printStackTrace();
} finally {
try {
pstmt.close();
con.close();
}catch(SQLException e){
e.printStackTrace();
}
}

return recordsAffected;

}

public static void update() {

}

public static List<BhPost> AllPosts () throws SQLException, ClassNotFoundException{
List<BhPost> posts = new ArrayList<BhPost>();
String sql = "select postid,postdate,posttext,bhuserid from bhpost";
ResultSet rs = null;
Connection con = null;
PreparedStatement pstmt = null;

Class.forName("oracle.jdbc.driver.OracleDriver");
con = DriverManager.getConnection("jdbc:oracle:thin:ora1/ora1@localhost:1521:orcl");
pstmt = con.prepareStatement(sql);
rs = pstmt.executeQuery();
// Fetch each row from the result set
while (rs.next()) {
long postid = rs.getInt("postid");
java.util.Date postdate = rs.getDate("postdate");
String posttext = rs.getString("posttext");
long userid = rs.getLong("bhuserid");

BhPost p = new BhPost();
p.setPostid(postid);
p.setPostdate(convertJavaDateToSqlDate(postdate));
p.setPosttext(posttext);
p.setBhuserid(userid);
//add the post to the arraylist
posts.add(p);
}
return posts;
}

public static List<BhPost> postsofUser(long userid)
{
List<BhPost> userposts = new ArrayList<BhPost>();

return userposts;
}
public static List<BhPost> postsofUser(String useremail)
{
List<BhPost> userposts = new ArrayList<BhPost>();

return userposts;
}

public static List<BhPost> searchPosts (String search)
{
List<BhPost> searchposts = new ArrayList<BhPost>();

String qString = "select b from Bhpost b "
+ "where b.posttext like :search";

return searchposts;
}

public static java.sql.Date convertJavaDateToSqlDate(java.util.Date date) {
return new java.sql.Date(date.getTime());
}

}