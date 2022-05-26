package br.com.nava.demo.service;

import br.com.nava.demo.exceptions.BusinessException;
import br.com.nava.demo.model.CategoryModel;
import br.com.nava.demo.repository.CategoryDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService implements ICrudService<CategoryModel> {

    private final CategoryDAO categoryDAO;

    /**
     * Lists all category's
     * @return List of category's
     */
    @Override
    public List<CategoryModel> listAll() {
        return categoryDAO.listAll();
    }

    /**
     * Gets a category by it's ID
     * @param catId Category ID
     * @return Category matching ID
     */
    @Override
    public CategoryModel getById(Integer catId) {
        CategoryModel category = categoryDAO.getById(catId);
        if (category == null) {
            throw new BusinessException("Category not found!");
        }
        return category;
    }

    /**
     * Saves the given category
     * @param category Category to be saved
     */
    @Override
    public void save(CategoryModel category) {
        validateSave(category);
        categoryDAO.save(category);
    }

    /**
     * Updates the given category
     * @param category Category to be updated
     * @param catId Category ID to be updated
     */
    @Override
    public void update(CategoryModel category, Integer catId) {
        validateUpdate(category, catId);
        categoryDAO.update(category, catId);
    }

    /**
     * Deletes the product matching the given ID
     * @param catId Category ID
     */
    @Override
    public void delete(Integer catId) {
        categoryDAO.delete(catId);
    }

    /**
     * Validate category save
     * @param category validate save
     */
    private void validateSave(CategoryModel category) {
        if (category.getName() == null || category.getName().isEmpty()) {
            throw new BusinessException("Error when saving: The name field was not inserted!");
        }
    }

    /**
     * Validate category update
     * @param category
     * @param catId Category ID
     */
    private void validateUpdate(CategoryModel category, Integer catId) {
        if (category.getName() == null || category.getName().isEmpty()) {
            throw new BusinessException("Error when updating: The name field was not inserted!");
        }
        if (catId == null) {
            throw new BusinessException("Error when saving: The category Id field was not inserted!");
        }
    }
}
