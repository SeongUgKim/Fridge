package edu.emory.fridge1.web.Member;

import edu.emory.fridge1.domain.member.Member;
import edu.emory.fridge1.service.MemberService;
import edu.emory.fridge1.web.Member.form.MemberSaveForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/add")
    public String addForm(@ModelAttribute("member") Member member) {
        return "members/addMemberForm";
    }

    @PostMapping("/add")
    public String save(@Validated @ModelAttribute MemberSaveForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "members/addMemberForm";
        }
        Member member = new Member(form.getFirstName(), form.getLastName(), form.getEmail(), form.getPassword());
        memberService.save(member);
        log.info("{}", member.getFirstName());
        log.info("{}", member.getLastName());
        log.info("{}", member.getEmail());
        log.info("{}", member.getPassword());
        return "redirect:/";
    }
}
