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

        //必要な変数を宣言
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = null;

        try {
            // JDBCドライバをロード
            Class.forName("com.mysql.cj.jdbc.Driver");

            // mysqlに接続
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost/kadaidb?useSSL=false&allowPublicKeyRetrieval=true",
                    "root",
                    "rootroot");

            // SQL文を変数にセット
            sql = "SELECT * FROM person WHERE id = ? limit 1";

            // キー入力を読み取って、変数にセット
            System.out.print("IDを入力してください > ");
            int id= keyIn();

            //
            pstmt = con.prepareStatement(sql);

            // SQL文の?にキーボード入力の値をセット
            pstmt.setInt(1, id);

            // SQL実行
            rs = pstmt.executeQuery();

            // 実行結果からnameとageの値を取得
            rs.next();
            String name = rs.getString("Name");
            int age = rs.getInt("age");

            // 取得した値を表示
            System.out.println(name);
            System.out.println(age);

            //例外処理ブロック
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // キーボード入力の値を取得（parseIntで数値に変換して取得）
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
