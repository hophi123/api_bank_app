/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.vnext.phinh.api_bank_app.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vnext.phinh.api_bank_app.bean.ResultBean;
import com.vnext.phinh.api_bank_app.service.BankService;
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
@RequestMapping(value = "/api")
public class BankController {

    @Autowired
    BankService bankService;

    private static final Log log = LogFactory.getLog(UserServiceImpl.class);

    /**
     * getAll
     * @author: (VNEXT) PhiNH
     * @return ResultBean
     */
    @RequestMapping(value = "/bank", method = RequestMethod.GET, produces = "application/json")
    public ResultBean getAll() {
        log.debug("### getAll start ###");
        ResultBean resultBean = null;
        try {
            resultBean = bankService.getAll();
        } catch (ApiValidateException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.debug("### getAll end ###");
        return resultBean;
    }
}
