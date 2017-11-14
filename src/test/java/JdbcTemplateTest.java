import com.fm.school.config.RootConfig;
import com.fm.school.config.WebConfig;
import com.fm.school.domain.Student;
import com.fm.school.service.StudentDao;
import com.fm.school.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created by fadlymunandar on 10/18/17.
 */

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class, RootConfig.class})
public class JdbcTemplateTest {


    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    StudentService studentService;

    @Test
    public void addStudentTest() {
        assertNotNull(studentService);
    }

    @Test
    public void jdbcNotNullTest() {
        assertNotNull(jdbcTemplate);
    }

    @Test
    public void usersTest() {
        //jdbcTemplate.update("insert into users(username, password, enabled) values ('fadly','fadly', 1)", new MapSqlParameterSource());
        String username = jdbcTemplate.query("select username from users where username='fadly'",
                new ResultSetExtractor<String>() {
                    public String extractData(ResultSet rs) throws SQLException, DataAccessException {
                        if (rs.next()) {
                            return rs.getString("username");
                        }

                    return null;
            }
        });
        assertEquals("the username: ", "fadly", username);
    }

    @Test
    public void connectToOracleDbTest() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@server1.digikom.com:1541:xe", "admin", "admin");
            assertNotNull("Connection to oracle db successfull", connection);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
