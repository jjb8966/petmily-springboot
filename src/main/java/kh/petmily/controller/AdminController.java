package kh.petmily.controller;

import kh.petmily.domain.abandoned_animal.AbandonedAnimal;
import kh.petmily.domain.donation.form.DonationCreateForm;
import kh.petmily.domain.donation.form.DonationDetailForm;
import kh.petmily.domain.donation.form.DonationModifyForm;
import kh.petmily.domain.donation.form.DonationPageForm;
import kh.petmily.domain.member.Member;
import kh.petmily.service.AbandonedAnimalService;
import kh.petmily.service.DonateService;
import kh.petmily.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
@Slf4j
public class AdminController {

    private final DonateService donateService;
    private final AbandonedAnimalService abandonedAnimalService;
    private final MemberService memberService;

    @GetMapping
    public String adminPage() {
        return "/admin/adminPage";
    }

    @GetMapping("/member")
    public void memberPage() {

    }

    @GetMapping("/animal")
    public void animalPage() {

    }

    @GetMapping("/board")
    public void boardPage() {

    }

    @GetMapping("/adopt_temp")
    public void adoptTempPage() {

    }

    @GetMapping("/donation")
    public String donationPage(@RequestParam(defaultValue = "1") int pageNo, Model model) {
        DonationPageForm donationPageForm = donateService.getDonationPage(pageNo);
        model.addAttribute("donationPageForm", donationPageForm);

        return "/admin/donation/donationList";
    }

    @GetMapping("/donation/detail")
    public String donationDetail(@RequestParam("dNumber") int dNumber,
                                 @RequestParam("abNumber") int abNumber,
                                 @RequestParam("mNumber") int mNumber,
                                 HttpServletRequest request,
                                 Model model) {
        String animalName = abandonedAnimalService.findName(abNumber);
        String memberName = memberService.findName(mNumber);

        if (animalName != null) {
            request.setAttribute("animalName", animalName);
        }

        if (memberName != null) {
            request.setAttribute("memberName", memberName);
        }

        DonationDetailForm donationDetailForm = donateService.getDonation(dNumber);

        model.addAttribute("donationDetail", donationDetailForm);

        return "/admin/donation/donationDetail";
    }

    @GetMapping("donation/create")
    public String donationCreateForm(Model model) {
        List<AbandonedAnimal> abandonedAnimals = abandonedAnimalService.selectAll();
        List<Member> members = memberService.selectAll();

        model.addAttribute("abandonedAnimals", abandonedAnimals);
        model.addAttribute("members", members);

        return "/admin/donation/createDonation";
    }

    @PostMapping("donation/create")
    public String donationCreate(@ModelAttribute DonationCreateForm donationCreateForm, Model model) {
        donateService.create(donationCreateForm);

        return "/admin/donation/createDonationSuccess";
    }

    @GetMapping("donation/modify")
    public String donationModifyForm(@RequestParam("dNumber") int dNumber, Model model) {
        List<AbandonedAnimal> abandonedAnimals = abandonedAnimalService.selectAll();
        List<Member> members = memberService.selectAll();

        DonationModifyForm donationModifyForm = donateService.getDonationModify(dNumber);

        donationModifyForm.setDNumber(dNumber);
        log.info("dNumber = {}", dNumber);

        model.addAttribute("abandonedAnimals", abandonedAnimals);
        model.addAttribute("members", members);
        model.addAttribute("donationModify", donationModifyForm);

        return "/admin/donation/modifyDonation";
    }

    @PostMapping("donation/modify")
    public String donationModify(@RequestParam("dNumber") int dNumber,
                                 @RequestParam("abNumber") int abNumber,
                                 @RequestParam("mNumber") int mNumber,
                                 @ModelAttribute DonationModifyForm donationModifyForm, Model model, RedirectAttributes redirectAttributes) {
        donationModifyForm.setDNumber(dNumber);
        log.info("dNumber = {}", dNumber);

        donateService.modify(donationModifyForm);

        model.addAttribute("donationModify", donationModifyForm);

        redirectAttributes.addAttribute("dNumber", dNumber);
        redirectAttributes.addAttribute("abNumber", abNumber);
        redirectAttributes.addAttribute("mNumber", mNumber);

        return "redirect:/admin/donation/detail?dNumber={dNumber}&abNumber={abNumber}&mNumber={mNumber}";
    }

    @GetMapping("/donation/delete")
    public String delete(@RequestParam("dNumber") int dNumber) {
        donateService.delete(dNumber);

        return "/admin/donation/deleteDonationSuccess";
    }

    @GetMapping("/volunteer")
    public void volunteerPage() {

    }
}
