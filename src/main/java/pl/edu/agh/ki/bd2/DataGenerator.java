package pl.edu.agh.ki.bd2;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class DataGenerator {
    private Set<Blog> blogs;
    private Set<Post> posts;
    private Set<Comment> comments;
    private String[] names = {"Piotr Zmilczak", "Artur Rodak", "Areq", "Pan Tadeusz", "Mickiewicz"};

    public DataGenerator() {
        this.blogs = new HashSet<>();
        this.posts = new HashSet<>();
        this.comments = new HashSet<>();
        for (int i = 1; i < 11; i++) {
            blogs.add(new Blog(String.format("Blog %s", i),
                    String.format("Blog ten numer %s jest fajny", i),
                    names[new Random().nextInt(5)]));
        }
        int i = 1;
        for(Blog b: this.blogs){
            Post p = new Post(
                    new Date(getRandomTimeBetweenTwoDates()),
                    String.format("Tytuł numer %s",i),
                    String.format("Content numer %s",i),
                    b
            );
            posts.add(p);
            b.addPost(p);
            i++;
        }
        i=0;
        for(Blog b: this.blogs){
            Comment c = new Comment(
                    new Date(getRandomTimeBetweenTwoDates()),
                    String.format("Tytuł numer %s",i),
                    names[new Random().nextInt(5)],
                    String.format("Content numer %s",i),
                    b);
            this.comments.add(c);
            b.addComment(c);
            i++;
        }
        i=0;
        for(Post p: this.posts){
            Comment c = new Comment(
                    new Date(getRandomTimeBetweenTwoDates()),
                    String.format("Tytuł numer %s",i),
                    names[new Random().nextInt(5)],
                    String.format("Content numer %s",i),
                    p);
            this.comments.add(c);
            p.addComment(c);
            i++;
        }
    }

    private long getRandomTimeBetweenTwoDates () {
        long beginTime = Timestamp.valueOf("2017-01-01 00:00:00").getTime();
        long endTime = Timestamp.valueOf("2017-12-31 00:58:00").getTime();
        long diff = endTime - beginTime + 1;
        return beginTime + (long) (Math.random() * diff);
    }

    public Set<Blog> getBlogs() {
        return blogs;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public Set<Comment> getComments() {
        return comments;
    }
}
