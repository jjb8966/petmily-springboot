package kh.petmily.domain.adopt_review.form;

import lombok.Data;

import java.sql.Date;

@Data
public class AdoptReviewForm {
    private int bNumber;
    private int mNumber;
    private String name;
    private String kindOfBoard;
    private String title;
    private String content;
    private String imgPath;
    private Date wrTime;
    private String checkPublic;
    private int viewCount;
    private int replyCount;

}