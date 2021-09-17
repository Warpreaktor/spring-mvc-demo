package com.geekbrains.ru.springmvcdemo.service.impl;

import com.geekbrains.ru.springmvcdemo.domain.CategoryEntity;
import com.geekbrains.ru.springmvcdemo.domain.ProductEntity;
import com.geekbrains.ru.springmvcdemo.domain.ProductSearchCondition;
import com.geekbrains.ru.springmvcdemo.repository.ProductRepository;
import com.geekbrains.ru.springmvcdemo.service.CategoryService;
import com.geekbrains.ru.springmvcdemo.service.ProductService;
import com.geekbrains.ru.springmvcdemo.util.FileUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final CategoryService categoryService;

    @Override
    public List<ProductEntity> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Page<ProductEntity>  findAllByPriceLessThanEqual(Integer maxPrice, Pageable pageable){
        return productRepository.findAllByPriceLessThanEqual(maxPrice, pageable);
    }
    @Override
    public Page<ProductEntity>  findAllByPriceGreaterThanEqual(Integer minPrice, Pageable pageable){
        return productRepository.findAllByPriceLessThanEqual(minPrice, pageable);
    }

    @Override
    public Page<ProductEntity> findAllByPageAndCategory(Pageable pageable, String categoryAlias) {
        if (StringUtils.isNotBlank(categoryAlias)){
            CategoryEntity category = categoryService.findByAlias(categoryAlias);
            return productRepository.findAllByCategories(category, pageable);
        }
        return productRepository.findAll(pageable);
    }

    @Override
    public Page<ProductEntity>  findAllByPriceLessThanEqualAndPriceGreaterThanEqual(Integer maxPrice, Integer minPrice, Pageable pageable){
        if (minPrice < 0 ) minPrice = 0;
        if (minPrice > maxPrice ) minPrice = maxPrice;
        if (maxPrice < 0 ) maxPrice = 0;
        if (maxPrice < minPrice ) maxPrice = minPrice;

        if (maxPrice > 0) {
            return productRepository.findAllByPriceLessThanEqualAndPriceGreaterThanEqual(maxPrice, minPrice, pageable);
        }else{
            return productRepository.findAllByPriceGreaterThanEqual(minPrice, pageable);
        }
    }

    @Override
    public Page<ProductEntity> findAllBySearchCondition(ProductSearchCondition searchCondition){
        Pageable pageRequest = PageRequest.of(
                searchCondition.getPageNum(),
                searchCondition.getPagesSize(),
                Sort.by(searchCondition.getSortDirection(), searchCondition.getSortField()));

        return productRepository.findAll(pageRequest);
    }

    @Override
    @Transactional
    public ProductEntity findById(long id) {
        return productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Page<ProductEntity> findByTitle(String name, Pageable pageable){
        name = "%" + name + "%";
        return productRepository.findByTitle(name, pageable);
    }

    @Override
    public ProductEntity save(ProductEntity product) {
        return productRepository.save(product);
    }

    @Override
    public Page<ProductEntity> findAllByPage(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public ProductEntity saveWithImage(ProductEntity product, MultipartFile image) {
        ProductEntity savedProduct = productRepository.save(product);

        if (image != null && !image.isEmpty()) {
            Path pathImage = FileUtils.saveProductImage(image);
            savedProduct.setImageLink(pathImage.toString());

            productRepository.save(savedProduct);
        }
        return savedProduct;
    }

    @Override
    @Transactional
    public ProductEntity deleteById(Long id){
        ProductEntity product = findById(id);
        productRepository.deleteById(id);
        return product;
    }


}
