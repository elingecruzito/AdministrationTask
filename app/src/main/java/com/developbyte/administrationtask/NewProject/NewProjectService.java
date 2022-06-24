package com.developbyte.administrationtask.NewProject;

import com.developbyte.administrationtask.Abstract.AbstractService;
import java.util.HashMap;
import java.util.Map;

public class NewProjectService extends AbstractService implements INewProject.INewProjectInformationHandler {

    private INewProject.INewProjectInformationDelegate iNewProjectInformationDelegate;
    private Map<String, String> parameters = new HashMap<>();

    public void setInformationDelegate(INewProject.INewProjectInformationDelegate iNewProjectInformationDelegate) {
        this.iNewProjectInformationDelegate = iNewProjectInformationDelegate;
    }
}