package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            /* 회원 저장
            Member member = new Member();
            member.setId(2L);
            member.setName("HelloB");

            em.persist(member); //'MEMBER' 테이블에 회원 저장
            */
            /* 회원 조회 및 수정
            Member findMember = em.find(Member.class, 1L);
            findMember.setName("HelloJPA");
            */

            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                            .getResultList();

            for(Member member : result) {
                System.out.println("member = " + member.getName());
            }

            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
