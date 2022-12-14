package ru.zubov.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Getter
@Setter
public class UserData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "email", nullable = false, length = 20)
    private String email;

    @Column(name = "user_password", nullable = false, length = Integer.MAX_VALUE)
    private String userPassword;

    @Column(name = "username", nullable = false, length = Integer.MAX_VALUE)
    private String username;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserData userData = (UserData) o;
        return id != null && Objects.equals(id, userData.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
