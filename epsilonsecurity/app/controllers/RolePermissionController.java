package controllers;

import play.mvc.*;
import models.databaseModel.roles.DbRolePermission;
import models.databaseModel.helpers.DbRolePermissionHelper;

public class RolePermissionController extends Controller {

//    TODO: Implement functionality just RolePermissionController
    public Result listRolePermissions() {
    return ok();
}

    public Result createRolePermission(Integer roleId, Integer permissionId) {
        return ok();
    }

    public Result retrieveRolePermission(Integer rolePermissionId) {
        return ok();
    }

    public Result deleteRolePermission(Integer rolePermissionId) {
        return ok();
    }
}
