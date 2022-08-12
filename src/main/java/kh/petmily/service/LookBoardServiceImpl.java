package kh.petmily.service;

import kh.petmily.dao.LookBoardDao;
import kh.petmily.domain.look_board.form.LookBoardListForm;
import kh.petmily.domain.look_board.form.LookBoardPageForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LookBoardServiceImpl implements LookBoardService {
    private final LookBoardDao lookBoardDao;
    private int size = 6;

    @Override
    public LookBoardPageForm getLookPage(int pageNo) {
        int total = lookBoardDao.selectCount();
        List<LookBoardListForm> content = lookBoardDao.selectIndex((pageNo - 1) * size + 1, (pageNo - 1) * size + size);

        return new LookBoardPageForm(total, pageNo, size, content);
    }
}