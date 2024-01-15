package dao;

import model.Problem;
import model.Type;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.DataSourceUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProblemDao {
    QueryRunner r=new QueryRunner(DataSourceUtils.getDataSource());
    public int selectProblemCount(int role,int uid) throws SQLException {
        StringBuffer sql=new StringBuffer("select count(*) from problem");
        //String sql = "select count(*) from problem";
        List<Object> params=new ArrayList<>();
        if (role == 1) {
            sql.append(" where uid=?");
            params.add(uid);
        }
        return r.query(sql.toString(),params.toArray(), new ScalarHandler<Long>()).intValue();
    }
    public List selectProblemList(int pageNo, int pageSize,int role,int uid) throws SQLException {
        StringBuffer sql=new StringBuffer("select * from problem");
        List<Object> params=new ArrayList<>();
        if(role==1){
            sql.append(" where uid=?");
            params.add(uid);
        }
        params.add((pageNo-1)*pageSize);
        params.add(pageSize);
        sql.append(" limit ?,?");

        //String sql = "select * from problem limit ?,?";
        return r.query(sql.toString(), new BeanListHandler<Problem>(Problem.class),params.toArray() );
    }


    public int deleteProblem(int pid) throws SQLException {
        String sql="delete from problem where pid=?";
        return r.update(sql,pid);
    }
    public int addProblem(Problem problem) throws SQLException {
        String sql="insert into problem(uid,title,page,content,link,status,time) values(?,?,?,?,?,?,?)";
        return r.update(sql,problem.getUid(),problem.getTitle(),problem.getPage(),problem.getContent(),problem.getLink(),problem.getStatus(),problem.getTime());
    }
    public int updateProblem(Problem problem) throws SQLException {
        String sql="update problem set status=? where pid=?";
        return r.update(sql,problem.getStatus(),problem.getPid());
    }
}
