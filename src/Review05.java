import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Review05 {

    public static void main(String[] args) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = null;
        
        try {
            // JDBCドライバをロード
            Class.forName("com.mysql.cj.jdbc.Driver");

            // DB接続
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost/kadaidb?useSSL=false&allowPublicKeyRetrieval=true",
                    "root",
                    "rootroot");

            // キー入力を読み取って、idにセット
            System.out.print("IDを入力してください > ");
            int id= keyIn();
            
            // SQL文を準備（?の箇所はキーボード入力の値）
            sql = "SELECT * FROM person WHERE id = ? limit 1";
            
            //
            pstmt = con.prepareStatement(sql);



            // SQL文を生成
            pstmt.setInt(1, id);

            // SQL実行
            rs = pstmt.executeQuery();
            
            // nameとageの値を取得
            rs.next();
            String name = rs.getString("Name");
            int age = rs.getInt("age");
            
            // 取得した値を表示
            System.out.println(name);
            System.out.println(age);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static int keyIn() {
        String line = null;
        try {
            BufferedReader key = new BufferedReader(new InputStreamReader(System.in));
            line = key.readLine();
        } catch (IOException e) {

        }
        return Integer.parseInt(line);
    }

}
