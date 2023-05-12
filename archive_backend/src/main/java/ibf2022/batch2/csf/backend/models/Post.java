package ibf2022.batch2.csf.backend.models;

public class Post {

    private String name;
    private String title;
    private String comments;
    private byte[] image;
    private String postId;
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
    public byte[] getImage() {
        return image;
    }
    public void setImage(byte[] file) {
        this.image = file;
    }
    public String getPostId() {
        return postId;
    }
    public void setPostId(String postId) {
        this.postId = postId;
    }

    

}
