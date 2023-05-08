package com.example.listam.controller;


import com.example.listam.entity.Comment;
import com.example.listam.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/comments")
    public String commentsPage(ModelMap modelMap) {
        List<Comment> all = commentRepository.findAll();
        modelMap.addAttribute("comments", all);
        return "comments";
    }

    @GetMapping("/comments/add")
    public String addCommentPage() {
        return "addComment";
    }

    @PostMapping("/comments/add")
    public String addComment(@RequestParam("comment") String name) {
        Comment comment = new Comment();
        comment.setComment(name);
        commentRepository.save(comment);
        return "redirect:/comments";
    }

    @GetMapping("/comments/remove")
    public String removeComment(@RequestParam("id") int id) {
        commentRepository.deleteById(id);
        return "redirect:/comments";
    }

}
