/**
 *  ajax 请求工具类
 */
;!function (win) {
    let Ajax = function () {
    }
    Ajax.prototype = {

        /**
         * get请求方法
         * @param api 请求地址
         * @param params 参数
         * @param successCallback 成功回调方法
         * @param errorCallback 失败回调方法
         */
        get: function (api, params, successCallback, errorCallback) {
            $.ajax({
                url: getApiUrl(api),
                async: true,
                type: 'GET',
                data: params,
                dataType: "json",
                headers: {
                    "Authorization": localStorageGet(_const.TOKEN)
                },
                xhrFields: {
                    withCredentials: true
                },
                beforeSend: function (xmlHttp) {
                    // xmlHttp.setRequestHeader("If-Modified-Since","0");
                    // xmlHttp.setRequestHeader("Cache-Control","no-cache");
                },
                traditional: true,
                success: function (data) {
                    let code = data['code'];
                    if (200 === code) {
                        successCallback(data);
                    } else {
                        errorCallback(data);
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    layer.closeAll();
                    if (undefined === XMLHttpRequest.responseJSON) {
                        layer.msg("服务器已经断开了连接~~~~", {icon: 5, offset: '50px'});
                    } else {
                        if (errorCallback) {
                            errorCallback(XMLHttpRequest);
                        } else {
                            layer.msg(XMLHttpRequest.responseJSON.message, {icon: 5, offset: '50px'});
                        }
                    }
                }
            });
        },

        /**
         * post请求方法 "用来处理Content-Type: 为 application/json编码的内容"
         * @param api 请求地址
         * @param params 参数
         * @param successCallback 成功回调方法
         * @param errorCallback 失败回调方法
         */
        post: function (api, params, successCallback, errorCallback) {
            if (typeof (params) === 'object') params = JSON.stringify(params)
            let token = localStorage.getItem(_const.TOKEN);
            $.ajax({
                    url: getApiUrl(api),
                    async: true,
                    type: 'POST',
                    data: params,
                    dataType: "json",
                    contentType: 'application/json;charset=utf-8',
                    headers: {
                        "Authorization": token
                    },
                    xhrFields: {
                        withCredentials: true
                    },
                    beforeSend: function (xmlHttp) {
                        // xmlHttp.setRequestHeader("If-Modified-Since","0");
                        // xmlHttp.setRequestHeader("Cache-Control","no-cache");
                    }
                    ,
                    traditional: true,
                    success: function (data) {
                        let code = data['code'];
                        if (200 === code) {
                            successCallback(data);
                        } else {
                            errorCallback(data);
                        }
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        if (undefined === XMLHttpRequest.responseJSON) {
                            layer.msg("服务器已经断开了连接~~~~", {icon: 5, offset: '50px'});
                        } else {
                            layer.msg(XMLHttpRequest.responseJSON.message, {icon: 5, offset: '50px'});
                        }
                    }
                }
            );
        },

        /**
         * post请求方法 "用来处理Content-Type: 为 application/json编码的内容"
         * @param api 请求地址
         * @param params 参数
         * @param successCallback 成功回调方法
         * @param errorCallback 失败回调方法
         */
        uploadPost: function (api, params, successCallback, errorCallback) {
            let token = localStorage.getItem(_const.TOKEN);
            $.ajax({
                    url: getApiUrl(api),
                    async: true,
                    type: 'POST',
                    data: params,
                    dataType: "json",
                    headers: {
                        "Authorization": token
                    },
                    contentType: false,    //不可缺
                    processData: false,    //不可缺
                    success: function (data) {
                        let code = data['code'];
                        if (200 === code) {
                            successCallback(data);
                        } else {
                            errorCallback(data);
                        }
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        if (undefined === XMLHttpRequest.responseJSON) {
                            layer.msg("服务器已经断开了连接~~~~", {icon: 5, offset: '50px'});
                        } else {
                            layer.msg(XMLHttpRequest.responseJSON.message, {icon: 5, offset: '50px'});
                        }
                    }
                }
            );
        },
        /**
         * 默认请求方法
         * @param type 请求类型
         * @param api 请求地址
         * @param params 参数
         * @param successCallback 成功回调方法
         */
        default: function (api, type, params, successCallback, errorCallback) {
            $.ajax({
                url: getApiUrl(api),
                async: true,
                type: type,
                data: params,
                dataType: "json",
                xhrFields: {
                    withCredentials: true
                },
                traditional: true,
                success: function (data) {
                    let code = data['code'];
                    if (200 === code) {
                        successCallback(data);
                    } else {
                        errorCallback(data);
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    layer.closeAll();
                    if (undefined === XMLHttpRequest.responseJSON) {
                        layer.msg("服务器已经断开了连接~~~~", {icon: 5, offset: '50px'});
                    } else {
                        layer.msg(XMLHttpRequest.responseJSON.message, {icon: 5, offset: '50px'});
                    }
                }
            });
        }
    }
    win.essAjax = new Ajax();
}(window);

/**
 * 获取请求接口地址
 * @param api 地址
 * @return url 请求地址
 */
let getApiUrl = function (api) {
    return _const.BASE_URL + api;
}

let getUrl = function (api, daname) {
    return api.base + api[daname];
}


/**
 * 获取api接口地址
 * @param data
 * @param type
 * @param callBack
 */
let getBaseUrl = function (data, type, flag, callBack) {
    let url = "";
    const base = data.base;
    switch (type) {
        //列表
        case 1:
            url = base + data.list;
            break;
        //添加
        case 2:
            url = base + data.add;
            break;
        //编辑
        case 3:
            url = base + data.edit;
            break;
        //删除
        case 4:
            url = base + data.del;
            break;
        default:
            callBack(base, data);
    }
    if (flag)
        return _const.BASE_URL + url;
    else
        return url;
}

/**
 * 校验后台请求
 */
const checkOnlineNet = function () {
    essAjax.get("/usr", {}, null, function (data) {
        if (404 === data.status) layer.closeAll();
    });
}

const getQueryVariable = function (variable) {
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i = 0; i < vars.length; i++) {
        var pair = vars[i].split("=");
        if (pair[0] == variable) {
            return decodeURIComponent(pair[1]);
        }
    }
    return (false);
}

// 去除前缀下划线转换驼峰
const toHump = function (name) {
    let str = name.substring(name.indexOf("_") + 1);
    str = str.charAt(0).toUpperCase() + str.slice(1);
    return str.replace(/\_(\w)/g, function (all, letter) {
        return letter.toUpperCase();
    });
}

// 驼峰转换下划线
const toLine = function (name) {
    return name.replace(/([A-Z])/g, "_$1").toLowerCase();
}