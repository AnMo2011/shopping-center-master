package com.ejzblog.shopping.controller;

import com.ejzblog.shopping.result.ResponseResultDTO;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.Api;
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
 * @date 2022-07-05 14:07
 * @see com.ejzblog.shopping.controller
 */
@SuppressWarnings("ALL")
@ApiSort(10)
@Api(tags = "admin test 前端控制器")
@RestController
@RequestMapping(value = "/admin-test")
public class AdminTestController {

    @GetMapping
    public ResponseResultDTO<String> get() {
        return ResponseResultDTO.success(System.currentTimeMillis() + "");
    }

}
