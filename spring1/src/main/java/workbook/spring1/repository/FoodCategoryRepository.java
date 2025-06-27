package workbook.spring1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import workbook.spring1.domain.FoodCategory;

public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Long> {
}
