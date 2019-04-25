package com.wechat.controller;

import com.wechat.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
}
