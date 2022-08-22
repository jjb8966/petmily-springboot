package kh.petmily.service;

import kh.petmily.domain.adopt_review.form.AdoptReviewForm;
import kh.petmily.domain.adopt_review.form.AdoptReviewModifyForm;
import kh.petmily.domain.adopt_review.form.AdoptReviewWriteForm;
import kh.petmily.domain.adopt_review.form.BoardPage;

public interface AdoptReviewService {

    // ====== 검색 추가 ======
    public BoardPage getAdoptReviewPage(int pbNumber, String kindOfBoard, String searchType, String keyword);

    public AdoptReviewForm getAdoptReview(int bNumber);

    public void write(AdoptReviewWriteForm adoptReviewWriteForm);

    public AdoptReviewModifyForm getAdoptReviewModify(int bNumber);

    public void modify(AdoptReviewModifyForm modReq);

    public void delete(int bNumber);

    String boardName(int bNumber);

    // ====== 조회수 추가 ======
    public int updateViewCount(int bNumber);
}