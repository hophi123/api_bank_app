/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.vnext.phinh.api_bank_app.service.impl;

/**
* 
*
*/

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vnext.phinh.api_bank_app.bean.BankFeeEntity;
import com.vnext.phinh.api_bank_app.dao.BankFeeDao;
import com.vnext.phinh.api_bank_app.service.BankFeeService;

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
public class BankFeeServiceImpl implements BankFeeService {

    @Autowired
    private BankFeeDao bankfeeDao;

    private static final Log log = LogFactory.getLog(UserServiceImpl.class);

    /**
     * getTransFeeByiId
     * @author (VNEXT) PhiNH
     * @param id
     * @return BankFeeEntity bankFeeEntity;
     */
    @Override
    public BankFeeEntity getTransFeeByiId(int id) {
        log.debug("### getTransFeeByiId start ###");
        BankFeeEntity bankFeeEntity = null;
        bankFeeEntity = bankfeeDao.getTransFeeById(id);
        log.debug("### getTransFeeByiId end ###");
        return bankFeeEntity;
    }

}
