package com.developbyte.administrationtask.Home;

import com.developbyte.administrationtask.Abstract.AbstractService;
import java.util.HashMap;
import java.util.Map;

public class HomeService extends AbstractService implements IHome.IHomeInformationHandler {

    private IHome.IHomeInformationDelegate iHomeInformationDelegate;
    private Map<String, String> parameters = new HashMap<>();

    public void setInformationDelegate(IHome.IHomeInformationDelegate iHomeInformationDelegate) {
        this.iHomeInformationDelegate = iHomeInformationDelegate;
    }
}