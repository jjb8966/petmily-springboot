package kh.petmily.service;

import kh.petmily.domain.abandoned_animal.AbandonedAnimal;
import kh.petmily.domain.abandoned_animal.form.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AbandonedAnimalService {

    public AbandonedAnimalDetailForm getDetailForm(int abNumber);

    public AbandonedAnimalPageForm getAbandonedAnimalPage(int pageNo);

    String findName(int abNumber);

    public void write(AbandonedAnimalWriteForm abandonedAnimalWriteForm);

    public AbandonedAnimalModifyForm getAbandonedModify(int abNumber);

    public void modify(AbandonedAnimalModifyForm abandonedAnimalModifyForm);

    public String storeFile(MultipartFile file, String filePath) throws IOException;

    public void delete(int abNumber);
}
