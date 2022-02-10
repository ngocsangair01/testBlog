package com.example.demedung.controllers;

import com.example.demedung.exceptions.NotFoundException;
import com.example.demedung.models.Blog;
import com.example.demedung.models.User;
import com.example.demedung.repositories.BlogRepository;
import com.example.demedung.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/blogs")
public class BlogController {
    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UserRepository userRepository;
    @PostMapping("/{idUser}")
    public ResponseEntity<?> createBlog(@RequestParam("title") String title,
                                        @RequestParam("content")String content,
                                        @PathVariable("idUser")Integer idUser) {
        Optional<User> user0 = userRepository.findById(idUser);
        if (user0.isEmpty()){
            throw new NotFoundException("Khong co user");
        }
        Blog blog = new Blog(title,content,user0.get());
        user0.get().getBlogs().add(blog);
        Blog blog1 = blogRepository.save(blog);
        return ResponseEntity.status(201).body(blog1);
    }

    @GetMapping()
    public ResponseEntity<?> getAllBlogs(){
        List<Blog> blogs = blogRepository.findAll();
        if (blogs.size()==0){
            throw new NotFoundException("khong tim thay blog");
        }
        return ResponseEntity.status(200).body(blogs);
    }
}
