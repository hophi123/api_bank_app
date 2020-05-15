/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.vnext.phinh.api_bank_app.service;

import com.vnext.phinh.api_bank_app.bean.ResultBean;
import com.vnext.phinh.api_bank_app.bean.UserEntity;
import com.vnext.phinh.api_bank_app.response.JwtResponse;
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
public interface UserService {

    /**
     * createUser
     * @author: (VNEXT) PhiNH
     * @param json
     * @return UserEntity
     * @throws ApiValidateException
     */
    public UserEntity createUser(String json) throws ApiValidateException;

    /**
     * updateUser
     * @author: (VNEXT) PhiNH
     * @param json
     * @return UserEntity
     * @throws ApiValidateException
     */
    public UserEntity updateUser(String json) throws ApiValidateException;

    /**
     * updateUser
     * @author: (VNEXT) PhiNH
     * @param userEntity
     * @throws ApiValidateException
     */
    public void updateUser(UserEntity userEntity) throws ApiValidateException;

    /**
     * findById
     * @author: (VNEXT) PhiNH
     * @param id
     * @return UserEntity
     */
    public UserEntity findById(Integer id);

    /**
     * findByPhoneNumber
     * @author: (VNEXT) PhiNH
     * @param phone_number
     * @return UserEntity
     */
    public UserEntity findByPhoneNumber(String phone_number);

    /**
     * getBalance
     * @author: (VNEXT) PhiNH
     * @param json
     * @return ResultBean
     * @throws ApiValidateException
     */
    public ResultBean getBalance() throws ApiValidateException;

    /**
     * getByUserName
     * @author: (VNEXT) PhiNH
     * @param username
     * @return UserEntity
     */
    public UserEntity getByUsername(String name);

    /**
     * login
     * @author: (VNEXT) PhiNH
     * @param json
     * @return JwtResponse
     * @throws ApiValidateException
     * @throws Exception
     */
    public JwtResponse login(String json) throws ApiValidateException, Exception;
}
