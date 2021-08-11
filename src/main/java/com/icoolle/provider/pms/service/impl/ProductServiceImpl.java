package com.icoolle.provider.pms.service.impl;

import com.icoolle.annotation.TransactionalService;
import com.icoolle.common.core.service.impl.IcoolleServiceImpl;
import com.icoolle.model.pms.dto.ProductSaveDTO;
import com.icoolle.model.pms.dto.ProductUpdateDTO;
import com.icoolle.model.pms.po.Product;
import com.icoolle.provider.pms.mapper.ProductMapper;
import com.icoolle.provider.pms.service.ProductService;
import org.springframework.beans.BeanUtils;
import com.icoolle.model.pms.vo.ProductListVO;
import com.icoolle.model.pms.dto.ProductListDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
* @author: YY
* @description: 商品表业务接口实现
* @projectName: ProductServiceImpl
* @package: com.icoolle.provider.pms.service.impl
* @date: 2021-08-11 16:03:53
* @version: V1.0.0
*/
@TransactionalService
public class ProductServiceImpl extends IcoolleServiceImpl<Product, ProductMapper> implements ProductService {

        @Autowired
        private ProductMapper productMapper;

        @Override
        public List<ProductListVO> list(ProductListDTO productListDTO) {
            return productMapper.list(productListDTO);
        }

        @Override
        public void saveProduct(ProductSaveDTO productSaveDTO) {
            Product product = new Product();
            BeanUtils.copyProperties(productSaveDTO, product);
            super.generalInsert(product);
        }

        @Override
        public void updateProduct(ProductUpdateDTO productUpdateDTO) {
            Product product = new Product();
            BeanUtils.copyProperties(productUpdateDTO, product);
            super.generalUpdate(product);
        }

        @Override
        public void deleteProduct(Long id) {
            super.removeById(id);
        }

        @Override
        public ProductListVO getProduct(Long id){
            ProductListVO productListVO = productMapper.getProduct(id);
            return productListVO;
        }
}
