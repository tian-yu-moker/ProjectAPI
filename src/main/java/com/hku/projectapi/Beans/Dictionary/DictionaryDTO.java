package com.hku.projectapi.Beans.Dictionary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DictionaryDTO
{
    private List<CompanyBean> companies;
    private List<QuestionTagBean> questionTagBeans;
}
