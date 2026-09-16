package com.lylechristine.socialnetwork.repository;
import com.lylechristine.socialnetwork.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface PostRepository extends JpaRepository<Post,Long>{
    List<Post> findAllByOrderByCreatedAtDesc();
    List<Post> findByAuthorUsernameOrderByCreatedAtDesc(String username);
}
