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

import com.vnext.phinh.api_bank_app.bean.AccountEntity;
import com.vnext.phinh.api_bank_app.bean.ResultBean;
import com.vnext.phinh.api_bank_app.service.AccountService;
import com.vnext.phinh.api_bank_app.service.impl.UserServiceImpl;
import com.vnext.phinh.api_bank_app.utils.ApiValidateException;

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
@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private AccountService accountService;

    private static final Log log = LogFactory.getLog(UserServiceImpl.class);

    /**
     * createUser
     * @author: (VNEXT) PhiNH
     * @param json
     * @return ResponseEntity<ResultBean>(resultBean, HttpStatus.OK)
     */
    @RequestMapping(value = "/account", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<ResultBean> createUser(@RequestBody String json) {
        log.debug("### createUser start ###");
        AccountEntity accountEntity = null;
        ResultBean resultBean = null;
        try {
            accountEntity = accountService.createAccount(json);

        } catch (ApiValidateException e) {
            e.printStackTrace();
            resultBean = new ResultBean(e.getCode(), e.getField(), e.getMessage());
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_GATEWAY);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_GATEWAY);
        }
        resultBean = new ResultBean(accountEntity, "200", "Account successfully created!");
        log.debug("### createUser end ###");
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }
}
