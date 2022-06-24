package com.developbyte.administrationtask.InfoProject;

import com.developbyte.administrationtask.Abstract.AbstractService;
import java.util.HashMap;
import java.util.Map;

public class InfoProjectService extends AbstractService implements IInfoProject.IInfoProjectInformationHandler {

    private IInfoProject.IInfoProjectInformationDelegate iInfoProjectInformationDelegate;
    private Map<String, String> parameters = new HashMap<>();

    public void setInformationDelegate(IInfoProject.IInfoProjectInformationDelegate iInfoProjectInformationDelegate) {
        this.iInfoProjectInformationDelegate = iInfoProjectInformationDelegate;
    }
}