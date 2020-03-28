package com.letopo.product.service.impl;

import com.letopo.product.entity.Product;
import com.letopo.product.mapper.ProductMapper;
import com.letopo.product.service.IProductService;
import com.letopo.common.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jh
 * @since 2020-03-20
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

}
