package com.hdn.daoimp;

import com.hdn.dao.CategoryDao;
import com.hdn.entity.Cate_User_Entity;
import com.hdn.entity.CategoryEntity;
import com.hdn.entity.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class CategoryImp implements CategoryDao {
	
    @Autowired
    SessionFactory sessionFactory;

    public CategoryEntity getCategory(Long id) {
        Session session  = sessionFactory.getCurrentSession();
        try {
            Query query = session.createQuery("from CategoryEntity where id = :id");
            query.setLong("id",id);
            CategoryEntity categoryEntity = (CategoryEntity) query.uniqueResult();
            return categoryEntity;
        } catch ( Exception e) {
            return null;
        }
    }

    @Transactional
    public List<CategoryEntity> findAll() {
        Session session  = sessionFactory.getCurrentSession();
        try {
            Query query = session.createQuery("from CategoryEntity");
            List<CategoryEntity> categoryEntities = query.getResultList();
            return categoryEntities;
        } catch ( Exception e) {
            return null;
        }
    }

    
    public Integer addCategory(CategoryEntity categoryEntity) {
        Session session  = sessionFactory.getCurrentSession();
        try {
            Integer categoryId = (Integer)session.save(categoryEntity);
            return categoryId;
        } catch ( Exception e) {
            return null;
        }
    }

    public List<CategoryEntity> findCourseByUserId(Long userId) {

        Session session  = sessionFactory.getCurrentSession();
        try {
            Query query = session.createQuery("from CategoryEntity c WHERE c.userEntity.id = :userId");
            query.setParameter("userId", userId);
            List<CategoryEntity> categoryEntities = query.getResultList();
            return categoryEntities;
        } catch ( Exception e) {
            return null;
        }
    }

}
