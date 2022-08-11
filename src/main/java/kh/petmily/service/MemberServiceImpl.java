package kh.petmily.service;

import kh.petmily.domain.member.form.JoinRequest;
import kh.petmily.domain.member.form.MemberInfo;
import kh.petmily.dao.MemberDao;
import kh.petmily.domain.member.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Member findById(String userId) {
        Member member = memberDao.selectMemberById(userId);
        if (member == null) {
            throw new NoSuchElementException();
        }
        return member;
    }

    @Override
    public void withdraw(int mNumber) {

    }

    @Override
    public boolean checkPwCorrect(int mNumber, String pw) {
        return false;
    }

    @Override
    public void changeMemberInfo(int mNumber, MemberInfo memberInfo) {
        Member member = memberInfo.toMember();
        member.toInfoInsertMNumber(mNumber);
        log.info("[update MemberInfo] = {} ", member);
        memberDao.update(member);
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
