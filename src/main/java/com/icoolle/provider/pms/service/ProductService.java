package com.icoolle.provider.pms.service;

import com.icoolle.common.core.service.IcoolleService;
import com.icoolle.model.pms.dto.ProductSaveDTO;
import com.icoolle.model.pms.dto.ProductUpdateDTO;
import com.icoolle.model.pms.po.Product;
import com.icoolle.provider.pms.mapper.ProductMapper;
import com.icoolle.model.pms.vo.ProductListVO;
import com.icoolle.model.pms.dto.ProductListDTO;
import java.util.List;

/**
* @author: YY
* @description:  商品表业务接口
* @projectName: ProductService
* @package: com.icoolle.provider.pms.service
* @date: 2021-08-11 16:03:53
* @version: V1.0.0
*/
public interface ProductService extends IcoolleService<Product, ProductMapper> {

        /**
        * 不分页商品表列表
        * @param productListDTO
        * @return
        */
        List<ProductListVO> list(ProductListDTO productListDTO);

        /**
         * 保存商品表信息
         *
         * @param productSaveDTO 商品表信息参数
         */
        void saveProduct(ProductSaveDTO productSaveDTO);


        /**
         * 修改商品表信息
         *
         * @param productUpdateDTO 商品表信息参数
         */
        void updateProduct(ProductUpdateDTO productUpdateDTO);

        /**
         * 删除商品表信息
         *
         * @param id 主键ID
         */
        void deleteProduct(Long id);

        /**
        * 商品表详情
        * @param id
        * @return
        */
        ProductListVO getProduct(Long id);
}
