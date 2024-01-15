package dao;

import model.Book;
import model.History;
import model.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.DataSourceUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HistoryDao {
    QueryRunner r1=new QueryRunner(DataSourceUtils.getDataSource());
    QueryRunner r=new QueryRunner();
    public int addHistory(History history,Connection conn) throws SQLException {
        String sql="insert into history(uid,name,account,bid,bookName,begintime,endtime,status) values(?,?,?,?,?,?,?,?)";
        return r.update(conn,sql,history.getUid(),history.getName(),history.getAccount(),history.getBid(),history.getBookName(),
                history.getBeginTime(),history.getEndTime(),history.getStatus());
    }

    public int selectBorrowCount(Integer uid,Integer status) throws SQLException {
        //System.out.println(word);
        System.out.println("uid:"+uid);
        System.out.println("status:"+status);
        //System.out.println();
        List<Object> params = new ArrayList<>();
        StringBuffer sql=new StringBuffer("select count(*) from history where 1=1");
        if (uid != null) {
            sql.append(" and uid =?");
            params.add(uid);
        }
        if (status != null) {
            sql.append(" and status = ?");
            params.add(status);
        }
        //String sql="select count(*) from history";
        return r1.query(sql.toString(), new ScalarHandler<Long>(),params.toArray()).intValue();

    }
    public List selectBorrowList(int pageNo, int pageSize,Integer uid,Integer status) throws SQLException {
        //String sql = "select * from history limit ?,?";
        StringBuffer sql=new StringBuffer("select * from history where 1=1");
        List<Object> params = new ArrayList<>();
        if (uid != null) {
            sql.append(" and uid = ?");
            params.add(uid);
        }
        if (status != null) {
            sql.append(" and status = ?");
            params.add(status);
        }
        params.add((pageNo-1)*pageSize);
        params.add(pageSize);
        sql.append(" order by begintime desc limit ?,?");
        return r1.query(sql.toString(), new BeanListHandler<History>(History.class),params.toArray() );

    }
    //用于通过hid查询history对象
    public List<History> getHisByID(String hid) throws SQLException {
        /*StringBuffer sql=new StringBuffer("select * from history");
        List<Object> params=new ArrayList<>();
        System.out.println("hid:"+hid);
        if(hid!=null&&hid!=""){
            sql.append(" where hid=?");
            params.add(hid);
        }*/
        String sql="select * from history where hid=?";
        System.out.println("hid:"+hid);

        return r1.query(sql,new BeanListHandler<History>(History.class),Integer.parseInt(hid));


    }
    //修改状态Status
    public int changeStatus(History history, Connection conn) throws SQLException {


        String sql="update history set status=? where hid=?";

        return r.update(conn,sql, history.getStatus(),history.getHid());
    }
    //修改截止时间
    public int changeReturnTime(History history) throws SQLException {
        String sql="update history set endtime=? where hid=?";
        return r1.update(sql,history.getEndTime(),history.getHid());
    }

}
