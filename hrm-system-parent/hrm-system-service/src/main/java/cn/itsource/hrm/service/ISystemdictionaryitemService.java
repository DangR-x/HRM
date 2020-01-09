package cn.itsource.hrm.service;

import cn.itsource.hrm.domain.Systemdictionaryitem;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author DangR-X
 * @since 2019-12-26
 */
public interface ISystemdictionaryitemService extends IService<Systemdictionaryitem> {
    public List<Systemdictionaryitem> listSn(String sn);
}
