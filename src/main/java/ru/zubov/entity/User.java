package ru.zubov.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "user_data")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class User extends EntityAbstract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "EMAIL", nullable = false, length = 20)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "USERNAME", nullable = false)
    private String username;

    ////         optional = false - используется для указание ленивой загрузки для связи one to one
//    @OneToOne(mappedBy = "user", optional = false)
//    private Activity activity;
//
//    // optional = false - используется для указание ленивой загрузки для связи one to one
//    @OneToOne(mappedBy = "user", optional = false)
//    private Stat stat;
//
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(mappedBy = "users")
    private Set<Role> roles = new HashSet<>();
//
//    @OneToMany(mappedBy = "user")
//    private List<Category> categories;
//
//    @OneToMany(mappedBy = "user")
//    private List<Priority> priorities;
//
//    @OneToMany(mappedBy = "user")
//    private List<Task> tasks;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return this.getId().equals(user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }

    @Override
    public String toString() {
        return username;
    }
}
