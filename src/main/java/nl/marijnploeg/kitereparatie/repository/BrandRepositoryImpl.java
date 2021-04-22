package nl.marijnploeg.kitereparatie.repository;

import nl.marijnploeg.kitereparatie.model.Brand;
import nl.marijnploeg.kitereparatie.model.BrandType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public class BrandRepositoryImpl implements CustomBrandRepository{

    @Autowired
    EntityManager entityManager;

    @Override
    public Brand getBrandByName(String brandName) {
        String sql = "SELECT b FROM Brand b WHERE b.brandName=:brandname";
        final TypedQuery<Brand> query = entityManager.createQuery(sql, Brand.class);
        query.setParameter("brandname", brandName);

        return query.getSingleResult();
    }

    @Override
    public boolean existsByName(String brandName) {
        return false;
    }

    @Override
    public void deleteBrand(String brandName) {
        String sql = "DELETE FROM Brand WHERE brandName=:brandname";
        final TypedQuery<Brand> query = entityManager.createQuery(sql, Brand.class);
        query.setParameter("brandname", brandName);
    }

    @Override
    public boolean existsByType(BrandType brandType) {
//        String sql = "SELECT bt FROM Brand bt WHERE brandType=:brandtype";
//        final TypedQuery<Brand> query = entityManager.createQuery(sql, Brand.class);
//        query.setParameter("brandtype", brandType);
        return false;
    }

    @Override
    public List<Brand> getBrandsByType(BrandType brandType) {
        String sql = "SELECT b FROM Brand b WHERE b.brandType=:brandtype";
        final TypedQuery<Brand> query = entityManager.createQuery(sql, Brand.class);
        query.setParameter("brandtype", brandType);

        return query.getResultList();
    }
}
