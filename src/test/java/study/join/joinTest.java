package study.join;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.junit.Test;
import util.DBUtil;

public class joinTest {

    @Test
    public void run() {
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            em = DBUtil.getEntityManager();
            tx = em.getTransaction();
            tx.begin();

            List<Dept> depts = IntStream.rangeClosed(1, 3).mapToObj(i -> {
                return new Dept("dept" + i, "loc" + i);
            }).collect(Collectors.toList());

            for (var d : depts) {
                em.persist(d);
            }

            List<Emp> emps = IntStream.rangeClosed(1, 10).mapToObj(i -> {
                return new Emp("emp" + i, depts.get((int) (Math.random() * 10 % depts.size())));
            }).collect(Collectors.toList());

            for (var e : emps) {
                em.persist(e);
            }

            tx.commit();

            tx.begin();
            Emp emp1= em.find(Emp.class,(long)1);
            System.out.println(emp1.getEname());
            System.out.println(emp1.getDeptId().getDname());
            System.out.println(emp1.getDeptId().getLoc());
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
                em = null;
            }
        }
    }
}
