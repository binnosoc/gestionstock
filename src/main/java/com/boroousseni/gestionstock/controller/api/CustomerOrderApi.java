package com.boroousseni.gestionstock.controller.api;

import static com.boroousseni.gestionstock.utils.Constants.APP_ROOT;

import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "commandes clients")
@RequestMapping(APP_ROOT)
public interface CustomerOrderApi {

}
