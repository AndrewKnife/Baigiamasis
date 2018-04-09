package lt.kaunascoding.web.model.mysql;

import lt.kaunascoding.web.controller.userControl.Sha256;
import lt.kaunascoding.web.model.mysql.classes.Category;
import lt.kaunascoding.web.model.mysql.classes.News;
import lt.kaunascoding.web.model.mysql.classes.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MysqlCommands {

    private Connection conn;
    private Statement statement;

    public MysqlCommands() {
        buildConnectionFactory();
    }

    //region User Methods
    public void insertNewUser(User user) {
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO `users`");
        query.append(user.tableRows());
        query.append(" VALUES ");
        query.append(user.toTable());
        query.append(";");
        try {
            statement.executeUpdate(query.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateUser(User naujas) {
        naujas = getUser(naujas);
        StringBuilder query = new StringBuilder();
        query.append("UPDATE `users` SET  `last_login_date` = ");
        query.append("\"" + new Timestamp(System.currentTimeMillis()) + "\"");
        query.append(" WHERE  `users`.`id` =" + naujas.getId());
        query.append(";");
        try {
            statement.executeUpdate(query.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Boolean userExists(User naujas) {
        boolean exists;
        User current = getUser(naujas);
        if (current == null || current.getNickname() == null) {
            exists = false;
        } else {
            exists = true;
        }
        return exists;
    }

    public Boolean checkLogin(User naujas) {
        boolean canlogin = false;
        User current = getUser(naujas);
        naujas.setPassword(Sha256.getSha256(naujas.getPassword()));
        if (current == null) {
            canlogin = false;
        } else {
            if (naujas.getPassword().equals(current.getPassword()) && naujas.getNickname().equals(current.getNickname())) {
                canlogin = true;
            } else {
                canlogin = false;
            }
        }
        return canlogin;
    }

    public User getUser(User user) {
        User result = new User();
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM `users` WHERE ");
        query.append("nickname = " + "\"" + user.getNickname() + "\"");
        query.append(";");
        try {
            ResultSet set = statement.executeQuery(query.toString());
            while (set.next()) {
                result = new User(set);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<User> getUserList() {
        List<User> result = new ArrayList<User>();
        try {
            ResultSet set = statement.executeQuery("SELECT * FROM `users`;");
            while (set.next()) {
                result.add(new User(set));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
    //endregion

    //region Categories Methods
    public List<Category> getCategoriesList() {
        List<Category> result = new ArrayList<Category>();
        try {
            ResultSet set = statement.executeQuery("SELECT * FROM `categories`;");
            while (set.next()) {
                result.add(new Category(set));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
    //endregion

    //region News Methods
    public List<News> getPopularNews(String category) {

        List<News> result = new ArrayList<News>();
        StringBuilder query = new StringBuilder();
        if (category != null && category != "") {
            query.append("SELECT * FROM `news` WHERE `category_name` = \"" + category + "\" ORDER BY `view_count` DESC LIMIT 3;");
        } else {
            query.append("SELECT * FROM `news` ORDER BY `view_count` DESC LIMIT 3  ;");
        }
        try {
            ResultSet set = statement.executeQuery(query.toString());
            while (set.next()) {
                result.add(new News(set));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<News> getNewsList() {
        List<News> result = new ArrayList<News>();
        try {
            ResultSet set = statement.executeQuery("SELECT * FROM `news` ORDER BY `created_date` DESC ;");
            while (set.next()) {
                result.add(new News(set));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<News> getNewsByCategory(String category, int limit) {
        List<News> result = new ArrayList<News>();
        StringBuilder query = new StringBuilder();
        if(limit == 0){
            query.append("SELECT * FROM `news` WHERE `category_name` =" + "\"" + category + "\"" + " ORDER BY `created_date` DESC ;");
        }else {
            query.append("SELECT * FROM `news` WHERE `category_name` =" + "\"" + category + "\"" + " ORDER BY `created_date` DESC LIMIT " + limit + " ;");
        }

        try {
            ResultSet set = statement.executeQuery(query.toString());
            while (set.next()) {
                result.add(new News(set));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public News getNewsByID(int id) {
        News result = new News();
        try {
            ResultSet set = statement.executeQuery("SELECT * FROM `news` WHERE `id` =" + id + ";");
            while (set.next()) {
                result = new News(set);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            statement.executeUpdate("UPDATE `news` SET `view_count` = " + (result.getView_count() + 1) + " WHERE `news`.`id` = " + result.getId() + " ;");
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
    //endregion

    private void buildConnectionFactory() {
        try {
            conn = new MysqlConnector().Connect();
            statement = conn.createStatement();
        } catch (SQLException e) {

        }
    }

}
