package service;

import dao.BookDao;
import dao.HistoryDao;
import dao.UserDao;
import model.Book;
import model.History;
import model.Page;
import model.User;
import utils.DataSourceUtils;
import utils.DateUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class HistoryService {
    private HistoryDao hDao=new HistoryDao();
    public Page getHistoryPage(int pageNumber,Integer uid,Integer status) {
        Page p = new Page();
        p.setPageNumber(pageNumber);
        int pageSize = 6;
        int totalCount = 0;
        try {
            totalCount = hDao.selectBorrowCount(uid,status);
            System.out.println("count:"+totalCount);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        p.SetPageSizeAndTotalCount(pageSize, totalCount);
        List list=null;
        try {
            list = hDao.selectBorrowList( pageNumber, pageSize,uid,status);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        p.setList(list);
        System.out.println("uid:"+uid);
        System.out.println("status:"+status);
        return p;
    }

    public void returnBook(String hid) throws SQLException {
        Connection conn= DataSourceUtils.getConnection();
        try {
            //事物自动提交关闭
            conn.setAutoCommit(false);
            //获取history，修改status为2，表示为已归还
            List<History> list=hDao.getHisByID(hid);
            System.out.println("---这里是Service层");
            History history=list.get(0);
            System.out.println(history.toString());

            history.setStatus(2);
            hDao.changeStatus(history,conn);
            //获取bid，修改book库存num
            String bid= String.valueOf(history.getBid());
            BookDao bDao=new BookDao();
            Book book=bDao.getListByID(null,bid).get(0);
            book.setNum(book.getNum()+1);
            bDao.changeNum(book,conn);
            //获取uid，修改user借阅数maxnum
            UserDao uDao=new UserDao();
            /*if(role==2){
                //管理员

            }else{

            }*/
            String uid=String.valueOf(history.getUid());
            User user=uDao.getUserByID(uid).get(0);
            user.setMaxNum(user.getMaxNum()+1);
            uDao.changeUserNum(user,conn);

            conn.commit();
        }
        catch (Exception e){
            e.printStackTrace();
            conn.rollback();//只要一个发生异常就回滚
        }
    }public int changeReturnTime(History history) throws SQLException {
        return hDao.changeReturnTime(history);
    }
}
