package kh.petmily.domain.member.form;

import java.sql.Date;

public class MemberInfo {

    private String id;
    private String pw;
    private String name;
    private Date birth;
    private String gender;
    private String email;
    private String phone;
    private String grade;

    public MemberInfo(String id, String pw, String name, Date birth, String gender, String email, String phone, String grade) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.birth = birth;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.grade = grade;
    }

    public MemberInfo(String pw, String name, String email, String phone) {
        this.pw = pw;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
}
