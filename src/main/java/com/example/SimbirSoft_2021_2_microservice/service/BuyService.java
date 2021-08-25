package com.example.SimbirSoft_2021_2_microservice.service;

import com.example.SimbirSoft_2021_2_microservice.Dto.BuyDto;
import com.example.SimbirSoft_2021_2_microservice.entity.BalanceEntity;
import com.example.SimbirSoft_2021_2_microservice.entity.UserEntity;
import com.example.SimbirSoft_2021_2_microservice.exception.BalanceExistsException;
import com.example.SimbirSoft_2021_2_microservice.exception.BalanceInsufficientFundsException;
import com.example.SimbirSoft_2021_2_microservice.exception.UserNotFoundException;
import com.example.SimbirSoft_2021_2_microservice.exception.UserPasswordNotValidateException;
import com.example.SimbirSoft_2021_2_microservice.repository.BalanceCrud;
import com.example.SimbirSoft_2021_2_microservice.repository.UserCrud;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.JSONObject;

// 1 способ
//@RequiredArgsConstructor
@Service
@Slf4j
public class BuyService {

    // 2 способ
    //@Autowired
    //private UserCrud userCrud;

    private final BalanceCrud balanceCrud; // создаём интерфейс для взаимодействия с бд
    private final UserCrud userCrud;

    private final Long priceToProject = 1000L;

    // 3 способ
    public BuyService(BalanceCrud balanceCrud, UserCrud userCrud) {
        this.balanceCrud = balanceCrud;
        this.userCrud = userCrud;
    }

    public String buyProject(BuyDto buyDto) throws Exception {
        UserEntity userEntity = userCrud.findByEmail(buyDto.getEmail());

        //  проверка на то что вообще существуют
        if(userEntity==null){
            throw new UserNotFoundException();
        }
        //  проверка
        if (userEntity.getPassword().equals(buyDto.getPassword())){
            throw new UserPasswordNotValidateException();
        }
        BalanceEntity balanceEntity = balanceCrud.findByUserId(userEntity.getUserId());
        if (balanceEntity.getBalance()<priceToProject){
            throw new BalanceInsufficientFundsException();
        }

        String token = getTokenLogin(buyDto);
        System.out.println("------------------15-------------------");
        return token;
    }

    private String getTokenLogin(BuyDto buyDto) throws Exception {

        // Наша ссылка, куда мы посылаем наш запрос, для получения токена
        String url = "http://localhost:8070/control/login";
        URL obj = new URL(url);

        System.out.println("------------------1-------------------");

        // составляем тело JSON, для отправки
        Map<String,Object> params = new LinkedHashMap<>(); // создаём массив
        params.put("username", buyDto.getEmail()); // закидываем параметры
        params.put("password", buyDto.getPassword());

        System.out.println("------------------2-------------------");

        // сборщик строк, который передаём как JSON объект
        StringBuilder postData = new StringBuilder();

        // при помощи цикла записываем параметры в сборщик строк
        for (Map.Entry<String,Object> param : params.entrySet()) {
            if (postData.length() != 0) postData.append('&');
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }

        System.out.println("------------------3-------------------");

        byte[] postDataBytes = postData.toString().getBytes("UTF-8"); // переводим в байт код

        // устанавливаем соединение
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        conn.setDoOutput(true);
        conn.getOutputStream().write(postDataBytes);

        // считываем полученные значения
        Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

        System.out.println(4);

        StringBuilder sb = new StringBuilder();
        for (int c; (c = in.read()) >= 0;)
            sb.append((char)c);

        String response = sb.toString();
        System.out.println(response);

        JSONObject myResponse = new JSONObject(response.toString());

        return myResponse.getString("token");
    }
}
