package com.niujl.test;

import com.niujl.mapper.BrandMapper;
import com.niujl.pojo.Brand;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyBatisTest {
    @Test
    public void testSelectAll() throws IOException {
        //1、加载核心配置文件
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        2、获取SqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();
//        3、执行sql
//        List<User> users = sqlSession.selectList("test.selectAll");
//        System.out.println(users);
//        3、1获取UserMapper接口代理的对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        List<Brand> brands = brandMapper.selectAll();
        System.out.println(brands);
//        4、释放资源
        sqlSession.close();
    }
    @Test
    public void testSelectById() throws IOException {
        //设置参数
        int id=1;

        //1、加载核心配置文件
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        2、获取SqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();
//        3、1获取UserMapper接口代理的对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
//        3、2执行方法
        List<Brand> brands = brandMapper.selectById(id);
        System.out.println(brands);
//        4、释放资源
        sqlSession.close();
    }

    @Test
    public void testSelectByCondition() throws IOException {
        //设置参数
//        int status=1;
        String companyName="华为";
//        String brandName="华为";
        //处理参数
        companyName = "%" + companyName + "%";
//        brandName = "%" + brandName + "%";
        //封装对象
        Brand brand = new Brand();
//        brand.setStatus(status);
        brand.setCompanyName(companyName);
//        brand.setBrandName(brandName);
        //封装成Map
        Map map = new HashMap();
//        map.put("status", status);
        map.put("companyName", companyName);
//        map.put("brandName", brandName);

        //1、加载核心配置文件
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        2、获取SqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();
//        3、1获取UserMapper接口代理的对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
//        3、2执行方法（封装成对象或map来执行最好）（mybatis会解析map或brand对象）
//        List<Brand> brands = brandMapper.selectByCondition(status, companyName, brandName);
//        List<Brand> brands = brandMapper.selectByCondition(brand);
        List<Brand> brands = brandMapper.selectByCondition(map);
        System.out.println(brands);
//        4、释放资源
        sqlSession.close();
    }

    @Test
    public void testSelectByConditionSingle() throws IOException {
        //设置参数
//        int status=1;
        String companyName="小米";
//        String brandName="小米";
        //处理参数
        companyName = "%" + companyName + "%";
//        brandName = "%" + brandName + "%";
        //封装对象
        Brand brand = new Brand();
//        brand.setStatus(status);
        brand.setCompanyName(companyName);
//        brand.setBrandName(brandName);
        //封装Map对象
//        Map map = new HashMap();
//        map.put("status", status);
//        map.put("companyName", companyName);
//        map.put("brandName", brandName);

        //1、加载核心配置文件
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        2、获取SqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();
//        3、1获取UserMapper接口代理的对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
//        3、2执行方法
//        List<Brand> brands = brandMapper.selectByConditionSingle(status, companyName, brandName);
        List<Brand> brands = brandMapper.selectByConditionSingle(brand);
        System.out.println(brands);
//        4、释放资源
        sqlSession.close();
    }

    @Test
    public void testAdd() throws IOException {
        //设置参数
        int status=1;
        String companyName="波导手机";
        String brandName="波导002";
        String description = "手机中的战斗机!";
        int ordered = 100;
        //封装对象
        Brand brand = new Brand();
        brand.setStatus(status);
        brand.setCompanyName(companyName);
        brand.setBrandName(brandName);
        brand.setDescription(description);
        brand.setOrdered(ordered);
        //封装Map对象
//        Map map = new HashMap();
//        map.put("status", status);
//        map.put("companyName", companyName);
//        map.put("brandName", brandName);

        //1、加载核心配置文件
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        2、获取SqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();
//        2、1是否开启事务（不填则为false-开启事物，需要commit）（flase，则需要commit提交；true，则自动提交）
//        SqlSession sqlSession = sqlSessionFactory.openSession(true);
//        3、1获取UserMapper接口代理的对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
//        3、2执行方法，添加记录没有返回值
        brandMapper.add(brand);
//        3、3提交事务才能保存在数据库中（若开启了事务，必须执行commit）;XML里设置useGeneratedKeys="true" keyProperty="id"，才可以拿到返回值id
        sqlSession.commit();
        Integer id = brand.getId();
        System.out.println(id);
//        4、释放资源
        sqlSession.close();
    }

    @Test
    public void testUpdate() throws IOException {
        //设置参数
        int status=0;
//        String companyName="波导手机";
//        String brandName="波导1";
//        String description = "波导手机,手机中的战斗机";
        int ordered = 400;
        int id = 9;
        //封装测试的参数对象
        Brand brand = new Brand();
        brand.setStatus(status);
//        brand.setCompanyName(companyName);
//        brand.setBrandName(brandName);
//        brand.setDescription(description);
        brand.setOrdered(ordered);
        brand.setId(id);
        //封装Map对象
//        Map map = new HashMap();
//        map.put("status", status);
//        map.put("companyName", companyName);
//        map.put("brandName", brandName);

        //1、加载核心配置文件
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        2、获取SqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();
//        3、1获取UserMapper接口代理的对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
//        3、2执行方法，添加记录没有返回值
        int count = brandMapper.update(brand);
//        3、3提交事务才能保存在数据库中（若开启了事务，必须执行commit）
        sqlSession.commit();
        System.out.println(count);
//        4、释放资源
        sqlSession.close();
    }

    @Test
    public void testDeleteById() throws IOException {
        //设置参数
        int id = 10;

        //1、加载核心配置文件
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        2、获取SqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();
//        3、1获取UserMapper接口代理的对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
//        3、2执行方法，删除没有返回值
        brandMapper.deleteById(id);
//        3、3提交事务才能保存在数据库中（若开启了事务，必须执行commit）
        sqlSession.commit();
//        System.out.println(count);
//        4、释放资源
        sqlSession.close();
    }

    @Test
    public void testDeleteByIds() throws IOException {
        //接受参数
        int[] ids = {11,12};

        //1、加载核心配置文件
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        2、获取SqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();
//        3、1获取UserMapper接口代理的对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
//        3、2执行方法，删除没有返回值
        brandMapper.deleteByIds(ids);
//        3、3提交事务才能保存在数据库中（若开启了事务，必须执行commit）
        sqlSession.commit();
//        System.out.println(count);
//        4、释放资源
        sqlSession.close();
    }

}
