package demo.pafday27redo.service;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.pafday27redo.model.Comment;
import demo.pafday27redo.repo.CommentRepo;
import jakarta.json.Json;
import jakarta.json.JsonObject;

@Service
public class CommentService {
    
    @Autowired
    private CommentRepo commentRepo;

    public List<Document> findCommentByGid(int gid) {
        return commentRepo.getCommentByGid(gid);
    }

    public Comment docToComment(Document commentDoc) {
        return new Comment(
            commentDoc.getString("c_id"),
            commentDoc.getString("user"),
            commentDoc.getInteger("rating"),
            commentDoc.getString("c_text"),
            commentDoc.getInteger("gid")
        );
    }

    //create json object using object builder
    public Document newComment(Comment c) {
        JsonObject obj = Json.createObjectBuilder()
        .add("c_id", c.c_id())
        .add("user", c.user())
        .add("rating", c.rating())
        .add("c_text", c.c_text())
        .add("gid", c.gid())
        .build();
        
        return commentRepo.newComment(Document.parse(obj.toString()));
    }
}
