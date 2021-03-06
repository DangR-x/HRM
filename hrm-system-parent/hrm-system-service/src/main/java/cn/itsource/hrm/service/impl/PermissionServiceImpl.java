package cn.itsource.hrm.service.impl;

import cn.itsource.hrm.domain.Permission;
import cn.itsource.hrm.mapper.PermissionMapper;
import cn.itsource.hrm.service.IPermissionService;
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
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}
