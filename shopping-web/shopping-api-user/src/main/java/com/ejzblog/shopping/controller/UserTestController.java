package com.ejzblog.shopping.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author Mango
 * @version v1.0.0
 * @date 2022-07-05 14:08
 * @see com.ejzblog.shopping.controller
 */
@RestController
@RequestMapping(value = "/user-test")
public class UserTestController {

    @GetMapping
    public String get() {
        return System.currentTimeMillis() + "";
    }

}
