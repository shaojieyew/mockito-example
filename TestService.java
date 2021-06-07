package app.c2.service;

import app.c2.C2PlatformProperties;
import app.c2.dao.AppDao;
import app.c2.model.App;
import app.c2.model.AppInstance;
import app.c2.model.File;
import app.c2.model.Project;
import app.c2.model.compositeField.AppId;
import app.c2.service.spark.model.SparkArgKeyValuePair;
import app.c2.service.yarn.YarnSvc;
import app.c2.service.yarn.YarnSvcFactory;
import app.c2.service.yarn.model.YarnApp;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class TestService {

    public int convertStrToInt(String s){
        return Integer.parseInt(s);
    }

}
