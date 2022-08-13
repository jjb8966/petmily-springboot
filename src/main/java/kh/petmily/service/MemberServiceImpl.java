package kh.petmily.service;

import kh.petmily.domain.member.form.JoinRequest;
import kh.petmily.domain.member.form.MemberChangeForm;
import kh.petmily.dao.MemberDao;
import kh.petmily.domain.member.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
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

    @Override
    public void withdraw(int mNumber) {

    }

    @Override
    public boolean checkPwCorrect(int mNumber, String pw) {
        return false;
    }

    @Override
    public Member modify(Member member, MemberChangeForm memberChangeForm) {
        Member mem = toMemberFromChange(member, memberChangeForm);

        memberDao.update(mem);

        log.info("Service - modify - member : {}", mem);

        return mem;
    }

    private Member toMemberFromChange(Member member, MemberChangeForm memberChangeForm) {
        return new Member(member.getMNumber(), member.getId(), memberChangeForm.getPw(), memberChangeForm.getName(), member.getBirth(), member.getGender(), memberChangeForm.getEmail(), memberChangeForm.getPhone(), member.getGrade());
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