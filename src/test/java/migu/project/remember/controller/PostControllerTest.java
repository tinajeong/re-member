package migu.project.remember.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class PostControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext ctx;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))  // 한글 인코딩
                .alwaysDo(print())
                .build();
    }

    @Test
    void testFeed() throws Exception {
        mockMvc.perform(get("/feed"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("title")))
                .andExpect(content().string(containsString("contents")))
                .andExpect(content().string(containsString("category")));
    }

    @Test
    void testTitle() throws Exception {
        String title = "에게";
        mockMvc.perform(get("/title?search="+title))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(title)));
    }

    @Test
    void testCategory() throws Exception{
        String category = "편지";
        mockMvc.perform(get("/category?search="+category))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(category)));
    }

}