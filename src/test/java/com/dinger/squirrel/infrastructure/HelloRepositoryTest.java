package com.dinger.squirrel.infrastructure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.CollectionUtils;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class HelloRepositoryTest {
    @Autowired
    private HelloRepository helloRepository;

    @Test
    public void HELLO_저장_및_조회() {
        // given
        final HelloEntity hello = HelloEntity.builder()
                .title("제목99")
                .contents("내용99")
                .build();
        // when
        helloRepository.save(hello);
        final List<HelloEntity> helloList = helloRepository.findAll();

        // then
        Assertions.assertFalse(CollectionUtils.isEmpty(helloList));
        Assertions.assertTrue(helloList.size() > 0);
    }
}
