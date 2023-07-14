package demo.pafday27redo.model;

public record Game(
    Integer gid,
    String name,
    Integer year,
    Integer ranking,
    Integer users_rated,
    String url,
    String image
) {
    
}

