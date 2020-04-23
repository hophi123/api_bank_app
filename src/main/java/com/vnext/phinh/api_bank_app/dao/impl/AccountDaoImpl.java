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

import com.vnext.phinh.api_bank_app.bean.AccountEntity;
import com.vnext.phinh.api_bank_app.dao.AccountDao;
import com.vnext.phinh.api_bank_app.service.impl.UserServiceImpl;

/**
 * [OVERVIEW] XXXXX.
 *
 * @author: (VNEXT) PhiNH
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2020/04/16      (VNEXT) PhiNH       Create new
*/
@Repository
@Transactional
public class AccountDaoImpl implements AccountDao {

    @PersistenceContext
    private EntityManager entityManager;

    private static final Log log = LogFactory.getLog(UserServiceImpl.class);

    /**
     * createAccount
     * @author: (VNEXT) PhiNH
     * @param accountEntity
     */
    @Override
    public void createAccount(AccountEntity accountEntity) {
        log.debug("### createAccount start ###");
        log.debug("### createAccount end ###");
        entityManager.persist(accountEntity);
    }

    /**
     * findAccount
     * @author: (VNEXT) PhiNH
     * @param id_user
     * @param id_bank
     * @return AccountEntity accountEntity
     */
    @Override
    public AccountEntity findAccount(Integer id_user, Integer id_bank) {
        log.debug("### findAccount start ###");
        AccountEntity accountEntity = null;
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT a ");
        sql.append("    FROM AccountEntity a ");
        sql.append(" WHERE a.id_user = :id_user ");
        sql.append(" AND ");
        sql.append("    a.id_bank = :id_bank ");
        Query query = this.entityManager.createQuery(sql.toString());
        query.setParameter("id_user", id_user).setParameter("id_bank", id_bank);
        try {
            accountEntity = (AccountEntity) query.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        log.debug("### findAccount end ###");
        return accountEntity;
    }

    /**
     * findAccount
     * @author: (VNEXT) PhiNH
     * @param id
     * @return AccountEntity accountEntity
     */
    @Override
    public AccountEntity findAccount(Integer id) {
        log.debug("### findAccount start ###");
        AccountEntity accountEntity = null;
        accountEntity = entityManager.find(AccountEntity.class, id);
        log.debug("### findAccount end ###");
        return accountEntity;
    }

    /**
     * updateAccount
     * @author: (VNEXT) PhiNH
     * @param accountEntity
     */
    @Override
    public void updateAccount(AccountEntity accountEntity) {
        log.debug("### updateAccount start ###");
        log.debug("### updateAccount end ###");
        entityManager.merge(accountEntity);
    }

}
