package cn.ksdshpx.controller;

import cn.ksdshpx.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/6/27
 * Time: 15:43
 * Description:BookController类
 */
@Controller
public class BookController {
    @Autowired
    private BookService bookService;
}
