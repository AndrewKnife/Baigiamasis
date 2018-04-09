package lt.kaunascoding.web.model.mysql.classes;

import lt.kaunascoding.web.model.mysql.interfaces.ITable;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Category implements ITable {
    private int id;
    private String name;


    public Category() {
    }

    public Category(ResultSet eilute){
        try {
            id = eilute.getInt("id");
            name = eilute.getString("name");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    //region Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //endregion


    @Override
    public String tableRows() {
        StringBuilder table = new StringBuilder();
        table.append("(id,name)");
        return table.toString();
    }

    @Override
    public String toTable() {
        StringBuilder table = new StringBuilder();
        table.append("(");
        table.append("NULL,");
        table.append("\"" + name + "\"");
        table.append(")");
        return table.toString();
    }
}
