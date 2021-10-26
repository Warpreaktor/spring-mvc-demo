package com.geekbrains.ru.springmvcdemo.domain.constant;

public class RequestNameConstant {
    //REST API
    public final static String API_V1 = "/api/v1";

    //Общие имена
    public final static String HOME_PAGE = "/";
    public final static String ADD = "/add";


    //Товары и категории
    public final static String PRODUCT = "/product";
    public final static String PRODUCT_FORM = "/form";
    public final static String CATEGORY = "/category";

    //Корзина покупателя
    public final static String CART = "/cart";
    public final static String BUY = "/buy";

    //админская панель
    public final static String ADMIN = "/admin";
    public final static String USERS = "/users";
    public final static String LOCK_USER = "/lock-user";
    public final static String DISABLE_USER = "/disable-user";
    public final static String ENABLE_USER = "/enable-user";
}
