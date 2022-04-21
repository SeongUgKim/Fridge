package edu.emory.fridge1.web;

import edu.emory.fridge1.service.MemberService;
import edu.emory.fridge1.web.session.SessionMember;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Slf4j
@Controller
@RequiredArgsConstructor
public class RecipeController {
    private final MemberService memberService;

    @GetMapping("/recipe")
    public String recipe() {
        return "recipe";
    }
}
