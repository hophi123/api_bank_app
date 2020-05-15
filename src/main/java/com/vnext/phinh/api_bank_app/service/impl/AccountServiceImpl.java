/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.vnext.phinh.api_bank_app.service.impl;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vnext.phinh.api_bank_app.bean.AccountEntity;
import com.vnext.phinh.api_bank_app.bean.UserEntity;
import com.vnext.phinh.api_bank_app.common.EncodeDecode;
import com.vnext.phinh.api_bank_app.dao.AccountDao;
import com.vnext.phinh.api_bank_app.dao.UserDao;
import com.vnext.phinh.api_bank_app.service.AccountService;
import com.vnext.phinh.api_bank_app.service.BankService;
import com.vnext.phinh.api_bank_app.utils.ApiValidateException;
import com.vnext.phinh.api_bank_app.utils.DataUtils;

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
@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private BankService bankService;
    @Autowired
    EncodeDecode encodeDecode;

    private static final Log log = LogFactory.getLog(UserServiceImpl.class);

    /**
     * createAccont
     * @author (VNEXT) PhiNH
     * @param json
     * @return AccountEntity accountEntity;
     */
    public AccountEntity createAccount(String json) throws ApiValidateException {
        log.debug("### createAccount start ###");
        AccountEntity accountEntity = new AccountEntity();
        JSONObject jsonAccount = new JSONObject(json);
        String phone = DataUtils.getPhoneByToken();
        UserEntity userEntity = userDao.findByPhoneNumber(phone);
        Integer idUser = userEntity.getId();
        if (jsonAccount.isEmpty()) {
            throw new ApiValidateException("400", "Please enter all field!");
        }

        if (bankService.findBankById(jsonAccount.getInt("id_bank")) == null) {
            throw new ApiValidateException("400", "id_bank", "Bank id does not exist!");
        }

        if (accountDao.findAccount(idUser, jsonAccount.getInt("id_bank")) != null) {
            throw new ApiValidateException("400", "account", "Account already exists!");
        } else {
            Integer id_bank = jsonAccount.getInt("id_bank");
            accountEntity.setId_user(idUser);
            accountEntity.setId_bank(id_bank);
            accountEntity.setBalance(0D);
            accountDao.createAccount(accountEntity);
            log.debug("### createAccount end ###");
            return accountEntity;
        }
    }

    /**
     * findAccount
     * @author (VNEXT) PhiNH
     * @param id_user, id_bank
     * @return AccountEntity accountEntity;
     */
    @Override
    public AccountEntity findAccount(Integer id_user, Integer id_bank) {
        log.debug("### findAccount start ###");
        AccountEntity accountEntity = null;
        accountEntity = accountDao.findAccount(id_user, id_bank);
        log.debug("### findAccount end ###");
        return accountEntity;
    }

    /**
     * findAccount
     * @author (VNEXT) PhiNH
     * @param id
     * @return AccountEntity accountEntity;
     */
    @Override
    public AccountEntity findAccount(Integer id) {
        log.debug("### findAccount start ###");
        AccountEntity accountEntity = new AccountEntity();
        accountEntity = accountDao.findAccount(id);
        log.debug("### findAccount end ###");
        return accountEntity;
    }

    /**
     * updateAccount
     * @author (VNEXT) PhiNH
     * @param accountEntity
     * @return AccountEntity accountEntity;
     */
    @Override
    public void updateAccount(AccountEntity accountEntity) {
        log.debug("### updateAccount start ###");
        log.debug("### updateAccount end ###");
        accountDao.updateAccount(accountEntity);
    }

}
