package com.lylechristine.socialnetwork.repository;
import com.lylechristine.socialnetwork.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface ChatMessageRepository extends JpaRepository<ChatMessage,Long>{
 @Query("select m from ChatMessage m where (m.sender=:a and m.recipient=:b) or (m.sender=:b and m.recipient=:a) order by m.sentAt")
 List<ChatMessage> conversation(@Param("a") String a,@Param("b") String b);
}
