package com.prac.almond.app.system;

import com.prac.almond.app.product.model.Product;
import com.prac.almond.app.product.model.ProductDto;
import com.prac.almond.app.system.model.Sys;
import com.prac.almond.app.system.model.SysDto;
import com.prac.almond.app.system.model.SysPaging;
import com.prac.almond.app.system.model.SysParam;
import com.prac.almond.app.system.service.SysService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class SysController {
    private final SysService sysService;

    @GetMapping("/system")
    public ModelAndView systemList() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("system/systemList");
        return mav;
    }

    @GetMapping("/system/systemList")
    @ResponseBody
    public Object systemListData(SysParam sysParam) {
        SysPaging result = sysService.findSystemPaging(sysParam);
        return result;
    }

    @GetMapping("/system/insert")
    public ModelAndView insertSystem() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("system/registerSystem");
        return mav;
    }

    @PostMapping("/system/save")
    @ResponseBody
    public String saveSystemData(SysDto sysDto) {
        String result = sysService.saveSystemData(sysDto);
        return result;

    }
    @GetMapping("/system/systemInfo/{sysSeq}")
    public ModelAndView systemInfo(@PathVariable("sysSeq") int sysSeq) {
        ModelAndView modelAndView = new ModelAndView();
        Sys systemInfo = sysService.findSystemInfo(sysSeq);
        modelAndView.addObject("systemInfo", systemInfo);
        modelAndView.setViewName("system/systemInfo");
        return modelAndView;
    }
    @PutMapping("/system/update")
    @ResponseBody
    public String updateSystemData(SysDto sysDto) {
        String updateSystemData = sysService.updateSystemData(sysDto);
        System.out.println(updateSystemData);
        return updateSystemData;
    }
    @DeleteMapping("/system/delete")
    @ResponseBody
    public String deleteSystemData(int sysSeq) {
        String deleteSystemData = sysService.deleteSystemData(sysSeq);
        System.out.println("delete");
        return deleteSystemData;
    }

}
