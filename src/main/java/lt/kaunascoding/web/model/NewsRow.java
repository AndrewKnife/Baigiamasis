package lt.kaunascoding.web.model;

import lt.kaunascoding.web.model.mysql.classes.Category;
import lt.kaunascoding.web.model.mysql.classes.News;

import java.util.List;

public class NewsRow {
    private Category kategorija;
    private List<News> bignews;
    private List<List<List<News>>> smallnews;

    public NewsRow() {
    }

    public NewsRow(Category kategorija, List<News> bignews, List<List<List<News>>> smallnews) {
        this.kategorija = kategorija;
        this.bignews = bignews;
        this.smallnews = smallnews;
    }

    public Category getKategorija() {
        return kategorija;
    }

    public void setKategorija(Category kategorija) {
        this.kategorija = kategorija;
    }

    public List<News> getBignews() {
        return bignews;
    }

    public void setBignews(List<News> bignews) {
        this.bignews = bignews;
    }

    public List<List<List<News>>> getSmallnews() {
        return smallnews;
    }

    public void setSmallnews(List<List<List<News>>> smallnews) {
        this.smallnews = smallnews;
    }
}
