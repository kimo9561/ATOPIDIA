package model.Biz;

import java.util.List;

import model.Dto.UserInfoAnswerDto;
import model.Dto.UserInfoDto;

public interface UserInfoBiz{
	public Object selectAll(int startRow, int endRow);
	public UserInfoDto selectOne(int info_No);
	public boolean insertInfo(UserInfoDto dto);
	public boolean deleteInfo(int info_No);
	public boolean updateInfo(UserInfoDto dto);
	public boolean saveInfo(UserInfoDto dto);
	public List<UserInfoDto> user_bestInfo();
	public int getBoardCount();
	public Object service(Object obj) throws Exception;
	public int insertAnswer(UserInfoAnswerDto dto);
	
}
