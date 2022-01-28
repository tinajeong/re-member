package migu.project.remember.repository;

import migu.project.remember.data.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface PostRepository extends CrudRepository<Post, String> {
    List<Post> findByTitleContaining(String title);
    Post findByPostId(String id);
    List<Post> findAll();
    List<Post> findByCategoryContaining(String category);
}
