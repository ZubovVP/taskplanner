package ru.zubov.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "user_data", schema = "taskplanner", catalog = "task_planner")
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
}
