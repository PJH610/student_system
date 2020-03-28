package com.letopo.news.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.letopo.common.model.R;
import com.letopo.news.entity.News;
import com.letopo.news.service.INewsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jh
 * @since 2020-02-20
 */
@RestController
@RequestMapping("api/auth/news")
//@CrossOrigin(origins = "*")
@Api(tags = "新闻信息API")
public class NewsController {
    @Autowired
    private INewsService newsService;

    /**
     * 分页查询新闻信息API
     *
     * @param current
     * @param size
     * @return
     */
    @GetMapping
    @ApiOperation("新闻列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页"),
            @ApiImplicitParam(name = "size", value = "一页显示的条数")
    })

    @RequiresRoles(value = {"admin","admin1","admin123"} ,logical = Logical.OR)
    public R get(
            @RequestParam(defaultValue = "1") long current,
            @RequestParam(defaultValue = "10") long size) {
        Page<News> pageInfo = new Page<>(current, size);
        newsService.page(pageInfo);
//        Session session = SecurityUtils.getSubject().getSession();
//        System.out.println(session.getId());
        return R.ok("请求成功!", pageInfo);
    }

    @ApiOperation("新闻详情")
    @ApiImplicitParam(name = "id", value = "新闻ID")
    @GetMapping("{id}")
    public R get(@PathVariable Integer id) {
        return R.ok("请求成功", newsService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "发布新闻")
    public R post(@RequestBody News news) {
        newsService.save(news);
        return R.ok("发布成功", news);
    }


    @PutMapping
    public R put(@RequestBody News news) {
        newsService.updateById(news);
        return R.ok("更新成功", news);
    }

    @PatchMapping
    public R patch(@RequestBody News news) {
        newsService.updateById(news);
        return R.ok("请求成功", news);
    }

    @DeleteMapping("{id}")
    public R delete(@PathVariable Integer id) {
        newsService.removeById(id);
        return R.ok("删除成功");
    }
}
