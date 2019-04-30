package com.czj.wechatappdemo.dao;

import com.czj.wechatappdemo.entity.Area;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AreaDaoTest {

    @Autowired
    private AreaDao areaDao;
    @Test
    public void queryArea() {
        List<Area> list = areaDao.queryArea();
        assertEquals(4,list.size());
    }

    @Test
    public void queryAreaById() {
        assertEquals("东苑",areaDao.queryAreaById(2).getAreaName());
    }

    @Test
    public void insertArea() {
        Area area = new Area();
        area.setAreaName("广东");
        area.setPriority(1);//数据库定义not null default '0',不设置priority会报错？？？
        area.setCreateTime(new Date());
        assertEquals(1,areaDao.insertArea(area));
    }

    @Test
    public void updateArea() {
        Area area = new Area();
        area.setAreaId(7);
        area.setAreaName("广西");
        assertEquals(1,areaDao.updateArea(area));
    }

    @Test
    public void deleteArea() {
        assertEquals(1,areaDao.deleteArea(7));
    }
}