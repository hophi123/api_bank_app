/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.vnext.phinh.api_bank_app.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vnext.phinh.api_bank_app.bean.BankEntity;
import com.vnext.phinh.api_bank_app.bean.ResultBean;
import com.vnext.phinh.api_bank_app.dao.BankDao;
import com.vnext.phinh.api_bank_app.service.BankService;
import com.vnext.phinh.api_bank_app.utils.ApiValidateException;

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
@Service
@Transactional
public class BankServiceImpl implements BankService {

    @Autowired
    private BankDao bankDao;

    private static final Log log = LogFactory.getLog(UserServiceImpl.class);

    /**
     * getListBank
     * @author (VNEXT) PhiNH
     * @return ResultBean(listBank, "200", "OKOK")
     */
    @Override
    public ResultBean getAll() throws ApiValidateException {
        log.debug("### getListBank start ###");
        List<BankEntity> listBank = new ArrayList<BankEntity>();
        listBank = bankDao.getAll();
        if (listBank == null) {
            throw new ApiValidateException("400", "List Bank Entity is not exist");
        }
        log.debug("### getListBank end ###");
        return new ResultBean(listBank, "200", "OKOK");
    }

    /**
     * findBankById
     * @author (VNEXT) PhiNH
     * @param id
     * @return BankEntity bankEntity
     */
    @Override
    public BankEntity findBankById(Integer id) {
        log.debug("### findBankById start ###");
        BankEntity bankEntity = null;
        bankEntity = bankDao.findBankById(id);
        log.debug("### findBankById end ###");
        return bankEntity;
    }

}
