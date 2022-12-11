package migu.project.remember.controller;

import migu.project.remember.data.Post;
import migu.project.remember.data.PostForm;
import migu.project.remember.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
        for (Post post : postList) {
            log.info(post.toString());
        }
        return postList;
    }

    @GetMapping("feed/title")
    public List<Post> title(@RequestParam(value = "search") String title) {
        return postService.getByTitle(title);
    }

    @GetMapping("feed/category")
    public List<Post> category(@RequestParam(value = "search") String keyword) {
        List<Post> postList = null;
        try {
            log.info("search keyword : {} -> {}", keyword, URLDecoder.decode(keyword, "UTF-8"));
            postList = postService.getByCategory(URLDecoder.decode(keyword, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        for (Post post : postList) {
            log.info(post.toString());
        }
        return postList;
    }
    @GetMapping("category")
    public List<String> distinctCategory() {
        return postService.getDistinctCategory();
    }
    @PostMapping("chunk")
    public String postChunk(@RequestBody @Valid PostForm postForm, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {

            log.info("invalid postForm :"+postForm.toString());
            return "redirect:/feed";
        }

        String category = postForm.getCategory();
        category = category == null ? "" : category;

        Post post = new Post(postForm.getTitle(), postForm.getContents(), category);
        postService.save(post);
        return "redirect:/feed";
    }

}
