package ru.zubov.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "user_data")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "email", nullable = false, length = 20)
    private String email;

    @Column(name = "user_password", nullable = false)
    private String password;

    @Column(name = "username", nullable = false)
    private String username;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Category> categories;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Priority> priorities;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Activity activity;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Stat stat;

@ManyToMany(fetch = FetchType.LAZY)
@JoinTable(name = "user_role",
        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;
}
