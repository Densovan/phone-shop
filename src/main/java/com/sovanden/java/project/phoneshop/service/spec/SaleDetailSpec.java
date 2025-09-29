package com.sovanden.java.project.phoneshop.service.spec;

import com.sovanden.java.project.phoneshop.entity.SaleDetail;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class SaleDetailSpec implements Specification<SaleDetail> {
    private SaleDetailFilter saleDetailFilter;

    @Override
    public Predicate toPredicate(Root<SaleDetail> SaleDetail, CriteriaQuery<?> query, CriteriaBuilder cb) {
        return null;
    }
}
