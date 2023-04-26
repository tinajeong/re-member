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
@RequestMapping("feed")
public class PostController {

    private final Logger log = LoggerFactory.getLogger(PostController.class);
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Post> all() {
        List<Post> postList = postService.getAll();
        postList.forEach(log::info);
        return postList;
    }

    @GetMapping("title")
    public List<Post> title(@RequestParam("search") String title) {
        return postService.getByTitle(title);
    }

    @GetMapping("category")
    public List<Post> category(@RequestParam("search") String keyword) {
        try {
            String decodedKeyword = URLDecoder.decode(keyword, "UTF-8");
            log.info("search keyword : {} -> {}", keyword, decodedKeyword);
            List<Post> postList = postService.getByCategory(decodedKeyword);
            postList.forEach(log::info);
            return postList;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("category/distinct")
    public List<String> distinctCategory() {
        return postService.getDistinctCategory();
    }

    @PostMapping("chunk")
    public String postChunk(@RequestBody @Valid PostForm postForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.info("invalid postForm :{}", postForm);
            return "redirect:/feed";
        }

        String category = postForm.getCategory() == null ? "" : postForm.getCategory();
        Post post = new Post(postForm.getTitle(), postForm.getContents(), category);
        postService.save(post);
        return "redirect:/feed";
    }
}
