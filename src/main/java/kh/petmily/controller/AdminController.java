package kh.petmily.controller;

import kh.petmily.domain.pet.Pet;
import kh.petmily.domain.pet.form.PetForm;
import kh.petmily.domain.pet.form.PetPageForm;
import kh.petmily.service.AbandonedAnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AbandonedAnimalService abandonedAnimalService;

    @GetMapping
    public String adminPage() {
        return "/admin/adminPage";
    }

    @GetMapping("/member")
    public void memberPage() {

    }

    @GetMapping("/animal")
    public String animalPage() {
        return "/admin/animal/animalPage";
    }

    @GetMapping("/board")
    public void boardPage() {

    }

    @GetMapping("/animal/pet")
    public String petBoard(@RequestParam(defaultValue = "1") int pageNum, Model model) {
        PetPageForm petPage = abandonedAnimalService.getPetPage(pageNum);
        model.addAttribute("petPage", petPage);
        return "/admin/petBoard";
    }

    @ResponseBody
    @PostMapping("/animal/pet")
    public String petBoard(PetForm pet) {
        try {
            abandonedAnimalService.savePet(new Pet(pet));
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            return "없는 회원 아이디거나 등록할수없는 종입니다.";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "성공";
    }

    @ResponseBody
    @PostMapping("/animal/pet/update")
    public String petUpdate(PetForm pet) {
        try {
            abandonedAnimalService.modifyPet(new Pet(pet));
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            return "등록할수없는 종입니다.";
        } catch (UncategorizedSQLException e) {
            e.printStackTrace();
            return "없는 회원아이디입니다.";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "성공";
    }

    @GetMapping("/animal/pet/delete/{cpNumber}")
    public String animalDelete(@PathVariable int cpNumber) {
        abandonedAnimalService.deletePet(cpNumber);
        return "redirect:/admin/animal/pet";
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
