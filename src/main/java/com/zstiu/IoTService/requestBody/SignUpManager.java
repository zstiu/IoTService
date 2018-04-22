package com.zstiu.IoTService.requestBody;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class SignUpManager {
    private String managerName;
    private String password;
    private String apiKey;
}
