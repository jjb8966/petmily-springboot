package kh.petmily.domain.adopt_review;

import kh.petmily.domain.DomainObj;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Blob;
import java.sql.Date;

@Getter
@NoArgsConstructor
public class AdoptReview implements DomainObj {

    private int bNumber;
    private int mNumber;
    private String name;
    private String kindOfBoard;
    private String title;
    private String content;
    private String imgPath;
    private Blob video;
    private Date wrTime;
    private String checkPublic;

    public AdoptReview(int bNumber, int mNumber, String name, String kindOfBoard, String title, String content, String imgPath, Blob video, Date wrTime, String checkPublic) {
        this.bNumber = bNumber;
        this.mNumber = mNumber;
        this.name = name;
        this.kindOfBoard = kindOfBoard;
        this.title = title;
        this.content = content;
        this.imgPath = imgPath;
        this.video = video;
        this.wrTime = wrTime;
        this.checkPublic = checkPublic;
    }

    public AdoptReview(int bNumber, int mNumber, String kindOfBoard, String title, String content, Date wrTime, String checkPublic) {
        this.bNumber = bNumber;
        this.mNumber = mNumber;
        this.kindOfBoard = kindOfBoard;
        this.title = title;
        this.content = content;
        this.wrTime = wrTime;
        this.checkPublic = checkPublic;
    }

    public AdoptReview(int mNumber, String kindOfBoard, String title, String content, String checkPublic) {
        this.mNumber = mNumber;
        this.kindOfBoard = kindOfBoard;
        this.title = title;
        this.content = content;
        this.checkPublic = checkPublic;
    }

    public AdoptReview(int bNumber, String title, String content, String checkPublic) {
        this.bNumber = bNumber;
        this.title = title;
        this.content = content;
        this.checkPublic = checkPublic;
    }
}
