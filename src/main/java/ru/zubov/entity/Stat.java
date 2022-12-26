package ru.zubov.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "stat")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Stat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "completed_total", updatable = false)
    private Long completedTotal;


    @Column(name = "uncompleted_total",updatable = false)
    private Long uncompletedTotal;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

}
