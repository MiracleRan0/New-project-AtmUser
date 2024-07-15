package JDBC;

import java.sql.*;

public class SQL {
    public static int someIntValue;
    // ... 其他代码 ...
    public static String result;
    // 验证用户名和密码的方法
    static int state=1;
    public static boolean validateUser(String username, String password,String sql, String search) {
        String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String dbURL = "jdbc:sqlserver://localhost:1433; DatabaseName=ATM;encrypt=true;trustServerCertificate=true"; // 假设端口是默认的1433
        String userName = "GHR22367106"; // 替换为您的数据库用户名
        String userPassword = "22367106"; // 替换为您的密码

        // 查询语句，同时验证用户名、密码和账户状态
        sql = "SELECT user_status FROM ATM_user WHERE user_account = ? AND user_password = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(dbURL, userName, userPassword);
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();

            // 检查是否找到了匹配的行
            if (rs.next()) {
                // 获取账户状态

                String Status = rs.getString("user_status");
                String lockedState = "Locked";

                // 检查账户状态是否为锁定
                if (Status.equals(lockedState)) {
                    // 账户已被锁定
                    System.out.println("账户已被锁定");
                    state=0;
                } else {
                    // 账户状态正常，验证通过
                    // 如果需要返回其他信息，可以在这里处理searchColumn
                    System.out.println("验证成功");
                    state=1;}
                return true;
            }

        }

        catch (Exception e) {
            e.printStackTrace();
            return false; // 如果发生异常，则返回false
        } finally {
            // 关闭资源（在真实环境中应该使用try-with-resources）
            try {

                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // 如果没有找到匹配的行或发生异常，默认返回false
        return false;
    }
}
