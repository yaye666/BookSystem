package service;

import dao.BookDao;
import dao.HistoryDao;
import dao.UserDao;
import model.*;
import utils.DataSourceUtils;
import utils.DateUtils;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class BookService {
    private BookDao bDao=new BookDao();
    public Page getBookPage(int pageNumber,String word,int rank) {
        Page p = new Page();
        p.setPageNumber(pageNumber);
        int pageSize = 6;
        int totalCount = 0;
        try {
            totalCount = bDao.selectBookCount(word);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        p.SetPageSizeAndTotalCount(pageSize, totalCount);
        List list=null;
        try {
            list = bDao.selectBookList( pageNumber, pageSize,word,rank);
            System.out.println(list.get(0).toString());
            System.out.println(list.get(1).toString());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        p.setList(list);
        return p;
    }
    public int delete(int tid) throws SQLException {
        return bDao.delete(tid);

    }
    public int addBook(Book book) throws SQLException {
        return bDao.addBook(book);
    }
    public int updateBook(Book book) throws SQLException {
        return bDao.updateBook(book);
    }
    //用户借阅图书
    public void borrowBook(User user, String bid) throws SQLException {
        Connection conn= DataSourceUtils.getConnection();
        try {
            //事物自动提交关闭
            conn.setAutoCommit(false);
            //根据bid获取图书信息
            List<Book> list=bDao.getListByID(null,bid);
            Book book=list.get(0);
            System.out.println("这里是Service层");
            System.out.println(book.toString());
            //创建图书借阅记录
            History history=new History();
            history.setUid(user.getUid());
            history.setName(user.getName());
            history.setAccount(user.getAccount());
            history.setBid(book.getBid());
            history.setBookName(book.getBookName());

            Date d = new Date();
            history.setBeginTime(d);
            history.setEndTime(DateUtils.dateAdd(d, user.getLendNum()));//还书时间  借书时间 + lend_num
            history.setStatus(1);
            HistoryDao hDao=new HistoryDao();
            hDao.addHistory(history,conn);
            //改变图书库存和借阅次数  book.num--,book.times++
            Integer num=book.getNum();
            book.setNum(--num);
            Integer times=book.getTimes();
            book.setTimes(++times);
            bDao.changeNum(book,conn);
            //改变用户信息  user。times++
            user.setTimes(user.getTimes()+1);
            user.setMaxNum(user.getMaxNum()-1);
            UserDao uDao=new UserDao();
            uDao.changeUserNum(user,conn);
            conn.commit();//事务提交
        }
        catch (Exception e){
            e.printStackTrace();
            conn.rollback();//只要一个发生异常就回滚
        }
    }
}
