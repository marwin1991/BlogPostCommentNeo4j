package pl.edu.agh.ki.bd2;

import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;

import java.util.Date;

public class Solution {

    private final GraphDatabase graphDatabase = GraphDatabase.createDatabase();

    public void databaseStatistics() {
        System.out.println(graphDatabase.runCypher("CALL db.labels()"));
        System.out.println(graphDatabase.runCypher("CALL db.relationshipTypes()"));
    }

    public void runAllTests() {
        System.out.println(getBlogs());
        System.out.println(getPosts());
        System.out.println(getComments());
/*
        DataGenerator dg = new DataGenerator();
        for(Blog b: dg.getBlogs())
            createBlogInDb(b);
        for(Post p: dg.getPosts()) {
            createPostInDb(p);
            createPostToBlogRelationship(p);
        }
        for(Comment c: dg.getComments()) {
            createCommentInDb(c);
            if(c.getBlog() == null)
                createCommentToPostRelationship(c);
            else
                createCommentToBlogRelationship(c);
        }

        Blog b = new Blog("TestRellationshipForBlog", "Test", "Piotr Zmilczak");
        Post p = new Post(new Date(), "TestRelForBlog", "Test", b);
        Comment c1 = new Comment(new Date(), "TestComment", "PiotrZmilczak", "aaa", b);
        Comment c2 = new Comment(new Date(), "TestComment2", "PiotrZmilczak", "aaaa", p);
        System.out.println(createBlogInDb(b));
        System.out.println(createPostInDb(p));
        System.out.println(createPostToBlogRelationship(p));
        System.out.println(createCommentInDb(c1));
        System.out.println(createCommentInDb(c2));
        System.out.println(createCommentToBlogRelationship(c1));
        System.out.println(createCommentToPostRelationship(c2));
        System.out.println(getAllRelationshipsForNode(b));
        System.out.println(getAllRelationshipsForNode(p));
        System.out.println(getAllRelationshipsForNode(c1));
        System.out.println(getAllRelationshipsForNode(c2));
        System.out.println(findShortestPathBetweenTwoNodes(c1,c2));*/
    }

    private String getBlogs() {
        return graphDatabase.runCypher("MATCH (b:Blog) RETURN b");
    }

    private String getPosts() {
        return graphDatabase.runCypher("MATCH (p:Post) RETURN p");
    }

    private String getComments() {
        return graphDatabase.runCypher("MATCH (c:Comment) RETURN c");
    }

    private String createBlogInDb(Blog blog) {
        String query = "CREATE (b:Blog {" +
                "name:\'" + blog.getName() + "\', " +
                "author:\'" + blog.getAuthor() + "\', " +
                "description:\'" + blog.getDescription() + "\'" +
                "}) RETURN b";
        return graphDatabase.runCypher(query);
    }

    private String createPostInDb(Post post) {
        String query = "CREATE (p:Post {" +
                "title:\'" + post.getTitle() + "\', " +
                "content:\'" + post.getContent() + "\', " +
                "date:\'" + post.getDate() + "\'" +
                "}) RETURN p";
        return graphDatabase.runCypher(query);
    }

    private String createCommentInDb(Comment comment) {
        String query = "CREATE (c:Comment {" +
                "title:\'" + comment.getTitle() + "\', " +
                "author:\'" + comment.getAuthor() + "\', " +
                "content:\'" + comment.getContent() + "\', " +
                "date:\'" + comment.getDate() + "\'" +
                "}) RETURN c";
        return graphDatabase.runCypher(query);
    }

    private String createPostToBlogRelationship(Post post) {
        String query = "MATCH (b:Blog),(p:Post)  WHERE " +
                "b.name = \'" + post.getBlog().getName() + "\' AND " +
                "b.author = \'" + post.getBlog().getAuthor() + "\' AND " +
                "b.description = \'" + post.getBlog().getDescription() + "\' AND " +
                "p.title = \'" + post.getTitle() + "\' AND " +
                "p.content = \'" + post.getContent() + "\' AND " +
                "p.date = \'" + post.getDate() + "\' " +
                "CREATE (b)<-[r:POST_TO_BLOG]-(p) RETURN r";
        return graphDatabase.runCypher(query);
    }

    private String createCommentToPostRelationship(Comment comment) {
        if (comment.getPost() == null) {
            System.out.println("It is blog's comment, not comment's one.");
            throw new IllegalArgumentException();
        }
        String query = "MATCH (c:Comment),(p:Post)  WHERE " +
                "c.title = \'" + comment.getTitle() + "\' AND " +
                "c.author = \'" + comment.getAuthor() + "\' AND " +
                "c.content = \'" + comment.getContent() + "\' AND " +
                "c.date = \'" + comment.getDate() + "\' AND " +
                "p.title = \'" + comment.getPost().getTitle() + "\' AND " +
                "p.content = \'" + comment.getPost().getContent() + "\' AND " +
                "p.date = \'" + comment.getPost().getDate() + "\' " +
                "CREATE (p)<-[r:COMMENT_TO_POST]-(c) RETURN r";
        return graphDatabase.runCypher(query);
    }

