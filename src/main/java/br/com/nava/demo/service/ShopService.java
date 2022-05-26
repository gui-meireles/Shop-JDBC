package br.com.nava.demo.service;

import br.com.nava.demo.exceptions.BusinessException;
import br.com.nava.demo.model.ShopModel;
import br.com.nava.demo.repository.ShopDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ShopService implements ICrudService<ShopModel> {

    private final ShopDAO shopDAO;

    /**
     * Lists all shops
     *
     * @return List of shops
     */
    @Override
    public List<ShopModel> listAll() {
        return shopDAO.listAll();
    }

    /**
     * Gets a shop by it's ID
     *
     * @param shpId Shop ID
     * @return Shop matching ID
     */
    @Override
    public ShopModel getById(Integer shpId) {
        ShopModel shop = shopDAO.getById(shpId);
        if (shop == null) {
            throw new BusinessException("Shop not found!");
        }
        return shop;
    }

    /**
     * Saves the given shop
     *
     * @param shop Shop to be saved
     */
    @Override
    public void save(ShopModel shop) {
        validateSave(shop);
        shopDAO.save(shop);
    }

    /**
     * Updates the given shop
     *
     * @param shop Shop to be updated
     * @param shpId Shop ID to be updated
     */

    public void update(ShopModel shop, Integer shpId) {
        validateUpdate(shop, shpId);
        shopDAO.update(shop, shpId);
    }

    /**
     * Deletes the shop matching the given ID
     *
     * @param id Shop ID
     */
    @Override
    public void delete(Integer id) {
        shopDAO.delete(id);
    }

    /**
     *  Validate shop save
     *
     * @param shop validate save
     */
    private void validateSave(ShopModel shop) {
        if (shop.getName() == null || shop.getName().isEmpty()) {
            throw new BusinessException("Error when saving: The name field was not inserted!");
        }
        if (shop.getAddress() == null || shop.getAddress().isEmpty()) {
            throw new BusinessException("Error when saving: The address field was not inserted!");
        }
    }

    /**
     *  Validate shop update
     *
     * @param shop
     * @param shpId Shop ID
     */
    private void validateUpdate(ShopModel shop, Integer shpId) {
        if (shop.getName() == null || shop.getName().isEmpty()) {
            throw new BusinessException("Error when updating: The name field was not inserted!");
        }
        if (shop.getAddress() == null || shop.getAddress().isEmpty()) {
            throw new BusinessException("Error when updating: The address field was not inserted!");
        }
        if (shpId == null) {
            throw new BusinessException("Error when updating: The shop Id field was not inserted!");
        }
    }
}
