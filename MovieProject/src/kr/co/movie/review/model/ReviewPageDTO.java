package kr.co.movie.review.model;

import java.util.List;

public class ReviewPageDTO {
	private int total; // ��ü �� ����
	private int currentPage; // ���� ���� �ִ� ������
	private List<ReviewVO> boardList; // ����¡�� �� ���
	private int totalPages; // ��ü ������ ����
	private int startPage; // ���� ������ ��ȣ
	private int endPage; // �� ������ ��ȣ
	
	
	// �����ڴ� ��ü �� ����, ���� ������, ����¡�� �� ����� �޾Ƽ� ������ 
	// ������ ������ �����ϴ�.
	public ReviewPageDTO(int total, 
						int currentPage,
						List<ReviewVO> boardList) {
		this.total = total;
		this.currentPage = currentPage;
		this.boardList = boardList;
		
		// �Ʒ����� �� 3���� ���� �����ؼ� ������ ������ ä��ϴ�.
		// ���� ���°�� ������ �� ��ư ��ü�� �ʿ����
		if(total == 0) {
			this.totalPages = 0;
			this.startPage = 0;
			this.endPage = 0;
		} else {
			// �Խñ� �� ������ �̿��� ��ü ������ �������� ���ϱ�
			this.totalPages = this.total / 5;
			if(this.total % 10 > 0) {
				// ���� �������� 10���� ���������� ������
				// �������� �������� �ϳ� �� �߰��ؾ���
				this.totalPages += 1;
			}
			
			// ���� ���� �ִ� ������ �׷��� ���� ��ȣ ���ϱ�.
			int modVal = this.currentPage % 5;
			this.startPage =
					this.currentPage / 5 * 5 + 1;
			if(modVal == 0) {
				this.startPage -= 5;
			}
			
			// �ش� ������ �׷��� �� ��ȣ ���ϱ�.
			endPage = startPage + (5 - 1);
			// �� ������ ���� ������� ������ ��ȣ��
			// totalPages�� �ʰ��ϴ� ����
			// totalPages�� ��� �����Ѵ�.
			if(endPage > totalPages) {
				endPage = totalPages;
			}
			
		}
	}// end constructor
	
	
	public int getTotal() {
		return total; // �� �� ���� ����
	}
	
	public boolean hasNoBoard() {
		return total == 0; // �Խù� ǥ�Ⱑ �Ұ����Ҷ� true ����
	}
	
	public boolean hasBoard() {
		return total > 0; // �Խù��� ������ true ����
	}
	
	public int getTotalPages() {
		return totalPages; // �������� �� ���� ����
	}
	
	public List<ReviewVO> getBoardList(){
		return boardList; // �ش� �������� ǥ���� �� ��� ����
	}
	
	public int getStartPage() {
		return startPage; // �ش� ������ �׷��� ���۹�ȣ ����
	}
	
	public int getEndPage() {
		return endPage; // �ش� ������ �׷��� ����ȣ ����
	}


	// ���� ������
	public int getCurrentPage() {
		return currentPage;
	}
	
	
	@Override
	public String toString() {
		return "BoardPageDTO [total=" + total + ", currentPage=" + currentPage + ", boardList=" + boardList
				+ ", totalPages=" + totalPages + ", startPage=" + startPage + ", endPage=" + endPage + "]";
	}

}