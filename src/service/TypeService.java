package service;

import dao.TypeDao;
import model.Page;
import model.Type;

import java.sql.SQLException;
import java.util.List;

public class TypeService {
    private TypeDao tDao=new TypeDao();
    public Page getTypePage(int pageNumber) {
        Page p = new Page();
        p.setPageNumber(pageNumber);
        int pageSize = 5;
        int totalCount = 0;
        try {
            totalCount = tDao.selectTypeCount();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        p.SetPageSizeAndTotalCount(pageSize, totalCount);
        List list=null;
        try {
            list = tDao.selectTypeList( pageNumber, pageSize);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        p.setList(list);
        return p;
    }
    public List<Type> queryAll(String tid,String typeName) throws SQLException {
        return tDao.queryAll();
    }
    public int delete(int tid) throws SQLException {
        return tDao.delete(tid);

    }
    public int addType(String typeName) throws SQLException {
        return tDao.addType(typeName);
    }
    public int updateType(Type type) throws SQLException {
        return tDao.updateType(type);
    }

}