    private String createCommentToBlogRelationship(Comment comment) {
        if (comment.getBlog() == null) {
            System.out.println("It is post's comment, not blog's one.");
            throw new IllegalArgumentException();
        }
        String query = "MATCH (c:Comment),(b:Blog)  WHERE " +
                "c.title = \'" + comment.getTitle() + "\' AND " +
                "c.author = \'" + comment.getAuthor() + "\' AND " +
                "c.content = \'" + comment.getContent() + "\' AND " +
                "c.date = \'" + comment.getDate() + "\' AND " +
                "b.name = \'" + comment.getBlog().getName() + "\' AND " +
                "b.author = \'" + comment.getBlog().getAuthor() + "\' AND " +
                "b.description = \'" + comment.getBlog().getDescription() + "\' " +
                "CREATE (b)<-[r:COMMENT_TO_BLOG]-(c) RETURN r";
        return graphDatabase.runCypher(query);
    }

    private String getAllRelationshipsForNode(Object o) {
        if (o.getClass() == Blog.class)
            return getAllRelationshipsForBlog((Blog) o);
        if (o.getClass() == Post.class)
            return getAllRelationshipsForPost((Post) o);
        if (o.getClass() == Comment.class)
            return getAllRelationshipsForComment((Comment) o);
        return null;
    }

    private String getAllRelationshipsForBlog(Blog blog) {
        String query = "MATCH (b:Blog {" +
                "name:\'" + blog.getName() + "\', " +
                "author:\'" + blog.getAuthor() + "\', " +
                "description:\'" + blog.getDescription() + "\'" +
                "})-[r]-(a)" +
                "RETURN r, a, b";
        return graphDatabase.runCypher(query);
    }

    private String getAllRelationshipsForPost(Post post) {
        String query = "MATCH (p:Post {" +
                "title:\'" + post.getTitle() + "\', " +
                "content:\'" + post.getContent() + "\', " +
                "date:\'" + post.getDate() + "\'" +
                "})-[r]-(a)" +
                "RETURN r, a, p";
        return graphDatabase.runCypher(query);
    }

    private String getAllRelationshipsForComment(Comment comment) {
        String query = "MATCH (c:Comment {" +
                "title:\'" + comment.getTitle() + "\', " +
                "author:\'" + comment.getAuthor() + "\', " +
                "content:\'" + comment.getContent() + "\', " +
                "date:\'" + comment.getDate() + "\'" +
                "})-[r]-(a)" +
                "RETURN r, a, c";
        return graphDatabase.runCypher(query);
    }

    private String getBlogPropertiesForNeo4jQueryAsString(Blog blog) {
        return  "Blog {" +
                "name:\'"+blog.getName()+"\', "+
                "author:\'"+blog.getAuthor()+"\', "+
                "description:\'"+blog.getDescription()+"\'"+
                "})";
    }

    private String getPostPropertiesForNeo4jQueryAsString(Post post) {
        return  "Post {" +
                "title:\'" + post.getTitle() + "\', " +
                "content:\'" + post.getContent() + "\', " +
                "date:\'" + post.getDate() + "\'"+
                "})";
    }

    private String getCommentPropertiesForNeo4jQueryAsString(Comment comment) {
        return  "Comment {" +
                "title:\'" + comment.getTitle() + "\', " +
                "author:\'" + comment.getAuthor() + "\', " +
                "content:\'" + comment.getContent() + "\', " +
                "date:\'" + comment.getDate() + "\'"+
                "})";
    }

    private String getNodePropertiesForNeo4jQueryAsString(Object o) {
        if (o.getClass() == Blog.class)
            return getBlogPropertiesForNeo4jQueryAsString((Blog) o);
        if (o.getClass() == Post.class)
            return getPostPropertiesForNeo4jQueryAsString((Post) o);
        if (o.getClass() == Comment.class)
            return getCommentPropertiesForNeo4jQueryAsString((Comment) o);
        return null;
    }
    private String findShortestPathBetweenTwoNodes(Object o1, Object o2){
        String query = "MATCH (n1:"+getNodePropertiesForNeo4jQueryAsString(o1)+"," +
                "(n2:"+getNodePropertiesForNeo4jQueryAsString(o2)+","+
                "p = shortestPath((n1)-[*]-(n2))"+
                "RETURN p";
        return graphDatabase.runCypher(query);
    }

}
