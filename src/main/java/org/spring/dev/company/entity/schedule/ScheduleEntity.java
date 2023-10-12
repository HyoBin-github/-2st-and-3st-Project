package org.spring.dev.company.entity.schedule;


import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.spring.dev.company.dto.schedule.ScheduleDto;
import org.spring.dev.company.entity.util.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "c_personal_table")
public class ScheduleEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDateTime StartDateTime;

    @NotNull
    private LocalDateTime EndDateTime;

    private String Content;

    // 일정 타입에 따라 컬러 지정
    @ColumnDefault("'#0000ff'")
    private String color;

    // 일정 타입
    @ColumnDefault("'NORMAL'")
    private String Type;

    @NotNull
    private Long memberId;
    // 개인 일정 테이블

    public static ScheduleEntity toEntity(ScheduleDto scheduleDto){
        return ScheduleEntity.builder()
                .id(scheduleDto.getId())
                .StartDateTime(scheduleDto.getStartDateTime())
                .EndDateTime(scheduleDto.getEndDateTime())
                .Content(scheduleDto.getContent())
                .color(scheduleDto.getColor())
                .Type(scheduleDto.getType())
                .memberId(scheduleDto.getMemberId())
                .build();
    }

}
