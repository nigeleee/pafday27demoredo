package demo.pafday27redo.repo;

import java.util.List;


import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;


@Repository
public class GameRepo {
    
    @Autowired
    private MongoTemplate template;

    private final String G_NAME = "name";
    private final String G_GAME = "game";
    private final String G_ID = "gid";

    public List<Document> getGameByName(String name) {
        
        Criteria criteria = Criteria.where(G_NAME).is(name);
        Query query = Query.query(criteria);

        return template.find(query, Document.class, G_GAME);
    }

    public List<Document> getGameByGid(int gid) {

        Criteria criteria = Criteria.where(G_ID).is(gid);
        Query query = Query.query(criteria);

        return template.find(query, Document.class, G_GAME);
    }

}
