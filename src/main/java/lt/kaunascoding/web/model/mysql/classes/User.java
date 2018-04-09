package lt.kaunascoding.web.model.mysql.classes;

import lt.kaunascoding.web.model.mysql.interfaces.ITable;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class User implements ITable{

    private int id;
    private String nickname;
    private String password;
    private String name;
    private String email;
    private String level;
    private Date regDate;
    private Timestamp lastLoginDate;

    public User() {

    }

    public User(ResultSet eilute){
        try {
            id = eilute.getInt("id");
            nickname = eilute.getString("nickname");
            password = eilute.getString("password");
            name = eilute.getString("name");
            email = eilute.getString("email");
            level = eilute.getString("level");
            regDate = eilute.getDate("reg_date");
            lastLoginDate = eilute.getTimestamp("last_login_date");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User(int id, String nickname, String password, String name, String email, String level, Date regDate, Timestamp lastLoginDate) {
        this.id = id;
        this.nickname = nickname;
        this.password = password;
        this.name = name;
        this.email = email;
        this.level = level;
        this.regDate = regDate;
        this.lastLoginDate = lastLoginDate;
    }

    //region Getters and Setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Timestamp getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Timestamp lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    //endregion

    public String tableRows() {
        StringBuilder table = new StringBuilder();
        table.append("(id,nickname,password,name,email,level,reg_date,last_login_date)");
        return table.toString();
    }

    public String toTable() {
        StringBuilder table = new StringBuilder();
        table.append("(");
        table.append("NULL,");
        table.append("\"" + nickname + "\""+",");
        table.append("\"" + password + "\""+",");
        table.append("\"" + name + "\""+",");
        table.append("\"" + email + "\""+",");
        table.append("\"" + level + "\""+",");
        table.append("\"" + regDate.toString() + "\""+",");
        table.append("\"" + lastLoginDate.toString() + "\"");
        table.append(")");
        return table.toString();
    }
}
