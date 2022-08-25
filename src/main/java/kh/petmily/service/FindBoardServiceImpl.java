package kh.petmily.service;

import kh.petmily.dao.FindBoardDao;
import kh.petmily.domain.find_board.FindBoard;
import kh.petmily.domain.find_board.form.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindBoardServiceImpl implements FindBoardService {
    private final FindBoardDao findBoardDao;
    private int size = 6;

    private String getFullPath(String filename, String filePath) {
        return filePath + filename;
    }

    private String extractExt(String originalFilename) {
        int position = originalFilename.lastIndexOf(".");

        return originalFilename.substring(position + 1);
    }

    @Override
    public String storeFile(MultipartFile file, String filePath) throws IOException {
        log.info("storeFile = {} ", file.getOriginalFilename());

        if (file.isEmpty()) {
            return null;
        }

        File storeFolder = new File(filePath);
        if (!storeFolder.exists()) {
            storeFolder.mkdir();
        }
        String originalFilename = file.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        String storeFileName = uuid + "." + extractExt(originalFilename);
        String fullPath = getFullPath(storeFileName, filePath);
        file.transferTo(new File(fullPath));

        return storeFileName;
    }

    @Override
    public void write(FindBoardWriteForm fwForm) {
        FindBoard findBoard = toFindFromFW(fwForm);
        findBoardDao.insert(findBoard);
    }

    private FindBoard toFindFromFW(FindBoardWriteForm req) {
        return new FindBoard(req.getMNumber(), req.getSpecies(), req.getKind(), req.getLocation(), req.getFullPath(), req.getTitle(), req.getContent());
    }

    @Override
    public void modify(FindBoardModifyForm fmForm) {
        FindBoard findBoard = toFindFromFM(fmForm);
        findBoard.setFaNumber(fmForm.getFaNumber());
        findBoardDao.update(findBoard);
    }

    private FindBoard toFindFromFM(FindBoardModifyForm req) {
        return new FindBoard(req.getMNumber(), req.getSpecies(), req.getKind(), req.getLocation(), req.getFullPath(), req.getTitle(), req.getContent());
    }

    @Override
    public void delete(int faNumber) {
        findBoardDao.delete(faNumber);
    }

    @Override
    public FindBoardDetailForm getDetailForm(int faNumber) {
        FindBoard findBoard = findBoardDao.findByPk(faNumber);
        FindBoardDetailForm detailForm = toDetailForm(findBoard);

        return detailForm;
    }

    private FindBoardDetailForm toDetailForm(FindBoard findBoard) {
        return new FindBoardDetailForm(findBoard.getFaNumber(), findBoard.getMNumber(), findName(findBoard.getFaNumber()), findBoard.getSpecies(), findBoard.getKind(), findBoard.getLocation(), findBoard.getAnimalState(), findBoard.getImgPath(), findBoard.getWrTime(), findBoard.getTitle(), findBoard.getContent());
    }

    @Override
    public FindBoardPageForm getFindPage(int pageNo) {
        int total = findBoardDao.selectCount();
        List<FindBoardListForm> content = findBoardDao.selectIndex((pageNo - 1) * size + 1, (pageNo - 1) * size + size);

        return new FindBoardPageForm(total, pageNo, size, content);
    }

    @Override
    public FindBoardModifyForm getModifyForm(int faNumber) {
        FindBoard findBoard = findBoardDao.findByPk(faNumber);
        FindBoardModifyForm modifyForm = toModifyForm(findBoard);

        return modifyForm;
    }

    private FindBoardModifyForm toModifyForm(FindBoard findBoard) {
        return new FindBoardModifyForm(findBoard.getSpecies(), findBoard.getKind(), findBoard.getLocation(), findBoard.getTitle(), findBoard.getContent());
    }

    @Override
    public String findName(int faNumber) {
        return findBoardDao.selectName(faNumber);
    }

    @Override
    public FindBoardPageForm getMembersFindPage(int pageNo, int mNumber, String matched) {
        int total = findBoardDao.selectMemberCount(mNumber, matched);
        List<FindBoardListForm> content = findBoardDao.selectMemberIndex((pageNo - 1) * size + 1, (pageNo - 1) * size + size, mNumber, matched);

        return new FindBoardPageForm(total, pageNo, size, content);
    }

    @Override
    public FindBoard getFindBoard(int faNumber) {
        return findBoardDao.findByPk(faNumber);
    }
}