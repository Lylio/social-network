package com.lylechristine.socialnetwork.repository;
import com.lylechristine.socialnetwork.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface NotificationRepository extends JpaRepository<Notification,Long>{
 List<Notification> findByRecipientUsernameOrderByCreatedAtDesc(String username);
 long countByRecipientUsernameAndReadFlagFalse(String username);
}
