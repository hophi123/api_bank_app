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
import com.vnext.phinh.api_bank_app.service.AuthService;
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
 * 001       1.0       2020/04/15      (VNEXT) PhiNH       Create new
*/
@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthService authservice;

    private static final Log log = LogFactory.getLog(UserServiceImpl.class);

    /**
     * checkLogin
     * @author: (VNEXT) PhiNH
     * @param json
     * @return ResponseEntity<ResultBean>
     */
    @RequestMapping(value = "/auth", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<ResultBean> checkLogin(@RequestBody String json) {
        log.debug("### checkLogin start ###");
        ResultBean resultbean = null;
        UserEntity userEntity = null;
        try {
            userEntity = authservice.checkLogin(json);
        } catch (ApiValidateException e) {
            e.printStackTrace();
            resultbean = new ResultBean(e.getCode(), e.getField(), e.getMessage());
            return new ResponseEntity<ResultBean>(resultbean, HttpStatus.BAD_GATEWAY);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<ResultBean>(resultbean, HttpStatus.BAD_GATEWAY);
        }
        resultbean = new ResultBean(userEntity, "200", "Login SUCCESS");
        log.debug("### checkLogin end ###");
        return new ResponseEntity<ResultBean>(resultbean, HttpStatus.OK);
    }
}
