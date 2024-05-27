package com.pay.demo;

import org.junit.jupiter.api.Test;


public class ApiTest {

    /**
     * private key, please keep it in a safe place
     */
    public static String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCP0P0KDjcHuI+Bhzebw/JDV1ZclAZbbAxtv9VFqhZ4dCbxd00tZoMgi8EI7m7LF4VJSeY/XeHsWjCflVyGYRfVJ2eCa+o0ModP2EJlZnVHFTjfKg1az3QILw60LhcAxcCi+UD7nax6CMZQgCsgsbwaDe+YrIgGE7eg/ti6JU8hi7i6m3GwqXc9+qH6MGoCwohxv1erg3wrK9vuDC9kBSKWvnolr0cgIsV5mxSgkmJoJdIbPbDVlTehFCfPnsO5xsThWfw5kPQIxtqkm7i762eUT1TsUqYJFabhgNKKsJsljSEo0lEDy9vwsGxXBj+XFRRdOTozUHHT+jKYnTRPACv/AgMBAAECggEAEzQVxbT2Niq3xtT2YO8+Ny2/U2dUnfqEglceNEos6/KejJmgjZJlqf/fS8ECvE2st4eNniJ8G2JDoduA2lS2qPi1Ap5ETKn+lXPMEdwnhtFXobzswZZ36OvXq8mHusutGFXuDmsUb8siJ/elSp1Q/62is6E+HImfeHjvGZQHx/O7+MdvbYNNn5EchomxDiRa8iHiF9Nrc56Vckjk3XcuCDH3eZMRghiWbii3IzYzG9ETEpK8S1gWKaYUWZGSIKvHz3k4W05/+WF98w72B4S78xjxa8SuRJgoeE5U0qoJ1UrX5CvaOOWuuGknwhzstEuMWNhZ6V/GdwZR9lz9l/p4sQKBgQDFR8t1+8ncM1Aa5jjcI+/CoW9bdf17VJBWyXAP/sJyHC6XtaP0yT7GQrjC8t7PPtPwBQnmnSD6ZTKbI0+Br9Dj4jsimnaL0EfJyDlcu6j7etCoOQSVDlw8w1T4RE3J+mqrBHNNEcxLUNVArPBPWpwN8lb7R1PKIULrg9acm7c9+QKBgQC6n2IhEK4ENn7+y3hA6JUD7z0cIivzs8Pjbo2yNbjFvDyjSsnKa+RcoRIy59kgmmFkEliuzrDAH4br2JKq6B3TU94sxk60ZqypsiTz/V7I5cb5jv99U9GigW9s1iwoCugJSctUqfLwQJwBtOO+hxADr3aoTVLP7Y02CRNbFl2XtwKBgQDCrfvBHUzxaQ22zdP0od37glWiuwf+yc96ZWSZ1DzMYLU17wCyElpJShSMBSINACIjbMV9dzfRAUZ0Q980ymxoRZs3pZgwlsQRAu5gbavvJx57s3CrKzWonNXf/X+KPv0+cLDbsCGbfVREc6Tdmjv/o1Nkutmb0UD5quuBNkUY+QKBgDr/E9h8G2b9i1wlGpj6bdFWmi0AqIBcPfryAh1qWkU4YrsEc5JoVULrMIOjQ8LIyy0Fl797W+kAjniUeJlK09Lw2nWxI0RoiQEEbiYr3QEJksNl72LBUq5a2MzBUChAemYlTAAx0bkd07O+aZjbvbZMi+hcuWc+I7wHVnUfNus1AoGAPrtLMEom1lTCFiboxirHMHCQOYJPPSZxGuYjwJ7WC++GeEt3SSEDPE470dw3lecJ6cmpQOQla5hppqUMAVefgyDuxwl+eo0b8PqFtehvRh5ME9aPQFZJbD5i1vyADbPs1OF6UwlfsFP6bSdVBg9bW6MBihB5eDu4Dn6P+gLN6D4=";
    /**
     * platform public key, please download from pay partner dashboard
     */
    public static String platformKey =
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAh0PH7EgkGmlnImO5AQxoPT6970lRgHq3zMQAoSGrK7JUgAuRWHwSrzTVulnTy5W5V1JGUSZ0nUeq3Pk7qS808x9nMQgBniKqdsk4izpCG7ogdklr1BoevOw5+eXkyzNM6ydsB5qllI413p9lBFJ3qJkVUA0IC/rXpYBsvbSDHPoVAaKFmEGpM6qvgwbTTFFCaQvWqHLuZpgSY+knFzsuOzzfCr6JBQ2X1xoJZ0DviIBdc3BoomUP84879EShBthNaYqNYE5pJKNvoDIiTPsA/iZAJnCixXlshC6kn8fT6Y48YOFjnhNaBISfa9QKD+waxfJ8lNdPt2I8pvWxew6yEwIDAQAB";
    private final String baseUrl = "https://api.test.topay.mobi/v1/"; // todo

    private final PayClient client = PayClient.build(baseUrl, privateKey, platformKey);

    @Test
    public void queryPartnerBalance() {
        String respStr = client.queryPartnerBalance("USDT_TRC20",
                "2000051",
                String.valueOf(System.currentTimeMillis()));
        System.out.println(respStr);
    }

