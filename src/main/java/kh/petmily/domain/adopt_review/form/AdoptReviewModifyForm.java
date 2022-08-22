package kh.petmily.domain.adopt_review.form;

import lombok.Data;

@Data
public class AdoptReviewModifyForm {

    private int bNumber;
    private int mNumber;
    private String title;
    private String content;
    private String checkPublic;

    public AdoptReviewModifyForm(int bNumber, String title, String content, String checkPublic){
        this.bNumber = bNumber;
        this.title = title;
        this.content = content;
        this.checkPublic = checkPublic;
    }
}
