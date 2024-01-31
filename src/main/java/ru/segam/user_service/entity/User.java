package ru.segam.user_service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq_gen")
    @SequenceGenerator(name = "user_seq_gen", sequenceName = "user_seq",
            allocationSize = 1)
    @Column (name = "id")
    private long userId;

    @Column (name = "nick_name")
    private String nickName;

    @Column (name = "first_name")
    private String firstName;

    @Column (name = "email")
    private String email;

    @Column (name = "age")
    private int age;

    @Column (name = "password")
    private String password;
}
