package com.example.service.impl;

import com.example.entity.TbDeviceListRe705;
import com.example.entity.TbDeviceListXiaojiang;
import com.example.mapper.TbDeviceListRe705Mapper;
import com.example.service.TbDeviceListRe705Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class TbDeviceListRe705ServiceImpl extends ServiceImpl<TbDeviceListRe705Mapper, TbDeviceListRe705> implements TbDeviceListRe705Service {
    @Resource
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<TbDeviceListRe705> findAllbyPage(Integer currentPage, Integer pagerow) {


        int starter = (currentPage - 1) * pagerow;
        String sql = "select * from tb_device_list_re705  limit " + starter + " , " + pagerow;
        List<TbDeviceListRe705> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TbDeviceListRe705.class));

        return list;
    }

    @Override
    public int countAll() {
        String sql = "SELECT * FROM tb_device_list_re705";
        List<TbDeviceListRe705> userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TbDeviceListRe705.class));
        return userList.size();
    }

    @Override
    public List<TbDeviceListRe705> searchAllbyPage(Integer pagerow,String deviceid, Integer currentPage, String orderId, String starttime, String endtime) {
        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }

        int starter = (currentPage - 1) * pagerow;
        String sql = null;
        String limit = " limit " + starter + " , " + pagerow;
        String devid = " deviceid  LIKE \"%" + deviceid + "%\"";
        String ordid = "order_id  LIKE \"%" + orderId + "%\"";
        String time = "test_datetime BETWEEN \"" + starttime + "\" and  \"" + endtime + "\"";
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String endtime1 = ft.format(dNow);
        String starttime1 = ft.format(new Date(dNow.getTime() - 1 * 24 * 60 * 60 * 1000));
        if (deviceid != null && deviceid.length() != 0) {
            sql = "select * from tb_device_list_re705 WHERE " + devid + limit;
        }
        if (orderId != null && orderId.length() != 0) {
            sql = "select * from tb_device_list_re705 WHERE " + ordid + limit;
        }
        if (starttime != null && starttime.length() != 0) {
            sql = "select * from tb_device_list_re705 WHERE " + time + limit;
        }
        if (deviceid != null && deviceid.length() != 0 && orderId != null && orderId.length() != 0) {
            sql = "select * from tb_device_list_re705 WHERE " + ordid + " and " + devid + limit;
        }
        if (deviceid != null && deviceid.length() != 0 && starttime != null && starttime.length() != 0) {
            sql = "select * from tb_device_list_re705 WHERE " + devid + " and " + time + limit;
        }
        if (orderId != null && orderId.length() != 0 && starttime != null && starttime.length() != 0) {
            sql = "select * from tb_device_list_re705 WHERE " + ordid + " and " + time + limit;
        }
        if (deviceid != null && deviceid.length() != 0 && orderId != null && orderId.length() != 0 && starttime != null && starttime.length() != 0) {
            sql = "select * from tb_device_list_re705 WHERE " + ordid + " and " + devid + " and " + time + limit;

        }

        List<TbDeviceListRe705> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TbDeviceListRe705.class));

        return list;
    }

    @Override
    public List<TbDeviceListRe705> searchAllbyPagenum(String deviceid, Integer currentPage, String orderId, String starttime, String endtime) {
        String sql = null;
        String devid = " deviceid  LIKE \"%" + deviceid + "%\"";
        String ordid = "order_id  LIKE \"%" + orderId + "%\"";
        String time = "test_datetime BETWEEN \"" + starttime + "\" and  \"" + endtime + "\"";
        if (deviceid != null && deviceid.length() != 0) {
            sql = "select * from tb_device_list_re705 WHERE " + devid;
        }
        if (orderId != null && orderId.length() != 0) {
            sql = "select * from tb_device_list_re705 WHERE " + ordid;
        }
        if (starttime != null && starttime.length() != 0) {
            sql = "select * from tb_device_list_re705 WHERE " + time;
        }
        if (deviceid != null && deviceid.length() != 0 && orderId != null && orderId.length() != 0) {
            sql = "select * from tb_device_list_re705 WHERE " + ordid + " and " + devid;
        }
        if (deviceid != null && deviceid.length() != 0 && orderId != null && orderId.length() != 0 && starttime != null && starttime.length() != 0) {
            sql = "select * from tb_device_list_re705 WHERE " + ordid + " and " + devid + " and " + time;
        }
        if (deviceid != null && deviceid.length() != 0 && starttime != null && starttime.length() != 0) {
            sql = "select * from tb_device_list_re705 WHERE " + devid + " and " + time;
        }
        if (orderId != null && orderId.length() != 0 && starttime != null && starttime.length() != 0) {
            sql = "select * from tb_device_list_re705 WHERE " + ordid + " and " + time;
        }
        List<TbDeviceListRe705> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TbDeviceListRe705.class));

        return list;
    }
}
