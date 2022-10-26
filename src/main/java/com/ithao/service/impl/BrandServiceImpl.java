package com.ithao.service.impl;
import com.ithao.mapper.BrandMapper;
import com.ithao.pojo.PageBean;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.ithao.pojo.brand;
import com.ithao.service.BrandService;
import com.ithao.utils.sqlSessionFactoryUtils;

import java.util.List;

public class BrandServiceImpl implements BrandService {
    SqlSessionFactory factory = sqlSessionFactoryUtils.getSqlSessionFactory();
    public List<brand> selectAll() {
        SqlSession sqlSession = factory.openSession();

        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        List<brand> brands = mapper.selectAll();

        sqlSession.close();
        return brands;
    }

    public void add(brand brand) {
        SqlSession sqlSession = factory.openSession();

        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        mapper.add(brand);
        sqlSession.commit();

        sqlSession.close();
    }

    public void delete(int id) {
        SqlSession sqlSession = factory.openSession();

        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        mapper.delete(id);
        sqlSession.commit();
        sqlSession.close();


    }

    public void deleteById(int[] ids) {
        SqlSession sqlSession = factory.openSession();

        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        mapper.deleteById(ids);

        sqlSession.commit();
        sqlSession.close();
    }

    public void update(brand brand) {
        SqlSession sqlSession = factory.openSession();

        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        mapper.update(brand);

        sqlSession.commit();
        sqlSession.close();
    }

    public PageBean<brand> selectByPage(int currentPage, int pageSize) {
        SqlSession sqlSession = factory.openSession();

        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        int begin = (currentPage -1 )* pageSize;
        int size = pageSize;
        List<brand> rows = mapper.selectByPage(begin, size);

        int totalCount = mapper.selectTotalCount();

        PageBean<brand> pageBean = new PageBean<brand>();

        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);

        sqlSession.close();
        return pageBean;
    }

    public PageBean<brand> selectByPageAndCondition(int currentPage, int pageSize, brand brand) {

        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);


        //4. 计算开始索引
        int begin = (currentPage - 1) * pageSize;
        // 计算查询条目数
        int size = pageSize;

        // 处理brand条件，模糊表达式
        String brandName = brand.getBrandName();
        if (brandName != null && brandName.length() > 0) {
            brand.setBrandName("%" + brandName + "%");
        }

        String companyName = brand.getCompanyName();
        if (companyName != null && companyName.length() > 0) {
            brand.setCompanyName("%" + companyName + "%");
        }

        //5. 查询当前页数据
        List<brand> rows = mapper.selectByPageAndCondition(begin, size, brand);

        //6. 查询总记录数
        int totalCount = mapper.selectTotalCountByCondition(brand);

        //7. 封装PageBean对象
        PageBean<brand> pageBean = new PageBean<brand>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);

        //8. 释放资源
        sqlSession.close();

        return pageBean;
    }

}
