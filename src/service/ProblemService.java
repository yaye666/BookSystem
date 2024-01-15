package service;

import dao.ProblemDao;
import model.History;
import model.Page;
import model.Problem;
import model.User;

import java.sql.SQLException;
import java.util.List;

public class ProblemService {
    ProblemDao pDao=new ProblemDao();
    public Page getProblemPage(int pageNumber,int role,int uid) {
        Page p = new Page();
        p.setPageNumber(pageNumber);
        int pageSize = 6;
        int totalCount = 0;
        try {
            totalCount = pDao.selectProblemCount(role,uid);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        p.SetPageSizeAndTotalCount(pageSize, totalCount);
        List list=null;
        try {
            list = pDao.selectProblemList( pageNumber, pageSize,role,uid);
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
    public Integer deleteProblem(Integer pid ) throws SQLException {
        return pDao.deleteProblem(pid);
    }
    public int addProblem(Problem problem) throws SQLException {
        return pDao.addProblem(problem);
    }
    public int updateProblem(Problem problem) throws SQLException {
        return pDao.updateProblem(problem);
    }

}
