package com.example.web;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.common.Page.PageList;
import com.example.common.lang.Result;
import com.example.entity.TbDeviceListRe705;
import com.example.mapper.TbDeviceListRe705Mapper;
import com.example.service.TbDeviceListRe705Service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Tu
 * @since 2021-05-07
 */
@Data
@RestController
@RequestMapping("/")
public class TbDeviceListRe705Controller {
    @Resource
    private JdbcTemplate jdbcTemplate;
    @Autowired
    TbDeviceListRe705Service tbDeviceListRe705Service;
    @Resource
    private TbDeviceListRe705Mapper tbDeviceListRe705Mapper;

    @GetMapping("devListku")
    public Result logiest(Integer currentPage) {
        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }

        Integer pagerow = 100;
        PageList pageList = new PageList();

        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String endtime = ft.format(dNow);
        String starttime = ft.format(new Date(dNow.getTime() - 1 * 24 * 60 * 60 * 1000));
        List<TbDeviceListRe705> data = tbDeviceListRe705Service.findAllbyPage(currentPage, pagerow);
        int TotalRows = tbDeviceListRe705Service.countAll();
        pageList.setPage(currentPage);
        pageList.setSize(pagerow);
        pageList.setTotal(TotalRows);
        int pages = 0;
        if (TotalRows % pagerow == 0) {
            pages = TotalRows / pagerow;
        } else {
            pages = TotalRows / pagerow + 1;
        }
//        System.out.println("目前分页的总页数是"+pages);
        pageList.setPages(pages);

        pageList.setRecords(data);
        return Result.succ("操作成功！", pageList);
    }

    @GetMapping("/searchlist")
    public Result searchData(String deviceid, Integer currentPage, String orderId, String starttime, String endtime) {

        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }
        Integer pagerow = 100;
        PageList pageList = new PageList();
        List<TbDeviceListRe705> data = tbDeviceListRe705Service.searchAllbyPage(pagerow, deviceid, currentPage, orderId, starttime, endtime);
        List<TbDeviceListRe705> datasize = tbDeviceListRe705Service.searchAllbyPagenum(deviceid, currentPage, orderId, starttime, endtime);

        int TotalRows = datasize.size();
        pageList.setPage(currentPage);
        pageList.setSize(pagerow);
        pageList.setTotal(TotalRows);
        int pages = 0;
        if (TotalRows % pagerow == 0) {
            pages = TotalRows / pagerow;
        } else {
            pages = TotalRows / pagerow + 1;
        }
//        System.out.println("目前分页的总页数是"+pages);
        pageList.setPages(pages);

        pageList.setRecords(data);


        return Result.succ("操作成功", pageList);
    }

    @GetMapping("/statistical6")
    public Result statisticalNum6(Integer num, Integer currentPage, String orderId, String starttime, String endtime) {
        String sql;
        Long userList;
        Integer d = 3;
        if (num == null) {
            return Result.fail("null");
        }
        if (num >= d) {
            if (orderId != null && orderId.length() != 0) {
                if (starttime != null && starttime.length() != 0) {
                    sql = "select COUNT(*) from tb_device_list_re705 WHERE check_count >=? AND order_id=? AND (test_datetime BETWEEN ? AND ?) ";
                    userList = (jdbcTemplate.queryForObject(sql, Long.class, num, orderId, starttime, endtime));
                } else {
                    sql = "select COUNT(*) from tb_device_list_re705 WHERE check_count >=? AND order_id=? ";
                    userList = (jdbcTemplate.queryForObject(sql, Long.class, num, orderId));
                }

            } else {
                if (starttime != null && starttime.length() != 0) {
                    sql = "select COUNT(*) from tb_device_list_re705 WHERE check_count >=?  AND (test_datetime BETWEEN ? AND ?) ";
                    userList = (jdbcTemplate.queryForObject(sql, Long.class, num, starttime, endtime));
                } else {
                    sql = "select COUNT(*) from tb_device_list_re705 WHERE check_count >=? ";
                    userList = jdbcTemplate.queryForObject(sql, Long.class, num);
                }
            }

        } else {
            if (orderId != null && orderId.length() != 0) {
                if (starttime != null && starttime.length() != 0) {
                    sql = "select COUNT(*) from tb_device_list_re705 WHERE check_count =? AND order_id=? AND test_datetime BETWEEN ? AND ? ";
                    userList = (jdbcTemplate.queryForObject(sql, Long.class, num, orderId, starttime, endtime));
                } else {
                    sql = "select COUNT(*) from tb_device_list_re705 WHERE check_count =? AND order_id=? ";
                    userList = (jdbcTemplate.queryForObject(sql, Long.class, num, orderId));
                }

            } else {
                if (starttime != null && starttime.length() != 0) {
                    sql = "select COUNT(*) from tb_device_list_re705 WHERE check_count =? AND test_datetime BETWEEN ? AND ? ";
                    userList = (jdbcTemplate.queryForObject(sql, Long.class, num, starttime, endtime));
                } else {
                    sql = "select COUNT(*) from tb_device_list_re705 WHERE check_count =? ";
                    userList = jdbcTemplate.queryForObject(sql, Long.class, num);
                }
            }
        }
        return Result.succ("操作成功", userList);
    }

    /**
     * [java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, java.lang.String]
     *
     * @return com.example.common.lang.Result
     * @author Tu
     * @date 2021/5/11 11:40
     * @message  此方法与上面使用SQL写的方法一样
     */
    @GetMapping("/statistical")
    public Result statisticalNum(Integer num, Integer currentPage, String orderId, String starttime, String endtime) {
        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }
        EntityWrapper<TbDeviceListRe705> entityWrapper = new EntityWrapper();
        if (orderId != null && orderId.length() != 0) {
            entityWrapper.eq("order_id", orderId);
        }
        Integer d = 3;
        if (num != null) {
            if (num >= d) {
                entityWrapper.ge("check_count", num);
            } else {

                entityWrapper.eq("check_count", num);
            }
        }
// 获取列表是没有做时间处理 这里也是同样
        if (starttime != null && starttime.length() != 0) {
            entityWrapper.between("test_datetime", starttime, endtime);
        }

        List<TbDeviceListRe705> devLists = tbDeviceListRe705Mapper.selectList(entityWrapper);

        return Result.succ("操作成功", devLists.size());
    }

    @PostMapping("/receivefile")
    public Result xjlist(@RequestBody List<TbDeviceListRe705> xjlists) {
        if (xjlists.size() == 0 || xjlists == null) {
            return Result.fail("插入数据为空！");
        }
        TbDeviceListRe705 savefile = new TbDeviceListRe705();
        List<TbDeviceListRe705> savefileList = new ArrayList<>(xjlists);
        /**
         * 第一种时间长
         * 1000/1min
         * 1000每次
         */

//        for (int i = 0; i < savefileList.size(); i++) {
//            BeanUtil.copyProperties((xjlists.get(i)), savefile);
//            xiaoJListService.saveOrUpdate(savefile);
//        }
        /**
         * 第二种时间明显减少
         * 1000/78ms
         * 1000每次
         */
        tbDeviceListRe705Service.insertBatch(savefileList);
        return Result.succ("插入成功！", savefile);
    }
}

