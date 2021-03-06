package ua.repository.impl;

import static org.springframework.data.jpa.repository.query.QueryUtils.toOrders;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import ua.entity.ClothingModel;
import ua.entity.ClothingModel_;
import ua.entity.Season;
import ua.entity.SectionOfClothes;
import ua.entity.TypeOfClothes;
import ua.model.filter.ClothingModelFilter;
import ua.model.view.ClothingModelView;
import ua.repository.ClothingModelViewRepository;

@Repository
public class ClothingModelViewRepositoryImpl implements ClothingModelViewRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Page<ClothingModelView> findAllClothingModelView(ClothingModelFilter filter, Pageable pageable) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ClothingModelView> cq = cb.createQuery(ClothingModelView.class).distinct(true);
        Root<ClothingModel> root = cq.from(ClothingModel.class);
        Join<ClothingModel, Season> joinSeason = root.join(ClothingModel_.season);
        Join<ClothingModel, TypeOfClothes> joinType = root.join(ClothingModel_.typeOfClothes);
        Join<ClothingModel, SectionOfClothes> joinSection = root.join(ClothingModel_.sectionOfClothes);
        cq.multiselect(root.get(ClothingModel_.id), root.get("name"), root.get("text"),
                joinSeason.get("name"), joinType.get("name"), joinSection.get("name"), root.get("version"));
        Predicate predicate = new PredicateBuilder(cb, root, filter).toPredicate();
        if (predicate != null) cq.where(predicate);
        cq.orderBy(toOrders(pageable.getSort(), root, cb));
        List<ClothingModelView> content = em.createQuery(cq)
                .setFirstResult(pageable.getPageNumber() * pageable.getPageSize())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<ClothingModel> countRoot = countQuery.from(ClothingModel.class);
        countQuery.select(cb.count(countRoot));
        Predicate countPredicate = new PredicateBuilder(cb, countRoot, filter).toPredicate();
        if (countPredicate != null) countQuery.where(countPredicate);
        return PageableExecutionUtils.getPage(content, pageable, () -> em.createQuery(countQuery).getSingleResult());
    }

    private static class PredicateBuilder {

        final CriteriaBuilder cb;

        final Root<ClothingModel> root;

        final ClothingModelFilter filter;

        final List<Predicate> predicates = new ArrayList<>();

        PredicateBuilder(CriteriaBuilder cb, Root<ClothingModel> root, ClothingModelFilter filter) {
            this.cb = cb;
            this.root = root;
            this.filter = filter;
        }

        void findBySearch() {
            if (!filter.getSearch().isEmpty()) {
                predicates.add(cb.like(root.get("name"), filter.getSearch() + "%"));
            }
        }

        void findBySectionOfClothesId() {
            if (!filter.getSectionOfClothesName().isEmpty()) {
                Join<ClothingModel, SectionOfClothes> join = root.join(ClothingModel_.sectionOfClothes);
                predicates.add(join.get("name").in(filter.getSectionOfClothesName()));
            }
        }

        Predicate toPredicate() {
            findBySearch();
            findBySectionOfClothesId();
            return predicates.isEmpty() ? null : cb.and(predicates.toArray(new Predicate[0]));
        }

    }

}
