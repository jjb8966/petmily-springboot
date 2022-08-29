package kh.petmily.domain.member.form;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@Data
@NoArgsConstructor
public class MemberCreateForm {
    private int mNumber;
    private String id;
    private String pw;
    private String name;
    private Date birth;
    private String gender;
    private String email;
    private String phone;
    private String grade;
}
