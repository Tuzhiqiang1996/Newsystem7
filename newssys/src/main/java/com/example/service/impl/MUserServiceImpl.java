package com.example.service.impl;

import com.example.entity.MUser;
import com.example.mapper.MUserMapper;
import com.example.service.MUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Tu
 * @since 2021-05-07
 */
@Service
public class MUserServiceImpl extends ServiceImpl<MUserMapper, MUser> implements MUserService {
    @Resource
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<MUser > findAllbyPage(int pagenum, int pagerow) {
        int starter = (pagenum-1)*pagerow;
        String sql = "select * from m_user order by id asc  limit " + starter +" , "+ pagerow;
        List<MUser> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(MUser.class));

        return list;
    }

    @Override
    public int countAll() {
        String sql = "SELECT * FROM m_user";
        List<MUser> userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(MUser.class));
//        List<MUser> userList = jdbcTemplate.query(sql, new RowMapper<MUser>() {
//            @Override
//            public MUser mapRow(ResultSet rs, int rowNum) throws SQLException {
//
//                return new MUser();
//            }
//        });
        return userList.size();
    }

}
