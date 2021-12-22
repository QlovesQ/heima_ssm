package com.itheima.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.ssm.domain.Product;
import com.itheima.ssm.service.IProductService;
import com.itheima.ssm.utils.DateStringEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Date.class,new DateStringEditor());
    }

    /**
     * 查询所有
     * @param mv
     * @return
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(ModelAndView mv, @RequestParam(name = "page",required = true,defaultValue = "1")int page,@RequestParam(name = "size",required = true,defaultValue = "4")int size){
        List<Product> ps = productService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(ps);
        mv.addObject("productList",pageInfo);
        mv.setViewName("product-list");
        return mv;
    }

    /**
     * 产品添加
     * @param product
     */
    @RequestMapping("/save.do")
    public String save(Product product){
        productService.save(product);
        return"redirect:findAll.do";
    }
}
