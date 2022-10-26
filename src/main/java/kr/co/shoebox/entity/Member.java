package kr.co.shoebox.entity;

import kr.co.shoebox.constant.Role;
import kr.co.shoebox.dto.MemberFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="member")
@Getter @Setter
@ToString
public class Member extends BaseEntity {

    @Id
    @Column(name="member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String postcode;

    private String roadAddress;

    private String detailAddress;

    private int question;

    private String answer;

    @Column(unique = true)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL
            , orphanRemoval = true, fetch = FetchType.LAZY)
    private Cart cart;

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL
            , orphanRemoval = true, fetch = FetchType.LAZY)
    private Wish wish;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL
            , orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();

    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder){
        Member member = new Member();
        member.setName(memberFormDto.getName());
        member.setEmail(memberFormDto.getEmail());
        member.setPostcode(memberFormDto.getPostcode());
        member.setRoadAddress(memberFormDto.getRoadAddress());
        member.setDetailAddress(memberFormDto.getDetailAddress());
        member.setPhoneNumber(memberFormDto.getPhoneNumber());
        member.setQuestion(memberFormDto.getQuestion());
        member.setAnswer(memberFormDto.getAnswer());
        String password = passwordEncoder.encode(memberFormDto.getPassword());
        member.setPassword(password);
        member.setRole(Role.USER);
        return member;
    }

}
