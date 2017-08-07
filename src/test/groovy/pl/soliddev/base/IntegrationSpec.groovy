package pl.soliddev.base

import org.junit.Before
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.ConfigurableMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import pl.soliddev.AppRunner
import pl.soliddev.infrastructure.config.Profiles
import spock.lang.Specification

@ActiveProfiles([Profiles.INTEGRATION])
@ContextConfiguration
@SpringBootTest(
        classes = [AppRunner],
        webEnvironment = SpringBootTest.WebEnvironment.MOCK
)
abstract class IntegrationSpec extends Specification {
    @Autowired
    protected WebApplicationContext webApplicationContext

    MockMvc mockMvc;

    @Before
    void setupMockMvc() {
        ConfigurableMockMvcBuilder mockMvcBuilder = MockMvcBuilders.webAppContextSetup(webApplicationContext)
        mockMvc = mockMvcBuilder.build()
    }
}
