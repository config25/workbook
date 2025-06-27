package workbook.spring1.repository.Review;

import org.springframework.data.jpa.repository.JpaRepository;
import workbook.spring1.domain.mapping.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {


}
