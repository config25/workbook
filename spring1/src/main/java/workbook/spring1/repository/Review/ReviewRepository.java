package workbook.spring1.repository.Review;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import workbook.spring1.domain.Store;
import workbook.spring1.domain.User;
import workbook.spring1.domain.mapping.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findAllByStore(Store store, PageRequest pageRequest);
    Page<Review> findAllByUser(User user, PageRequest pageRequest);
}
