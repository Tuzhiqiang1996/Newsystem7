package com.example.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.example.entity.TbOrder;
import com.baomidou.mybatisplus.service.IService;
import com.github.pagehelper.IPage;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Tu
 * @since 2021-05-07
 */
public interface TbOrderService extends IService<TbOrder> {

    List<TbOrder> findAllbyPage(Integer currentPage, Integer pagerow);

    int countAll();
}
