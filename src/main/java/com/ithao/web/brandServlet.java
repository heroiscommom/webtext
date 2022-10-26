package com.ithao.web;


import com.alibaba.fastjson.JSON;
import com.ithao.pojo.PageBean;
import com.ithao.pojo.brand;
import com.ithao.service.BrandService;
import com.ithao.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/brand/*")
public class brandServlet extends baseServlet {
    private BrandService service = new BrandServiceImpl();
    public void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<brand> brands = service.selectAll();

        String s = JSON.toJSONString(brands);

        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(s);
    }
    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        BufferedReader br = req.getReader();
        String params = br.readLine();

        brand brand = JSON.parseObject(params, brand.class);

        service.add(brand);

        resp.getWriter().write("success");
    }
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        int id = Integer.parseInt(req.getParameter("id"));

        service.delete(id);

        resp.getWriter().write("success");
    }
    public void deleteById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        BufferedReader br = req.getReader();
        String params = br.readLine();

        int[] ints = JSON.parseObject(params, int[].class);

        service.deleteById(ints);

        resp.getWriter().write("success");
    }
    public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
/*        req.setCharacterEncoding("utf-8");
        BufferedReader br = req.getReader();
        String params = br.readLine();

        brand brand = JSON.parseObject(params, brand.class);
        Integer id = brand.getId();
        String brandName = brand.getBrandName();
        String companyName = brand.getCompanyName();
        Integer status = brand.getStatus();
        String description = brand.getDescription();
        Integer ordered = brand.getOrdered();

        brand brand1 = new brand();
        brand1.setId(id);
        brand1.setOrdered(ordered);
        brand1.setStatus(status);
        brand1.setDescription(description);
        brand1.setBrandName(brandName);
        brand1.setCompanyName(companyName);

        service.update(brand1);*/
        // solve garbled char problem first
        req.setCharacterEncoding("utf-8");
        //get params in JSON from req
        BufferedReader reader = req.getReader();
        String params = reader.readLine();//no matter how long, it is one line
        //get a brand ob from json
        brand brand = JSON.parseObject(params, brand.class);
        //invoke update method put brand in ()
        service.update(brand);

        //till now no problem, resp a bingo
        resp.getWriter().write("success");
    }
    public void selectByPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String _currentPage = req.getParameter("currentPage");
        String _pageSize = req.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        PageBean<brand> pageBean = service.selectByPage(currentPage, pageSize);

        String jsonString = JSON.toJSONString(pageBean);

        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }
    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 接收 当前页码 和 每页展示条数    url?currentPage=1&pageSize=5
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        // 获取查询条件对象
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串

        //转为 Brand
        brand brand = JSON.parseObject(params, brand.class);


        //2. 调用service查询
        PageBean<brand> pageBean = service.selectByPageAndCondition(currentPage,pageSize,brand);

        //2. 转为JSON
        String jsonString = JSON.toJSONString(pageBean);
        //3. 写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
}
