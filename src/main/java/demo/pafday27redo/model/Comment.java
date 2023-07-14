package demo.pafday27redo.model;

public record Comment(
    String c_id,
    String user,
    Integer rating,
    String c_text,
    Integer gid
) {
    
}
