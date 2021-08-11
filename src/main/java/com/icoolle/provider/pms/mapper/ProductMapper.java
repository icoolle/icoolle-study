package com.icoolle.provider.pms.mapper;

import com.icoolle.common.core.mapper.IcoolleMapper;
import com.icoolle.model.pms.po.Product;
import com.icoolle.model.pms.vo.ProductListVO;
import com.icoolle.model.pms.dto.ProductListDTO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
* @author: YY
* @description: 商品表业务底层接口
* @projectName: ProductMapper
* @package: com.icoolle.provider.pms.mapper
* @date: 2021-08-11 16:03:53
* @version: V1.0.0
*/
public interface ProductMapper extends IcoolleMapper<Product> {

        /**
        * 不分页商品表列表
        * @param productListDTO
        * @return
        */
        List<ProductListVO> list(@Param("param") ProductListDTO productListDTO);

        /**
        * 商品表详情
        * @param id
        * @return
        */
        ProductListVO getProduct(Long id);

}
