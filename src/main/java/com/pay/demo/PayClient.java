package com.pay.demo;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pay.demo.dto.*;
import com.pay.demo.utils.OkHttpSSL;
import com.pay.demo.utils.OkHttpUtils;
import com.pay.demo.utils.SignUtils;
import lombok.SneakyThrows;
import okhttp3.OkHttpClient;

public class PayClient {

    private String merchantPrivateKey;

    private String platformPublicKey;

    private String baseUrl;

    private OkHttpClient httpClient;

    private void PayClient() {

    }

    public static PayClient build(String baseUrl, String merchantPrivateKey, String platformPublicKey) {
        PayClient client = new PayClient();
        client.baseUrl = baseUrl;
        client.merchantPrivateKey = merchantPrivateKey;
        client.platformPublicKey = platformPublicKey;
        client.httpClient = new OkHttpClient();
        return client;
    }

    @SneakyThrows
    public static PayClient buildNoSSL(String baseUrl, String merchantPrivateKey, String platformPublicKey) {
        PayClient client = new PayClient();
        client.baseUrl = baseUrl;
        client.merchantPrivateKey = merchantPrivateKey;
        client.platformPublicKey = platformPublicKey;
        client.httpClient = new OkHttpClient.Builder()
                .sslSocketFactory(OkHttpSSL.getIgnoreInitedSslContext().getSocketFactory(),OkHttpSSL.IGNORE_SSL_TRUST_MANAGER_X509)
                .hostnameVerifier(OkHttpSSL.getIgnoreSslHostnameVerifier())
                .build();
        return client;
    }

    /**
     * 查询充币地址
     * @param currency
     * @param customer_ref_id
     * @param partner_id
     * @param nonce
     * @return
     */
    public String queryDepositAddress(String currency, String customer_ref_id, String description,
                                      String partner_id, String nonce) {
        ChargeAddressQueryRequest request = new ChargeAddressQueryRequest();
        request.setCurrency(currency);
        request.setCustomer_ref_id(customer_ref_id);
        request.setDescription(description);

        request.setPartner_id(partner_id);
        request.setSign_type("RSA");
        request.setTimestamp(String.valueOf(System.currentTimeMillis()));
        request.setNonce(nonce);

        SignUtils.sign(request, merchantPrivateKey);

        return OkHttpUtils.doPost(httpClient, baseUrl + "order/deposit_address",
                JSON.toJSONString(request));
    }


    /**
     * 查询订单状态
     * @param request_id
     * @param order_no
     * @param partner_id
     * @param nonce
     * @return
     */
    public String queryTxnStatus(String request_id, String order_no,
                                 String partner_id, String nonce) {
        QueryOrderRequest request = new QueryOrderRequest();
        request.setRequest_id(request_id);
        request.setOrder_no(order_no);

        request.setPartner_id(partner_id);
        request.setSign_type("RSA");
        request.setTimestamp(String.valueOf(System.currentTimeMillis()));
        request.setNonce(nonce);

        SignUtils.sign(request, merchantPrivateKey);

        return OkHttpUtils.doPost(httpClient, baseUrl + "order/query",
                JSON.toJSONString(request));
    }

    /**
     * 发送提币交易
     * @param request_id
     * @param currency
     * @param address
     * @param amount
     * @param customer_ref_id
     * @param notify_url
     * @param partner_id
     * @param nonce
     * @return
     */
    public String withdrawCoin(String request_id, String currency, String address, String tag, String amount, String customer_ref_id, String notify_url, String description,
                               String partner_id, String nonce) {
        ExtractOrderRequest request = new ExtractOrderRequest();
        request.setRequest_id(request_id);
        request.setCurrency(currency);
        request.setAddress(address);
        request.setTag(tag);
        request.setAmount(amount);
        request.setCustomer_ref_id(customer_ref_id);
        request.setNotify_url(notify_url);
        request.setDescription(description);

        request.setPartner_id(partner_id);
        request.setSign_type("RSA");
        request.setTimestamp(String.valueOf(System.currentTimeMillis()));
        request.setNonce(nonce);

        SignUtils.sign(request, merchantPrivateKey);

        return OkHttpUtils.doPost(httpClient, baseUrl + "order/withdraw",
                JSON.toJSONString(request));
    }

    /**
     * 发送提币交易
     * @param request_id
     * @param currency
     * @param amount
     * @param notify_url
     * @param partner_id
     * @param nonce
     * @return
     */
    public String pay(String request_id, String currency,  String amount, String email, String notify_url,
                      String partner_id, String nonce,
                      String payment_method,
                      String payment_type, String payment_channel, String customer_ref_id, Customer customer) {
        PayOrderRequest request = new PayOrderRequest();
        request.setRequest_id(request_id);
        request.setCurrency(currency);
        request.setAmount(amount);
        request.setNotify_url(notify_url);
        request.setPayment_method(payment_method);
        request.setProduct_name("virtual product");
        request.setRemark("virtual product");
        request.setEmail(email);
        request.setPartner_id(partner_id);
        request.setSign_type("RSA");
        request.setTimestamp(String.valueOf(System.currentTimeMillis()));
        request.setNonce(nonce);
        request.setReturn_url("http://www.baidu.com");

        request.setPayment_type(payment_type);
        request.setPayment_channel(payment_channel);
        request.setCustomer_ref_id(customer_ref_id);
        request.setCustomer(customer == null ? null:JSONObject.toJSONString(customer));

        SignUtils.sign(request, merchantPrivateKey);

        return OkHttpUtils.doPost(httpClient, baseUrl + "order/pay",
                JSON.toJSONString(request));
    }

