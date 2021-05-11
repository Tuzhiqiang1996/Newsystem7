package com.example.service.impl;

import com.example.entity.TbOrder;
import com.example.mapper.TbOrderMapper;
import com.example.service.TbOrderService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Tu
 * @since 2021-05-07
 */
@Service
public class TbOrderServiceImpl extends ServiceImpl<TbOrderMapper, TbOrder> implements TbOrderService {
    @Resource
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<TbOrder> findAllbyPage(Integer currentPage, Integer pagerow) {
        int starter = (currentPage - 1) * pagerow;
        String sql = "select * from tb_order  limit " + starter + " , " + pagerow;
        List<TbOrder> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TbOrder.class));

        return list;
    }

    @Override
    public int countAll() {
        String sql = "SELECT * FROM tb_order";
        List<TbOrder> userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TbOrder.class));
        return userList.size();
    }
}
