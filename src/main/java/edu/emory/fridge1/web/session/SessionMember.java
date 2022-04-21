package edu.emory.fridge1.web.session;

import edu.emory.fridge1.domain.member.Member;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class SessionMember implements Serializable {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    public SessionMember(Member member) {
        this.firstName = member.getFirstName();
        this.lastName = member.getLastName();
        this.email = member.getEmail();
        this.password = member.getPassword();
    }
}
