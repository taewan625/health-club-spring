package healthclub.com.util;

import healthclub.com.vo.SearchVO;

public class Pagination {

	// 페이지 내비게이션의 크기 1,2,3,4,5 / 6,7,8,9,10 / 11,12,13,14,15
	private final int NAV_SIZE = 5;

	// 총 게시물 개수
	private int totalRecordCount;
	// 전체 페이지의 개수
	private int totalPageCount; 	
    
	// 페이지당 출력할 데이터 개수 -> html에 의미 없는 sequece 만들 때 사용
    private int recordSize;
    
	// 내비게이션의 첫 번째 페이지 : 현재 페이지가 13 페이지 일때 11페이지가 startPage
	private int startPage;
	// 내비게이션의 마지막 페이지 : 현재 페이지가 13 페이지 일때 15페이지가 endPage
	private int endPage;

	// 이전 페이지로 이동하는 링크를 보여줄 것인지의 여부
	private boolean existPrevPage; 
	// 다음 페이지로 이동하는 링크를 보여줄 것인지의 여부
	private boolean existNextPage; 


	
	public Pagination(int totalRecordCount, SearchVO params) {
		this.totalRecordCount = totalRecordCount;
		this.recordSize = params.getRecordSize();
		
		setPagination(params);
	}

	// 페이징 계산하는데 필요한게 3가지 totalcnt, page, pagesize
	private void setPagination(SearchVO params) {
		
		// 남는 페이지가 있을 수 있기 때문에 올림처리 (총 데이터 개수 / 한페이지당 보여줄 데이터 개수)
		this.totalPageCount = (int) Math.ceil(this.totalRecordCount / (double) params.getRecordSize()); 
		
		// 네비게이션 첫번째 페이지 : 시작페이지는 항상 1로 끝난다 (현재페이지 -> 시작페이지) : 5 -> 1, 15 -> 11, 11 -> 11, 25 -> 21
		this.startPage = (params.getCurrentPage() - 1) / NAV_SIZE * NAV_SIZE + 1;
		
		// endPage가 totalPage보다 작을 수 있기 때문에
		this.endPage = Math.min(this.startPage + NAV_SIZE - 1, this.totalPageCount);

		// 시작 페이지가 1일 때만 안 나오면 된다.
		this.existPrevPage = this.startPage != 1;

		// 마지막 페이지와 전체 페이지 개수가 동일하지 않을 경우만 가능
		this.existNextPage = this.endPage != totalPageCount; 
	}

	
	// getter, setter
	public int getTotalRecordCount() {
		return totalRecordCount;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public int getRecordSize() {
		return recordSize;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public boolean isExistPrevPage() {
		return existPrevPage;
	}

	public boolean isExistNextPage() {
		return existNextPage;
	}
	
}
