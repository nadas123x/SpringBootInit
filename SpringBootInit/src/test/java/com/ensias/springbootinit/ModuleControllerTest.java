package com.ensias.springbootinit;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import com.ensias.springbootinit.Controller.ModuleController;
import com.ensias.springbootinit.Repository.ModuleRepository;
import com.ensias.springbootinit.model.Module;
import com.ensias.springbootinit.services.ModuleService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
public class ModuleControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private ModuleController moduleController;

    @Mock
    private ModuleService moduleService;

    @Mock
    private ModuleRepository moduleRepo;

    private Module module;
    private List<Module> modules;
    private Page<Module> modulePage;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(moduleController).build();

        module = new Module(1, "Module 1","Module 1 Description" );
        modules = new ArrayList<>();
        modules.add(module);

        modulePage = new PageImpl<Module>(modules);
    }

    @Test
    public void testGetModules() throws Exception {
        when(moduleService.getAllModules()).thenReturn(modules);

        mockMvc.perform(MockMvcRequestBuilders.get("/modules"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(1L))
                .andExpect(jsonPath("$.[0].nom").value("Module 1"))
                .andExpect(jsonPath("$.[0].description").value("Module 1 Description"));

        verify(moduleService, times(1)).getAllModules();
    }
    @Test
    public void testSupprimerModule() throws Exception {
        // Given
        Long id = 1L;
        doNothing().when(moduleService).supprimerModule(id);

        // When
        mockMvc.perform(delete("/modules/{id}", id))
                .andExpect(status().isNoContent());

        // Then
        verify(moduleService, times(1)).supprimerModule(id);
    }
}