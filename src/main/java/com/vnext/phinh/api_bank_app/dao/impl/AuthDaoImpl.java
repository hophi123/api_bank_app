/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.vnext.phinh.api_bank_app.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.vnext.phinh.api_bank_app.bean.UserEntity;
import com.vnext.phinh.api_bank_app.dao.AuthDao;
import com.vnext.phinh.api_bank_app.service.impl.UserServiceImpl;

/**
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
public class AuthDaoImpl implements AuthDao {

    @PersistenceContext
    private EntityManager entityManager;

    private static final Log log = LogFactory.getLog(UserServiceImpl.class);

    /**
     * checkLogin
     * @author: (VNEXT) PhiNH
     * @param phone_number
     * @param password
     * @return UserEntity userEntity
     */
    @Override
    public UserEntity checkLogin(String phone_number, String password) {
        log.debug("### checkLogin start ###");
        UserEntity userEntity = null;
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT u ");
        sql.append(" FROM ");
        sql.append("    UserEntity u ");
        sql.append(" WHERE u.phone_number = :phone_number ");
        sql.append(" AND ");
        sql.append("    u.password = :password");
        Query query = this.entityManager.createQuery(sql.toString());
        query.setParameter("phone_number", phone_number).setParameter("password", password);
        try {
            userEntity = (UserEntity) query.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        log.debug("### checkLogin end ###");
        return userEntity;
    }

}
