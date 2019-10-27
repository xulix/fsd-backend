package com.fsd.mod.training;

import com.fsd.mod.training.entity.Training;
import com.fsd.mod.training.repository.TrainingRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrainingTest {

    @Autowired
    private TrainingRepository trainingRepository;

    @Test
    public void test1() {
        assertNotNull(trainingRepository);
        Training training = new Training();
        training.setStatus("New");
        training = trainingRepository.save(training);
        System.out.println(training.getStatus());
    }

    @Test
    public void test2() {
        assertNotNull(trainingRepository);
        List<Training> trainings = trainingRepository.findAll();
        System.out.println(trainings.size());
    }

}
