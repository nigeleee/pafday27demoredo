package demo.pafday27redo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import demo.pafday27redo.model.Comment;
import demo.pafday27redo.model.Game;
import demo.pafday27redo.service.CommentService;
import demo.pafday27redo.service.GameService;

@Controller
@RequestMapping
public class MainController {
    
    @Autowired
    private GameService gameSvc;
    @Autowired
    private CommentService commentSvc;
    
    @GetMapping
    public String landPage() {
        
        return "home";
    }

    @GetMapping(path="/search")
    public String searchResult(@RequestParam String name, Model model) {
        try {
            List<Document> game = gameSvc.findGameByName(name);
            Document gameDoc = game.get(0);
            Game result = gameSvc.docToGame(gameDoc);
        
            model.addAttribute("result", result);

            return "result";
        } catch (NoSuchElementException e) {
            
            model.addAttribute("error", e.getMessage());
            return "error";
        }   
        
    }   
    
    @GetMapping(path="/game/{gid}")
    public String gameDetails(@PathVariable int gid, Model model) {

        List<Document> game = gameSvc.findGameByGid(gid);
        Document gameDoc = game.get(0);
        Game gameDetails = gameSvc.docToGame(gameDoc); 

        List<Document> comment = commentSvc.findCommentByGid(gameDetails.gid());
        List<Comment> comments = new ArrayList<>();
        for (Document commentDoc : comment) {
            Comment c = commentSvc.docToComment(commentDoc);

            comments.add(c);
        }
        
        model.addAttribute("gameDetails", gameDetails);
        model.addAttribute("comments", comments);


        return "game";
        
    }

    @PostMapping(path="/comment")
    public String createComment(@RequestBody MultiValueMap<String, String> input, Model model) {
        System.out.println(input);

        String c_id = UUID.randomUUID().toString();

        Comment c = new Comment(c_id, input.getFirst("user"), Integer.parseInt(input.getFirst("rating")), input.getFirst("c_text"), Integer.parseInt(input.getFirst("gid")));

        System.out.println(c);

        commentSvc.newComment(c);

        System.out.println(commentSvc.newComment(c));

        return "success";
    }
}
