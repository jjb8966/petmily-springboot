package kh.petmily.service;

import kh.petmily.domain.member.form.*;
import kh.petmily.domain.member.Member;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
public interface MemberService {

    public void join(JoinRequest joinReq);

    public Member login(String id, String pw);

    public void logout();

    public void withdraw(int mNumber);

    public boolean checkPwCorrect(int mNumber, String pw);

    Member modify(Member member, MemberChangeForm memberChangeForm);

    String findName(int mNumber);

    public boolean isPwEqualToConfirm(String pw, String confirmPw);

    public MemberPageForm getMemberPage(int pageNo);

    public MemberDetailForm getMember(int mNumber);

    public MemberModifyForm getMemberModify(int mNumber);

    public void delete(int mNumber);

    public void create(MemberCreateForm memberCreateForm);

    public void modify(MemberModifyForm memberModifyForm);

    List<Member> selectAll();
}
