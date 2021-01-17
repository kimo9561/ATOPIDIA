package model.Biz;

import java.util.List;

import model.Dao.UserInfoDao;
import model.Dto.AdminInfoAnswerDto;
import model.Dto.AdminInfoDto;
import model.Dto.UserInfoAnswerDto;
import model.Dto.UserInfoDto;

public interface AdminInfoBiz{
	public Object selectAll(int startRow, int endRow);
	public AdminInfoDto selectOne(int info_No);
	public boolean insertInfo(AdminInfoDto dto);
	public boolean deleteInfo(int info_No);
	public boolean updateInfo(AdminInfoDto dto);
	public boolean saveInfo(AdminInfoDto dto);
	public List<AdminInfoDto> admin_bestInfo();
	public int getBoardCount();
	public Object service(Object obj) throws Exception;
	public int insertAnswer(AdminInfoAnswerDto dto);
	
}
