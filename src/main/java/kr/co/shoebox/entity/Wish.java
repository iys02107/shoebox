package kr.co.shoebox.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "wish")
@Getter @Setter
@ToString
public class Wish extends BaseEntity {

    @Id
    @Column(name = "wish_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    public static Wish createWish(Member member){
        Wish wish = new Wish();
        wish.setMember(member);
        return wish;
    }

}