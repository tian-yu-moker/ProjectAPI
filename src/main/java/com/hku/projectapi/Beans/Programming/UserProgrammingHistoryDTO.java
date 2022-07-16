package com.hku.projectapi.Beans.Programming;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProgrammingHistoryDTO
{
    private String userId;
    private String userName;
    private int questionId;
    private List<ProgrammingHistoryBean> historyRecord;
}
