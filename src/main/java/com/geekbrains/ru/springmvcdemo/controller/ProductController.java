package com.geekbrains.ru.springmvcdemo.controller;

import com.geekbrains.ru.springmvcdemo.domain.ProductEntity;
import com.geekbrains.ru.springmvcdemo.service.CategoryService;
import com.geekbrains.ru.springmvcdemo.service.ProductService;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    private final Validator validator;

    @GetMapping
    public String getProducts(@RequestParam(value = "pageNum", required = false) Integer pageNum,
                              @RequestParam(value = "minPrice", required = false) Integer minPrice,
                              @RequestParam(value = "maxPrice", required = false) Integer maxPrice,
                              @RequestParam(value = "title", required = false) String title,
                              Model model) {
        final int pageSize = 5;
        Page<ProductEntity> page;
        Pageable pageRequest = PageRequest.of(pageNum == null ? 0 : pageNum, pageSize);
        if (minPrice == null) minPrice = 0;
        if (maxPrice == null) maxPrice = 0;

        if (minPrice == 0 && maxPrice == 0 && title == null){
            page = productService.findAllByPage(pageRequest);
        }else if(title != null){
            page = productService.findByTitle(title, pageRequest);
        }
        else {
            page = productService.findAllByPriceLessThanEqualAndPriceGreaterThanEqual(maxPrice, minPrice, pageRequest);
        }

        model.addAttribute("page", page);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        model.addAttribute("title", title);

        return "product/list";
    }

    @GetMapping("/form")
    public String addProduct(@RequestParam(value = "id", required = false) Long productId, Model model,
                             @ModelAttribute(value = "violations") String violations) {

        if (productId != null) {
            ProductEntity product = productService.findById(productId);
            model.addAttribute("product", product);
        } else {
            model.addAttribute("product", new ProductEntity());
        }

        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("values", new ArrayList<>(Arrays.asList("asd", "asd")));
        model.addAttribute("violations", violations);

        return "product/form";
    }

    @PostMapping
    public RedirectView saveProduct(ProductEntity product,
                                    @ModelAttribute("values") ArrayList<String> values,
                                    @RequestParam(value = "image", required = false) MultipartFile image,
                                    RedirectAttributes attributes) {

        Set<ConstraintViolation<ProductEntity>> violationResult = validator.validate(product);
        if (CollectionUtils.isNotEmpty(violationResult)) {
            String violations = violationResult.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining("\n"));

            attributes.addFlashAttribute("violations", violations);

            return new RedirectView("/product/form");
        }

        productService.saveWithImage(product, image);

        return new RedirectView("/product?pageNum=0");
    }

}
