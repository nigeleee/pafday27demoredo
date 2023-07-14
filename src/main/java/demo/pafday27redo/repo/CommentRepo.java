package demo.pafday27redo.repo;


import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;



@Repository
public class CommentRepo {
    
    @Autowired
    private MongoTemplate template;

    private final String C_ID = "gid";
    private final String C_COM = "comment";

    public List<Document> getCommentByGid(int gid) {
        
        Criteria criteria = Criteria.where(C_ID).is(gid);
        Query query = Query.query(criteria);

        return template.find(query, Document.class, C_COM);
    }

    public Document newComment(Document d) {
        return template.insert(d, C_COM);
    }

}

