package com.example.service;

import com.example.entity.MUser;
import com.baomidou.mybatisplus.service.IService;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Tu
 * @since 2021-05-07
 */
public interface MUserService extends IService<MUser> {

    List<MUser> findAllbyPage(int pagenum, int pagerow);

    int countAll();
}
