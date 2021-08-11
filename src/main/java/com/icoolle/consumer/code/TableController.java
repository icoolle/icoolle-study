package com.icoolle.consumer.code;


import com.icoolle.annotation.IgnoredUrl;
import com.icoolle.annotation.RestMappingController;
import com.icoolle.common.constant.consts.MethodConst;
import com.icoolle.common.core.model.Result;
import com.icoolle.common.core.model.ResultPage;
import com.icoolle.model.code.dto.TableDTO;
import com.icoolle.model.code.dto.TableDesignSaveDTO;
import com.icoolle.model.code.po.Table;
import com.icoolle.model.code.po.TableInfo;
import com.icoolle.provider.code.service.TableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@IgnoredUrl(urls = {"/table/list_table", "/table/find_table_info", "/table/save_table_info"})
@Api(tags = "表数据管理")
@RestMappingController("table")
public class TableController {

    private final static String ENV_LOCAL = "local";
    /**
     * 当前环境
     */
    @Value("${spring.profiles.active}")
    private String profile;

    @Autowired
    private TableService tableService;


    @ApiOperation(value = "分页查询表数据", httpMethod = MethodConst.POST)
    @PostMapping("list_table")
    public Result<ResultPage<Table>> list(@RequestBody TableDTO tableDTO) {
        return Result.success(ResultPage.startPage(tableService.list(tableDTO)));
    }

    @ApiOperation(value = "分页查询列数据", httpMethod = MethodConst.POST)
    @PostMapping("find_table_info")
    public Result<List<TableInfo>> list(String tableName) {
        return Result.success(tableService.findTableInfo(tableName));
    }

    @ApiOperation(value = "生成代码数据", httpMethod = MethodConst.POST)
    @PostMapping("save_table_info")
    public Result saveTableInfo(@Validated @RequestBody TableDesignSaveDTO tableDesignSaveDTO) {
//        if (ENV_LOCAL.equals(profile)) {
        if (true) {
            tableService.saveTableInfo(tableDesignSaveDTO);
            return Result.success();
        }
        return Result.failure();
    }
}
