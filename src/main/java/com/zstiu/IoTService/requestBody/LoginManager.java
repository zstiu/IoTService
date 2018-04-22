package com.zstiu.IoTService.requestBody;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LoginManager {
    private String managerName;
    private String password;
}
