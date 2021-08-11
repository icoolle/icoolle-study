/**
 * 系统配置常量值
 */
let _const = {

    // ******** 基础 常量配置 *******************
    // 保存token键key
    TOKEN: "token"
    // 基础请求地址
    , BASE_URL: "./../../.."
    , LOCAL_BASE_URL: "./../../.."
    // ******** ajax 常量配置 *******************
    // 请求类型
    , CONTENTTYPE: "application/json;charset=utf-8"
};


const DATE_TYPE = [
    {jdbcType: 'varchar', javaType: 'String'},
    {jdbcType: 'varbinary', javaType: 'String'},
    {jdbcType: 'char', javaType: 'String'},
    {jdbcType: 'blob', javaType: 'String'},
    {jdbcType: 'text', javaType: 'String'},
    {jdbcType: 'integer', javaType: 'Integer'},
    {jdbcType: 'int', javaType: 'Integer'},
    {jdbcType: 'tinyint', javaType: 'Integer'},
    {jdbcType: 'smallint', javaType: 'Integer'},
    {jdbcType: 'mediumint', javaType: 'Integer'},
    {jdbcType: 'bit', javaType: 'Boolean'},
    {jdbcType: 'bigint', javaType: 'Long'},
    {jdbcType: 'float', javaType: 'Float'},
    {jdbcType: 'double', javaType: 'Double'},
    {jdbcType: 'decimal', javaType: 'BigDecimal'},
    {jdbcType: 'boolean', javaType: 'Boolean'},
    {jdbcType: 'date', javaType: 'LocalDate'},
    {jdbcType: 'time', javaType: 'LocalTime'},
    {jdbcType: 'datetime', javaType: 'LocalDateTime'},
    {jdbcType: 'timestamp', javaType: 'LocalDateTime'},
    {jdbcType: 'year', javaType: 'LocalDate'},
];

/**
 * 获取后台常量数据
 * @type {SelfParam}
 */
;!function (win) {
    let SelfParam = function () {
    }

    SelfParam.prototype = {

        /**
         * 用户类型
         * @returns {*[]}
         */
        getUserInfoType: function () {
            let userType = [
                {
                    name: "普通用户"
                    , value: 0
                }
                , {
                    name: "管理员"
                    , value: 1
                }
                , {
                    name: "小管理员"
                    , value: 2
                }
            ];
            return userType;
        },

        getResourceType: function () {
            return [
                {name: '菜单', value: 1}
                , {name: '按钮', value: 2}
            ]
        },

        getDateTypeName: function (jdbcType) {
            let str = "";
            for (let i = 0; i < DATE_TYPE.length; i++) {
                if (DATE_TYPE[i].jdbcType === jdbcType) {
                    str = DATE_TYPE[i].javaType;
                    break;
                }
            }
        }
    };
    win.sysParam = new SelfParam();
}(window);