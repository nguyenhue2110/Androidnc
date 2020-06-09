package hue.com.mob201_ps08729.model;

import java.util.List;

public class News {

    private String title;
    private String link;

    private String img;

    public News() {
    }

    public News(String title, String link, String img) {
        this.title = title;
        this.link = link;
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
