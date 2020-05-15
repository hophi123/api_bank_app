/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.vnext.phinh.api_bank_app.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.vnext.phinh.api_bank_app.bean.UserEntity;
import com.vnext.phinh.api_bank_app.dao.UserDao;
import com.vnext.phinh.api_bank_app.response.BalanceResponse;
import com.vnext.phinh.api_bank_app.service.impl.UserServiceImpl;

/**
 * 
 * [OVERVIEW] XXXXX.
 *
 * @author: (VNEXT) PhiNH
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2020/04/15      (VNEXT) PhiNH       Create new
 */
@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    private static final Log log = LogFactory.getLog(UserServiceImpl.class);

    /**
     * createUser
     * @author: (VNEXT) PhiNH
     * @param userEntity
     */
    @Override
    public void createUser(UserEntity userEntity) {
        log.debug("### createUser start ###");
        entityManager.persist(userEntity);
        log.debug("### createUser end ###");
    }

    /**
     * updateUser
     * @author: (VNEXT) PhiNH
     * @param userEntity
     */
    @Override
    public void updateUser(UserEntity userEntity) {
        log.debug("### updateUser start ###");
        entityManager.merge(userEntity);
        log.debug("### updateUser end ###");
    }

    /**
     * findById
     * @author: (VNEXT) PhiNH
     * @param id
     * @return UserEntity
     */
    @Override
    public UserEntity findById(int id) {
        log.debug("### findById start ###");
        log.debug("### findById end ###");
        return entityManager.find(UserEntity.class, id);
    }

    /**
     * getBalanceById
     * @author: (VNEXT) PhiNH
     * @param id
     * @return List<BalanceResponse>
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<BalanceResponse> getBalanceById(int id) {
        log.debug("### getBalanceById start ###");
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT new com.vnext.phinh.api_bank_app.response.BalanceResponse( ");
        sql.append("    u.name, ");
        sql.append(" a.balance, ");
        sql.append("    b.name As bank_name) ");
        sql.append(" FROM UserEntity AS u ");
        sql.append(" JOIN AccountEntity AS a ");
        sql.append("    ON u.id = a.id_user ");
        sql.append(" JOIN BankEntity AS b ");
        sql.append("    ON b.id = a.id_bank ");
        sql.append(" WHERE ");
        sql.append("    u.id = :id ");
        Query query = this.entityManager.createQuery(sql.toString());
        query.setParameter("id", id);
        List<BalanceResponse> listBalanceResponse = new ArrayList<BalanceResponse>();
        listBalanceResponse = query.getResultList();
        log.debug("### getBalanceById end ###");
        return listBalanceResponse;
    }

    /**
     * findByPhoneNumber
     * @author: (VNEXT) PhiNH
     * @param phone_number
     * @return UserEntity
     */
    @Override
    public UserEntity findByPhoneNumber(String phone_number) {
        log.debug("### findByPhoneNumber start ###");
        UserEntity userEntity = null;
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT u ");
        sql.append(" FROM ");
        sql.append("    UserEntity u ");
        sql.append(" WHERE ");
        sql.append("    u.phone_number = :phone_number ");
        Query query = this.entityManager.createQuery(sql.toString());
        query.setParameter("phone_number", phone_number);
        try {
            userEntity = (UserEntity) query.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        log.debug("### findByPhoneNumber end ###");
        return userEntity;
    }

    /**
     * findOtherPhoneNumber
     * @author: (VNEXT) PhiNH
     * @param phone_number
     * @param id
     * @return UserEntity
     */
    @Override
    public UserEntity findOtherPhoneNumber(String phone_number, Integer id) {
        log.debug("### findOtherPhoneNumber start ###");
        UserEntity userEntity = null;
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT u ");
        sql.append(" FROM ");
        sql.append("    UserEntity u ");
        sql.append(" WHERE ");
        sql.append("    u.phone_number = :phone_number ");
        sql.append(" AND ");
        sql.append("    u.id != :id ");
        Query query = this.entityManager.createQuery(sql.toString());
        query.setParameter("phone_number", phone_number);
        query.setParameter("id", id);
        try {
            userEntity = (UserEntity) query.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        log.debug("### findOtherPhoneNumber end ###");
        return userEntity;
    }

    @Override
    public UserEntity findUserByName(String name) {
        log.debug("### findUserByName start ###");
        UserEntity userEntity = null;
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT u ");
        sql.append(" FROM ");
        sql.append("    UserEntity u ");
        sql.append(" WHERE ");
        sql.append("    u.name = :name ");
        Query query = this.entityManager.createQuery(sql.toString());
        query.setParameter("name", name);
        try {
            userEntity = (UserEntity) query.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
        log.debug("### findUserByName end ###");
        return userEntity;
    }

}
