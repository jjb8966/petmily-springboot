package kh.petmily.domain.board.form;

import lombok.Data;

import java.util.Date;

@Data
public class ReadBoardForm {
    private int mNumber;
    private int bNumber;
    private String name;
    private String kindOfBoard;
    private String title;
    private String content;
    private Date wrTime;
    private String checkPublic;

    public ReadBoardForm(int mNumber, int bNumber, String name, String kindOfBoard, String title, String content, Date wrTime, String checkPublic){
        this.mNumber = mNumber;
        this.bNumber = bNumber;
        this.name = name;
        this.kindOfBoard = kindOfBoard;
        this.title = title;
        this.content = content;
        this.wrTime = wrTime;
        this.checkPublic = checkPublic;
    }
}





