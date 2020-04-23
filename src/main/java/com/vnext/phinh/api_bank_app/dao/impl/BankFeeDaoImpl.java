/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.vnext.phinh.api_bank_app.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.vnext.phinh.api_bank_app.bean.BankFeeEntity;
import com.vnext.phinh.api_bank_app.dao.BankFeeDao;
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
public class BankFeeDaoImpl implements BankFeeDao {

    @PersistenceContext
    private EntityManager entityManager;

    private static final Log log = LogFactory.getLog(UserServiceImpl.class);

    /**
     * getTransFeeById
     * @author: (VNEXT) PhiNH
     * @param id
     * @return BankFeeEntity
     */
    @Override
    public BankFeeEntity getTransFeeById(int id) {
        log.debug("### getTransFeeById start ###");
        BankFeeEntity bankFeeEntity = null;
        bankFeeEntity = entityManager.find(BankFeeEntity.class, id);
        log.debug("### getTransFeeById end ###");
        return bankFeeEntity;
    }

}
