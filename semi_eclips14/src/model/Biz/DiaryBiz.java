package model.Biz;


import java.util.List;
import model.Dto.DiaryDto;
import model.Dto.MyInfoDto;
import model.Dto.MyRecipeDto;


public interface DiaryBiz{
	public List<DiaryDto> selectAllDiary(int member_No);
	
	public DiaryDto selectOneDiary(int diary_No);
	public int deleteAllDiary(String[] chks);
	public int deleteDiary(int diary_No);
	public int insertDiary(DiaryDto dto);
	public int updateDiary(DiaryDto dto);
	public int selectdiaryno();
}
