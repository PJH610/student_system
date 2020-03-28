package com.letopo.carousel.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.letopo.carousel.entity.Carousel;
import com.letopo.carousel.service.ICarouselService;
import com.letopo.common.model.R;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  轮播图管理
 * </p>
 *
 * @author jh
 * @since 2020-03-12
 */
@RestController
@RequestMapping("/carousel/carousel")
public class CarouselController {

    @Autowired
    ICarouselService carouselService;

    @GetMapping
    @ApiOperation("轮播列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页"),
            @ApiImplicitParam(name = "size", value = "一页显示的条数")
    })

//    @RequiresRoles(value = {"admin","admin1","admin123"} ,logical = Logical.OR)
    public R get(
            @RequestParam(defaultValue = "1") long current,
            @RequestParam(defaultValue = "10") long size) {
        Page<Carousel> pageInfo = new Page<>(current, size);
        carouselService.page(pageInfo);
        return R.ok("请求成功!", pageInfo);
    }


    @PostMapping
    @ApiOperation(value = "添加轮播")
    public R post(@RequestBody Carousel carousel) {
        carouselService.save(carousel);
        return R.ok("请求成功", carousel);
    }


    @PutMapping
    public R put(@RequestBody Carousel carousel) {
        carouselService.updateById(carousel);
        return R.ok("请求成功", carousel);
    }

    @PatchMapping
    public R patch(@RequestBody Carousel carousel) {
        carouselService.updateById(carousel);
        return R.ok("请求成功", carousel);
    }

    @DeleteMapping("{id}")
    public R delete(@PathVariable Integer id) {
        carouselService.removeById(id);
        return R.ok("请求成功");
    }

}
