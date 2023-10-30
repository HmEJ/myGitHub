import com.myhd.mapper.RecruitMapper;
import com.myhd.service.IRecruitService;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @Classname RecruitServiceImplTest
 * @Description TODO
 * @Date 2023/10/26 上午10:35
 * @Created by joneelmo
 */
@SpringBootTest(classes = com.myhd.JsoftProject03Application.class)
public class RecruitServiceImplTest {
    @Resource
    IRecruitService recruitService;


    @Test
    void acquireHighSalaryList(){
        val result = recruitService.acquireHighSalaryList();
        System.out.println(result);
    }

//    @Test
//    void searchRecruit(){
//        val result = recruitService.searchRecruit(true, 5);
//        System.out.println(result);
//    }

    @Test
    void acquireRecruitByCompanyId(){
//        val result = recruitService.acquireRecruitByCompanyId(101);
//        System.out.println(result);
    }
    @Test
    void acquireRecruitInfo(){
//        val result = recruitService.acquireRecruitInfo(101, 200002);
//        System.out.println(result);
    }

}
