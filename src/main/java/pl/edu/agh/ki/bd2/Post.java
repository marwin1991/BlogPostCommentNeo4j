package pl.edu.agh.ki.bd2;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Post {
    private String title;
    private String content;
    private Date date;
    private Blog blog;
    private Set<Comment> comments;

    public Post(Date date, String title, String content, Blog blog) {
        this.date = date;
        this.title = title;
        this.content = content;
        this.blog = blog;
        this.comments = new HashSet<>();
    }

    public void addComment(Comment comment){
        this.comments.add(comment);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
}
