package com.itheima.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.ssm.domain.Orders;
import com.itheima.ssm.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private IOrdersService ordersService;

    //查询全部订单---未分页的
//    @RequestMapping("findAll.do")
//    public ModelAndView findAll(ModelAndView mv){
//
//        List<Orders> ordersList = ordersService.findAll();
//
//        mv.addObject("ordersList",ordersList);
//
//        mv.setViewName("orders-list");
//
//        return mv;
//    }

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(ModelAndView mv, @RequestParam(name = "page",required = true,defaultValue = "1")int page,@RequestParam(name = "size",required = true,defaultValue = "4")int size){
        List<Orders> ordersList = ordersService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(ordersList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("orders-page-list");
        return mv;
    }
}
