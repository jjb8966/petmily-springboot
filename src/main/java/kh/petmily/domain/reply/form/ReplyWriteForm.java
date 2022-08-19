package kh.petmily.domain.reply.form;

import kh.petmily.domain.DomainObj;
import lombok.Data;

@Data
public class ReplyWriteForm implements DomainObj {
    private int bNumber;
    private int mNumber;
    private String reply;

    public ReplyWriteForm(int bNumber, int mNumber, String reply) {
        this.bNumber = bNumber;
        this.mNumber = mNumber;
        this.reply = reply;
    }
}