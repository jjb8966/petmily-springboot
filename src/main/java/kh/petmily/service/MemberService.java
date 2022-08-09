package kh.petmily.service;

import kh.petmily.domain.member.form.JoinRequest;
import kh.petmily.domain.member.form.MemberInfo;
import kh.petmily.domain.member.Member;

public interface MemberService {

    public void join(JoinRequest joinReq);

    public Member login(String id, String pw);

    public void logout();

//    불필요 메소드
//    public MemberInfo findById(String userId);

    public void withdraw(int mNumber);

    public boolean checkPwCorrect(int mNumber, String pw);

    public void changeMemberInfo(String id, MemberInfo memberInfo);

    String findName(int mNumber);
}
