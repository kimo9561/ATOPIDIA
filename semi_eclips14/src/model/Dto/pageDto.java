package model.Dto;

public class pageDto {
	private int page;
	private int totalPage;
	private int startPage;
	private int endPage;
	private Object data;
	
	
	
	public pageDto() {
		super();
	}
	
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	
	
	public pageDto(int page, int totalPage, int startPage, int endPage, Object data) {
		super();
		this.page = page;
		this.totalPage = totalPage;
		this.startPage = startPage;
		this.endPage = endPage;
		this.data = data;
	}

	@Override
	public String toString() {
		return "pageDto [page=" + page + ", totalPage=" + totalPage + ", startPage=" + startPage + ", endPage="
				+ endPage + ", data=" + data + "]";
	}
	
	
}
