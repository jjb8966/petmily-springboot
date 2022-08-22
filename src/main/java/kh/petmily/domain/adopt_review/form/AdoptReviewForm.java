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
    private Date wrTime;
    private String checkPublic;
    private int viewCount;
    private int replyCount;

    public AdoptReviewForm(int bNumber, int mNumber, String name, String kindOfBoard, String title, String content, Date wrTime, String checkPublic) {
        this.bNumber = bNumber;
        this.mNumber = mNumber;
        this.name = name;
        this.kindOfBoard = kindOfBoard;
        this.title = title;
        this.content = content;
        this.wrTime = wrTime;
        this.checkPublic = checkPublic;
    }

    // ====== 조회수, 댓글수 추가 ======
    public AdoptReviewForm(int bNumber, int mNumber, String name, String kindOfBoard, String title, String content, Date wrTime, String checkPublic, int viewCount, int replyCount) {
        this.bNumber = bNumber;
        this.mNumber = mNumber;
        this.name = name;
        this.kindOfBoard = kindOfBoard;
        this.title = title;
        this.content = content;
        this.wrTime = wrTime;
        this.checkPublic = checkPublic;
        this.viewCount = viewCount;
        this.replyCount = replyCount;
    }
}