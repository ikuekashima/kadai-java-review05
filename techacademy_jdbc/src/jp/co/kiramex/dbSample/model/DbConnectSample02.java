package jp.co.kiramex.dbSample.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnectSample02 {

    public static void main(String[] args) {
        // 3. データベース接続と結果取得のための変数宣言
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // 1. ドライバのクラスをJava上で読み込む
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2. DBと接続する
            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost/world?useSSL=false&allowPublicKeyRetrieval=true",
                        "root", "ikuiku1919");
            } catch (SQLException e1) {
                // TODO 自動生成された catch ブロック
                e1.printStackTrace();
            }
            // 4. DBとやりとりする窓口（Statementオブジェクト）の作成
            try {
                stmt = con.createStatement();
            } catch (SQLException e1) {
                // TODO 自動生成された catch ブロック
                e1.printStackTrace();
            }
            // 5, 6. Select文の実行と結果を格納／代入
            String sql = "SELECT * FROM country where Code = 'ABW'";
            try {
                rs = stmt.executeQuery(sql);
            } catch (SQLException e1) {
                // TODO 自動生成された catch ブロック
                e1.printStackTrace();
            }

            // 7-1. 更新前の結果を表示する
            System.out.println("更新前===================");
            try {
                if (rs.next()) {
                    // Name列の値を取得
                    String name = rs.getString("Name");
                    // Population列の値を取得
                    int population = rs.getInt("Population");
                    // 取得した値を表示
                    System.out.println(name + "\n" + population);
                }
            } catch (SQLException e1) {
                // TODO 自動生成された catch ブロック
                e1.printStackTrace();
            }

            // 7-2. 更新処理を行なう
            System.out.println("更新処理実行=============");
            String updateSql = "update country set Population = 105000 where Code = 'ABW'";
            int count = 0;
            try {
                count = stmt.executeUpdate(updateSql);
            } catch (SQLException e) {
                // TODO 自動生成された catch ブロック
                e.printStackTrace();
            }
            System.out.println("更新行数：" + count);

            // 7-3. 更新後の結果を表示する
            try {
                rs.close();
            } catch (SQLException e) {
                // TODO 自動生成された catch ブロック
                e.printStackTrace();
            }// 更新後の検索のため、一旦閉じる（閉じないと警告が出るため）
            System.out.println("更新後=================");
            try {
                rs = stmt.executeQuery(sql);
            } catch (SQLException e) {
                // TODO 自動生成された catch ブロック
                e.printStackTrace();
            }
            try {
                if (rs.next()) {
                    // Name列の値を取得
                    String name = rs.getString("Name");
                    // Population列の値を取得
                    int population = rs.getInt("Population");
                    // 取得した値を表示
                    System.out.println(name + "\n" + population);
                }
            } catch (SQLException e) {
                // TODO 自動生成された catch ブロック
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        } finally {
            // 7. 接続を閉じる
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();

                }
            }
        }
    }

}
