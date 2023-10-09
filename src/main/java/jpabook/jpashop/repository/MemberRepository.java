package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;


    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        // 첫 번째 파라미터 : 타입, 두 번째 파라미터 : primary key
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        // table이 아닌 entity(member)에 대해 진행하는 JPQL query
        return em.createQuery("select m from Member as m",Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member as m where m.name=:name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
