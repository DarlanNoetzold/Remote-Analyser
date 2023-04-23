package tech.noetzold.remoteanalyser.unit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import tech.noetzold.remoteanalyser.controller.BadLanguageController;
import tech.noetzold.remoteanalyser.model.BadLanguage;
import tech.noetzold.remoteanalyser.service.BadLanguageService;
import tech.noetzold.remoteanalyser.service.LoginAppService;
import tech.noetzold.remoteanalyser.util.LoginApiService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BadLanguageController.class)
public class BadLanguageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BadLanguageService badLanguageService;

    @MockBean
    private LoginAppService loginService;

    @MockBean
    private LoginApiService loginProp;

    @Test
    public void testBadLanguage() throws Exception {
        List<BadLanguage> badLanguages = new ArrayList<>();
        badLanguages.add(new BadLanguage("test1"));
        badLanguages.add(new BadLanguage("test2"));

        given(badLanguageService.buscaBadLanguage(anyString())).willReturn(badLanguages);

        mockMvc.perform(get("/badLanguage"))
                .andExpect(status().isOk())
                .andExpect(view().name("/badLanguage"))
                .andExpect(model().attribute("badLanguages", badLanguages));
    }

    @Test
    public void testRemover() throws Exception {
        doNothing().when(badLanguageService).removeBadLanguage(anyString(), anyLong());

        mockMvc.perform(get("/badLanguage/remover/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(view().name("/badLanguage"));
    }

    @Test
    public void testSave() throws Exception {
        doNothing().when(badLanguageService).saveBadLanguage(anyString(), any(BadLanguage.class));

        mockMvc.perform(post("/badLanguage/save")
                        .param("name", "test"))
                .andExpect(status().isOk())
                .andExpect(view().name("redirect:/badLanguage"));
    }
}