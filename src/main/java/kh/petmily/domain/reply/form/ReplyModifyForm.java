package kh.petmily.domain.reply.form;

import kh.petmily.domain.DomainObj;
import lombok.Data;

@Data
public class ReplyModifyForm {
    private int brNumber;
    private String reply;

    public ReplyModifyForm(int brNumber, String reply) {
        this.brNumber = brNumber;
        this.reply = reply;
    }
}