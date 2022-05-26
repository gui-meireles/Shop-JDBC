package br.com.nava.demo.service;

import br.com.nava.demo.exceptions.BusinessException;
import br.com.nava.demo.model.ProductModel;
import br.com.nava.demo.repository.ProductDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService implements ICrudService<ProductModel> {

    private final ProductDAO productDAO;

    /**
     * Lists all products
     * @return List of products
     */
    @Override
    public List<ProductModel> listAll() {
        return productDAO.listAll();
    }

    /**
     * Gets a product by it's ID
     *
     * @param prdId Product ID
     * @return Product matching ID
     */
    @Override
    public ProductModel getById(Integer prdId) {
        ProductModel product = productDAO.getById(prdId);
        if (product == null){
            throw new BusinessException("Product not found!");
        }
        return product;
    }

    /**
     * Saves the given product
     * @param product Product to be saved
     */
    @Override
    public void save(ProductModel product) {
        validateSave(product);
        productDAO.save(product);
    }

    /**
     * Updates the given product
     * @param product Product to be updated
     * @param prdId Product ID to be updated
     */
    @Override
    public void update(ProductModel product, Integer prdId) {
        validateUpdate(product, prdId);
        productDAO.update(product, prdId);
    }

    /**
     * Deletes the product matching the given ID
     * @param prdId Product ID
     */
    @Override
    public void delete(Integer prdId) {
        productDAO.delete(prdId);
    }

    /**
     * Validate product save
     * @param product validate save
     */
    public void validateSave(ProductModel product) {
        if (product.getName() == null || product.getName().isEmpty()) {
            throw new BusinessException("Error when saving: The name field was not inserted!");
        }
        if (product.getDescription() == null || product.getDescription().isEmpty()) {
            throw new BusinessException("Error when saving: The description field was not inserted!");
        }
        if (product.getCategory().getCatId() == null) {
            throw new BusinessException("Error when saving: The category Id field was not inserted!");
        }
    }

    /**
     * Validate product update
     * @param product
     * @param prdId Product ID
     */
    public void validateUpdate(ProductModel product, Integer prdId) {
        if (product.getName() == null || product.getName().isEmpty()) {
            throw new BusinessException("Error when updating: The name field was not inserted!");
        }
        if (product.getDescription() == null || product.getDescription().isEmpty()) {
            throw new BusinessException("Error when updating: The description field was not inserted!");
        }
        if (product.getCategory().getCatId() == null) {
            throw new BusinessException("Error when updating: The category Id field was not inserted!");
        }
        if (prdId == null) {
            throw new BusinessException("Error when updating: The product Id field was not inserted!");
        }
    }
}
