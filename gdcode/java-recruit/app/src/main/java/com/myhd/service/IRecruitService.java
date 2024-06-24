package com.myhd.service;

import com.myhd.util.Result;


/**
 * <p>
 * 招聘职位表 服务类
 * </p>
 *
 * @author Jinhui-Huang
 * @since 2023-10-23
 */
public interface IRecruitService {


    /**
     * @description 顶部搜索框查询功能
     * @author JoneElmo
     * @date 2023-10-26 09:25
     * @param useQuickSearch 是否启用快捷查询。true表示使用快捷查询
     * @param args 查询的参数
     * @return
     */
    Result searchRecruit(Boolean useQuickSearch,Object args,Integer pageNum);

    /**
     * Description: getFields 服务层中获取redis中存放的相关领域
     * @return com.myhd.util.Result
     * @author jinhui-huang
     * @Date 2023/10/26
     * */
    Result getFields();
    /**
     * @description 高薪职位查询
     * 需要同企业表连接，获取公司logo
     * 根据薪资max值降序排列
     * 使用mybatis resultmap来映射这个图标字段
     * @author JoneElmo && CYQH
     * @date 2023-10-24 10:53
     * @return Recruit
     */
    Result acquireHighSalaryList();

    /**
     * @description
     * 企业详情页面查询该企业下所有招聘岗位功能
     * 需要分页
     * 根据企业ID查询招聘信息
     * 通过和申请表外连接来查询 判断是否被申请 （通过user_id来判断）
     * @author JoneElmo && CYQH
     * @date 2023-10-24 10:07
     * @param companyId 企业编号
     * @return RECRUIT 招聘信息
     */
    Result acquireRecruitByCompanyId(Integer companyId,Integer userId);

    /**
     * @description 职位信息页面 展示相关信息功能
     * 通过和申请表外连接判断是否已经被申请 （通过user_id来判断）
     * 如果user_id为空 则表示该岗位没有被申请 否则表示岗位被申请
     * 示例：
     * select r.*,a.* from tb_recruit r
     * left join tb_apply a
     * on r.id = a.recruit_id
     * @author JoneElmo && CYQH
     * @date 2023-10-24 10:27
     * @param
     * @return
     */
    Result acquireRecruitInfo(Integer companyId,Integer recruitId,Integer userId);

    /**
     * @description 模糊查询
     * 查询所有岗位信息  需要分页 pageInfo
     * 和申请表外连接 判断是否被申请
     * @author JoneElmo && CYQH
     * @date 2023-10-24 10:39
     * @param like 模糊查询的参数  招聘的职位关键字(recruit_name)
     * @return
     */
    Result getLikeInfo(String like,Integer pageNum);

}
