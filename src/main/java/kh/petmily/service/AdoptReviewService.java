package kh.petmily.service;

import kh.petmily.domain.adopt_review.form.AdoptReviewForm;
import kh.petmily.domain.adopt_review.form.AdoptReviewModifyForm;
import kh.petmily.domain.adopt_review.form.BoardPage;
import kh.petmily.domain.adopt_review.form.AdoptReviewWriteForm;

public interface AdoptReviewService {
    public BoardPage getAdoptReviewPage(int pbNumber, String kindOfBoard);

    public AdoptReviewForm getAdoptReview(int bNumber);

    public void write(AdoptReviewWriteForm adoptReviewWriteForm);

    public AdoptReviewModifyForm getAdoptReviewModify(int bNumber);

    public void modify(AdoptReviewModifyForm modReq);

    public void delete(int bNumber);

    String boardName(int bNumber);
}