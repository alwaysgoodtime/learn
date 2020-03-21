package com.goodtime.domain;

import lombok.Data;
import lombok.ToString;

/**这种是多表关联查询的笨办法
 * @author goodtime
 * @create 2020-03-11 8:04 下午
 */
@Data
@ToString
public class AccountUser {
    private Integer userid;
    private String password;
    private Integer id;
    private String name;
    private Double money;

}
