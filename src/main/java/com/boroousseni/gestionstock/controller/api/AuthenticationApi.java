package com.boroousseni.gestionstock.controller.api;

import static com.boroousseni.gestionstock.utils.Constants.APP_ROOT;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "authentication")
@RequestMapping(APP_ROOT + "/auth")
public interface AuthenticationApi {


}
