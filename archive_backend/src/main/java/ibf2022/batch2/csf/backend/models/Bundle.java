package ibf2022.batch2.csf.backend.models;

import java.util.ArrayList;

public class Bundle {
    
    private String bundleId;
    private String date;
    private String title; 
    private String name;
    private String comments;
    private ArrayList<String> urls = new ArrayList<String>();

    
    
    public Bundle() {
    }
    public Bundle(String bundleId, String date, String title, String name, String comments, ArrayList<String> urls) {
        this.bundleId = bundleId;
        this.date = date;
        this.title = title;
        this.name = name;
        this.comments = comments;
        this.urls = urls;
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
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }
    public ArrayList<String> getUrls() {
        return urls;
    }
    public void setUrls(ArrayList<String> urls) {
        this.urls = urls;
    }
    @Override
    public String toString() {
        return "Bundle [bundleId=" + bundleId + ", date=" + date + ", title=" + title + ", name=" + name + ", comments="
                + comments + ", urls=" + urls + "]";
    }

    

    

}

//Model is used for holding data retrieved from database, esp data for images 
// public record Bundle(String bundleId, String date, String title, String name, 
//     String comments, String[] urls){
    
// }

