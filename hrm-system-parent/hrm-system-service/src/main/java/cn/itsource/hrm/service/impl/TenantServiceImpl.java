package cn.itsource.hrm.service.impl;

import cn.itsource.hrm.domain.Tenant;
import cn.itsource.hrm.mapper.TenantMapper;
import cn.itsource.hrm.service.ITenantService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author DangR-X
 * @since 2020-01-01
 */
@Service
public class TenantServiceImpl extends ServiceImpl<TenantMapper, Tenant> implements ITenantService {

}
