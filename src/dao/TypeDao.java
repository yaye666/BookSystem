package dao;

import model.Type;
import model.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.DataSourceUtils;

import java.sql.SQLException;
import java.util.List;

public class TypeDao {

    QueryRunner r=new QueryRunner(DataSourceUtils.getDataSource());
    public int selectTypeCount() throws SQLException {

        String sql = "select count(*) from type";
        return r.query(sql, new ScalarHandler<Long>()).intValue();
    }
    public List selectTypeList(int pageNo, int pageSize) throws SQLException {

        String sql = "select * from type limit ?,?";
        return r.query(sql, new BeanListHandler<Type>(Type.class), (pageNo-1)*pageSize,pageSize );
    }
    public List queryAll() throws SQLException {
        String sql="select * from type";
        return r.query(sql,new BeanListHandler<Type>(Type.class));
    }

    public int delete(int tid) throws SQLException {
        String sql="delete from type where tid=?";
        return r.update(sql,tid);
    }
    public int addType(String typeName) throws SQLException {
        String sql="insert into type(typename) values(?)";
        return r.update(sql,typeName);
    }
    public int updateType(Type type) throws SQLException {
        String sql="update type set typename=? where tid=?";
        return r.update(sql,type.getTypeName(),type.getTid());
    }

}
