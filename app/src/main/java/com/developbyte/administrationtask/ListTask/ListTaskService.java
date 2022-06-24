package com.developbyte.administrationtask.ListTask;

import com.developbyte.administrationtask.Abstract.AbstractService;
import java.util.HashMap;
import java.util.Map;

public class ListTaskService extends AbstractService implements IListTask.IListTaskInformationHandler {

    private IListTask.IListTaskInformationDelegate iListTaskInformationDelegate;
    private Map<String, String> parameters = new HashMap<>();

    public void setInformationDelegate(IListTask.IListTaskInformationDelegate iListTaskInformationDelegate) {
        this.iListTaskInformationDelegate = iListTaskInformationDelegate;
    }
}