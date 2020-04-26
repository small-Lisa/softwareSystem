package com.alipay.config;
import java.io.FileWriter;
import java.io.IOException;

public class AlipayConfig {
    public static String app_id = "2016101500692365";
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCEov7NN0PInEhMu6C/xR/NBW/0Qa6VFj8LqHsaevioKf34HuLzT74Rftd3ZLqK6T3ZQU3wzeyOR1IZFy9LWNy/vIn3/XbkrAcCzDZb3i4BaZLrRRt0KZMqhHYrsKaL4OO4uN1VsJt6PChCH2BSIsXNRn0mSv1185ZTHsOO0oujFKkZ0Br65qdwCQVWbZFJqC2ZgfGQ9DwjXzmQ0ZiVrgsG0DDBUQdUyVGVI/KpYg6Zi0Ci/jhWMPtT+RXd516og5HSIvAaRKcYiQx1SOKwvaCTvXuI6epyBzIBuRrJ/qTlQojAqg983J11P686C6n4WNOp0IOjh321ok9DOaZoVKqVAgMBAAECggEAZmuzFGa7ifrcfZxZWhxvHRM4y0W4+VkPYAI+5UjWII0kl2fKWpZWMA5hdcmrnr7TSqN5+xwYshly1PspQyagd9KwO3ZXS+dnFor44uHjZqXOlKxtsbq08J0O31fuk48aabce+x+AP9iaQc0WxPYjBguLcnlv1Zg+mXrlCbGdsFSMz1WhuOCNMaaXEGsOKfnHurEdt2hY/drH1a14JfSNL/SB7ktvEXbJzBWwyrsLv6Qu7PP1ABG98GHZbhNN+jgz3Eg4dWDbZeu/2MSQqzMzUe5UKUdaT5Q60Ni48GgVgTd8x2QiYHJHw3hjaaadm8/h18bfJBwoZxJaSkGIGaLatQKBgQDnzOko4Q0tBdkGfIOa3PJxHjOAhk0SZo4rFqKfudNQiN4veUOExRDLSJSMREBow6cceiio84hwZe3c6SE/GTr72uCO2ULuwGrruc72W5867ahfla35DtY5UlsEAGv9cIe67UDPa/yzboeiOYAjcIRQLtqmx9/PxDBSJQ4fDWCgJwKBgQCSe9aquMI2n6XSVJ6XfCJCeJ7/Xg8E1tWHqJROf6H+5Y6QWCrnVDo7BdlGt03RJMe8Ae3ggTlCPgvmuqrRFpWu8ApP0vXoz8m4r0XaWGhQyqHuAUTmERKOpDXreuv78/+v1DnbhgXwQnr4C1CKgnC0TrrDfo4i1K7xYkefUSUY4wKBgQCOfwlWeAMBina/5guBSBJCN66xc94tUZ5UHrYaOsIM4Ba2WHWJSRvp29R15f8yaBRzDSWxtwRJFGAFTcuCXqBD82nYZvcejmssPi1chTZ9o1u+ZuVsFhLbaiVJcHjydBr8bp1I5bmTFH+M2oxLPQAZyrPfkz+jGZga52ym6jJcQQKBgEGObqoE+dYcaMfS2w8MnQCGnxHGdI5D31hRF95xvHwpHD55VuokhdL4cR721jqtbT401tLMwmVlA0qFbAEbTG34O4Ux8p/4rlybBHYNbtIJfaLYqo8UQOvZjdAPOSGPN3TnU+Vs+6dk+UedOLRkYDAxs9/d+ZqY+JpK06b3V9TlAoGBAI7qmsMSWzqvsnUWGvVDsfibxB43KgP0+0ZTNML+J5cACFyeBrha8/OKDKfaqu/UgkA6qgpWAwC6qa2gdZdju76BlitkBoBqV7qbr/IwPg414lJdIv1pesP8wZlRlqK/lYUdCcS/sRkA3BcMJQcZTZl/yRG8IWM/LALcZv7/uBpJ";
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAn9Ilnyo+s5S8Fkexs1VMuMQrRxlIg3i83MtxNjQtikljVLvlQ1bW0WqUruVe3pcKoyL5iTIbpfuv9MDn+pOUjGo/zOafbkcPQUJkUOA3duUNrjxRYLLqyt2ICmN5qFmjzcPkwEjoX4gM1+qJF6vbfvUVq2sS4fQdKz/FfbFdKA8RItxB556xzP3llAJNftfnatUcSr3R/fueVFo4UHLjEozjZeK3PeVWxC1rwXlQy6mOISkx4M05Imkw1qoB3y7cSDN+kB7AZYAJ4LpntujZs72v3VtCKnU9lz/QhhNs32nztn6Dtn/++wrX+Wh24+lrDtUuV3t7m5+4NeRSBnds1wIDAQAB";
    public static String notify_url = "http://127.0.0.1:8080/software/notify_url.jsp";
    public static String return_url = "http://127.0.0.1:8080/LisasasaSoftware/return_url.jsp";
    public static String sign_type = "RSA2";
    public static String charset = "utf-8";
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
    public static String log_path = "C:\\";

    public AlipayConfig() {
    }

    public static void logResult(String sWord) {
        FileWriter writer = null;

        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis() + ".txt");
            writer.write(sWord);
        } catch (Exception var11) {
            var11.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException var10) {
                    var10.printStackTrace();
                }
            }

        }

    }
}