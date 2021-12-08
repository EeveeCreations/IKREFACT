import nl.hsleiden.svdj8.Svdj8Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Svdj8Application.class)
@AutoConfigureMockMvc
public class GeneralStatusCodeTest {

    private ArrayList<String> endPointList = new ArrayList<>();

    @Autowired
    private MockMvc mvc;

    @Before
    public void init() {
        endPointList.add("admin");
        endPointList.add("answer");
        endPointList.add("category");
        endPointList.add("givenanswer");
        endPointList.add("grant");
        endPointList.add("question");
        endPointList.add("route");
        endPointList.add("result");
    }

    @Test
    public void givenEndpoints_whenGet1_thenStatusCode200() throws Exception {
        for (String endpoint : endPointList) {
            mvc.perform(get("http://localhost:8080/" + endpoint + "/1"))
                    .andExpect(status().isOk());
        }
    }

    @Test
    public void givenEndpoints_whenGetAll_thenStatusCode200() throws Exception {
        for (String endpoint : endPointList) {
            mvc.perform(get("http://localhost:8080/" + endpoint + "/all"))
                    .andExpect(status().isOk());
        }
    }

    @Test
    public void givenEndpoints_whenGet5000000_thenStatusCode404() throws Exception {
        for (String endpoint : endPointList) {
            mvc.perform(get("http://localhost:8080/" + endpoint + "/5000000"))
                    .andExpect(status().is(404));
        }
    }

    @Test
    public void givenEndpoints_whenGetMinusOne_thenStatusCode404() throws Exception {
        for (String endpoint : endPointList) {
            mvc.perform(get("http://localhost:8080/" + endpoint + "/-1"))
                    .andExpect(status().is(404));
        }
    }

    @Test
    public void givenEndpoints_whenGetZero_thenStatusCode404() throws Exception {
        for (String endpoint : endPointList) {
            mvc.perform(get("http://localhost:8080/" + endpoint + "/0"))
                    .andExpect(status().is(404));
        }
    }

    @Test
    public void givenEndpoints_whenGetNoInteger_thenStatusCode400() throws Exception {
        for (String endpoint : endPointList) {
            mvc.perform(get("http://localhost:8080/" + endpoint + "/Z"))
                    .andExpect(status().is(400));
        }
    }

    @Test
    public void givenEndpoints_whenGetNoInputGet_thenStatusCode405() throws Exception {
        for (String endpoint : endPointList) {
            mvc.perform(get("http://localhost:8080/" + endpoint))
                    .andExpect(status().is(405));
        }
    }

    @Test
    public void givenApplication_whenNoEndPoint_thenStatusCode404() throws Exception {
        mvc.perform(get("http://localhost:8080/"))
                .andExpect(status().is(404));
    }

    @Test
    public void givenEndpoints_whenNoInputPut_thenStatusCode400() throws Exception{
        for(String endpoint : endPointList){
            mvc.perform(put("http://localhost:8080/"+endpoint + "/1"))
                    .andExpect(status().is(400));
        }

    }
}
