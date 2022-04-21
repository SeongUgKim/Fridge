package edu.emory.fridge1.service;

import edu.emory.fridge1.domain.member.Member;
import edu.emory.fridge1.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public Long save(Member member) {
        return memberRepository.save(member).getId();
    }

    @Transactional
    public Long update(Long id, Member member) {
        Member findMember = memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No Member"));
        findMember.update(member.getFirstName(), member.getLastName(), member.getEmail(), member.getPassword());
        return id;
    }

    @Transactional
    public void delete(Long id) {
        Member findMember = memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No Member"));
        memberRepository.delete(findMember);
    }

    @Transactional(readOnly = true)
    public List<Member> findAll() {
        return memberRepository.findAllMember();
    }

    @Transactional(readOnly = true)
    public Optional<Member> findByEmail(String email) {
        return findAll().stream()
                .filter(m -> m.getEmail().equals(email))
                .findFirst();
    }
}
