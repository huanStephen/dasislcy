package org.eocencle.dasislcy.service.impl;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eocencle.dasislcy.component.PageAdapter;
import org.eocencle.dasislcy.dao.ChapterMapper;
import org.eocencle.dasislcy.dao.OutlineMapper;
import org.eocencle.dasislcy.dao.SubjectMapper;
import org.eocencle.dasislcy.dao.SubjectQuestionMapper;
import org.eocencle.dasislcy.dto.SubjectQuestionRelationDto;
import org.eocencle.dasislcy.entity.ChapterEntity;
import org.eocencle.dasislcy.entity.OutlineEntity;
import org.eocencle.dasislcy.entity.SubjectEntity;
import org.eocencle.dasislcy.entity.SubjectQuestionEntity;
import org.eocencle.dasislcy.service.IChapterService;
import org.eocencle.dasislcy.service.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 科目service
 *
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
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private ChapterMapper chapterMapper;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private OutlineMapper outlineMapper;

    @Autowired
    private IChapterService chapterService;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private SubjectQuestionMapper subjectQuestionMapper;

    private final static String excel2003L = ".xls";    //2003- 版本的excel
    private final static String excel2007U = ".xlsx";   //2007+ 版本的excel

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
        Map<String, Integer> params = new HashMap<String, Integer>();
        params.put("start", (page.getCurrPage() - 1) * page.getPageSize());
        params.put("pageSize", page.getPageSize());

        page.setList(this.subjectMapper.getSubjects(params));

        return page;
    }

    @Override
    public List<SubjectQuestionRelationDto> getSubjectQuestionMappings(Integer questionId) {
        return this.subjectQuestionMapper.getSubjectQuestionMappings(questionId);
    }

    @Override
    @Transactional
    public void importSubjectFile(String filePath) {
        Workbook wb = null;
        try {
            wb = getWorkbook(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Sheet sheet = wb.getSheetAt(0);

        Row row = sheet.getRow(1);
        SubjectEntity subject = new SubjectEntity();
        if (null != row) {
            subject.setName(row.getCell(2).getStringCellValue());
        }
        row = sheet.getRow(2);
        if (null != row) {
            subject.setDescription(row.getCell(2).getStringCellValue());
        }

        this.subjectMapper.insertSelective(subject);

        boolean finish = false, cellB = false, cellC = false, cellD = false;
        String title = "";
        int rowNo = 3;
        Cell cell = null;
        ChapterEntity chapter = null;
        OutlineEntity outline = null;
        int chapterSort = 1, outlineSort = 1;
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
                    chapter = new ChapterEntity();
                    chapter.setSubjectId(subject.getId());
                    chapter.setTitle(row.getCell(2).getStringCellValue());
                    chapter.setSort(chapterSort++);
                } else if ("章节描述".equals(title)) {
                    chapter.setDescription(row.getCell(2).getStringCellValue());
                    this.chapterMapper.insertSelective(chapter);
                } else if ("语法点".equals(title) || "知识点".equals(title)) {
                    if (CellType.BLANK != row.getCell(3).getCellTypeEnum()) {
                        outline = new OutlineEntity();
                        outline.setSubjectId(subject.getId());
                        outline.setChapterId(chapter.getId());
                        outline.setSort(outlineSort++);
                        outline.setTitle(row.getCell(3).getStringCellValue());

                        rowNo ++;
                        row = sheet.getRow(rowNo);
                        outline.setDescription(row.getCell(3).getStringCellValue());

                        this.outlineMapper.insertSelective(outline);
                    }
                }
            } else {
                cellB = true;
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

            rowNo++;
        } while (!finish);
    }

    /**
     * 根据文件后缀，自适应上传文件的版本
     *
     * @param filePath
     * @return
     * @throws Exception
     */
    private Workbook getWorkbook(String filePath) throws Exception {
        Workbook wb = null;
        String fileType = filePath.substring(filePath.lastIndexOf("."), filePath.length());
        InputStream inStr = new FileInputStream(filePath);
        if (excel2003L.equals(fileType)) {
            wb = new HSSFWorkbook(inStr);  //2003-
        } else if (excel2007U.equals(fileType)) {
            wb = new XSSFWorkbook(inStr);  //2007+
        } else {
            throw new Exception("解析的文件格式有误！");
        }
        return wb;
    }
}
