package com.ejzblog.shopping.model.query;

import com.ejzblog.shopping.pages.SuperPageBO;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * Description：平台账户基本信息
 * </p>
 *
 * @author Mango
 * @version v1.0.0
 * @date 2022-07-05 17:59
 * @see com.ejzblog.shopping.model.query
 */
@Getter
@Setter
@ApiModel("平台账户基本信息（查询参数）")
public class AdminAccountQuery extends SuperPageBO {

    private static final long serialVersionUID = -1995039883531153353L;


    private String account;


}
