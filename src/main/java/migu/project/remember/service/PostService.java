package migu.project.remember.service;

import migu.project.remember.data.Post;
import migu.project.remember.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAll()  {
        return postRepository.findAll();
    }

    public List<Post> getByTitle(String title) {
        return postRepository.findByTitleContaining(title);
    }

    public List<Post> getByCategory(String category) {
        return postRepository.findByCategory(category);
    }

    public void save(Post post) {
        postRepository.save(post);
    }

}
