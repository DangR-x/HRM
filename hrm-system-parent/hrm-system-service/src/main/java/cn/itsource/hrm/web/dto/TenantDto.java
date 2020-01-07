package cn.itsource.hrm.web.dto;

import cn.itsource.hrm.domain.Tenant;
import lombok.Data;

/**
 * @Description TODO
 * @Author dangran
 * @Date 2020/1/3 15:10
 * @Version v1.0
 */
@Data
public class TenantDto {

    private Tenant tenant;
    private String username;
    private String password;
    private Long meal;


}