    public String payout(String request_id, String currency,  String amount,  String notify_url, String country,
                         String partner_id, String nonce,String settleCurrency, String account_type, String accountNumber,String method,
                         String walletCode, String bankCode,String phone, String email, String name,
                         String document_type, String document_id, String bank_routing_code) {
        PayoutOrderRequest request = new PayoutOrderRequest();
        request.setRequest_id(request_id);
        request.setCurrency(currency);
        request.setAmount(amount);
        request.setSettle_currency(settleCurrency);
        request.setNotify_url(notify_url);
        request.setRemark("virtual product");
        request.setPartner_id(partner_id);
        request.setSign_type("RSA");
        request.setTimestamp(String.valueOf(System.currentTimeMillis()));
        request.setNonce(nonce);

        request.setPayout_method(method);
        request.setAccount_no(accountNumber);
        request.setAccount_type(account_type);
        request.setCountry(country);
        request.setWallet_code(walletCode);
        request.setBank_code(bankCode);
        request.setPhone(phone);
        request.setEmail(email);
        request.setAccount_name(name);

        request.setDocument_type(document_type);
        request.setDocument_id(document_id);

        request.setBank_routing_code(bank_routing_code);

        SignUtils.sign(request, merchantPrivateKey);

        return OkHttpUtils.doPost(httpClient, baseUrl + "order/payout",
                JSON.toJSONString(request));
    }

    /**
     * 查询商户余额
     * @param currency
     * @param partner_id
     * @param nonce
     * @return
     */
    public String queryPartnerBalance(String currency,
                                      String partner_id, String nonce) {
        QueryPartnerBalanceRequest request = new QueryPartnerBalanceRequest();
        request.setCurrency(currency);

        request.setPartner_id(partner_id);
        request.setSign_type("RSA");
        request.setTimestamp(String.valueOf(System.currentTimeMillis()));
        request.setNonce(nonce);

        SignUtils.sign(request, merchantPrivateKey);

        return OkHttpUtils.doPost(httpClient, baseUrl + "balance/partner",
                JSON.toJSONString(request));
    }

    public boolean verifyNotify(String body){
        JSONObject bodyObj = JSONObject.parseObject(body);
        String sign = bodyObj.getString("sign");
        String content = SignUtils.assembleContent(bodyObj);

        VerifySignRequest verifySignRequest = new VerifySignRequest();
        verifySignRequest.setSign(sign);
        verifySignRequest.setCharset("UTF-8");
        verifySignRequest.setKeyType("RSA");
        verifySignRequest.setPublicKey(platformPublicKey);
        verifySignRequest.setContent(content);
        VerifySignResponse response = SignUtils.verifySign(verifySignRequest);
        return response.getSignOk();
    }

    public String estimate(String currency, String amount,
                           String partner_id, String nonce){
        FeeEstimateRequest request = new FeeEstimateRequest();
        request.setCurrency(currency);
        request.setAmount(amount);

        request.setPartner_id(partner_id);
        request.setSign_type("RSA");
        request.setTimestamp(String.valueOf(System.currentTimeMillis()));
        request.setNonce(nonce);

        SignUtils.sign(request, merchantPrivateKey);

        return OkHttpUtils.doPost(httpClient, baseUrl + "fee/estimate",
                JSON.toJSONString(request));
    }

    public String verifyDepositAddress(String currency, String address, String tag,
                                       String partner_id, String nonce) {
        VerifyDepositAddressRequest request = new VerifyDepositAddressRequest();
        request.setCurrency(currency);
        request.setAddress(address);
        request.setTag(tag);

        request.setPartner_id(partner_id);
        request.setSign_type("RSA");
        request.setTimestamp(String.valueOf(System.currentTimeMillis()));
        request.setNonce(nonce);

        SignUtils.sign(request, merchantPrivateKey);

        return OkHttpUtils.doPost(httpClient, baseUrl + "order/verifyDepositAddress",
                JSON.toJSONString(request));
    }

    public String queryTradeRate(String baseCurrency, String quoteCurrency,
                                 String partner_id, String nonce) {
        QueryRateRequest request = new QueryRateRequest();
        request.setBaseCurrency(baseCurrency);
        request.setQuoteCurrency(quoteCurrency);

        request.setPartner_id(partner_id);
        request.setSign_type("RSA");
        request.setTimestamp(String.valueOf(System.currentTimeMillis()));
        request.setNonce(nonce);

        SignUtils.sign(request, merchantPrivateKey);

        return OkHttpUtils.doPost(httpClient, baseUrl + "rate/queryTradeRate",
                JSON.toJSONString(request));
    }

    public String queryPayoutChannel(String country, String currency,
                                     String partner_id, String nonce) {
        PayoutChannelRequest request = new PayoutChannelRequest();
        request.setCountry(country);
        request.setCurrency(currency);

        request.setPartner_id(partner_id);
        request.setSign_type("RSA");
        request.setTimestamp(String.valueOf(System.currentTimeMillis()));
        request.setNonce(nonce);

        SignUtils.sign(request, merchantPrivateKey);

        return OkHttpUtils.doPost(httpClient, baseUrl + "fee/queryPayoutChannel",
                JSON.toJSONString(request));
    }
}
