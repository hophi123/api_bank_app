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

import com.vnext.phinh.api_bank_app.bean.UserEntity;
import com.vnext.phinh.api_bank_app.dao.AuthDao;
import com.vnext.phinh.api_bank_app.service.AuthService;
import com.vnext.phinh.api_bank_app.service.UserService;
import com.vnext.phinh.api_bank_app.utils.ApiValidateException;
import com.vnext.phinh.api_bank_app.utils.RegexUtils;

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
@Service
@Transactional
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthDao authdao;
    @Autowired
    private UserService userService;

    private static final Log log = LogFactory.getLog(UserServiceImpl.class);

    /**
     * checkLogin
     * @author (VNEXT) PhiNH
     * @param json
     * @return UserEntity userEntity
     */
    @Override
    public UserEntity checkLogin(String json) throws ApiValidateException {
        log.debug("### checkLogin start ###");
        UserEntity userEntity = null;
        JSONObject jsonAuth = new JSONObject(json);
        if (jsonAuth.isEmpty()) {
            throw new ApiValidateException("400", "Please enter all field!");
        } else if (userService.findByPhoneNumber(jsonAuth.getString("phone_number")) == null) {
            throw new ApiValidateException("400", "id", "Phone numbers does not exist!");
        } else if (!jsonAuth.getString("password").trim().matches(RegexUtils.PASSWORD_PATTERN)) {
            throw new ApiValidateException("400", "id", "Password must be the correct format(Ex:Abc@123)!");
        } else if (authdao.checkLogin(jsonAuth.getString("phone_number"), jsonAuth.getString("password")) == null) {
            throw new ApiValidateException("400", "phone_number or password", "Incorrect phone numbers or password!");
        } else {
            String phone_number = jsonAuth.getString("phone_number");
            String password = jsonAuth.getString("password");
            userEntity = authdao.checkLogin(phone_number, password);
            log.debug("### checkLogin end ###");
            return userEntity;
        }

    }

    /**
     * checkLogin
     * @author (VNEXT) PhiNH
     * @param phone_number, password
     * @return UserEntity userEntity
     */
    @Override
    public Integer checkLogin(String phone_number, String password) throws ApiValidateException {
        log.debug("### checkLogin start ###");
        UserEntity userEntity = null;
        if (userService.findByPhoneNumber(phone_number) == null) {
            throw new ApiValidateException("400", "id", "Phone numbers does not exist!");
        } else if (!password.matches(RegexUtils.PASSWORD_PATTERN)) {
            throw new ApiValidateException("400", "password", "Password must be the correct format(Ex:Abc@123)!");
        } else if (authdao.checkLogin(phone_number, password) == null) {
            throw new ApiValidateException("400", "phone_number or password", "Incorrect phone numbers or password!");
        } else {
            userEntity = authdao.checkLogin(phone_number, password);
            log.debug("### checkLogin end ###");
            return userEntity.getId();
        }
    }

}