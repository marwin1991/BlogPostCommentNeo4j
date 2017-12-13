package pl.edu.agh.ki.bd2;

import java.util.Date;

public class Comment {
    private String title;
    private String author;
    private String content;
    private Date date;
    private Post post;
    private Blog blog;

    public Comment(Date date, String title, String author, String content, Post post) {
        this.date = date;
        this.title = title;
        this.author = author;
        this.content = content;
        this.post = post;
        this.blog = null;
    }

    public Comment(Date date, String title, String author, String content, Blog blog) {
        this.date = date;
        this.title = title;
        this.author = author;
        this.content = content;
        this.blog = blog;
        this.post = null;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

}
