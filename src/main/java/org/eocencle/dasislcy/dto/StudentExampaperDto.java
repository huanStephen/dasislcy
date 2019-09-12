package org.eocencle.dasislcy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: Xukai
 * Description:
 * CreateDate: 2019/9/10 22:28
 * Modified By:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentExampaperDto {

    private Integer id;

    private Integer studentId;

    private Integer exampaperId;

    private String examName;

    private String subjectName;
}
