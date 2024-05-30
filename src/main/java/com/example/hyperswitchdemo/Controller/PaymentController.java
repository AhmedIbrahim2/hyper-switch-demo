package com.example.hyperswitchdemo.Controller;

import com.example.hyperswitchdemo.service.PaymentService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:4200/payement"})
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create-payment")
    public ResponseEntity<?> createPayment() throws JSONException {
        String payload = "{ \"amount\": 100, \"currency\": \"USD\" }";
        String responseString = paymentService.createPayment(payload);
        JSONObject responseJson = new JSONObject(responseString);

        String clientSecret = responseJson.getString("client_secret");

        JSONObject finalResponse = new JSONObject();
        finalResponse.put("clientSecret", clientSecret);

        return ResponseEntity.ok(finalResponse.toString());
    }
}
