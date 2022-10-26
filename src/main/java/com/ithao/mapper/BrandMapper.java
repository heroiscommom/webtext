package com.ithao.mapper;

import com.ithao.pojo.brand;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BrandMapper {
    @Select("select * from tb_brand")
    @ResultMap("BrandResultMap")
    List<brand> selectAll();


    @Insert("insert into tb_brand values (null,#{brandName},#{companyName},#{ordered},#{description},#{status})")
    void add(brand brand);

    @Update("update tb_brand set brand_name=#{brandName},company_name=#{companyName},ordered=#{ordered},description=#{description},status=#{status} where id = #{id}) ")
    void update(brand brand);

    @Delete("DELETE from tb_brand WHERE id = #{id}")
    void delete(int id);

    void deleteById(@Param("ids") int[] ids);


    @Select("select * from tb_brand limit #{begin} , #{size}")
    @ResultMap("BrandResultMap")
    List<brand> selectByPage(@Param("begin") int begin,@Param("size") int size);

    /**
     *
     * @return
     */
    @Select("select count(*) from tb_brand")
    int selectTotalCount();

    List<brand> selectByPageAndCondition(@Param("begin") int begin,@Param("size") int size,@Param("brand") brand brand);

    int selectTotalCountByCondition(brand brand);

}
