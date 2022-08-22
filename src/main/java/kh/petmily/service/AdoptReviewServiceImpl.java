package kh.petmily.service;

import kh.petmily.dao.AdoptReviewDao;
import kh.petmily.domain.adopt_review.AdoptReview;
import kh.petmily.domain.adopt_review.form.AdoptReviewForm;
import kh.petmily.domain.adopt_review.form.AdoptReviewModifyForm;
import kh.petmily.domain.adopt_review.form.AdoptReviewWriteForm;
import kh.petmily.domain.adopt_review.form.BoardPage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdoptReviewServiceImpl implements AdoptReviewService {

    private final AdoptReviewDao adoptReviewDao;
    private int size = 6;

    @Value("${file.dir}")
    private String fileDir;

    private String getFullPath(String filename) {
        return fileDir + filename;
    }

    @Override
    public BoardPage getAdoptReviewPage(int pageNum, String kindOfBoard) {
        int total = adoptReviewDao.selectCount(kindOfBoard);
        List<AdoptReviewForm> content = adoptReviewDao.selectIndex((pageNum - 1) * size + 1, (pageNum - 1) * size + size, kindOfBoard);

        return new BoardPage(total, pageNum, size, content);
    }

    private String extractExt(String originalFilename) {
        int position = originalFilename.lastIndexOf(".");

        return originalFilename.substring(position + 1);
    }

    public String storeFile(MultipartFile file) throws IOException {
        log.info("storeFile = {} ", file.getOriginalFilename());

        if (file.isEmpty()) {
            return null;
        }

        File storeFolder = new File(fileDir);
        if (!storeFolder.exists()) {
            storeFolder.mkdir();
        }
        String originalFilename = file.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        String storeFileName = uuid + "." + extractExt(originalFilename);
        String fullPath = getFullPath(storeFileName);
        file.transferTo(new File(fullPath));

        return storeFileName;
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
                arForm.getImgPath(),
                arForm.getWrTime(),
                arForm.getCheckPublic()
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

        log.info("adoptReview.getImgPath() = {} ", adoptReview.getImgPath());

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

    private AdoptReview toAdoptReview(AdoptReviewWriteForm req){
        return new AdoptReview(
                req.getmNumber(),
                req.getKindOfBoard(),
                req.getTitle(),
                req.getContent(),
                req.getCheckPublic(),
                req.getFullPath()
        );
    }

    private AdoptReviewModifyForm toAdoptReviewModify(AdoptReview adoptReview) {
        return new AdoptReviewModifyForm(adoptReview.getBNumber(), adoptReview.getTitle(), adoptReview.getContent(), adoptReview.getCheckPublic());
    }

    private AdoptReview toAdoptReviewModifyForm(AdoptReviewModifyForm modReq) {
        return new AdoptReview(modReq.getBNumber(), modReq.getTitle(), modReq.getContent(), "Y", modReq.getFullPath());
    }
}