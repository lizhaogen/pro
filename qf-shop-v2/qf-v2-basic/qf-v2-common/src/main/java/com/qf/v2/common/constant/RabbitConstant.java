package com.qf.v2.common.constant;

public interface RabbitConstant {
    String PRODUCT_WEB_EXCHANGE = "product_web_exchange";

    String PRODUCT_ITEM_SAVE_UPDATE_QUEUE = "product_item_save_update_queue";

    String PRODUCT_SEARCH_SAVE_UPDATE_QUEUE = "product_search_save_update_queue";

    String ITEM_SERVICE_ADD_ROUTING_KEY = "product.add";

    String SEARCH_SERVICE_ADD_ROUTING_KEY = "productResult.add";

    String EMAIL_USER_REGIST_QUEUE = "email_user_regist_queue";

    String EMAIL_REGIST_EXCHANGE = "email_regist_exchange";

    String EMAIL_REGIST_USER_ROUTING_KEY = "email_regist_user_routing_key";
}
