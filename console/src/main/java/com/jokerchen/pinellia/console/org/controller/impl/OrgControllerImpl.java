package com.jokerchen.pinellia.console.org.controller.impl;

import com.jokerchen.pinellia.common.annotation.OperationLogger;
import com.jokerchen.pinellia.common.constant.Constant;
import com.jokerchen.pinellia.common.model.TreeNode;
import com.jokerchen.pinellia.console.org.controller.OrgController;
import com.jokerchen.pinellia.console.org.entity.Org;
import com.jokerchen.pinellia.console.org.service.OrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description:
 * @author Joker Chen
 * @date 2019-03-13 16:35:20
 */
@RestController
public class OrgControllerImpl implements OrgController {

    @Autowired
    private OrgService orgService;

    @Override
    public List<TreeNode> findOrg() {
        return orgService.findOrg();
    }

    @Override
    @OperationLogger(operationType=Constant.OPERATION_DELETE,operationDesc="删除组织")
    public void deleteOrg(String code) {
        orgService.deleteOrg(code);
    }

    @Override
    @OperationLogger(operationType=Constant.OPERATION_UPDATE,operationDesc="停用组织")
    public void disableOrg(String code) {
        orgService.disableOrg(code);
    }

    @Override
    @OperationLogger(operationType=Constant.OPERATION_UPDATE,operationDesc="启用组织")
    public void enableOrg(String code) {
        orgService.enableOrg(code);
    }

    @Override
    @OperationLogger(operationType=Constant.OPERATION_SAVE,operationDesc="保存组织信息")
    public void saveOrg(Org org) {
        orgService.saveOrg(org);
    }

}
