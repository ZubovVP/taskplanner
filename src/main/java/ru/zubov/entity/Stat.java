package ru.zubov.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.Objects;

@Entity
@Table(name = "stat")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Stat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "COMPLETED_TOTAL", updatable = false)
    private Long completedTotal;


    @Column(name = "UNCOMPLETED_TOTAL", updatable = false)
    private Long uncompletedTotal;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @MapsId     //используется для ленивой загрузки для связи @OneToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stat stat = (Stat) o;
        return id.equals(stat.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Stat{" +
                "completedTotal=" + completedTotal +
                ", uncompletedTotal=" + uncompletedTotal +
                '}';
    }
}
