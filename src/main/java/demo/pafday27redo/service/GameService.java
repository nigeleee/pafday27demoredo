package demo.pafday27redo.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.pafday27redo.model.Game;
import demo.pafday27redo.repo.GameRepo;

@Service
public class GameService {
    
    @Autowired
    private GameRepo gameRepo;

    public List<Document> findGameByName(String name) {
        
        List<Document> doc = gameRepo.getGameByName(name);
        if(doc.isEmpty()){
            throw new NoSuchElementException("No such game found");
        }
        return doc;
    }

    public List<Document> findGameByGid(int gid) {
        return gameRepo.getGameByGid(gid);
    }

    public Game docToGame (Document gameDoc) {
        return new Game(
            gameDoc.getInteger("gid"),
            gameDoc.getString("name"),
            gameDoc.getInteger("year"),
            gameDoc.getInteger("ranking"),
            gameDoc.getInteger("users_rated"),
            gameDoc.getString("url"),
            gameDoc.getString("image")
        );
    }
    
}

    