package model.Dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Dto.AdminInfoAnswerDto;
import model.Dto.AdminInfoDto;
import model.Dto.UserInfoAnswerDto;
import model.Dto.UserInfoDto;
import model.Dto.UserRecipeAnswerDto;
import model.Dto.pageDto;

import static common.JDBCTemplate.*;

public class AdminInfoDaoImpl implements AdminInfoDao {

	@Override
	public Object selectAll(Connection con, int startRow, int endRow) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<AdminInfoDto> list = new ArrayList<AdminInfoDto>();
		System.out.println("selectAll");

		try {
			pstm = con.prepareStatement(selectAllPage);
			pstm.setInt(1, startRow);
			pstm.setInt(2, endRow);

			System.out.println("3. query 준비" + selectAllPage);

			rs = pstm.executeQuery();
			System.out.println("4. query 실행 및 리턴");
			while (rs.next()) {
				AdminInfoDto pdto = new AdminInfoDto();
				pdto.setInfo_No(rs.getInt(2));
				pdto.setMember_No(rs.getInt(3));
				pdto.setInfo_Writer(rs.getString(4));
				pdto.setInfo_Title(rs.getString(5));
				pdto.setInfo_Text(rs.getString(6));
				pdto.setInfo_Date(rs.getDate(7));
				pdto.setInfo_Hits(rs.getInt(8));
				pdto.setInfo_Save(rs.getString(9));
				pdto.setGrade_Code(rs.getInt(10));
				pdto.setInfo_Image_Name(rs.getString(11));
				pdto.setInfo_Image_Realname(rs.getString(12));
				pdto.setInfo_Image_Uploadpath(rs.getString(13));

				list.add(pdto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);

			System.out.println("db 종료 \n");
		}

		return list;
	}

	@Override
	public AdminInfoDto selectOne(Connection con, int info_No) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		AdminInfoDto res = new AdminInfoDto();

		try {
			pstm = con.prepareStatement(selectOne);
			pstm.setInt(1, info_No);
			System.out.println("query 준비: " + selectOne);

			rs = pstm.executeQuery();
			System.out.println("4. 실행 및 리턴");

			while (rs.next()) {
				res.setInfo_No(rs.getInt(1));
				res.setMember_No(rs.getInt(2));
				res.setInfo_Writer(rs.getString(3));
				res.setInfo_Title(rs.getString(4));
				res.setInfo_Text(rs.getString(5));
				res.setInfo_Date(rs.getDate(6));
				res.setInfo_Hits(rs.getInt(7));
				res.setInfo_Save(rs.getString(8));
				res.setGrade_Code(rs.getInt(9));
				res.setInfo_Image_Name(rs.getString(10));
				res.setInfo_Image_Realname(rs.getString(11));
				res.setInfo_Image_Uploadpath(rs.getString(12));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
			System.out.println("db 종료 \n");
		}
		return res;
	}

	@Override
	public int insertInfo(Connection con, AdminInfoDto dto) {
		PreparedStatement pstm = null;
		int res = 0;
		System.out.println("insertInfo");
		try {
			pstm = con.prepareStatement(insert);
			pstm.setInt(1, dto.getMember_No());
			pstm.setString(2, dto.getInfo_Writer());
			pstm.setString(3, dto.getInfo_Title());
			pstm.setString(4, dto.getInfo_Text());
			pstm.setString(5, dto.getInfo_Image_Name());
			pstm.setString(6, dto.getInfo_Image_Realname());
			pstm.setString(7, dto.getInfo_Image_Uploadpath());
			System.out.println("3. query 준비: " + insert);

			res = pstm.executeUpdate();
			System.out.println("4. query 실행 및 리턴");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstm);

		}

