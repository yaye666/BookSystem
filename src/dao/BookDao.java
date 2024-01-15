package dao;

import model.Book;
import model.Type;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.DataSourceUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
    QueryRunner r=new QueryRunner(DataSourceUtils.getDataSource());
    public int selectBookCount(String word) throws SQLException {
        //System.out.println(word);
        StringBuffer sql = new StringBuffer("select count(*) from book LEFT JOIN type ON book.tid = type.tid");
        if(word!=null&& !word.isEmpty()){
            sql.append(" where bookname like '%"+word+"%'");
            sql.append(" or typename like '%"+word+"%'");
            sql.append(" or author like '%"+word+"%'");
            sql.append(" or press like '%"+word+"%'");
        }
        System.out.println("word:"+word);
        System.out.println(sql);
        int i= r.query(sql.toString(), new ScalarHandler<Long>()).intValue();
        System.out.println(i);
        return i;
    }
    public List selectBookList(int pageNo, int pageSize,String word,int rank) throws SQLException {
        StringBuffer sql=new StringBuffer("select book.*,type.typename from book LEFT JOIN type ON book.tid = type.tid");
        if(word!=null&& !word.isEmpty()){
            sql.append(" where bookname like '%"+word+"%'");
            sql.append(" or typename like '%"+word+"%'");
            sql.append(" or author like '%"+word+"%'");
            sql.append(" or press like '%"+word+"%'");
        }
        if(rank==1){
            sql.append((" order by times desc"));
        }
        sql.append(" limit ?,?");
        System.out.println(sql);
        //String sql = "select book.*,type.typename from book,type where book.tid=type.tid limit ?,?";
        return r.query(sql.toString(), new BeanListHandler<Book>(Book.class), (pageNo-1)*pageSize,pageSize );
    }
    public int addBook(Book book) throws SQLException {
        String sql="insert into book(bookname,author,num,press,tid,times) values(?,?,?,?,?,0)";
        return r.update(sql,book.getBookName(),book.getAuthor(),book.getNum(),book.getPress(),book.getTid());
    }
    public int delete(int bid) throws SQLException {
        String sql="delete from book where bid=?";
        return r.update(sql,bid);
    }
    public int updateBook(Book book) throws SQLException {
        String sql="update book set tid=?,press=?,num=? where bid=?";

         return r.update(sql,book.getTid(),book.getPress(),book.getNum(),book.getBid());
    }
    //查询
    public List<Book> getListByID(String bookName,String bid){
        StringBuffer sql=new StringBuffer("select * from book");
        List<Object> params=new ArrayList<>();
        if(bookName!=null&&bookName!=""){
            sql.append(" where bookname=?");
            params.add(bookName);
        }
        if(bid!=null&&bid!=""){
            sql.append(" where bid=?");
            params.add(bid);
        }
        try {
            return r.query(sql.toString(),new BeanListHandler<Book>(Book.class),params.toArray());
        }catch (SQLException e){
            return null;
        }

    }
    //改变图书库存
    public int changeNum(Book book, Connection conn) throws SQLException {
        QueryRunner r1=new QueryRunner();

        String sql="update book set num=?,times=? where bid=?";

        return r1.update(conn,sql,book.getNum(),book.getTimes(),book.getBid());
    }
}
