package com.example.web;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.example.common.Page.PageList;
import com.example.common.lang.Result;
import com.example.entity.MUser;
import com.example.entity.TbDeviceLog;
import com.example.mapper.TbDeviceLogMapper;
import com.example.service.TbDeviceLogService;
import com.github.pagehelper.IPage;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

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
public class TbDeviceLogController {
    @Autowired
    TbDeviceLogService deviceLogService;
/**
 @GetMapping("/devlogList") public Result logiest(Integer currentPage) {
 if (currentPage == null || currentPage < 1) {
 currentPage = 1;
 }
 Integer pagerow = 100;
 PageList pageList = new PageList();
 List<TbDeviceLog> data = deviceLogService.findAllbyPage(currentPage, pagerow);
 int TotalRows = deviceLogService.countAll();
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


 @GetMapping("/searchlog") public Result searchData(String deviceid, Integer currentPage, String starttime, String endtime) {

 if (currentPage == null || currentPage < 1) {
 currentPage = 1;
 }
 Integer pagerow = 100;
 PageList pageList = new PageList();
 List<TbDeviceLog> data = deviceLogService.findAllbyPage(currentPage, pagerow, deviceid, starttime, endtime);
 //        int TotalRows = deviceLogService.countAll();
 List<TbDeviceLog> datasize  = deviceLogService.findAllbyPagenum(currentPage, pagerow, deviceid, starttime, endtime);
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
 pageList.setPages(pages);

 pageList.setRecords(data);
 return Result.succ("", pageList);
 }
 */

    /**
     * [java.lang.Integer]
     *
     * @return com.example.common.lang.Result
     * @author Tu
     * @date 2021/5/13 16:23
     * @message 实验分析 上诉查询方法 比之下的方法慢
     */


    @GetMapping("/devlogList")
    public Result logiest(Integer currentPage) {
        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }
        Integer pagerow = 100;
        Page iPage = new Page<>(currentPage, pagerow);
        EntityWrapper<TbDeviceLog> entityWrapper = new EntityWrapper<>();
        entityWrapper.orderBy("id", true);
        iPage = deviceLogService.selectPage(iPage, entityWrapper);

        if (iPage == null||iPage.getTotal() == 0) {
            return Result.fail("没有数据！");
        }
        return Result.succ("获取成功！", iPage);
    }

    /**
     * [java.lang.String, java.lang.Integer, java.lang.String, java.lang.String]
     *
     * @return com.example.common.lang.Result
     * @author Tu
     * @date 2021/5/13 16:24
     * @message
     */
    @GetMapping("/searchlog")
    public Result searchData(String deviceid, Integer currentPage, String starttime, String endtime) {

        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }
        Integer pagerow = 100;
        Page iPage = new Page<>(currentPage, pagerow);
        EntityWrapper<TbDeviceLog> entityWrapper = new EntityWrapper<>();
        entityWrapper.orderBy("addtime", true);
        if (deviceid != null && deviceid.length() != 0) {
            entityWrapper.like("deviceid", deviceid);
        }
        if (starttime != null && starttime.length() != 0) {
            entityWrapper.between("addtime", starttime, endtime);
        }

        iPage = deviceLogService.selectPage(iPage, entityWrapper);

        if (iPage == null||iPage.getTotal() == 0) {
            return Result.fail("没有数据！");
        }

        return Result.succ("获取成功！", iPage);
    }
}

