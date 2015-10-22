package com.resources.facade;

import com.resources.entity.SystemConfig;
import com.resources.pagination.admin.DefaultAdminPagination;

public class SystemConfigFacade extends AbstractFacade {

    public SystemConfigFacade() {
        super(SystemConfig.class);
    }

    @Override
    public void pageData(DefaultAdminPagination pagination) {
    }
}
