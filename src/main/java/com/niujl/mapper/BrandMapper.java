package com.niujl.mapper;

import com.niujl.pojo.Brand;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BrandMapper {
    /**
     * 查询所有
     * @return
     */
    List<Brand> selectAll();
    /**
     * 查看详情：根据id
     */
    List<Brand> selectById(int id);
    /**
     * 根据条件查找
     * 1、散装参数：
     * 2、对象参数：
     * 3、map集合参数：
     */
//    @Param()注解是给xml(写sql语句的文件代码看的)，因为sql语句有多个参数占位符，所以需要指定参数占位符与值的对应关系
//    List<Brand> selectByCondition(@Param("status") int status, @Param("companyName") String companyName, @Param("brandName")String brandName);
//    如果传的是对象的话
//    List<Brand> selectByCondition(Brand brand);
//    如果传的是Map集合的话
    List<Brand> selectByCondition(Map map);
    /**
     * 单条件动态查询
     */
    List<Brand> selectByConditionSingle(Brand brand);
    /**
     * 添加记录
     */
    void add(Brand brand);
    /**
     * 修改功能
     */
    int update(Brand brand);
    /**
     * 根据id删除功能
     */
    void deleteById(int id);
    /**
     * 批量删除
     * 数组需要注解改变一下传递的名称
     * mybatis会将数组封装成一个map集合
     *               key:array , value:数组
     *               使用@Param注解改变map集合的默认key的名称
     */
    void deleteByIds(@Param("ids") int[] ids );
}
