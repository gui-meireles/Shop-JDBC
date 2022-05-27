package br.com.nava.demo.controller;

import br.com.nava.demo.exceptions.BadRequestException;
import br.com.nava.demo.exceptions.NotFoundException;
import br.com.nava.demo.model.ProductModel;
import br.com.nava.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/")
    public List<ProductModel> listAll() {
        return productService.listAll();
    }

    @GetMapping("/{prdId}")
    public ProductModel getById(@PathVariable Integer prdId) throws NotFoundException {
        ProductModel product = productService.getById(prdId);
        return product;
    }

    @PostMapping("/")
    public void save(@RequestBody ProductModel product) throws BadRequestException {
            productService.save(product);
    }

    @PostMapping("/{prdId}")
    public void update(@RequestBody ProductModel product, @PathVariable Integer prdId) throws BadRequestException {
        productService.update(product, prdId);
    }

    @DeleteMapping("/{prdId}")
    public void delete(@PathVariable Integer prdId) throws NotFoundException {
        productService.delete(prdId);
    }
}
