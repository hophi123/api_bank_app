/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.vnext.phinh.api_bank_app.dao.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.vnext.phinh.api_bank_app.bean.TransactionEntity;
import com.vnext.phinh.api_bank_app.dao.TransactionDao;
import com.vnext.phinh.api_bank_app.model.TransHistoryResponse;
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
public class TransactionDaoImpl implements TransactionDao {

    @PersistenceContext
    private EntityManager entityManager;

    private static final Log log = LogFactory.getLog(UserServiceImpl.class);

    /**
     * getListTransaction
     * @author: (VNEXT) PhiNH
     * @param id
     * @param dateFrom
     * @param dateTo
     * @return List<TransactionEntity>
     * @throws ParseException
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<TransactionEntity> getListTransaction(Integer id, String dateFrom, String dateTo) throws ParseException {
        log.debug("### getListTransaction start ###");
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT t ");
        sql.append(" FROM ");
        sql.append("    TransactionEntity t ");
        sql.append(" WHERE t.id_user_from = :id_user_from ");
        sql.append(" AND ");
        sql.append("    t.date > :date1 ");
        sql.append(" AND ");
        sql.append("    t.date < :date2 ");
        Query query = this.entityManager.createQuery(sql.toString());
        query.setParameter("id_user_from", id).setParameter("date1", dateFrom).setParameter("date2", dateTo);
        List<TransactionEntity> listTransaction = new ArrayList<TransactionEntity>();
        listTransaction = query.getResultList();
        log.debug("### getListTransaction end ###");
        return listTransaction;
    }

    /**
     * createTrans
     * @author: (VNEXT) PhiNH
     * @param transactionEntity
     */
    @Override
    public void createTrans(TransactionEntity transactionEntity) {
        log.debug("### createTrans start ###");
        log.debug("### createTrans end ###");
        entityManager.persist(transactionEntity);
    }

    /**
     * getListTransHistory
     * @author: (VNEXT) PhiNH
     * @param id
     * @return List<TransactionEntity> listTrans
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<TransactionEntity> getListTransHistory(Integer id) {
        log.debug("### getListTransHistory start ###");
        StringBuilder sql = new StringBuilder();
        List<TransactionEntity> listTrans = new ArrayList<TransactionEntity>();
        sql.append(" SELECT t");
        sql.append(" FROM ");
        sql.append("    TransactionEntity t ");
        sql.append(" WHERE ");
        sql.append("    t.id_user_from = :id_user_from");
        sql.append(" AND( ");
        sql.append("    t.action = 0 ");
        sql.append(" OR ");
        sql.append("    t.action = 1) ");
        Query query = this.entityManager.createQuery(sql.toString());
        query.setParameter("id_user_from", id);
        listTrans = query.getResultList();
        log.debug("### listTrans end ###");
        return listTrans;
    }

    /**
     * getListTrans
     * @author: (VNEXT) PhiNH
     * @param id
     * @return List<TransHistoryResponse> listTrans
     */
    @SuppressWarnings("unchecked")
    public List<TransHistoryResponse> getListTrans(Integer id) {
        log.debug("### getListTrans start ###");
        StringBuilder sql = new StringBuilder();
        List<TransHistoryResponse> listTrans = new ArrayList<TransHistoryResponse>();
        sql.append(" SELECT new com.vnext.phinh.api_bank_app.model.TransHistoryResponse(");
        sql.append(" b.name, ");
        sql.append(" t.action, ");
        sql.append(" t.date, ");
        sql.append(" t.trans_amount) ");
        sql.append(" FROM ");
        sql.append("    TransactionEntity AS t ");
        sql.append(" JOIN BankEntity AS b ");
        sql.append("    ON t.id_bank_from = b.id");
        sql.append(" WHERE ");
        sql.append("    t.id_user_from = :id_user_from");
        sql.append(" AND( ");
        sql.append("    t.action = 0 ");
        sql.append(" OR ");
        sql.append("    t.action = 1) ");
        Query query = this.entityManager.createQuery(sql.toString());
        query.setParameter("id_user_from", id);
        listTrans = query.getResultList();
        log.debug("### getListTrans end ###");
        return listTrans;
    }

    /**
     * getListTransfer
     * @author: (VNEXT) PhiNH
     * @param id
     * @return List<TransactionEntity> listTrans
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<TransactionEntity> getListTransfer(Integer id) {
        log.debug("### getListTransfer start ###");
        StringBuilder sql = new StringBuilder();
        List<TransactionEntity> listTrans = new ArrayList<TransactionEntity>();
        sql.append(" SELECT t");
        sql.append(" FROM ");
        sql.append("    TransactionEntity t ");
        sql.append(" WHERE( ");
        sql.append("    t.id_user_from = :id_user_from ");
        sql.append(" OR ");
        sql.append("    t.id_user_to = :id_user_to) ");
        sql.append(" AND ");
        sql.append("    t.action = 2 ");
        Query query = this.entityManager.createQuery(sql.toString());
        query.setParameter("id_user_from", id).setParameter("id_user_to", id);
        listTrans = query.getResultList();
        log.debug("### getListTransfer end ###");
        return listTrans;
    }

    /**
     * getListTransfer
     * @author: (VNEXT) PhiNH
     * @param id_user, id_bank
     * @return List<TransactionEntity> listTrans
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<TransactionEntity> getListTransfer(Integer id_user, Integer id_bank) {
        log.debug("### getListTransfer start ###");
        StringBuilder sql = new StringBuilder();
        List<TransactionEntity> listTrans = new ArrayList<TransactionEntity>();
        sql.append(" SELECT t");
        sql.append(" FROM ");
        sql.append("    TransactionEntity t ");
        sql.append(" WHERE ");
        sql.append("    t.id_user_from = :id_user_from ");
        sql.append(" AND ");
        sql.append(" t.id_bank_to = :id_bank_to ");
        sql.append(" AND ");
        sql.append("    t.action = 2 ");
        Query query = this.entityManager.createQuery(sql.toString());
        query.setParameter("id_user_from", id_user).setParameter("id_bank_to", id_bank);
        listTrans = query.getResultList();
        log.debug("### getListTransfer end ###");
        return listTrans;
    }

}
