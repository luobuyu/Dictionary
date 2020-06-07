package module;

import java.sql.*;

public class Datebase {
    private static final String dbDriver = "com.mysql.cj.jdbc.Driver";// 连接MySQL数据库的驱动
    private static final String URL = "jdbc:mysql://localhost:3306/dictionary?serverTimezone=Asia/Shanghai";// 连接MySQL数据库的路径
    private static final String USERNAME = "root";// 连接MySQL数据库的用户名
    private static final String PASSWORD = "15287517740";// 连接MySQL数据库的密码
    private static Connection con = null;// 初始化连接MySQL数据库的对象

    public Datebase() {
        try {
            Class.forName(dbDriver);
        } catch (ClassNotFoundException e) {
            System.out.println("数据库驱动加载失败");
            e.printStackTrace();
        }
    }

    public static void creatConnection() {
        try {
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        if(con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                con = null;
            }
        }
    }

    public Word searchWord(String word) {
        Word result = new Word();
        result.setWord(word);
        word = word.replace("'", "\\'");
        if(con == null) {
            creatConnection();
        }
        ResultSet rs;
        PreparedStatement statement;
        // 读取 word 表，找到星级和编号
        try {
            statement = con.prepareStatement("select * from word where word = " + "'" + word + "'");
            rs = statement.executeQuery();
            boolean flag = true;
            while(rs.next()) {
                flag = false;
                result.setWordID(rs.getInt("word_id"));
                result.setStars(rs.getString("stars"));
            }
            if(flag) {  // 没有进入while，说明没有找到
                return null;
            }

            // 找到 see_also
            statement = con.prepareStatement("select * from see_also where word_id = " + "'" + result.getWordID() + "'");
            rs = statement.executeQuery();
            while(rs.next()) {
                result.getSee_also().add(rs.getString("word"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 查询 items 表，找到不同的意思
        try {
            statement = con.prepareStatement("select * from items where word_id = " + "'" + result.getWordID() + "'");
            rs = statement.executeQuery();
            ResultSet rs2;
            Item item;

            while(rs.next()) {
                item = new Item(rs.getInt("items_id"), rs.getString("label"), rs.getString("word_ch"), rs.getString("gram"), rs.getString("explanation"));
                // 找到例句
                statement = con.prepareStatement("select * from sentences where items_id = " + "'" + item.getItemID() +"'");
                rs2 = statement.executeQuery();
                while(rs2.next()) {
                    item.addSentence(new Sentence(rs2.getString("sentence_en"), rs2.getString("sentence_ch")));
                }
                // 找到 tips
                statement = con.prepareStatement("select * from tips where items_id = " + "'" + item.getItemID() + "'");
                rs2 = statement.executeQuery();
                while(rs2.next()) {
                    item.addTip(rs2.getString("tips"));
                }

                // 加入结果集
                result.addItem(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
