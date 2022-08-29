package kh.petmily.controller;

import kh.petmily.domain.adopt.form.AdoptPageForm;
import kh.petmily.domain.adopt.form.TempPageForm;
import kh.petmily.service.AdoptTempService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdoptTempService adoptTempService;

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

    // 입양/임보 관리 메인 페이지
    @GetMapping("/adopt_temp")
    public String adoptTempTotalList() {
        return "/admin/adoptTemp/adoptTempTotalList";
    }

    // 입양/임보 대기 선택 페이지
    @GetMapping("/adopt_temp/wait")
    public String adoptTempWaitList() {
        return "/admin/adoptTemp/adoptTempWaitList";
    }

    // 입양 대기 페이지
    @GetMapping("/adopt_temp/wait/adopt")
    public String waitAdoptDetail(@RequestParam(defaultValue = "1") int pageNo,
                                  @RequestParam(defaultValue = "처리중") String status,
                                  Model model) {

        AdoptPageForm adopt = adoptTempService.getAdoptWaitPage(pageNo, status);
        model.addAttribute("adopt", adopt);

        return "/admin/adoptTemp/waitAdoptDetail";
    }

    // 입양 승인 버튼
    @GetMapping("/adopt_temp/wait/adopt/approve")
    public String adoptApprove(@RequestParam int adNumber) {

        adoptTempService.adoptApprove(adNumber);

        return "/admin/adoptTemp/adoptSuccess";
    }

    // 입양 거절 버튼
    @GetMapping("/adopt_temp/wait/adopt/refuse")
    public String adoptRefuse(@RequestParam int adNumber) {

        adoptTempService.adoptRefuse(adNumber);

        return "/admin/adoptTemp/adoptRefuse";
    }

    // 임보 대기 페이지
    @GetMapping("/adopt_temp/wait/temp_pet")
    public String waitTempDetail(@RequestParam(defaultValue = "1") int pageNo,
                                 @RequestParam(defaultValue = "처리중") String status,
                                    Model model) {

        TempPageForm temp = adoptTempService.getTempWaitPage(pageNo, status);
        model.addAttribute("temp", temp);

        return "/admin/adoptTemp/waitTempPetDetail";
    }

    // 임보 승인 버튼
    @GetMapping("/adopt_temp/wait/temp_pet/approve")
    public String tempApprove(@RequestParam int tNumber) {

        adoptTempService.tempApprove(tNumber);

        return "/admin/adoptTemp/tempSuccess";
    }

    // 임보 거절 버튼
    @GetMapping("/adopt_temp/wait/temp_pet/refuse")
    public String tempRefuse(@RequestParam int tNumber) {

        adoptTempService.tempRefuse(tNumber);

        return "/admin/adoptTemp/tempRefuse";
    }

    // 입양/임보 완료 선택 페이지
    @GetMapping("/adopt_temp/complete")
    public String adoptTempCompleteList() {
        return "/admin/adoptTemp/adoptTempCompleteList";
    }

    // 입양 완료된 리스트
    @GetMapping("/adopt_temp/complete/adopt")
    public String completeAdoptDetail(@RequestParam(defaultValue = "1") int pageNo,
                                      @RequestParam(defaultValue = "완료") String status,
                                      Model model) {

        AdoptPageForm adopt = adoptTempService.getAdoptWaitPage(pageNo, status);
        model.addAttribute("adopt", adopt);

        return "/admin/adoptTemp/completeAdoptDetail";
    }

    // 임보 완료된 리스트
    @GetMapping("/adopt_temp/complete/temp_pet")
    public String completeTempPetDetail(@RequestParam(defaultValue = "1") int pageNo,
                                        @RequestParam(defaultValue = "완료") String status,
                                        Model model) {

        TempPageForm temp = adoptTempService.getTempWaitPage(pageNo, status);
        model.addAttribute("temp", temp);

        return "/admin/adoptTemp/completeTempPetDetail";
    }

    // 입양/임보 거절 선택 페이지
    @GetMapping("/adopt_temp/refuse")
    public String adoptTempRefuseList() {
        return "/admin/adoptTemp/adoptTempRefuseList";
    }

    // 입양 거절된 리스트
    @GetMapping("/adopt_temp/refuse/adopt")
    public String refuseAdoptDetail(@RequestParam(defaultValue = "1") int pageNo,
                                    @RequestParam(defaultValue = "거절") String status,
                                    Model model) {

        AdoptPageForm adopt = adoptTempService.getAdoptWaitPage(pageNo, status);
        model.addAttribute("adopt", adopt);

        return "/admin/adoptTemp/refuseAdoptDetail";
    }

    // 임보 거절된 리스트
   @GetMapping("/adopt_temp/refuse/temp_pet")
    public String refuseTempPetDetail(@RequestParam(defaultValue = "1") int pageNo,
                                      @RequestParam(defaultValue = "거절") String status,
                                      Model model) {

       TempPageForm temp = adoptTempService.getTempWaitPage(pageNo, status);
       model.addAttribute("temp", temp);

        return "/admin/adoptTemp/refuseTempPetDetail";
    }

    @GetMapping("/donation")
    public void donationPage() {

    }

    @GetMapping("/volunteer")
    public void volunteerPage() {

    }
}
