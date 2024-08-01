package study.join;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Dept {
    @Id
    @Column(name = "DEPTNO")
    private long deptno;

    @Column(name = "DNAME", length = 20)
    private String dname;

    @Column(name = "LOC", length = 20)
    private String loc;

    @OneToMany(mappedBy = "dept")
    private Set<Emp> empDatas = new HashSet<>();
}