		return res;
	}

	@Override
	public int deleteInfo(Connection con, int info_No) {
		PreparedStatement pstm = null;
		int res = 0;
		System.out.println("deleteInfo");
		try {
			pstm = con.prepareStatement(delete);
			pstm.setInt(1, info_No);
			System.out.println("03.query 준비: " + delete);

			res = pstm.executeUpdate();
			System.out.println("04.실행 및 리턴");

		} catch (SQLException e) {
			System.out.println("3/4 단계 에러");
			e.printStackTrace();
		} finally {
			close(pstm);

			System.out.println("05.db 종료\n");
		}

		return res;
	}

	@Override
	public int updateInfo(Connection con, AdminInfoDto dto) {
		PreparedStatement pstm = null;
		int res = 0;
		System.out.println("updateInfo");
		try {
			pstm = con.prepareStatement(update);
			pstm.setString(1, dto.getInfo_Title());
			pstm.setNString(2, dto.getInfo_Text());
			pstm.setString(3, dto.getInfo_Image_Name());
			pstm.setString(4, dto.getInfo_Image_Realname());
			pstm.setString(5, dto.getInfo_Image_Uploadpath());
			pstm.setInt(6, dto.getMember_No());
			pstm.setInt(7, dto.getInfo_No());
			System.out.println("3. query 준비: "+update);
			
			res = pstm.executeUpdate();
			System.out.println("4. query 실행 및 리턴");
			
		} catch (SQLException e) {
			System.out.println("3/4 단계 에러");
			e.printStackTrace();
		} finally {
			close(pstm);

			System.out.println("5. db 종료 \n");
		}

		return res;

	}

	@Override
	public int saveInfo(Connection con, AdminInfoDto dto) {
		PreparedStatement pstm = null;
		int res = 0;

		try {
			pstm = con.prepareStatement(save);
			pstm.setInt(1, dto.getInfo_No());
			System.out.println(save);

			res = pstm.executeUpdate();
			System.out.println("query 실행 및 준비");

			if (res > 0) {
				commit(con);
			}

		} catch (SQLException e) {
			System.out.println("3/4 단계 에러");
			e.printStackTrace();
		} finally {
			close(pstm);
		}

		return res;

	}
	public int deleteAnswer(Connection con, int info_No, int answer_No) {
		PreparedStatement pstm = null;
		int res = 0;
		System.out.println("deleteAnswer");
		
		try {
			pstm = con.prepareStatement(deleteAnswer);
			pstm.setInt(1, info_No);
			pstm.setInt(2, answer_No);
			System.out.println("03.query 준비: " + deleteAnswer);
			
			res = pstm.executeUpdate();
			System.out.println("04. query 실행 및 리턴");
		
		} catch (SQLException e) {
			System.out.println("3/4 단계 에러");
			e.printStackTrace();
		}finally {
			close(pstm);
			System.out.println("05. 종료\n");
		}
		
		return res;
	}

	public List<AdminInfoAnswerDto> answerlist(Connection con, int info_No) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<AdminInfoAnswerDto> res = new ArrayList<AdminInfoAnswerDto>();
		System.out.println("answerlist");

		try {
			pstm = con.prepareStatement(answerList);
			pstm.setInt(1, info_No);
			System.out.println("03.query 준비: " + answerList);

			rs = pstm.executeQuery();
			System.out.println("04.query 실행 및 리턴");

			while (rs.next()) {
				AdminInfoAnswerDto tmp = new AdminInfoAnswerDto();
				tmp.setInfo_No(rs.getInt(1));
				tmp.setAnswer_No(rs.getInt(2));
				tmp.setAnswer_Writer(rs.getString(3));
				tmp.setAnswer_Content(rs.getString(4));
				tmp.setAnswer_Date(rs.getDate(5));

				res.add(tmp);

			}

		} catch (SQLException e) {
			System.out.println("3/4 단계 에러");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
			System.out.println("05. db종료 \n");
		}

		return res;
	}
	
	public AdminInfoAnswerDto answerSelectOne(Connection con, int info_No, int answer_No) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		AdminInfoAnswerDto res = new AdminInfoAnswerDto();
		System.out.println("answerSelectOne");
		
		try {
			pstm = con.prepareStatement(answerSelectOne);
			pstm.setInt(1, info_No);
			pstm.setInt(2, answer_No);
			System.out.println("03.query 준비: " + answerSelectOne);
			
			rs = pstm.executeQuery();
			System.out.println("04. query 실행 및 리턴");
			
			while(rs.next()) {
				res.setInfo_No(rs.getInt(1));
				res.setAnswer_No(rs.getInt(2));
				res.setAnswer_Writer(rs.getString(3));
				res.setAnswer_Content(rs.getString(4));
				res.setAnswer_Date(rs.getDate(5));
			}
			
			
		} catch (SQLException e) {
			System.out.println("3/4 단계 에러");
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
		}
		
		
		return res;
	}
	
	public int updateAnswer(Connection con, AdminInfoAnswerDto dto) {
		PreparedStatement pstm = null;
		int res = 0;
		System.out.println("updateAnswer");
		try {
			pstm = con.prepareStatement(updateAnswer);
			pstm.setString(1, dto.getAnswer_Writer());
			pstm.setString(2, dto.getAnswer_Content());
			pstm.setInt(3, dto.getInfo_No());
			pstm.setInt(4, dto.getAnswer_No());
			System.out.println("03.query 준비: " + updateAnswer);
			
			res = pstm.executeUpdate();
			System.out.println("04. query 실행 및 리턴");
			
		} catch (SQLException e) {
			System.out.println("3/4 단계 에러");
			e.printStackTrace();
		}finally {
			close(pstm);
			System.out.println("05.db 종료\n");
		}
		
		return res;
	}
	
	public int addHits(Connection con, int info_No) {
		PreparedStatement pstm = null;
		int res = 0;
		System.out.println("addHits");

		try {
			pstm = con.prepareStatement(addHits);
			pstm.setInt(1, info_No);
			System.out.println("03.query 준비: " + addHits);

			res = pstm.executeUpdate();
			System.out.println("04.query 실행 및 리턴");

		} catch (SQLException e) {
			System.out.println("3/4 단계 에러");
			e.printStackTrace();
		} finally {
			close(pstm);
			System.out.println("05.db 종료\n");
		}

		return res;
	}

	@Override
	public List<AdminInfoDto> admin_bestInfo(Connection con) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<AdminInfoDto> res = new ArrayList<AdminInfoDto>();

		try {
			pstm = con.prepareStatement(bestInfoSql);
			System.out.println("3. query 준비:" + bestInfoSql);

			rs = pstm.executeQuery();
			System.out.println("4. query 실행 및 리턴");

			while (rs.next()) {
				AdminInfoDto tmp = new AdminInfoDto();
				tmp.setInfo_No(rs.getInt(1));
				tmp.setMember_No(rs.getInt(2));
				tmp.setInfo_Title(rs.getString(3));

				res.add(tmp);
			}
		} catch (SQLException e) {
			System.out.println("3/4 단계 오류");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
			System.out.println("5. db 종료\n");
		}
		return res;
	}

	@Override
	public int getBoardCount(Connection con) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int res = 0;

		try {
			pstm = con.prepareStatement(countSql);
			rs = pstm.executeQuery();

			if (rs.next()) {
				res = rs.getInt(1);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstm);

		}

		return res;
	}

	@Override
	public Object service(Connection con, Object obj) throws Exception {
		AdminInfoDaoImpl dao = new AdminInfoDaoImpl();

		int totalRow = dao.getBoardCount(con);
		int currentPage = ((Integer) obj);

		// 한페이지에 몇개의 글
		int rowPerPage = 10;

		// 전체 페이지 = (전체글 개수-1)/페이지당 글 수+1
		int totalPage = (totalRow - 1) / rowPerPage + 1;

		// 시작번호
		int startRow = (currentPage - 1) * rowPerPage + 1;
		// 끝번호
		int endRow = startRow + rowPerPage - 1;
		System.out.println(startRow);
		System.out.println(endRow);

		int pagePerGroup = 5;

		int startPage = (currentPage - 1) / pagePerGroup * pagePerGroup + 1;

		// 끝페이지=시작페이지+그룹당 보여줄 페이지-1

		int endPage = startPage + pagePerGroup - 1;

		if (endPage > totalPage) {
			endPage = totalPage;
		}

		pageDto dto = new pageDto();

		dto.setPage(currentPage);
		dto.setTotalPage(totalPage);
		dto.setStartPage(startPage);
		dto.setEndPage(endPage);
		dto.setData(dao.selectAll(con, startRow, endRow));

		return dto;
	}

	public int insertAnswer(Connection con, AdminInfoAnswerDto dto) {
		PreparedStatement pstm = null;
		int res = 0;
		System.out.println("insertAnswer");

		try {
			pstm = con.prepareStatement(addAnswer);
			pstm.setInt(1, dto.getInfo_No());
			pstm.setString(2, dto.getAnswer_Writer());
			pstm.setString(3, dto.getAnswer_Content());
			System.out.println("03.query 준비: " + addAnswer);

			res = pstm.executeUpdate();
			System.out.println("04.query 실행 및 리턴");

		} catch (SQLException e) {
			System.out.println("3/4 단계 에러");
			e.printStackTrace();
		} finally {
			close(pstm);
			System.out.println("05.db 종료\n");
		}

		return res;
	}

}
