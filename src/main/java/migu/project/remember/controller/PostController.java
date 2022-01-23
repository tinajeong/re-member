package migu.project.remember.controller;

import migu.project.remember.data.Post;
import migu.project.remember.data.PostForm;
import migu.project.remember.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {

    private final Logger log = LoggerFactory.getLogger(PostController.class);
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("feed")
    public List<Post> all() {
        List<Post> postList = postService.getAll();
        // model.addAttribute("postList", postList);
        for (Post post : postList) {
            log.info(post.toString());
        }
        return postList;
    }

    @GetMapping("title")
    public List<Post> title(@RequestParam(value = "search") String title) {
        return postService.getByTitle(title);
    }

    @GetMapping("category")
    public List<Post> category(@RequestParam(value = "search") String title) {
        return postService.getByCategory(title);
    }

    @PostMapping("chunk")
    public String postChunk(@RequestBody PostForm postForm) {
        String category = postForm.getCategory();
        category = category == null ? "" : category;

        Post post = new Post(postForm.getTitle(), postForm.getContents(), category);
        postService.save(post);
        return "redirect:/feed";
    }

}
