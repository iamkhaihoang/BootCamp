import com.example.project_app;
import org.junit.Test;
import static org.junit.Assert.*;

public class AppTest {

    @Test
    public void testApp() {
        project_app myApp = new project_app();

        String result = myApp.getStatus();

        assertEquals("OK", result);
    }

}
