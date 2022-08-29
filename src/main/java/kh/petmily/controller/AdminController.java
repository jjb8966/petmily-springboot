package kh.petmily.controller;

import kh.petmily.domain.abandoned_animal.form.AbandonedAnimalDetailForm;
import kh.petmily.domain.abandoned_animal.form.AbandonedAnimalModifyForm;
import kh.petmily.domain.abandoned_animal.form.AbandonedAnimalPageForm;
import kh.petmily.domain.abandoned_animal.form.AbandonedAnimalWriteForm;
import kh.petmily.service.AbandonedAnimalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.MalformedURLException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
@Slf4j
public class AdminController {

    private final AbandonedAnimalService abandonedAnimalService;

    @ResponseBody
    @GetMapping("/upload")
    public ResponseEntity<Resource> list(String filename, HttpServletRequest request) {

        String fullPath =request.getSession().getServletContext().getRealPath("/");
        fullPath = fullPath+"resources/upload/";
        fullPath = fullPath+filename;

        log.info("fullPath = {} ", fullPath);

        try {
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_PNG)
                    .body(new UrlResource("file:" + fullPath));
        } catch (MalformedURLException e) {
            log.info("fullPath = {} ", fullPath);

            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public String adminPage() {
        return "/admin/adminPage";
    }

    @GetMapping("/member")
    public void memberPage() {

    }

    @GetMapping("/animal")
    public String animalPage() { return "/admin/animal/animalPage"; }

    @GetMapping("/animal/abandoned")
    public String adminAbandonedList(@RequestParam(defaultValue = "1") int pageNo, Model model) {
        AbandonedAnimalPageForm abandonedAnimals = abandonedAnimalService.getAbandonedAnimalPage(pageNo);
        model.addAttribute("abandonedAnimals", abandonedAnimals);

        return "/admin/animal/adminAbandonedList";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam("abNumber") int abNumber, Model model) {
        AbandonedAnimalDetailForm detailForm = abandonedAnimalService.getDetailForm(abNumber);
        log.info("detailForm = {}", detailForm);

        model.addAttribute("detailForm", detailForm);

        return "/abandoned_animal/detailAbandonedAnimal";
    }


    @GetMapping("/animal/abandoned/write")
    public String adminAbandonedWriteForm() {
        return "/admin/animal/AbandonedAnimalWriteForm";
    }

    @PostMapping("/animal/abandoned/write")
    public String adminAbandonedWrite(@ModelAttribute AbandonedAnimalWriteForm abandonedAnimalWriteForm, HttpServletRequest request){
        String fullPath = request.getSession().getServletContext().getRealPath("/");
        fullPath = fullPath + "resources/upload/";

        String filename = "";

        if(!abandonedAnimalWriteForm.getImgPath().isEmpty()) {
            try {
                filename = abandonedAnimalService.storeFile(abandonedAnimalWriteForm.getImgPath(), fullPath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            abandonedAnimalWriteForm.setFullPath(filename);
        } else {
            abandonedAnimalWriteForm.setFullPath("");
        }

        log.info("AbandonedAnimalWriteForm = {}", abandonedAnimalWriteForm);

        abandonedAnimalService.write(abandonedAnimalWriteForm);

        return "/admin/animal/writeSuccess";
    }

    @GetMapping("/animal/abandoned/modify")
    public String adminAbandonedModifyForm(@RequestParam("abNumber") int abNumber, Model model) {
        AbandonedAnimalModifyForm modReq = abandonedAnimalService.getAbandonedModify(abNumber);

        model.addAttribute("modReq", modReq);

        return "/admin/animal/AbandonedAnimalModifyForm";
    }

    @PostMapping("/animal/abandoned/modify")
    public String adminAbandonedModify(@RequestParam("abNumber") int abNumber,
                                       @ModelAttribute AbandonedAnimalModifyForm modReq,
                                       HttpServletRequest request,
                                       Model model,
                                       RedirectAttributes redirectAttributes) {
        String fullPath = request.getSession().getServletContext().getRealPath("/");
        fullPath = fullPath+"resources/upload/";

        modReq.setAbNumber(abNumber);
        String filename = null;

        try {
            filename = abandonedAnimalService.storeFile(modReq.getImgPath(), fullPath);
            modReq.setFullPath(filename);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        log.info("AbandonedAnimalModifyForm = {}", modReq);

        abandonedAnimalService.modify(modReq);
        model.addAttribute("modReq", modReq);
        redirectAttributes.addAttribute("abNumber", abNumber);

        log.info("ModifyForm = {}", modReq);

        return "redirect:/admin/animal/abandoned";
    }

    @GetMapping("/animal/abandoned/delete")
    public String adminAbandonedDelete(@RequestParam("abNumber") int abNumber, RedirectAttributes redirectAttributes) {
        abandonedAnimalService.delete(abNumber);
        redirectAttributes.addAttribute("abNumber", abNumber);

        return "redirect:/admin/animal/abandoned";
    }

    @GetMapping("/board")
    public void boardPage() {

    }

    @GetMapping("/adopt_temp")
    public void adoptTempPage() {

    }

    @GetMapping("/donation")
    public void donationPage() {

    }

    @GetMapping("/volunteer")
    public void volunteerPage() {

    }
}
