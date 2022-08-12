package kh.petmily.domain.look_board.form;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LookBoardWriteForm {
    private int mNumber;
    private String species;
    private String kind;
    private String location;
    //private String imgPath;
    private String title;
    private String content;

    public LookBoardWriteForm(String species, String kind, String location, String title, String content) {
        this.species = species;
        this.kind = kind;
        this.location = location;
        this.title = title;
        this.content = content;
    }
}