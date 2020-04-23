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
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vnext.phinh.api_bank_app.bean.ResultBean;
import com.vnext.phinh.api_bank_app.bean.UserEntity;
import com.vnext.phinh.api_bank_app.dao.UserDao;
import com.vnext.phinh.api_bank_app.model.BalanceResponse;
import com.vnext.phinh.api_bank_app.service.AuthService;
import com.vnext.phinh.api_bank_app.service.UserService;
import com.vnext.phinh.api_bank_app.utils.ApiValidateException;
import com.vnext.phinh.api_bank_app.utils.RegexUtils;

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
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private AuthService authService;

    private static final Log log = LogFactory.getLog(UserServiceImpl.class);

    /**
     * createUser
     * @author (VNEXT) PhiNH
     * @param json
     * @return UserEntity userEntity
     */
    @Override
    public UserEntity createUser(String json) throws ApiValidateException {
        log.debug("### createUser start ###");
        JSONObject jsonUser = new JSONObject(json);
        if (jsonUser.isEmpty()) {
            throw new ApiValidateException("400", "Please enter all field!");
        } else if ((jsonUser.getString("name").trim().length() == 0) || (jsonUser.getString("name").trim().length() > 10)) {
            throw new ApiValidateException("400", "Name cannot be more than 10 character!");
        } else if (!jsonUser.getString("phone_number").trim().matches(RegexUtils.PHONENUMBER_PATTERN)) {
            throw new ApiValidateException("400", "Phone numbers from 10 to 11 numbers!");
        } else if (userDao.findByPhoneNumber(jsonUser.getString("phone_number")) != null) {
            throw new ApiValidateException("400", "Phone numbers already exist!");
        } else if (!jsonUser.getString("dateofbirth").trim().matches(RegexUtils.DATE_PATTERN)) {
            throw new ApiValidateException("400", "Date must be the correct format(Ex:1998/07/03!");
        } else if (!jsonUser.getString("password").trim().matches(RegexUtils.PASSWORD_PATTERN)) {
            throw new ApiValidateException("400", "Password must be the correct format(Ex:Abc@123)!");
        } else {
            UserEntity userEntity = new UserEntity();
            userEntity.setName(jsonUser.getString("name"));
            userEntity.setPhone_number(jsonUser.getString("phone_number"));
            userEntity.setDateofbirth(jsonUser.getString("dateofbirth"));
            userEntity.setPassword(jsonUser.getString("password"));
            userDao.createUser(userEntity);
            log.debug("### createUser end ###");
            return userEntity;
        }

    }

    /**
     * updateUser
     * @author (VNEXT) PhiNH
     * @param json
     * @return UserEntity userEntity
     */
    @Override
    public UserEntity updateUser(String json) throws ApiValidateException {
        log.debug("### updateUser start ###");
        JSONObject jsonUser = new JSONObject(json);
        Integer id = authService.checkLogin(jsonUser.getString("phone_number"), jsonUser.getString("password"));
        if (jsonUser.isEmpty()) {
            throw new ApiValidateException("400", "Please enter all field");
        } else if ((jsonUser.getString("new_name").trim().length() == 0) || (jsonUser.getString("new_name").trim().length() > 10)) {
            throw new ApiValidateException("400", "New name cannot be more than 10 character!");
        } else if (!jsonUser.getString("new_phone_number").trim().matches(RegexUtils.PHONENUMBER_PATTERN)) {
            throw new ApiValidateException("400", "New phone numbers from 10 to 11 numbers!");
        } else if (userDao.findOtherPhoneNumber(jsonUser.getString("new_phone_number"), id) != null) {
            throw new ApiValidateException("400", "New phone numbers already exist!");
        } else if (!jsonUser.getString("new_dateofbirth").trim().matches(RegexUtils.DATE_PATTERN)) {
            throw new ApiValidateException("400", "New date must be the correct format(Ex:1998/07/03!");
        } else if (!jsonUser.getString("new_password").trim().matches(RegexUtils.PASSWORD_PATTERN)) {
            throw new ApiValidateException("400", "New password must be the correct format(Ex:Abc@123)!");
        } else {
            UserEntity userEntity = userDao.findById(id);
            userEntity.setName(jsonUser.getString("new_name"));
            userEntity.setPhone_number(jsonUser.getString("new_phone_number"));
            userEntity.setDateofbirth(jsonUser.getString("new_dateofbirth"));
            userEntity.setPassword(jsonUser.getString("new_password"));
            userDao.updateUser(userEntity);
            log.debug("### updateUser end ###");
            return userEntity;
        }

    }

    /**
     * getBalance
     * @author (VNEXT) PhiNH
     * @param json
     * @return ResultBean(listBalanceResponse, "200", "Find success!")
     */
    @Override
    public ResultBean getBalance(String json) throws ApiValidateException {
        log.debug("### getBalance start ###");
        List<BalanceResponse> listBalanceResponse = new ArrayList<BalanceResponse>();
        JSONObject jsonUser = new JSONObject(json);
        int id = authService.checkLogin(jsonUser.getString("phone_number"), jsonUser.getString("password"));
        listBalanceResponse = userDao.getBalanceById(id);
        if (listBalanceResponse.isEmpty()) {
            throw new ApiValidateException("400", "User is not exist");
        } else {
            log.debug("### getBalance end ###");
            return new ResultBean(listBalanceResponse, "200", "Find success!");
        }

    }

    /**
     * findById
     * @author (VNEXT) PhiNH
     * @param id
     * @return UserEntity userEntity
     */
    @Override
    public UserEntity findById(Integer id) {
        log.debug("### findById start ###");
        UserEntity userEntity = null;
        userEntity = userDao.findById(id);
        log.debug("### findById end ###");
        return userEntity;
    }

    /**
     * updateUser
     * @author (VNEXT) PhiNH
     * @param userEntity
     */
    @Override
    public void updateUser(UserEntity userEntity) throws ApiValidateException {
        log.debug("### updateUser start ###");
        log.debug("### updateUser end ###");
        userDao.updateUser(userEntity);
    }

    /**
     * findByPhoneNumber
     * @author (VNEXT) PhiNH
     * @param phone_number
     * @return UserEntity userEntity
     */
    @Override
    public UserEntity findByPhoneNumber(String phone_number) {
        log.debug("### findByPhoneNumber start ###");
        UserEntity userEntity = null;
        userEntity = userDao.findByPhoneNumber(phone_number);
        log.debug("### findByPhoneNumber end ###");
        return userEntity;
    }

}
