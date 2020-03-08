package com.online.edu.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.online.edu.eduservice.entity.EduSubject;
import com.online.edu.eduservice.entity.SubjectNestedVo;
import com.online.edu.eduservice.entity.SubjectVo;
import com.online.edu.eduservice.mapper.EduSubjectMapper;
import com.online.edu.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-02-02
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService{

    //读取excel内容 添加分类表里面
    @Override
    public List<String> importSubject(MultipartFile file) {
        List<String> list = new ArrayList<>();
        try {
            //1.获取文件输入流
            InputStream in = file.getInputStream();
            // 2。创建workbook
            Workbook workbook = new HSSFWorkbook(in);
            //3.workbook获取sheet
            Sheet sheet = workbook.getSheetAt(0);

            //存储错误信息


            //4。sheet获取row
            //循环开始遍历多少行
            int lastRowNum = sheet.getLastRowNum();
            for(int i=1;i<=lastRowNum;i++){
                Row row = sheet.getRow(i);
                //行数据不为空
                Cell cellone = row.getCell(0); //获取第一列
                if(cellone==null){
                    String str = "第一列为空";
                    list.add(str);
                    return list;
                }
                String cellOneValue = cellone.getStringCellValue();  //第一列的值
                if(cellOneValue==null){
                    String str = "第"+i+"行数据为空";
                    list.add(str);
                    continue;
                }

                //添加一级分类
                //因为很多Excel第一类要去重
                EduSubject eduSubject1 = this.existOneSubject(cellOneValue);

                Long id_parent;
                //添加
                if(eduSubject1==null){
                    EduSubject eduSubject = new EduSubject();
                    eduSubject.setTitle(cellOneValue);
                    eduSubject.setParentId(0l);
                    eduSubject.setSort(0);
                    baseMapper.insert(eduSubject);

                    id_parent = eduSubject.getId();
                }else{
                    //存在就不添加了
                    System.out.println(eduSubject1.getTitle()+"已存在");
                    //把一级Id的值赋值给他
                    id_parent=eduSubject1.getId();
                }

                //获取第二列的值
                Cell cellTwo = row.getCell(1);
                if(cellTwo==null){
                    String str = "第"+i+"行数据为空";
                    list.add(str);
                    continue;
                }
                //从第二行开始获取数据
                //5。row获取cell
                String cellTwoValue = cellTwo.getStringCellValue();  //第而列的值

                //判断数据库中存在二级分类
                EduSubject eduSubject = this.existTwoSubject(cellTwoValue, id_parent);
                if(eduSubject==null){
                   EduSubject eduSubject2 = new EduSubject();
                   eduSubject2.setTitle(cellTwoValue);
                   eduSubject2.setSort(0);
                   eduSubject2.setParentId(id_parent);
                   baseMapper.insert(eduSubject2);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
             return  list;

    }

    //最终实现 展示列表
    @Override
    public List<SubjectNestedVo> nestedList() {
        //最终要展示的数据列表
        ArrayList<SubjectNestedVo> subjectNestedVoArrayList = new ArrayList<>();
        //获取一级分类数据记录
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", 0);
        queryWrapper.orderByAsc("sort", "id");
        List<EduSubject> subjects = baseMapper.selectList(queryWrapper);
        //获取二级分类数据记录
        QueryWrapper<EduSubject> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.ne("parent_id", 0);
        queryWrapper2.orderByAsc("sort", "id");
        List<EduSubject> subSubjects = baseMapper.selectList(queryWrapper2);
        //填充一级分类vo数据
        int count = subjects.size();
        for (int i = 0; i < count; i++) {
            EduSubject subject = subjects.get(i);
            //创建一级类别vo对象
            SubjectNestedVo subjectNestedVo = new SubjectNestedVo();
            BeanUtils.copyProperties(subject, subjectNestedVo);
            subjectNestedVoArrayList.add(subjectNestedVo);
            //填充二级分类vo数据
            ArrayList<SubjectVo> subjectVoArrayList = new ArrayList<>();
            int count2 = subSubjects.size();
            for (int j = 0; j < count2; j++) {
                EduSubject subSubject = subSubjects.get(j);
                if(subject.getId().equals(subSubject.getParentId())){
                    //创建二级类别vo对象
                    SubjectVo subjectVo = new SubjectVo();
                    BeanUtils.copyProperties(subSubject, subjectVo);
                    subjectVoArrayList.add(subjectVo);
                    System.out.println("1111");
                }
            }
            subjectNestedVo.setChildren(subjectVoArrayList);

    }
        return subjectNestedVoArrayList; 
    }

    @Override
    public boolean deleteSubjectById(Long id) {
         //判断一级分类下面是否有二级分类
        //根据parent_id判断
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",id);
        Integer count = baseMapper.selectCount(wrapper);
        //判断是否有二级分类
        if(count>0){
            return false; //有二级分类
        }else{
            int i = baseMapper.deleteById(id);
            return i>0;
        }
    }

    //添加一级分类
    @Override
    public boolean saveOneLevel(EduSubject eduSubject) {
        //判断一级分类是否存在
        EduSubject eduSubject1 = this.existOneSubject(eduSubject.getTitle());
        if(eduSubject1==null){
            eduSubject.setParentId(0L);
            int result = baseMapper.insert(eduSubject);
            return result>0;
        }else{
            return false;
        }
    }

    //添加是否有二级
    @Override
    public boolean saveTwoLevel(EduSubject eduSubject) {
        //判断二级分类是否存在
        EduSubject eduSubject1 = this.existTwoSubject(eduSubject.getTitle(), eduSubject.getParentId());
        if(eduSubject1==null){ //如果不存在
            int insert = baseMapper.insert(eduSubject);
            return insert>0;
        }
        return false;
    }

    //判断数据库中是否存在二级分类
    private EduSubject existTwoSubject(String name,Long parentId){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        //拼接条件
        wrapper.eq("title",name);
        wrapper.eq("parent_id",parentId);
        //调用方法
        EduSubject eduSubject = baseMapper.selectOne(wrapper);
        return eduSubject;
    }


    //判断数据库表中是否存在一级分裂
    private EduSubject existOneSubject(String name){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        //拼接条件
        wrapper.eq("title",name);
        wrapper.eq("sort",0);
        //调用方法
        EduSubject eduSubject = baseMapper.selectOne(wrapper);
        return  eduSubject;
    }


}
