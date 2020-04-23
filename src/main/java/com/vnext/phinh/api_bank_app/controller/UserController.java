/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.vnext.phinh.api_bank_app.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vnext.phinh.api_bank_app.bean.ResultBean;
import com.vnext.phinh.api_bank_app.bean.UserEntity;
import com.vnext.phinh.api_bank_app.service.UserService;
import com.vnext.phinh.api_bank_app.service.impl.UserServiceImpl;
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
@RestController
@RequestMapping("/api")
public class UserController {
    /**
     * 
     */
    @Autowired
    private UserService userService;

    private static final Log log = LogFactory.getLog(UserServiceImpl.class);

    /**
     * createUser
     * @author: (VNEXT) PhiNH
     * @param json
     * @return ResponseEntity<ResultBean>
     */
    @RequestMapping(value = "/user", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<ResultBean> createUser(@RequestBody String json) {
        log.debug("### createUser start ###");
        UserEntity userEntity = null;
        ResultBean resultBean = null;
        try {
            userEntity = userService.createUser(json);

        } catch (ApiValidateException e) {
            e.printStackTrace();
            resultBean = new ResultBean(e.getCode(), e.getField(), e.getMessage());
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_GATEWAY);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_GATEWAY);
        }
        resultBean = new ResultBean(userEntity, "200", "User successfully created!");
        log.debug("### createUser end ###");
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    /**
     * updateUser
     * @author: (VNEXT) PhiNH
     * @param json
     * @return ResponseEntity<ResultBean>
     */
    @RequestMapping(value = "/user", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<ResultBean> updateUser(@RequestBody String json) {
        log.debug("### updateUser start ###");
        UserEntity userEntity = null;
        ResultBean resultBean = null;
        try {
            userEntity = userService.updateUser(json);

        } catch (ApiValidateException e) {
            e.printStackTrace();
            resultBean = new ResultBean(e.getCode(), e.getField(), e.getMessage());
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_GATEWAY);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_GATEWAY);
        }
        resultBean = new ResultBean(userEntity, "200", "Update success!");
        log.debug("### updateUser end ###");
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_GATEWAY);
    }

    /**
     * getBalance
     * @author: (VNEXT) PhiNH
     * @param json
     * @return ResponseEntity<ResultBean>
     */
    @RequestMapping(value = "/user/balance", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<ResultBean> getBalance(@RequestBody String json) {
        log.debug("### getBalance start ###");
        ResultBean resultBean = null;
        try {
            resultBean = userService.getBalance(json);
        } catch (ApiValidateException e) {
            e.printStackTrace();
            resultBean = new ResultBean(e.getCode(), e.getField(), e.getMessage());
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_GATEWAY);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.debug("### getBalance end ###");
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_GATEWAY);
    }

}
