package edu.emory.fridge1;

import edu.emory.fridge1.domain.item.Item;
import edu.emory.fridge1.domain.member.Member;
import edu.emory.fridge1.service.ItemService;
import edu.emory.fridge1.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final ItemService itemService;
    private final MemberService memberService;
    @PostConstruct
    public void init() {
        itemService.save(new Item("Chocolate", 10, LocalDate.now()));
        itemService.save(new Item("Milk", 20, LocalDate.of(2023, 10, 1)));
        Member member = new Member("test", "test", "test@gmail.com", "test!");
        memberService.save(member);
    }
}
