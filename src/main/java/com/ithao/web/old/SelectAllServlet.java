package com.ithao.web.old;

import com.alibaba.fastjson.JSON;
import com.ithao.pojo.brand;
import com.ithao.service.BrandService;
import com.ithao.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//@WebServlet("/selectAllServlet")
public class SelectAllServlet extends HttpServlet {
   private BrandService service = new BrandServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<brand> brands = service.selectAll();

        String s = JSON.toJSONString(brands);

        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(s);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            this.doPost(request, response);
    }
}
