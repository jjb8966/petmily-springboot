package kh.petmily.domain.adopt_review.form;

import kh.petmily.domain.DomainObj;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdoptReviewWriteForm implements DomainObj {
    private int mNumber;
    private String kindOfBoard;
    private String title;
    private String content;
    private String checkPublic;


    public AdoptReviewWriteForm(int mNumber, String kindOfBoard, String title, String content, String checkPublic) {
        this.mNumber = mNumber;
        this.kindOfBoard = kindOfBoard;
        this.title = title;
        this.content = content;
        this.checkPublic = checkPublic;
    }

    public int getmNumber() {
        return mNumber;
    }
}