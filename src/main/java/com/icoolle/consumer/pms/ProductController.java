package com.icoolle.consumer.pms;

import com.icoolle.annotation.RestMappingController;
import com.icoolle.common.constant.consts.AuthorConst;
import com.icoolle.common.constant.consts.MethodConst;
import com.icoolle.common.core.controller.IcoolleController;
import com.icoolle.common.core.model.Result;
import com.icoolle.common.core.model.ResultPage;
import com.icoolle.model.pms.dto.ProductListDTO;
import com.icoolle.model.pms.dto.ProductSaveDTO;
import com.icoolle.model.pms.dto.ProductUpdateDTO;
import com.icoolle.model.pms.vo.ProductListVO;
import com.icoolle.provider.pms.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

/**
* @author: YY
* @description: 商品表管理接口
* @projectName: ProductController
* @package: com.icoolle.consumer.pms
* @date: 2021-08-11 16:03:53
* @version: V1.0.0
*/
@Api(tags = "商品表管理")
@RestMappingController("product")
public class ProductController extends IcoolleController<ProductService> {


    @ApiOperation(value = "分页查询商品表数据", httpMethod = MethodConst.POST, notes = AuthorConst.YY)
    @PostMapping("list_product")
    public ResultPage<ProductListVO> listProduct(@RequestBody ProductListDTO productListDTO) {
        return ResultPage.startPage(super.service.listByPage(productListDTO));
    }

    @ApiOperation(value = "不分页查询商品表数据", httpMethod = MethodConst.POST, notes = AuthorConst.YY)
    @PostMapping("list")
    public Result<List<ProductListVO>> list(@RequestBody ProductListDTO productListDTO) {
         return Result.success(super.service.list(productListDTO));
    }

    @ApiOperation(value = "添加商品表数据", httpMethod = MethodConst.POST, notes = AuthorConst.YY)
    @PostMapping("save_product")
    public Result<?> saveProduct(@Validated @RequestBody ProductSaveDTO productSaveDTO) {
        super.service.saveProduct(productSaveDTO);
        return Result.success();
    }

    @ApiOperation(value = "修改商品表数据", httpMethod = MethodConst.POST, notes = AuthorConst.YY)
    @PostMapping("update_product")
    public Result<?> updateProduct(@Validated @RequestBody ProductUpdateDTO productUpdateDTO) {
        super.service.updateProduct(productUpdateDTO);
        return Result.success();
    }

    @ApiOperation(value = "删除商品表数据", httpMethod = MethodConst.GET, notes = AuthorConst.YY)
    @GetMapping("delete_product/{id}")
    @ApiParam(name = "id", value = "主键id", required = true)
    public Result<?> deleteProduct(@PathVariable Long id) {
        super.service.deleteProduct(id);
        return Result.success();
    }

    @ApiOperation(value = "商品表详情", httpMethod = MethodConst.GET,notes = AuthorConst.YY)
    @GetMapping("getProduct/{id}")
    @ApiParam(name = "id", value = "主键id", required = true)
    public Result<ProductListVO> getProduct(@PathVariable Long id) {
         ProductListVO productListVO = super.service.getProduct(id);
         return Result.success(productListVO);
    }

}
