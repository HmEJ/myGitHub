import com.myhd.entity.Resume;
import com.myhd.service.IResumeService;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @Classname ResumeServiceImplTest
 * @Description TODO
 * @Date 2023/10/26 上午10:21
 * @Created by joneelmo
 */
@SpringBootTest(classes = com.myhd.JsoftProject03Application.class)
public class ResumeServiceImplTest {
    @Resource
    IResumeService resumeService;

    @Test
    void acquireResumeInfo(){
        System.out.println(resumeService.acquireResumeInfo(1000));
    }

    @Test
    void saveResumeInfo(){
        val resume = new Resume();
        resume.setResumeId(88888);
        System.out.println(resumeService.saveResumeInfo(resume));
    }
}
