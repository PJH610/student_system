package com.letopo.product.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.letopo.common.model.R;
import com.letopo.product.entity.Product;
import com.letopo.product.service.IProductService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jh
 * @since 2020-03-20
 */
@RestController
@RequestMapping("/product/product")
public class ProductController {


    @Autowired
    private IProductService productService;

    /**
     * 分页查询产品API
     *
     * @param num
     * @param size
     * @return
     */
    @GetMapping
    @ApiOperation("产品列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "num", value = "当前页"),
            @ApiImplicitParam(name = "size", value = "一页显示的条数")
    })
    public R get(
            @RequestParam(defaultValue = "1") long num,
            @RequestParam(defaultValue = "10") long size) {
        Page<Product> pageInfo = new Page<>(num, size);
        productService.page(pageInfo);
//        Session session = SecurityUtils.getSubject().getSession();
//        System.out.println(session.getId());
        return R.ok("请求成功!", pageInfo);
    }

    // 分页显示，查询用户
//    @CrossOrigin
//    @GetMapping("/list")
//    public Map<String, Object> list(@RequestParam(defaultValue = "1") Integer page_num, @RequestParam(defaultValue = "10") Integer page_size) {
//
//        PageHelper.startPage(page_num, page_size);
//        return R.ok("请求成功",new MyPageInfo<Product>(productService.list()));
//    }

    @ApiOperation("单个产品详情")
    @ApiImplicitParam(name = "id", value = "用户ID")
    @GetMapping("{id}")
    public R get(@PathVariable Integer id) {
        return R.ok("请求成功", productService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "添加用户")
    public R post(@RequestBody Product product) {
        productService.save(product);
        return R.ok("请求成功", product);
    }


    @PutMapping
    public R put(@RequestBody Product product) {
        productService.updateById(product);
        return R.ok("请求成功", product);
    }

    @PatchMapping
    public R patch(@RequestBody Product product) {
        productService.updateById(product);
        return R.ok("请求成功", product);
    }

    @DeleteMapping("{id}")
    public R delete(@PathVariable Integer id) {
        productService.removeById(id);
        return R.ok("请求成功");
    }
}
