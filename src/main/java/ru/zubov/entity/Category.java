package ru.zubov.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="category", schema = "taskplanner", catalog = "task_planner")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "completed_count", updatable = false)
    private Long completedCount;

    @Column(name = "uncompleted_count", updatable = false)
    private Long uncompletedCount;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
