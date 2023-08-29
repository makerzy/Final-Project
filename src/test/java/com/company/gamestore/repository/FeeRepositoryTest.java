package com.company.gamestore.repository;


import com.company.gamestore.model.Fee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FeeRepositoryTest {

    @Autowired
    FeeRepository feeRepository;
    private Fee fee;

    @Test
    public void getFeeByState(){

    }
}
