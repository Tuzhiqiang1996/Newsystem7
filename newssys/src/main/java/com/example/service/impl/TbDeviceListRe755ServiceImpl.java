package com.example.service.impl;

import com.example.entity.TbDeviceListRe755;
import com.example.mapper.TbDeviceListRe755Mapper;
import com.example.service.TbDeviceListRe755Service;
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
public class TbDeviceListRe755ServiceImpl extends ServiceImpl<TbDeviceListRe755Mapper, TbDeviceListRe755> implements TbDeviceListRe755Service {
    @Resource
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<TbDeviceListRe755> findAllbyPage(Integer currentPage, Integer pagerow) {
        int starter = (currentPage - 1) * pagerow;
        String sql = "select * from tb_device_list_re755  limit " + starter + " , " + pagerow;
        List<TbDeviceListRe755> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TbDeviceListRe755.class));

        return list;
    }

    @Override
    public int countAll() {
        String sql = "SELECT * FROM tb_device_list_re755";
        List<TbDeviceListRe755> userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TbDeviceListRe755.class));
        return userList.size();
    }

    @Override
    public List<TbDeviceListRe755> searchAllbyPage(Integer currentPage, Integer pagerow, String deviceid, Integer currentPage1, String orderId, String starttime, String endtime) {
        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }

        int starter = (currentPage - 1) * pagerow;
        String sql = null;
        String limit = " limit " + starter + " , " + pagerow;
        String devid = " deviceid  LIKE \"%" + deviceid + "%\"";
        String ordid = "order_id  LIKE \"%" + orderId + "%\"";
        String time = "test_datetime BETWEEN \"" + starttime + "\" and  \"" + endtime + "\"";
        if (deviceid != null && deviceid.length() != 0) {
            sql = "select * from tb_device_list_re755 WHERE " + devid + limit;
        }
        if (orderId != null && orderId.length() != 0) {
            sql = "select * from tb_device_list_re755 WHERE " + ordid + limit;
        }
        if (starttime != null && starttime.length() != 0) {
            sql = "select * from tb_device_list_re755 WHERE " + time + limit;
        }
        if (deviceid != null && deviceid.length() != 0 && orderId != null && orderId.length() != 0) {
            sql = "select * from tb_device_list_re755 WHERE " + ordid + " and " + devid + limit;
        }
        if (deviceid != null && deviceid.length() != 0 && orderId != null && orderId.length() != 0 && starttime != null && starttime.length() != 0) {
            sql = "select * from tb_device_list_re755 WHERE " + ordid + " and " + devid + " and " + time + limit;
        }
        if (deviceid != null && deviceid.length() != 0 && starttime != null && starttime.length() != 0) {
            sql = "select * from tb_device_list_re755 WHERE " + devid + " and " + time + limit;
        }
        if (orderId != null && orderId.length() != 0 && starttime != null && starttime.length() != 0) {
            sql = "select * from tb_device_list_re755 WHERE " + ordid + " and " + time + limit;
        }
        List<TbDeviceListRe755> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TbDeviceListRe755.class));

        return list;
    }

    @Override
    public List<TbDeviceListRe755> searchAllbyPagenum(Integer currentPage, Integer pagerow, String deviceid, Integer currentPage1, String orderId, String starttime, String endtime) {

        String sql = null;
        String devid = " deviceid  LIKE \"%" + deviceid + "%\"";
        String ordid = "order_id  LIKE \"%" + orderId + "%\"";
        String time = "test_datetime BETWEEN \"" + starttime + "\" and  \"" + endtime + "\"";
        if (deviceid != null && deviceid.length() != 0) {
            sql = "select * from tb_device_list_re755 WHERE " + devid;
        }
        if (orderId != null && orderId.length() != 0) {
            sql = "select * from tb_device_list_re755 WHERE " + ordid;
        }
        if (starttime != null && starttime.length() != 0) {
            sql = "select * from tb_device_list_re755 WHERE " + time;
        }
        if (deviceid != null && deviceid.length() != 0 && orderId != null && orderId.length() != 0) {
            sql = "select * from tb_device_list_re755 WHERE " + ordid + " and " + devid;
        }
        if (deviceid != null && deviceid.length() != 0 && orderId != null && orderId.length() != 0 && starttime != null && starttime.length() != 0) {
            sql = "select * from tb_device_list_re755 WHERE " + ordid + " and " + devid + " and " + time;
        }
        if (deviceid != null && deviceid.length() != 0 && starttime != null && starttime.length() != 0) {
            sql = "select * from tb_device_list_re755 WHERE " + devid + " and " + time;
        }
        if (orderId != null && orderId.length() != 0 && starttime != null && starttime.length() != 0) {
            sql = "select * from tb_device_list_re755 WHERE " + ordid + " and " + time;
        }
        List<TbDeviceListRe755> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TbDeviceListRe755.class));

        return list;
    }
}
