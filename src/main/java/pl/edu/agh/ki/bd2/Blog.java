package pl.edu.agh.ki.bd2;

import java.util.HashSet;
import java.util.Set;

public class Blog {
    private String name;
    private String description;
    private String author;

    private Set<Post> posts;
    private Set<Comment> comments;

    public Blog(String name, String description, String author) {
        this.name = name;
        this.description = description;
        this.author = author;
        this.posts = new HashSet<>();
        this.comments = new HashSet<>();
    }


    public void addPost(Post post){
        this.posts.add(post);
    }
    public void addComment(Comment comment){
        this.comments.add(comment);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
}
