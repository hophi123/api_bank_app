/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.vnext.phinh.api_bank_app.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.vnext.phinh.api_bank_app.bean.BankEntity;
import com.vnext.phinh.api_bank_app.dao.BankDao;
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
public class BankDaoImpl implements BankDao {

    @PersistenceContext
    private EntityManager entityManager;

    private static final Log log = LogFactory.getLog(UserServiceImpl.class);

    /**
     * getAll
     * @author: (VNEXT) PhiNH
     * @return List<BankEntity>
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<BankEntity> getAll() {
        log.debug("### getAll start ###");
        log.debug("### getAll end ###");
        return entityManager.createQuery("FROM BankEntity").getResultList();
    }

    /**
     * findBankById
     * @author: (VNEXT) PhiNH
     * @param id
     * @return BankEntity bankEntity
     */
    @Override
    public BankEntity findBankById(Integer id) {
        log.debug("### findBankById start ###");
        BankEntity bankEntity = null;
        bankEntity = entityManager.find(BankEntity.class, id);
        log.debug("### findBankById end ###");
        return bankEntity;
    }

}
