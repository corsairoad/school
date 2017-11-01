import com.fm.school.config.RootConfig;
import com.fm.school.config.WebConfig;
import com.fm.school.service.StudentDao;
import com.fm.school.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by fadlymunandar on 10/18/17.
 */

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class, RootConfig.class})
public class JdbcTemplateTest {


    @Autowired
    StudentService studentService;

    @Test
    public void addStudentTest() {
        assertNotNull(studentService);
    }
}
