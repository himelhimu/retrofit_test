package sabbir.mpower.com.feb_test.model;

import java.io.Serializable;

/**
 * Created by sabbir on 2/25/17.
 */

public class PhotoDetails implements Serializable {
    private String date,explanation,title,url;

    public PhotoDetails(String date, String explanation, String title, String url) {
        this.date = date;
        this.explanation = explanation;
        this.title = title;
        this.url = url;
    }

    public PhotoDetails() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
