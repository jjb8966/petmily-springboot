package kh.petmily.service;

import kh.petmily.dao.AdoptReviewDao;
import kh.petmily.domain.adopt_review.AdoptReview;
import kh.petmily.domain.adopt_review.form.AdoptReviewForm;
import kh.petmily.domain.adopt_review.form.AdoptReviewModifyForm;
import kh.petmily.domain.adopt_review.form.AdoptReviewWriteForm;
import kh.petmily.domain.adopt_review.form.BoardPage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdoptReviewServiceImpl implements AdoptReviewService {

    private final AdoptReviewDao adoptReviewDao;
    private int size = 6;

    @Override
    public BoardPage getAdoptReviewPage(int pageNum, String kindOfBoard, String searchType, String keyword) {
        int total = adoptReviewDao.selectCount(kindOfBoard, searchType, keyword);

        List<AdoptReviewForm> content = adoptReviewDao.selectIndex((pageNum - 1) * size + 1, (pageNum - 1) * size + size, kindOfBoard, searchType, keyword);

        return new BoardPage(total, pageNum, size, content);
    }

    @Override
    public AdoptReviewForm getAdoptReview(int bNumber) {
        AdoptReview arForm = adoptReviewDao.findByPk(bNumber);

        return new AdoptReviewForm(
                arForm.getBNumber(),
                arForm.getMNumber(),
                boardName(arForm.getBNumber()),
                arForm.getKindOfBoard(),
                arForm.getTitle(),
                arForm.getContent(),
                arForm.getWrTime(),
                arForm.getCheckPublic(),
                arForm.getViewCount(),
                arForm.getReplyCount()
        );
    }

    @Override
    public void write(AdoptReviewWriteForm adoptReviewWriteForm) {
        AdoptReview adoptReview = toAdoptReview(adoptReviewWriteForm);
        adoptReviewDao.insert(adoptReview);
    }

    @Override
    public AdoptReviewModifyForm getAdoptReviewModify(int bNumber) {
        AdoptReview adoptReview = adoptReviewDao.findByPk(bNumber);
        AdoptReviewModifyForm modReq = toAdoptReviewModify(adoptReview);
        return modReq;
    }

    @Override
    public void modify(AdoptReviewModifyForm modReq) {
        AdoptReview adoptReview = toAdoptReviewModifyForm(modReq);
        adoptReviewDao.update(adoptReview);
    }

    @Override
    public void delete(int bNumber) {
        adoptReviewDao.delete(bNumber);
    }

    @Override
    public String boardName(int bNumber) {
        return adoptReviewDao.selectName(bNumber);
    }

    @Override
    public int updateViewCount(int bNumber) {
        return adoptReviewDao.updateViewCount(bNumber);
    }

    private AdoptReview toAdoptReview(AdoptReviewWriteForm req) {
        return new AdoptReview(
                req.getmNumber(),
                req.getKindOfBoard(),
                req.getTitle(),
                req.getContent(),
                req.getCheckPublic()
        );
    }

    private AdoptReviewModifyForm toAdoptReviewModify(AdoptReview adoptReview) {
        return new AdoptReviewModifyForm(adoptReview.getBNumber(), adoptReview.getTitle(), adoptReview.getContent(), adoptReview.getCheckPublic());
    }

    private AdoptReview toAdoptReviewModifyForm(AdoptReviewModifyForm modReq) {
        return new AdoptReview(modReq.getBNumber(), modReq.getTitle(), modReq.getContent(), "Y");
    }
}