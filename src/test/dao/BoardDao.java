package test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.JdbcUtil;
import test.vo.MyBoardVo;

public class BoardDao {
	private  static BoardDao instance=new BoardDao();
	private BoardDao() {}
	public static BoardDao getInstance() {
		return instance;
	}
	public int getCount(String field,String keyword) {//�쟾泥� 湲��쓽 媛��닔 援ы븯湲�
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getConn();
			String sql="select NVL(count(*),0) from myboard";
			if(field!=null && !field.equals("")) {
				sql+=" where "+field + " like '%"+keyword +"%'";
			}
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				int cnt=rs.getInt(1);
				return cnt;
			}
			return 0;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			JdbcUtil.close(con, pstmt, rs);
		}	
	}
	public ArrayList<MyBoardVo> list(int startRow,int endRow,
			String field,String keyword){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getConn();
			String sql=null;
			if(field==null || field.equals("")) { //검색조건이 없는경우
				sql="select * from" + 		
					"    (" + 
					"        select aa.*,rownum rnum from" + 
					"        (" + 
					"            select * from myboard order by num desc" + 
					"        )aa" + 
					")where rnum>=? and  rnum<=?";
			}else {
				sql="select * from" + 		
					"    (" + 
					"        select aa.*,rownum rnum from" + 
					"        (" + 
					"            select * from myboard where "+field+" like '%"+keyword+"%' order by num desc" + 
					"        )aa" + 
					")where rnum>=? and  rnum<=?";
			}
		    pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,startRow);
			pstmt.setInt(2,endRow);
			rs=pstmt.executeQuery();
			ArrayList<MyBoardVo> list=new ArrayList<MyBoardVo>();
			while(rs.next()) {
				MyBoardVo vo=new MyBoardVo(rs.getInt("num"),
						rs.getString("writer"), 
						rs.getString("pwd"),
						rs.getString("title"), 
						rs.getString("content"), 
						rs.getDate("regdate"));
				list.add(vo);
			}
			return list;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			JdbcUtil.close(con, pstmt, rs);
		}	
	}
	public MyBoardVo detail(int num){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getConn();
			String sql="select * from myboard where num=?";	
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,num);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				MyBoardVo vo=new MyBoardVo(rs.getInt("num"),
						rs.getString("writer"), 
						rs.getString("pwd"),
						rs.getString("title"), 
						rs.getString("content"), 
						rs.getDate("regdate"));
				return vo;
			}
			return null;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			JdbcUtil.close(con, pstmt, rs);
		}	
	}
	public int insert(MyBoardVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql=
			"insert into myboard values(myboard_seq.nextval,?,?,?,?,sysdate)";
		try {
			con=JdbcUtil.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,vo.getWriter());
			pstmt.setString(2, vo.getPwd());
			pstmt.setString(3,vo.getTitle());
			pstmt.setString(4,vo.getContent());
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			JdbcUtil.close(con, pstmt, null);
		}
	}
	public int delete(int num,int pwd) {
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql=
			"delete from myboard where num=? and pwd=?";
		try {
			con=JdbcUtil.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,num);
			pstmt.setInt(2,pwd);
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			JdbcUtil.close(con, pstmt, null);
		}
	}
}













