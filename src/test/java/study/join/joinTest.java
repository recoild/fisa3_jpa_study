package study.join;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.junit.Test;
import util.DBUtil;

public class joinTest {
    @Test
    public void empCreateTest() {
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            em = DBUtil.getEntityManager();
            tx = em.getTransaction();
            tx.begin();

            LocalDate hireDate = LocalDate.of(2024, 8, 1);

            Emp emp = new Emp(9000, "emp9000", "job9000", 9000, hireDate, 9000, 9000, null);
            em.persist(emp);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
                em = null;
            }
        }
    }

    @Test
    public void empReadTest() {
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            em = DBUtil.getEntityManager();
            tx = em.getTransaction();
            tx.begin();

            Emp emp1 = em.find(Emp.class, 9000L);
            Optional<Emp> data = Optional.ofNullable(emp1);
            data.ifPresentOrElse(e -> {
                System.out.println(e.getEname());

                Optional<Dept> dept = Optional.ofNullable(e.getDept());
                dept.ifPresent(d -> System.out.println(d.getDname()));
            }, () -> System.out.println("No data"));

            tx.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
                em = null;
            }
        }
    }

    @Test
    public void empUpdateDeptTest() {
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            em = DBUtil.getEntityManager();
            tx = em.getTransaction();
            tx.begin();

            Emp emp1 = em.find(Emp.class, 9000L);
            List<Dept> dept = em.createQuery("select d from Dept d", Dept.class).getResultList();
            int randNum = (int) (Math.random() * 10 % dept.size());

            emp1.setDept(dept.get(randNum));
            em.persist(emp1);

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
                em = null;
            }
        }
    }

    @Test
    public void empDeleteTest() {
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            em = DBUtil.getEntityManager();
            tx = em.getTransaction();
            tx.begin();

            Emp emp1 = em.find(Emp.class, 9000L);
            em.remove(emp1);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
                em = null;
            }
        }
    }

    @Test
    public void deptCreateTest() {
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            em = DBUtil.getEntityManager();
            tx = em.getTransaction();
            tx.begin();

            Dept dept = new Dept(90, "dept90", "loc90", null);
            em.persist(dept);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
                em = null;
            }
        }
    }

    @Test
    public void deptReadTest() {
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            em = DBUtil.getEntityManager();
            tx = em.getTransaction();
            tx.begin();

            Dept dept1 = em.find(Dept.class, 90L);

            Optional<Dept> data = Optional.ofNullable(dept1);
            data.ifPresentOrElse(d -> {
                System.out.println(d.getDname());

                Optional<Set<Emp>> empDatas = Optional.ofNullable(d.getEmpDatas());
                empDatas.ifPresent(e -> e.forEach(emp -> System.out.println(emp.getEname())));
            }, () -> System.out.println("No data"));
            tx.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
                em = null;
            }
        }
    }

    @Test
    public void deptUpdateTest() {
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            em = DBUtil.getEntityManager();
            tx = em.getTransaction();
            tx.begin();

            Dept dept1 = em.find(Dept.class, 90L);
            dept1.setLoc("00000000");
            em.persist(dept1);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
                em = null;
            }
        }
    }

    @Test
    public void deptDeleteTest() {
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            em = DBUtil.getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            
            Set<Emp> empDatas = em.find(Dept.class, 90L).getEmpDatas();
            Optional<Set<Emp>> data = Optional.ofNullable(empDatas);
            if(data.isPresent()) {
                for(Emp emp : empDatas) {
                    emp.setDept(null);
                    em.persist(emp);
                }
            }
                

            Dept dept1 = em.find(Dept.class, 90L);
            em.remove(dept1);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
                em = null;
            }
        }
    }


}
