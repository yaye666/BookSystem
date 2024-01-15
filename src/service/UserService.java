package service;

import dao.UserDao;
import model.Book;
import model.Page;
import model.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    private UserDao uDao=new UserDao();
    public  boolean register(User user){
        try {
            if(uDao.isAccountExist(user.getAccount())) {
                return false;
            }
            if(uDao.isPhoneExist(user.getPhone())) {
                return false;
            }
            uDao.addUser(user);
            return true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }
    public User login(String account,String password){
        User user=null;
        try{
            user=uDao.selectByAccountPassword(account,password);
        }catch (SQLException e){
            e.printStackTrace();
        }
        if(user!=null){
            return user;
        }
        try{
            user=uDao.selectByPhonePassword(account,password);
        }catch (SQLException e){
            e.printStackTrace();
        }
        if(user!=null){
            return user;
        }
        return null;
    }
    public boolean isPasswordRight(User user,String password) throws SQLException {
        return uDao.isPasswordRight(user,password);
    }
    public Page getUserPage(int pageNumber,int rank) {
        Page p = new Page();
        p.setPageNumber(pageNumber);
        int pageSize = 6;
        int totalCount = 0;
        try {
            totalCount = uDao.selectUserCount(rank);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        p.SetPageSizeAndTotalCount(pageSize, totalCount);
        List list=null;
        try {
            list = uDao.selectUserList( pageNumber, pageSize,rank);
            //System.out.println(list.get(0).toString());
            //System.out.println(list.get(1).toString());
            //System.out.println(list.get(2).toString());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        p.setList(list);
        return p;
    }
    public Integer delete(Integer uid ) {
        return uDao.delete(uid);
    }
    public int addUser(User user) throws SQLException {
        return uDao.addUser(user);
    }
    public int updateUser(User user) throws SQLException {
        return uDao.updateUser(user);
    }


}
