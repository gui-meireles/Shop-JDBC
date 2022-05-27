package br.com.nava.demo.controller;

import br.com.nava.demo.exceptions.BadRequestException;
import br.com.nava.demo.exceptions.NotFoundException;
import br.com.nava.demo.model.ShopModel;
import br.com.nava.demo.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/shops")
public class ShopController {

    private final ShopService shopService;

    @GetMapping("/")
    public List<ShopModel> listAll() {
        return shopService.listAll();
    }

    @GetMapping("/{shpId}")
    public ShopModel getById(@PathVariable Integer shpId) throws NotFoundException {
        ShopModel shop = shopService.getById(shpId);
        return shop;
    }

    @PostMapping("/")
    public void save(@RequestBody ShopModel shop) throws BadRequestException {
        shopService.save(shop);
    }

    @PostMapping("/{shpId}")
    public void update(@RequestBody ShopModel shop, @PathVariable Integer shpId) throws BadRequestException {
        shopService.update(shop, shpId);
    }

    @DeleteMapping("/{shpId}")
    public void delete(@PathVariable Integer shpId) throws NotFoundException {
        shopService.delete(shpId);
    }
}
