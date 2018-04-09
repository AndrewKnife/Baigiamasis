package lt.kaunascoding.web.model.mysql.classes;

import lt.kaunascoding.web.model.mysql.interfaces.ITable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

public class News implements ITable{
    private int id;
    private String title;
    private String author;
    private String category_name;
    private String thumbnail;
    private String tags;
    private String punchline;
    private String positions;
    private Timestamp created_date;
    private String alltext;
    private boolean hot;
    private int view_count;

    public News() {

    }

    public News(ResultSet eilute){
        try {
            id = eilute.getInt("id");
            title = eilute.getString("title");
            author = eilute.getString("author");
            category_name = eilute.getString("category_name");
            thumbnail = eilute.getString("thumbnail");
            tags = eilute.getString("tags");
            punchline = eilute.getString("punchline");
            positions = eilute.getString("positions");
            created_date = eilute.getTimestamp("created_date");
            alltext = eilute.getString("alltext");
            hot = eilute.getBoolean("hot");
            view_count = eilute.getInt("view_count");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public News(int id, String title, String author, String category_name, String thumbnail,
                String tags, String punchline, String positions, Timestamp created_date, String alltext, boolean hot, int view_count) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category_name = category_name;
        this.thumbnail = thumbnail;
        this.tags = tags;
        this.punchline = punchline;
        this.positions = positions;
        this.created_date = created_date;
        this.alltext = alltext;
        this.hot = hot;
        this.view_count = view_count;
    }

    //region Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String  getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getPunchline() {
        return punchline;
    }

    public void setPunchline(String punchline) {
        this.punchline = punchline;
    }

    public String getPositions() {
        return positions;
    }

    public void setPositions(String positions) {
        this.positions = positions;
    }

    public String getCreated_date() {
        LocalDateTime data = created_date.toLocalDateTime();
        String tekstas = data.getYear()+"-"+data.getMonthValue()+"-"+data.getDayOfMonth() ;
        tekstas = tekstas + " "+ data.getHour()+":"+data.getMinute();
        return tekstas;
    }

    public void setCreated_date(Timestamp created_date) {
        this.created_date = created_date;
    }

    public String getAlltext() {
        return alltext;
    }

    public void setAlltext(String alltext) {
        this.alltext = alltext;
    }

    public boolean isHot() {
        return hot;
    }

    public void setHot(boolean hot) {
        this.hot = hot;
    }

    public int getView_count() {
        return view_count;
    }

    public void setView_count(int view_count) {
        this.view_count = view_count;
    }

//endregion


    @Override
    public String tableRows() {
        StringBuilder table = new StringBuilder();
        table.append("(id,title,author,category_name,thumbnail,tags,punchline,positions,created_date, alltext, hot, view_count)");
        return table.toString();
    }

    @Override
    public String toTable() {
        StringBuilder table = new StringBuilder();
        table.append("(");
        table.append("NULL,");
        table.append("\"" + title + "\""+",");
        table.append("\"" + author + "\""+",");
        table.append("\"" + category_name + "\""+",");
        table.append("\"" + thumbnail + "\""+",");
        table.append("\"" + tags + "\""+",");
        table.append("\"" + punchline + "\""+",");
        table.append("\"" + positions + "\""+",");
        table.append("\"" + created_date.toString() + "\""+",");
        table.append("\"" + alltext + "\""+",");
        table.append("\"" + hot + "\""+",");
        table.append("" + view_count + "");
        table.append(")");
        return table.toString();
    }
}
