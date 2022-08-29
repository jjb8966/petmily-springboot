package kh.petmily.service;

import kh.petmily.domain.member.Member;
import kh.petmily.domain.member.form.JoinRequest;
import kh.petmily.domain.member.form.MemberChangeForm;

import java.util.List;

import java.util.List;

public interface MemberService {

    public void join(JoinRequest joinReq);

    public Member login(String id, String pw);

    public void logout();

    public void withdraw(int mNumber);

    public boolean checkPwCorrect(int mNumber, String pw);

    Member modify(Member member, MemberChangeForm memberChangeForm);

    String findName(int mNumber);

    List<Member> selectAll();

    public boolean isPwEqualToConfirm(String pw, String confirmPw);

    List<Member> selectAll();
}