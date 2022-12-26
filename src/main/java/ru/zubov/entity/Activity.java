package ru.zubov.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "activity")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "activatied", nullable = false)
    @Basic
    @Convert(converter = org.hibernate.type.NumericBooleanConverter.class)
    private boolean activatied;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "uuid", nullable = false, length = Integer.MAX_VALUE)
    private String uuid;

}
