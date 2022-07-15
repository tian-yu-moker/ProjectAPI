package com.hku.projectapi.Beans.Programming;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProgrammingUploadDTO
{
    // Question id
    private Integer questionId;
    // User uploaded code
    private String codes;
    // What language
    private String language;
}
