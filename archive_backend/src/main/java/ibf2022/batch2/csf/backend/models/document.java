package ibf2022.batch2.csf.backend.models;

import java.io.Serializable;

public class document implements Serializable{

    private String name;
    private String title;
    private String comments;
    private String bundleId;
    private String date;
    private String[] url;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }
    public String getBundleId() {
        return bundleId;
    }
    public void setBundleId(String bundleId) {
        this.bundleId = bundleId;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String[] getUrl() {
        return url;
    }
    public void setUrl(String[] url) {
        this.url = url;
    }
    
    
    
}
