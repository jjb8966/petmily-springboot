package kh.petmily.service;

import kh.petmily.domain.member.form.JoinRequest;
import kh.petmily.domain.member.form.MemberInfo;
import kh.petmily.dao.MemberDao;
import kh.petmily.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberDao memberDao;

    @Override
    public void join(JoinRequest joinReq) {
        Member member = toMember(joinReq);
        memberDao.insert(member);
    }

    @Override
    public Member login(String id, String pw) {
        Member member = memberDao.selectMemberById(id);

        if (!pw.equals(member.getPw())) {
            return null;
        }

        return member;
    }

    @Override
    public void logout() {

    }

//    @Override
//    public MemberInfo findById(String userId) {
//        Member member = memberDao.selectById(userId);
//
//        if (member == null) {
//            throw new MemberNotFoundException();
//        }
//
//        String id = member.getId();
//        String pw = member.getPw();
//        String name = member.getName();
//        Date birth = member.getBirth();
//        String gender = member.getGender();
//        String email = member.getEmail();
//        String phone = member.getPhone();
//        String grade = member.getGrade();
//
//        MemberInfo memberInfo = new MemberInfo(id, pw, name, birth, gender, email, phone, grade);
//
//        return memberInfo;
//    }

    @Override
    public void withdraw(int mNumber) {
        memberDao.delete(mNumber);
    }

    @Override
    public boolean checkPwCorrect(int mNumber, String pw) { //계정 비번 일치하는지
        Member member = memberDao.findByPk(mNumber);

        return member.getPw().equals(pw);
    }

    @Override
    public boolean isPwEqualToConfirm(String pw, String confirmPw) { // 비번, 비번확인 일치하는지
        return pw != null && pw.equals(confirmPw);
    }

    @Override
    public void changeMemberInfo(String id, MemberInfo memberInfo) {

    }

    @Override
    public String findName(int mNumber) {
        return memberDao.selectName(mNumber);
    }

    private Member toMember(JoinRequest joinReq) {
        String id = joinReq.getId();
        String pw = joinReq.getPw();
        String name = joinReq.getName();
        Date birth = joinReq.getBirth();
        String gender = joinReq.getGender();
        String email = joinReq.getEmail();
        String phone = joinReq.getPhone();

        return new Member(id, pw, name, birth, gender, email, phone);
    }
}
