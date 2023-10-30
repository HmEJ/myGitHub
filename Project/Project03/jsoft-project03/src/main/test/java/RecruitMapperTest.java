import com.myhd.entity.Recruit;
import com.myhd.mapper.RecruitMapper;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Classname RecruitMapperTest
 * @Description TODO
 * @Date 2023/10/24 下午3:49
 * @Created by joneelmo
 */
@SpringBootTest(classes = com.myhd.JsoftProject03Application.class)
public class RecruitMapperTest {
    @Autowired
    RecruitMapper recruitMapper;
    @Test
    public void getRecruitByCompanyIdTest(){
        val list = recruitMapper.getRecruitByCompanyId(100001);
        for (Recruit recruit : list) {
            if (recruit.getUserId()==null){
                System.out.println("该企业未被申请");
            }else {
                System.out.println("该企业已被申请");
            }
        }
    }

//    @Test
//    public void getRecruitInfo(){
//        System.out.println(recruitMapper.getRecruitInfo(100001, 200002));
//    }

    @Test
    public void getLikeInfo(){
        val list = recruitMapper.getLikeInfo(null);
        for (Recruit recruit : list) {
            System.out.println(recruit);
        }
    }

    @Test
    public void getHighSalary(){
        val list = recruitMapper.getHighSalary();
        for (Recruit recruit : list) {
            System.out.println(recruit);
        }
    }
}
