package service;

import org.junit.Test;
import config.UserConfiguration;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class UserConfigServiceTest {

    private final UserConfigService sut = new UserConfigService();

    @Test
    public void return_not_null_option_object() {
        //when
        UserConfiguration result = sut.loadConfigFile();
        //then
        assertThat(result).isNotNull();
    }


}
