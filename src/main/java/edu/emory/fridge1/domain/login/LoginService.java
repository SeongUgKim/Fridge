package edu.emory.fridge1.domain.login;

import edu.emory.fridge1.domain.member.Member;
import edu.emory.fridge1.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberService memberService;

    public Member login(String email, String password) {
        return memberService.findByEmail(email).filter(m -> m.getPassword().equals(password)).orElse(null);
    }
}
