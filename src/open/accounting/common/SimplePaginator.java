package open.accounting.common;

import java.util.ArrayList;
import java.util.List;

public class SimplePaginator {
	private int size; //페이지 사이즈
	private int pageNo; // 페이지 넘버
	private int totalCount; // 총 게시물 갯수
    private boolean nextPage = false; // 이전존재 유무
    private boolean prevPage = false; // 다음존재 유무
    private int prevInt; // 이전을 눌렀을 때 이동 되는 페이지
    private int nextInt; // 다음을 눌렀을 때 이동 되는 페이지
    private int lastInt; // 마지막 페이지 
    
    public int getPrevInt() {
		return prevInt;
	}

	public int getNextInt() {
		return nextInt;
	}

	private List<Integer> list = new ArrayList<Integer>(); // 리스트 보여주기
    
    public SimplePaginator(int size, int pageNo, int totalCount) {
    	this.size = size;
    	this.pageNo = pageNo;
    	this.totalCount = totalCount;
    	makePaging();
    }
    
    private void getPaging() {
    	int tempNum = totalCount%size;
    	if((tempNum) == 0) {
    		lastInt = totalCount/size;
    	} else {
    		lastInt = (totalCount/size) + 1;
    	}
    	
    	//첫페이지는 정확하게 그린다.
    	if(pageNo / 10 == 0) {
    		int count = totalCount/size;
    		if(count > 0 && totalCount % size == 0) {
    			count --;
    		}
    		if(count > 10) {
    			nextInt = 10;
    			nextPage = true;
    			for(int i = 1 ; i <= 10 ; i++) {
    				list.add(i);
    			}
    		} else {
    			for(int i = 1 ; i <= count + 1 ; i++ ) {
        			list.add(i);
        		}
    		}
    	}else {
    		int start = pageNo / 10;
    		
    		for(int i = start * 10 + 1 ; i <= (start + 1) * 10 ; i ++) {
    			if(i  <= lastInt) {
    				list.add(i);
    			} 
    		}
    		
    	
    		prevInt = start * 10 - 1;
    		nextInt = (start + 1) * 10;
    		nextPage = false;
    		prevPage = true;
    	}
    }
    
    public void makePaging() {
		int tempNum = totalCount % size;
		if ((tempNum) == 0) {
			lastInt = totalCount / size;
		} else {
			lastInt = (totalCount / size) + 1;
		}

		int start = pageNo / 10;
		
		if(pageNo % 10 == 0) {
			start = start - 1;
		}
		
		for(int i = (start * 10) + 1; i <= (start + 1) * 10; i++) {
			if(lastInt >= i) {
				list.add(i);
			}
		}
		
		if(list.size() >= 10) {
			nextPage = true;
			nextInt = list.get(9) + 1;
		}
		
		if(start >= 1) {
			prevPage = true;
			prevInt = list.get(0) - 1;
		}
	}

	public int getSize() {
		return size;
	}

	public int getPageNo() {
		return pageNo;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public boolean isNextPage() {
		return nextPage;
	}

	public boolean isPrevPage() {
		return prevPage;
	}

	public List<Integer> getList() {
		return list;
	}

	public int getLastInt() {
		return lastInt;
	}
}
