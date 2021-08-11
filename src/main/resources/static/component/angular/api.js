/**
 * 项目API请求地址管理
 */
const API_URL = {

    tableInfo: {
        base: "/table",
        listTable: "/list_table",
        columnList: "/find_table_info",
        saveDesign: "/save_table_info"
    },


    // ************************************ 产品管理模块 ************************************

    /**
     * 物料管理
     */
    product: {
        base: "/product",
        add: "/uploadAddProduct",
    }

    /**
     * 分类管理
     */
    , category: {
        base: "/category",
        list: "/list",
    }

    , brand: {
        base: "/brand",
        list: "/list",
    }

    , supplier: {
        base: "/supplier",
        list: "/list_supplier",
    }

    // ************************************ 权限管理模块 ************************************

    /**
     * 用户信息管理
     */
    , userInfo: {
        base: "/userinfo",
        list: "/listbyuserinfo",
        add: "/adduserinfo",
        edit: "/edituserinfo",
        del: "/deleteuserinfo"
    }

    /**
     * 角色管理
     */
    , role: {
        base: "/role",
        list: "/listbyrole",
        add: "/addrole",
        edit: "/editrole",
        del: "/deleterole"
    }

    /**
     * 资源权限管理
     */
    , permission: {
        base: "/permission",
        list: "/listbypermission",
        add: "/addpermission",
        edit: "/editpermission",
        del: "/deletepermission"
    }


}

/**
 * 全局app
 * @type {null}
 */
let app = null;