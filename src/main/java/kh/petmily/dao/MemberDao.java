package kh.petmily.dao;

import kh.petmily.domain.DomainObj;
import kh.petmily.domain.member.Member;
import kh.petmily.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberDao implements BasicDao {

    private final MemberMapper mapper;

    // =======BasicDao 메소드=======
    @Override
    public Member findByPk(int pk) {
        return mapper.selectByPk(pk);
    }

    @Override
    public void insert(DomainObj obj) {
        mapper.insert((Member) obj);
    }

    @Override
    public void update(DomainObj obj) {
        mapper.update((Member) obj);
    }

    @Override
    public void delete(int pk) {
        mapper.delete(pk);
    }
    // =======BasicDao 메소드=======

    public int findPk(String id, String pw) {
        return mapper.selectmNumber(id, pw);
    }

    public Member selectMemberById(String id) {
        return mapper.selectMemberById(id);
    }

    public String selectName(int pk) {
        return mapper.selectName(pk);
    }

    public String selectBirth(int pk) {
        return mapper.selectBirth(pk);
    }

    public String selectPhone(int pk) {
        return mapper.selectPhone(pk);
    }

    public String selectEmail(int pk) {
        return mapper.selectEmail(pk);
    }

    public List<Member> selectAll() {
        return mapper.selectAll();
    }
}
