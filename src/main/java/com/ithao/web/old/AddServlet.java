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
import java.io.BufferedReader;
import java.io.IOException;

//@WebServlet("/addServlet")
public class AddServlet extends HttpServlet {
    private BrandService service = new BrandServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        BufferedReader br = request.getReader();
        String params = br.readLine();

        brand brand = JSON.parseObject(params, brand.class);

        service.add(brand);

        response.getWriter().write("success");

}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            this.doPost(request, response);
    }
}
