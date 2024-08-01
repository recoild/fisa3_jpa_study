package study.join;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Emp {
    @Id
    @Column(name = "EMPNO")
    private long empno;

    @Column(name = "ENAME", length = 20)
    private String ename;

    @Column(name = "JOB", length = 20)
    private String job;

    @Column(name = "MGR")
    private long mgr;

    @Column(name = "HIREDATE", length = 20)
    private LocalDate hiredate;

    @Column(name = "SAL")
    private long sal;

    @Column(name = "COMM")
    private long comm;

    @ManyToOne
    @JoinColumn(name = "DEPTNO", nullable = true)
    private Dept dept;
}
