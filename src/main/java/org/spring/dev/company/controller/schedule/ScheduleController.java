package org.spring.dev.company.controller.schedule;

import lombok.AllArgsConstructor;
import org.spring.dev.company.dto.schedule.ScheduleDto;
import org.spring.dev.company.service.schedule.ScheduleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("schedule")
@AllArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping("/{memberId}/index")
    public String index(
            @PathVariable(name = "memberId") Long memberId) {
        return "schedule/schedule";
    }



    /*
        멤버 아이디를 필수로 개인 스퀘줄 정보를 가져옴
        검색 조건에 따라 가져올수있게 설정
     */
    @GetMapping("/{memberId}")
    @ResponseBody
    public List<ScheduleDto> getScheduleDetail(ScheduleDto scheduleDto,
            @PathVariable(name = "memberId") Long memberId)
    {

        List<ScheduleDto> scheduleDtoList = scheduleService.getScheduleDetail(scheduleDto);

        return scheduleDtoList;
    }


    // C U D 이 컨트롤러 통해서 함
    @PostMapping("/{memberId}")
    @ResponseBody
    public ScheduleDto postSchedule(
            @PathVariable(name = "memberId") Long memberId,
            @RequestBody ScheduleDto scheduleDto)
    {
        ScheduleDto result = scheduleService.postSchedule(scheduleDto);
        return scheduleDto;
    }

}
