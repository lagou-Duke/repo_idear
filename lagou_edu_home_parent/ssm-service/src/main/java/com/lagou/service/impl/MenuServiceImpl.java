package com.lagou.service.impl;

import com.lagou.dao.MenuMapper;
import com.lagou.domain.Menu;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;


    /**
     * 查询全部的父子菜单信息
     * */
    @Override
    public List<Menu> findSubMenuListByPid(int pid) {
        List<Menu> list = menuMapper.findSubMenuListByPid(pid);
        return list;
    }


    /**
     * 查询所有菜单
     */
    @Override
    public List<Menu> findAllMenu() {
        List<Menu> allMenu = menuMapper.findAllMenu();
        return allMenu;
    }

    /**
     * 根据id查询对应的菜单信息
     * @param id
     * @return
     */
    @Override
    public Menu findMenuById(Integer id) {
        Menu menu = menuMapper.findMenuById(id);

        return menu;

    }


    /**
     * 添加菜单
     */
    @Override
    public void saveMenu(Menu menu) {
        //补全信息  前台传递了  这里就不用写了
//        Date date = new Date();
//        menu.setCreatedTime(date);
//        menu.setUpdatedTime(date);
//        menu.setUpdatedBy("system");
//        menu.setCreatedBy("system");

        menuMapper.saveMenu(menu);

    }


    /**
     * 修改菜单
     */
    @Override
    public void updateMenu(Menu menu) {
        //补全信息  前台传递了  这里就不用写了 正常情况下这里不需要写CreatedTime  CreatedBy
////        Date date = new Date();
////        menu.setCreatedTime(date);
////        menu.setUpdatedTime(date);
////        menu.setUpdatedBy("system");
////        menu.setCreatedBy("system");

        menuMapper.updateMenu(menu);
    }


}
