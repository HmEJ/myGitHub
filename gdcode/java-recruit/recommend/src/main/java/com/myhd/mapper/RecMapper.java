package com.myhd.mapper;

import com.myhd.dto.InitRecDTO;
import com.myhd.dto.RecReturnDTO;
import com.myhd.entity.Company;
import com.myhd.entity.InitRec;
import com.myhd.entity.Recruit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Classname RecMapper
 * @Description
 * @Date 2024/4/5 下午11:06
 * @Created by HmEJ
 */
@Mapper
public interface RecMapper {

    /**
     * 根据recruit_id列表查询招聘信息
     * @param ids
     * @return
     */
    List<RecReturnDTO> getRecruitInfoByIdList(List<Long> ids);


    /**
     * 获取初始化推荐结果
     * @param initRecDTO
     * @return
     */
    List<RecReturnDTO> getInitRec(InitRecDTO initRecDTO);

    /**
     * 保存初始化推荐信息 (标签推荐)
     * @param initRec
     * @return
     */
    int saveInit(InitRec initRec);

    /**
     * 获取用户申请的职位
     * @param userId
     * @return
     */
    List<Integer> getList(Integer userId);

    /**
     * 获取用户关注的公司列表
     * @param userId
     * @return
     */
    List<Integer> getFollowList(Integer userId);

    /**
     * 根据企业id列表查询招聘信息id
     * @param companyIdList
     * @return
     */
    List<Integer> getRecruitByCompanyIdList(List<Integer> companyIdList);

    int insertRating(Integer id, Integer k, Double rating, Long timeStamp);
}
