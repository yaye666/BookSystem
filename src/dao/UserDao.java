package dao;
import model.Book;
import model.History;
import org.apache.commons.dbutils.QueryRunner;
import model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.DataSourceUtils;
public class UserDao {
    QueryRunner r=new QueryRunner(DataSourceUtils.getDataSource());
    public int addUser(User user) throws SQLException{
        String sql="insert into user(account,name,password,phone,times,lendnum,maxnum,role)values(?,?,?,?,0,?,?,?)";
        return r.update(sql,user.getAccount(),user.getName(),user.getPassword(),user.getPhone(),user.getLendNum(),user.getMaxNum(),user.getRole());

    }
    public int updateUser(User user) throws SQLException {
        String sql="update user set password=?,name=?,phone=?,lendnum=?,maxnum=? where uid=?";
        System.out.println(user.toString());
        return r.update(sql,user.getPassword(),user.getName(),user.getPhone(),user.getLendNum(),user.getMaxNum(),user.getUid());
    }

    public boolean isAccountExist(String account)throws SQLException{
        String sql="select * from user where account=?";
        User u=r.query(sql,new BeanHandler<User>(User.class),account);
        if(u==null){
            return false;
        }else{
            return true;
        }
    }
    public boolean isPhoneExist(String phone) throws SQLException {

        String sql = "select * from user where phone = ?";
        User u = r.query(sql, new BeanHandler<User>(User.class),phone);
        if(u==null) {
            return false;
        }else {
            return true;
        }
    }
    public boolean isPasswordRight(User user,String password) throws SQLException {
        String sql="select * from user where account=? and password=?" ;
        User u=r.query(sql,new BeanHandler<User>(User.class),user.getAccount(),password);
        if(u==null){
            return false;
        }else{
            return true;
        }
    }
    public User selectByAccountPassword(String account,String password) throws SQLException {

        String sql = "select * from user where account=? and password=?";
        return r.query(sql, new BeanHandler<User>(User.class),account,password);
    }
    public User selectByPhonePassword(String phone,String password) throws SQLException {

        String sql = "select * from user where phone=? and password=?";
        return r.query(sql, new BeanHandler<User>(User.class),phone,password);
    }
    public int selectUserCount(int rank) throws SQLException {
        StringBuffer sql=new StringBuffer("select count(*) from user");
        if(rank==1){
            sql.append(" where role=1");
        }
        //String sql = "select count(*) from user";
        return r.query(sql.toString(), new ScalarHandler<Long>()).intValue();
    }
    public List selectUserList(int pageNo, int pageSize,int rank) throws SQLException {
        StringBuffer sql=new StringBuffer("select * from user");
        if(rank==1){
            sql.append(" where role=1");
            sql.append(" order by times desc");
        }
        sql.append(" limit ?,?");
        //String sql = "select * from user limit ?,?";
        return r.query(sql.toString(), new BeanListHandler<User>(User.class), (pageNo-1)*pageSize,pageSize );
    }
    public int delete(Integer uid)  {

        String sql = "delete from user where uid = ?";
        Object[] params={uid};
        try{
            return r.update(sql,params);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }
    //改变用户借阅信息
    public int changeUserNum(User user, Connection conn) throws SQLException {
        QueryRunner r1=new QueryRunner();
        String sql="update user set times=?,maxnum=? where uid=?";
        return r1.update(conn,sql,user.getTimes(),user.getMaxNum(),user.getUid());
    }
    //根据UID获取User
    public List<User> getUserByID(String uid){
        StringBuffer sql=new StringBuffer("select * from user");
        List<Object> params=new ArrayList<>();

        if(uid!=null&&uid!=""){
            sql.append(" where uid=?");
            params.add(uid);
        }
        try {
            return r.query(sql.toString(),new BeanListHandler<User>(User.class),params.toArray());
        }catch (SQLException e){
            return null;
        }

    }
}
