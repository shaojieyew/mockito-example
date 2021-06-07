package app.c2.controller;

import app.c2.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RestController
@RequestMapping(value = "/")
public class TestController {

  public Logger logger = LoggerFactory.getLogger(TestController.class);

  @Autowired
  TestService testService;

  @GetMapping("test/{s}")
  public void convertStringToInt(@PathVariable String s) {
    try{
      testService.convertStrToInt(s);
      logger.info(String.format("convertStrToInt successfully returns %s", s));
    }catch (Exception ex){
      logger.error(ex.getLocalizedMessage());
    }
  }
}
