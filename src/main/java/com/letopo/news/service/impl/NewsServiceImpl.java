package com.letopo.news.service.impl;

import com.letopo.news.entity.News;
import com.letopo.news.mapper.NewsMapper;
import com.letopo.news.service.INewsService;
import com.letopo.common.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jh
 * @since 2020-02-20
 */
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements INewsService {

}
