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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vnext.phinh.api_bank_app.bean.ResultBean;
import com.vnext.phinh.api_bank_app.bean.BankFeeEntity;
import com.vnext.phinh.api_bank_app.service.BankFeeService;
import com.vnext.phinh.api_bank_app.service.impl.UserServiceImpl;

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
public class BankFeeController {

    @Autowired
    private BankFeeService transfeeservice;

    private static final Log log = LogFactory.getLog(UserServiceImpl.class);

    /**
     * findById
     * @author: (VNEXT) PhiNH
     * @param id
     * @return ResponseEntity<ResultBean>
     */
    @RequestMapping(value = "/transfee/{id}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<ResultBean> findById(@PathVariable Integer id) {
        log.debug("### findById start ###");
        BankFeeEntity transFeeEntity = null;
        ResultBean resultBean = null;
        try {
            transFeeEntity = transfeeservice.getTransFeeByiId(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_GATEWAY);
        }
        resultBean = new ResultBean(transFeeEntity, "200", "OKOK");
        log.debug("### findById end ###");
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

}
