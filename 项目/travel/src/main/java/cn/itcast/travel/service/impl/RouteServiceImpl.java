package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.*;
import cn.itcast.travel.dao.impl.*;
import cn.itcast.travel.domain.*;
import cn.itcast.travel.service.RouteService;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author goodtime
 * @create 2020-01-05 4:05 下午
 */
public class RouteServiceImpl implements RouteService {

    RouteDao routeDao = new RouteDaoImpl();
    PageBean pageBean = new PageBean<Route>();
    SellerDao sellerDao = new SellerDaoImpl();
    RouteImgDao routeImgDao =  new RouteImgDaoImpl();
    FavoriteDao favoriteDao = new FavoriteDaoImpl();
    UserDao userDao = new UserDaoImpl();


    @Override
    public PageBean queryForCate(String cid, String currentPage, String pageCount, String rname) {
        int currentPageReal;
        int pageCountReal;
        String rnameReal = "ignoreme";
        int allPage;
        int allRows;

        if(pageCount == null || pageCount.length() == 0){
            pageCountReal = 10;
        }else {
            pageCountReal = Integer.parseInt(pageCount);
        }

        if(cid == null || cid.length() == 0 || cid.equals("null")){
            cid = "0";
        }

        if(rname != null && rname.length() != 0 && !rname.equals("null")){
            try {
                rnameReal = new String(rname.getBytes("ISO8859-1"),"UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if(!rnameReal.equals("ignoreme")){
            allRows = routeDao.findAllRouteCountByRnameAndCid(rnameReal,Integer.parseInt(cid));
        }
        else {
            allRows = routeDao.findAllRouteCountByCid(Integer.parseInt(cid));
        }

        allPage = (allRows % pageCountReal == 0)? allRows / pageCountReal : (allRows / pageCountReal + 1);

        if(currentPage == null || currentPage.length() == 0 || currentPage.equals("null")|| Integer.parseInt(currentPage) < 1){
            currentPageReal = 1;
        }
        else{
            if(currentPage.length() != 0 && Integer.parseInt(currentPage)  > allPage){
                if ( allPage != 0){
                currentPageReal = allPage;}
                else{ currentPageReal = 1;
                }

            }
            else{currentPageReal = Integer.parseInt(currentPage);}
        }

        pageBean.setAllRows(allRows);
        pageBean.setCurrentPages(currentPageReal);
        pageBean.setPageCount(pageCountReal);
        pageBean.setList(routeDao.findRouteByCidAndRname(Integer.parseInt(cid),currentPageReal,pageCountReal,rnameReal));
        return pageBean;
    }

    @Override
    public List queryForRoute(String rid, String username,String coll) {

        List arrayList = new ArrayList();
        int ridReal = Integer.parseInt(rid);

        Route routeByRid = routeDao.findRouteByRid(ridReal);
        int sid = routeByRid.getSid();
        List imgByRid = routeImgDao.findImgByRid(ridReal);
        Seller seller = sellerDao.findBySid(sid);
        arrayList.add(routeByRid);//第0元素
        arrayList.add(imgByRid);
        arrayList.add(seller);
        int uid = 0;
        if(username != null && username.length() != 0){
        uid = userDao.findByUsernameReturnUid(username);
        }
        if(username != null && username.length() != 0 && coll.equals("")){
        boolean findByRidAndUid = favoriteDao.findByRidAndUid(ridReal, uid);
        arrayList.add(findByRidAndUid);
        }else{
            arrayList.add(false);//第三元素
        }

//      给前端信息，确实点击收藏是否需要登录
        if(coll.equals("1") && (username == null || username.length() == 0)){
                arrayList.add(false);
        }else if(coll.equals("1")){
            favoriteDao.addByRidAndUid(ridReal,uid);
            arrayList.add(true);//第四元素
            arrayList.set(3,true);
            }
        else{
            arrayList.add(true);
        }
        int findCountByRid = favoriteDao.findCountByRid(ridReal);
        arrayList.add(findCountByRid);//第五个元素
        return arrayList;
    }
}
