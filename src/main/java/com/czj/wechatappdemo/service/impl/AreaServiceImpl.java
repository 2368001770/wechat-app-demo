package com.czj.wechatappdemo.service.impl;

import com.czj.wechatappdemo.dao.AreaDao;
import com.czj.wechatappdemo.entity.Area;
import com.czj.wechatappdemo.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaDao areaDao;

    @Override
    public List<Area> getAreaList() {
        return areaDao.queryArea();
    }

    @Override
    public Area getAreaById(int areaId) {
        return areaDao.queryAreaById(areaId);
    }

    @Transactional
    @Override
    public boolean addArea(Area area) {
        if(area.getAreaName() != null && !area.getAreaName().equals("")){
            area.setCreateTime(new Date());
            area.setLastEditTime(new Date());
            try{
                int effectedNum = areaDao.insertArea(area);
                if(effectedNum > 0){
                    return true;
                }else {
                    throw new RuntimeException("插入区域信息失败！");
                }
            }catch (Exception e){
                throw new RuntimeException("插入区域信息失败： " + e.getMessage());
            }
        }else {
            throw new RuntimeException("区域信息不能为空！");
        }
    }

    @Transactional
    @Override
    public boolean modifyArea(Area area) {
        if(area.getAreaId() != null && area.getAreaId() > 0){
            area.setLastEditTime(new Date());
            try{
                int effectedNum = areaDao.updateArea(area);
                if(effectedNum > 0){
                    return true;
                }else {
                    throw new RuntimeException("更新区域信息失败！");
                }
            }catch (Exception e){
                throw new RuntimeException("更新区域信息失败: " + e.getMessage());
            }
        }else {
            throw new RuntimeException("区域信息不能为空！");
        }
    }

    @Transactional
    @Override
    public boolean deleteArea(int areaId) {
        if(areaId > 0){
           try{
               int effectedNum = areaDao.deleteArea(areaId);
               if(effectedNum > 0){
                   return true;
               }else {
                   throw new RuntimeException("删除区域信息失败！");
               }
           }catch (Exception e){
               throw new RuntimeException("删除区域信息失败 ：" + e.getMessage());
           }
        }else {
            throw new RuntimeException("区域id不能为空！");
        }
    }
}
