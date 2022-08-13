package kh.petmily.controller;

import kh.petmily.domain.abandoned_animal.form.AbandonedAnimalDetailForm;
import kh.petmily.domain.abandoned_animal.form.AbandonedAnimalPageForm;
import kh.petmily.domain.abandoned_animal.form.AdoptTempSubmitForm;
import kh.petmily.domain.abandoned_animal.form.DonateSubmitForm;
import kh.petmily.domain.abandoned_animal.form.VolunteerApplySubmitForm;
import kh.petmily.domain.member.Member;
import kh.petmily.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/abandoned_animal")
@RequiredArgsConstructor
@Slf4j
public class AbandonedAnimalController {

    private final AbandonedAnimalService abandonedAnimalService;
    private final AdoptTempService adoptTempService;
    private final DonateService donateService;
    private final MemberService memberService;
    private final VolunteerService volunteerService;

    @GetMapping("/list")
    public String list(HttpServletRequest request, Model model) {
        String pageNoVal = request.getParameter("pageNo");
        int pageNo = 1;

        if (pageNoVal != null) {
            pageNo = Integer.parseInt(pageNoVal);
        }

        AbandonedAnimalPageForm abandonedAnimals = abandonedAnimalService.getAbandonedAnimalPage(pageNo);
        model.addAttribute("abandonedAnimals", abandonedAnimals);

        return "/abandoned_animal/listAbandonedAnimal";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam("abNumber") int abNumber, Model model) {
        AbandonedAnimalDetailForm detailForm = abandonedAnimalService.getDetailForm(abNumber);
        log.info("detailForm = {}", detailForm);

        model.addAttribute("detailForm", detailForm);

        return "/abandoned_animal/detailAbandonedAnimal";
    }

    //=======후원하기=======
    @GetMapping("/auth/donate")
    public String donateForm(@RequestParam("abNumber") int abNumber, HttpServletRequest request, Model model) {

        Member member = getAuthMember(request);

        int mNumber = member.getMNumber();
        String animalName = donateService.findAnimalName(abNumber);
        String memberName = donateService.findMemberName(mNumber);

        if (animalName != null) {
            model.addAttribute("animalName", animalName);
        }

        if (memberName != null) {
            model.addAttribute("memberName", memberName);
        }

        return "/abandoned_animal/donateSubmitForm";
    }

    @PostMapping("/auth/donate")
    public String donate(@ModelAttribute DonateSubmitForm donateSubmitForm, HttpServletRequest request) {

        log.info("donateSubmitForm = {}", donateSubmitForm);

        Member member = getAuthMember(request);

        int mNumber = member.getMNumber();

        donateSubmitForm.setMNumber(mNumber);
        donateService.donate(donateSubmitForm);

        return "/abandoned_animal/submitSuccess";
    }

    //=======입양/임보하기=======
    @GetMapping("/auth/adopt_temp")
    public String adoptTempForm(@RequestParam int abNumber, HttpServletRequest request) {

        Member member = getAuthMember(request);
        int mNumber = member.getMNumber();

        String animalName = abandonedAnimalService.findName(abNumber);
        String memberName = memberService.findName(mNumber);

        if (animalName != null) {
            request.setAttribute("animalName", animalName);
        }

        if (memberName != null) {
            request.setAttribute("memberName", memberName);
        }

        return "/abandoned_animal/adoptTempSubmitForm";
    }

    @PostMapping("/auth/adopt_temp")
    public String adoptTemp(@ModelAttribute AdoptTempSubmitForm form,
                            @RequestParam String adoptOrTemp,
                            HttpServletRequest request,
                            RedirectAttributes redirectAttributes) {

        log.info("adoptTempSubmitForm = {}", form);

        Member member = getAuthMember(request);
        int mNumber = member.getMNumber();

        form.setMNumber(mNumber);

        if (adoptOrTemp.equals("adopt")) {
            adoptTempService.adopt(form);
        }

        if (adoptOrTemp.equals("temp")) {
            adoptTempService.tempProtect(form);
        }

        redirectAttributes.addAttribute("abNumber", form.getAbNumber());

        return "/abandoned_animal/submitSuccess";
    }

    //=======봉사하기=======
    @GetMapping("/auth/volunteer")
    public String volunteerForm(@RequestParam("abNumber") int abNumber, HttpServletRequest request, Model model) {
        Member member = getAuthMember(request);
        int mNumber = member.getMNumber();

        String animalName = volunteerService.findAnimalName(abNumber);
        String memberName = volunteerService.findMemberName(mNumber);
        String memberBirth = volunteerService.findMemberBirth(mNumber);
        String memberPhone = volunteerService.findMemberPhone(mNumber);
        String memberEmail = volunteerService.findMemberEmail(mNumber);

        if (animalName != null) {
            model.addAttribute("animalName", animalName);
        }

        if (memberName != null) {
            model.addAttribute("memberName", memberName);
        }

        if (memberBirth != null) {
            model.addAttribute("memberBirth", memberBirth);
        }

        if (memberPhone != null) {
            model.addAttribute("memberPhone", memberPhone);
        }

        if (memberEmail != null) {
            model.addAttribute("memberEmail", memberEmail);
        }

        return "/abandoned_animal/volunteerAbandonedAnimal";
    }

    @PostMapping("/auth/volunteer")
    private String volunteer(@RequestParam("abNumber") int abNumber, @ModelAttribute VolunteerApplySubmitForm volunteerApplySubmitForm, HttpServletRequest request) {
        log.info("volunteerApplySubmitForm = {}", volunteerApplySubmitForm);

        Member member = getAuthMember(request);
        int mNumber = member.getMNumber();

        int sNumber = volunteerService.findsNumber(abNumber);

        volunteerApplySubmitForm.setMNumber(mNumber);
        volunteerApplySubmitForm.setSNumber(sNumber);

        volunteerService.volunteer(volunteerApplySubmitForm);

        return "/abandoned_animal/submitSuccess";
    }

    private static Member getAuthMember(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Member member = (Member) session.getAttribute("authUser");
        return member;
    }
}
