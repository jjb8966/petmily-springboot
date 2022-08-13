package kh.petmily.domain.find_board.form;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FindBoardWriteForm {
    private int mNumber;
    private String species;
    private String kind;
    private String location;
    //private String imgPath;
    private String title;
    private String content;

    public FindBoardWriteForm(String species, String kind, String location, String title, String content) {
        this.species = species;
        this.kind = kind;
        this.location = location;
        this.title = title;
        this.content = content;
    }
}