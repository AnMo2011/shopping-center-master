package com.ejzblog.shopping.controller;

import com.ejzblog.shopping.annotation.TokenValidation;
import com.ejzblog.shopping.result.ResponseResultDTO;
import com.ejzblog.shopping.service.OssService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static com.ejzblog.shopping.enums.ResponseStatusEnum.PLEASE_SELECT_UPLOAD_FILE;
import static com.ejzblog.shopping.enums.ResponseStatusEnum.UPLOADING_OF_EMPTY_FILES_IS_NOT_ALLOWED;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author Mango
 * @version v1.0.0
 * @date 2022-07-09 13:43
 * @see com.ejzblog.shopping.controller
 */
@SuppressWarnings("ALL")
@Slf4j
@ApiSort(200)
@Api(tags = "OSS 前端控制器")
@RestController
@RequestMapping("/oss-do")
public class OssController {

    /**
     * 上传
     * @param file 文件
     * @return
     */
    @TokenValidation
    @ApiOperation(value = "上传OSS（需要传token）")
    @ApiImplicitParam(name = "file", value = "文件", required = true, dataTypeClass = MultipartFile.class)
    @ApiOperationSupport(order = 100)
    @PostMapping
    public ResponseResultDTO<String> uploadOss(MultipartFile file) {

        // 判断文件
        if (file == null) {
            return ResponseResultDTO.unSuccess(PLEASE_SELECT_UPLOAD_FILE);
        }

        if (file.isEmpty()) {
            return ResponseResultDTO.unSuccess(UPLOADING_OF_EMPTY_FILES_IS_NOT_ALLOWED);
        }

        String url = OssService.uploadOss(file);

        return ResponseResultDTO.success(url);
    }

    /**
     * 删除
     * @param url 图片地址
     * @return
     */
    @TokenValidation
    @ApiOperation(value = "删除OSS（需要传token）")
    @ApiImplicitParam(name = "url", value = "图片地址", required = true, dataTypeClass = String.class)
    @ApiOperationSupport(order = 200)
    @DeleteMapping
    public ResponseResultDTO<Void> removeOss(String url) {
        OssService.deleteOss(url);
        return ResponseResultDTO.success(null);
    }

}
