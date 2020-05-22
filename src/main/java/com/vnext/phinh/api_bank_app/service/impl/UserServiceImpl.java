/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.vnext.phinh.api_bank_app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.vnext.phinh.api_bank_app.bean.ResultBean;
import com.vnext.phinh.api_bank_app.bean.UserEntity;
import com.vnext.phinh.api_bank_app.common.EncodeDecode;
import com.vnext.phinh.api_bank_app.dao.UserDao;
import com.vnext.phinh.api_bank_app.dao.UserRepositoty;
import com.vnext.phinh.api_bank_app.response.BalanceResponse;
import com.vnext.phinh.api_bank_app.response.JwtResponse;
import com.vnext.phinh.api_bank_app.service.UserService;
import com.vnext.phinh.api_bank_app.utils.ApiValidateException;
import com.vnext.phinh.api_bank_app.utils.DataUtils;
import com.vnext.phinh.api_bank_app.utils.JwtTokenUtil;
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
    private UserRepositoty userRepositoty;
    @Autowired
    private UserDetailsService detailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private EncodeDecode encodeDecode;

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
        }

        if ((jsonUser.getString("name").trim().length() == 0) || (jsonUser.getString("name").trim().length() > 10)) {
            throw new ApiValidateException("400", "Name cannot be more than 10 character!");
        }

        if (!jsonUser.getString("phone_number").trim().matches(RegexUtils.PHONENUMBER_PATTERN)) {
            throw new ApiValidateException("400", "Phone numbers from 10 to 11 numbers!");
        }

        if (userDao.findByEmail(jsonUser.getString("email")) != null) {
            throw new ApiValidateException("400", "Email already exist!");
        }

        if (!jsonUser.getString("dateofbirth").trim().matches(RegexUtils.DATE_PATTERN)) {
            throw new ApiValidateException("400", "Date must be the correct format(Ex:1998/07/03!)");
        }

        if (!jsonUser.getString("password").trim().matches(RegexUtils.PASSWORD_PATTERN)) {
            throw new ApiValidateException("400", "Password must be the correct format(Ex:Abc@123) and must not exceed 8 characters!");
        } else {
            UserEntity userEntity = new UserEntity();
            userEntity.setName(jsonUser.getString("name"));
            userEntity.setPhone_number(jsonUser.getString("phone_number"));
            userEntity.setDateofbirth(jsonUser.getString("dateofbirth"));
            userEntity.setPassword(this.encodeDecode.encode(jsonUser.getString("password")));
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
        String email = DataUtils.getEmailByToken();
        UserEntity userEntity = userDao.findByEmail(email);
        Integer idUser = userEntity.getId();
        if (jsonUser.isEmpty()) {
            throw new ApiValidateException("400", "Please enter all field");
        }

        if ((jsonUser.getString("new_name").trim().length() == 0) || (jsonUser.getString("new_name").trim().length() > 10)) {
            throw new ApiValidateException("400", "New name cannot be more than 10 character!");
        }

        if (!jsonUser.getString("new_phone_number").trim().matches(RegexUtils.PHONENUMBER_PATTERN)) {
            throw new ApiValidateException("400", "New phone numbers from 10 to 11 numbers!");
        }

        if (userDao.findOtherPhoneNumber(jsonUser.getString("new_phone_number"), idUser) != null) {
            throw new ApiValidateException("400", "New phone numbers already exist!");
        }

        if (!jsonUser.getString("new_dateofbirth").trim().matches(RegexUtils.DATE_PATTERN)) {
            throw new ApiValidateException("400", "New date must be the correct format(Ex:1998/07/03!");
        }

        if (!jsonUser.getString("new_password").trim().matches(RegexUtils.PASSWORD_PATTERN)) {
            throw new ApiValidateException("400", "New password must be the correct format(Ex:Abc@123)!");
        } else {
            userEntity = userDao.findById(idUser);
            userEntity.setName(jsonUser.getString("new_name"));
            userEntity.setPhone_number(jsonUser.getString("new_phone_number"));
            userEntity.setDateofbirth(jsonUser.getString("new_dateofbirth"));
            userEntity.setPassword(encodeDecode.encode(jsonUser.getString("new_password")));
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
    public ResultBean getBalance() throws ApiValidateException {
        log.debug("### getBalance start ###");
        String email = DataUtils.getEmailByToken();
        UserEntity userEntity = userDao.findByEmail(email);
        Integer idUser = userEntity.getId();
        List<BalanceResponse> listBalanceResponse = new ArrayList<BalanceResponse>();
        listBalanceResponse = userDao.getBalanceById(idUser);
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
    public UserEntity findByEmail(String email) {
        log.debug("### findByPhoneNumber start ###");
        UserEntity userEntity = null;
        userEntity = userDao.findByEmail(email);
        log.debug("### findByPhoneNumber end ###");
        return userEntity;
    }

    /**
     * getByUsername
     * @author (VNEXT) PhiNH
     * @param name
     * @return UserEntity userEntity
     */
    @Override
    public UserEntity getByUsername(String name) {
        log.debug("### getByUsername start ###");
        Optional<UserEntity> userOptional = userRepositoty.findByName(name);
        log.debug("### getByUsername end ###");
        return userOptional.orElse(null);
    }

    /**
     * login
     * @author (VNEXT) PhiNH
     * @param name
     * @return JwtResponse
     */
    @Override
    public JwtResponse login(String json) throws ApiValidateException, Exception {
        log.debug("### login start ###");
        JSONObject jsonUser = new JSONObject(json);
        if (jsonUser.isEmpty()) {
            throw new ApiValidateException("400", "Please enter all field");
        }
        String email = jsonUser.getString("email");
        String password = encodeDecode.encode(jsonUser.getString("password"));
        UserDetails userDetails = detailsService.loadUserByUsername(email);
        if (!password.equals(userDetails.getPassword())) {
            throw new ApiValidateException("400", "Password is not valid");
        } else {
            String token = jwtTokenUtil.generateToken(userDetails);
            UserEntity user = userDao.findByEmail(email);
            JwtResponse jwtResponse = new JwtResponse(token, user);
            log.debug("### login end ###");
            return jwtResponse;
        }

    }

}