    @Test
    public void depositAddress() {
        String respStr = client.queryDepositAddress("TRX",
                "C100001",
                "desc",
                "2000051",
                String.valueOf(System.currentTimeMillis()));
        System.out.println(respStr);
    }


    // 查询
    @Test
    public void query() {
        String respStr = client.queryTxnStatus("1660903745906",
                "2022081920000600086100906100",
                "2000051",
                String.valueOf(System.currentTimeMillis()));

        System.out.println(respStr);
    }

    // 提币
    @Test
    public void withdrawCoin() {
        String respStr = client.withdrawCoin(String.valueOf(System.currentTimeMillis()),
                "BNB_BSC",
                "0xADFB656a8D0c0D22c523404c23699f9299975216",
                null,
                "0.3",
                "C100001",
                null,
                "desc",
                "2000051",
                String.valueOf(System.currentTimeMillis()));

        System.out.println(respStr);
    }

    // 提币
    @Test
    public void pay() {
        String respStr = client.pay(String.valueOf(System.currentTimeMillis()),
                "USDT_TRC20",
                "30",
                "12345@qq.com",
                null,
                "2000051",
                String.valueOf(System.currentTimeMillis()),
                "on_line",
                null,
                null,
                null,
                null);

        System.out.println(respStr);
    }

    @Test
    public void payout() {
        String respStr = client.payout(String.valueOf(System.currentTimeMillis()),
                "USDT_TRC20",
                "2",
                null,
                "BR",
                "2000051",
                String.valueOf(System.currentTimeMillis()),
                "BRL",
                "CPF",
                "xxx",
                "wallet_transfer",
                "pix",
                null,
                "21312",
                "123@qq.com",
                "test",
                "CPF",
                "xxx",
                "HDFC0000128"
        );

        System.out.println(respStr);
    }

    @Test
    public void verifyNotify(){
        String body = "{\n" +
                "    \"actual_amount\": \"980000.000000000000000000\",\n" +
                "    \"currency\": \"VND\",\n" +
                "    \"customer_ref_id\": \"10000\",\n" +
                "    \"extra_info\": \"{\\\"channel\\\": \\\"truepay_vnd\\\", \\\"payType\\\": \\\"bank_acct_pay\\\", \\\"productName\\\": \\\"coin\\\", \\\"customerRefId\\\": \\\"10000\\\", \\\"paymentMethod\\\": \\\"direct\\\", \\\"beneficiaryBankCode\\\": \\\"1000403\\\"}\",\n" +
                "    \"fee_amount\": \"20000.000000000000000000\",\n" +
                "    \"finish_time\": \"1712113755000\",\n" +
                "    \"order_amount\": \"1000000.000000000000000000\",\n" +
                "    \"order_no\": \"2024040313000600701011213674\",\n" +
                "    \"order_type\": \"pay\",\n" +
                "    \"partner_id\": \"2000109\",\n" +
                "    \"product_code\": \"m_pay_invoke\",\n" +
                "    \"request_id\": \"10000_20240403091211409\",\n" +
                "    \"settle_currency\": \"VND\",\n" +
                "    \"sign\": \"VXvFGsFZyWq3L+nO2BVyznB1f1e5yCsJTgXWxqg9i+LqfjRKdOQ4+Jez0vBquBXuddzHWkO/1uu2hftu4GioZSFhPjRaH6MmrDV8qTQWGaxt4iZN1OAjuRdzt/b0g3S/qke86DmC7MBlPjIL4gkEs0ByI5bkqzYn1Ec0sX2D2H4uTGqrEa84oHT4PkT+ImW8qqjXQQLxJrcgv85y73nIVGhFpR7+cxW/F3V3S7buH0h1sDfutBJmJSF1DYZpVKB9Jbp/eTyVEPq37ytwW/+egdc2+KaS+ZUxzStIIkOd+zKyVzT2zy8L9LWdH6zHwilTxsC6ciN0qIJTlpGq6arEmQ==\",\n" +
                "    \"status\": 99\n" +
                "}";
        boolean signOk = client.verifyNotify(body);
        System.out.println(signOk);
    }

    @Test
    public void estimate(){
        String respStr = client.estimate("USDT_BSC",
                "100",
                "2000051",
                String.valueOf(System.currentTimeMillis()));
        System.out.println(respStr);
    }

    @Test
    public void verifyDepositAddress(){
        String respStr = client.verifyDepositAddress("BTC_TEST",
                "n2iXfkTK1djWmBc9j6w8r2KkW59BQyikuX",
                "",
                "2000051",
                String.valueOf(System.currentTimeMillis()));
        System.out.println(respStr);
    }

    @Test
    public void queryTradeRate(){
        String respStr = client.queryTradeRate("USDT_TRC20",
                "IDR",
                "2000051",
                String.valueOf(System.currentTimeMillis()));
        System.out.println(respStr);
    }

    @Test
    public void queryPayoutChannel(){
        String respStr = client.queryPayoutChannel("ID",
                "IDR",
                "2000051",
                String.valueOf(System.currentTimeMillis()));
        System.out.println(respStr);
    }
}
