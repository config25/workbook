package workbook.spring1.service.FoodService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import workbook.spring1.repository.FoodCategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FoodCategoryServiceImpI implements FoodCategoryService{

    private final FoodCategoryRepository foodCategoryRepository;

    @Override
    public boolean isAllCategoriesExist(List<Long> categoryIds) {
        return categoryIds.stream().allMatch(foodCategoryRepository::existsById);
    }
}
