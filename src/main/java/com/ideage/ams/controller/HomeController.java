package com.ideage.ams.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @GetMapping("/attendancelist")
    public String showAttendancelist(Model model) {
        return "worktime/workingTimeList :: workingTime";
    }

    @GetMapping("/attendanceDetail")
    public String showAttendanceDetail(Model model) {
        // 以下ダミーデータ後でDBから取得必要
        List<Integer> holidays = new ArrayList<>();
        holidays.add(3);
        holidays.add(6);

        model.addAttribute("holidays", holidays);

        // 基本情報
        model.addAttribute("currentYear", 2024);
        model.addAttribute("currentMonth", 5);
        
        return "worktime/workingTimeDetail :: workingTimeDetail";
    }

    @GetMapping("/default")
    public String showdefault() {
        return "layout/default :: default";
    }
}
