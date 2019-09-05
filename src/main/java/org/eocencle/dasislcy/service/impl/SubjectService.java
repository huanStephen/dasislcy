package org.eocencle.dasislcy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eocencle.dasislcy.component.PageAdapter;
import org.eocencle.dasislcy.dao.SubjectMapper;
import org.eocencle.dasislcy.dao.SubjectQuestionMapper;
import org.eocencle.dasislcy.dto.SubjectQuestionRelationDto;
import org.eocencle.dasislcy.entity.SubjectEntity;
import org.eocencle.dasislcy.entity.SubjectQuestionEntity;
import org.eocencle.dasislcy.service.IChapterService;
import org.eocencle.dasislcy.service.IOutlineService;
import org.eocencle.dasislcy.service.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * 科目service
 * @Auther: huanStephen
 * @Date: 2019/4/28
 * @Description:
 */
@Service
public class SubjectService implements ISubjectService {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private SubjectMapper subjectMapper;

    @Autowired
    private IChapterService chapterService;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private SubjectQuestionMapper subjectQuestionMapper;

    private final static String excel2003L =".xls";    //2003- 版本的excel
    private final static String excel2007U =".xlsx";   //2007+ 版本的excel

    @Override
    public SubjectEntity getSubjectById(Integer id) {
        return this.subjectMapper.selectByPrimaryKey(id);
    }

    @Override
    public void addSubject(SubjectEntity subject) {
        this.subjectMapper.insertSelective(subject);
    }

    @Override
    public void addSubjectQuestionMapping(SubjectQuestionEntity mapping) {
        this.subjectQuestionMapper.insertSelective(mapping);
    }

    @Override
    @Transactional
    public void removeSubjectById(Integer id) {
        // 删除科目
        this.subjectMapper.deleteByPrimaryKey(id);
        // 删除章节
        this.chapterService.removeChapterBySubjectId(id);
    }

    @Override
    public void removeSubjectQuestionMapping(Integer mappingId) {
        SubjectQuestionEntity mapping = this.subjectQuestionMapper.selectByPrimaryKey(mappingId);
        SubjectQuestionEntity search = new SubjectQuestionEntity();
        search.setQuestionId(mapping.getQuestionId());
        List<SubjectQuestionEntity> mappingList = this.subjectQuestionMapper.select(search);
        // 判断映射是否只有一条，如果是一条则更新为“未分类”，如果多条则删除
        if (1 == mappingList.size()) {
            mapping.setSubjectId(0);
            mapping.setChapterId(null);
            mapping.setOutlineId(null);
            mapping.setUpdateTime(null);
            mapping.setWeight(0);
            this.subjectQuestionMapper.updateByPrimaryKey(mapping);
        } else {
            this.subjectQuestionMapper.deleteByPrimaryKey(mappingId);
        }
    }

    @Override
    public void updateSubject(SubjectEntity subject) {
        this.subjectMapper.updateByPrimaryKeySelective(subject);
    }

    @Override
    public void updateSubjectQuestionMapping(SubjectQuestionEntity mapping) {
        this.subjectQuestionMapper.updateByPrimaryKeySelective(mapping);
    }

    @Override
    public PageAdapter<SubjectEntity> getSubjects(PageAdapter<SubjectEntity> page) {
        PageHelper.startPage(page.getCurrPage(), page.getPageSize());
        List<SubjectEntity> list = this.subjectMapper.selectAll();
        PageInfo<SubjectEntity> info = new PageInfo<SubjectEntity>(list);
        page.setList(list);
        page.setTotal(new Long(info.getTotal()).intValue());

        return page;
    }

    @Override
    public List<SubjectQuestionRelationDto> getSubjectQuestionMappings(Integer questionId) {
        return this.subjectQuestionMapper.getSubjectQuestionMappings(questionId);
    }

    @Override
    public void importSubjectFile(String filePath) {
        Workbook wb = null;
        try {
            wb = getWorkbook(new FileInputStream(filePath), "subject.xlsx");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Sheet sheet = wb.getSheetAt(0);

        Row row = sheet.getRow(2);
        if (null != row) {
            System.out.println("科目名称：" + row.getCell(2).getStringCellValue());
        }
        row = sheet.getRow(3);
        if (null != row) {
            System.out.println("科目描述：" + row.getCell(2).getStringCellValue());
        }

        boolean finish = false, cellB = false, cellC = false, cellD = false;
        String title = "";
        int rowNo = 6;
        Cell cell = null;
        do {
            cellB = false;
            cellC = false;
            cellD = false;
            row = sheet.getRow(rowNo);
            if (null != row) {
                cell = row.getCell(1);
                // B2为空的情况
                if (cell.getCellTypeEnum() == CellType.BLANK) {
                    cellB = true;
                } else {
                    title = cell.getStringCellValue();
                }

                if ("章节名称".equals(title)) {
                    System.out.println("章节名称：" + row.getCell(2).getStringCellValue());
                } else if ("章节描述".equals(title)) {
                    System.out.println("章节描述：" + row.getCell(2).getStringCellValue());
                } else if ("语法点".equals(title)) {
                    System.out.println("大纲：" + row.getCell(3).getStringCellValue());
                } else if ("知识点".equals(title)) {
                    System.out.println("大纲：" + row.getCell(3).getStringCellValue());
                }
            }

            if (null == row) {
                cellC = true;
                cellD = true;
            } else {
                if (row.getCell(2).getCellTypeEnum() == CellType.BLANK) {
                    cellC = true;
                }

                if (row.getCell(3).getCellTypeEnum() == CellType.BLANK) {
                    cellD = true;
                }
            }

            finish = cellB && cellC && cellD;

            rowNo ++;
        } while (!finish);
    }

    /**
     * 描述：根据文件后缀，自适应上传文件的版本
     * @param inStr 将file.getInputStream()获取的输入流
     * @param fileName file.getOriginalFilename()获取的原文件名
     */
    private Workbook getWorkbook(InputStream inStr, String fileName) throws Exception{
        Workbook wb = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if(excel2003L.equals(fileType)){
            wb = new HSSFWorkbook(inStr);  //2003-
        }else if(excel2007U.equals(fileType)){
            wb = new XSSFWorkbook(inStr);  //2007+
        }else{
            throw new Exception("解析的文件格式有误！");
        }
        return wb;
    }
}
